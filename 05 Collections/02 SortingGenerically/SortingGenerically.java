// Name: Anirudh Mantha 
// Date: 12/05/2021

import java.io.*;      //the File class
import java.util.*;    //ArrayList & the Scanner class in Java 1.5
 
public class SortingGenerically
{
   public static void main(String[] args) throws Exception
   {
      //Widgets
      List<Comparable> apple = inputWidgets("widgets.txt");
      sort(apple);
      output(apple);
      System.out.println("There are " + apple.size() +" widgets, sorted.");
      
      //Strings
      List<Comparable> strList = inputStrings("strings.txt");
      sort(strList);
      output(strList);
      System.out.println("There are " + strList.size() +" strings, alphabetized.");
   }
   
   public static List<Comparable> inputWidgets(String filename) throws Exception
   {
      Scanner sc = new Scanner(new File(filename));
      List<Comparable> ar = new ArrayList<Comparable>();
      while(sc.hasNext()){
         int cub = sc.nextInt();
         int hand = sc.nextInt();
         ar.add(new Widget(cub, hand));
      }
      
      sc.close();
      
      return ar;
   }
   
   public static List<Comparable> inputStrings(String filename) throws Exception
   {
      Scanner sc = new Scanner(new File(filename));
      List<Comparable> ar = new ArrayList<Comparable>();
      while(sc.hasNext()){
         String str = sc.nextLine();
         //shortcut in splitting string and reading it in same line
         for(String temp: str.split(" ")){
            ar.add(temp);
         } 
      }
      sc.close();
   
      return ar;
   }
   /*  these methods are all GENERIC   */
   //I took normal foundatoins so methods were really similar to Unit 5 Lab 06;
   public static <T extends Comparable<T>> void sort(List<T> array)
   {
      int maximum;
      for(int i = 0; i < array.size(); i++)
      {
         maximum = findMax(array, array.size() - i);
         swap(array, maximum, array.size() - i - 1);
      }
   } 
   public static <T extends Comparable<T>> int findMax(List<T> array, int upper)
   {
      int maximum = 0;
      for(int i = 0; i < upper; i++){
         if(array.get(maximum).compareTo(array.get(i)) <  0){
            maximum = i;
         }
      }
      
          
      return maximum; 
   
   }
   
   public static <T> void swap(List<T> array, int a, int b)
   {
      T d = array.get(a);
      array.set(a, array.get(b));
      array.set(b, d);
   }
   
   public static void output(List<?> array)
   {
      for(int i = 0; i < array.size(); i++){
         System.out.println(array.get(i));
      }
   }
}

/*************************************
 0 cubits 14 hands
 1 cubits 3 hands
 2 cubits 14 hands
 5 cubits 14 hands
 10 cubits 14 hands
 11 cubits 11 hands
 12 cubits 0 hands
 12 cubits 7 hands
 13 cubits 9 hands
 15 cubits 12 hands
 17 cubits 5 hands
 18 cubits 13 hands
 19 cubits 13 hands
 19 cubits 13 hands
 22 cubits 6 hands
 23 cubits 7 hands
 24 cubits 15 hands
 24 cubits 15 hands
 26 cubits 2 hands
 28 cubits 5 hands
 28 cubits 12 hands
 29 cubits 15 hands
 31 cubits 0 hands
 32 cubits 1 hands
 32 cubits 11 hands
 32 cubits 11 hands
 32 cubits 12 hands
 35 cubits 3 hands
 39 cubits 2 hands
 39 cubits 5 hands
 41 cubits 10 hands
 43 cubits 2 hands
 43 cubits 5 hands
 43 cubits 6 hands
 51 cubits 2 hands
 54 cubits 14 hands
 55 cubits 8 hands
 56 cubits 3 hands
 57 cubits 12 hands
 62 cubits 15 hands
 63 cubits 0 hands
 64 cubits 13 hands
 67 cubits 3 hands
 70 cubits 0 hands
 73 cubits 5 hands
 74 cubits 7 hands
 75 cubits 9 hands
 81 cubits 5 hands
 85 cubits 14 hands
 86 cubits 3 hands
 90 cubits 13 hands
 91 cubits 3 hands
 92 cubits 1 hands
 92 cubits 8 hands
 96 cubits 1 hands
 98 cubits 8 hands
 99 cubits 5 hands
 There are 57 widgets, sorted.
 APCS
 Encapsulation
 Exam
 Generics
 Inheritance
 Java
 Method
 OOP
 Object
 Oriented
 Polymorphism
 Programming
 There are 12 strings, alphabetized.   
 ****************************************/