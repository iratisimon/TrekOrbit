package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AdminController;
import model.Planet;
import model.Ser;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.JLabel;

public class AdminView extends JFrame implements ActionListener{
	
	
	private Ser admin;
	private AdminController c;
	private JButton changePlanet;
	private Planet p;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel availabilities;


	/**
	 * Create the frame.
	 * @param c 
	 */
	public AdminView(AdminController c1) {
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
		availabilities.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availabilities.setBounds(432, 568, 81, 13);
		contentPane.add(availabilities);
		
		changePlanet.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
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
