// Name: Anirudh Mantha
// Date: 2.22.22
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;
   public void BST_Generic()
   {
      root = null;
      size = 0;
   
   }
   public int size()
   {
      return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public E add(E s) 
   {
      root = add(root, s); 
      size++;  
      return s;
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {      
      if(t == null){
         return new TreeNode<E>(s, null, null);
      }
      if(t.getValue().compareTo(s) >= 0){
         t.setLeft(add(t.getLeft(), s));
      }
      if(t.getValue().compareTo(s) < 0){
         t.setRight(add(t.getRight(), s));
      }
      return t;
   
   //          if(t == null){
   //             return new TreeNode(s, null, null);
   //          }
   //          TreeNode q = t, p = null;
   //          while(q!=null){
   //             p = q;
   //             if(x.compareTo(p.getValue() <= 0){
   //                q = p.getLeft();
   //             }
   //             else{
   //                q = p.getRight();
   //             }
   //             if(s.compareTo(p.getValue().toString() <= 0){
   //                p.setLeft(new TreeNode(x));
   //             }
   //             else{
   //                p.setRight(new TreeNode(x));
   //             }
   //             return t;
   //          }
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains(E obj)
   {
      return contains(root, obj);   
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if(t == null)
         return false;
      if(t.getValue().compareTo(x) > 0)
         return contains(t.getLeft(), x);
      else if(t.getValue().compareTo(x) < 0){
         return contains(t.getRight(), x);
      }
      return true;
   }
   
   public E min()
   {
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t == null){
         return null;
      }   
      while(t.getLeft() != null){
         t = t.getLeft();
      }
      return t.getValue();
   }
   
   public E max()
   {
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t == null){
         return null;
      }   
      else if(t.getRight() == null){
         return t.getValue();
      }
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root
      toReturn += toString(t.getRight());  //recurse right 
        
      return toReturn; 
   }
   public List<E> toList(){
      return toList(root);
   }
   
   public List<E> toList(TreeNode<E> root){
     
      List<E> x = new ArrayList<E>();
      
      if(root == null)
         return x;
      if(root.getLeft() != null);
         x.addAll(toList(root.getLeft()));   //recurse left
      
      x.add(root.getValue());              //process root
      
      if(root.getRight() != null);
         x.addAll(toList(root.getRight()));  //recurse right 
        
      return x; 
   }
      public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      TreeNode<E> parent = current;
      TreeNode<E> pointer = current;
      
      //FIRST STEP
      //set pointer to the value
      
      boolean found = false;
      while(!found){
         if(current.getValue().compareTo(target) == 0){
            found = true;
            break;
         }
         parent = current;
         if(current.getValue().compareTo(target) < 0){
            current = current.getRight();
            
         }
         else if(current.getValue().compareTo(target) > 0){
            current = current.getLeft();
         }
      }
   //       System.out.println(current.getValue().toString());
   //       System.out.println(parent.getValue().toString());
     
      boolean leftOright = false;
      if(parent.getLeft() == current){
         leftOright = true;
      }
      else if(parent.getRight() == current){
         leftOright = false;
      }
      
      
      // root and leaf
      if(size == 1){
         current = null;
         return current; 
      }         
      
      // leaf only
      else if(current.getLeft() == null && current.getRight() == null){
         if(!leftOright)
            parent.setRight(null);
         if(leftOright)
            parent.setLeft(null);
            
         return pointer;
      }
      
      else if((current == pointer) && (current.getRight() != null) && (current.getLeft() != null)){
         current = current.getLeft();
         if(current.getRight() == null && current.getLeft() != null){
            parent.setLeft(current.getLeft());
            return pointer;
         }
         if(current.getRight() == null && current.getLeft() == null){
            parent.setLeft(current);
            return pointer;
         }
         while(current.getRight() != null){
            parent = current;
            current = current.getRight();
         }  
         pointer.setValue(current.getValue());
         
         if(current.getLeft() != null){
            parent.setRight(current.getLeft());
         }   
         else{
            parent.setRight(null);
         }
         return pointer; 
      }
      
      else if((current == pointer) && (current.getRight() != null) && (current.getLeft() == null)){
         current = current.getRight();
         pointer.setRight(null);
         pointer = current;
         return pointer;
      }
      
      else if((current == pointer) && (current.getRight() == null) && (current.getLeft() != null)){
         current = current.getLeft();
         pointer.setLeft(null);
         pointer = current;
         return pointer;
      }
      
      else if(current.getRight() != null && current.getLeft() == null){
         if(!leftOright){
            parent.setRight(current.getRight());
         }
         if(leftOright){
            parent.setLeft(current.getRight());
         }
         return pointer;
      }
      else if(current.getRight() == null && current.getLeft() != null){
         if(!leftOright){
            parent.setRight(current.getLeft());
         }
         if(leftOright){
            parent.setLeft(current.getLeft());
         }
         return pointer;
      }
      
      else if(current.getRight() != null && current.getLeft() != null){
         TreeNode temp = current;
         current = current.getLeft();
         //parent = current;
      if(current.getRight() == null){
          parent = temp;
         temp.setValue(current.getValue());

         if(current.getRight() == null && current.getLeft() == null){
            parent.setLeft(null); 
         }
         else if(current.getLeft() != null){
            parent.setLeft(current.getLeft());
         }
         return pointer;
         }
         while(current.getRight() != null){
            parent = current;
            current = current.getRight();
         }  

         temp.setValue(current.getValue());
         
         if(current.getRight() == null && current.getLeft() == null){
            parent.setRight(null); 
         }
         else if(current.getLeft() != null){
            parent.setRight(current.getLeft());
         }
         return pointer;
         
      }
      
      return pointer;      
      // case 2a
      
      //etc.
   
   }


}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode<E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode<E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }

}