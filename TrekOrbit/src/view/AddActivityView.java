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

	private AdminController c;
    private String planetName;
    private JButton addButton;
    private String selectedActivity;
    private ButtonGroup group;
    private AdminView parent;

    public AddActivityView(JFrame parent, AdminController controller, String planetName) {
        super(parent, "Add Activity", true);
        this.parent=(AdminView) parent;
        this.c = controller;
        this.planetName = planetName;
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select activity to add:");
        panel.add(label);

        ArrayList<Activity> availableActivities = c.getAvailableActivities(planetName);

        if (!availableActivities.isEmpty()) {
            group = new ButtonGroup();
            for (Activity activity : availableActivities) {
                JRadioButton radioButton = new JRadioButton(activity.getNombre());
                radioButton.setActionCommand(activity.getNombre()); // Establecer el nombre de la actividad como comando de acción
                group.add(radioButton);
                panel.add(radioButton);
            }

            addButton = new JButton("Add");
            addButton.addActionListener(this);
            panel.add(addButton);
        } else {
            panel.add(new JLabel("No more activities available."));
        }

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == addButton) {
            String selectedActivityName = group.getSelection().getActionCommand(); // Obtener el nombre de la actividad seleccionada
            if (selectedActivityName != null && !selectedActivityName.isEmpty()) {
                boolean added = c.addPlanetActivity(planetName, selectedActivityName);
                if (added) {
                    JOptionPane.showMessageDialog(this, "Activity added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    parent.updatePlanetActivities(selectedActivityName);
                } else {
                    JOptionPane.showMessageDialog(this, "Error adding activity", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an activity to add", "Error", JOptionPane.ERROR_MESSAGE);
            }
            dispose(); // Cerrar el diálogo después de añadir la actividad
        }
    }

}
