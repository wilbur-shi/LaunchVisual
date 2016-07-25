package launchVisual;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LaunchVisual {

	public static final int ROWS = 2, COLUMNS = 2, WIDTH = 800, HEIGHT = 800;
	private JFrame jf;

	public LaunchVisual() {
		jf = new JFrame("LaunchVisual");
		jf.setBounds(0, 0, WIDTH, HEIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		jf.setResizable(true);

		LaunchRenderer panelDrawing = new LaunchRenderer();
		panelDrawing.setBounds(0, 0, 1500, 700);
		jf.add(panelDrawing);

		JPanel panelEntry = new JPanel();
		panelEntry.setBounds(0, 700, 800, 100);
		jf.add(panelEntry);
		panelEntry.setLayout(null);
		panelEntry.setBorder(BorderFactory.createLineBorder(Color.black));

		// add JLabels
		JLabel lblMass = new JLabel("Enter mass");
		lblMass.setBounds(25, 30, 75, 20);
		JLabel lblVelocity = new JLabel("Enter velocity");
		lblVelocity.setBounds(155, 30, 100, 20);
		JLabel lblAngle = new JLabel("Enter angle");
		lblAngle.setBounds(295, 30, 75, 20);
		JLabel lblTimeStep = new JLabel("Enter timestep");
		lblTimeStep.setBounds(420, 30, 100, 20);

		// add text fields
		JTextField tfMass = new JTextField();
		tfMass.setBounds(95, 30, 50, 20);
		JTextField tfVelocity = new JTextField();
		tfVelocity.setBounds(235, 30, 50, 20);
		JTextField tfAngle = new JTextField();
		tfAngle.setBounds(365, 30, 50, 20);
		JTextField tfTimeStep = new JTextField();
		tfTimeStep.setBounds(510, 30, 50, 20);

		// add components
		panelEntry.add(tfMass);
		panelEntry.add(tfVelocity);
		panelEntry.add(tfAngle);
		panelEntry.add(tfTimeStep);
		panelEntry.add(lblMass);
		panelEntry.add(lblVelocity);
		panelEntry.add(lblAngle);
		panelEntry.add(lblTimeStep);

		// add buttons
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.setBounds(650, 30, 100, 20);
		panelEntry.add(btnLaunch);

		btnLaunch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{	
					double mass, velocity, angle, timestep;
					mass = Double.parseDouble(tfMass.getText());
					velocity = Double.parseDouble(tfVelocity.getText());
					angle = Double.parseDouble(tfAngle.getText());
					timestep = Double.parseDouble(tfTimeStep.getText());
					panelDrawing.initialize(mass, velocity, angle, timestep);
					
				}
				catch(Exception e1)
				{
					System.out.println("Error: need to enter values.");
				}
				//panelDrawing.initialize(10, 20, 45, .5);
			}
		});

		/*
		 * JFrame jf = new JFrame("LaunchVisual"); jf.setLayout(new
		 * GridLayout(ROWS, COLUMNS));
		 * 
		 * JButton btnPlay = new JButton("Click Here"); jf.add(btnPlay);
		 * 
		 * JTextField tfMass = new JTextField("Enter Mass"); jf.add(tfMass);
		 * 
		 * JPanel p = new JPanel(); jf.add(p); JRadioButton rb1 = new
		 * JRadioButton("English", true); JRadioButton rb2 = new
		 * JRadioButton("random");
		 * 
		 * ButtonGroup group = new ButtonGroup(); group.add(rb1);
		 * group.add(rb2);
		 * 
		 * p.setLayout(new GridLayout(3,1)); p.add(rb1); p.add(rb2);
		 * 
		 * String [] labels = {"Hello", "World", "!!!!"}; JList l = new
		 * JList(labels); jf.add(l);
		 * 
		 * jf.setSize(WIDTH, HEIGHT); jf.setVisible(true);
		 * jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * jf.setResizable(false);
		 */
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaunchVisual window = new LaunchVisual();
					window.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
