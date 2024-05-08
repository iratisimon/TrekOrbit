package controller;

import model.Ser;
import model.User;

public interface ManageUser {
	public User showUserData(Ser ser); 
	public boolean modifyUserData(String nickOriginal, String passwdOriginal, String nickNew, String passwd);//depende de lo que quieras modificar
}
