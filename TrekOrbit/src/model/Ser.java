package model;

public class Ser {
	protected String id;
	protected String passwd;
	protected String nick;
	protected boolean admin;
	
	public Ser(String id, String passwd, String nick, boolean admin) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.nick = nick;
		this.admin = admin;
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
	
	public Ser() {
		
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
  
}
