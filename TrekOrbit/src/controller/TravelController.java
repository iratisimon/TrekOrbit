package controller;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import model.Planet;
import model.Planeta;
import model.Travel;

/**
 * Esta clase actua como controlador para gestionar operaciones relacionadas con
 * viajes en el sistema. Implementa la interfaz ManageTravel para definir
 * metodos para comprar, ver y cancelar viajes, asi como obtener informacion
 * sobre planetas. El controlador se comunica con la base de datos para realizar
 * estas operaciones.
 * 
 * <p>
 * Nota: La clase asume una estructura de base de datos especifica y utiliza las
 * interfaces y modelos relacionados.
 * </p>
 * 
 * @author Meylin
 */
public class TravelController implements ManageTravel {
	private Connection con;
	private PreparedStatement stmt;
	private DBConnection conController = new DBConnection();
	final String COMPRARVIAJE = "INSERT INTO VIAJE VALUES (?,?,?,?,?)";
	final String COMPRAVIAJE2 = "INSERT INTO VIAJE_ACTIVIDAD VALUES (?,?)";
	final String CANCELARVIAJE = "DELETE FROM VIAJE WHERE Cod_Viaje=?";
	final String VERVIAJES = "CALL VerViajesComprados(?)";
	final String OBTENERPLANETA = "SELECT * FROM PLANETA WHERE Nombre = ?";
	final String EXISTEACTIVIDAD = "SELECT Nombre_Act FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ?";
	final String DISPONIBILIDADPLANETAS = "SELECT Disponibilidad FROM PLANETA WHERE Nombre = ? ";
	final String ELIMINARACTIVIDADES = "DELETE FROM viaje_actividad WHERE Cod_Viaje = ?";
	final String OBTENERACTIVIDADES = "SELECT Nombre_Act FROM VIAJE_ACTIVIDAD WHERE Cod_Viaje = ?";
	final String HAYVIAJES = "SELECT COUNT(*) FROM VIAJE WHERE ID_Usuario = ?";

	/**
	 * Realiza la compra de un viaje en la base de datos.
	 * 
	 * @param origen        el origen del viaje
	 * @param fechaViaje    la fecha del viaje
	 * @param nombrePlaneta el nombre del planeta de destino
	 * @param idUsuarios    el ID del usuario que compra el viaje
	 * @param actividades   las actividades asociadas al viaje
	 * @return true si la compra se realiza con exito, false si no
	 */
	@Override
	public boolean buyTrip(String origen, Date fechaViaje, String nombrePlaneta, String idUsuarios,
			ArrayList<String> actividades) {
		boolean compraRealizada = false;
		con = conController.openConnection();
		try {
			stmt = con.prepareStatement(COMPRARVIAJE);
			String cod = getNextTravelCode();
			stmt.setString(1, cod);
			stmt.setString(2, origen);
			stmt.setDate(3, fechaViaje);
			stmt.setString(4, nombrePlaneta);
			stmt.setString(5, idUsuarios);
			if (stmt.executeUpdate() == 1) {
				compraRealizada = true;
				if (!actividades.isEmpty()) {
					for (String act : actividades) {
						PreparedStatement stmt2 = con.prepareStatement(COMPRAVIAJE2);
						stmt2.setString(1, cod);
						stmt2.setString(2, act);
						stmt2.executeUpdate();
						stmt2.close();
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			try {
				conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la Base de Datos");
				e.printStackTrace();
			}
		}
		return compraRealizada;
	}

	/**
	 * Obtiene una lista de viajes asociados a un nombre de usuario.
	 * 
	 * @param nombre el nombre de usuario
	 * @return una lista de objetos Travel representando los viajes
	 */
	@Override
	public ArrayList<Travel> seeTrip(String nombre) {
		CallableStatement cstmt;
		ResultSet rs = null;
		Travel trip = null;
		ArrayList<Travel> trips = null;
		con = conController.openConnection();

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

				// Obtener actividades asociadas a este viaje
				trip.setActividades(getActivitiesForTrip(trip.getCod_viaje()));
				trips.add(trip);
			}

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}

		return trips;
	}

	/**
	 * Cancela un viaje en la base de datos.
	 * 
	 * @param codViaje el codigo del viaje a cancelar
	 * @return true si la cancelacion se realiza con exito, false si no
	 */
	@Override
	public boolean cancelTrip(String codViaje) {
		boolean cancelado = false;
		con = conController.openConnection();
		try {
			// Eliminar actividades asociadas al viaje
			deleteActivitiesForTrip(codViaje);

			// Luego, eliminar el viaje en sí
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
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return cancelado;
	}

	/**
	 * Obtiene informacion sobre un planeta de la base de datos.
	 * 
	 * @param planetName el nombre del planeta
	 * @return un objeto Planet representando el planeta
	 */
	@Override
	public Planet getPlanetData(String planetName) {
		Planet planet = null;
		ArrayList<String> actividades = getPlanetActivities(planetName);
		ResultSet rs = null;
		PreparedStatement stmt = null;
		con = conController.openConnection();
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
			e.printStackTrace();
		}
		try {
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return planet;
	}

	/**
	 * Obtiene el proximo codigo disponible para un viaje.
	 * 
	 * @return el proximo codigo de viaje disponible
	 */
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
			e.printStackTrace();
		}
		return nextCode;
	}

	/**
	 * Obtiene las actividades disponibles en un planeta de la base de datos.
	 * 
	 * @param nombrePlaneta el nombre del planeta
	 * @return una lista de actividades disponibles en el planeta
	 */

	public ArrayList<String> getPlanetActivities(String nombrePlaneta) {
		ArrayList<String> activities = new ArrayList<>();
		con = conController.openConnection();
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
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return activities;
	}

	/**
	 * Obtiene la disponibilidad de un planeta de la base de datos.
	 * 
	 * @param nombrePlaneta el nombre del planeta
	 * @return un mapa que indica la disponibilidad del planeta
	 */
	public HashMap<String, Boolean> getPlanetDisponibility(String nombrePlaneta) {
		HashMap<String, Boolean> disponibilidad = new HashMap<>();
		con = conController.openConnection();
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
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return disponibilidad;
	}

	/**
	 * Convierte el nombre de un planeta en un valor de enumeracion Planeta.
	 * 
	 * @param planetName el nombre del planeta
	 * @return el valor de enumeracion Planeta correspondiente
	 */
	private Planeta convertToPlanetEnum(String planetName) {
		return Planeta.valueOf(planetName.toUpperCase());
	}

	private ArrayList<String> getActivitiesForTrip(String codViaje) {
		ArrayList<String> activities = new ArrayList<>();
		con = conController.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(OBTENERACTIVIDADES);
			stmt.setString(1, codViaje);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				activities.add(rs.getString("Nombre_Act"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener actividades del viaje: " + e.getMessage());
		} finally {
			try {
				conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return activities;
	}

	public boolean deleteActivitiesForTrip(String codViaje) {
		try {
			PreparedStatement stmt = con.prepareStatement(ELIMINARACTIVIDADES);
			stmt.setString(1, codViaje);
			int rowsAffected = stmt.executeUpdate();
			// Devolver true si se eliminaron actividades correctamente
			stmt.close();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("Error al eliminar actividades del viaje: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkTrips(String id) {
		boolean hasTrips = false;
		con = conController.openConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(HAYVIAJES);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            int tripCount = rs.getInt(1); // Recupera el valor del recuento
	            hasTrips = tripCount > 0; // Verifica si el recuento es mayor que cero
	        }
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar si el usuario tiene viajes: " + e.getMessage());
		} finally {
			try {
				conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hasTrips;
	}

}
