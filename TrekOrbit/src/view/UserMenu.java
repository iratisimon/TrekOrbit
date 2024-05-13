package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.TravelController;
import controller.UserController;
import model.Ser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class UserMenu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton NuevaAventura;
	private JButton MisAventuras;
	private JButton iconoPerfil;
	private JLabel lblCerrarSesion;
	private AccessController controladorAcceso;
	private UserController controladorUsuario;
	private Ser ser;
	private JLabel lblUsuario;

	/**
	 * Create the frame.
	 */
	public UserMenu(AccessController controladorAcceso, UserController controladorUsuario, Ser ser) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMenu.class.getResource("/images/logotipo_trekorbit.png")));
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
        iconoPerfil.setIcon(new ImageIcon(UserMenu.class.getResource("/images/TrekyIconoPerfil.png"))); // Cambia la ruta según la ubicación de tu icono de perfil
        iconoPerfil.setBounds(804, 10, 184, 189); // Ajusta las coordenadas y el tamaño según tus necesidades
        iconoPerfil.setContentAreaFilled(false); // Establecer el fondo del botón como transparente
        iconoPerfil.setBorderPainted(false);
        contentPane.add(iconoPerfil);
		
		JLabel bienvenido = new JLabel("");
		bienvenido.setIcon(new ImageIcon(UserMenu.class.getResource("/images/Menu.png")));
		bienvenido.setBounds(211, 10, 583, 189);
		contentPane.add(bienvenido);
		
		//label para foto de treky
		JLabel treky = new JLabel("");
		treky.setIcon(new ImageIcon(UserMenu.class.getResource("/images/TrekyUserMenu.png")));
		treky.setBounds(291, 285, 391, 358);
		contentPane.add(treky);
		
		//boton para acceder a la ventana de comprar viaje
		NuevaAventura = new JButton("Nueva Aventura ");
		NuevaAventura.setForeground(new Color(255, 255, 255));
		NuevaAventura.setBackground(new Color(128, 128, 255));
		NuevaAventura.setFont(new Font("Verdana", Font.BOLD, 23));
		NuevaAventura.setBounds(700, 273, 264, 122);
		contentPane.add(NuevaAventura);
		
		//boton para acceder a la ventana que te muestra los viajes comprados
		MisAventuras = new JButton("Mis Aventuras");
		MisAventuras.setForeground(new Color(255, 255, 255));
		MisAventuras.setBackground(new Color(128, 128, 255));
		MisAventuras.setFont(new Font("Verdana", Font.BOLD, 23));
		MisAventuras.setBounds(51, 273, 264, 122);
		contentPane.add(MisAventuras);
		
		lblUsuario = new JLabel("@"+ser.getNick());
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Magneto", Font.BOLD, 25));
		lblUsuario.setForeground(new Color(255, 128, 0));
		lblUsuario.setBounds(238, 152, 486, 42);
		contentPane.add(lblUsuario);
		
		//label para el boton de cerrar sesión
		lblCerrarSesion = createClickableLabel("C:\\Users\\1dami\\Desktop\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\CERRARSESION.png",80, 479, 156, 131);
		contentPane.add(lblCerrarSesion);
		
		//label para el fondo
		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Magneto", Font.PLAIN, 25));
		fondo.setForeground(Color.WHITE);
		fondo.setIcon(new ImageIcon(UserMenu.class.getResource("/images/galaxy.jpg")));
		fondo.setBounds(0, 0, 1266, 683);
		contentPane.add(fondo);
		
		iconoPerfil.addActionListener(this);
		NuevaAventura.addActionListener(this);
		MisAventuras.addActionListener(this);
	}
	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(UserMenu.class.getResource("/images/CERRARSESION.png")));
		label.setBounds(10, 539, 156, 91);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar por encima del
																			// JLabel // Label
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar qué etiqueta fue clicada
				if (label == lblCerrarSesion) {
					// Si fue la etiqueta "Cerrar Sesión", volver a la ventana anterior
					LogIn volver = new LogIn(controladorAcceso);
					volver.setVisible(true);
					dispose();
				}
			}
		});
		return label;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		TravelController controladorViaje = new TravelController();
		if (o == NuevaAventura) {
			BuyTrip buy = new BuyTrip(controladorViaje,ser,controladorAcceso,controladorUsuario);
			buy.setVisible(true);
			dispose();
		} else if (o == MisAventuras) {
			SeeTrips see = new SeeTrips(controladorViaje,ser,controladorAcceso,controladorUsuario);
			see.setVisible(true);
			dispose();
		} else if (o == iconoPerfil) {
			Profile p = new Profile (controladorAcceso,controladorUsuario, ser);
			p.setVisible(true);
			dispose();
		}
	}
}
