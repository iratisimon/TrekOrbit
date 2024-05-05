package controller;

import java.sql.*;
import java.sql.Date;
import java.util.*;


import model.Planet;
import model.Planeta;
import model.Travel;

public class TravelController implements ManageTravel {
	private Connection con;
	private PreparedStatement stmt;
	final String COMPRARVIAJE = "INSERT INTO VIAJE VALUES (?,?,?,?,?)";
	final String CANCELARVIAJE = "DELETE FROM VIAJE WHERE Cod_Viaje=?";
	final String VERVIAJES = "CALL  VerViajesComprados(?)";
	final String OBTENERPLANETA = "SELECT * FROM PLANETA WHERE Nombre = ?";
	final String EXISTEACTIVIDAD = "SELECT Nombre_Act FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ?";
	final String DISPONIBILIDADPLANETAS = "SELECT Disponibilidad FROM PLANETA WHERE Nombre = ? ";


	private void openConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/TREKORBIT?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	private void closeConnection() throws SQLException {
		System.out.println("Conexion cerrada");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("--------------------");
	}

	@Override
	public boolean buyTrip(String origen, Date fechaViaje, String nombrePlaneta, String idUsuarios) {
		boolean compraRealizada = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(COMPRARVIAJE);
			stmt.setString(1, getNextTravelCode());
			stmt.setString(2, origen);
			stmt.setDate(3, fechaViaje);
			stmt.setString(4, nombrePlaneta);
			stmt.setString(5, idUsuarios);
			if (stmt.executeUpdate() == 1) {
				compraRealizada = true;
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return compraRealizada;
	}

	@Override
	public ArrayList<Travel> seeTrip(String nombre) {
		CallableStatement cstmt;
		ResultSet rs = null;
		Travel trip = null;
		ArrayList<Travel> trips = null;
		this.openConnection();
		try {
			cstmt = con.prepareCall(VERVIAJES);
			cstmt.setString(1, nombre);
			rs = cstmt.executeQuery();
			trips = new ArrayList<Travel>();
			while (rs.next()) {
				trip = new Travel();
				trip.setCod_viaje(rs.getString("cod_viaje"));
				String originPlanet = rs.getString("Origen");
				Planeta oriPlanet = convertToPlanetEnum(originPlanet);
				trip.setOrigen(oriPlanet);
				trip.setFechaViaje(rs.getDate("FechaViaje").toLocalDate());
				String planetName = rs.getString("Nombre_Planeta");
				Planeta destinationPlanet = convertToPlanetEnum(planetName);
				trip.setNom_destino(destinationPlanet);
				trips.add(trip);
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}

		return trips;
	}

	@Override
	public boolean cancelTrip(String codViaje) {
		boolean cancelado = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(CANCELARVIAJE);
			stmt.setString(1, codViaje);
			if (stmt.executeUpdate() == 1) {
				cancelado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return cancelado;
	}

	@Override
	public Planet getPlanet(String planetName) {
		Planet planet = null;
		ArrayList<String> actividades = getPlanetActivities(planetName);
		ResultSet rs = null;
		PreparedStatement stmt;
		this.openConnection();
		try {
			stmt = con.prepareStatement(OBTENERPLANETA);
			stmt.setString(1, planetName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				planet = new Planet();
				String name = rs.getString("Nombre");
				Planeta nomPla = convertToPlanetEnum(name);
				planet.setNom_planeta(nomPla);
				planet.setSuperficie(rs.getDouble("Superficie"));
				planet.setHabitantes(rs.getInt("Habitantes"));
				planet.setClima(rs.getString("Clima"));
				planet.setActivities(actividades);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return planet;
	}

	// Método para obtener el próximo código de viaje disponible en el formato VNNN
	public String getNextTravelCode() {
		String nextCode = null;
		String query = "SELECT MAX(Cod_Viaje) FROM VIAJE";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String maxCode = rs.getString(1);
				if (maxCode != null) {
					int number = Integer.parseInt(maxCode.substring(1)) + 1;
					nextCode = String.format("V%03d", number);
				} else {
					nextCode = "V001";
				}
			} else {
				nextCode = "V001";
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nextCode;
	}

	public ArrayList<String> getPlanetActivities(String nombrePlaneta) {
		ArrayList<String> activities = new ArrayList<>();
		this.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(EXISTEACTIVIDAD);
			stmt.setString(1, nombrePlaneta);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				activities.add(rs.getString("Nombre_Act"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener actividades del planeta: " + e.getMessage());
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return activities;
	}

	public HashMap<String, Boolean> getPlanetDisponibility(String nombrePlaneta) {
		HashMap<String, Boolean> disponibilidad = new HashMap<>();
		this.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(DISPONIBILIDADPLANETAS);
			stmt.setString(1, nombrePlaneta);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				boolean isAvailable = rs.getBoolean("Disponibilidad");
				disponibilidad.put(nombrePlaneta, isAvailable);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la disponibilidad del planeta: " + e.getMessage());
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disponibilidad;
	}

	// Método para convertir el nombre del planeta de String a enum
	private Planeta convertToPlanetEnum(String planetName) {
		return Planeta.valueOf(planetName.toUpperCase());
	}

}
