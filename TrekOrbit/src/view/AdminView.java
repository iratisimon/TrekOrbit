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

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;

public class AdminView extends JFrame {
	
	private Planet p;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel availabilities;
	private JList<String> activityList;
	private DefaultListModel<String> activityListModel;
	private JLabel lblVolver;
	private JLabel botonAñadir;
	private JLabel botonQuitar;
	private JLabel botonDisp;

	private String path = "/images/";
	private String png = ".png";
	private String imagePath;

	private AccessController controladorAcceso;
	private Ser admin;
	private AdminController c;
	

	public AdminView(AccessController controladorAcceso, AdminController c1, Ser administrador) {
		this.controladorAcceso = controladorAcceso;
		this.admin = administrador;
		this.c = c1;

		p = c.getPlanet(admin.getNick());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		if (p.isDisponibilidad()) {
			imagePath = path + p.getNom_planeta().name() + 400 + png;
		} else {
			imagePath = path + p.getNom_planeta().name() + 400 + "B" + png;
		}
		

		setContentPane(contentPane);
		contentPane.setLayout(null);


		// Mostrar lista de actividades
		JLabel activityLabel = new JLabel("Actividades:");
		activityLabel.setIcon(new ImageIcon(AdminView.class.getResource("/images/Actividades.png")));
		activityLabel.setBounds(40, 100, 375, 76);
		activityLabel.setForeground(new Color(255, 255, 255));
		activityLabel.setFont(new Font("Magneto", Font.PLAIN, 32));
		contentPane.add(activityLabel);

		activityListModel = new DefaultListModel<>();
		activityList = new JList<>(activityListModel);
		activityList.setForeground(new Color(255, 255, 255));
		activityList.setFont(new Font("Magneto", Font.PLAIN, 17));
		activityList.setBorder(null);
		activityList.setBackground(new Color(22, 15, 46));
		JScrollPane activityScrollPane = new JScrollPane(activityList);
		activityScrollPane.setBounds(20, 187, 395, 310);
		contentPane.add(activityScrollPane);

		// Obtener y mostrar las actividades del planeta
		ArrayList<String> planetActivities = c.getPlanetActivities(p.getNom_planeta().name());
		for (String activity : planetActivities) {
			activityListModel.addElement(activity);
		}
		
		JLabel Treki = new JLabel("");
		Treki.setIcon(new ImageIcon(AdminView.class.getResource("/images/TrekyDisp.png")));
		Treki.setBounds(554, 61, 356, 241);
		contentPane.add(Treki);

		JLabel lblPlaneta = new JLabel("");
		lblPlaneta.setBounds(564, 256, 308, 268);
		lblPlaneta.setIcon(new ImageIcon(getClass().getResource(imagePath)));
		contentPane.add(lblPlaneta);

		// Botones para añadir y quitar actividades

		botonAñadir = new JLabel("");
        botonAñadir.setIcon(new ImageIcon(AdminView.class.getResource("/images/BotonActividad.png")));
        botonAñadir.setBounds(40, 464, 150, 166);
        botonAñadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Agregamos un cursor de mano
        contentPane.add(botonAñadir);

        botonQuitar = new JLabel("");
        botonQuitar.setIcon(new ImageIcon(AdminView.class.getResource("/images/BotonQuitarActividad.png")));
        botonQuitar.setBounds(243, 486, 150, 127);
        botonQuitar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cambiamos el cursor a mano
        contentPane.add(botonQuitar);
		
		botonDisp = new JLabel("");
        botonDisp.setIcon(new ImageIcon(AdminView.class.getResource("/images/BotonDisp.png")));
        botonDisp.setBounds(554, 510, 356, 141);
        botonDisp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Agregamos un cursor de mano
        contentPane.add(botonDisp);
        
        lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(AdminView.class.getResource("/images/VolverBlanco.png")));
		lblVolver.setBounds(0, 8, 266, 92);
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblVolver);
		
		JLabel lblFondo = new JLabel("New label");
		lblFondo.setBackground(new Color(255, 255, 255));
		lblFondo.setBounds(0, 8, 1010, 633);
		lblFondo.setIcon(new ImageIcon(AdminView.class.getResource("/images/galaxy.jpg")));
		contentPane.add(lblFondo);
		
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getSource() == botonAñadir) {
	                AddActivityView NewActivities = new AddActivityView(AdminView.this, c, p.getNom_planeta().name());
	            } else if (e.getSource() == botonQuitar) {
	                String selectedActivity = activityList.getSelectedValue();
	                if (selectedActivity != null) {
	                    int confirm = JOptionPane.showConfirmDialog(AdminView.this, "Seguro que quieres borrar la actividad seleccionada?", "Confirm", JOptionPane.YES_NO_OPTION);
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        boolean removed = c.removePlanetActivity(p.getNom_planeta().name(), selectedActivity);
	                        if (removed) {
	                            JOptionPane.showMessageDialog(AdminView.this, "Se ha borrado la actividad", "Success", JOptionPane.INFORMATION_MESSAGE);
	                            updatePlanetActivities(p.getNom_planeta().name());
	                        } else {
	                            JOptionPane.showMessageDialog(AdminView.this, "Error borrando la actividad", "Error", JOptionPane.ERROR_MESSAGE);
	                        }
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(AdminView.this, "Selecciona una actividad para removerla", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else if (e.getSource() == botonDisp) {
	            	
	            	if (p.isDisponibilidad()) {
                        imagePath = path + p.getNom_planeta().name() + 400 + "B" + png; // Cambiar a la imagen de planeta no disponible
                    } else {
                        imagePath = path + p.getNom_planeta().name() + 400 + png; // Cambiar a la imagen de planeta disponible
                    }
                    lblPlaneta.setIcon(new ImageIcon(getClass().getResource(imagePath)));	            	
                    // Lógica para cambiar la disponibilidad del planeta
                    p = c.changePlanetAvailability(p, admin.getNick());
                    
                } else if (e.getSource() == lblVolver) {
                	LogIn login = new LogIn(controladorAcceso);
            		login.setVisible(true);
            		dispose();
                }
	            
	        }
	    };
	    
	    botonAñadir.addMouseListener(mouseAdapter);
	    botonQuitar.addMouseListener(mouseAdapter);
	    botonDisp.addMouseListener(mouseAdapter);
	    lblVolver.addMouseListener(mouseAdapter);
	}
	  
	public void updatePlanetActivities(String planetName) {
		ArrayList<String> planetActivities = c.getPlanetActivities(planetName);
		activityListModel.clear(); // Limpiar el modelo de lista antes de añadir las actividades actualizadas
		for (String activity : planetActivities) {
			activityListModel.addElement(activity);
		}
	}
}
