package controller;

import model.User;

public interface ManageAccess {
	public User logIn(String nick,String passwd);
	public boolean singUp(String nick,String nombre,String passwd,String raza);
}
