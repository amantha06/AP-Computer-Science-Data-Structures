// Name: Anirudh Mantha
// Date: 01.07.22 - 01.09.22
 
import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      //copy and paste from assignment sheet
      parenExp.add("5 + 7");
      parenExp.add("( 15 + -7 )");
      parenExp.add(") 5 + 7 (");
      parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
      parenExp.add("< { 5 + 7 } * 3 >");
      parenExp.add("[ ( 5 + 7 ) * ] 3");
      parenExp.add("( 5 + 7 ) * 3");
      parenExp.add("5 + ( 7 * 3 )");
      parenExp.add("( (5 + 7) * 3");
      parenExp.add("[ ( 5 + 7 ] * 3 )");
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      parenExp.add("( [ ( 5 + 7 ) * 3 ]");
      parenExp.add("( ( ( ) $ ) )");
      parenExp.add("( ) [ ]");
   
      for( String s : parenExp)
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {      
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
      
      Stack<String> pancake = new Stack<String>();
           
   
     
      for(int i = 0; i< exp.length(); i++){
      
         //add the left parenthesis elements to the stack
         if(LEFT.contains("" + exp.charAt(i))){
            pancake.push("" + exp.charAt(i));
         
         }
      
         //if it is still WHEN you encounter a right element return false
         
         if(pancake.empty() && RIGHT.contains("" + exp.charAt(i))){
            return false;
         }
         
         //if the right element is EQUIVALENET to the PEEK of the stack, POP the stack, and CHECK AGAIN for the next element 
         if(RIGHT.contains("" + exp.charAt(i)) && isLeftParen(pancake.peek()) == isRightParen(""+exp.charAt(i))){
            pancake.pop();
         }
      }
      //you want to the stack to have nothing at the end
      if(pancake.empty()){
         return true; 
      }   
      //if there are lingering parenthesis, then it is invalid
      
         
      return false;  //so it compiles    
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
