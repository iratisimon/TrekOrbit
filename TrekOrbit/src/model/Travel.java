package model;

import java.util.ArrayList;

public class Travel {
	private String cod_viaje;
	private Planeta origen;
	private double duracion;
	private Planeta nom_destino;
	private ArrayList<Activity> actividades;

	public Travel(String cod_viaje, Planeta origen, double duracion, Planeta destino) {
		this.cod_viaje = cod_viaje;
		this.origen = origen;
		this.duracion = duracion;
		this.actividades = new ArrayList<Activity>();
	}

	public Travel() {
		this.cod_viaje = null;
		this.origen = null;
		this.duracion = 0;
		this.nom_destino = null;
		this.actividades = new ArrayList<Activity>();

	}

	public String getCod_viaje() {
		return cod_viaje;
	}

	public void setCod_viaje(String cod_viaje) {
		this.cod_viaje = cod_viaje;
	}

	public Planeta getOrigen() {
		return origen;
	}

	public void setOrigen(Planeta origen) {
		this.origen = origen;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Planeta getNom_destino() {
		return nom_destino;
	}

	public void setNom_destino(Planeta nom_destino) {
		this.nom_destino = nom_destino;
	}

	public ArrayList<Activity> getActividades() {
		return actividades;
	}

	public void setActividades(ArrayList<Activity> actividades) {
		this.actividades = actividades;
	}

}
