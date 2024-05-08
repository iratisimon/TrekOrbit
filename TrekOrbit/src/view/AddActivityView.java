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
import javax.swing.border.EmptyBorder;

import controller.AdminController;
import model.Activity;

public class AddActivityView extends JDialog implements ActionListener{

	private AdminController controladorAdmin;
    private String planetName;
    private JButton addButton;
    private String selectedActivity;
    private ButtonGroup group;
    private AdminView parent;

    public AddActivityView(JFrame parent, AdminController controller, String planetName) {
        super(parent, "Add Activity", true);
        this.parent=(AdminView) parent;
        this.controladorAdmin = controller;
        this.planetName = planetName;
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Selecciona la actividad que quieras añadir:");
        panel.add(label);

        ArrayList<Activity> availableActivities = controladorAdmin.getAvailableActivities(planetName);

        if (!availableActivities.isEmpty()) {
            group = new ButtonGroup();
            for (Activity activity : availableActivities) {
                JRadioButton radioButton = new JRadioButton(activity.getNombre());
                radioButton.setActionCommand(activity.getNombre()); // Establecer el nombre de la actividad como comando de acción
                group.add(radioButton);
                panel.add(radioButton);
            }

            addButton = new JButton("Añadir");
            addButton.addActionListener(this);
            panel.add(addButton);
        } else {
            panel.add(new JLabel("No hay más actividades disponibles."));
        }

        add(panel);
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
                    JOptionPane.showMessageDialog(this, "La actividad ha sido añadida", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parent.updatePlanetActivities(planetName);
                } else {
                    JOptionPane.showMessageDialog(this, "Error añadiendo la actividad", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una actividad para añadirla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose(); // Cerrar el diálogo después de añadir la actividad
        }
    }

}
