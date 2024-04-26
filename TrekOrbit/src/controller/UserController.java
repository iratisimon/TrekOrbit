package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Ser;
import model.User;


public class UserController implements ManageUser {
	private Connection con;
	private PreparedStatement stmt;
	final String OBTENERUSUARIO = "SELECT S.Nick, S.Passwd, U.Nombre, U.Raza FROM SER S, USUARIO U WHERE S.ID = U.ID_Usuario AND S.Nick=? AND S.Passwd=?";
	final String MODIFICARUSUARIO = "UPDATE SER SET Nick=?, Passwd=? WHERE Nick=? AND Passwd=?";
	
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

	public User mostrarDatosUser(Ser ser) {
	    User usuario = null;
	    this.openConnection();
	    
	    try {
	        stmt = con.prepareStatement(OBTENERUSUARIO);
	        stmt.setString(1, ser.getNick());
	        stmt.setString(2, ser.getPasswd());
	        ResultSet resultSet = stmt.executeQuery();
	        
	        if (resultSet.next()) {
	            // Crear un objeto User y asignar los valores obtenidos del resultado
	            usuario = new User();
	            usuario.setNick(resultSet.getString("Nick"));
	            usuario.setPasswd(resultSet.getString("Passwd"));
	            usuario.setNombre(resultSet.getString("Nombre"));
	            usuario.setRaza(resultSet.getString("Raza"));
	            // Asignar otros campos según sea necesario
	        } else {
	            // Si el resultado está vacío, el usuario no existe
	            System.out.println("El usuario no existe.");
	            return null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            this.closeConnection();
	        } catch (SQLException e) {
	            System.out.println("Error en el cierre de la conexión");
	            e.printStackTrace();
	        }
	    }
	   
	    return usuario;
	}
  	
	@Override
	public boolean modificarDatosUser(String nickOriginal, String passwdOriginal, String nickNew, String passwd) {
	    boolean modificado = false;
	    this.openConnection();
	    Ser ser = new Ser();
	    
	    try {
	        PreparedStatement stmt = con.prepareStatement(MODIFICARUSUARIO);
	        stmt.setString(1, nickNew);
	        stmt.setString(2, passwd);
	        stmt.setString(3, nickOriginal);
	        stmt.setString(4, passwdOriginal); // Agregar la contraseña original como parámetro
	        int rowsAffected = stmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            modificado = true;
	            // Actualizar la referencia al usuario con el nuevo nombre de usuario y contraseña
	            ser.setNick(nickNew);
	            ser.setPasswd(passwd);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al intentar modificar el usuario.");
	        e.printStackTrace();
	    } finally {
	        try {
	            this.closeConnection();
	        } catch (SQLException e) {
	            System.out.println("Error en el cierre de la conexión.");
	            e.printStackTrace();
	        }
	    }

	    return modificado;
	}


}