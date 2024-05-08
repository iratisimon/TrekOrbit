package controller;

import java.util.ArrayList;
import model.Planet;

/**
 * Esta interfaz proporciona métodos para que el administrador del sistema gestione la información de los planetas.
 */
public interface ManageAdmin {
	/**
     * Obtiene información sobre un planeta específico a partir del nombre de usuario del administrador.
     *
     * @param Nick el nombre de usuario (nick) del administrador que solicita la información del planeta.
     * @return un objeto Planet que representa la información del planeta obtenida.
     */
	public Planet getPlanetFromAdmin(String Nick);
	
	/**
     * Cambia la disponibilidad de un planeta en el sistema.
     *
     * @param planetName el nombre del planeta cuya disponibilidad se va a cambiar.
     * @return true si la disponibilidad del planeta se cambió correctamente, false de lo contrario.
     */
	public boolean changePlanetAvailability(String planetName);
	
	 /**
     * Elimina una actividad de un planeta específico en el sistema.
     *
     * @param planetName el nombre del planeta del que se eliminará la actividad.
     * @param activityName el nombre de la actividad que se eliminará del planeta.
     * @return true si la actividad se eliminó correctamente del planeta, false de lo contrario.
     */
	public boolean removePlanetActivity(String planetName, String activityName);
	
	 /**
     * Obtiene una lista de actividades asociadas a un planeta específico en el sistema.
     *
     * @param nombrePlaneta el nombre del planeta del que se desean obtener las actividades.
     * @return un ArrayList de Strings que contiene los nombres de las actividades asociadas al planeta.
     */
	public ArrayList<String> getPlanetActivities(String nombrePlaneta);
}