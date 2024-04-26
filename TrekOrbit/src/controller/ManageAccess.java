package controller;

import model.Ser;

public interface ManageAccess {
	public Ser logIn(String nick,String passwd);
	public boolean singUp(String nick,String nombre,String passwd,String raza);
	public String generarIdUsuario();
	public boolean existeIdUsuario(String id);
}
