package view;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Welcome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Welcome() {
		setTitle("WELCOME");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/images/logotipo_trekorbit.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fondo = new JLabel("");
		fondo.setFont(new Font("Verdana", Font.BOLD, 10));
		fondo.setIcon(new ImageIcon(Welcome.class.getResource("/images/AL INFINITO Y MAS ALLÁ.png")));
		fondo.setBounds(0, 0, 992, 601);
		contentPane.add(fondo);
		
		JLabel fondoAnimado = new JLabel("");
		fondoAnimado.setFont(new Font("Verdana", Font.BOLD, 10));
		fondoAnimado.setIcon(new ImageIcon(Welcome.class.getResource("/images/fondito.gif")));
		fondoAnimado.setBounds(0, 0, 992, 601);
		contentPane.add(fondoAnimado);

	}
}
