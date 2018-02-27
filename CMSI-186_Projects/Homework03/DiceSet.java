/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private static int count;
   private int sides;
   private static Die[] ds = null;
//   private int[] pips = null;    YOU DON'T NEED THIS; IT'S PART OF THE DIE CLASS AND DOESN'T BELONG HERE

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int ksides ) {
    // Die[] ds = new Die[count];      // THIS IS CAUSING A NULL POINTER EXCEPTION; YOU ARE DECLARING A LOCAL COPY!!
    ds = new Die[count];
    for(int i=0; i<count; i++) {
      ds[i] = new Die(ksides);
    }
    this.count = count;       // THESE TWO LINES WILL ENSURE THAT THE CLASS FIELDS ARE INITIALIZED PROPERLY
    this.sides = ksides;
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
    int total = 0;
    for(int i=0; i<count; i++) {
      total = total + ds[i].getValue();
    }
    return total;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public static void aroll() {
    for(int i=0; i<count; i++) {
      ds[i].roll();
    }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @throws IllegalArgumentException if the index is out of range
   */
   public static int rollIndividual( int dieIndex ) {
      return ds[dieIndex-1].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @throws IllegalArgumentException if the index is out of range
   */
   public static int getIndividual( int dieIndex ) {
      return ds[dieIndex-1].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
    String result = "[";
      for(int i=0; i<count; i++) {
        result = result + "," + ds[i].getValue();
      }
      result = result + "]";
      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();       // THIS WAS NEVER FINISHED SO I ADDED IT FOR YOU
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds1 ) {
      for(int i=0; i<count;i++) {
//        if(ds[i]!=ds1[i]) {  THIS IS WRONG; DS1 IS A DICE SET, NOT AN ARRAY.  SEE NEXT LINE
        if(ds[i]!=ds1.ds[i]) {
          return false;
//          break;  THIS IS AN UNREACHABLE STATEMENT; YOU ARE RETURNING IN THE LINE ABOVE IT
        }
      } return true;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      System.out.println("Welcome to DiceSet!");
      DiceSet ds = new DiceSet(3,10);
      System.out.println("string= " + ds.toString());
      ds.aroll();
      System.out.println("string= " + ds.toString());
      System.out.println("value= " + ds.getIndividual(1));

   }
}
