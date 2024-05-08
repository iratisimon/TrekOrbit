package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	 /**
     * Abre una conexion con la base de datos.
     */
	public Connection openConnection() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			String url = "jdbc:mysql://localhost:3306/TREKORBIT?serverTimezone=Europe/Madrid&useSSL=false";
			con = DriverManager.getConnection(url, "root", "abcd*1234");
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
		return con;
	}
	/**
     * Cierra la conexion con la base de datos.
     * 
     * @throws SQLException si ocurre un error al cerrar la conexión
     */
	public void closeConnection(PreparedStatement stmt, Connection con) throws SQLException {
		System.out.println("Conexion cerrada");
		if (stmt != null)
			stmt.close();
		if (con != null)
			con.close();
		System.out.println("--------------------");
	}
}
