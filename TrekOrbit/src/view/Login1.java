package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class Login1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField passwd;
	private JButton boton;
	private JButton registro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login1 frame = new Login1();
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
	public Login1() {
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
		lblNewLabel_1.setBounds(426, 223, 129, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CONTRASEÑA: ");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(426, 275, 169, 42);
		contentPane.add(lblNewLabel_1_1);
		
		user = new JTextField();
		user.setBounds(553, 236, 241, 25);
		contentPane.add(user);
		user.setColumns(10);
		
		passwd = new JPasswordField();
		passwd.setBounds(585, 288, 209, 25);
		contentPane.add(passwd);
		passwd.setColumns(10);
		
		boton = new JButton("Iniciar Sesion");
		boton.setBackground(new Color(255, 255, 255));
		boton.setForeground(new Color(0, 0, 0));
		boton.setFont(new Font("Verdana", Font.BOLD, 13));
		boton.setBounds(563, 355, 129, 25);
		contentPane.add(boton);
		
		JLabel lblNewLabel_2 = new JLabel("Aun no te has registrado?");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel_2.setBounds(449, 406, 186, 30);
		contentPane.add(lblNewLabel_2);
		
		registro = new JButton("Registrarse");
		registro.setBackground(new Color(255, 255, 255));
		registro.setFont(new Font("Verdana", Font.BOLD, 9));
		registro.setBounds(645, 413, 104, 18);
		contentPane.add(registro);
		registro.addActionListener(this);

		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 10));
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\pablo\\Downloads\\stars_space_galaxy_117958_1280x720.jpg"));
		lblNewLabel_3.setBounds(10, 10, 1246, 673);
		contentPane.add(lblNewLabel_3);
	}
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if (o==registro) {
			dispose();
			Login2 login2 = new Login2();
			login2.setVisible(true);
		}
		
		
		
	}
}
