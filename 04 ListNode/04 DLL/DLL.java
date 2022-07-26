// Name: Anirudh Mantha
// Date: 11/18/21

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
    
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      DLNode temporary = new DLNode(obj, null, null);
      DLNode head2 = head;
      for(int i = 0; i < index; i++){
         head2 = head2.getNext();
      }
      size++;
      
      temporary.setNext(head2.getNext());
      temporary.setPrev(head2);
      
      head2.setNext(temporary);
      head2.getNext().getNext().setPrev(temporary);
      
      /* enter your code below  */
      
                    
                    
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      DLNode head2 = head;
      
      for(int i = 0; i < index+1; i++){
         head2 = head2.getNext();
      }
      return head2.getValue();
      
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temporary = head;
      for(int i = 0; i < index+1; i++){
         temporary = temporary.getNext();
      }
      Object objprev = temporary.getValue();
      temporary.setValue(obj);
      return objprev;
      
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temporary = head;
      for(int i = 0; i <= index; i++){
         temporary = temporary.getNext();
      }
      temporary.getPrev().setNext(temporary.getNext());
      temporary.getNext().setPrev(temporary.getPrev());
      temporary.setNext(null);
      temporary.setPrev(null);
      size--;
      
      return temporary.getValue();
      
   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      add(0, obj);
         //ALTERNATE METHOD
   //       DLNode temporary = new DLNode(obj, head, null); 
   //       temporary.setNext(head.getNext());
   //       head.setNext(temporary);
   //       head.getNext().setPrev(temporary);
   //       size++;
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      add(size, obj);
         
         //ALTERNATE METHOD
   //       DLNode temporary = new DLNode(obj, head.getPrev(), head);
   //       head.setPrev(temporary);
   //       head.getPrev().setNext(temporary);
   //       size++;
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
   //ALTERNATE METHOD
   //       head.setNext(head.getNext().getNext());
   //       head.getNext().getNext().setPrev(head);
   //       head.getNext().setNext(null);
   //       head.getNext().setPrev(null);
   //       size--;
   //       return head.getNext().getValue();
         
      return remove(0);
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
         //ALTERNATE METHOD
   //       head.setPrev(head.getPrev().getPrev());
   //       head.getPrev().getPrev().setNext(head);
   //       head.getPrev().setNext(null);
   //       head.getPrev().setPrev(null);
   //       return head.getPrev().getValue();
      return remove(size-1);
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
   
   //    String str = "[";
   //    for(int i = 0; i < size; i++){
   //       head = head.getNext();
   //       str = str + head.getValue();
   //    }
   //    str = str + "]";
   //    return str;
   
   // ALTERNATIVE METHOD
      DLNode temporary = head.getNext();
      DLNode head2 = head;
      String str = "[" ;
      while(temporary != head2){
         if(temporary == head2.getPrev()){
            str = str + temporary.getValue();
            temporary = temporary.getNext();
            break;  
         }
         else{
            str = str + temporary.getValue() + ", ";
            temporary = temporary.getNext();
         
         }
      
      }
      str = str + "]";
      return str;
   }
   
   
}