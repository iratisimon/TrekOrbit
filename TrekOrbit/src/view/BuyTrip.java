package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BuyTrip extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMercurio;
	private JLabel lblVenus;
	private JLabel lblTierra;
	private JLabel lblMarte;
	private JLabel lblJupiter;
	private JLabel lblSaturno;
	private JLabel lblUrano;
	private JLabel lblNeptuno;
	private JLabel lblPluton;

	/**
	 * Create the frame.
	 */
	public BuyTrip() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuyTrip.class.getResource("/images/logotipo_trekorbit.png")));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMercurio = new JLabel("");
		lblMercurio.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Mercurio.png")));
		lblMercurio.setBounds(230, 208, 93, 119);
		contentPane.add(lblMercurio);
		
		lblVenus = new JLabel("");
		lblVenus.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Venus.png")));
		lblVenus.setBounds(362, 159, 186, 163);
		contentPane.add(lblVenus);
		
		lblTierra = new JLabel("");
		lblTierra.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Tierra.png")));
		lblTierra.setBounds(420, 376, 162, 148);
		contentPane.add(lblTierra);
		
		lblMarte = new JLabel("");
		lblMarte.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Marte.png")));
		lblMarte.setBounds(552, 365, 124, 138);
		contentPane.add(lblMarte);
		
		lblJupiter = new JLabel("");
		lblJupiter.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Jupiter.png")));
		lblJupiter.setBounds(644, 186, 450, 554);
		contentPane.add(lblJupiter);
		
		lblSaturno = new JLabel("");
		lblSaturno.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Saturno.png")));
		lblSaturno.setBounds(750, 534, 150, 150);
		contentPane.add(lblSaturno);
		
		lblUrano = new JLabel("");
		lblUrano.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Urano.png")));
		lblUrano.setBounds(850, 208, 126, 133);
		contentPane.add(lblUrano);
		
		lblNeptuno = new JLabel("");
		lblNeptuno.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Neptuno.png")));
		lblNeptuno.setBounds(939, 340, 217, 214);
		contentPane.add(lblNeptuno);
		
		lblPluton = new JLabel("");
		lblPluton.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/Pluton.png")));
		lblPluton.setBounds(1000, 413, 112, 136);
		contentPane.add(lblPluton);
				
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/FondoOrbitas.png")));
		lblFondo.setBounds(100, 100, 1024, 680);
		contentPane.add(lblFondo);
		
	}
}
