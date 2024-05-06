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
>>>>>>> 47f94975d4b6f33163bd7c00da3030a4ff90e418
		AccessController controladorAcceso = new AccessController();
		LogIn login = new LogIn(controladorAcceso);
		login.setVisible(true);

	}
}
