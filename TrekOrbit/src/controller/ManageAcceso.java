package controller;

import model.Usuario;

public interface ManageAcceso {

	public Usuario LogIn(String user, String passwd);
	
	public boolean altaUsuario(String user,String dni,String passwd,int edad);
}
