package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AccessController;
import controller.AdminController;
import model.Planet;
import model.Planeta;
import model.Ser;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;

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
  
  private String path = "/images/";
	private String png = ".png";
    
  private AccessController controladorAcceso;
  private Ser admin;
	private AdminController c;
	private JLabel lblNewLabel;


	public AdminView(AccessController controladorAcceso, AdminController c1,Ser administrador) {
		this.controladorAcceso = controladorAcceso;
		this.admin=administrador;
		this.c=c1;
		
		p=c.getPlanet(admin.getNick());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		String imagePath = path + p.getNom_planeta().name() + 400 + png;
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		changePlanet = new JButton("Cambia la disponibilidad");
		changePlanet.setBounds(373, 604, 262, 29);
		changePlanet.setFont(new Font("Magneto", Font.PLAIN, 17));
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
        activityLabel.setBounds(70, 50, 300, 40);
        activityLabel.setForeground(new Color(255, 255, 255));
        activityLabel.setFont(new Font("Magneto", Font.PLAIN, 30));
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
        
        JLabel lblPlaneta = new JLabel("");
		lblPlaneta.setBounds(660, 67, 300, 270);
		lblPlaneta.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		contentPane.add(lblPlaneta);
        

        // Botones para añadir y quitar actividades
        addButton = new JButton("Añadir actividades");
        addButton.setBounds(20, 500, 120, 30);
        addButton.setFont(new Font("Magneto", Font.PLAIN, 12));
        addButton.addActionListener(this);
        contentPane.add(addButton);

        removeButton = new JButton("Quitar actividades");
        removeButton.setBounds(160, 500, 150, 30);
        removeButton.setForeground(new Color(0, 0, 0));
        removeButton.setFont(new Font("Magneto", Font.PLAIN, 12));
        removeButton.addActionListener(this);
        contentPane.add(removeButton);
        
        JLabel lblFondo = new JLabel("New label");
        lblFondo.setBounds(0, 0, 1010, 633);
        lblFondo.setIcon(new ImageIcon(AdminView.class.getResource("/images/galaxy.jpg")));
        contentPane.add(lblFondo);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(667, 208, 45, 13);
        contentPane.add(lblNewLabel);
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
