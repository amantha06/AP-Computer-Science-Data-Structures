// Name: Anirudh Mantha
// Date: 3.10.22

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> retmap = new TreeMap<String, Set<String>>();
      
      
      while(infile.hasNext())
         //retmap.put(infile.next(), infile.next());
         //this doesnt work because infile.next() is not a set
         //need to call add
         add(retmap, infile.next(), infile.next());
      return retmap;    
      
   }
   
   public static void add(Map<String, Set<String>> dictionary, String translation, String word)
   { 
      Set diction = dictionary.get(translation);
      if(diction == null)
         diction = new TreeSet<String>();
      diction.add(word);
      dictionary.put(translation, diction);
      
      //adding words to the set
      //putting the set and key into the map
      //getting called above
   }
   public static void display(Map<String, Set<String>> m)
   {
      for(String s : m.keySet())
         System.out.println(s + " " + m.get(s));
      
      System.out.println();
      
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> retmap = new TreeMap<String, Set<String>>();
      for(String s : dictionary.keySet()){
         Set<String> storeval = new TreeSet<String>();
         storeval = dictionary.get(s);
         //storing the value of every key into a set
         for(String n : storeval){
            add(retmap, n, s);
         }
         //adding in the other order
      }
      return retmap;

   }
      
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/