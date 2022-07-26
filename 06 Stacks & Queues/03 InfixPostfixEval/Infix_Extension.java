// Name: Anirudh Mantha 
// Date: 1.15.22
//uses PostfixEval

import java.util.*;


public class Infix_Extension
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   public static final String numbers = "1 2 3 4 5 6 7 8 9 0";
   
   public static void main(String[] args) throws Exception
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
      
//       infixExp.add("3 + 4 * 5");
//       infixExp.add("3 * 4 + 5");
//       infixExp.add("1.3 + 2.7 + -6 * 6");
//       infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
//       infixExp.add("3 * 4 + 5 / 2 - 5");   
//       infixExp.add("8 + 1 * 2 - 9 / 3");   
//       infixExp.add("3 * ( 4 * 5 + 6 )");   
//       infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
//       infixExp.add("2 + 7 % 3");
//       infixExp.add("( 2 + 7 ) % 3");
      
//       infixExp.add("( 3.0 + -1.0 ) ^ 3.0");
      infixExp.add("2 ^ 3 + 3");
      infixExp.add("3 * 2 ^ 3");
      infixExp.add("( 1 + 3 ) !");
      infixExp.add("1 + 3 !");
      infixExp.add("1 * 3 !");
      infixExp.add("3 ? 2");
      infixExp.add("3 @ 2");
      infixExp.add("( 3 + 2");
      infixExp.add("3 + 2 }");
      infixExp.add("( 3 + 2 ]");
   
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         //System.out.println(infix + "\t\t\t" + pf );  
      
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix) throws Exception
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> pancake = new Stack<String>();
      String postfix = "";
      
      if(ParenMatch.checkParen(infix) == false){
         throw new Exception(infix + " ERROR in parentheses");
      }
      
        
      for(String s : nums){
         //System.out.println(s.substring(0,1));
         if(!numbers.contains(s.substring(s.length()-1)) && !operators.contains(s) && !LEFT.contains(s) && !RIGHT.contains(s) && !numbers.contains(s)){
            throw new Exception(infix + " ERROR non-algebraic symbol");
         }
         
         if(!operators.contains(s) && !LEFT.contains(s) && !RIGHT.contains(s)){
            postfix = postfix + " " + s; 
         }
         else if(LEFT.contains(s)){
            pancake.push(s);  
         }
         else if(RIGHT.contains(s)){
            do{
               postfix = postfix + " " + pancake.pop();
            }
            while(!LEFT.contains(pancake.peek()));
            pancake.pop();
         }
         boolean check = false; 
         if(operators.contains(s)){
            while(!check){
               if(pancake.empty() || LEFT.contains(pancake.peek()) || isStrictlyLower(s, pancake.peek())){
                  pancake.push(s);
                  check = true;
               }
               else{
                  
                  postfix = postfix + " " + pancake.pop();
                  
               }
            }
         } 
          
      }
      while(!pancake.empty()){
         if(!LEFT.contains(pancake.peek())){
            postfix = postfix + " " +  pancake.pop();
         }
      }
      if(postfix.substring(0, 1).equals(" ")){
         postfix = postfix.substring(1);
      }
      return postfix;
   }
    
   
   
   //enter your precedence method below
   public static boolean isStrictlyLower(String next, String top){
      String addsub = "+-";
      String muldiv = "*/%";
      String exp = "^";
      
      int nexval = 0;
      int topval = 0;
      
      if(addsub.contains(next)){
         nexval = 1; 
      }
      if(addsub.contains(top)){
         topval = 1; 
      }
      if(muldiv.contains(next)){
         nexval = 2; 
      }
      if(muldiv.contains(top)){
         topval = 2; 
      }
      if(exp.contains(next)){
         nexval = 3; 
      }
      if(exp.contains(top)){
         topval = 3; 
      }
      
      if(nexval > topval){
         return true;
      }
      return false;
   }

   
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
