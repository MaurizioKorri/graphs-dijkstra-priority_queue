# Generic graph implementation and Dijkstra algorithm using priority queue 

## Graph: 
Implementation of a Graph data structure with generic types for both vertexes and edges of the graph. All various operations that are usually needed in a graph are also implemented. 

## Minimum Heap:
Implementation of a generic Minumum Heap binary tree that is implemented using an ArrayList where all the positions of each element are associated with the element itself in a hashMap so that we can reduce the complexity of the fundamental DecreaseKey() operation that will later be used in the Dijkstra Algorithm.

Unit tests using JUnity are also available.  

## Dijkstra Algorithm:

Dijkstra algorithm is an algorithm used to find the shortest paths between vertexes in a weighted graph. This particular variant of the algorithm uses the Minimum Heap previously mentioned as a priority queue to decrease the complexity of the algorithm from O(V^2) to O(E * log V) where V is the number of vertexes and e is the number of edges. 
The algorithm picks the unvisited vertex with the lowest distance, calculates the distance through it to each unvisited neighbor, and updates the neighbor’s distance if smaller. 

## Example usage: 
In the GraphUsageMain.java there is a usage of the Dijkstra algorithm, a graph containing the distance between some italian cities and a fraction of the localities closest to them is loaded from a .csv file and a shortest path between the city of "Torino" and "Catania" is found using the algorithm. 
