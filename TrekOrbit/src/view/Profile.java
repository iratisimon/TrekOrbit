package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class Profile extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNick;
	private JPasswordField txtPasswd;
	private JLabel lblMensaje;
	private JButton btnModificar;
	private JButton btnGuardar;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtRaza;
	
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
		lblNick.setFont(new Font("Verdana", Font.BOLD, 25));
		lblNick.setForeground(new Color(255, 255, 255));
		lblNick.setBounds(465, 161, 113, 28);
		contentPane.add(lblNick);

		txtNick = new JTextField();
		txtNick.setBounds(679, 157, 262, 32);
		txtNick.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtNick);
		txtNick.setColumns(10);

		JLabel lblPasswd = new JLabel("Contraseña:");
		lblPasswd.setFont(new Font("Verdana", Font.BOLD, 25));
		lblPasswd.setForeground(new Color(255, 255, 255));
		lblPasswd.setBounds(465, 220, 188, 32);
		contentPane.add(lblPasswd);

		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(679, 220, 262, 32);
		txtPasswd.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtPasswd);
		txtPasswd.setColumns(10);
		
		JLabel lblRaza = new JLabel ("Mi raza es: ");
		lblRaza.setFont(new Font("Verdana", Font.BOLD, 25));
		lblRaza.setForeground(new Color(255, 255, 255));
		lblRaza.setBounds(92, 463, 188, 33);
		contentPane.add(lblRaza);
		
		txtRaza = new JTextField();
		txtRaza.setEditable(false);
		txtRaza.setBounds(329, 463, 164, 28);
		contentPane.add(txtRaza);
		txtRaza.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Verdana", Font.BOLD, 20));
		btnModificar.setForeground(new Color(0, 0, 128));
		btnModificar.setBackground(new Color(255, 204, 255));
		btnModificar.setBounds(603, 305, 148, 38);
		contentPane.add(btnModificar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(0, 0, 128));
		btnGuardar.setFont(new Font("Verdana", Font.BOLD, 20));
		btnGuardar.setBackground(new Color(255, 204, 255));
		btnGuardar.setBounds(793, 305, 148, 38);
		contentPane.add(btnGuardar);
		
		JLabel fotoPerfil = new JLabel("");
		fotoPerfil.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\Treky12.png"));
		fotoPerfil.setBounds(92, 92, 316, 322);
		contentPane.add(fotoPerfil);
		
		lblMensaje = new JLabel("");
		lblMensaje.setForeground(new Color(204, 0, 51));
		lblMensaje.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
		lblMensaje.setBounds(541, 399, 400, 28);
		contentPane.add(lblMensaje);
		
		lblNombre = new JLabel("Mi nombre es:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 25));
		lblNombre.setBounds(92, 522, 219, 28);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(329, 522, 164, 28);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\galaxy.jpg"));
		fondo.setBounds(10, 0, 1021, 657);
		contentPane.add(fondo);
		
		btnGuardar.addActionListener(this);
		btnModificar.addActionListener(this);
		
		cargarDatosUsuario();
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
	            lblMensaje.setForeground(new Color(51, 255, 102));
	            lblMensaje.setText("Se ha modificado correctamente");
	            
	            // Actualizar los campos con los nuevos datos
	            txtNick.setText(nickNew);
	            txtPasswd.setText(passwd);
	        } else {
	            // Si no se pudieron guardar los cambios
	            lblMensaje.setForeground(new Color(204, 0, 51));
	            lblMensaje.setText("No se pudo modificar los datos");
	        }
	    }	
	}
}