/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  TupleTestHarness.java
 * Purpose    :  Test harness for the Tuple class
 * @author    :  Professor Don Murphy
 * @author    :  B.J. Johnson totally ripped off from the original; thanks Professor Murphy!
 * Date       :  2017-04-19
 * Description:  Duh.... see the Purpose above
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-19  B.J. Johnson  Initial writing and release; stolen blatently from Professor Murphy
 *                                    with his permission; added this comment block and some javadocs
 *  1.1.0  2017-04-28  B.J. Johnson  Added complete JavaDocs; added "throws" clauses to the methods that
 *                                    use them; added explanatory notes to comments; added "testCount"
 *                                    field and "makeTwoDigits" method to implement automated test count
 *                                    during test runs; added tests for the "isImpossible" method and the
 *                                    "hashCode" method
 *  1.2.0  2018-04-16  B.J. Johnson  Added a few more tests to verify six- and ten-tuples
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class TupleTestHarness {

   private static int attempts  = 0;
   private static int successes = 0;
   private static int testCount = 0;

  /**
   *  the main method which calls all the test methods
   */
   public static void main(String[] args) {

      test_toString();
      test_setElement();
      test_add();
      test_equals();
      test_getElement();
      test_length();
      test_total();
      test_isImpossible();
      test_hashCode();

      System.out.println( "\n\nResults: " + successes + "/" + attempts + " tests passed.");
   }

  /**
   *  method to display success or failure as a result of a test
   *  @param  value  boolean success/failure
   *  Note that this method keeps track of the count of tests run
   *    as well as the number of successes
   */
   private static void displaySuccessIfTrue( boolean value) {
      attempts++;
      successes += value ? 1 : 0;

      System.out.println(value ? "success" : "failure");
   }

  /**
   *  silly little method to add zeros to the front of a number string
   *    to ensure it fills two places for test output alignment
   *  @return two-character string that is a two-place number from 00 to 99
   *  note: this method also increments the testCount private field
   */
   private static String makeTwoDigits() {
      testCount++;
      if( testCount < 10 ) {
         return new String( "0" + testCount );
      } else {
         return new Integer( testCount ).toString();
      }
   }

  /**
   *  method to display a failure, probably for things that are
   *   supposed to fail on purpose [that's a guess]
   */
   private static void displayFailure() {
      displaySuccessIfTrue( false );
   }

  /**
   *  method to test the "toString()" method a buncha times
   */
   public static void test_toString() {
      try {
         System.out.println( "Test " + makeTwoDigits() + "    : testing Tuple.IMPOSSIBLE.toString():" );
         System.out.print  ( "  Expecting: 'Impossible tuple': " );
         displaySuccessIfTrue( "Impossible tuple".equals(Tuple.IMPOSSIBLE.toString() ));
      } catch( Exception e ) {
         e.printStackTrace();
         displayFailure();
      }

      try {
         System.out.println( "Test " + makeTwoDigits() + "    : testing if Tuple.IMPOSSIBLE returns isImpossible():" );
         System.out.print  ( "  Expecting: 'true': " );
         displaySuccessIfTrue( Tuple.IMPOSSIBLE.isImpossible() );
      } catch( Exception e ) {
         e.printStackTrace();
         displayFailure();
      }

     // Only one impossible.
      Tuple fakeImpossible = new Tuple(new int[0]);
      try {
         System.out.println( "Test " + makeTwoDigits() + "    : testing if '<>' [empty Tuple] equals fakeImpossible.toString():" );
         System.out.print  ( "  Expecting: 'true': " );
         displaySuccessIfTrue( "<>".equals(fakeImpossible.toString() ));
      } catch( Exception e ) {
         e.printStackTrace();
         displayFailure();
      }

      try {
         System.out.println( "Test " + makeTwoDigits() + "    : testing if fakeImpossible equals Tuple.IMPOSSIBLE:" );
         System.out.print  ( "  Expecting: 'true': " );
         displaySuccessIfTrue( fakeImpossible.equals(Tuple.IMPOSSIBLE));
      } catch( Exception e ) {
         e.printStackTrace();
         displayFailure();
      }

      try {
         System.out.println( "Test " + makeTwoDigits() + "    : testing if fakeImpossible returns isImpossible:" );
         System.out.print  ( "  Expecting: 'true': " );
         displaySuccessIfTrue( !fakeImpossible.isImpossible() );
      } catch( Exception e ) {
         e.printStackTrace();
         displayFailure();
      }