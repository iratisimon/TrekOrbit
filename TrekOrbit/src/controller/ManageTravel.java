package controller;

import java.sql.Date;
import java.util.ArrayList;

import model.Planet;
import model.Travel;

public interface ManageTravel {
	
	public boolean buyTrip(String origen,Date duracion,String nombrePlaneta,String idUsuarios);
	public ArrayList<Travel> seeTrip(String nombre);
	public boolean cancelTrip(String codViaje);
	public Planet getPlanet(String planetName);
	
	
}
