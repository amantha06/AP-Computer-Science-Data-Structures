// Name:  Anirudh Mantha 
// Date: 9-4-21
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      
     
      
      

      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
   
      if(s.length() == 0)
         return "";
      
      //remove and store the beginning punctuation 
      //remove and store the ending punctuation 
   
      String storeS = "";
      String storeE = "";
      boolean hasSpunc = true;
      boolean hasEpunc = true;
      
      for(int i = 0; i < s.length(); i++){
         if(punct.contains("" + s.charAt(i)) == true && hasSpunc == true){
            storeS = storeS + s.charAt(i);
         
         }  
         else{
            hasSpunc = false;
         
         } 
      
      }
      
      for(int i = s.length() - 1; i >= 0; i--){
         if(punct.contains("" + s.charAt(i)) == true && hasEpunc == true){
            storeE = storeE + s.charAt(i);
         
         }  
         else{
            hasEpunc = false;
         
         } 
         
                  
         
         
         //System.out.println(storeE);
      
      }
      
      String storeEnew = "";
      for(int i = 1; i <= storeE.length(); i++){
         storeEnew = storeEnew + storeE.charAt(storeE.length()-i);
             //System.out.println(storeEnew);
      }
       
      s = s.substring(storeS.length(), s.length());
      s = s.substring(0, s.length() - storeEnew.length());
    
           
      
      //s = s.replace(storeEnew, "");
      
    
        
   
      
      
           
      
      
      
      //START HERE with the basic case:
     
      
      String fvow = "";
      int fvowpos = -1;
      String placeholder = "" + s.charAt(0);
      boolean checkvow = false;
      if(vowels.contains(placeholder) && checkvow == false){
         fvow = fvow + placeholder;
         fvowpos = 0;
         //System.out.println(fvow);
         checkvow = true;
      }
        
        
      for(int i = 1; i < s.length(); i++){
         placeholder = "" + s.charAt(i);         
         String ys = "Yy";
                  
         String newstring = s.toLowerCase();    
         if(newstring.contains("q") && newstring.charAt(newstring.indexOf("q") + 1) == 'u' && checkvow == false){
               
            int qupos = newstring.indexOf("q"); 
            int qupos2 = newstring.indexOf("u");
            int diff = qupos2 - qupos;
               
            if(diff == 1 && vowels.contains("" + newstring.charAt(qupos2 + 1))){
               fvow = "" + newstring.charAt(qupos2 + 1);
               fvowpos = newstring.indexOf("" + newstring.charAt(qupos2 + 1));
               //System.out.println(fvow);
               checkvow = true;
               break;
            }  
                                    
         }
            
            
         if(ys.contains(placeholder)|| vowels.contains(placeholder) && checkvow == false){
            fvow = fvow + placeholder;
            fvowpos = i;
            //System.out.println(fvow);
            checkvow = true;
            break;
            
         } 
         
        
      
      }
         
      if(checkvow == false){
         
         return "**** NO VOWEL ****";
      }
            
              
         
         
      for(int k = 0; k < s.length(); k++){
            
         if(checkvow == true){
            boolean upper = Character.isUpperCase(s.charAt(0));
         
             if(fvowpos == 0){
                s = storeS + s + "way" + storeEnew;
                return s;
             }
            
            if(upper){
               String firstchar = "" + s.charAt(0);
               s = s.replaceFirst(firstchar, firstchar.toLowerCase()); 
               
               
               //have twice so that the first letter in new string can be capital
               String p1 = "";
              
               p1 = s.substring(0, fvowpos);
               s = s.replaceFirst(p1, "");
               
               firstchar = "" + s.charAt(0);
               
               
               s = s.replaceFirst(firstchar, firstchar.toUpperCase());
               
              
               s = storeS + s + p1 + "ay" + storeEnew; 
               
               
              
               return s;
            
            
            }       
            String p1 = "";
            p1 = s.substring(0, fvowpos);
            s = s.replaceFirst(p1, "");
         
            s = storeS + s + p1 + "ay" + storeEnew;
            
            
            
            return s;
         }    
                
      }
            
            
            //chatAt
            //substring
            //indexOf
            //contains
            
            
         
         
      
      //     find the index of the first vowel  COMPLETE
      //     y is a vowel if it is not the first letter COMPLETE
      //     qu 
      
      //if qu is before vowel, it is a consonant
      
      
      
      //if no vowel has been found
      
      
      //is the first letter capitalized?
      
      
      //return the piglatinized word 
      
      return s;
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
      
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      
      
      while(infile.hasNext()){
         String inputWord = infile.nextLine();
         String[] input = inputWord.split(" ");
         for(int i = 0; i < input.length; i++){
            if(i < input.length - 1){
               outfile.print(pig(input[i]) + " ");
            
            }
            else{
               outfile.print(pig(input[i]));
            
            }
         }
         
         if(infile.hasNext()){
            outfile.println();
         
         }
         
      }
      
      
      
      
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
         
      String puncS = "";
      String puncE = "";
      boolean hasSpunc = true;
      boolean hasEpunc = true;
      
      for(int i = 0; i < s.length(); i++){
         if(punct.contains("" + s.charAt(i)) == true && hasSpunc == true){
            puncS = puncS + s.charAt(i);
         
         }  
         else{
            hasSpunc = false;
         
         } 
      
      }
      
      for(int i = s.length() - 1; i >= 0; i--){
         if(punct.contains("" + s.charAt(i)) == true && hasEpunc == true){
            puncE = puncE + s.charAt(i);
         
         }  
         else{
            hasEpunc = false;
         
         } 
      }
      
      String puncEnew = "";
      for(int i = 1; i <= puncE.length(); i++){
         puncEnew = puncEnew + puncE.charAt(puncE.length()-i);
      }
   
//       s = s.substring(puncS.length(), s.length());
//       s = s.substring(0, s.length() - puncEnew.length());
      
      s = s.substring(puncS.length(), s.length()-puncEnew.length());

      
      String newstring = "";
   
      for(int k = 0; k < s.length(); k++){
            
         boolean upper = Character.isUpperCase(s.charAt(0));
         
            
         if(upper){
            String firstchar = "" + s.charAt(0);
            s = s.replaceFirst(firstchar, firstchar.toLowerCase()); 
               
               
            for(int i = 1; i <= s.length(); i++){
               newstring = newstring + s.charAt(s.length()-i);
            }
            
            
            //should be newstring not S, but doesnt work cause newstring has ??? at start because of McDonalds
            firstchar = "" + newstring.charAt(0);
            newstring = newstring.replaceFirst(firstchar, firstchar.toUpperCase());
            
            s = puncS + newstring + puncEnew; 
            upper = false;
            return s;
            
            
         } 
               
         for(int i = 1; i <= s.length(); i++){
            newstring = newstring + s.charAt(s.length()-i);
         }
            
         s = puncS + newstring + puncEnew; 
         
         return s;
         
      
         
            
      }
   
         
      return "";
   }      
         
}

