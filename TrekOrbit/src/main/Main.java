package main;

import java.time.LocalDate;

import controller.AdminController;
import model.Admin;
import model.Ser;
import model.User;
import view.AdminView;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Pruebas del id autogenerado
		 /*LocalDate fechaIngresoAdmin1 = LocalDate.of(2024, 4, 17);
	     LocalDate fechaIngresoAdmin2 = LocalDate.of(2024, 4, 18);
		
		 User usuario1 = new User("contraseña123", "usuario1", "irati", "humana");
		 User usuario2 = new User("password456", "usuario2", "meylin", "humana");
		        
		 Admin admin1 = new Admin("adminPass", "admin1",fechaIngresoAdmin1);
		 Admin admin2 = new Admin("admin123", "admin2",fechaIngresoAdmin2);
		        
		 System.out.println("ID de usuario 1: " + usuario1.getId());
		 System.out.println("ID de usuario 2: " + usuario2.getId());
		 System.out.println("ID de administrador 1: " + admin1.getId());
		 System.out.println("ID de administrador 2: " + admin2.getId());*/
		
		Ser admin1= new Ser("A001","Hermes", "abcd*1234");
		Ser admin3= new Ser("A003", "Gaia","abcd*1234");
		AdminController c = new AdminController();
		AdminView frame = new AdminView(c,admin3);
		frame.setVisible(true);
	}

}
