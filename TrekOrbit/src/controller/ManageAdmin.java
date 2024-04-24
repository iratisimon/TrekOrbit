package controller;

import java.util.ArrayList;

import model.Activity;
import model.Planet;

public interface ManageAdmin {

	public Planet getPlanet(String Nick);
	public boolean changePlanetAvailability(Planet p);
	public boolean addPlanetActivity(String planetName, String selectedActivity);
	public ArrayList<Activity> removePlanetActivity();
}
