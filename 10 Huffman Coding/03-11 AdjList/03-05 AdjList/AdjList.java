// Name: Anirudh Mantha
// Date: 05.12.22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
   */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
   
   
  /* enter your code here  */
  
   public Vertex(String str){
      name = str;
      adjacencies = new HashSet<Vertex>();
   }   
  
   public String getName(){
      return name; 
   }
  
   public String toString(){
   
      String returningstringvalue = name;
      returningstringvalue = returningstringvalue + " [";
      for(Vertex temporaryvertex : adjacencies){
         if(temporaryvertex != null)
            returningstringvalue = returningstringvalue + temporaryvertex.getName() + " ";
      }
      returningstringvalue = returningstringvalue.strip();
      returningstringvalue = returningstringvalue + "]";
   
   //name + space + [
   //iterate through vertices in adj
      // if v is not null, then add v.getName + SPACE
   //strip 
   // add ] 
      return returningstringvalue;
   }
   
   public int hashCode(){
      return toString().hashCode();
   }
  
   public HashSet<Vertex> getAdjacencies(){
      return adjacencies;
   }
  
   public void addAdjacent(Vertex v){
      adjacencies.add(v);
   }
  
   public int compareTo(Vertex v){
      return name.compareTo(v.getName());
   }
   
   public boolean equals(Object obj){
      boolean returnval = (obj instanceof Vertex) && (name.compareTo(((Vertex)obj).name) == 0);
      return returnval;
   }
   

   
  
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 
}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
   //public List<Vertex> depthFirstRecur(String name);
   //public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
   
   private HashSet<Vertex> vertList = new HashSet<Vertex>();
   /* constructor is not needed because of the instantiation above */
  
   /* enter your code here  */
 
   public Set<Vertex> getVertices(){
      return vertList;
   } 
   
   public Vertex getVertex(String vertexInput){
      return vertexMap.get(vertexInput);
   }
   
   public Map<String, Vertex> getVertexMap(){
      return vertexMap;
   }
   
   public void addVertex(String additionalvert){
      if(!vertexMap.containsKey(additionalvert)){
         Vertex temporaryvertex = new Vertex(additionalvert);
          //make new vertex first
         vertexMap.put(additionalvert, temporaryvertex);
         vertList.add(temporaryvertex);
      }
   }
   
   public void addEdge(String source, String target){
      //already have add adjacent just plug in the values
      vertexMap.get(source).addAdjacent(vertexMap.get(target));
   }
   
   public String toString(){  
      
      String returningstringvalue = "";
      for(String temporary : vertexMap.keySet()){
         returningstringvalue = returningstringvalue + vertexMap.get(temporary).toString() + "\n";
      }   
      returningstringvalue = returningstringvalue.strip();
   
      //getvalue of every key . toString
      //add it to a new line \n
      //take out the extra indentation at the end
      
      return returningstringvalue;
      
   }

   
   public List<Vertex> depthFirstSearch(String name){
      Vertex store = getVertex(name);
      //make the string into a vertex
      Vertex tempstore = null;
      List<Vertex> returnlist = new ArrayList<Vertex>();
      Set<Vertex> storereturnlist = store.getAdjacencies();
      Set<Vertex> tempstoreset = new HashSet<Vertex>();
      //get the adjacents to iterate through and use stack later
      Stack<Vertex> storestack = new Stack<Vertex>();
      
      for(Vertex value : storereturnlist){
      
         storestack.push(value);
         //push values
      
      }
      
      while( !storestack.isEmpty() ){
      
      //as the stack has stuff do the alg
         tempstore = storestack.pop();
         if( !returnlist.contains(tempstore) ){
            //add the vertex that are not there
            returnlist.add(tempstore) ;
            
            tempstoreset = tempstore.getAdjacencies();
            for(Vertex value : tempstoreset){
               if(!returnlist.contains(value)){
               //check again with new adjacent values
                  storestack.push(value);
                
               }
            
            } 
         }
      
         
      }
      //return
      return returnlist;
   }
   //same EXACT thing as above
   //use queue instead of stack
   //because it is breadth rather than depth
   public List<Vertex> breadthFirstSearch(String name){
      Vertex store = getVertex(name);
      //make the string into a vertex
      Vertex tempstore = null;
      List<Vertex> returnlist = new ArrayList<Vertex>();
      Set<Vertex> storereturnlist = store.getAdjacencies();
      Set<Vertex> tempstoreset = new HashSet<Vertex>();
      //get the adjacents to iterate through and use stack later
      Queue<Vertex> storequeue = new LinkedList<Vertex>();
      
      for(Vertex value : storereturnlist){
      
         storequeue.add(value);
         //add values
      
      }
      
      while( !storequeue.isEmpty() ){
      
      //as the queue has stuff do the alg
         tempstore = storequeue.remove();
         if( !returnlist.contains(tempstore) ){
            //add the vertex that are not there
            returnlist.add(tempstore) ;
            
            tempstoreset = tempstore.getAdjacencies();
            for(Vertex value : tempstoreset){
               if(!returnlist.contains(value)){
               //check again with new adjacent values
                  storequeue.add(value);
                
               }
            
            } 
         }
      
        
      }
      //return
      return returnlist;
   }   
   public void readData(String cities, String edges) throws FileNotFoundException{
      Scanner sc = new Scanner(new File(cities));
      
      while(sc.hasNext()){
      
         addVertex(sc.next());
      
      }   
      
      Scanner sc2 = new Scanner(new File(edges));
      while(sc2.hasNext()){
         
         addEdge(sc2.next(), sc2.next());
         
      }
      
      
   }
   public int edgeCount(){
      int returnval = 0;
      
      for(String str : vertexMap.keySet()){
         Vertex v = vertexMap.get(str);
         returnval += v.getAdjacencies().size(); 
      }
      
      return returnval;
   } 
   public int vertexCount(){
   
   return vertexMap.size();
   
   }
   
   public boolean isReachable(String source, String target){
      
      List<Vertex> templistvals = depthFirstSearch(source);
      boolean returnval = templistvals.contains(getVertex(target));
      return returnval;
   }  
   public boolean isStronglyConnected(){ 
   
      boolean returnval = false;
      for(String str : vertexMap.keySet()){
         
         for(String str2 : vertexMap.keySet()){
            
            if(!isReachable(str, str2))
               return false;
            
               
         }
         
      } 
      
      return true; 
   }
   
   
   //problems throughout the lab: Had a treeset instead of hashset, caused problem in ordering
   //QUEUE is implemented with a LinkedList
   /*   extra credit methods */
   
 
 
}


