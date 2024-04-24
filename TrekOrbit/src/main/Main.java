package main;

import controller.TravelController;
import view.BuyTrip;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TravelController controlador = new TravelController();
		BuyTrip bt = new BuyTrip(controlador);
		bt.setVisible(true);

	}

}
