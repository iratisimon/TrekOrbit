package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ser;
import model.User;

public class AccessController implements ManageAccess {
	private Connection con;
	private PreparedStatement stmt;
	final String OBTENERUSUARIO = "SELECT * FROM USUARIO WHERE ID_Usuario=(SELECT ID FROM Ser WHERE Nick=?)";
	final String ALTAUSUARIO = "INSERT INTO USUARIO VALUES (?,?,?,?,?)";
	
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
	public Ser logIn(String nick, String passwd) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Ser ser = null;
		
		this.openConnection();
		try {
			stmt = con.prepareStatement(OBTENERUSUARIO);
			stmt.setString(1, nick);
			rs = stmt.executeQuery();
			// Si solo me devuelve uno, usamos if; si me devuelve mas de una linea, usamos
			// while
			if (rs.next()) {
				ser = new User();
				ser.setNick(nick);
				ser.setPasswd(passwd);
			} else {
				ser = new User();
				ser.setNick("");
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
		return ser;
	}

	@Override
	public boolean singUp(String nick, String nombre, String passwd, String raza) {
		// TODO Auto-generated method stub
		boolean modificado = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(ALTAUSUARIO);
			stmt.setString(1, nick);
			stmt.setString(2, passwd);
			stmt.setString(3, nombre);
			stmt.setString(4, raza);
			if (stmt.executeUpdate() == 1) {
				modificado = true;
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
		return modificado;
	}

}