/**
 *  File name     :  HighRoll.java
 *  Purpose       :  Program to play the game which uses DiceSet.java
 *  Author        :  Vania Revelina
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

   public HighRoll( String args[] ) {
   	System.out.println("Welcome to HighRoll!");
    dice = String.parseInt( args[0] );
    sides = String.parseInt( args[1] );
    ds = new DiceSet(dice, sides);
    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
    System.out.println("type 1 to roll all the dice");
    System.out.println("type 2 to roll a single die");
    System.out.println("type 3 to calculate the score for this set");
    System.out.println("type 4 to save this score as high score");
    System.out.println("type 5 to display the high score");
    System.out.println("type q to quite the program");
    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
    while( true ) {
       System.out.print( ">>" );
       String inputLine = null;
       try {
          inputLine = input.readLine();
          if( 0 == inputLine.length() ) {
             System.out.println("enter some text!:");
           }
          if( 1 == inputLine.charAt(0)) {
          	ds.aroll();
          }
          if( 2 == inputLine.charAt(0)) {
          	ds.rollIndividual(1);
          }
          if( 3 == inputLine.charAt(0)) {
          	System.out.println("score= " + ds.sum());
          }
          if( 4 == inputLine.charAt(0)) {
          	int highscore = ds.sum();
          	System.out.println("high score saved");
          }
          if( 5 == inputLine.charAt(0)) {
          	System.out.println("high score= " + highscore);
          }
          if( 'q' == inputLine.charAt(0) ) {
             break;
          }         }
       catch( IOException ioe ) {
           System.out.println( "Caught IOException" );
       }
      }
   }

}
