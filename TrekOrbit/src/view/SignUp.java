package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import factory.AccessFactory;
import ownExceptions.UsuarioExistenteException;
import java.awt.Toolkit;

public class SignUp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldNick;
	private JPasswordField textFieldRepiteNewpasswd;
	private JPasswordField textFieldIntroducirNewPasswd;
	private JButton registrarseBtn;
	private JButton iniciarSesionBtn;
	private JTextField textFieldRaza;
	private JButton show;
	private JButton hide;
	private JButton show2;
	private JButton hide2;

	private AccessController controladorAcceso;

	/**
	 * Create the frame.
	 * 
	 * @param controladorAcesso
	 */
	public SignUp(AccessController controladorAcceso) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/images/logotipo_trekorbit.png")));
		setTitle("REGISTRO");
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

		JLabel tituloLbl = new JLabel("");
		tituloLbl.setIcon(new ImageIcon(SignUp.class.getResource("/images/AL_INFINITO_Y_MAS_ALLÁ_PEQUEÑO.png")));
		tituloLbl.setBackground(new Color(0, 0, 0));
		tituloLbl.setForeground(new Color(255, 255, 255));
		tituloLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLbl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 60));
		tituloLbl.setBounds(254, 11, 516, 153);
		contentPane.add(tituloLbl);

		JLabel nickLbl = new JLabel("Usuario: ");
		nickLbl.setForeground(new Color(255, 255, 255));
		nickLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nickLbl.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		nickLbl.setBounds(203, 222, 153, 30);
		contentPane.add(nickLbl);

		JLabel repitePasswdLbl = new JLabel("Repite la contraseña:");
		repitePasswdLbl.setForeground(new Color(255, 255, 255));
		repitePasswdLbl.setHorizontalAlignment(SwingConstants.LEFT);
		repitePasswdLbl.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		repitePasswdLbl.setBounds(143, 375, 295, 30);
		contentPane.add(repitePasswdLbl);

		JLabel preguntaRegistradoLbl = new JLabel("¿Ya estas registrado?");
		preguntaRegistradoLbl.setForeground(new Color(255, 255, 255));
		preguntaRegistradoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		preguntaRegistradoLbl.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		preguntaRegistradoLbl.setBounds(283, 534, 237, 30);
		contentPane.add(preguntaRegistradoLbl);

		JLabel razaLbl = new JLabel("Raza:");
		razaLbl.setHorizontalAlignment(SwingConstants.CENTER);
		razaLbl.setForeground(Color.WHITE);
		razaLbl.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		razaLbl.setBounds(203, 263, 153, 30);
		contentPane.add(razaLbl);

		JLabel nombreLbl = new JLabel("Nombre:");
		nombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nombreLbl.setForeground(Color.WHITE);
		nombreLbl.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		nombreLbl.setBounds(203, 169, 153, 30);
		contentPane.add(nombreLbl);

		JLabel passwdLbl = new JLabel("Contraseña: ");
		passwdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		passwdLbl.setForeground(Color.WHITE);
		passwdLbl.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		passwdLbl.setBounds(203, 320, 164, 30);
		contentPane.add(passwdLbl);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(420, 175, 276, 25);
		textFieldNombre.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldNick = new JTextField();
		textFieldNick.setBounds(420, 223, 276, 25);
		textFieldNick.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);

		textFieldRepiteNewpasswd = new JPasswordField();
		textFieldRepiteNewpasswd.setColumns(10);
		textFieldRepiteNewpasswd.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		textFieldRepiteNewpasswd.setBounds(420, 378, 276, 25);
		contentPane.add(textFieldRepiteNewpasswd);

		textFieldIntroducirNewPasswd = new JPasswordField();
		textFieldIntroducirNewPasswd.setColumns(10);
		textFieldIntroducirNewPasswd.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		textFieldIntroducirNewPasswd.setBounds(420, 326, 276, 25);
		contentPane.add(textFieldIntroducirNewPasswd);

		textFieldRaza = new JTextField();
		textFieldRaza.setBounds(420, 272, 276, 25);
		textFieldRaza.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		contentPane.add(textFieldRaza);
		textFieldRaza.setColumns(10);

		registrarseBtn = new JButton("");
		registrarseBtn.setIcon(new ImageIcon(SignUp.class.getResource("/images/Registrarse.png")));
		registrarseBtn.setBorderPainted(false);
		registrarseBtn.setContentAreaFilled(false);
		registrarseBtn.setBounds(373, 416, 263, 106);
		contentPane.add(registrarseBtn);

		iniciarSesionBtn = new JButton("Iniciar Sesion");
		iniciarSesionBtn.setForeground(new Color(255, 0, 255));
		iniciarSesionBtn.setBorderPainted(false);
		iniciarSesionBtn.setFocusPainted(true);
		iniciarSesionBtn.setContentAreaFilled(false);
		iniciarSesionBtn.setFont(new Font("OCR A Extended", Font.BOLD, 12));
		iniciarSesionBtn.setBounds(508, 536, 153, 28);
		contentPane.add(iniciarSesionBtn);

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
		show.setIcon(new ImageIcon(SignUp.class.getResource("/images/OjoVerde.png")));
		show.setBounds(706, 323, 52, 27);
		show.setBorderPainted(false); // Oculta el borde del botón
		show.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		show.addActionListener(this);

		getContentPane().add(show);

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
		hide.setIcon(new ImageIcon(SignUp.class.getResource("/images/OjoRosa.png")));
		hide.setBounds(706, 323, 52, 27);
		hide.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		hide.setBorderPainted(false); // Oculta el borde del botó
		hide.addActionListener(this);

		getContentPane().add(hide);

		// BOTON 2

		show2 = new JButton("") {
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
		show2.setForeground(Color.BLACK);
		show2.setIcon(new ImageIcon(SignUp.class.getResource("/images/OjoVerde.png")));
		show2.setBounds(706, 378, 52, 27);
		show2.setBorderPainted(false); // Oculta el borde del botón
		show2.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		show2.addActionListener(this);

		getContentPane().add(show2);

		hide2 = new JButton("") {
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
		hide2.setForeground(Color.BLACK);
		hide2.setIcon(new ImageIcon(SignUp.class.getResource("/images/OjoRosa.png")));
		hide2.setBounds(706, 378, 52, 27);
		hide2.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		hide2.setBorderPainted(false); // Oculta el borde del botó
		hide2.addActionListener(this);
		getContentPane().add(hide2);

		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon(SignUp.class.getResource("/images/galaxy.jpg")));
		fondo.setBounds(-13, 0, 1016, 601);
		contentPane.add(fondo);

		registrarseBtn.addActionListener(this);
		iniciarSesionBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == show) {
			textFieldIntroducirNewPasswd.setEchoChar((char) 0);
			hide.setVisible(true);
			show.setVisible(false);
		} else if (o == hide) {
			textFieldIntroducirNewPasswd.setEchoChar('\u2022');
			hide.setVisible(false);
			show.setVisible(true);
		}

		// BOTON 2
		if (o == show2) {
			textFieldRepiteNewpasswd.setEchoChar((char) 0);
			hide2.setVisible(true);
			show2.setVisible(false);
		} else if (o == hide2) {
			textFieldRepiteNewpasswd.setEchoChar('\u2022');
			hide2.setVisible(false);
			show2.setVisible(true);
		}

		if (o == iniciarSesionBtn) {
			dispose();
			LogIn login1 = new LogIn(controladorAcceso);
			login1.setVisible(true);
		}

		if (o == registrarseBtn) {
			String passwd1 = new String(textFieldIntroducirNewPasswd.getPassword());
			String passwd2 = new String(textFieldRepiteNewpasswd.getPassword());
			String nick = textFieldNick.getText();
			String raza = textFieldRaza.getText();
			String nombre = textFieldNombre.getText();

			// Verificar que ningún campo esté vacío
			if (passwd1.isEmpty() || passwd2.isEmpty() || nick.isEmpty() || raza.isEmpty() || nombre.isEmpty()) {
				MakeLessUgly.showMessageDialog("Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				if (!passwd1.equals(passwd2)) {
					MakeLessUgly.showMessageDialog("Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						boolean modificado = AccessFactory.getManageAccess().singUp(nombre, nick, raza, passwd1);
						if (modificado) {
							MakeLessUgly.showMessageDialog("Se ha registrado correctamente", "Success",
									JOptionPane.INFORMATION_MESSAGE);
							double segundos = 0.7;
							try {
								Thread.sleep((long) (segundos * 1000));
							} catch (InterruptedException ex) {
								Thread.currentThread().interrupt();
							}

							this.dispose();
							LogIn login1 = new LogIn(controladorAcceso);
							login1.setVisible(true);
						} else {
							MakeLessUgly.showMessageDialog("Nombre de usuario existente", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (UsuarioExistenteException ex) {
						MakeLessUgly.showMessageDialog(ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}