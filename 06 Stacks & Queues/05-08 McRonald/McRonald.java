//Updated on 12.14.2020 v2

//Name: Anirudh Mantha   
//Date: 1.18.22
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
   
      /***************************************
      Write your code for the simulation   
      **********************************/
      Queue<Customer> line = new LinkedList<Customer>();
      int thisCustomerTime = timeToOrderAndBeServed();
      for(int i = 0; i <= TIME; i++){
         if(Math.random() < 0.2){
            line.add(new Customer(i, thisCustomerTime)); 
         }
         displayTimeAndQueue(line, i);
         if(!line.isEmpty()){
            of.write("       " + line.peek() + " is now being served for " + line.peek().getEstTime() + " minutes." + "\n");
         }
  
         if(!line.isEmpty() && thisCustomerTime > 1){
            thisCustomerTime--; 
         }
         else if(!line.isEmpty()){
            totalMinutes += i-line.peek().getArrivalTime() + line.peek().getEstTime();
         
            if(i-line.peek().getArrivalTime() + line.peek().getEstTime() > longestWaitTime){
               longestWaitTime = i-line.peek().getArrivalTime() + line.peek().getEstTime();
            }
            line.remove(); 
            if(line.size() > longestQueue){
               longestQueue = line.size();
            }
            
            customers++;
            thisCustomerTime = timeToOrderAndBeServed();
         } 
      }
      int num = TIME;
      while(!line.isEmpty()){
      
      //    displayTimeAndQueue(line, i);
      //have to copy and paste the same process for customers after 11:59   
      //this time the time will be higher than 1080, that is why we start at thsi time and incriment until all of the customers leave
         if(!line.isEmpty() && thisCustomerTime > 1){
            thisCustomerTime--; 
         }
         else if(!line.isEmpty()){
            totalMinutes += num-line.peek().getArrivalTime() + line.peek().getEstTime();
            
            if(num-line.peek().getArrivalTime() + line.peek().getEstTime() > longestWaitTime){
               longestWaitTime = num-line.peek().getArrivalTime() + line.peek().getEstTime();
            }
            line.remove(); 
         
            if(line.size() > longestQueue){
               longestQueue = line.size();
            }
            customers++;
            thisCustomerTime = timeToOrderAndBeServed();
         } 
           
         num++;  
      }
   
        
        
        
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
      public Customer(int time, int ordser){
         orderAndBeServed = ordser;
         arrivedAt = time; 
         
      }
      
      public int getEstTime(){
         return orderAndBeServed;
      }
      
      public String toString(){
         return ""+arrivedAt;
      }
      
      public int getArrivalTime(){
         return arrivedAt;
      }  
   
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
   
    
    
   }
}