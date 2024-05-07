package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ser;
import ownExceptions.UsuarioExistenteException;
import ownExceptions.SerNoEncontradoException;

public class AccessController implements ManageAccess {
	private Connection con;
	private PreparedStatement stmt;
	private DBConnectionController conController = new DBConnectionController();
	final String OBTENERSER = "SELECT * FROM SER WHERE Nick=? AND Passwd=?";
	final String OBTENERUSUARIO = "SELECT * FROM USUARIO WHERE ID_Usuario = (SELECT ID FROM SER WHERE Nick=?)";
	final String ALTASER = "INSERT INTO SER(ID, Passwd, Nick, EsAdmin) VALUES (?, ?, ?, ?)";
	final String ALTAUSUARIO = "INSERT INTO USUARIO(ID_Usuario, Nombre, Raza) VALUES (?, ?, ?)";
	final String OBTENERULTIMOID = "SELECT ID_Usuario FROM USUARIO ORDER BY ID_Usuario DESC LIMIT 1";
	final String EXISTEID = "SELECT ID FROM SER WHERE ID=?";

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

	@Override
	public boolean singUp(String nombre, String nick, String raza, String passwd) throws UsuarioExistenteException {
		// TODO Auto-generated method stub
		boolean modificado = false;
		String nuevoID = generarIdUsuario(); // Generar un nuevo ID de usuario

		try {
			con = conController.openConnection();

			// Verificar si ya existe un usuario con el mismo nick
			if (existeUsuarioConNick(nick)) {
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

	@Override
	public String generarIdUsuario() {
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
			while (existeIdUsuario(nuevoID)) {
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

	@Override
	public boolean existeIdUsuario(String id) {
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

	@Override
	public boolean existeUsuarioConNick(String nick) throws SQLException {
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