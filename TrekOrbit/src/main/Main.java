package main;

import controller.AccessController;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessController controladorAcceso = new AccessController();
		LogIn login = new LogIn(controladorAcceso);
		login.setVisible(true);
	}
}
