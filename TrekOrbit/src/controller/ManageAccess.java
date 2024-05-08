package controller;

import model.Ser;
import ownExceptions.UsuarioExistenteException;
import ownExceptions.SerNoEncontradoException;

/**
 * Esta interfaz define métodos para gestionar el acceso de los usuarios al sistema.
 */
public interface ManageAccess {
	/**
     * Método para que un usuario inicie sesión en el sistema.
     *
     * @param nick el nombre de usuario (nick) del usuario que desea iniciar sesión.
     * @param passwd la contraseña del usuario que desea iniciar sesión.
     * @return el objeto Ser que representa al usuario que ha iniciado sesión.
     * @throws SerNoEncontradoException si el usuario no se encuentra en la base de datos.
     */
	public Ser logIn(String nick,String passwd) throws SerNoEncontradoException;
	
	 /**
     * Método para que un nuevo usuario se registre en el sistema.
     *
     * @param nick el nombre de usuario (nick) del nuevo usuario.
     * @param nombre el nombre completo del nuevo usuario.
     * @param passwd la contraseña del nuevo usuario.
     * @param raza la raza del nuevo usuario.
     * @return true si el usuario se ha registrado correctamente, false de lo contrario.
     * @throws UsuarioExistenteException si ya existe un usuario con el mismo nombre de usuario (nick).
     */
	public boolean singUp(String nick,String nombre,String passwd,String raza) throws UsuarioExistenteException;
}
