package principal;

import javax.swing.SwingUtilities;

import controller.AccessController;
import model.User;
import view.LogIn;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Crear una instancia del controlador de acceso
        AccessController controladorAcceso = new AccessController();
        
        User usuario=null;
        LogIn login = new LogIn(controladorAcceso, usuario);
        login.setVisible(true);
        
     // 
	}

}
