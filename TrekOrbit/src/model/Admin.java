package model;

import java.time.LocalDate;

public class Admin extends Ser{
	private LocalDate fechaIngreso;
	
	public Admin(String passwrd, String nom_user, LocalDate fechaIngreso, boolean admin) {
		super(passwrd, nom_user, admin);
		this.fechaIngreso = fechaIngreso;
		
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}