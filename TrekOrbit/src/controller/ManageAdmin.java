package controller;

import java.util.ArrayList;

import model.Activity;
import model.Planet;

public interface ManageAdmin {

	public Planet getPlanet(String Nick);
	public boolean changePlanetAvailability(Planet p);
	public ArrayList<Activity> addPlanetActivity();
	public ArrayList<Activity> removePlanetActivity();
}
