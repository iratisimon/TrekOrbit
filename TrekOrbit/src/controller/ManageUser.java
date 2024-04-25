package controller;

import model.Ser;

public interface ManageUser {
	public Ser mostrarDatosUser(Ser ser); //pide un usuario para mostrar sus datos
	public boolean modificarDatosUser(String nick, String passwd);//depende de lo que quieras modificar
}
