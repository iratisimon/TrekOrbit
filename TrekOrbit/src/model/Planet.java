package model;

import java.util.ArrayList;

public class Planet {
	private Planeta nom_planeta;
	private double superficie;
	private int habitantes;
	private String clima;
	private boolean disponibilidad;
	private Admin administrador;
	private ArrayList<String> activities;

	public Planet(Planeta nom_planeta, double superficie, int habitantes, String clima, boolean disponibilidad) {
		this.nom_planeta = nom_planeta;
		this.superficie = superficie;
		this.habitantes = habitantes;
		this.clima = clima;
		this.disponibilidad = disponibilidad;
		this.activities= new ArrayList<>();

	}

	public Planet() {
		this.nom_planeta = null;
		this.superficie = 0;
		this.habitantes = 0;
		this.clima = null;
		this.disponibilidad = false;
		this.activities= new ArrayList<>();
	}

	public Planeta getNom_planeta() {
		return nom_planeta;
	}

	public void setNom_planeta(Planeta nom_planeta) {
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

	public ArrayList<String> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<String> activities) {
		this.activities = activities;
	}

	public Admin getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Admin administrador) {
		this.administrador = administrador;
	}

}
