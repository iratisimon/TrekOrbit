package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AccessController;
import controller.AdminController;
import model.Planet;
import model.Ser;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class AdminView extends JFrame implements ActionListener{
	private JButton changePlanet;
	private Planet p;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel availabilities;
	private JList<String> activityList;
    private DefaultListModel<String> activityListModel;
    private JButton addButton;
    private JButton removeButton;
    
    private AccessController controladorAcceso;
    private Ser admin;
	private AdminController c;


	/**
	 * Create the frame.
	 * @param c 
	 */
	public AdminView(AccessController controladorAcceso, AdminController c1,Ser administrador) {
		this.controladorAcceso = controladorAcceso;
		this.admin=administrador;
		this.c=c1;
		
		p=c.getPlanet(admin.getNick());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		changePlanet = new JButton("CAMBIA LA DISPONIBILIDAD");
		changePlanet.setFont(new Font("Verdana", Font.PLAIN, 15));
		changePlanet.setBounds(373, 604, 262, 29);
		contentPane.add(changePlanet);
		
		if(p.isDisponibilidad()) {
			availabilities = new JLabel("Planet disponible");
		}else {
			availabilities = new JLabel("Planeta NO disponible");
		}
		availabilities.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availabilities.setBounds(390, 568, 183, 13);
		contentPane.add(availabilities);
		
		changePlanet.addActionListener(this);
		
		// Mostrar lista de actividades
        JLabel activityLabel = new JLabel("Actividades:");
        activityLabel.setBounds(20, 50, 100, 20);
        contentPane.add(activityLabel);

        activityListModel = new DefaultListModel<>();
        activityList = new JList<>(activityListModel);
        JScrollPane activityScrollPane = new JScrollPane(activityList);
        activityScrollPane.setBounds(20, 80, 300, 400);
        contentPane.add(activityScrollPane);

        // Obtener y mostrar las actividades del planeta
        ArrayList<String> planetActivities = c.getPlanetActivities(p.getNom_planeta().name());
        for (String activity : planetActivities) {
            activityListModel.addElement(activity);
        }

        // Botones para añadir y quitar actividades
        addButton = new JButton("Añadir actividades");
        addButton.setBounds(20, 500, 120, 30);
        addButton.addActionListener(this);
        contentPane.add(addButton);

        removeButton = new JButton("Quitar actividades");
        removeButton.setBounds(160, 500, 150, 30);
        removeButton.addActionListener(this);
        contentPane.add(removeButton);
	}
	
	public void updatePlanetActivities(String planetName) {
		ArrayList<String> planetActivities = c.getPlanetActivities(planetName);
	    activityListModel.clear(); // Limpiar el modelo de lista antes de añadir las actividades actualizadas
	    for (String activity : planetActivities) {
	        activityListModel.addElement(activity);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addButton) {
			AddActivityView NewActivities = new AddActivityView(this, c, p.getNom_planeta().name());
		}

		
		if(e.getSource()==changePlanet) {
			p=c.changePlanetAvailability(p,admin.getNick());
			//p = c.getPlanet(admin.getNick());
				if(!p.isDisponibilidad()) {
					availabilities.setText("Planeta NO disponible");
				}else {
					availabilities.setText("Planeta disponible");
				}
			
		}
		if (e.getSource() == removeButton) {
            String selectedActivity = activityList.getSelectedValue();
            if (selectedActivity != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Seguro que quieres borrar la actividad seleccionada?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean removed = c.removePlanetActivity(p.getNom_planeta().name(), selectedActivity);
                    if (removed) {
                        JOptionPane.showMessageDialog(this, "Se ha borrado la actividad", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updatePlanetActivities(p.getNom_planeta().name());
                    } else {
                        JOptionPane.showMessageDialog(this, "Error borrando la actividad", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una actividad para removerla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
	}
}
