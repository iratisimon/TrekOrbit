package controller;

import model.Ser;
import model.User;

public interface ManageUser {
	public User mostrarDatosUser(Ser ser); //pide un usuario para mostrar sus datos
	public boolean modificarDatosUser(String nick, String passwd);//depende de lo que quieras modificar
}
