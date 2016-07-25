package launchVisual;

import java.util.*;
import java.awt.*;

/**
 * Class ProjectileMotion takes in certain values to calculate and print out the
 * numbers corresponding to a cannon shooting a ball in a parabolic path.
 * 
 * @author Wilbur Shi
 * @version 4/13/14
 */
public class ProjectileMotion {
	private double m, v, angle, timestep = 0;
	private final double a = -9.8; // acceleration of gravity in m/s
	private final double xStart = -300;
	private final double zStart = -300;
	Scanner in = new Scanner(System.in);

	/**
	 * Constructor prompts user for mass, initial velocity, angle, and timestep;
	 * it then executes the remaining calculations.
	 */

	public static void main(String[] args) {
		new ProjectileMotion();
	}

	public ProjectileMotion() {
		System.out.println("Welcome to the Projectile Motion simulator. Please enter the following:");

		System.out.print("Mass (kg): ");
		m = in.nextDouble();

		System.out.print("Initial velocity (m/s): ");
		v = in.nextDouble();

		System.out.print("Angle (degrees): ");
		angle = in.nextDouble();

		System.out.print("Time step (seconds): ");
		timestep = in.nextDouble();

		advance();
	} // end ProjectileMotion constructor

	/**
	 * Initialize values to calculate trajectory and path of cannonball. Uses a
	 * for loop that increments on timestep until the time is equal to the time
	 * the ball reaches the ground. In every loop, calculate the x coordinate
	 * and z coordinate, then call printValues method to print out the values in
	 * a table.
	 */
	public void advance() {
		ArrayList<Double> x = new ArrayList<Double>(); // initialize ArrayList
														// for the x positions,
														// start
		x.add(0.); // position 0
		ArrayList<Double> z = new ArrayList<Double>(); // initialize ArrayList
														// for the z positions,
														// start
		z.add(0.); // position 0

		/*
		 * I can do the same process with finding half timestep velocities and
		 * such for vx, but I kept it as constant in the formula so I could
		 * understand the changing vz's better
		 */
		ArrayList<Double> vx = new ArrayList<Double>(); // initialize ArrayList
														// for the vx's, and
		vx.add(v * Math.cos(Math.toRadians(angle))); // start at the beginning
														// vx calculated using
														// vectors

		ArrayList<Double> vzHalfStep = new ArrayList<Double>(); // initialize
																// ArrayList for
																// the vz's
																// every half
																// timestep,
		vzHalfStep.add(v * Math.sin(Math.toRadians(angle))); // start at the
																// beginning vz
																// calculated
																// using vectors

		ArrayList<Double> vzTimeStep = new ArrayList<Double>(); // initialize
																// ArrayList for
																// the vz's
																// every full
																// timestep,
		vzTimeStep.add(vzHalfStep.get(0)); // start at the beginning vz
											// calculated using vectors

		int indexHalf = 0; // initialize the index of the vzHalfStep ArrayList.
							// Since 1/2Dt is the same, adding 1 to ArrayList
							// index is same as if adding 1/2Dt every time.
		int index = 0; // initialize the index for the x, z, and vzTimeStep
						// ArrayLists. Since Dt is same, adding 1 to ArrayList
						// index is same as if adding Dt every time.
		int retrieveIndex = 1; // initialize the index number for which
								// vzHalfStep index to use. After z(1), the
								// index I have to use for the vz(t+1/2Dt)
								// increases by 2 every time.
		double t = 0; // initialize time

		System.out.printf("%s %7s %7s \n", "t", "x(t)", "z(t)"); // initialize
																	// table,
																	// print
																	// first
																	// values
		do // calculate values until z(t) <= 0
		{
			vzHalfStep.add(indexHalf + 1, vzHalfStep.get(indexHalf) + .5 * a * timestep); // vz(t+1/2Dt)
																							// =
																							// vz(t)
																							// +
																							// 1/2*a*Dt
			indexHalf++; // add 1/2Dt, or simply add 1 to the index since this
							// is an ArrayList
			if (vzHalfStep.size() > retrieveIndex) // after calculating enough
													// halfstep vz's, then
													// calculate the full
													// timestep values
			{
				vzTimeStep.add(index + 1, vzHalfStep.get(retrieveIndex) + .5 * a * timestep); // vz(t+Dt)
																								// =
																								// vz(t)
																								// +
																								// 1/2*a*Dt
				z.add(index + 1, z.get(index) + vzHalfStep.get(retrieveIndex) * timestep); // z(t+Dt)
																							// =
																							// z(t)
																							// +
																							// vz(t+1/2Dt)*Dt
				x.add(index + 1, x.get(index) + vx.get(0) * timestep); // x(t+Dt)
																		// =
																		// x(t)
																		// +
																		// vx(t+1/2Dt)*Dt
																		// ;
																		// however,
																		// I
																		// kept
																		// the
																		// vx
																		// constant
																		// so I
																		// could
																		// look
																		// at
																		// the
																		// changing
																		// vz's
																		// better
																		// and
																		// ignore
																		// this
																		// for
																		// now

				printValues(t, x.get(index), z.get(index)); // print values
				// draw(x.get(index), z.get(index));
				t += timestep; // the time to be printed out increases by
								// timestep
				index++; // increase index of vzTimeStep, z, and x
				retrieveIndex += 2; // increase by 2 the index used for
									// vz(t+1/2Dt) in the function z(t+Dt) =
									// z(t) + vz(t+1/2Dt)*Dt

			}
		} while (z.get(index) > 0);
		// draw(x.get(index), z.get(index));
		System.out.printf("%s \n%s %.2f \n%s %.2f \n", "Projectile hits the ground!", "Flying time:", t -= timestep,
				"Range:", x.get(index - 1));

		/*
		 * OLD CODE, DOES NOT PRODUCE CORRECT RESULTS ouble finalTime = 2*vz /
		 * -a; // solve for the final time using a formula I derived double x =
		 * 0; // initialize x coordinate starting at 0 double z = 0; //
		 * initialize z coordinate starting at 0 double t = 0; // initialize
		 * time starting at 0 double vx = v * Math.cos(Math.toRadians(angle));
		 * // solve for the vx using vectors/geometry double vz = v *
		 * Math.sin(Math.toRadians(angle)); // solve for the vz using
		 * vectors/geometry
		 * 
		 * System.out.printf("%s %7s %7s \n", "t", "x(t)", "z(t)"); //
		 * initialize table for (t = 0; t <= finalTime; t+= timestep) {
		 * printValues(t, x, z); // solve for x coordinate, vz, then use vz to
		 * solve for z coordinate x = ( x*t + vx*t*timestep +
		 * .5*vx*Math.pow(timestep, 2) ) / (t + timestep); vz = ( vz*(t +
		 * .5*timestep) + .5*a*(t + timestep)*(timestep) ) / (t + timestep); z =
		 * ( z*t + vz*t*timestep + .5*vz*Math.pow(timestep, 2) ) / (t +
		 * timestep); if (vz == 0) // theoretically, when the vertical velocity
		 * is 0, it has reached // the max height and so I set this max height
		 * to what is currently the z coordinate
		 * 
		 */

	} // end advance method

	/**
	 * 
	 */
	public void draw(double xLoc, double zLoc) {
		/*pen.setColor(Color.WHITE);
		pen.fillRect(1000, 1000);
		double xCoordinate = xStart + 10 * xLoc;
		double zCoordinate = zStart + 10 * zLoc;
		// pen.setColor(Color.black);
		pen.up();
		pen.move(xCoordinate, zCoordinate);
		pen.setColor(Color.black);
		pen.down();
		pen.drawCircle(50);*/
		/*
		 * pen.setColor(Color.white); pen.home(); pen.up();
		 * pen.drawRect(1000,1000); pen.down(); pen.fillRect(1000,1000);
		 */
	}

	/**
	 * @param t
	 *            time
	 * @param x
	 *            x coordinate
	 * @param z
	 *            z coordinate Prints out the three values in a formatted table.
	 */
	public void printValues(double t, double x, double z) {
		System.out.printf("%.2f %4.2f %6.2f \n", t, x, z);
	} // end printValues

} // end ProjectileMotion class
