package model;

public class Ser {
	private static int ultimoIDUsuario = 002;
	protected String id;
	protected String passwd;
	protected String nick;
	protected boolean admin;
	
	public Ser() {
		
	}
	
	public Ser(String passwd, String nick, boolean admin) {
		this.passwd = passwd;
		this.nick = nick;
		this.admin = admin;
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
  
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public static String generarIDUsuario() {
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
}
