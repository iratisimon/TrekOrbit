package main;

import controller.AccessController;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
<<<<<<< HEAD
		
		/*TravelController controlador = new TravelController();
		BuyTrip bt = new BuyTrip(controlador);
		bt.setVisible(true);*/
		
		//pruebas
		
=======
>>>>>>> e66a2ca2f2fbe06243572986279d2592b5599dc0
		AccessController controladorAcceso = new AccessController();
		LogIn login = new LogIn(controladorAcceso);
		login.setVisible(true);

	}
}
