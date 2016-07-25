package launchVisual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LaunchRenderer extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int R_SCALE = 2, COORD_SCALE_Z = 20, COORD_SCALE_X = 10, START_X = 50, START_Z = 700;
	private static final double GRAVITY = -9.8;
	private double vx, angle, timestep, timeCount;
	private int radius, index, indexHalfStep, retrieveIndex;
	//private ArrayList<Integer> 
	private ArrayList<Double> vzTimeStep, vzHalfStep, xCoord, zCoord;;
	private Timer timer;
	private boolean initialized = false, reachedGround;

	public LaunchRenderer() {

		setLayout(null);
		JLabel lblTest = new JLabel("Welcome to Launch Visual.");
		lblTest.setBounds(500, 0, 200, 30);
		add(lblTest);
		setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public void initialize(double m, double v, double a, double ts) {
		if (initialized)
		{
			timer.stop();
		}
		initialized = true;
		index = 0; // initialize the index for the x, z, and vzTimeStep
		// ArrayLists. Since Dt is same, adding 1 to ArrayList
		// index is same as if adding Dt every time.
		indexHalfStep = 0;// initialize the index of the vzHalfStep ArrayList.
		// Since 1/2Dt is the same, adding 1 to ArrayList
		// index is same as if adding 1/2Dt every time.
		retrieveIndex = 1; // initialize the index number for which
		// vzHalfStep index to use. After z(1), the
		// index I have to use for the vz(t+1/2Dt)
		// increases by 2 every time.
		
		timestep = ts;
		timeCount = 0;
		
		angle = a;
		radius = (int) m * R_SCALE;
		vx = v * Math.cos(Math.toRadians(a)); // solve for the velocityX using vectors/geometry; it is constant
        vzHalfStep = new ArrayList<Double>(); // initialize ArrayList for the vz's every half timestep,
        vzHalfStep.add( v * Math.sin(Math.toRadians(angle)) );  // start at the beginning vz calculated using vectors

        vzTimeStep = new ArrayList<Double>(); // initialize ArrayList for the vz's every full timestep,
        vzTimeStep.add( vzHalfStep.get(0) );                    // start at the beginning vz calculated using vectors
        
        xCoord = new ArrayList<Double>(); // initialize ArrayList for the x positions, start 
        xCoord.add(0.);                                     // position 0 
        zCoord = new ArrayList<Double>(); // initialize ArrayList for the z positions, start
        zCoord.add(0.);                                     // position 0
		int speed = (int) (timestep * 1000);
		timer = new Timer(speed, this);
		timer.start();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (initialized) {
			doDrawing(g);
		}
	}

	public void doDrawing(Graphics g) {
		int xScaled = (int)(START_X + (COORD_SCALE_X * xCoord.get(index)) - radius*2);
		int zScaled = (int)(START_Z - (COORD_SCALE_Z * zCoord.get(index)) - radius*2);
		if (zCoord.get(index) < 0) {
			timer.stop();
			initialized = false;
			g.drawOval(xScaled,  START_Z-radius*2,  radius*2,  radius*2);
		}
		else
		{
			g.drawOval(xScaled, zScaled, radius * 2, radius * 2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		vzHalfStep.add(indexHalfStep + 1, vzHalfStep.get(indexHalfStep) + .5 * GRAVITY * timestep); // vz(t+1/2Dt)= vz(t) + 1/2*a*Dt
		indexHalfStep++; // add 1/2Dt, or simply add 1 to the index since this is an ArrayList
		if (vzHalfStep.size() > retrieveIndex) // after calculating enough halfstep vz's, then calculate the full timestep values
		{
			vzTimeStep.add(index + 1, vzHalfStep.get(retrieveIndex) + .5 * GRAVITY * timestep); // vz(t+Dt) = vz(t) + 1/2*a*Dt
			zCoord.add(index + 1, (zCoord.get(index) + vzHalfStep.get(retrieveIndex) * timestep)); // z(t+Dt) = z(t) + vz(t+1/2Dt)*Dt
			xCoord.add(index + 1, (xCoord.get(index) + vx * timestep)); /* x(t+Dt) = x(t) + vx(t+1/2Dt)*Dt ; however, I kept the vx constant so I 
			 														could look at the changing vz's better and ignore this for now	*/																
			repaint();
			
			printValues(timeCount, xCoord.get(index), zCoord.get(index)); // print values
			
			timeCount += timestep; // the time to be printed out increases by timestep
			index++; // increase index of vzTimeStep, z, and x
			retrieveIndex += 2; // increase by 2 the index used for vz(t+1/2Dt) in the function z(t+Dt) = z(t) + vz(t+1/2Dt)*Dt
		}


	
		/*xCoord = (int) (COORD_SCALE
				* ((xCoord * timeCount + velocityX * timeCount * timestep + .5 * velocityX * Math.pow(timestep, 2))
						/ (timeCount + timestep)));
		velocityZ = (velocityZ * (timeCount + .5 * timestep) + .5 * GRAVITY * (timeCount + timestep) * (timestep))
				/ (timeCount + timestep);
		zCoord = (int) (COORD_SCALE
				* ((zCoord * timeCount + velocityZ * timeCount * timestep + .5 * velocityZ * Math.pow(timestep, 2))
						/ (timeCount + timestep))); 
		timeCount += timestep;
						 * */

	

	}
	
	public void printValues(double t, double x, double z) {
		System.out.printf("%.2f %4.2f %6.2f \n", t, x, z);
	} // end printValues

}
