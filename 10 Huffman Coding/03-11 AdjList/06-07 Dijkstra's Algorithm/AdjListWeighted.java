// Name:Anirudh Mantha
// Date:05/24/22
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   
   public int hashCode(){
      return toString().hashCode();
   }
   
   
   public boolean equals(Object obj){
      boolean returnval = target.equals(((Neighbor)obj).target);
      return returnval;
   }
   
   public int compareTo(Neighbor valneighbor){
      return target.getName().compareTo(valneighbor.getTarget().getName());
   }
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........
   
   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
   
   public wVertex getTarget(){
      return target;
   }
   
   public double getEdgeDistance(){
      return edgeDistance;
   }
   
  

}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToSource to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToSource; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToSource = d;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToSource;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToSource = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToSource - other.distanceToSource);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString()
   { 
      String toReturn = "";
      toReturn += vertex.getName() + " " + distanceToSource;
      
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName(); 
      
    //returns an arraylist of PQelements that store distanceToSource to another wVertex
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
      
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToSource component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double m);
   public Set<Neighbor> getNeighbors(); 
   public void addAdjacent(wVertex v, double d);  
   public String toString(); 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */ 
   public wVertex(String n){
      name = n;
      neighbors = new TreeSet<Neighbor>();
      alDistanceToVertex = new ArrayList<PQelement>();
   }
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   public String getName(){
      return name;
   }
   
   public Set<Neighbor> getNeighbors(){
      return neighbors;
   }
   
   public void addAdjacent(wVertex v, double d){
      neighbors.add(new Neighbor(v, d));
   }
   
   public ArrayList<PQelement> getAlDistanceToVertex(){
      return alDistanceToVertex;
   }
   
   public PQelement getPQelement(wVertex v){
      for(PQelement temporary: alDistanceToVertex){
         if(temporary.getVertex().getName().equals(v.getName())){
            return temporary;
         }
      }
      return null;
   }
   
   public Double getDistanceToVertex(wVertex v){
      if(getPQelement(v) == null){
         return null;
      }
      return getPQelement(v).getDistanceToVertex();
   }
   
   public boolean equals(Object objectvar){
      if(objectvar instanceof wVertex){
         return name.equals(((wVertex)objectvar).getName());
      }
      
      return false;
   }
   
   public void setDistanceToVertex(wVertex vw, double dis){
      if(getPQelement(vw) == null){
         alDistanceToVertex.add(new PQelement(vw, dis));
      }
      
      else{
         getPQelement(vw).setDistanceToVertex(dis);
      }
   }
   
   
   
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }
   
   public int compareTo(wVertex wv){
      return name.compareTo(wv.getName());  
   }
   
   public int hashCode(){
      return name.hashCode();
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface//,AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */
  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   
   
   public String toString()
   {
      String strResult = "";
      for(String vName: vertexMap.keySet())
      {
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
   
   public Set<wVertex> getVertices(){
      Set<wVertex> returnset = new TreeSet<wVertex>();
      for(String stringvalue : vertexMap.keySet()){
         returnset.add(vertexMap.get(stringvalue));      
      }
      return returnset;
   }
   public Map<String, wVertex> getVertexMap(){  //this is just for codepost testing
      return vertexMap;
   }
   public wVertex getVertex(String vName){
      return vertexMap.get(vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   
   
   }
   public void addVertex(String vName){
      if(!vertexMap.keySet().contains(vName)){
      
         vertexMap.put(vName, new wVertex(vName));
     
      }  
   }  
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d){
      vertexMap.get(source).addAdjacent(vertexMap.get(target), d);
   }
   
   public void minimumWeightPath(String vertexName){ // Dijstra's algorithm
      PriorityQueue<PQelement> priqueue = new PriorityQueue<PQelement>();
      PQelement sourcevariable = new PQelement(getVertex(vertexName), 0);
      for(String newstringvariable : vertexMap.keySet()){
         if(!newstringvariable.equals(vertexName)){
            sourcevariable.getVertex().getAlDistanceToVertex().add(new PQelement(getVertex(newstringvariable), wVertex.NODISTANCE));
         }
         
         else{
            getVertex(vertexName).getAlDistanceToVertex().add(sourcevariable);
         }
         
      }
      
      priqueue.add(sourcevariable);
      
      while(!priqueue.isEmpty()){
         PQelement cur = priqueue.remove();
         for(Neighbor nei : cur.getVertex().getNeighbors()){
            if(cur.getDistanceToVertex() + nei.getEdgeDistance() < sourcevariable.getVertex().getPQelement(nei.getTarget()).getDistanceToVertex()){
               sourcevariable.getVertex().setDistanceToVertex(nei.getTarget(), cur.getDistanceToVertex() + nei.getEdgeDistance());
            }
            
            priqueue.add(sourcevariable.getVertex().getPQelement(nei.getTarget()));
         }
      }
      
   
   }
   /*  Graphs 7 has two more methods */ 
   public List<String> getShortestPathTo(wVertex target) 
   {
      return null;
   }  
     
   public void readData(String vertexNames, String edgeListData) 
   {
     /* use a try-catch  */
   
   }
}