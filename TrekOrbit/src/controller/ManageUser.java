package controller;

import model.Ser;
import model.User;

public interface ManageUser {
	public User mostrarDatosUser(Ser ser); 
	public boolean modificarDatosUser(String nickOriginal, String passwdOriginal, String nickNew, String passwdNew);//depende de lo que quieras modificar
}
