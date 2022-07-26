// Name: Anirudh Mantha 
// Date: 3/8/2022

import java.io.*;
import java.util.*;  

/* This program takes a text file, creates an index (by line numbers)
 * for all the words in the file and writes the index
 * into the output file.  The program prompts the user for the file names.
 */
public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      
      DocumentIndex index = makeIndex(inputFile);
      
      System.out.println( index.toString() );
      PrintWriter outputFile = new PrintWriter(new FileWriter("fishIndex.txt"));
      outputFile.println(index.toString());
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static DocumentIndex makeIndex(Scanner inputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      return index;  
   }
}

class DocumentIndex extends TreeMap<String, TreeSet<Integer>>
{
   public DocumentIndex(){
      super();
   }
  
  /** Extracts all the words from str, skipping punctuation and whitespace
   *  and for each word calls addWord(word, num).  A good way to skip punctuation
   *  and whitespace is to use String's split method, e.g., split("[., \"!?]") 
   */
   public void addAllWords(String str, int lineNum) 
   {
      for(String s: str.split("[,. \"?!]")){
         if(!s.equals("")){
            addWord(s, lineNum);
         }
      
      } 
   }

  /** Makes the word uppercase.  If the word is already in the map, updates the lineNum.
   *  Otherwise, adds word and TreeSet to the map, and updates the lineNum   
   */
   public void addWord(String word, int lineNum)
   {
      word = word.toUpperCase();
      String uppercaseletter = word;
      if(this.containsKey(uppercaseletter)){
      
         TreeSet<Integer> set = this.get(uppercaseletter);
         
         set.add(lineNum);
      }
      
      
      
      else if(!this.containsKey(uppercaseletter)){
      
      
      
         this.put(uppercaseletter, new TreeSet<Integer>());
         TreeSet<Integer> xyz = this.get(uppercaseletter);
         xyz.add(lineNum);
      }
      
      else{
      
      System.out.println("NONE");
      
      }
   
   }
   
   public String toString()
   {
      String s = super.toString();

      String cont = "{}[]";
      String temp = s;
      for(int i = 0; i < s.length(); i++){
         if(cont.contains("" + s.charAt(i))){
            temp = temp.replace("" + s.charAt(i), "");
         }
         if("=".contains("" + s.charAt(i))){
            temp = temp.replace("" + s.charAt(i), " ");
         }
         

      }
      //System.out.println(this.get() + "");
      return temp;
        
   }
}

/**********************************************
     ----jGRASP exec: java -ea IndexMakerMap
 
 Enter input file name: fish.txt
 Done.
 
  ----jGRASP: operation complete.
  
************************************************/
/****************** fishIndex.txt  **************
A 12, 14, 15
ARE 16
BLACK 6
BLUE 4, 7
CAR 14
FISH 1, 2, 3, 4, 6, 7, 8, 9, 16
HAS 11, 14
LITTLE 12, 14
LOT 15
NEW 9
OF 16
OLD 8
ONE 1, 11, 14
RED 3
SAY 15
STAR 12
THERE 16
THIS 11, 14
TWO 2
WHAT 15   
   ************************/
