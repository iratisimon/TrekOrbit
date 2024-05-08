package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ser;
import ownExceptions.UsuarioExistenteException;
import ownExceptions.SerNoEncontradoException;

/**
 * Esta clase proporciona métodos para controlar el acceso y la gestión de usuarios en la base de datos.
 * Implementa la interfaz ManageAccess.
 */
public class AccessController implements ManageAccess {
	private Connection con;
	private PreparedStatement stmt;
	private DBConnection conController = new DBConnection();
	final String OBTENERSER = "SELECT * FROM SER WHERE Nick=? AND Passwd=?";
	final String OBTENERUSUARIO = "SELECT * FROM USUARIO WHERE ID_Usuario = (SELECT ID FROM SER WHERE Nick=?)";
	final String ALTASER = "INSERT INTO SER(ID, Passwd, Nick, EsAdmin) VALUES (?, ?, ?, ?)";
	final String ALTAUSUARIO = "INSERT INTO USUARIO(ID_Usuario, Nombre, Raza) VALUES (?, ?, ?)";
	final String OBTENERULTIMOID = "SELECT ID_Usuario FROM USUARIO ORDER BY ID_Usuario DESC LIMIT 1";
	final String EXISTEID = "SELECT ID FROM SER WHERE ID=?";

	/**
     * Método para iniciar sesión de un usuario en el sistema.
     *
     * @param nick el nombre de usuario (nick) del usuario.
     * @param passwd la contraseña del usuario.
     * @return el objeto Ser que representa al usuario que ha iniciado sesión.
     * @throws SerNoEncontradoException si el usuario no se encuentra en la base de datos.
     */
	@Override
	public Ser logIn(String nick, String passwd) throws SerNoEncontradoException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Ser ser = null;
		con = conController.openConnection();

		try {
			stmt = con.prepareStatement(OBTENERSER);
			stmt.setString(1, nick);
			stmt.setString(2, passwd);
			rs = stmt.executeQuery();

			if (rs.next()) {
				ser = new Ser();
				ser.setId(rs.getString("ID"));
				ser.setNick(nick);
				ser.setPasswd(passwd);
				ser.setAdmin(rs.getBoolean("EsAdmin"));
			} else {
				throw new SerNoEncontradoException("Ser no encontrado");
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
		return ser;
	}

	/**
     * Método para registrar un nuevo usuario en el sistema.
     *
     * @param nombre el nombre completo del nuevo usuario.
     * @param nick el nombre de usuario (nick) del nuevo usuario.
     * @param raza la raza del nuevo usuario.
     * @param passwd la contraseña del nuevo usuario.
     * @return true si el usuario se ha registrado correctamente, false de lo contrario.
     * @throws UsuarioExistenteException si ya existe un usuario con el mismo nombre de usuario (nick).
     */
	@Override
	public boolean singUp(String nombre, String nick, String raza, String passwd) throws UsuarioExistenteException {
		// TODO Auto-generated method stub
		boolean modificado = false;
		String nuevoID = generateUserId(); // Generar un nuevo ID de usuario

		try {
			con = conController.openConnection();

			// Verificar si ya existe un usuario con el mismo nick
			if (existsUserNick(nick)) {
				throw new UsuarioExistenteException("El usuario con el nick '" + nick + "' ya existe.");
			} else {
				// Insertar en la tabla SER
				stmt = con.prepareStatement(ALTASER);
				stmt.setString(1, nuevoID);
				stmt.setString(2, passwd);
				stmt.setString(3, nick);
				stmt.setBoolean(4, false); // Asumiendo que el nuevo usuario no es admin por defecto
				int rowsAffected = stmt.executeUpdate(); // Ejecutar la inserción

				if (rowsAffected == 1) { // Verificar si se insertó correctamente en SER
					// Insertar en la tabla USUARIO
					stmt = con.prepareStatement(ALTAUSUARIO);
					stmt.setString(1, nuevoID);
					stmt.setString(2, nombre);
					stmt.setString(3, raza);
					rowsAffected = stmt.executeUpdate(); // Ejecutar la inserción

					if (rowsAffected == 1) { // Verificar si se insertó correctamente en USUARIO
						modificado = true;
					}
				}
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
		return modificado;
	}

	 /**
     * Método para generar un nuevo ID de usuario único para el registro de usuarios.
     *
     * @return el nuevo ID de usuario generado.
     */
	public String generateUserId() {
		// TODO Auto-generated method stub

		ResultSet rs = null;
		String nuevoID = null;

		try {
			con = conController.openConnection();
			stmt = con.prepareStatement(OBTENERULTIMOID);
			rs = stmt.executeQuery();

			// Determinar el próximo ID a generar
			String ultimoID = "S002"; // ID inicial por si no hay usuarios en la base de datos
			if (rs.next()) {
				ultimoID = rs.getString("ID_Usuario");
			}
			int numero = Integer.parseInt(ultimoID.substring(1)) + 1; // Obtener el número y sumarle 1
			nuevoID = "S" + String.format("%03d", numero); // Formatear el número para obtener el nuevo ID

			// Verificar si el nuevo ID ya existe en la base de datos
			while (existsUserId(nuevoID)) {
				numero++; // Incrementar el número si el ID ya existe
				nuevoID = "S" + String.format("%03d", numero); // Generar un nuevo ID
			}

		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nuevoID;
	}

	/**
     * Método para verificar si un ID de usuario ya existe en la base de datos.
     *
     * @param id el ID de usuario a verificar.
     * @return true si el ID de usuario ya existe en la base de datos, false de lo contrario.
     */
	public boolean existsUserId(String id) {
		// TODO Auto-generated method stub
		boolean existe = false;

		// Consultar si el ID de usuario existe en la tabla SER
		try {
			stmt = con.prepareStatement(EXISTEID);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			// Verificar si se encontró algún resultado
			existe = rs.next();

			rs.close();
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		return existe;
	}

	/**
     * Método para verificar si un nombre de usuario (nick) ya existe en la base de datos.
     *
     * @param nick el nombre de usuario (nick) a verificar.
     * @return true si el nombre de usuario ya existe en la base de datos, false de lo contrario.
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL.
     */
	public boolean existsUserNick(String nick) throws SQLException {
		boolean existe = false;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(OBTENERUSUARIO);
			stmt.setString(1, nick);
			rs = stmt.executeQuery();

			if (rs.next()) {
				existe = true;
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return existe;
	}
}