package model;

public class Activity {
	private String nombre;
	private String descripcion;
	
	public Activity(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	
	public Activity(String nombre) {
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
