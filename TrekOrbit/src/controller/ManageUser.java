package controller;

import model.Ser;
import model.User;

public interface ManageUser {
	public User mostrarDatosUser(Ser ser); //pide un usuario para mostrar sus datos
	public boolean modificarDatosUser(String nickOriginal, String passwdOriginal, String nickNew, String passwdNew);//depende de lo que quieras modificar
}
