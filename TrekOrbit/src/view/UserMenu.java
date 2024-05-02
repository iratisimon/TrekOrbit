package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AccessController;
import controller.TravelController;
import controller.UserController;
import model.Ser;
import model.User;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import controller.TravelController;

public class UserMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton NuevaAventura;
	private JButton MisAventuras;
	private JButton iconoPerfil;
	
	private AccessController controladorAcceso;
	private UserController controladorUsuario;
	private Ser ser;
	private JLabel lblUsuario;

	/**
	 * Create the frame.
	 */
	public UserMenu(AccessController controladorAcceso, UserController controladorUsuario, Ser ser) {
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;
		this.ser = ser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label para el icono del perfil
		iconoPerfil = new JButton("");
        iconoPerfil.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\treky4-removebg-preview.png")); // Cambia la ruta según la ubicación de tu icono de perfil
        iconoPerfil.setBounds(56, 29, 184, 189); // Ajusta las coordenadas y el tamaño según tus necesidades
        iconoPerfil.setContentAreaFilled(false); // Establecer el fondo del botón como transparente
        iconoPerfil.setBorderPainted(false);
        contentPane.add(iconoPerfil);
		
		//label para foto de treky
		JLabel treky = new JLabel("");
		treky.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\treky8-removebg-preview.png"));
		treky.setBounds(291, 285, 391, 358);
		contentPane.add(treky);
		
		//boton para acceder a la ventana de comprar viaje
		NuevaAventura = new JButton("Nueva Aventura ");
		NuevaAventura.setForeground(new Color(255, 255, 255));
		NuevaAventura.setBackground(new Color(128, 128, 255));
		NuevaAventura.setFont(new Font("Verdana", Font.BOLD, 23));
		NuevaAventura.setBounds(698, 373, 264, 122);
		contentPane.add(NuevaAventura);
		
		//boton para acceder a la ventana que te muestra los viajes comprados
		MisAventuras = new JButton("Mis Aventuras");
		MisAventuras.setForeground(new Color(255, 255, 255));
		MisAventuras.setBackground(new Color(128, 128, 255));
		MisAventuras.setFont(new Font("Verdana", Font.BOLD, 23));
		MisAventuras.setBounds(49, 373, 264, 122);
		contentPane.add(MisAventuras);
		
		//label de titulo 
		JLabel bienvenida = new JLabel("Bienvenid@! ");
		bienvenida.setForeground(Color.WHITE);
		bienvenida.setFont(new Font("Verdana", Font.BOLD, 80));
		bienvenida.setBounds(294, 59, 657, 100);
		contentPane.add(bienvenida);
		
		lblUsuario = new JLabel("@"+ser.getNick());
		lblUsuario.setFont(new Font("Magneto", Font.BOLD, 25));
		lblUsuario.setForeground(new Color(51, 255, 102));
		lblUsuario.setBounds(467, 176, 244, 42);
		contentPane.add(lblUsuario);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(877, 570, 85, 21);
		contentPane.add(btnNewButton);
		//label para el fondo
		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Magneto", Font.PLAIN, 25));
		fondo.setForeground(Color.WHITE);
		fondo.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\galaxy.jpg"));
		fondo.setBounds(0, 0, 1266, 683);
		contentPane.add(fondo);
		
		
		
		
		iconoPerfil.addActionListener(this);
		NuevaAventura.addActionListener(this);
		MisAventuras.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == NuevaAventura) {
			TravelController controladorViaje= new TravelController();
			BuyTrip buy = new BuyTrip(controladorViaje,ser);
			buy.setVisible(true);
			dispose();
		} else if (o == MisAventuras) {
			// SeeTrip see = new SeeTrip(); Ventana de elbire -- controlador y usuario
			// a.setVisible(true);
			dispose();
		} else if (o == iconoPerfil) {
			Profile p = new Profile (controladorAcceso,controladorUsuario, ser);
			p.setVisible(true);
			dispose();
		}
	}
}
