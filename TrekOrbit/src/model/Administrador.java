package model;

import java.time.LocalDate;

public class Administrador extends Ser{
	private LocalDate fechaIngreso;
	
	public Administrador(String passwrd, String nom_user, LocalDate fechaIngreso) {
		super(passwrd, nom_user);
		this.fechaIngreso = fechaIngreso;
		
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}