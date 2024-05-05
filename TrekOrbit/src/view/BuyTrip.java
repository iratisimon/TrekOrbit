package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.TravelController;
import controller.UserController;
import model.Planet;
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
	private JLabel lblVolver;
	private String selectedPlanet;
	private TravelController travelControl;
	private Planet planet;
	private String path = "/images/";
	private String png = ".png";
	private Ser ser;
	private AccessController controladorAcceso;
	private UserController controladorUsuario;

	public BuyTrip(TravelController controlador, Ser ser, AccessController controladorAcceso,
			UserController controladorUsuario) {
		this.travelControl = controlador;
		this.ser = ser;
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;
		ser.setId("S001");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuyTrip.class.getResource("/images/logotipo_trekorbit.png")));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMercurio = createClickableLabel("/images/Mercurio.png", 230, 240, 93, 119, "Mercurio",
				travelControl.getPlanetDisponibility("Mercurio").get("Mercurio"));
		contentPane.add(lblMercurio);

		lblVenus = createClickableLabel("/images/Venus.png", 180, 40, 129, 128, "Venus",
				travelControl.getPlanetDisponibility("Venus").get("Venus"));
		contentPane.add(lblVenus);

		lblTierra = createClickableLabel("/images/Tierra.png", 270, 490, 130, 119, "Tierra",
				travelControl.getPlanetDisponibility("Tierra").get("Tierra"));
		contentPane.add(lblTierra);

		lblMarte = createClickableLabel("/images/Marte.png", 450, 365, 124, 138, "Marte",
				travelControl.getPlanetDisponibility("Marte").get("Marte"));
		contentPane.add(lblMarte);

		lblJupiter = createClickableLabel("/images/Jupiter.png", 505, 50, 200, 177, "Jupiter",
				travelControl.getPlanetDisponibility("Jupiter").get("Jupiter"));
		contentPane.add(lblJupiter);

		lblSaturno = createClickableLabel("/images/Saturno.png", 640, 300, 160, 160, "Saturno",
				travelControl.getPlanetDisponibility("Saturno").get("Saturno"));
		contentPane.add(lblSaturno);

		lblUrano = createClickableLabel("/images/Urano.png", 740, 150, 126, 133, "Urano",
				travelControl.getPlanetDisponibility("Urano").get("Urano"));
		contentPane.add(lblUrano);

		lblNeptuno = createClickableLabel("/images/Neptuno.png", 805, 480, 110, 110, "Neptuno",
				travelControl.getPlanetDisponibility("Neptuno").get("Neptuno"));
		contentPane.add(lblNeptuno);

		lblPluton = createClickableLabel("/images/Pluton.png", 940, 300, 90, 90, "Pluton",
				travelControl.getPlanetDisponibility("Pluton").get("Pluton"));
		contentPane.add(lblPluton);

		lblVolver = createClickableLabel("/images/VolverBlanco.png", 22, 0, 134, 75, "volver", true);
		contentPane.add(lblVolver);

		JLabel treki = new JLabel("");
		treki.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/TrekiViajero.png")));
		treki.setBounds(693, 10, 348, 174);
		contentPane.add(treki);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/FondoOrbitas.png")));
		lblFondo.setBounds(0, 10, 1010, 633);
		contentPane.add(lblFondo);

	}

	// Método para crear JLabels clicables
	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height, String planetName,
			boolean disponibilidad) {

		JLabel label = new JLabel("");
		label.setBounds(x, y, width, height);
		if (!disponibilidad) {
			String grayImagePath = path + planetName + "B" + png;
			label.setIcon(new ImageIcon(getClass().getResource(grayImagePath)));
		} else {
			label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectedPlanet = planetName;
					if (selectedPlanet.equalsIgnoreCase("volver")) {
						UserMenu volverMenu = new UserMenu(controladorAcceso, controladorUsuario, ser);
						volverMenu.setVisible(true);
						dispose();
					} else {
						openBuyTripPartTwo(selectedPlanet);
					}
				}
			});
		}
		return label;
	}

	// Función para abrir la ventana de BuyTripPartTwo
	private void openBuyTripPartTwo(String selectedPlanet) {
		planet = travelControl.getPlanet(selectedPlanet);
		BuyTripPartTwo buyTrip = new BuyTripPartTwo(selectedPlanet, travelControl, ser, planet, controladorAcceso,
				controladorUsuario);
		buyTrip.setVisible(true);
		dispose();
	}

}
