package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class ControladorAcceso implements ManageAcceso {

	

		private Connection con;
		private PreparedStatement stmt;
		final String OBTENERUSUARIO = "SELECT * FROM USUARIO WHERE Username= ? AND Passwd=?";
		final String MODIFICARUSUARIO = "UPDATE USUARIO SET DNI=?, Edad=? WHERE Username= ?";
		final String ALTAUSUARIO = "INSERT INTO USUARIO VALUES (?,?,?,?)";
		final String BAJAUSUARIO = "DELETE FROM USUARIO WHERE nick=?";

		private void openConnection() {
			try {
				String url = "jdbc:mysql://localhost:3306/usuarios?serverTimezone=Europe/Madrid&useSSL=false";
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
		public Usuario LogIn(String user, String passwd) {
			ResultSet rs = null;
			Usuario usuario = null;
			this.openConnection();
			try {
				stmt = con.prepareStatement(OBTENERUSUARIO);
				stmt.setString(1, user);
				stmt.setString(2, passwd);
				rs = stmt.executeQuery();
				// Si solo me devuelve uno, usamos if; si me devuelve mas de una linea, usamos
				// while
				if (rs.next()) {
					usuario = new Usuario();
					usuario.setNick(user);
					usuario.setPasswd(passwd);
					usuario.setNombre(rs.getString("Nombre"));
					usuario.setRaza(rs.getString("Raza"));
				} else {
					usuario = new Usuario();
					usuario.setNick("");
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
			return usuario;
		}
		
		public boolean altaUsuario(String user,String dni,String passwd,int edad) {
			boolean modificado = false;
			this.openConnection();
			try {
				stmt = con.prepareStatement(ALTAUSUARIO);
				stmt.setString(1, user);
				stmt.setString(2, passwd);
				stmt.setString(3, dni);
				stmt.setInt(4, edad);
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
