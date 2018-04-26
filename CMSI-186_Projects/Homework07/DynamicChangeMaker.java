/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Finding the optimal way of making change for a fixed amount
 * @author    :  Vania Revelina
 * Date       :  2018-04-19
 * Description:  This program solves the general problem of making change, it takes a sequence of
 *                coin denominations in no particular order, followed by an arbitrary amount of money 
 *                as input arguments, and outputs the optimal way of making change for that amount
 *                using those denominations.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-19  Vania R.      Initial Release
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class DynamicChangeMaker {

 public static int[] arrayF = null;
 public static int targ = 0;

  /* Constructor
   */
 public DynamicChangeMaker() {
  	super();
  }

  /**
   *  Method to validate all the inputs
   *  @return  boolean  true if all arguments are good
   *  @param   args   String args from main method
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static boolean handleInitialArguments ( String args[] ) {
    if( 0 == args.length ) {
  	  System.out.println( "    Sorry, you must enter 2 arguments \n" +
  		                "    Usage: java DynamicChangeMaker <denominations> [target] \n" +
   		                "    Please try again...........");
      System.exit( 1 );
    } else if( 1 == args.length ) {
      System.out.println( "    Sorry, you must enter 2 arguments \n" +
      	                  "    Usage: java DynamicChangeMaker <denominations> [target] \n" +
    		              "    Please try again...........");
    } else if( args.length > 2 ) {
      System.out.println( "    Sorry, you must enter 2 arguments \n" +
      	                  "    Usage: java DynamicChangeMaker <denominations> [target] \n" +
    		              "    Please try again...........");
    } else {
      String arrayD[] = args[0].split(",");
      arrayF = new int[arrayD.length];
      for(int i=0; i<arrayD.length; i++) {
        arrayF[i] = Integer.parseInt(arrayD[i]);
      }
      for(int i=0; i<arrayF.length; i++) {
        if ( arrayF[i]<0 ) {
      	  throw new IllegalArgumentException("\nSorry, denominations can't be negative!\n");
       	} else if ( arrayF[i] == 0 ) {
    	  throw new IllegalArgumentException("\nSorry, denominations can't be zero!\n");
    	}
      }
      targ = Integer.parseInt(args[1]);
      if ( targ<0 ) {
      	throw new IllegalArgumentException("\nSorry, target amount can't be negative!\n");
      } else if ( targ==0 ) {
      	throw new IllegalArgumentException("\nSorry, target amount can't be zero!\n");
      }
    }
    return true;
  }

 /** 
  *  Method to find the optimal way of making change
  *  @return Tuple    Tuple of the optimal way of making change
  *  @param  denom    Integer array of denominations 
  *  @param  target   Integer target amount
  *  @note   this method also validates the integer denominations array and target amount
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public static Tuple makeChangeWithDynamicProgramming( int[] denom, int target ) {

  // check if there are negative or zero denominations/target
  for (int i=0; i<denom.length; i++) {
    if (denom[i]==0) {
  	  System.out.println("Sorry! Your argument is 0!");
      return Tuple.IMPOSSIBLE;
  	} else if (denom[i]<0) {
  	  System.out.println("Sorry Your argument is negative!");
  	  return Tuple.IMPOSSIBLE;
  	}
  } 
  if(target<=0) {
  	System.out.println("Sorry! Your target is negative/0!");
  	return Tuple.IMPOSSIBLE;
  }

  // check if there are duplicated denominations
  for(int i=0; i<denom.length-1; i++) {
  	for(int j=i+1; j<denom.length; j++) {
  	  if ( denom[i] == denom[j] ) {
  	  	System.out.println("Sorry! Your denominations are duplicated!");
  	  	return Tuple.IMPOSSIBLE;
  	  }
  	}
  }


  Tuple[][] theTable = new Tuple[denom.length][target+1];
  for(int i=0; i<denom.length; i++) {
  	for(int j=0; j<target+1; j++) {
  	  if(j==0) {
  	    theTable[i][j] = new Tuple(denom.length);
  	  } else {
  	  	if(denom[i]>j) {
  	  	  theTable[i][j] = Tuple.IMPOSSIBLE;
  	  	  if(i!=0) {
  	  	    if(theTable[i][j]==Tuple.IMPOSSIBLE) {
  	  	  	  if(theTable[i-1][j]!=Tuple.IMPOSSIBLE) {
  	  	  	    theTable[i][j] = theTable[i-1][j];
  	  	  	  }
  	  	    }
  	  	    if(theTable[i-1][j]!=Tuple.IMPOSSIBLE) {
  	  	      if(theTable[i-1][j].total()<theTable[i][j].total()) {
  	  	  	    theTable[i][j] = theTable[i-1][j];
  	  	  	  }
  	  	    }
  	  	  }
  	  	} else {
  	  	  theTable[i][j] = new Tuple(denom.length);
  	  	  theTable[i][j].setElement(i,1);
  	  	  if((theTable[i][j-denom[i]])!=Tuple.IMPOSSIBLE) {
  	  	    theTable[i][j] = theTable[i][j].add(theTable[i][j-denom[i]]);
  	  	  } else {
  	  	    theTable[i][j] = Tuple.IMPOSSIBLE;
  	  	  }
  	  	  if(i!=0) {
  	  	  	if(theTable[i][j]==Tuple.IMPOSSIBLE) {
  	  	  	  if(theTable[i-1][j]!=Tuple.IMPOSSIBLE) {
  	  	  	  	theTable[i][j] = theTable[i-1][j];
  	  	  	  }
  	  	  	}
  	  	    if(theTable[i-1][j]!=Tuple.IMPOSSIBLE) {
  	  	      if(theTable[i-1][j].total()<theTable[i][j].total()) {
  	  	  	  	theTable[i][j] = theTable[i-1][j];
  	  	  	  }
  	  	  	}
  	  	  }
  	  	}
  	  }
  	}
  }
  return theTable[denom.length - 1][target];
 }

 /**
  *  The main program starts here
  *  @param  args String array of denominations and integer target amount 
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 public static void main( String[] args ) {
 	System.out.println( "\n  Hello world, welcome to the DynamicChangeMaker program!!\n" );
 	handleInitialArguments(args);
 	Tuple tup = makeChangeWithDynamicProgramming(arrayF,targ);
 	System.out.println("                   result: " + tup.toString() + "\n");
 }

}
