package model;

public class User extends Ser {
	private String nombre;
	private String raza;

	public User(String id, String passwd, String nick, boolean admin, String nombre, String raza) {
		super(id, passwd, nick, admin);
		this.nombre = nombre;
		this.raza = raza;
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
