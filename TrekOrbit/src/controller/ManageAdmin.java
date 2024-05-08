package controller;

import java.util.ArrayList;
import model.Planet;

public interface ManageAdmin {
	public Planet getPlanetFromAdmin(String Nick);
	public boolean changePlanetAvailability(String planetName);
	public boolean removePlanetActivity(String planetName, String activityName);
	public ArrayList<String> getPlanetActivities(String nombrePlaneta);
}