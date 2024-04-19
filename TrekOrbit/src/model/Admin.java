package model;

import java.time.LocalDate;

public class Admin extends Ser{
	private LocalDate fechaIngreso;
	
	public Admin(String passwrd, String nom_user, LocalDate fechaIngreso) {
		super(passwrd, nom_user);
		this.fechaIngreso = fechaIngreso;
		this.id = generarIDAdmin();
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}
