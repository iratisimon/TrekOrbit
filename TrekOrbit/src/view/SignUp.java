package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AccessController;
import model.User;

import javax.swing.JComboBox;

public class SignUp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldNick;
	private JTextField textFieldRepiteNewpasswd;
	private JTextField textFieldIntroducirNewPasswd;
	private JButton registrarseBtn;
	private JButton iniciarSesionBtn;
	private JLabel mensaje;
	private JButton showHidePasswd;
	private JTextField textFieldRaza;
	
	private AccessController controladorAcceso;
	private User usuario;


	/**
	 * Create the frame.
	 * @param controladorAcesso
	 */
	public SignUp(AccessController controladorAcceso) {
		this.controladorAcceso = controladorAcceso;
		setBackground(new Color(240, 240, 240));
		setForeground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
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
		tituloLbl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 40));
		tituloLbl.setBounds(344, 65, 611, 69);
		contentPane.add(tituloLbl);
		
		JLabel nickLbl = new JLabel("USUARIO: ");
		nickLbl.setForeground(new Color(255, 255, 255));
		nickLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nickLbl.setFont(new Font("Verdana", Font.BOLD, 18));
		nickLbl.setBounds(426, 271, 129, 42);
		contentPane.add(nickLbl);
		
		JLabel repitePasswdLbl = new JLabel("REPITE CONTRASEÑA:");
		repitePasswdLbl.setForeground(new Color(255, 255, 255));
		repitePasswdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		repitePasswdLbl.setFont(new Font("Verdana", Font.BOLD, 16));
		repitePasswdLbl.setBounds(426, 427, 227, 42);
		contentPane.add(repitePasswdLbl);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(553, 236, 276, 25);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldNick = new JTextField();
		textFieldNick.setBounds(553, 284, 276, 25);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);
		
		registrarseBtn = new JButton("Registrarse:");
		registrarseBtn.setBackground(new Color(255, 255, 255));
		registrarseBtn.setForeground(new Color(0, 0, 0));
		registrarseBtn.setFont(new Font("Verdana", Font.BOLD, 13));
		registrarseBtn.setBounds(582, 506, 129, 25);
		contentPane.add(registrarseBtn);
		
		JLabel preguntaRegistradoLbl = new JLabel("Ya estas registrado?");
		preguntaRegistradoLbl.setForeground(new Color(255, 255, 255));
		preguntaRegistradoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		preguntaRegistradoLbl.setFont(new Font("Verdana", Font.BOLD, 12));
		preguntaRegistradoLbl.setBounds(481, 562, 186, 30);
		contentPane.add(preguntaRegistradoLbl);
		
		iniciarSesionBtn = new JButton("Iniciar Sesion");
		iniciarSesionBtn.setBackground(new Color(255, 255, 255));
		iniciarSesionBtn.setFont(new Font("Verdana", Font.BOLD, 9));
		iniciarSesionBtn.setBounds(662, 569, 117, 18);
		contentPane.add(iniciarSesionBtn);

		
		JLabel razaLbl = new JLabel("RAZA:");
		razaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		razaLbl.setForeground(Color.WHITE);
		razaLbl.setFont(new Font("Verdana", Font.BOLD, 18));
		razaLbl.setBounds(426, 323, 81, 42);
		contentPane.add(razaLbl);
		
		JLabel nombreLbl = new JLabel("NOMBRE:");
		nombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nombreLbl.setForeground(Color.WHITE);
		nombreLbl.setFont(new Font("Verdana", Font.BOLD, 18));
		nombreLbl.setBounds(426, 223, 117, 42);
		contentPane.add(nombreLbl);
		
		textFieldRepiteNewpasswd = new JTextField();
		textFieldRepiteNewpasswd.setColumns(10);
		textFieldRepiteNewpasswd.setBounds(652, 439, 231, 25);
		contentPane.add(textFieldRepiteNewpasswd);
		
		textFieldIntroducirNewPasswd = new JTextField();
		textFieldIntroducirNewPasswd.setColumns(10);
		textFieldIntroducirNewPasswd.setBounds(642, 387, 241, 25);
		contentPane.add(textFieldIntroducirNewPasswd);
		
		
		JLabel nuevaContraseñaLbl = new JLabel("NUEVA CONTRASEÑA: ");
		nuevaContraseñaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nuevaContraseñaLbl.setForeground(Color.WHITE);
		nuevaContraseñaLbl.setFont(new Font("Verdana", Font.BOLD, 16));
		nuevaContraseñaLbl.setBounds(426, 375, 227, 42);
		contentPane.add(nuevaContraseñaLbl);
		
		
		mensaje = new JLabel("");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Verdana", Font.BOLD, 14));
		mensaje.setForeground(new Color(255, 255, 255));
		mensaje.setBounds(481, 602, 276, 35);
		mensaje.setVisible(false);
		contentPane.add(mensaje);
		
		textFieldRaza = new JTextField();
		textFieldRaza.setBounds(553, 333, 276, 25);
		contentPane.add(textFieldRaza);
		textFieldRaza.setColumns(10);
		
		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\galaxy.jpg"));
		fondo.setBounds(10, 10, 1246, 673);
		contentPane.add(fondo);
		
		registrarseBtn.addActionListener(this);
		iniciarSesionBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o == iniciarSesionBtn) {
	        dispose();
	        LogIn login1 = new LogIn(controladorAcceso, usuario);
	        login1.setVisible(true);
	    }
	    if (o == registrarseBtn) {
	    	if (controladorAcceso.singUp(textFieldNick.getText(), textFieldNombre.getText(), textFieldIntroducirNewPasswd.getText(), textFieldRaza.getText())) {
	    		mensaje.setText("Se ha agregado un nuevo usuario correctamente");
	    		dispose();
	    	} else {
	    		mensaje.setText("Error.No se ha podido agregar al nuevo usuario");
	    	}
	    }
	}
}