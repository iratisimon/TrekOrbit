package model;

public class Usuario extends Ser {
	private String nombre;
	private String raza;

	public Usuario(String passwrd, String nick, String nombre, String raza) {
		super(passwrd, nick);
		this.nombre = nombre;
		this.raza = raza;
		this.id = generarIDUsuario();
	}
	

	public Usuario() {
		
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}
}
