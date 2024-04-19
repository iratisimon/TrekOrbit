package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class MenuUsuario extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton NuevaAventura;
	JButton MisAventuras;

	/**
	 * Create the frame.
	 */
	public MenuUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label para el icono del perfil
		JLabel iconoPerfil = new JLabel("");
        iconoPerfil.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\treky4-removebg-preview.png")); // Cambia la ruta según la ubicación de tu icono de perfil
        iconoPerfil.setBounds(51, 23, 184, 228); // Ajusta las coordenadas y el tamaño según tus necesidades
        contentPane.add(iconoPerfil);
		
		//label para foto de treky
		JLabel treky = new JLabel("");
		treky.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\treky8-removebg-preview.png"));
		treky.setBounds(427, 325, 391, 358);
		contentPane.add(treky);
		
		//boton para acceder a la ventana de comprar viaje
		NuevaAventura = new JButton("Nueva Aventura ");
		NuevaAventura.setForeground(new Color(255, 255, 255));
		NuevaAventura.setBackground(new Color(128, 128, 255));
		NuevaAventura.setFont(new Font("Verdana", Font.BOLD, 30));
		NuevaAventura.setBounds(60, 375, 380, 150);
		contentPane.add(NuevaAventura);
		
		//boton para acceder a la ventana que te muestra los viajes comprados
		MisAventuras = new JButton("Mis Aventuras");
		MisAventuras.setForeground(new Color(255, 255, 255));
		MisAventuras.setBackground(new Color(128, 128, 255));
		MisAventuras.setFont(new Font("Verdana", Font.BOLD, 30));
		MisAventuras.setBounds(828, 375, 380, 150);
		contentPane.add(MisAventuras);
		
		//label de titulo 
		JLabel bienvenida = new JLabel("Bienvenid@! ");
		bienvenida.setForeground(Color.WHITE);
		bienvenida.setFont(new Font("Verdana", Font.BOLD, 99));
		bienvenida.setBounds(361, 73, 764, 100);
		contentPane.add(bienvenida);
		
		//label para el fondo
		JLabel fondo = new JLabel("");
		fondo.setForeground(Color.WHITE);
		fondo.setIcon(new ImageIcon("C:\\Users\\1dami\\Downloads\\galaxy.jpg"));
		fondo.setBounds(0, 0, 1266, 683);
		contentPane.add(fondo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == NuevaAventura) {
			
		} else if (o == MisAventuras) {
			
		}
	}
}
