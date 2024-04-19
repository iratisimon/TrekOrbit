package model;

public class Travel {
	private String cod_viaje;
	private String origen;
	private double duracion;
	
	public Travel(String cod_viaje, String origen, double duracion) {
		this.cod_viaje = cod_viaje;
		this.origen = origen;
		this.duracion = duracion;
	}

	public String getCod_viaje() {
		return cod_viaje;
	}

	public void setCod_viaje(String cod_viaje) {
		this.cod_viaje = cod_viaje;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

}
