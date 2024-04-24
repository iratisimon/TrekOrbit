package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserController implements ManageUser {
	private Connection con;
	private PreparedStatement stmt;
	final String OBTENERUSUARIO = "SELECT * FROM USUARIO WHERE ID_Usuario=(SELECT ID FROM SER WHERE Nick=? AND Passwd=?)";
	final String MODIFICARUSUARIO = "UPDATE SER SET Nick=?, Passwd=? WHERE Nick=?";
	
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
	public User mostrarDatosUser(User user) {
		// TODO Auto-generated method stub 
		User usuario = null;
		this.openConnection();
		
		try {
            // Llamar al procedimiento almacenado
            CallableStatement statement = con.prepareCall("{CALL VerDatosPersonales(?)}");
            statement.setString(1, user.getNick()); // Pasar el nombre de usuario como parámetro
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Crear un objeto User y asignar los valores obtenidos del resultado
                usuario = new User();
                usuario.setId(resultSet.getString("ID_Usuario"));
                usuario.setNick(resultSet.getString("Nick"));
                usuario.setRaza(resultSet.getString("Raza"));
                usuario.setNombre(resultSet.getString("Nombre"));
                
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
	public boolean modificarDatosUser(String nick, String passwd) {
		// TODO Auto-generated method stub
		boolean modificado = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(MODIFICARUSUARIO);
			stmt.setString(1, nick);
			stmt.setString(2, passwd);
			stmt.setString(3, nick);
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
