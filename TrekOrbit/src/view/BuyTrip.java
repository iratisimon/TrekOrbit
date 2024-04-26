package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TravelController;
import model.Ser;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private String selectedPlanet;
	private TravelController travelControl;
	private Ser ser;

	/**
	 * Create the frame.
	 */
	public BuyTrip(TravelController controlador,Ser ser) {
		this.travelControl = controlador;
		this.ser=ser;
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuyTrip.class.getResource("/images/logotipo_trekorbit.png")));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMercurio = createClickableLabel("/images/Mercurio.png", 230, 240, 93, 119, "Mercurio");
		contentPane.add(lblMercurio);

		lblVenus = createClickableLabel("/images/Venus.png", 180, 40, 129, 128, "Venus");
		contentPane.add(lblVenus);

		lblTierra = createClickableLabel("/images/Tierra.png", 270, 490, 130, 119, "Tierra");
		contentPane.add(lblTierra);

		lblMarte = createClickableLabel("/images/Marte.png", 450, 365, 124, 138, "Marte");
		contentPane.add(lblMarte);

		lblJupiter = createClickableLabel("/images/Jupiter.png", 505, 50, 200, 177, "Jupiter");
		contentPane.add(lblJupiter);

		lblSaturno = createClickableLabel("/images/Saturno.png", 640, 300, 160, 160, "Saturno");
		contentPane.add(lblSaturno);

		lblUrano = createClickableLabel("/images/Urano.png", 740, 150, 126, 133, "Urano");
		contentPane.add(lblUrano);

		lblNeptuno = createClickableLabel("/images/Neptuno.png", 805, 480, 110, 110, "Neptuno");
		contentPane.add(lblNeptuno);

		lblPluton = createClickableLabel("/images/Pluton.png", 940, 300, 90, 90, "Pluton");
		contentPane.add(lblPluton);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/FondoOrbitas.png")));
		lblFondo.setBounds(0, 0, 1008, 640);
		contentPane.add(lblFondo);
	}

	// Método para crear JLabels clicables
	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height, String planetName) {
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		label.setBounds(x, y, width, height);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar por encima del
																			// JLabel
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedPlanet = planetName;
				openBuyTripPartTwo(selectedPlanet);
			}
		});
		return label;
	}

	// Función para abrir la ventana de BuyTripPartTwo
	private void openBuyTripPartTwo(String selectedPlanet) {
		BuyTripPartTwo buyTrip = new BuyTripPartTwo(selectedPlanet, travelControl,ser);
		buyTrip.setVisible(true);
		dispose();
	}

}
