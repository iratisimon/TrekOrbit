package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.AdminController;
import controller.UserController;
import factory.AccessFactory;
import model.Ser;
import ownExceptions.SerNoEncontradoException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class LogIn extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNick;
	private JPasswordField passwd;
	private JButton inicio;
	private JButton registro;
	private AccessController controladorAcceso;
	private AdminController controladorAdmin;
	private UserController controladorUsuario;
	private Ser ser;
	private JButton show;
	private JButton hide;

	/**
	 * Create the frame.
	 */
	public LogIn(AccessController controladorAcesso) {
		
		MakeLessUgly.setDefaultCursor(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogIn.class.getResource("/images/logotipo_trekorbit.png")));
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

		JLabel titulo = new JLabel("");
		titulo.setIcon(new ImageIcon(LogIn.class.getResource("/images/AL_INFINITO_Y_MAS_ALLÁ_.png")));
		titulo.setBackground(new Color(0, 0, 0));
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 0, 992, 218);
		contentPane.add(titulo);

		JLabel nick = new JLabel("Usuario: ");
		nick.setForeground(new Color(255, 255, 255));
		nick.setHorizontalAlignment(SwingConstants.CENTER);
		nick.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		nick.setBounds(285, 263, 189, 42);
		contentPane.add(nick);

		JLabel contraseña = new JLabel("Contraseña: ");
		contraseña.setForeground(new Color(255, 255, 255));
		contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		contraseña.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		contraseña.setBounds(285, 337, 189, 42);
		contentPane.add(contraseña);

		JLabel preguntaRegristro = new JLabel("¿Aún no te has registrado?");
		preguntaRegristro.setForeground(new Color(255, 255, 255));
		preguntaRegristro.setHorizontalAlignment(SwingConstants.CENTER);
		preguntaRegristro.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		preguntaRegristro.setBounds(31, 560, 319, 30);
		contentPane.add(preguntaRegristro);

		textFieldNick = new JTextField();
		textFieldNick.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		textFieldNick.setBounds(479, 263, 241, 42);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);

		passwd = new JPasswordField();
		passwd.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		passwd.setBounds(479, 337, 241, 40);
		contentPane.add(passwd);
		passwd.setColumns(10);

		inicio = new JButton("");
		inicio.setIcon(new ImageIcon(LogIn.class.getResource("/images/IniciarSesion.png")));
		inicio.setBorderPainted(false);
		inicio.setFocusPainted(true);
		inicio.setContentAreaFilled(false);
		inicio.setForeground(new Color(0, 0, 0));
		inicio.setFont(new Font("Verdana", Font.BOLD, 15));
		inicio.setBounds(394, 397, 241, 148);
		contentPane.add(inicio);

		registro = new JButton("Registrarse");
		registro.setForeground(new Color(255, 0, 255));
		registro.setBorderPainted(false);
		registro.setFocusPainted(true);
		registro.setContentAreaFilled(false);
		registro.setFont(new Font("OCR A Extended", Font.BOLD, 12));
		registro.setBounds(820, 565, 124, 23);
		contentPane.add(registro);
		registro.addActionListener(this);

		show = new JButton("");
		show = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBackground().getAlpha() < 255) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setComposite(AlphaComposite.Src);
					g2.setColor(getBackground());
					g2.fillRect(0, 0, getWidth(), getHeight());
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		show.setForeground(Color.BLACK);
		show.setIcon(new ImageIcon(LogIn.class.getResource("/images/OjoVerde.png")));
		show.setBounds(730, 337, 52, 42);
		show.setBorderPainted(false); // Oculta el borde del botón
		show.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		show.addActionListener(this);

		getContentPane().add(show);
		getContentPane().add(show);

		hide = new JButton("");
		hide = new JButton("") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBackground().getAlpha() < 255) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setComposite(AlphaComposite.Src);
					g2.setColor(getBackground());
					g2.fillRect(0, 0, getWidth(), getHeight());
					g2.dispose();
				}
				super.paintComponent(g);
			}
		};
		hide.setForeground(Color.BLACK);
		hide.setIcon(new ImageIcon(LogIn.class.getResource("/images/OjoRosa.png")));
		hide.setBounds(730, 338, 52, 41);
		hide.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		hide.setBorderPainted(false); // Oculta el borde del botó
		hide.addActionListener(this);

		getContentPane().add(hide);
		getContentPane().add(hide);

		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon(LogIn.class.getResource("/images/galaxy.jpg")));
		fondo.setBounds(0, 0, 992, 601);
		contentPane.add(fondo);

		inicio.addActionListener(this);
		registro.addActionListener(this);
		MakeLessUgly.setAlienCursor(inicio);
		MakeLessUgly.setAlienCursor(registro);
		MakeLessUgly.setAlienCursor(show);
		MakeLessUgly.setAlienCursor(hide);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == show) {
			passwd.setEchoChar((char) 0);
			hide.setVisible(true);
			show.setVisible(false);

		} else if (o == hide) {
			passwd.setEchoChar('\u2022');
			hide.setVisible(false);
			show.setVisible(true);
		}

		if (o == registro) {
			SignUp login2 = new SignUp(controladorAcceso);
			login2.setVisible(true);
			this.dispose();
		}
		if (o == inicio) {
			String password = new String(passwd.getPassword());
			String nick = textFieldNick.getText();

			try {
				ser = AccessFactory.getManageAccess().logIn(nick, password);

				if (nick.isEmpty() || password.isEmpty()) {
					MakeLessUgly.showMessageDialog("Rellene todos los campos", "Error", JOptionPane.PLAIN_MESSAGE);

				} else {
					if (ser != null) {
						controladorUsuario = new UserController();
						controladorAdmin = new AdminController();
						if (ser.isAdmin()) {
							dispose();
							AdminView av = new AdminView(controladorAcceso, controladorAdmin, ser);
							av.setVisible(true);
						} else if (!ser.isAdmin()) {
							System.out.println(ser.getId());
							dispose();
							UserMenu um = new UserMenu(controladorAcceso, controladorUsuario, ser);
							um.setVisible(true);
						}
					}
				}
			} catch (SerNoEncontradoException ex) {
				MakeLessUgly.showMessageDialog("El usuario o la contraseña son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}