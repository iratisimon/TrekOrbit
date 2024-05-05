package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Travel {
	private static int ultimoCodViaje = 003;
	private String cod_viaje;
	private Planeta origen;
	private LocalDate fechaViaje;
	private Planeta nom_destino;
	private ArrayList<String> actividades;

	public Travel(String cod_viaje, Planeta origen, LocalDate fecha, Planeta destino) {

		this.cod_viaje = cod_viaje;
		this.origen = origen;
		this.fechaViaje = fecha;
		this.actividades = new ArrayList<String>();
	}

	public Travel() {
		this.cod_viaje = null;
		this.origen = null;
		this.fechaViaje = null;
		this.nom_destino = null;
		this.actividades = new ArrayList<String>();

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

	public LocalDate getFechaViaje() {
		return fechaViaje;
	}

	public void setFechaViaje(LocalDate fecha) {
		this.fechaViaje = fecha;
	}

	public Planeta getNom_destino() {
		return nom_destino;
	}

	public void setNom_destino(Planeta nom_destino) {
		this.nom_destino = nom_destino;
	}

	public ArrayList<String> getActividades() {
		return actividades;
	}

	public void setActividades(ArrayList<String> actividades) {
		this.actividades = actividades;
	}

	public static String generarIDViaje() {
		String letra = "V";
		int numero = ++ultimoCodViaje;
		String numeroMenor = "00";
		String numeroMayor = "0";

		if (ultimoCodViaje < 10) {
			String id_usuario = letra + numeroMenor + numero;
			return id_usuario;
		} else {
			String id_usuario = letra + numeroMayor + numero;
			return id_usuario;
		}
	}

}
