// Name: Anirudh Mantha
// Date:  2/5/2022
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }

   public void buildTree(String str)
   {
      Stack<TreeNode> operands = new Stack<TreeNode>(); 
      TreeNode rootex = null; 
      
      String[] inp = str.split(" ");
      if(inp.length == 1){
         rootex = new TreeNode(inp[0]); 
      }  
      for(String x: inp){
         TreeNode temp = new TreeNode(x);
         if(!operators.contains(x)){
            operands.push(temp);
         }    
         if(operators.contains(x) && !operands.isEmpty()){
            rootex = temp;
            rootex.setRight(operands.pop());
            if(operands.isEmpty() == false)
               rootex.setLeft(operands.pop());
            operands.push(rootex);
         
         }  
      }
      root = rootex;
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null){
         return 0;
      }
      if(isOperator(t.getValue().toString())){
         return computeTerm(t.getValue().toString(), evaluateNode(t.getLeft()), evaluateNode(t.getRight())); 
      }
      else{
         return Double.parseDouble(t.getValue().toString());
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
         return a + b;
      
      if(s.equals("-"))
         return a-b;
         
      if(s.equals("*"))
         return a*b;
      
      if(s.equals("/"))
         return a/b;
      
      if(s.equals("%"))
         return a%b;
         
      if(s.equals("^"))
         return Math.pow(a, b);
         
      return 0;   
   }
   
   private boolean isOperator(String s)
   {
      if(operators.contains(s)){
         return true;
      }
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root
      toReturn += inorderTraverse(t.getRight());  //recurse right 
        
      return toReturn;  
   
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {

      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //process root
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}