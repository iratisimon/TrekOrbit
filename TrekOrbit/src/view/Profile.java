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
import model.Ser;
import model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class Profile extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JPasswordField txtPasswd;
	private JButton btnModificar;
	private JButton btnGuardar;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtRaza;
	private JLabel lblVolver;
	
	private AccessController controladorAcceso;
	private UserController controladorUsuario;
	private Ser ser;

	/**
	 * Create the frame.
	 */
	
	public Profile(AccessController controladorAcceso, UserController controladorUsuario, Ser ser) {
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;
		this.ser = ser;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Magneto", Font.BOLD, 25));
		lblNick.setForeground(new Color(255, 255, 255));
		lblNick.setBounds(465, 177, 113, 28);
		contentPane.add(lblNick);

		txtNick = new JTextField();
		txtNick.setBounds(679, 173, 262, 32);
		txtNick.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtNick);
		txtNick.setColumns(10);

		JLabel lblPasswd = new JLabel("Contraseña:");
		lblPasswd.setFont(new Font("Magneto", Font.BOLD, 25));
		lblPasswd.setForeground(new Color(255, 255, 255));
		lblPasswd.setBounds(465, 236, 188, 32);
		contentPane.add(lblPasswd);

		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(679, 236, 262, 32);
		txtPasswd.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtPasswd);
		txtPasswd.setColumns(10);
		
		JLabel lblRaza = new JLabel ("Mi raza: ");
		lblRaza.setFont(new Font("Magneto", Font.BOLD, 25));
		lblRaza.setForeground(new Color(255, 255, 255));
		lblRaza.setBounds(465, 399, 188, 33);
		contentPane.add(lblRaza);
		
		txtRaza = new JTextField();
		txtRaza.setEditable(false);
		txtRaza.setBounds(679, 408, 262, 28);
		contentPane.add(txtRaza);
		txtRaza.setColumns(10);

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
		
		JLabel fotoPerfil = new JLabel("");

  //pablo
		fotoPerfil.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\Treky12.png"));
		fotoPerfil.setBounds(93, 173, 316, 322);

  //irati
		fotoPerfil.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\Treky12.png"));
		fotoPerfil.setBounds(93, 173, 316, 322);

		contentPane.add(fotoPerfil);
		
		lblNombre = new JLabel("Mi nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Magneto", Font.BOLD, 25));
		lblNombre.setBounds(465, 467, 219, 28);
		contentPane.add(lblNombre);
		
		lblVolver = createClickableLabel("/images/VolverBlanco.png", 10, 11, 134, 75);
		contentPane.add(lblVolver);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(679, 467, 262, 28);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel fondo = new JLabel("");

  //pablo
		fondo.setIcon(new ImageIcon("C:\\Users\\pablo\\OneDrive\\Escritorio\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\galaxy.jpg"));

  //irati
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\Repositorio\\TrekOrbit\\TrekOrbit\\src\\images\\galaxy.jpg"));

		fondo.setBounds(10, 0, 1021, 657);
		contentPane.add(fondo);
		
		btnGuardar.addActionListener(this);
		btnModificar.addActionListener(this);
		
		cargarDatosUsuario();
    }
	
	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		label.setBounds(42, 43, width, height);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar por encima del
																			// JLabel // Label
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar qué etiqueta fue clicada
				if (label == lblVolver) {
					// Si fue la etiqueta "Volver", volver a la ventana anterior
					UserMenu volver = new UserMenu(controladorAcceso, controladorUsuario,ser);
					volver.setVisible(true);
					dispose();
				}
			}
		});
		return label;
	}

	public void cargarDatosUsuario() {
	    // Obtener los datos del usuario utilizando el controlador de usuario
	    User datosUsuario = controladorUsuario.mostrarDatosUser(ser);
	
	    if (datosUsuario != null) {
	        // Establecer los valores en los campos de texto
	        txtNick.setText(ser.getNick());
	        txtPasswd.setText(ser.getPasswd());
	        txtRaza.setText(datosUsuario.getRaza());
            txtNombre.setText(datosUsuario.getNombre());
	    } else {
	        System.out.println("No se pudieron obtener los datos del usuario.");
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
	    String nickOriginal = ser.getNick();
	    String passwdOriginal = ser.getPasswd(); // Agregar la obtención de la contraseña original
	    String nickNew = txtNick.getText();
	    String passwd = new String(txtPasswd.getPassword());
	    boolean modificado = false;
		
	    if (o == btnModificar) {
	        // Si se presionó el botón "Modificar", habilita la edición de los campos
	    	ser.setNick(nickNew);
	    	System.out.println(ser.getNick());
	    	
	        txtNick.setEditable(true);
	        txtPasswd.setEditable(true);
	    }
	    if (o == btnGuardar) {
	        // Si se presionó el botón "Guardar", intenta guardar los cambios en la base de datos
	        modificado = controladorUsuario.modificarDatosUser(nickOriginal, passwdOriginal, nickNew, passwd);
	        System.out.println(modificado);
	        if (modificado == true) {
	            // Si se modificaron los datos con éxito
	            // Deshabilita la edición de los campos
	            txtNick.setEditable(false);
	            txtPasswd.setEditable(false);
	            JOptionPane.showMessageDialog(this, "Se ha modificado correctamente", "Success", JOptionPane.INFORMATION_MESSAGE);
                
	            
	            // Actualizar los campos con los nuevos datos
	            txtNick.setText(nickNew);
	            txtPasswd.setText(passwd);
	        } else {
	            JOptionPane.showMessageDialog(this, "No se han podido modificar los datos", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }	
	}
}