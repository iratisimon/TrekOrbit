package controller;

import model.Ser;
import ownExceptions.UsuarioExistenteException;
import ownExceptions.SerNoEncontradoException;

public interface ManageAccess {
	public Ser logIn(String nick,String passwd) throws SerNoEncontradoException;
	public boolean singUp(String nick,String nombre,String passwd,String raza) throws UsuarioExistenteException;
}
