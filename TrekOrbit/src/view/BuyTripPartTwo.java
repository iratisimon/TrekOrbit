package view;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import controller.TravelController;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.SwingConstants;

public class BuyTripPartTwo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String path = "/images/";
	private String png = ".png";
	private TravelController travelControl;
	private JLabel lblVolver;
	private JLabel lblReservar;
	private JCalendar calendar;

	/**
	 * Create the frame.
	 */
	public BuyTripPartTwo(String planetName, TravelController controlador) {

		this.travelControl = controlador;
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
        
        calendar = new JCalendar();
        calendar.getYearChooser().getSpinner().setForeground(new Color(255, 255, 255));
        calendar.getYearChooser().getSpinner().setBackground(new Color(23, 17, 39));
        calendar.getMonthChooser().getComboBox().setForeground(new Color(255, 255, 255));
        calendar.getMonthChooser().getComboBox().setBackground(new Color(23, 17, 39));
		calendar.getDayChooser().setBackground(Color.WHITE);
		calendar.getDayChooser().setWeekdayForeground(Color.WHITE);
		calendar.setBackground(new Color(0,0,0,0));
		calendar.setWeekdayForeground(Color.WHITE);
		calendar.setWeekOfYearVisible(false);
		calendar.setDecorationBackgroundColor(new Color(0,0,0,0));
		calendar.setFont(new Font("OCR A Extended", Font.BOLD, 12));
		// Ubicar y agregar al panel
		calendar.setBounds(660, 340, 300, 200);
        getContentPane().add(calendar);
        
		JLabel lblPlaneta = new JLabel("");
		lblPlaneta.setBounds(660, 50, 300, 300);
		lblPlaneta.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		contentPane.add(lblPlaneta);

		JLabel lblPlanetName = new JLabel(planetName);
		lblPlanetName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlanetName.setFont(new Font("Magneto", Font.PLAIN, 50));
		lblPlanetName.setForeground(new Color(255, 255, 255));
		lblPlanetName.setBounds(611, 15, 400, 50);
		contentPane.add(lblPlanetName);

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
					BuyTrip volver = new BuyTrip(travelControl);
					volver.setVisible(true);
					dispose();
				} else if (label == lblReservar) {
					ConfirmReservation reserva = new ConfirmReservation(travelControl);
					reserva.setVisible(true);
					dispose();
				}
			}
		});
		return label;
	}
}
