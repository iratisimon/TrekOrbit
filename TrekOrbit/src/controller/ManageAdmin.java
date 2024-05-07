package controller;

import java.util.ArrayList;

import model.Activity;
import model.Planet;

public interface ManageAdmin {

	public Planet getPlanet(String Nick);
	public boolean changePlanetAvailability(String planetName);
	public ArrayList<Activity> getAvailableActivities(String planetName);
	public boolean addPlanetActivity(String planetName, String selectedActivity);
	public boolean removePlanetActivity(String planetName, String activityName);
}
