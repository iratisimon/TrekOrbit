package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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

public class AdminView extends JFrame implements ActionListener{
	
	
	private Ser admin;
	private AdminController c;
	private JButton changePlanet;
	private Planet p;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel availabilities;
	private JList<String> activityList;
    private DefaultListModel<String> activityListModel;
    private JButton addButton;
    private JButton removeButton;


	/**
	 * Create the frame.
	 * @param c 
	 */
	public AdminView(AdminController c1,Ser administrador) {
		this.admin=administrador;
		this.c=c1;
		p=c.getPlanet(admin.getNick());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		changePlanet = new JButton("CHANGE AVAILABILITY");
		changePlanet.setFont(new Font("Verdana", Font.PLAIN, 15));
		changePlanet.setBounds(373, 604, 211, 29);
		contentPane.add(changePlanet);
		
		if(p.isDisponibilidad()) {
			availabilities = new JLabel("Planet available");
		}else {
			availabilities = new JLabel("Planet NOT available");
		}
		availabilities.setFont(new Font("Tahoma", Font.PLAIN, 18));
		availabilities.setBounds(390, 568, 183, 13);
		contentPane.add(availabilities);
		
		changePlanet.addActionListener(this);
		
		// Mostrar lista de actividades
        JLabel activityLabel = new JLabel("Activities:");
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
        addButton = new JButton("Add Activity");
        addButton.setBounds(20, 500, 120, 30);
        addButton.addActionListener(this);
        contentPane.add(addButton);

        removeButton = new JButton("Remove Activity");
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
			c.changePlanetAvailability(p);
			if(p.isDisponibilidad()) {
				availabilities.setText("Planet NOT available");
			}else {
				availabilities.setText("Planet available");
			}
			
		}
	}
}
