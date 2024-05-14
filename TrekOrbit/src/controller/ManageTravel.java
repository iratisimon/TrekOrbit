package controller;

import java.sql.Date;
import java.util.ArrayList;
import model.Planet;
import model.Travel;

/**
 * Interfaz que define las operaciones controlables por un gestor de viajes.
 * Esta interfaz proporciona metodos para gestionar operaciones relacionadas con
 * los viajes.
 * 
 * @author Meylin
 */
public interface ManageTravel {

	public boolean buyTrip(String origen, Date fechaViaje, String nombrePlaneta, String idUsuarios,
			ArrayList<String> actividades);

	/**
	 * Compra un viaje para un usuario.
	 * 
	 * @param origen        el origen del viaje
	 * @param fechaViaje    la fecha del viaje
	 * @param nombrePlaneta el planeta de destino
	 * @param idUsuarios    el ID del usuario
	 * @param actividades   las actividades incluidas en el viaje
	 * @return true si el viaje se compro correctamente, false en caso contrario
	 */

	public ArrayList<Travel> seeTrip(String nombre);

	/**
	 * Recupera una lista de viajes para un usuario específico.
	 *
	 * @param nombre el nombre del usuario
	 * @return un ArrayList de objetos Travel representando los viajes del usuario
	 */

	public boolean cancelTrip(String codViaje);

	/**
	 * Cancela un viaje.
	 *
	 * @param codViaje el codigo del viaje a cancelar
	 * @return true si el viaje se canceló correctamente, false en caso contrario
	 */

	public Planet getPlanetData(String planetName);
	/**
	 * Recupera información sobre un planeta especifico.
	 *
	 * @param planetName el nombre del planeta
	 * @return un objeto Planet representando el planeta
	 */
	
	public boolean checkTrips(String id);
}
