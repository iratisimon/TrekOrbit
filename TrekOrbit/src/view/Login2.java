package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class Login2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newName;
	private JTextField newUser;
	private JTextField newpasswdN;
	private JTextField newPasswd;
	private JComboBox newRaza;
	private JButton newLogin;
	private JButton cuentaExist;
	private JLabel passCoin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 frame = new Login2();
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
	public Login2() {
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
		
		JLabel lblNewLabel = new JLabel("TREKORBIT");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(344, 65, 611, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_1.setBounds(426, 271, 129, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("REPITE CONTRASEÑA:");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(426, 427, 227, 42);
		contentPane.add(lblNewLabel_1_1);
		
		newName = new JTextField();
		newName.setBounds(553, 236, 276, 25);
		contentPane.add(newName);
		newName.setColumns(10);
		
		newUser = new JTextField();
		newUser.setBounds(553, 284, 276, 25);
		contentPane.add(newUser);
		newUser.setColumns(10);
		
		newLogin = new JButton("Registrarse:");
		newLogin.setBackground(new Color(255, 255, 255));
		newLogin.setForeground(new Color(0, 0, 0));
		newLogin.setFont(new Font("Verdana", Font.BOLD, 13));
		newLogin.setBounds(582, 506, 129, 25);
		contentPane.add(newLogin);
		newLogin.addActionListener(this);
		
		JLabel lblNewLabel_2 = new JLabel("Ya estas registrado?");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(481, 562, 186, 30);
		contentPane.add(lblNewLabel_2);
		
		cuentaExist = new JButton("Iniciar Sesion");
		cuentaExist.setBackground(new Color(255, 255, 255));
		cuentaExist.setFont(new Font("Verdana", Font.BOLD, 9));
		cuentaExist.setBounds(662, 569, 117, 18);
		contentPane.add(cuentaExist);
		cuentaExist.addActionListener(this);

		
		JLabel lblNewLabel_1_1_1 = new JLabel("RAZA:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(426, 323, 81, 42);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("NOMBRE:");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(426, 223, 117, 42);
		contentPane.add(lblNewLabel_1_1_2);
		
		newpasswdN = new JTextField();
		newpasswdN.setColumns(10);
		newpasswdN.setBounds(652, 439, 231, 25);
		contentPane.add(newpasswdN);
		
		newPasswd = new JTextField();
		newPasswd.setColumns(10);
		newPasswd.setBounds(642, 387, 241, 25);
		contentPane.add(newPasswd);
		
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("NUEVA CONTRASEÑA: ");
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_2_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNewLabel_1_1_2_1.setBounds(426, 375, 227, 42);
		contentPane.add(lblNewLabel_1_1_2_1);
		
		
		newRaza = new JComboBox();
		newRaza.setBounds(553, 338, 276, 21);
		contentPane.add(newRaza);
		
		
		
		
		passCoin = new JLabel("Las contraseñas no coinciden");
		passCoin.setHorizontalAlignment(SwingConstants.CENTER);
		passCoin.setFont(new Font("Verdana", Font.BOLD, 14));
		passCoin.setForeground(new Color(255, 0, 0));
		passCoin.setBounds(481, 602, 276, 35);
		passCoin.setVisible(false);
		contentPane.add(passCoin);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 10));
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\pablo\\Downloads\\stars_space_galaxy_117958_1280x720.jpg"));
		lblNewLabel_3.setBounds(10, 10, 1246, 673);
		contentPane.add(lblNewLabel_3);
	}
	
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o == cuentaExist) {
	        dispose();
	        Login1 login1 = new Login1();
	        login1.setVisible(true);
	    }
	    if (o == newLogin) {
	        passCoin.setVisible(false); // Oculta el JLabel por defecto
	        if (newPasswd.getText().equals(newpasswdN.getText())) {
	            dispose();
	        } else {
	            passCoin.setVisible(true);
	        }
	    }
	}

}
