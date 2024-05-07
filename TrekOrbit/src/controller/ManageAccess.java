package controller;

import java.sql.SQLException;

import model.Ser;
import ownExceptions.UsuarioExistenteException;
import ownExceptions.SerNoEncontradoException;

public interface ManageAccess {
	public Ser logIn(String nick,String passwd) throws SerNoEncontradoException;
	public boolean singUp(String nick,String nombre,String passwd,String raza) throws UsuarioExistenteException;
	public String generarIdUsuario();
	public boolean existeIdUsuario(String id);
	public boolean existeUsuarioConNick(String nick) throws SQLException;
}
