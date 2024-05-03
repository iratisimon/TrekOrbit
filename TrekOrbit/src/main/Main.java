package main;

import controller.AccessController;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
		
		/*TravelController controlador = new TravelController();
		BuyTrip bt = new BuyTrip(controlador);
		bt.setVisible(true);*/
		
		AccessController controladorAcceso = new AccessController();
		LogIn login = new LogIn(controladorAcceso);
		login.setVisible(true);

	}
}
