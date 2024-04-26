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

	private AccessController controladorAcceso;
	private AdminController controladorAdmin;
	private UserController controladorUsuario;
	private Ser ser;

	/**
	 * Create the frame.
	 */

	public LogIn(AccessController controladorAcesso, Ser ser) {
		this.controladorAcceso = new AccessController();
		this.ser = ser;
		
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
    
		JLabel titulo = new JLabel("TREKORBIT");
		titulo.setBackground(new Color(0, 0, 0));
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 40));
		titulo.setBounds(344, 65, 611, 69);
		contentPane.add(titulo);

		JLabel nick = new JLabel("USUARIO: ");
		nick.setForeground(new Color(255, 255, 255));
		nick.setHorizontalAlignment(SwingConstants.CENTER);
		nick.setFont(new Font("Verdana", Font.BOLD, 18));
		nick.setBounds(426, 223, 129, 42);
		contentPane.add(nick);

		JLabel contraseña = new JLabel("CONTRASEÑA: ");
		contraseña.setForeground(new Color(255, 255, 255));
		contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		contraseña.setFont(new Font("Verdana", Font.BOLD, 18));
		contraseña.setBounds(426, 275, 169, 42);
		contentPane.add(contraseña);

		textFieldNick = new JTextField();
		textFieldNick.setBounds(553, 236, 241, 25);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);

		passwd = new JPasswordField();
		passwd.setBounds(585, 288, 209, 25);
		contentPane.add(passwd);
		passwd.setColumns(10);

		inicio = new JButton("Iniciar Sesion");
		inicio.setBackground(new Color(255, 255, 255));
		inicio.setForeground(new Color(0, 0, 0));
		inicio.setFont(new Font("Verdana", Font.BOLD, 13));
		inicio.setBounds(563, 355, 129, 25);
		contentPane.add(inicio);

		JLabel preguntaRegristro = new JLabel("¿Aún no te has registrado?");
		preguntaRegristro.setForeground(new Color(255, 255, 255));
		preguntaRegristro.setHorizontalAlignment(SwingConstants.CENTER);
		preguntaRegristro.setFont(new Font("Verdana", Font.BOLD, 12));
		preguntaRegristro.setBounds(449, 406, 186, 30);
		contentPane.add(preguntaRegristro);

		registro = new JButton("Registrarse");
		registro.setBackground(new Color(255, 255, 255));
		registro.setFont(new Font("Verdana", Font.BOLD, 9));
		registro.setBounds(645, 413, 104, 18);
		contentPane.add(registro);
		registro.addActionListener(this);


		mensaje = new JLabel("");
		mensaje.setForeground(new Color(255, 255, 255));
		mensaje.setBounds(435, 464, 364, 30);
		contentPane.add(mensaje);
		
		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\galaxy.jpg"));
		fondo.setBounds(10, 10, 1246, 673);
		contentPane.add(fondo);
		
		inicio.addActionListener(this);
		registro.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		String password = new String (passwd.getPassword());
		
		if (o==registro) {
			dispose();
			SignUp login2 = new SignUp(controladorAcceso);
			login2.setVisible(true);
		}
    
		if (o==inicio) {
			ser = controladorAcceso.logIn(textFieldNick.getText(), password);
      
			if (ser != null) {
	        
	        	controladorUsuario = new UserController();
	        	controladorAdmin = new AdminController();
	            
	            if (ser.isAdmin()) {
	                dispose();

	                AdminView av = new AdminView(controladorAcceso, controladorAdmin, ser);

	                av.setVisible(true);
	                
	            } else{
	                dispose(); 
	                UserMenu um = new UserMenu(controladorAcceso,controladorUsuario, ser);
	                um.setVisible(true);
	            }
	        } else {
	            mensaje.setText("Usuario o contraseña incorrectos");
	        }
		}
	}
}
