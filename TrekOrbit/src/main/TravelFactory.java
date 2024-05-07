package main;

import controller.ManageTravel;
import controller.TravelController;

public class TravelFactory {
	private static TravelController travel = new TravelController();
	
	public static ManageTravel getManageTravel() {
		return travel;
	}
}
