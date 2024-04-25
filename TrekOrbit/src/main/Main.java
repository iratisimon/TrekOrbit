package main;

import model.Ser;
import controller.AccessController;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessController controladorAcceso = new AccessController();
		Ser ser = new Ser();
		LogIn login = new LogIn(controladorAcceso, ser);
		login.setVisible(true);
	}
}
