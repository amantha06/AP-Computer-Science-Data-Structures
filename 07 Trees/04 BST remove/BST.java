// Name: Anirudh Mantha
// Date: 2.10.22

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);  //BST_AVL
   public void remove(String obj);    
   //public void removeBalanced(String obj); //extra lab Red_Black
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
BST. Implement the remove() method.
Test it with BST_Remove_Driver.java
**********************/
public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s); 
      size++;  
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null){
         return new TreeNode(s, null, null);
      }
      if(t.getValue().toString().compareTo(s) >= 0){
         t.setLeft(add(t.getLeft(), s));
      }
      if(t.getValue().toString().compareTo(s) < 0){
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
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);   
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      if(t.getValue().toString().compareTo(x) > 0)
         return contains(t.getLeft(), x);
      else if(t.getValue().toString().compareTo(x) < 0){
         return contains(t.getRight(), x);
      }
      return true;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null){
         return null;
      }   
      while(t.getLeft() != null){
         t = t.getLeft();
      }
      return t.getValue().toString();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null){
         return null;
      }   
      else if(t.getRight() == null){
         return t.getValue().toString();
      }
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root
      toReturn += toString(t.getRight());  //recurse right 
        
      return toReturn; 
   }


   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      TreeNode parent = current;
      TreeNode pointer = current;
      
      //FIRST STEP
      //set pointer to the value
      
      boolean found = false;
      while(!found){
         if(current.getValue().toString().compareTo(target) == 0){
            found = true;
            break;
         }
         parent = current;
         if(current.getValue().toString().compareTo(target) < 0){
            current = current.getRight();
            
         }
         else if(current.getValue().toString().compareTo(target) > 0){
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
         p
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