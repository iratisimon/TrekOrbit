package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TravelController;
import model.Planet;
import model.Ser;
import model.Travel;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	/**
	 * Create the frame.
	 * 
	 * @param travelControl
	 */
	public ConfirmReservation(TravelController travelControl, Travel trip, Ser ser, Planet planet) {
		this.travelControl = travelControl;
		this.travel = trip;
		this.ser = ser;
		this.planet = planet;
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ConfirmReservation.class.getResource("/images/logotipo_trekorbit.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOrigen = new JLabel("Origen: ");
		lblOrigen.setForeground(new Color(255, 255, 255));
		lblOrigen.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblOrigen.setBounds(237, 60, 134, 33);
		contentPane.add(lblOrigen);

		JLabel lblDestino = new JLabel("Destino: ");
		lblDestino.setForeground(Color.WHITE);
		lblDestino.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblDestino.setBounds(240, 111, 163, 33);
		contentPane.add(lblDestino);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblFecha.setBounds(240, 167, 134, 33);
		contentPane.add(lblFecha);

		JLabel lblActividades = new JLabel("Actividades: ");
		lblActividades.setForeground(Color.WHITE);
		lblActividades.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblActividades.setBounds(240, 211, 238, 33);

		origen = new JLabel("");
		origen.setForeground(Color.WHITE);
		origen.setFont(new Font("Magneto", Font.PLAIN, 30));
		origen.setBounds(411, 60, 134, 33);
		origen.setText(trip.getOrigen().name());
		contentPane.add(origen);

		destino = new JLabel("");
		destino.setForeground(Color.WHITE);
		destino.setFont(new Font("Magneto", Font.PLAIN, 30));
		destino.setBounds(413, 111, 163, 33);
		destino.setText(trip.getNom_destino().name());
		contentPane.add(destino);

		fecha = new JLabel("");
		fecha.setForeground(Color.WHITE);
		fecha.setFont(new Font("Magneto", Font.PLAIN, 30));
		fecha.setBounds(411, 167, 134, 33);
		fecha.setText(trip.getFechaViaje().toString());
		contentPane.add(fecha);

		contentPane.add(lblActividades);
		lblReservar = createClickableLabel("/images/ReservarBlanco.png", 880, 555, 134, 75);
		contentPane.add(lblReservar);

		lblVolver = createClickableLabel("/images/VolverBlanco.png", 10, 11, 134, 75);
		contentPane.add(lblVolver);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/galaxy.jpg")));
		lblFondo.setBounds(0, 0, 1008, 640);
		contentPane.add(lblFondo);

	}

	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height) {
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		label.setBounds(x, y, width, height);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiar el cursor al pasar por encima del
																			// JLabel // Label
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar qué etiqueta fue clicada
				if (label == lblVolver) {
					// Si fue la etiqueta "Volver", volver a la ventana anterior
					String planetName = travel.getNom_destino().name();
					BuyTripPartTwo volver = new BuyTripPartTwo(planetName, travelControl, ser, planet);
					volver.setVisible(true);
					dispose();
				} else if (label == lblReservar) {

				}
			}
		});
		return label;
	}
}
