package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Activity;
import model.Planet;
import model.Planeta;

public class AdminController implements ManageAdmin{
	
	private Connection con;
	private PreparedStatement stmt;
	final String OBTENERPLANETA = "SELECT * FROM PLANETA WHERE ID_Admin = (SELECT ID_Admin FROM ADMINISTRADOR WHERE ID_Admin = ( SELECT ID FROM SER WHERE Nick = ?))";
	final String CAMBIARDISPLANETA = "UPDATE PLANETA SET Disponibilidad= NOT Disponibilidad";
	final String QUITARACTIVIDAD = "";
	final String AÑADIRACTIVIDAD = "";

	

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
	public boolean changePlanetAvailability(Planet p) {
		// TODO Auto-generated method stub
		boolean cambiadaDisponibilidad = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(CAMBIARDISPLANETA);
			stmt.setString(1, p.getNom_planeta().name());
			if (stmt.executeUpdate() == 1) {
				cambiadaDisponibilidad = true;
			}
		}catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return cambiadaDisponibilidad;
	}


	@Override
	public ArrayList<Activity> addPlanetActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Activity> removePlanetActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planet getPlanet(String nick) {
		// TODO Auto-generated method stub
		Planeta planetEnum = null;
		Planet planeta=null;
		this.openConnection();
		try {
			stmt = con.prepareStatement(OBTENERPLANETA);
			stmt.setString(1, nick);
			ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String nombre = rs.getString("Nombre");
	            planetEnum=Planeta.valueOf(nombre);
	            double superficie = rs.getDouble("Superficie");
	            int habitantes = rs.getInt("Habitantes");
	            String clima = rs.getString("Clima");
	            boolean disponibilidad = rs.getBoolean("Disponibilidad");
	            // Crear un objeto Planeta con los datos obtenidos
	            planeta = new Planet(planetEnum, superficie, habitantes, clima, disponibilidad);
	        }
		}catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return planeta;
	}

}
