package launchVisual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class LaunchWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaunchWindow window = new LaunchWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LaunchWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 200);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 199, 434, 63);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Launch");
		btnNewButton.setBounds(335, 7, 89, 23);
		panel_1.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 32, 49, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Mass");
		lblNewLabel.setBounds(10, 11, 65, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Angle");
		lblNewLabel_1.setBounds(105, 11, 56, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblEnterLaunchVeloctity = new JLabel("Enter Launch Veloctity");
		lblEnterLaunchVeloctity.setBounds(203, 11, 114, 14);
		panel_1.add(lblEnterLaunchVeloctity);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(105, 32, 49, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(213, 32, 49, 20);
		panel_1.add(textField_2);
		
		JLabel lblMs = new JLabel("m/s");
		lblMs.setBounds(268, 35, 26, 14);
		panel_1.add(lblMs);
		
		JLabel lblRadians = new JLabel("radians");
		lblRadians.setBounds(157, 35, 46, 14);
		panel_1.add(lblRadians);
		
		JLabel lblKg = new JLabel("kg");
		lblKg.setBounds(69, 35, 26, 14);
		panel_1.add(lblKg);
	}
}
