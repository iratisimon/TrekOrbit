package view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.AccessController;
import controller.TravelController;
import controller.UserController;
import factory.TravelFactory;
import model.Ser;
import model.Travel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Cursor;

public class SeeTrips extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TravelController controladorViaje;
	private Ser ser;
	private AccessController controladorAcceso;
	private UserController controladorUsuario;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JLabel lblInfoViaje;
	private List<Travel> viajesUsuario;
	private JLabel lblVolver;
	private JLabel lblCancelarViaje;
	private JLabel lblActividadesViaje;

	public SeeTrips(TravelController controlador, Ser ser, AccessController controladorAcceso,
			UserController controladorUsuario) {
		this.controladorViaje = controlador;
		this.ser = ser;
		this.controladorAcceso = controladorAcceso;
		this.controladorUsuario = controladorUsuario;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creamos un modelo para la lista
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setBackground(new Color(22, 15, 46));
		list.setForeground(new Color(255, 255, 255));
		list.setFont(new Font("Magneto", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(418, 53, 523, 514);
		contentPane.add(scrollPane);

		// Crear un JLabel para mostrar la información completa del viaje
		lblInfoViaje = new JLabel("");
		lblInfoViaje.setForeground(new Color(255, 255, 255));
		lblInfoViaje.setFont(new Font("Magneto", Font.PLAIN, 15));
		lblInfoViaje.setBounds(50, 117, 300, 205);
		contentPane.add(lblInfoViaje);

		// Crear un JLabel para mostrar las actividades del viaje seleccionado
		lblActividadesViaje = new JLabel("");
		lblActividadesViaje.setForeground(new Color(255, 255, 255));
		lblActividadesViaje.setFont(new Font("Magneto", Font.PLAIN, 15));
		lblActividadesViaje.setBounds(50, 264, 300, 179);
		contentPane.add(lblActividadesViaje);

		lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(AdminView.class.getResource("/images/VolverBlanco.png")));
		lblVolver.setBounds(0, 0, 266, 92);
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblVolver);

		lblCancelarViaje = new JLabel("");
		lblCancelarViaje.setIcon(new ImageIcon(SeeTrips.class.getResource("/images/BotonCancelarViaje.png")));
		lblCancelarViaje.setBounds(123, 465, 163, 151);
		lblCancelarViaje.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblCancelarViaje);
		lblCancelarViaje.setVisible(false);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(SeeTrips.class.getResource("/images/galaxy.jpg")));
		lblFondo.setBounds(0, 0, 1008, 641);
		contentPane.add(lblFondo);

		// Actualizamos la lista de viajes al crear la ventana
		updateTripsList();

		list.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					// Obtener el índice del elemento seleccionado
					int index = list.getSelectedIndex();
					if (index != -1) {
						// Mostrar el botón si se selecciona un viaje
						lblCancelarViaje.setVisible(true);
						// Obtener el viaje seleccionado
						Travel viajeSeleccionado = viajesUsuario.get(index);

						// Mostrar la información completa del viaje en el JLabel
						String infoViaje = "Origen: " + viajeSeleccionado.getOrigen() + "<br>" + "Destino: "
								+ viajeSeleccionado.getNom_destino().name() + "<br>" + "Fecha de viaje: "
								+ viajeSeleccionado.getFechaViaje();
						lblInfoViaje.setText("<html>" + infoViaje + "</html>");

						// Obtener y mostrar las actividades asociadas al viaje
						String actividadesViaje = "<html><body>";
						actividadesViaje += "<b>Actividades del viaje:</b><br>";
						for (String actividad : viajeSeleccionado.getActividades()) {
							actividadesViaje += "- " + actividad + "<br>";
						}
						actividadesViaje += "</body></html>";
						lblActividadesViaje.setText(actividadesViaje);

						lblInfoViaje.setVisible(true);
						lblActividadesViaje.setVisible(true);
					} else {
						lblCancelarViaje.setVisible(false); // Ocultar el botón si no se selecciona ningún viaje
					}
				}
			}
		});

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource() == lblVolver) {
					UserMenu volverMenu = new UserMenu(controladorAcceso, controladorUsuario, ser);
					volverMenu.setVisible(true);
					dispose();
				} else if (e.getSource() == lblCancelarViaje) {
					cancelSelectedTrip();
				}

			}
		};
		lblVolver.addMouseListener(mouseAdapter);
		lblCancelarViaje.addMouseListener(mouseAdapter);
	}

	private void updateTripsList() {
		// TODO Auto-generated method stub
		// Obtener los viajes del usuario
		viajesUsuario = TravelFactory.getManageTravel().seeTrip(ser.getNick());

		// Ordenar la lista de viajes por fecha de viaje (de más nueva a más antigua)
		Collections.sort(viajesUsuario, new Comparator<Travel>() {
			@Override
			public int compare(Travel viaje1, Travel viaje2) {
				return viaje2.getFechaViaje().compareTo(viaje1.getFechaViaje());
			}
		});

		// Limpiar el modelo de la lista
		listModel.clear();

		// Agregar los viajes al modelo de la lista
		for (Travel viaje : viajesUsuario) {
			listModel.addElement(
					viaje.getOrigen() + " - " + viaje.getNom_destino().name() + " / " + viaje.getFechaViaje());
		}
	}

	private void cancelSelectedTrip() {
		// Obtener el índice del elemento seleccionado en la lista
		int index = list.getSelectedIndex();
		if (index != -1) {
			// Obtener el viaje seleccionado
			Travel viajeSeleccionado = viajesUsuario.get(index);

			// Verificar si la fecha del viaje ya ha pasado
			if (viajeSeleccionado.getFechaViaje().isBefore(LocalDate.now())) {
				// Mostrar un mensaje de error si la fecha del viaje ha pasado
				MakeLessUgly.showMessageDialog("No se puede cancelar un viaje que ya ha pasado la fecha.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				// Cancelar el viaje
				boolean cancelado = TravelFactory.getManageTravel().cancelTrip(viajeSeleccionado.getCod_viaje());
				if (cancelado) {
					// Actualizar la lista de viajes después de la cancelación
					updateTripsList();
					lblInfoViaje.setVisible(false);
					lblActividadesViaje.setVisible(false);
				}
			}
		} else {
			// Mostrar un mensaje de error si no se ha seleccionado ningún viaje
			MakeLessUgly.showMessageDialog("Por favor, selecciona un viaje para cancelar.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}