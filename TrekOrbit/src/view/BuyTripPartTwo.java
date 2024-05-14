package view;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import controller.AccessController;
import controller.TravelController;
import controller.UserController;
import model.Planet;
import model.Planeta;
import model.Ser;
import model.Travel;
import ownExceptions.FechaNoSeleccionadaException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.UIManager;

public class BuyTripPartTwo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String path = "/images/";
	private String png = ".png";
	private TravelController travelControl;
	private JLabel lblVolver;
	private JLabel lblReservar;
	private JComboBox<String> comboBoxPlanetasOrigen;
	private Planet planeta;
	private Ser ser;
	private LocalDate selectedDate;
	private JLabel poblacion;
	private JLabel superficie;
	private JLabel clima;
	private JCalendar calendar;
	private AccessController controladorAcceso;
	private UserController controladorUsuario;

	public BuyTripPartTwo(String planetName, TravelController controlador, Ser ser, Planet planet,
			AccessController controladorAcceso, UserController controladorUsuario) {

		MakeLessUgly.setDefaultCursor(this);
		this.travelControl = controlador;
		this.ser = ser;
		this.planeta = planet;
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;
		String imagePath = path + planetName + 400 + png;

		setIconImage(Toolkit.getDefaultToolkit().getImage(BuyTrip.class.getResource("/images/logotipo_trekorbit.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblReservar = createClickableLabel("/images/ReservarBlanco.png", 880, 555, 134, 75);
		contentPane.add(lblReservar);

		lblVolver = createClickableLabel("/images/VolverBlanco.png", 10, 11, 134, 75);
		contentPane.add(lblVolver);

		superficie = new JLabel("");
		superficie.setForeground(Color.WHITE);
		superficie.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		superficie.setBackground(UIManager.getColor("Button.background"));
		superficie.setBounds(182, 156, 172, 41);
		superficie.setText(String.valueOf(planeta.getSuperficie()));
		contentPane.add(superficie);

		clima = new JLabel("");
		clima.setForeground(Color.WHITE);
		clima.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		clima.setBackground(UIManager.getColor("Button.background"));
		clima.setBounds(115, 207, 510, 41);
		clima.setText(planeta.getClima());
		contentPane.add(clima);

		poblacion = new JLabel("");
		poblacion.setForeground(Color.WHITE);
		poblacion.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		poblacion.setBackground(UIManager.getColor("Button.background"));
		poblacion.setBounds(511, 156, 172, 41);
		poblacion.setText(String.valueOf(planeta.getHabitantes()));
		contentPane.add(poblacion);

		comboBoxPlanetasOrigen = new JComboBox<String>();
		comboBoxPlanetasOrigen.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		for (Planeta p : Planeta.values()) {
			String planetas = p.name();
			if (!planetas.equalsIgnoreCase(planetName)) {
				comboBoxPlanetasOrigen.addItem(planetas);
			}
		}
		comboBoxPlanetasOrigen.setBackground(new Color(23, 17, 39));
		comboBoxPlanetasOrigen.setForeground(new Color(255, 255, 255));
		comboBoxPlanetasOrigen.setBounds(182, 276, 221, 41);
		contentPane.add(comboBoxPlanetasOrigen);

		// Obtener la lista de actividades del planeta
		ArrayList<String> actividades = travelControl.getPlanetActivities(planetName);

		// Crear checkboxes para cada actividad
		int yCoord = 400; // Coordenada Y inicial
		for (String actividad : actividades) {
			JCheckBox checkBox = new JCheckBox(actividad);
			checkBox.setFont(new Font("OCR A Extended", Font.PLAIN, 20));
			checkBox.setForeground(Color.WHITE);
			checkBox.setBackground(new Color(23, 17, 39));
			checkBox.setBounds(36, yCoord, 300, 50); // Ajusta las coordenadas X e Y según tu diseño
			contentPane.add(checkBox);
			yCoord += 40; // Incrementa la coordenada Y para la próxima checkbox
		}

		calendar = new JCalendar();
		calendar.getDayChooser().setWeekdayForeground(new Color(255, 255, 255));
		calendar.getDayChooser().setSundayForeground(new Color(255, 255, 255));
		calendar.setBounds(660, 346, 300, 200);
		contentPane.add(calendar);
		MakeLessUgly.customizeCalendar(calendar);

		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// Obtener la fecha seleccionada del JCalendar
				java.util.Date selectedUtilDate = calendar.getDate();
				Instant instant = selectedUtilDate.toInstant();
				selectedDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

				// Obtener la fecha actual
				LocalDate currentDate = LocalDate.now();

				// Comparar la fecha seleccionada con la fecha actual
				if (selectedDate.isBefore(currentDate)) {
					// Si la fecha seleccionada es anterior a la fecha actual, mostrar un mensaje de
					// error
					MakeLessUgly.showMessageDialog("No se puede seleccionar una fecha anterior a la actual.",
							"Fecha Inválida", JOptionPane.PLAIN_MESSAGE);

					// Restaurar la fecha seleccionada al día actual
					calendar.setDate(java.sql.Date.valueOf(currentDate));
				}
			}

		});

		JLabel lblPlaneta = new JLabel("");
		lblPlaneta.setBounds(660, 67, 300, 270);
		lblPlaneta.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		contentPane.add(lblPlaneta);

		JLabel lblCaracteristicas = new JLabel("Características:");
		lblCaracteristicas.setForeground(Color.WHITE);
		lblCaracteristicas.setFont(new Font("Magneto", Font.PLAIN, 33));
		lblCaracteristicas.setBounds(10, 114, 362, 41);
		contentPane.add(lblCaracteristicas);

		JLabel lblSuperficie = new JLabel("Superficie:");
		lblSuperficie.setBackground(new Color(240, 240, 240));
		lblSuperficie.setForeground(Color.WHITE);
		lblSuperficie.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		lblSuperficie.setBounds(10, 156, 172, 41);
		contentPane.add(lblSuperficie);

		JLabel lblClima = new JLabel("Clima:");
		lblClima.setForeground(Color.WHITE);
		lblClima.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		lblClima.setBackground(UIManager.getColor("Button.background"));
		lblClima.setBounds(10, 207, 116, 41);
		contentPane.add(lblClima);

		JLabel lblHabitantes = new JLabel("Población:");
		lblHabitantes.setForeground(Color.WHITE);
		lblHabitantes.setFont(new Font("OCR A Extended", Font.PLAIN, 25));
		lblHabitantes.setBackground(UIManager.getColor("Button.background"));
		lblHabitantes.setBounds(355, 156, 246, 41);
		contentPane.add(lblHabitantes);

		JLabel lblActividades = new JLabel("¿Qué actividades deseas realizar?");
		lblActividades.setForeground(new Color(255, 255, 255));
		lblActividades.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividades.setFont(new Font("Magneto", Font.PLAIN, 30));
		lblActividades.setBounds(34, 346, 591, 41);
		contentPane.add(lblActividades);

		JLabel lblPlanetName = new JLabel(planetName);
		lblPlanetName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanetName.setFont(new Font("Magneto", Font.PLAIN, 50));
		lblPlanetName.setForeground(new Color(255, 255, 255));
		lblPlanetName.setBounds(611, 15, 400, 50);
		contentPane.add(lblPlanetName);

		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setForeground(new Color(255, 255, 255));
		lblOrigen.setBounds(10, 278, 145, 41);
		lblOrigen.setFont(new Font("Magneto", Font.PLAIN, 33));
		contentPane.add(lblOrigen);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(BuyTrip.class.getResource("/images/galaxy.jpg")));
		lblFondo.setBounds(0, 0, 1008, 640);
		contentPane.add(lblFondo);

	}

	private JLabel createClickableLabel(String imagePath, int x, int y, int width, int height) {
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		label.setBounds(x, y, width, height);
		MakeLessUgly.setAlienCursor(label);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar qué etiqueta fue clicada
				if (label == lblVolver) {
					// Si fue la etiqueta "Volver", volver a la ventana anterior
					BuyTrip volver = new BuyTrip(travelControl, ser, controladorAcceso, controladorUsuario);
					volver.setVisible(true);
					dispose();
				} else if (label == lblReservar) {
					ArrayList<String> actividadesSeleccionadas = new ArrayList<>();
					for (Component component : contentPane.getComponents()) {
						if (component instanceof JCheckBox) {
							JCheckBox checkBox = (JCheckBox) component;
							if (checkBox.isSelected()) {
								// Si el checkbox está seleccionado, añadir la actividad a la lista
								actividadesSeleccionadas.add(checkBox.getText());
							}
						}
					}
					try {
						// Verificar si se ha seleccionado una fecha
						if (selectedDate == null) {
							throw new FechaNoSeleccionadaException("Por favor, selecciona una fecha de viaje.");
						}
						Travel travel = new Travel();
						String planetaOrigen = (String) comboBoxPlanetasOrigen.getSelectedItem();
						travel.setOrigen(Planeta.valueOf(planetaOrigen));
						travel.setNom_destino(planeta.getNom_planeta());
						travel.setFechaViaje(selectedDate);
						travel.setActividades(actividadesSeleccionadas);
						ConfirmReservation reserva = new ConfirmReservation(travelControl, travel, ser, planeta,
								controladorAcceso, controladorUsuario);
						reserva.setVisible(true);
						dispose();
					} catch (FechaNoSeleccionadaException ex) {
						MakeLessUgly.showMessageDialog("No ha seleccionado una fecha", "Datos Incompletos",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		return label;
	}

}
