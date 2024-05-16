package main;

import javax.swing.SwingUtilities;

import controller.AccessController;
import view.LogIn;
import view.Welcome;

public class Main {

	public static void main(String[] args) {
		Welcome welcomeWindow = new Welcome();
		welcomeWindow.setVisible(true);

		// Crear temporizador para mostrar la ventana de inicio de sesión después de 5
		// segundos
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(5000); // Esperar 5 segundos

					// Cerrar la ventana de bienvenida
					welcomeWindow.dispose();

					// Mostrar la ventana de inicio de sesión
					showLoginWindow();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private static void showLoginWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AccessController controladorAcceso = new AccessController();
				LogIn login = new LogIn(controladorAcceso);
				login.setVisible(true);
			}
		});

	}

}
