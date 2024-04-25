package model;

public class User extends Ser {
	private String nombre;
	private String raza;

	public User(String passwrd, String nick, String nombre, String raza, boolean admin) {
		super(passwrd, nick,admin);
		this.nombre = nombre;
		this.raza = raza;
		this.id = generarIDUsuario();
	}

	public User() {
		
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
