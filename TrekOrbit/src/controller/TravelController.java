package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Travel;

public class TravelController implements ManageTravel {
	private Connection con;
	private PreparedStatement stmt;
	
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
	public boolean buyTrip() {
		ResultSet rs = null;
		Travel trip = null;
		this.openConnection();
		
		try {
			this.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Travel seeTrip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelTrip() {
		// TODO Auto-generated method stub
		return false;
	}
}
