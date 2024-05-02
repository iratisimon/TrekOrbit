package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.AdminController;
import controller.UserController;
import model.Ser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class LogIn extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNick;
	private JPasswordField passwd;
	private JButton inicio;
	private JButton registro;
	private JLabel mensaje;
	private boolean contraseñaVisible = false;

	private AccessController controladorAcceso;
	private AdminController controladorAdmin;
	private UserController controladorUsuario;
	private Ser ser;

	/**
	 * Create the frame.
	 */

	public LogIn(AccessController controladorAcesso) {
		this.controladorAcceso = new AccessController();

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
    
		JLabel titulo = new JLabel("TREKORBIT");
		titulo.setBackground(new Color(0, 0, 0));
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 60));
		titulo.setBounds(230, 112, 540, 69);
		contentPane.add(titulo);

		JLabel nick = new JLabel("Usuario: ");
		nick.setForeground(new Color(255, 255, 255));
		nick.setHorizontalAlignment(SwingConstants.CENTER);
		nick.setFont(new Font("Verdana", Font.BOLD, 25));
		nick.setBounds(251, 216, 202, 42);
		contentPane.add(nick);

		JLabel contraseña = new JLabel("Contraseña: ");
		contraseña.setForeground(new Color(255, 255, 255));
		contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		contraseña.setFont(new Font("Verdana", Font.BOLD, 25));
		contraseña.setBounds(219, 266, 270, 42);
		contentPane.add(contraseña);

		textFieldNick = new JTextField();
		textFieldNick.setBounds(479, 229, 241, 25);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);

		passwd = new JPasswordField();
		passwd.setBounds(479, 281, 241, 25);
		contentPane.add(passwd);
		passwd.setColumns(10);

		inicio = new JButton("Iniciar Sesion");
		inicio.setBackground(new Color(255, 255, 255));
		inicio.setForeground(new Color(0, 0, 0));
		inicio.setFont(new Font("Verdana", Font.BOLD, 15));
		inicio.setBounds(410, 345, 172, 30);
		contentPane.add(inicio);

		JLabel preguntaRegristro = new JLabel("¿Aún no te has registrado?");
		preguntaRegristro.setForeground(new Color(255, 255, 255));
		preguntaRegristro.setHorizontalAlignment(SwingConstants.CENTER);
		preguntaRegristro.setFont(new Font("Verdana", Font.BOLD, 15));
		preguntaRegristro.setBounds(267, 436, 319, 30);
		contentPane.add(preguntaRegristro);

		registro = new JButton("Registrarse");
		registro.setBackground(new Color(255, 255, 255));
		registro.setFont(new Font("Verdana", Font.BOLD, 12));
		registro.setBounds(560, 441, 124, 23);
		contentPane.add(registro);
		registro.addActionListener(this);


		mensaje = new JLabel("");
		mensaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mensaje.setBackground(new Color(255, 0, 0));
		mensaje.setForeground(new Color(255, 0, 0));
		mensaje.setBounds(435, 464, 364, 30);
		contentPane.add(mensaje);
		
		inicio.addActionListener(this);
		registro.addActionListener(this);
	
		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\galaxy.jpg"));
		fondo.setBounds(10, 0, 984, 593);
		contentPane.add(fondo);
		
	}
	
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    
	    if (o == registro) {
	        dispose();
	        SignUp login2 = new SignUp(controladorAcceso);
	        login2.setVisible(true);
	    }

	    if (o == inicio) {
	        String password = passwd.getText();
	        String nick = textFieldNick.getText();
	        ser = controladorAcceso.logIn(nick, password);
	        
	        if (nick.isEmpty()||password.isEmpty()) {
	        	mensaje.setText("Campos vacios");
	        }
	        else {

	        if (ser != null) {
	            controladorUsuario = new UserController();
	            controladorAdmin = new AdminController();

	            if (ser.isAdmin()) {
	                dispose();
	                AdminView av = new AdminView(controladorAcceso, controladorAdmin, ser);
	                av.setVisible(true);

	            } else if(!ser.isAdmin()) {
	                dispose();
	                UserMenu um = new UserMenu(controladorAcceso, controladorUsuario, ser);
	                um.setVisible(true);	            } 

	        } else {
	            mensaje.setText("Usuario incorrecto");
	        }
	    }
	    }
	}

}
