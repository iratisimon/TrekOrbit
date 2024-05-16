package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import controller.AdminController;
import model.Activity;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

public class AddActivityView extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminController controladorAdmin;
    private String planetName;
    private JButton addButton;
    private ButtonGroup group;
    private AdminView parent;

    public AddActivityView(JFrame parent, AdminController controller, String planetName) {
        super(parent, "Add Activity", true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(AddActivityView.class.getResource("/images/logotipo_trekorbit.png")));
        this.parent=(AdminView) parent;
        this.controladorAdmin = controller;
        this.planetName = planetName;
        initComponents();
    }

    private void initComponents() {
    	
    	JPanel mainPanel = new JPanel();
    	mainPanel.setBackground(new Color(22, 15, 46));
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	MakeLessUgly.setDefaultCursor(this);
    	    	
        JLabel label = new JLabel("Selecciona la actividad que quieras añadir:");
        label.setAlignmentY(1.0f);
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("OCR A Extended", Font.PLAIN, 16));
        mainPanel.add(label);

        ArrayList<Activity> availableActivities = controladorAdmin.getAvailableActivities(planetName);

        if (!availableActivities.isEmpty()) {
            group = new ButtonGroup();
            for (Activity activity : availableActivities) {
                JRadioButton radioButton = new JRadioButton(activity.getNombre());
                radioButton.setActionCommand(activity.getNombre()); // Establecer el nombre de la actividad como comando de acción
                radioButton.setBackground(new Color(22, 15, 46));
                radioButton.setForeground(new Color(255, 255, 255));
                group.add(radioButton);
                mainPanel.add(radioButton);
            }

            addButton = new JButton("Añadir");
            addButton.setFont(new Font("OCR A Extended", Font.PLAIN, 16));
            addButton.setBackground(new Color(255, 204, 255));
            addButton.setForeground(new Color(0, 0, 128));
        	MakeLessUgly.setAlienCursor(addButton);
            addButton.addActionListener(this);
            mainPanel.add(addButton);
        } else {
        	mainPanel.add(new JLabel("No hay más actividades disponibles."));
        }

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == addButton) {
            String activity = group.getSelection().getActionCommand(); // Obtener el nombre de la actividad seleccionada
            if (activity != null && !activity.isEmpty()) {
                boolean added = controladorAdmin.addPlanetActivity(planetName, activity);
                if (added) {
                	MakeLessUgly.showMessageDialog( "La actividad ha sido añadida", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parent.updatePlanetActivities(planetName);
                } else {
                	MakeLessUgly.showMessageDialog( "Error añadiendo la actividad", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
            	MakeLessUgly.showMessageDialog( "Selecciona una actividad para añadirla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose(); // Cerrar el diálogo después de añadir la actividad
        }
    }

}
