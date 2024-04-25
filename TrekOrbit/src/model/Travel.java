
package model;

public class Travel {
	private static int ultimoCodViaje = 003;
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
	
	public static String generarIDUsuario() {
		String letra = "V";
		int numero = ++ultimoCodViaje;
		String numeroMenor="00";
		String numeroMayor="0";
		
		if (ultimoCodViaje <10) {
			String id_usuario = letra + numeroMenor + numero;
			return id_usuario;
		} else {
			String id_usuario = letra + numeroMayor + numero;
			return id_usuario;
		}
	}
}