package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.UserController;
import factory.UserFactory;
import model.Ser;
import model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class Profile extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JPasswordField txtPasswd;
	private JButton btnModificar;
	private JButton btnGuardar;
	private JLabel lblVolver;
	private JButton show;
	private JButton hide;
	private AccessController controladorAcceso;
	private UserController controladorUsuario;
	private Ser ser;
	private JLabel raza;
	private JLabel nombre;

	/**
	 * Create the frame.
	 */

	public Profile(AccessController controladorAcceso, UserController controladorUsuario, Ser ser) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Profile.class.getResource("/images/logotipo_trekorbit.png")));
		setTitle("ME");
		MakeLessUgly.setDefaultCursor(this);
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;
		this.ser = ser;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fotoPerfil = new JLabel("");
		fotoPerfil.setIcon(new ImageIcon(Profile.class.getResource("/images/TrekyFotoPerfil.png")));
		fotoPerfil.setBounds(93, 173, 316, 322);
		contentPane.add(fotoPerfil);

		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Magneto", Font.BOLD, 25));
		lblNick.setForeground(new Color(255, 255, 255));
		lblNick.setBounds(465, 177, 113, 28);
		contentPane.add(lblNick);

		JLabel lblPasswd = new JLabel("Contraseña:");
		lblPasswd.setFont(new Font("Magneto", Font.BOLD, 25));
		lblPasswd.setForeground(new Color(255, 255, 255));
		lblPasswd.setBounds(465, 236, 188, 32);
		contentPane.add(lblPasswd);

		JLabel lblRaza = new JLabel("Mi raza: ");
		lblRaza.setFont(new Font("Magneto", Font.BOLD, 25));
		lblRaza.setForeground(new Color(255, 255, 255));
		lblRaza.setBounds(465, 399, 188, 33);
		contentPane.add(lblRaza);

		JLabel lblNombre = new JLabel("Mi nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Magneto", Font.BOLD, 25));
		lblNombre.setBounds(465, 467, 219, 28);
		contentPane.add(lblNombre);

		txtNick = new JTextField();
		txtNick.setFont(new Font("OCR A Extended", Font.PLAIN, 16));
		txtNick.setBounds(679, 173, 262, 32);
		txtNick.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtNick);
		txtNick.setColumns(10);

		txtPasswd = new JPasswordField();
		txtPasswd.setFont(new Font("OCR A Extended", Font.PLAIN, 16));
		txtPasswd.setBounds(679, 236, 262, 32);
		txtPasswd.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtPasswd);
		txtPasswd.setColumns(10);

		raza = new JLabel("");
		raza.setForeground(new Color(255, 255, 255));
		raza.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		raza.setBounds(679, 399, 262, 28);
		contentPane.add(raza);

		nombre = new JLabel("");
		nombre.setForeground(new Color(255, 255, 255));
		nombre.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
		nombre.setBounds(679, 467, 262, 28);
		contentPane.add(nombre);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Magneto", Font.BOLD, 20));
		btnModificar.setForeground(new Color(0, 0, 128));
		btnModificar.setBackground(new Color(255, 204, 255));
		btnModificar.setBounds(603, 321, 148, 38);
		contentPane.add(btnModificar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(0, 0, 128));
		btnGuardar.setFont(new Font("Magneto", Font.BOLD, 20));
		btnGuardar.setBackground(new Color(255, 204, 255));
		btnGuardar.setBounds(793, 321, 148, 38);
		contentPane.add(btnGuardar);

		lblVolver = createClickableLabel("/images/VolverBlanco.png", 10, 11, 134, 75);
		contentPane.add(lblVolver);

		btnGuardar.addActionListener(this);
		btnModificar.addActionListener(this);
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
		show.setIcon(new ImageIcon(LogIn.class.getResource("/images/ojoVerde.png")));
		show.setBounds(948, 241, 52, 27);
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
		hide.setIcon(new ImageIcon(LogIn.class.getResource("/images/ojoRosa.png")));
		hide.setBounds(948, 241, 52, 27);
		hide.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
		hide.setBorderPainted(false); // Oculta el borde del botó
		hide.addActionListener(this);

		getContentPane().add(hide);
		getContentPane().add(hide);

		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Profile.class.getResource("/images/galaxy.jpg")));
		fondo.setBounds(0, -20, 1031, 677);
		contentPane.add(fondo);
		MakeLessUgly.setAlienCursor(btnGuardar);
		MakeLessUgly.setAlienCursor(btnModificar);

		cargarDatosUsuario();
	}

	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		JLabel labelVolver = new JLabel("");
		labelVolver.setIcon(new ImageIcon(Profile.class.getResource("/images/VolverBlanco.png")));
		labelVolver.setBounds(42, 43, width, height);
		MakeLessUgly.setAlienCursor(labelVolver);
		labelVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar qué etiqueta fue clicada
				if (labelVolver == lblVolver) {
					// Si fue la etiqueta "Volver", volver a la ventana anterior
					UserMenu volver = new UserMenu(controladorAcceso, controladorUsuario, ser);
					volver.setVisible(true);
					dispose();
				}
			}
		});
		return labelVolver;
	}

	public void cargarDatosUsuario() {
		// Obtener los datos del usuario utilizando el controlador de usuario
		User datosUsuario = UserFactory.getManageUser().showUserData(ser);
		System.out.println("Entra cargar profile");

		if (datosUsuario != null) {
			// Establecer los valores en los campos de texto
			txtNick.setText(datosUsuario.getNick());
			txtPasswd.setText(datosUsuario.getPasswd());
			raza.setText(datosUsuario.getRaza());
			nombre.setText(datosUsuario.getNombre());
		} else {
			System.out.println("No se pudieron obtener los datos del usuario.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String nickOriginal = ser.getNick();
		String passwdOriginal = ser.getPasswd(); // Agregar la obtención de la contraseña original
		String nickNew = txtNick.getText();
		String passwd = new String(txtPasswd.getPassword());
		boolean modificado = false;

		if (o == show) {
			txtPasswd.setEchoChar((char) 0);
			hide.setVisible(true);
			show.setVisible(false);

		} else if (o == hide) {
			txtPasswd.setEchoChar('\u2022');
			hide.setVisible(false);
			show.setVisible(true);
		}

		if (o == btnModificar) {
			// Si se presionó el botón "Modificar", habilita la edición de los campos
			ser.setNick(nickNew);
			ser.setPasswd(passwd);
			System.out.println(ser.getNick());

			txtNick.setEditable(true);
			txtPasswd.setEditable(true);
		}
		if (o == btnGuardar) {
			// Si se presionó el botón "Guardar", intenta guardar los cambios en la base de
			// datos
			modificado = UserFactory.getManageUser().modifyUserData(nickOriginal, passwdOriginal, nickNew, passwd);
			System.out.println(modificado);
			if (modificado == true) {
				// Si se modificaron los datos con éxito
				// Deshabilita la edición de los campos
				txtNick.setEditable(false);
				txtPasswd.setEditable(false);
				MakeLessUgly.showMessageDialog("Se ha modificado correctamente", "Success",
						JOptionPane.INFORMATION_MESSAGE);

				// Actualizar los campos con los nuevos datos
				txtNick.setText(nickNew);
				txtPasswd.setText(passwd);
			} else {
				MakeLessUgly.showMessageDialog("No se han podido modificar los datos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		//cargarDatosUsuario();
	}
}