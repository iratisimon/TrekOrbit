package main;

import java.time.LocalDate;
import model.Admin;
import model.User;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Pruebas del id autogenerado
		 LocalDate fechaIngresoAdmin1 = LocalDate.of(2024, 4, 17);
	     LocalDate fechaIngresoAdmin2 = LocalDate.of(2024, 4, 18);
		
		 User usuario1 = new User("contraseña123", "usuario1", "irati", "humana");
		 User usuario2 = new User("password456", "usuario2", "meylin", "humana");
		        
	

	}

}
