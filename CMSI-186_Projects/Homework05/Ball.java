/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
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

public class Ball {
  /**
   *  Class field definintions go here
   */
  private double xloc;
  private double yloc;
  private double xvel;
  private double yvel;
  private double timeSlice;
  private static final double FIELD_X_SIZE = 500;
  private static final double FIELD_Y_SIZE = 500;

  /**
   *  Constructor goes here
   */
  public Ball(double xLoc, double yLoc, double xMove, double yMove) {
  	this.xloc = xLoc;
  	this.yloc = yLoc;
  	this.xvel = xMove;
  	this.yvel = yMove;
  }

  /**
   * get the x-coordinate of THIS die and return to the caller
   * @return double value of the x-coordinate of the ball
   */
  public double getXLocation() {
  	return this.xloc;
  }

  /**
   * get the y-coordinate of THIS die and return to the caller
   * @return double value of the y-coordinate of the ball
   */
  public double getYLocation() {
  	return this.yloc;
  }

  /**
   * get the velocity of THIS die in the x direction and return to the caller
   * @return double value of the velocity of the ball in the x direction
   */
  public double getXVelocity() {
  	return this.xvel;
  }

  /**
   * get the velocity of THIS die in the y direction and return to the caller
   * @return double value of the velocity of the ball in the y direction
   */
  public double getYVelocity() {
  	return this.yvel;
  }

  /**
   * Public method that returns a String representation 
   * of the location of THIS die in cartesian coordinates
   * @return String representation of the location of THIS die
   */
  public static String positionToString(Ball b1) {
  	String posString = "<" + b1.getXLocation() + "," + b1.getYLocation() + ">";
  	return posString;
  }

  /**
   * Public method that returns a String representation 
   * of the velocity of THIS die
   * @return String representation of the velocity of THIS die
   */
  public static String velocityToString(Ball b1) {
  	String velString = "<" + b1.getXVelocity() + "," + b1.getYVelocity() + ">";
  	return velString;
  }
  
  /**
   * Method to move THIS ball with the current velocity based on the timeSlice
   * then decreases the velocity of the ball by .1/second
   */
  public void ballMove() {
  	this.xloc = this.xloc + this.xvel;
  	this.yloc = this.yloc + this.yvel;
  	this.xvel = this.xvel - (0.01 * this.xvel * timeSlice);
  	this.yvel = this.yvel - (0.01 * this.yvel * timeSlice);
  }

}
