package GraphLib;
import java.util.*;

public class Graph<V, E> {
    
    public Map<V, Map<V,E>> edges;
    boolean oriented;

    public Graph(boolean oriented){
        edges = new HashMap<>();
        this.oriented = oriented;
    }

    public void insertVertex(V vertex) throws GraphException {
        if(vertex != null) {
            this.edges.putIfAbsent(vertex, new HashMap<>());
        }
        else throw new GraphException("Cannot insert vertex");
    }
    
    public void insertEdge(E edge, V vertex1, V vertex2) throws GraphException {
        if(edge!=null && vertex1!=null && vertex2!=null){
            this.edges.get(vertex1).putIfAbsent(vertex2,edge);

            if(!oriented){
                this.edges.get(vertex2).putIfAbsent(vertex1,edge);
            }
        }
        else throw new GraphException("Cannot insert edge");
    }
    
    public void removeEdge(V vertex1, V vertex2) throws GraphException {
        if(vertex1!=null && vertex2!=null && edges.containsKey(vertex1) && edges.containsKey(vertex2)){
            edges.get(vertex1).remove(vertex2);
        }
        else{
            throw new GraphException("cannot remove the edge");
        }
    }
    
    public void removeVertex(V vertex) throws GraphException {
        if(vertex != null && edges.containsKey(vertex)){
            edges.remove(vertex);
        }
        else throw new GraphException("cannot remove vertex");
    }

    public boolean isOriented() {
        return oriented;
    }
  
    public boolean searchVertex(V vertex){
        return this.edges.containsKey(vertex);
    }
    
    public boolean searchEdges(V vertex1, V vertex2){
        
        if(edges.containsKey(vertex1)) return edges.get(vertex1).containsKey(vertex2);
        
        else return false;
    }
  
    public int getVertexesNumber(){
        return edges.keySet().size();
    }
   
    public int getEdgesNumber(){
        int counter = 0;
        for (Map<V, E> internal : edges.values()) {
            counter += internal.size();
        }

        return counter;
    }
   
    public List<V> getVertexes() {
        List<V> list = new LinkedList<>();
        list.addAll(edges.keySet());
        return list;
    }
   
    public Map<V, Map<V, E>> getEdges() {
        return edges;
    }

    /*Returns the list of the neighbouring vertexes of the vertex passed by argument*/
    public List<V> getNeighbours(V vertex) throws GraphException{
        if(vertex == null) throw new GraphException("vertex parameter cannot be null");

        if(edges.containsKey(vertex)) {
            List<V> list = new LinkedList<>();
            list.addAll(edges.get(vertex).keySet());

            return list;
        }
        else return null;
    }
    
    /*Returns the value of the edge beetween the 2 vertexes passed by argument*/
    public E getLabel(V vertex1, V vertex2) throws GraphException {
        
        if(vertex1!=null && vertex2!=null && edges.containsKey(vertex1)&& edges.containsKey(vertex2)){
            return edges.get(vertex1).get(vertex2);
        }
        
        else throw new GraphException("Edge not in graph");
    }
}
