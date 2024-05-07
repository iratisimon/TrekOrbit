package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Activity;
import model.Planet;
import model.Planeta;

public class AdminController implements ManageAdmin{
	
	private Connection con;
	private PreparedStatement stmt;

	final String OBTENERPLANETA = "SELECT * FROM PLANETA WHERE ID_Admin = (SELECT ID_Admin FROM ADMINISTRADOR WHERE ID_Admin = ( SELECT ID FROM SER WHERE Nick = ?))";
	final String CAMBIARDISPLANETA = "UPDATE PLANETA SET Disponibilidad= NOT Disponibilidad WHERE Nombre = ?";
	final String EXISTEACTIVIDAD = "SELECT Nombre_Act FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ?";
	final String ACTIVIDADESDISPONIBLES ="SELECT Nombre FROM ACTIVIDAD WHERE Nombre NOT IN (SELECT Nombre_Act FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ?)";
	final String QUITARACTIVIDAD = "DELETE FROM PLANETA_ACTIVIDAD WHERE Nombre_Planeta = ? AND Nombre_Act = ?";;
	final String AÑADIRACTIVIDAD = "INSERT INTO PLANETA_ACTIVIDAD (Nombre_Planeta, Nombre_Act) VALUES (?, ?)";

	

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
	
	public ArrayList<Activity> getAvailableActivities(String planetName) {
        ArrayList<Activity> availableActivities = new ArrayList<>();
        this.openConnection();
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
	
	public ArrayList<String> getPlanetActivities(String nombrePlaneta) {
        ArrayList<String> activities = new ArrayList<>();
        this.openConnection();
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
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return activities;
    }


	@Override
	public boolean addPlanetActivity(String planetName, String selectedActivity) {
		// TODO Auto-generated method stub
		this.openConnection();
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
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	@Override
	public boolean removePlanetActivity(String planetName, String activityName) {
		this.openConnection();
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
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	@Override
	public Planet getPlanet(String nick) {
		// TODO Auto-generated method stub
		Planeta planetEnum = null;
		Planet planeta=null;
		this.openConnection();
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
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return planeta;
	}
	
	@Override
	public boolean changePlanetAvailability(String planetName) {
		// TODO Auto-generated method stub
		boolean cambiadaDisponibilidad = false;
		this.openConnection();
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
			this.closeConnection();
		} catch (SQLException e) {
			System.out.println("Error en el cierre de la Base de Datos");
			e.printStackTrace();
		}
		return cambiadaDisponibilidad;
	}

}
