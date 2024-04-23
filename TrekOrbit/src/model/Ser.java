package model;

public class Ser {
	private static int ultimoIDUsuario = 002;
	private static int ultimoIDAdmin = 002;
	protected String id;
	protected String passwd;
	protected String nick;
	
	public Ser(String passwd, String nick) {
		this.passwd = passwd;
		this.nick = nick;
	}
	public Ser(String id, String nick, String passwd) {
		this.id=id;
		this.passwd = passwd;
		this.nick = nick;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	protected static String generarIDUsuario() {
		String letra = "S";
		int numero = ++ultimoIDUsuario;
		String numeroMenor="00";
		String numeroMayor="0";
		
		if (ultimoIDUsuario <10) {
			String id_usuario = letra + numeroMenor + numero;
			return id_usuario;
		} else {
			String id_usuario = letra + numeroMayor + numero;
			return id_usuario;
		}
	}
	
	protected static String generarIDAdmin() {
		String letra = "A";
		int numero = ++ultimoIDAdmin;
		String numeroMenor="00";
		String numeroMayor="0";
		
		if (ultimoIDAdmin <10) {
			String id_usuario = letra + numeroMenor + numero;
			return id_usuario;
		} else {
			String id_usuario = letra + numeroMayor + numero;
			return id_usuario;
		}
	}
}
