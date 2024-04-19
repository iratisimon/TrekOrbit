package principal;

import java.time.LocalDate;
import model.Administrador;
import model.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Pruebas del id autogenerado
		 LocalDate fechaIngresoAdmin1 = LocalDate.of(2024, 4, 17);
	     LocalDate fechaIngresoAdmin2 = LocalDate.of(2024, 4, 18);
		
		 Usuario usuario1 = new Usuario("contraseña123", "usuario1", "irati", "humana");
		 Usuario usuario2 = new Usuario("password456", "usuario2", "meylin", "humana");
		        
	

	}

}
