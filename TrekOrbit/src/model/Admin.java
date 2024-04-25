package model;

import java.time.LocalDate;

public class Admin extends Ser{
	private LocalDate fechaIngreso;

	public Admin(String passwd, String nick, boolean admin, LocalDate fechaIngreso) {
		super(passwd, nick, admin);
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}

