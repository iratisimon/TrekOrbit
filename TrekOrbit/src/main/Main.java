package main;

import controller.AccessController;
import model.User;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AccessController controladorAcceso = new AccessController();
		User usuario = new User();
		LogIn login = new LogIn(controladorAcceso, usuario);
		login.setVisible(true);
	}

}
