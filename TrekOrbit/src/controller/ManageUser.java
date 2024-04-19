package controller;

import model.Usuario;

public interface ManageUser {
	public Usuario mostrarDatosUser(Usuario user); //pide un usuario para mostrar sus datos
	public boolean modificarDatosUser(String nick, String passwd);//depende de lo que quieras modificar
}
