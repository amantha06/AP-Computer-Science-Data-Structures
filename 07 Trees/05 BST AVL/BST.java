// Name: Anirudh Mantha
// Date: 02.17.2022

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

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





   /*  start the addBalanced methods */
   private int calcBalance(TreeNode t) //height to right minus 
   {                                    //height to left
   
   }

   private int height(TreeNode t)   //from TreeLab
   {
   
   }

   public void addBalanced(String value)  
   {
      add(value);
      balanceTree(      );   // for an AVL tree. Put in the arguments you want.
   }
   private void balanceTree(     )  //recursive.  Whatever makes sense.
   {
   
   }
   // 4 rotation methods
   
   
}