/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  @author       :  Vania Revelina
 *  Date written  :  2018-03-13
 *  Description   :  
 *
 *  Notes         :  None
 *  Warnings      :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-03-13  Vania R.      Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {
  /**
   * Class field definitions go here
   */
  private static final double BALL_WEIGHT_IN_POUND = 1.0;
  private static final double BALL_RADIUS_IN_INCHES = 4.45; 
  private static final double POLE_X_LOCATION = 221;
  private static final double POLE_Y_LOCATION = 347; 
  private static final double FIELD_X_SIZE = 500;
  private static final double FIELD_Y_SIZE = 500;
  private final double DEFAULT_TIME_SLICE = 1.0;
  private static double timeSlice = 0;
  private static int numberOfBalls = 0;
  private static Ball[] bs = null;

  /** 
   *  Constructor goes here
   */
  public SoccerSim( String args[] ) {
  	super();
  }

  /**
   *  Method to validate the x location of a ball
   *  @param  argValue  String from the main programs
   *  @return double-precision value of the argument
   *  @throws NumberFormatException
   *  @throws IllegalArgumentException
   */
  public static double validateXLocation( String argValue ) {
  	double xLoc = Double.parseDouble(argValue);
  	double absxloc = Math.abs(xLoc);
  	if(absxloc>FIELD_X_SIZE) {
  		System.out.println("Please enter a location that is inside the field");
  		System.exit(1);
  	}
  	return xLoc;
  }

  /**
   *  Method to validate the y location of a ball
   *  @param  argValue  String from the main programs
   *  @return double-precision value of the argument
   *  @throws NumberFormatException
   *  @throws IllegalArgumentException
   */
  public static double validateYLocation( String argValue ) {
  	double yLoc = Double.parseDouble(argValue);
  	double absyloc = Math.abs(yLoc);
  	if(absyloc>FIELD_Y_SIZE) {
  		System.out.println("Please enter a location that is inside the field");
  		System.exit(1);
  	}
  	return yLoc;
  }

  /** 
   * Method to validate the velocity of the ball in the x direction
   * @param  argValue  String from the main program input
   * @return double-precision value of the velocity of the ball in the x direction
   * @throws NumberFormat Exception
   * @throws IllegalArgumentException
   */
  public static double validateXMovement( String argValue ) throws NumberFormatException, IllegalArgumentException {
  	double returnValue = 0.0;
  	try {
  		returnValue = Double.parseDouble(argValue);
  	}
  	catch( NumberFormatException nfe ) {
  		throw new NumberFormatException("[convert value failed!]");
  	}
  	return returnValue;
  }

  /** 
   * Method to validate the velocity of the ball in the y direction
   * @param  argValue  String from the main program input
   * @return double-precision value of the velocity of the ball in the y direction
   * @throws NumberFormatException
   * @throws IllegalArgumentException
   */
  public static double validateYMovement( String argValue ) throws NumberFormatException, IllegalArgumentException {
  	double returnValue = 0.0;
  	try {
  		returnValue = Double.parseDouble(argValue);
  	}
  	catch( NumberFormatException nfe ) {
  		throw new NumberFormatException("[convert value failed!]");
  	}
  	return returnValue;
  }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
  public void handleInitialArguments( String args[] ) {
  	if( args.length < 8 ) {
  		System.out.println( "   Sorry you must enter at least the first 8 arguments for the SoccerSim\n" +
                            "   Usage: java SoccerSim <x-coordinate ball 1> <y-coordinate ball 1> " +
                            "   <x-speed ball 1> <y-speed ball 1> " +
                            "   <x-coordinate ball 2> <y-coordinate ball 2> " +
                            "   <x-speed ball 2> <y-speed ball 2> ... <timeSlice>\n" +
                            "   Please try again..........." );
        System.exit( 1 );
  	} else if( args.length%4 == 0 ) {
  		numberOfBalls = args.length/4;
  		Ball[] bs = new Ball[numberOfBalls];
  		for (int i=0; i<args.length; i+=4) {
  			double x_loc = validateXLocation(args[i]);
  			double y_loc = validateYLocation(args[i+1]);
  			double x_vel = validateXMovement(args[i+2]);
  			double y_vel = validateYMovement(args[i+3]);
  			bs[i] = new Ball(x_loc, y_loc, x_vel, y_vel);
  		}
  		timeSlice = DEFAULT_TIME_SLICE;
  	} else if( args.length%4 == 1 ) {
  		numberOfBalls = (args.length-1)/4;
  		Ball[] bs = new Ball[numberOfBalls];
  		for (int i=0; i<args.length; i+=4) {
  			double x_loc = validateXLocation(args[i]);
  			double y_loc = validateYLocation(args[i+1]);
  			double x_vel = validateXMovement(args[i+2]);
  			double y_vel = validateYMovement(args[i+3]);
  			bs[i] = new Ball(x_loc, y_loc, x_vel, y_vel);
  		}
  		timeSlice = Double.parseDouble(args[args.length-1]);
  	}
  }

  /**
   * Method to see if two balls collide
   * @return boolean value: true if any two balls collide
   */
  public static boolean ballCollision(Ball bs[]) {
  	double xDistance = 0.0;
  	double yDistance = 0.0;
  	double ballDistance = 0.0;
  	for (int i=0; i<numberOfBalls-1; i++) {
  		for (int k=i+1; k<numberOfBalls; k++) {
  			xDistance = bs[i].getXLocation() - bs[k].getXLocation();
  			yDistance = bs[i].getYLocation() - bs[k].getYLocation();
  			ballDistance = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
  			ballDistance = 12 * ballDistance;
  			if (ballDistance <= BALL_RADIUS_IN_INCHES) {
  				return true;
  			}
  		}
  	} return false;
  }

  /**
   * Method to see if any ball hits a pole
   * @return boolean value: true if a ball hits a pole
   */
  public static boolean poleCollision(Ball bs[]) {
  	double xDist = 0.0;
  	double yDist = 0.0;
  	double poleDistance = 0.0;
  	for (int i=0; i<numberOfBalls; i++) {
  		xDist = bs[i].getXLocation() - POLE_X_LOCATION;
  		yDist = bs[i].getYLocation() - POLE_Y_LOCATION;
  		poleDistance = Math.sqrt((xDist*xDist) + (yDist*yDist));
  		poleDistance = 12 * poleDistance;
  		if (poleDistance <= BALL_RADIUS_IN_INCHES) {
  			return true;
  		}
  	} return false;
  }

  /**
   * Method to tell if there is no collision
   * Mainly to help the main program to run
   * @return boolean value: true if there is no collision, false if there is
   */
  public static boolean noCollision() {
  	if (ballCollision(bs)) {
  		return false;
  	} else if (poleCollision(bs)) {
  		return false;
  	}
  }

  /**
   * Method to print the report showing the location and velocity of every ball
   * @return String, report of the location and velocity of every ball
   */
  public String toString() {
  	System.out.println("REPORT at" + Timer.timerToString());
  	String reportString = null;
  	for (int i=0; i<numberOfBalls; i++) {
  		reportString = "\n" + i + ":\t" + "position " + Ball.positionToString(bs[i]) + 
  			               "\t velocity " + Ball.velocityToString(bs[i]);
  	}
  	return reportString;
  }

  /**
   *  The main program starts here
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the x-coordinate of ball 1
   *                args[1] is the y-coordinate of ball 1
   *                args[2] is the speed of ball 1 in the x direction
   *                args[3] is the speed of ball 1 in the y direction
   *                args[4] is the x-coordinate of ball 2
   *                args[5] is the y-coordinate of ball 2
   *                args[6] is the speed of ball 2 in the x direction
   *                args[7] is the speed of ball 2 in the y direction
   *                ...and so on...
   *                args[n] is the timeSlice
   */
  public static void main( String args[] ) {
  	System.out.println( "\n\n       Hello world! Welcome to the SoccerSim program!!\n\n" );
  	SoccerSim ss = new SoccerSim(args);
  	ss.handleInitialArguments(args);
  	while(noCollision()) {
  		bs.tick();
  		bs.toString();
  	}
  	System.out.println("COLLISION FOUND AT TIME " + bs.timerToString());
  	System.exit(0);
  }
}