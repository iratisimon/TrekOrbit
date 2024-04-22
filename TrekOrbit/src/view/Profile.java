package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.UserController;
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
	private JTextField txtRaza;
	private JLabel lblMensaje;
	private UserController userController;
	private User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Profile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Verdana", Font.BOLD, 25));
		lblNick.setForeground(new Color(255, 255, 255));
		lblNick.setBounds(431, 183, 113, 28);
		contentPane.add(lblNick);

		txtNick = new JTextField();
		txtNick.setBounds(645, 179, 262, 32);
		txtNick.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtNick);
		txtNick.setColumns(10);

		JLabel lblPasswd = new JLabel("Contraseña:");
		lblPasswd.setFont(new Font("Verdana", Font.BOLD, 25));
		lblPasswd.setForeground(new Color(255, 255, 255));
		lblPasswd.setBounds(431, 242, 188, 32);
		contentPane.add(lblPasswd);

		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(645, 242, 262, 32);
		txtPasswd.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtPasswd);
		txtPasswd.setColumns(10);
		
		JLabel lblRaza = new JLabel ("Mi raza es: ");
		lblRaza.setFont(new Font("Verdana", Font.BOLD, 25));
		lblRaza.setForeground(new Color(255, 255, 255));
		lblRaza.setBounds(94, 490, 188, 33);
		contentPane.add(lblRaza);
		
		txtRaza = new JPasswordField();
		txtRaza.setBounds(272, 491, 202, 32);
		txtRaza.setEditable(false); // El campo no es editable, se actualizará con los datos del usuario
		contentPane.add(txtRaza);
		txtRaza.setColumns(10);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Verdana", Font.BOLD, 20));
		btnModificar.setForeground(new Color(0, 0, 128));
		btnModificar.setBackground(new Color(255, 204, 255));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Habilitar la edición de los campos
				txtNick.setEditable(true);
				txtPasswd.setEditable(true);
			}
		});
		btnModificar.setBounds(569, 327, 148, 38);
		contentPane.add(btnModificar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(0, 0, 128));
		btnGuardar.setFont(new Font("Verdana", Font.BOLD, 20));
		btnGuardar.setBackground(new Color(255, 204, 255));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Guardar los cambios en la base de datos
				String nick = txtNick.getText();
				String passwd = txtPasswd.getText();
				boolean modificado = userController.modificarDatosUser(nick, passwd);
				if (modificado) {
					// Actualizar los campos para reflejar los cambios
					txtNick.setEditable(false);
					txtPasswd.setEditable(false);
				}
			}
		});
		btnGuardar.setBounds(759, 327, 148, 38);
		contentPane.add(btnGuardar);
		
		JLabel fotoPerfil = new JLabel("");
		fotoPerfil.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\Treky12.png"));
		fotoPerfil.setBounds(89, 127, 316, 322);
		contentPane.add(fotoPerfil);
		
		JLabel lblMensaje = new JLabel("");
		lblMensaje.setForeground(new Color(204, 0, 51));
		lblMensaje.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
		lblMensaje.setBounds(569, 413, 338, 28);
		contentPane.add(lblMensaje);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\galaxy.jpg"));
		fondo.setBounds(0, 0, 1021, 657);
		contentPane.add(fondo);
		
		 // Inicializar el controlador del usuario
        userController = new UserController();
        // Obtener los datos del usuario actual y mostrarlos en los campos
        currentUser = getCurrentUser(); // Implementa este método para obtener el usuario actualmente autenticado
        if (currentUser != null) {
            txtNick.setText(currentUser.getNick());
            txtRaza.setText(currentUser.getRaza());
        }
    }

    // Método para obtener el usuario actualmente autenticado (debe ser implementado)
    private User getCurrentUser() {
        // Implementa este método para obtener el usuario actualmente autenticado
        // Por ejemplo, podrías tener un método en tu aplicación que devuelva el usuario actualmente autenticado
        // o podrías obtenerlo de alguna manera dependiendo de tu lógica de autenticación
        return null;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 // Guardar los cambios en la base de datos
        String nick = txtNick.getText();
        String passwd = txtPasswd.getText();
        boolean modificado = userController.modificarDatosUser(nick, passwd);
        if (modificado) {
            // Actualizar los campos para reflejar los cambios
            txtNick.setEditable(false);
            txtPasswd.setEditable(false);
            lblMensaje.setForeground(new Color(51, 255, 102));
            // Mostrar mensaje de éxito
            lblMensaje.setText("Se ha modificado correctamente");
        } else {
            // Si no se modificaron los datos, mostrar un mensaje de error
        	  lblMensaje.setForeground(new Color(204, 0, 51));
        	  lblMensaje.setText("No se pudo modificar los datos");
        }
	}
}