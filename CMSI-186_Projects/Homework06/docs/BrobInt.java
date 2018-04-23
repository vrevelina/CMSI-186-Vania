/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private int    sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private int[]  intValue   = null;      // int array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
    internalValue = value;
    if(validateDigits()) {
      if(internalValue.charAt(0)=='-') {
        sign = 1;
      }
      reversed = new StringBuffer(internalValue).reverse().toString();
      char lastChar = reversed.charAt(reversed.length()-1);
      int len = 0;
      if(lastChar == '+' || lastChar == '-') {
        len = reversed.length()-1;
      } else {
        len = reversed.length();
      }
      intValue = new int[len];
      for(int i=0; i<len; i++) {
        intValue[i] = Integer.parseInt("" + reversed.charAt(i));
      }
    } else {
      throw new IllegalArgumentException("bogus arguments");
    } 
   } 

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
    String valid = "-+0123456789";
    for(int i=0; i<internalValue.length(); i++) {
      if(!(valid.contains(String.valueOf(internalValue.charAt(i))))) {
        return false;
      }
    } return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      String reverser = new StringBuffer(internalValue).reverse().toString();
      return new BrobInt(reverser);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      String reverser = new StringBuffer(gint.internalValue).reverse().toString();
      return new BrobInt(reverser);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
    String results = "";
    int w = 0;
    int carry = 0;
    int newSign=0;
    int size = intValue.length + gint.intValue.length;
    int[] d = new int[size];
    if(sign == 0 && gint.sign == 0) {
      if(intValue.length>=gint.intValue.length) {
        for(int i=0; i<gint.intValue.length; i++) {
          d[i] = gint.intValue[i] + intValue[i] + carry;
          if(d[i]>9) {
            carry = 1;
            d[i] = d[i]%10;
          } else {
            carry = 0;
          }
        }
        for(int j=gint.intValue.length; j<intValue.length; j++) {
          d[j] = intValue[j] + carry;
          carry = 0;
        }
      } else if(intValue.length<gint.intValue.length) {
        for(int i=0; i<intValue.length; i++) {
          d[i] = intValue[i] + gint.intValue[i] + carry;
          if(d[i]>9) {
            carry = 1;
            d[i] = d[i]%10;
          } else {
            carry = 0;
          }
        }
        for(int j=intValue.length; j<gint.intValue.length; j++) {
          d[j] = gint.intValue[j] + carry;
          carry = 0;
        }
      }
      for(int p = d.length-1; p>=0; p--) {
        results += d[p];
      }
    } else if(sign == 1 && gint.sign == 1) {
      if(intValue.length>gint.intValue.length) {
        for(int i=0; i<gint.intValue.length; i++) {
          d[i] = gint.intValue[i] + intValue[i] + carry;
          if(d[i]>9) {
            carry = 1;
            d[i] = d[i]%10;
          } else {
            carry = 0;
          }
        }
        for(int j=gint.intValue.length; j<intValue.length; j++) {
          d[j] = intValue[j] + carry;
          carry = 0;
        }
      } else if(intValue.length<gint.intValue.length) {
        for(int i=0; i<intValue.length; i++) {
          d[i] = intValue[i] + gint.intValue[i] + carry;
          if(d[i]>9) {
            carry = 1;
            d[i] = d[i]%10;
          } else {
            carry = 0;
          }
        }
        for(int j=intValue.length; j<gint.intValue.length; j++) {
          d[j] = gint.intValue[j] + carry;
          carry = 0;
        }
      } else if(intValue.length==gint.intValue.length) {
        for(int i=0; i<intValue.length; i++) {
          d[i] = gint.intValue[i] + intValue[i] + carry;
          if(d[i]>9) {
            carry = 1;
            d[i] = d[i]%10;
          } else {
            carry = 0;
          }
          w=i;
        }
        d[w+1]=carry;
      }
      newSign = 1;
      for(int p = d.length-1; p>=0; p--) {
        results += d[p];
      }
    } else if(sign == 0 && gint.sign == 1) {
      gint.sign = 0;
      BrobInt res = subtract(gint);
      gint.sign = 1;
      return res;
    }
    int k=0;
    while(results.charAt(k)=='0') {
      k++;
    }
    results = results.substring(k,results.length());
    if(newSign==1) {
      results = "-" + results;
    }
    return new BrobInt(results);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
    String results = "";
    int w = 0;
    int carry = 0;
    int newSign=0;
    int size = intValue.length + gint.intValue.length;
    int[] d = new int[size];
    if(internalValue==gint.internalValue) {
      return ZERO;
    }
    if(sign==0 && gint.sign==0) {
      if(intValue.length>gint.intValue.length) {
        for(int i=0; i<gint.intValue.length; i++) {
          if(intValue[i]>=gint.intValue[i]) {
            d[i] = intValue[i] - gint.intValue[i];
          } else {
            d[i] = intValue[i] + 10 - gint.intValue[i];
            intValue[i+1] -= 1;
          }
        }
        for(int i=gint.intValue.length; i<intValue.length; i++) {
          d[i] = intValue[i];
        }
      } else if(intValue.length<gint.intValue.length) {
        newSign = 1;
        for(int i=0; i<intValue.length; i++) {
          if(gint.intValue[i]>=intValue[i]) {
            d[i] = gint.intValue[i] - intValue[i];
          } else {
            d[i] = gint.intValue[i] + 10 - intValue[i];
            gint.intValue[i+1] -= 1;
          }
        }
        for(int i=intValue.length; i<gint.intValue.length; i++) {
          d[i] = gint.intValue[i];
        }
      }
    } else if(sign==0 && gint.sign==1) {
      gint.sign=0;
      BrobInt reslt = add(gint);
      gint.sign=1;
      return reslt;
    } else if(sign==1 && gint.sign==0) {
      gint.sign=1;
      BrobInt reslt = add(gint);
      gint.sign=0;
      return reslt;
    } else if(sign==1 && gint.sign==1) {
      if(compareTo(gint)>0) {
        newSign = 1;
        for(int i = 0; i<gint.intValue.length; i++) {
          if(intValue[i]>=gint.intValue[i]) {
            d[i] = intValue[i] - gint.intValue[i];
          } else if(intValue[i]<gint.intValue[i]) {
            d[i] = intValue[i] + 10 - gint.intValue[i];
            intValue[i] -= 1;
          }
        }
        for(int i=gint.intValue.length; i<intValue.length; i++) {
          d[i] = intValue[i];
        }
      } else if(compareTo(gint)<=0) {
        for(int i = 0; i<intValue.length; i++) {
          if(gint.intValue[i]>=intValue[i]) {
            d[i] = gint.intValue[i] - intValue[i];
          } else if(gint.intValue[i]<intValue[i]) {
            d[i] = gint.intValue[i] + 10 - intValue[i];
            gint.intValue[i] -= 1;
          }
        }
        for(int i=intValue.length; i<gint.intValue.length; i++) {
          d[i] = gint.intValue[i];
        }
      }
    }
    for(int p = d.length-1; p>=0; p--) {
      results += d[p];
    }
    int k=0;
    while(results.charAt(k)=='0') {
      k++;
    }
    results = results.substring(k,results.length());
    if(newSign==1) {
      results = "-" + results;
    }
    return new BrobInt(results);
    //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
    String results = "";
    int carry = 0;
    int[] a = null;
    int[] b =null;
    if(sign==0 && gint.sign==1 || sign==1 && gint.sign==0) {
      results = "-";
    }
    if(internalValue.length()>gint.internalValue.length()) {
      a = new int[intValue.length];
      for(int i=0; i<a.length; i++) {
        a[i] = intValue[i];
      }
      b = new int[gint.intValue.length];
      for(int i=0; i<b.length; i++) {
        b[i] = gint.intValue[i];
      }
    } else {
      a = new int[gint.intValue.length];
      for(int i=0; i<a.length; i++) {
        a[i] = gint.intValue[i];
      }
      b = new int[intValue.length];
      for(int i=0; i<b.length; i++) {
        b[i] = intValue[i];
      }
    }
    int[] c = new int[intValue.length + gint.intValue.length + 1];
    for(int i=0; i<c.length; i++) {
      c[i] = 0;
    }
    for(int i=0; i<b.length; i++) {
      int k = i;
      for(int j=0; j<a.length; j++) {
        c[k] = (a[j] * b[i]) + carry + c[k];
        if(c[k]>9) {
          carry = (int)Math.floor(c[k]/10);
          c[k] = c[k]%10;
        } else {
          carry = 0;
        }
        k++;
      }
      if(carry != 0) {
        c[k] = carry;
      }
      carry = 0;
    }
    StringBuffer sb = new StringBuffer("");
    for(int p=c.length-1; p>=0; p--) {
      results += c[p];
    }
    int y=0;
    while(results.charAt(y)=='0') {
      y++;
    }
    results = results.substring(y,results.length());
    sb.append(results);
    String final_r = sb.toString();
    return new BrobInt(final_r);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
    if( internalValue.length() > gint.internalValue.length() ) {
      return 1;
    } else if( internalValue.length() < gint.internalValue.length() ) {
      return (-1);
    } else {
      for( int i = 0; i < internalValue.length(); i++ ) {
        Character a = Character.valueOf( internalValue.charAt(i) );
        Character b = Character.valueOf( gint.internalValue.charAt(i) );
        if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
          return 1;
        } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
          return (-1);
        }
      }
    }
    return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its ints
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      System.out.println( "    Test 014: Adding g11 and g12: " );
      BrobInt g7 = new BrobInt("10");
      BrobInt g11 = new BrobInt("10");
      BrobInt g12 = new BrobInt("20");
      System.out.println( "      expecting: 30 and got " + g11.add( g12 ) );
      System.out.println( "      expecting: 200\n" +
                          "        and got: " + g7.multiply( g12 ) );
      System.exit( 0 );
   }
}
