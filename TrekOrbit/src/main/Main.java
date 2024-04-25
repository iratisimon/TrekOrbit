package main;


import java.time.LocalDate;
import controller.AdminController;
import model.Admin;
import model.Ser;
import view.AdminView;
import controller.AccessController;
import model.User;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * //main Elbire Ser admin1= new Ser("A001","Hermes", "abcd*1234"); Ser admin3=
		 * new Ser("A003", "Gaia","abcd*1234"); AdminController c = new
		 * AdminController(); AdminView frame = new AdminView(null, c,admin3);
		 * frame.setVisible(true);
		 */

    //main Irati
		AccessController controladorAcceso = new AccessController();
		//User usuario = new User();
		//Admin admin = new Admin();
		Ser ser = new Ser();
		LogIn login = new LogIn(controladorAcceso, ser);
		login.setVisible(true);
	}

}
