package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Activity;

public class AdminController implements ManageAdmin{
	
	private Connection con;
	private PreparedStatement stmt;
	final String CAMBIARDISPLANETA = "INSERT INTO VIAJE VALUES (?,?,?,?,?)";
	final String QUITARACTIVIDAD = "DELETE FROM VIAJE WHERE Cod_Viaje=?";
	final String AÑADIRACTIVIDAD = "DELETE FROM VIAJE WHERE Cod_Viaje=?";

	

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
	public boolean changePlanetAvailability() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Activity> modifyPlanetActivities() {
		// TODO Auto-generated method stub
		return null;
	}

}
