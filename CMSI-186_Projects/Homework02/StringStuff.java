/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-19
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

   public StringStuff() {
      System.out.println("Constructor called...");
   }

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {
      String lowers = new String(s.toLowerCase());
      boolean isTrue = false;
      for(int i=0; i<=lowers.length(); i++) {
         char k = lowers.charAt(i);
         if(k=='a' || k=='e' || k=='i' || k=='o' || k=='u' || k=='y') {
            isTrue = true;
            break;
         }
      }
      return isTrue;
   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {
      String palindr = new String(s.toLowerCase());
      boolean palinTrue = false;
      int lengt = palindr.length();
      if(lengt == 1) {
         return true;
      } else if(lengt%2==0) {
         for(int i=0; i<=(lengt/2)-1; i++) {
            if(palindr.charAt(i) != palindr.charAt(lengt-i-1)) {
               palinTrue = false;
               break;
            } else {
               palinTrue = true;
            }
         }
         return palinTrue;
      } else {
         for(int i=0; i<=((lengt-1)/2)-1; i++) {
            if(palindr.charAt(i) != palindr.charAt(lengt-i-1)) {
               palinTrue = false;
               break;
            } else {
               palinTrue = true;
            }
         }
         return palinTrue;
      }
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
      char[] evenLetters = {'b','d','f','h','j','l','n','p','r','t','v','x','z'};
      String lowers = new String(s.toLowerCase());
      String evenWord = new String("");
      for(int i=0;i<lowers.length();i++) {
         for(int j=0;j<=12;j++) {
            if(lowers.charAt(i)==evenLetters[j]) {
               char p = s.charAt(i);
               evenWord = evenWord + Character.toString(p);
            }
         }
      }
      return evenWord;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
      char[] oddLetters = {'a','c','e','g','i','k','m','o','q','s','u','w','y'};
      String lowers = new String(s.toLowerCase());
      String oddWord = new String("");
      for(int i=0;i<lowers.length();i++) {
         for(int j=0;j<=12;j++) {
            if(lowers.charAt(i)==oddLetters[j]) {
               char p = s.charAt(i);
               oddWord = oddWord + Character.toString(p);
            }
         }
      }
      return oddWord;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
      String evenDupes = evensOnly(s);
      String evens = new String();
      if(evenDupes.length()==0) {
         return evens;
      } else {
         evens = evens + evenDupes.charAt(0);
         int k = 1;
         int p = 1;
         for(int i=0; i<k;i++) {
            for(int j=p; j<evenDupes.length();j++) {
               if(evens.charAt(i)!=evenDupes.charAt(j)) {
                  evens = evens + evenDupes.charAt(j);
                  k = k + 1;
                  p = j;
                  break;
               }
            }
         }
      }
      return evens;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
      String oddDupes = oddsOnly(s);
      String odds = new String();
      if(oddDupes.length()==0) {
         return odds;
      } else {
         odds = odds + oddDupes.charAt(0);
         int k = 1;
         int p = 1;
         for(int i=0; i<k;i++) {
            for(int j=p; j<oddDupes.length();j++) {
               if(odds.charAt(i)!=oddDupes.charAt(j)) {
                  odds = odds + oddDupes.charAt(j);
                  k = k + 1;
                  p = j;
                  break;
               }
            }
         }
      }
      return odds;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      String revers = new String();
      int k = s.length();
      for(int i=0; i<k; i++) {
         revers = revers + s.charAt(k-1-i);
      }
      return revers;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
   }
}
