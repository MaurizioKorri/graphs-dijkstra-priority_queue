package DijkstraAlgorithm;
import MinHeapLib.*;

import java.util.HashMap;
import java.util.List;

import GraphLib.Graph;
import GraphLib.GraphException;

public class DijkstraAlgo {

    /*Finds all the shortest path from the vertex source. It returns a Hashmap with the vertexes item 
    as key and a Vertex data structure that contains the minimum distance and parent Vertex as value*/
    
    public static <T> HashMap<T, Vertex> findShortestPath(Graph graph, T source) throws MinHeapException, GraphException {
        
        HeapComparator<Vertex<T>> comparator = (o1, o2) -> Float.compare(o1.DijkstraDistance, o2.DijkstraDistance);
        MinHeap<Vertex<T>> vertexHeap = new MinHeap<>(comparator);
        HashMap<T,Vertex> pathMap = new HashMap<>();
        
        for (T vertex : (List<T>) graph.getVertexes()) {

            Vertex tempVertex = new Vertex(vertex);

            if (vertex.equals(source)) tempVertex.DijkstraDistance = 0;

            vertexHeap.insert(tempVertex);
            pathMap.put(vertex, tempVertex);
        }

        while (vertexHeap.heapSize() > 0) {

            Vertex<T> u = vertexHeap.extractMinElem();

            for (T v : (List<T>)graph.getNeighbours(u.item)) {
                
                float distance = (float)graph.getLabel(u.item,v);
                Vertex currentVertex=pathMap.get(v);
                
                if (currentVertex.DijkstraDistance > u.DijkstraDistance + distance) {
                    
                    Vertex<T> newVertex = new Vertex(v);
                    
                    newVertex.DijkstraDistance = u.DijkstraDistance + distance;
                    newVertex.parent = u.item;
                    
                    pathMap.replace(v,newVertex);
                    try {
                       if(vertexHeap.heapSize() > 0) vertexHeap.decreaseKey(currentVertex, newVertex);
                    } 
                    catch (MinHeapException e) { e.printStackTrace();}
                }
            }
        }

        return pathMap;
    }
    

    /*prints all the visited vertexes on the destination path using the HashMap returned from the Dijkstra algorithm.*/
    public static <T> String minPath(HashMap<T,Vertex> mst, T destination) throws Exception {
        if (!mst.containsKey(destination)) throw new Exception("Vertex not present");
        
        StringBuilder path = new StringBuilder((String) destination);
        
        Vertex targetCity = mst.get(destination);
        
        while (targetCity.parent != null) {
            path.insert(0, targetCity.parent + " -> \n");
            targetCity = mst.get(targetCity.parent);
        }
        
        return path.toString();
    }
}

