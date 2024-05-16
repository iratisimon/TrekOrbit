package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Activity;
import model.Planet;
import model.Planeta;

/**
 * Esta clase proporciona métodos para que el administrador del sistema gestione los planetas y las actividades asociadas a ellos.
 */
public class AdminController implements ManageAdmin{
	
	private Connection con;
	private PreparedStatement stmt;
	private DBConnection conController = new DBConnection();
	final String OBTENERPLANETA = "SELECT * FROM PLANETA WHERE ID_Admin = (SELECT ID_Admin FROM ADMINISTRADOR WHERE ID_Admin = ( SELECT ID FROM SER WHERE Nick = ?))";
	final String CAMBIARDISPLANETA = "UPDATE PLANETA SET Disponibilidad= NOT Disponibilidad WHERE Nombre = ?";
	final String EXISTEACTIVIDAD = "SELECT Nombre_Act FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ?";
	final String ACTIVIDADESDISPONIBLES ="SELECT Nombre FROM ACTIVIDAD WHERE Nombre NOT IN (SELECT Nombre_Act FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ?)";
	final String QUITARACTIVIDAD = "DELETE FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ? AND Nombre_Act = ?";;
	final String AÑADIRACTIVIDAD = "INSERT INTO PLANETA_ACTIVIDAD (Nombre_Planeta, Nombre_Act) VALUES (?, ?)";

	/**
     * Obtiene las actividades disponibles que aún no están asociadas a un planeta específico.
     *
     * @param planetName el nombre del planeta del que se desea obtener las actividades disponibles.
     * @return una lista de actividades disponibles.
     */
	public ArrayList<Activity> getAvailableActivities(String planetName) {
        ArrayList<Activity> availableActivities = new ArrayList<>();
		    con = conController.openConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(ACTIVIDADESDISPONIBLES);
            stmt.setString(1, planetName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String activityName = rs.getString("Nombre");
                String activityDescription = ""; // Deberías obtener la descripción de la actividad desde la base de datos, o desde otro lugar si corresponde
                Activity activity = new Activity(activityName, activityDescription);
                availableActivities.add(activity);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener actividades disponibles para añadir: " + e.getMessage());
        }
        return availableActivities;
    }
	
	 /**
     * Obtiene las actividades asociadas a un planeta específico.
     *
     * @param nombrePlaneta el nombre del planeta del que se desean obtener las actividades.
     * @return una lista de nombres de actividades asociadas al planeta.
     */
	@Override
	public ArrayList<String> getPlanetActivities(String nombrePlaneta) {
        ArrayList<String> activities = new ArrayList<>();
        con = conController.openConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(EXISTEACTIVIDAD);
            stmt.setString(1, nombrePlaneta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                activities.add(rs.getString("Nombre_Act"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener actividades del planeta: " + e.getMessage());
        } finally {
            try {
            	conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return activities;
    }

	/**
     * Agrega una actividad a un planeta específico.
     *
     * @param planetName el nombre del planeta al que se desea agregar la actividad.
     * @param selectedActivity el nombre de la actividad que se desea agregar.
     * @return true si se agregó la actividad correctamente, false de lo contrario.
     */
	public boolean addPlanetActivity(String planetName, String selectedActivity) {
		// TODO Auto-generated method stub
		con = conController.openConnection();
		try {
            stmt = con.prepareStatement(AÑADIRACTIVIDAD);
            stmt.setString(1, planetName);
            stmt.setString(2, selectedActivity);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error adding activity to planet: " + e.getMessage());
            return false;
        } finally {
        	try {
        		conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	/**
     * Elimina una actividad de un planeta específico.
     *
     * @param planetName el nombre del planeta del que se desea eliminar la actividad.
     * @param activityName el nombre de la actividad que se desea eliminar.
     * @return true si se eliminó la actividad correctamente, false de lo contrario.
     */
	@Override
	public boolean removePlanetActivity(String planetName, String activityName) {
		con = conController.openConnection();
		try {
	    	stmt = con.prepareStatement(QUITARACTIVIDAD);
	    	stmt.setString(1, planetName);
	    	stmt.setString(2, activityName);
	        int rowsAffected = stmt.executeUpdate();
	        stmt.close();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        System.out.println("Error removing activity from planet: " + e.getMessage());
	        return false;
	    }finally {
        	try {
        		conController.closeConnection(stmt, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	/**
     * Obtiene información sobre el planeta asignado al administrador especificado.
     *
     * @param nick el nombre de usuario (nick) del administrador.
     * @return un objeto Planet que representa el planeta asignado al administrador.
     */
	@Override
	public Planet getPlanetFromAdmin(String nick) {
		// TODO Auto-generated method stub
		Planeta planetEnum = null;
		Planet planeta=null;
		con = conController.openConnection();
		try {
			stmt = con.prepareStatement(OBTENERPLANETA);
			stmt.setString(1, nick);
			ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String nombre = rs.getString("Nombre");
	            planetEnum=Planeta.valueOf(nombre.toUpperCase());
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
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return planeta;
	}
	
	/**
     * Cambia la disponibilidad del planeta especificado.
     *
     * @param planetName el nombre del planeta del que se desea cambiar la disponibilidad.
     * @return true si se cambió la disponibilidad correctamente, false de lo contrario.
     */
	@Override
	public boolean changePlanetAvailability(String planetName) {
		// TODO Auto-generated method stub
		boolean cambiadaDisponibilidad = false;
		con = conController.openConnection();
		try {
			stmt = con.prepareStatement(CAMBIARDISPLANETA);
			stmt.setString(1, planetName);
			if (stmt.executeUpdate() == 1) {
				cambiadaDisponibilidad = true;
			}
		}catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		}
		try {
			conController.closeConnection(stmt, con);
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return cambiadaDisponibilidad;
	}

}