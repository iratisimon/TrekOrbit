package model;

public class Planet {
	private String nom_planeta;
	private double superficie;
	private int habitantes;
	private String clima;
	private boolean disponibilidad;
	
	public Planet(String nom_planeta, double superficie, int habitantes, String clima, boolean disponibilidad) {
		super();
		this.nom_planeta = nom_planeta;
		this.superficie = superficie;
		this.habitantes = habitantes;
		this.clima = clima;
		this.disponibilidad = disponibilidad;
	}

	public String getNom_planeta() {
		return nom_planeta;
	}

	public void setNom_planeta(String nom_planeta) {
		this.nom_planeta = nom_planeta;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
}
