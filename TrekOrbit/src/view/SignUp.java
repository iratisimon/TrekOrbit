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

public class SignUp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldNick;
	private JPasswordField textFieldRepiteNewpasswd;
	private JPasswordField textFieldIntroducirNewPasswd;
	private JButton registrarseBtn;
	private JButton iniciarSesionBtn;
	//private JLabel mensaje;
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

		/*
		 * mensaje = new JLabel("");
		 * mensaje.setHorizontalAlignment(SwingConstants.CENTER); mensaje.setFont(new
		 * Font("Verdana", Font.BOLD, 14)); mensaje.setForeground(new Color(255, 0, 0));
		 * mensaje.setBounds(624, 442, 276, 35); mensaje.setVisible(false);
		 * contentPane.add(mensaje);
		 */

		textFieldRaza = new JTextField();
		textFieldRaza.setBounds(420, 272, 276, 25);
		contentPane.add(textFieldRaza);
		textFieldRaza.setColumns(10);
		
		show = new JButton("New button") {
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
        show.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\ojorojo1.png"));
        show.setBounds(763, 326, 52, 27);
        show.setBorderPainted(false); // Oculta el borde del botón
        show.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        show.addActionListener(this);

        getContentPane().add(show);
		
        hide = new JButton("New button") {
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
        hide.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\ojorojo1.png"));
        hide.setBounds(763, 324, 52, 27);
        hide.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        hide.setBorderPainted(false); // Oculta el borde del botó
        hide.addActionListener(this);

        getContentPane().add(hide);
        
        
        //BOTON 2 
        
        show2 = new JButton("New button") {
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
        show2.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\ojorojo1.png"));
        show2.setBounds(763, 381, 52, 27);
        show2.setBorderPainted(false); // Oculta el borde del botón
        show2.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        show2.addActionListener(this);

        getContentPane().add(show2);
		
        hide2 = new JButton("New button") {
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
        hide2.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\ojorojo1.png"));
        hide2.setBounds(763, 381, 52, 27);
        hide2.setContentAreaFilled(false); // Hace que el área de contenido del botón sea transparente
        hide2.setBorderPainted(false); // Oculta el borde del botó
        hide2.addActionListener(this);

        getContentPane().add(hide2);
        

		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));

  //pablo
		fondo.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\galaxy.jpg"));

  //irati
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\galaxy.jpg"));

		fondo.setBounds(10, 10, 984, 582);
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
		
		//BOTON 2
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
			String passwd1 = new String (textFieldIntroducirNewPasswd.getPassword());
			String passwd2 = new String (textFieldRepiteNewpasswd.getPassword());
			String nick = textFieldNick.getText();
			String raza = textFieldRaza.getText();
			String nombre = textFieldNombre.getText();

			// Verificar que ningún campo esté vacío
			if (passwd1.isEmpty() || passwd2.isEmpty() || nick.isEmpty() || raza.isEmpty() || nombre.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				if (!passwd1.equals(passwd2)) {
					JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean modificado = controladorAcceso.singUp(nombre, nick, raza, passwd1);
					if (modificado) {
						JOptionPane.showMessageDialog(this, "Se ha registrado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
						double segundos = 0.7;
						try {
							Thread.sleep((long) (segundos * 1000));
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}

						dispose(); //esto no lo esta haciendo???
						LogIn login1 = new LogIn(controladorAcceso);
						login1.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(this, "Nombre de usuario existente", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}