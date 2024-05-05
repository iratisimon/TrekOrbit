package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.TravelController;
import controller.UserController;
import model.Planet;
import model.Ser;
import model.Travel;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;

public class ConfirmReservation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblVolver;
	private JLabel lblReservar;
	private TravelController travelControl;
	private Travel travel;
	private Ser ser;
	private JLabel origen;
	private JLabel fecha;
	private JLabel destino;
	private Planet planet;
	private JTextArea actividadesTextArea;
	private AccessController controladorAcceso;
	private UserController controladorUsuario;

	public ConfirmReservation(TravelController travelControl, Travel trip, Ser ser, Planet planet,
			AccessController controladorAcceso, UserController controladorUsuario) {
		this.travelControl = travelControl;
		this.travel = trip;
		this.ser = ser;
		this.planet = planet;
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ConfirmReservation.class.getResource("/images/logotipo_trekorbit.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOrigen = new JLabel("Origen: ");
		lblOrigen.setForeground(new Color(255, 255, 255));
		lblOrigen.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblOrigen.setBounds(215, 64, 159, 33);
		contentPane.add(lblOrigen);

		JLabel lblDestino = new JLabel("Destino: ");
		lblDestino.setForeground(Color.WHITE);
		lblDestino.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblDestino.setBounds(215, 115, 163, 33);
		contentPane.add(lblDestino);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblFecha.setBounds(215, 168, 134, 33);
		contentPane.add(lblFecha);

		JLabel lblActividades = new JLabel("Actividades: ");
		lblActividades.setForeground(Color.WHITE);
		lblActividades.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblActividades.setBounds(215, 211, 238, 33);
		contentPane.add(lblActividades);

		origen = new JLabel(travel.getOrigen().toString());
		origen.setForeground(Color.WHITE);
		origen.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		origen.setBounds(411, 60, 287, 33);
		origen.setText(trip.getOrigen().name());
		contentPane.add(origen);

		destino = new JLabel(travel.getNom_destino().toString());
		destino.setForeground(Color.WHITE);
		destino.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		destino.setBounds(411, 111, 287, 33);
		destino.setText(trip.getNom_destino().name());
		contentPane.add(destino);

		fecha = new JLabel(travel.getFechaViaje().toString());
		fecha.setForeground(Color.WHITE);
		fecha.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		fecha.setBounds(411, 167, 287, 33);
		fecha.setText(trip.getFechaViaje().toString());
		contentPane.add(fecha);

		ArrayList<String> actividades = travel.getActividades();
		actividadesTextArea = new JTextArea();
		actividadesTextArea.setLineWrap(true);
		actividadesTextArea.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		actividadesTextArea.setForeground(Color.WHITE);
		actividadesTextArea.setOpaque(false);
		actividadesTextArea.setBounds(48, 254, 697, 245); // Establecer posición y tamaño
		contentPane.add(actividadesTextArea);
		for (String actividad : actividades) {
			actividadesTextArea.append(actividad + "\n"); // Agregar cada actividad seguida de un salto de línea
		}
		lblReservar = createClickableLabel("/images/AVIAJAR.png", 244, 454, 287, 179);
		contentPane.add(lblReservar);

		lblVolver = createClickableLabel("/images/VolverBlanco.png", 22, 11, 134, 75);
		contentPane.add(lblVolver);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/galaxy.jpg")));
		lblFondo.setBounds(0, 0, 793, 640);
		contentPane.add(lblFondo);

	}

	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height) {
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		label.setBounds(x, y, width, height);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar qué etiqueta fue clicada
				if (label == lblVolver) {
					// Si fue la etiqueta "Volver", volver a la ventana anterior
					String planetName = travel.getNom_destino().name();
					BuyTripPartTwo volver = new BuyTripPartTwo(planetName, travelControl, ser, planet,
							controladorAcceso, controladorUsuario);
					volver.setVisible(true);
					dispose();
				} else if (label == lblReservar) {
					LocalDate fecha = travel.getFechaViaje();
					java.sql.Date date = java.sql.Date.valueOf(fecha);
					if (travelControl.buyTrip(travel.getOrigen().name(), date, travel.getNom_destino().name(),
							ser.getId(), travel.getActividades())) {
						showMessageDialog("Que la fuerza os acompañe", "Viaje reservado",
								JOptionPane.PLAIN_MESSAGE);
						UserMenu volverMenu = new UserMenu(controladorAcceso, controladorUsuario, ser);
						volverMenu.setVisible(true);
						dispose();
					}
				}
			}
		});
		return label;
	}

	public static void showMessageDialog(String message, String title, int messageType) {
		// Establecer los colores de fondo para el JOptionPane
		UIManager.put("OptionPane.background", new Color(23, 17, 39));
		UIManager.put("Panel.background", new Color(23, 17, 39));

		// Establecer el color y la fuente del mensaje
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("OptionPane.messageFont", new Font("OCR A Extended", Font.BOLD, 20));

		// Mostrar el JOptionPane con el mensaje personalizado y la imagen
		JOptionPane.showMessageDialog(null, message, title, messageType);
	}
}
