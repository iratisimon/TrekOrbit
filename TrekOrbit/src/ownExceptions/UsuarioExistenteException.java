package ownExceptions;

public class UsuarioExistenteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UsuarioExistenteException(String message) {
		super(message);
	}
}
