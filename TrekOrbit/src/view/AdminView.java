package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import controller.AccessController;
import controller.AdminController;
import factory.AdminFactory;
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

public class AdminView extends JFrame {
	
	private Planet planeta;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> activityList;
	private DefaultListModel<String> activityListModel;
	private JLabel lblVolver;
	private JLabel botonAdd;
	private JLabel botonQuitar;
	private JLabel botonDisp;
	private String path = "/images/";
	private String png = ".png";
	private String imagePath;
	private AccessController controladorAcceso;
	private Ser admin;
	private AdminController controladorAdmin;
	

	public AdminView(AccessController controladorAcceso, AdminController controladorAdmin, Ser administrador) {
		MakeLessUgly.setDefaultCursor(this);
		this.controladorAcceso = controladorAcceso;
		this.admin = administrador;
		this.controladorAdmin = controladorAdmin;
		this.planeta = controladorAdmin.getPlanetFromAdmin(admin.getNick());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		if (planeta.isDisponibilidad()) {
			imagePath = path + planeta.getNom_planeta().name() + 400 + png;
		} else {
			imagePath = path + planeta.getNom_planeta().name() + 400 + "B" + png;
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
		activityList.setFont(new Font("OCR A Extended", Font.PLAIN, 17));
		activityList.setBorder(null);
		activityList.setBackground(new Color(22, 15, 46));
		JScrollPane activityScrollPane = new JScrollPane(activityList);
		activityScrollPane.setBounds(20, 187, 395, 310);
		contentPane.add(activityScrollPane);

		// Obtener y mostrar las actividades del planeta
		ArrayList<String> planetActivities = AdminFactory.getManageAdmin().getPlanetActivities(planeta.getNom_planeta().name());
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

		botonAdd = new JLabel("");
        botonAdd.setIcon(new ImageIcon(AdminView.class.getResource("/images/BotonActividad.png")));
        botonAdd.setBounds(40, 464, 150, 166);
        botonAdd.setCursor(MakeLessUgly.setAlienCursor(botonAdd));
        contentPane.add(botonAdd);

        botonQuitar = new JLabel("");
        botonQuitar.setIcon(new ImageIcon(AdminView.class.getResource("/images/BotonQuitarActividad.png")));
        botonQuitar.setBounds(243, 486, 150, 127);
        MakeLessUgly.setAlienCursor(botonQuitar);
        contentPane.add(botonQuitar);
		
		botonDisp = new JLabel("");
        botonDisp.setIcon(new ImageIcon(AdminView.class.getResource("/images/BotonDisp.png")));
        botonDisp.setBounds(554, 510, 356, 141);
        MakeLessUgly.setAlienCursor(botonDisp);
        contentPane.add(botonDisp);
        
        lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(AdminView.class.getResource("/images/CERRARSESION.png")));
		lblVolver.setBounds(10, 22, 166, 92);
        MakeLessUgly.setAlienCursor(lblVolver);
		contentPane.add(lblVolver);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBackground(new Color(255, 255, 255));
		lblFondo.setBounds(0, 8, 1010, 633);
		lblFondo.setIcon(new ImageIcon(AdminView.class.getResource("/images/galaxy.jpg")));
		contentPane.add(lblFondo);
		
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getSource() == botonAdd) {
	                AddActivityView NewActivities = new AddActivityView(AdminView.this, controladorAdmin, planeta.getNom_planeta().name());
	            } else if (e.getSource() == botonQuitar) {
	                String selectedActivity = activityList.getSelectedValue();
	                if (selectedActivity != null) {
	                    int confirm = JOptionPane.showConfirmDialog(AdminView.this, "Seguro que quieres borrar la actividad seleccionada?", "Confirm", JOptionPane.YES_NO_OPTION);
	                    if (confirm == JOptionPane.YES_OPTION) {
	                        boolean removed = AdminFactory.getManageAdmin().removePlanetActivity(planeta.getNom_planeta().name(), selectedActivity);
	                        if (removed) {
	                        	MakeLessUgly.showMessageDialog("Se ha borrado la actividad", "Success", JOptionPane.INFORMATION_MESSAGE);
	                            updatePlanetActivities(planeta.getNom_planeta().name());
	                        } else {
	                        	MakeLessUgly.showMessageDialog( "Error borrando la actividad", "Error", JOptionPane.ERROR_MESSAGE);
	                        }
	                    }
	                } else {
	                	MakeLessUgly.showMessageDialog("Selecciona una actividad para removerla", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } else if (e.getSource() == botonDisp) {
	            	// Lógica para cambiar la disponibilidad del planeta
	            	if (planeta.isDisponibilidad()) {
                        imagePath = path + planeta.getNom_planeta().name() + 400 + "B" + png; // Cambiar a la imagen de planeta no disponible
                    } else {
                        imagePath = path + planeta.getNom_planeta().name() + 400 + png; // Cambiar a la imagen de planeta disponible
                    }
                    lblPlaneta.setIcon(new ImageIcon(getClass().getResource(imagePath)));
                    
                    boolean cambio= AdminFactory.getManageAdmin().changePlanetAvailability(planeta.getNom_planeta().name());
                    if(cambio) {
                    	JOptionPane.showMessageDialog(AdminView.this, "Se ha cambiado la disponibilidad", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                    	JOptionPane.showMessageDialog(AdminView.this, "Error cambiando la disponibilidad", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    planeta = AdminFactory.getManageAdmin().getPlanetFromAdmin(admin.getNick());
                             
                } else if (e.getSource() == lblVolver) {
                	LogIn login = new LogIn(controladorAcceso);
            		login.setVisible(true);
            		dispose();
                }
	            
	        }
	    };
	    
	    botonAdd.addMouseListener(mouseAdapter);
	    botonQuitar.addMouseListener(mouseAdapter);
	    botonDisp.addMouseListener(mouseAdapter);
	    lblVolver.addMouseListener(mouseAdapter);
	}
	  
	public void updatePlanetActivities(String planetName) {
		ArrayList<String> planetActivities = AdminFactory.getManageAdmin().getPlanetActivities(planetName);
		activityListModel.clear(); // Limpiar el modelo de lista antes de añadir las actividades actualizadas
		for (String activity : planetActivities) {
			activityListModel.addElement(activity);
		}
	}
}