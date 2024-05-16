package controller;

import model.Ser;
import model.User;

/**
 * Esta interfaz proporciona métodos para administrar los datos de los usuarios en el sistema.
 */
public interface ManageUser {
	/**
     * Obtiene los datos de un usuario específico.
     *
     * @param ser el objeto Ser que representa al usuario del que se desean obtener los datos.
     * @return un objeto User que contiene los datos del usuario.
     */
	public User showUserData(Ser ser); 
	
	/**
     * Modifica los datos de un usuario en el sistema.
     *
     * @param nickOriginal el nombre de usuario (nick) original del usuario.
     * @param passwdOriginal la contraseña original del usuario.
     * @param nickNew el nuevo nombre de usuario (nick) que se desea asignar.
     * @param passwd la nueva contraseña que se desea asignar al usuario.
     * @return true si se modificaron los datos del usuario correctamente, false de lo contrario.
     */
	public boolean modifyUserData(String nickOriginal, String passwdOriginal, String nickNew, String passwd);//depende de lo que quieras modificar
}
