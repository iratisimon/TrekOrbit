package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import model.User;

public class SignUp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldNick;
	private JPasswordField textFieldRepiteNewpasswd;
	private JPasswordField textFieldIntroducirNewPasswd;
	private JButton registrarseBtn;
	private JButton iniciarSesionBtn;
	private JLabel mensaje;
	private JTextField textFieldRaza;

	private AccessController controladorAcceso;
	private User usuario;

	/**
	 * Create the frame.
	 * 
	 * @param controladorAcesso
	 */
	public SignUp(AccessController controladorAcceso) {
		this.controladorAcceso = controladorAcceso;

		setBackground(new Color(240, 240, 240));
		setForeground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 640);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel tituloLbl = new JLabel("TREKORBIT");
		tituloLbl.setBackground(new Color(0, 0, 0));
		tituloLbl.setForeground(new Color(255, 255, 255));
		tituloLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLbl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 60));
		tituloLbl.setBounds(262, 53, 516, 69);
		contentPane.add(tituloLbl);

		JLabel nickLbl = new JLabel("Usuario: ");
		nickLbl.setForeground(new Color(255, 255, 255));
		nickLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nickLbl.setFont(new Font("Verdana", Font.BOLD, 20));
		nickLbl.setBounds(251, 210, 171, 42);
		contentPane.add(nickLbl);

		JLabel repitePasswdLbl = new JLabel("Repite la contraseña:");
		repitePasswdLbl.setForeground(new Color(255, 255, 255));
		repitePasswdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		repitePasswdLbl.setFont(new Font("Verdana", Font.BOLD, 20));
		repitePasswdLbl.setBounds(191, 366, 329, 42);
		contentPane.add(repitePasswdLbl);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(420, 175, 276, 25);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldNick = new JTextField();
		textFieldNick.setBounds(420, 223, 276, 25);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);

		registrarseBtn = new JButton("Registrarse");
		registrarseBtn.setBackground(new Color(255, 255, 255));
		registrarseBtn.setForeground(new Color(0, 0, 0));
		registrarseBtn.setFont(new Font("Verdana", Font.BOLD, 15));
		registrarseBtn.setBounds(418, 444, 171, 30);
		contentPane.add(registrarseBtn);

		JLabel preguntaRegistradoLbl = new JLabel("¿Ya estas registrado?");
		preguntaRegistradoLbl.setForeground(new Color(255, 255, 255));
		preguntaRegistradoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		preguntaRegistradoLbl.setFont(new Font("Verdana", Font.BOLD, 15));
		preguntaRegistradoLbl.setBounds(302, 503, 186, 30);
		contentPane.add(preguntaRegistradoLbl);

		iniciarSesionBtn = new JButton("Iniciar Sesion");
		iniciarSesionBtn.setBackground(new Color(255, 255, 255));
		iniciarSesionBtn.setFont(new Font("Verdana", Font.BOLD, 12));
		iniciarSesionBtn.setBounds(498, 507, 153, 23);
		contentPane.add(iniciarSesionBtn);

		JLabel razaLbl = new JLabel("Raza:");
		razaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		razaLbl.setForeground(Color.WHITE);
		razaLbl.setFont(new Font("Verdana", Font.BOLD, 20));
		razaLbl.setBounds(293, 262, 81, 42);
		contentPane.add(razaLbl);

		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nombreLbl.setForeground(Color.WHITE);
		nombreLbl.setFont(new Font("Verdana", Font.BOLD, 20));
		nombreLbl.setBounds(241, 162, 169, 42);
		contentPane.add(nombreLbl);

		textFieldRepiteNewpasswd = new JPasswordField();
		textFieldRepiteNewpasswd.setColumns(10);
		textFieldRepiteNewpasswd.setBounds(519, 378, 231, 25);
		contentPane.add(textFieldRepiteNewpasswd);

		textFieldIntroducirNewPasswd = new JPasswordField();
		textFieldIntroducirNewPasswd.setColumns(10);
		textFieldIntroducirNewPasswd.setBounds(509, 326, 241, 25);
		contentPane.add(textFieldIntroducirNewPasswd);

		JLabel nuevaContraseñaLbl = new JLabel("Nueva contraseña: ");
		nuevaContraseñaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nuevaContraseñaLbl.setForeground(Color.WHITE);
		nuevaContraseñaLbl.setFont(new Font("Verdana", Font.BOLD, 20));
		nuevaContraseñaLbl.setBounds(223, 314, 276, 42);
		contentPane.add(nuevaContraseñaLbl);

		mensaje = new JLabel("");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Verdana", Font.BOLD, 14));
		mensaje.setForeground(new Color(255, 0, 0));
		mensaje.setBounds(624, 442, 276, 35);
		mensaje.setVisible(false);
		contentPane.add(mensaje);

		textFieldRaza = new JTextField();
		textFieldRaza.setBounds(420, 272, 276, 25);
		contentPane.add(textFieldRaza);
		textFieldRaza.setColumns(10);

		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon(
				"C:\\Users\\1dami\\Desktop\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\Imagenes\\galaxy.jpg"));
		fondo.setBounds(10, 10, 984, 582);
		contentPane.add(fondo);

		registrarseBtn.addActionListener(this);
		iniciarSesionBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == iniciarSesionBtn) {
			dispose();
			LogIn login1 = new LogIn(controladorAcceso);
			login1.setVisible(true);
		}

		if (o == registrarseBtn) {
			String passwd1 = textFieldIntroducirNewPasswd.getText();
			String passwd2 = textFieldRepiteNewpasswd.getText();
			String nick = textFieldNick.getText();
			String raza = textFieldRaza.getText();
			String nombre = textFieldNombre.getText();

			if (!passwd1.equals(passwd2)) {
				mensaje.setText("Las contraseñas no coinciden");
				mensaje.setForeground(Color.RED);
				mensaje.setVisible(true);
			} else {
				boolean modificado = controladorAcceso.singUp(nombre, nick, raza, passwd1);
				if (modificado) {
					mensaje.setText("Usuario creado correctamente");
					mensaje.setForeground(Color.RED);
					mensaje.setVisible(true);
					dispose(); // Cerramos la ventana después de crear el usuario
				} else {
					mensaje.setText("Error.No se ha podido agregar al nuevo usuario");
					mensaje.setForeground(Color.RED);
					mensaje.setVisible(true);
				}
			}
		}
	}
}
