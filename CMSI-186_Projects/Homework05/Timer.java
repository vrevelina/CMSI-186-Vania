/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
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


public class Timer {
  /**
   * Class field definitions go here
   */
  private static double totalSeconds = 0;
  private static double timeSlice = 1;
  private static int numberOfBalls = 0;
  private static Ball[] bs = null;

  /** 
   *  Constructor goes here
   */
  public Timer(double seconds) {
  	timeSlice = seconds;
  }

  /**
   * Method to move all the balls by one timeSlice
   * @return double-precision value of the current time in seconds
   */
  public double tick() {
  	totalSeconds += timeSlice;
  	for (int i=0; i<=numberOfBalls; i++) {
  		bs[i].ballMove();
  	}
  	return totalSeconds;
  }

  /**
   * Method to fetch the current time in seconds
   * @return double-precision value of the total time that has passed in seconds
   */
  public double getTotalSeconds() {
  	return totalSeconds;
  }

  /**
   * Method to return a String representation of the time
   * @return String value of the time that has passed
   */
  public static String timerToString() {
    double hour = Math.floor(totalSeconds/3600);
    double m = totalSeconds % 3600;
    double minute = Math.floor(m/60);
    double second = m % 60;
    String timeString = " " + hour + ":" + minute + ":" + second + " ";
    return timeString;
  }
}