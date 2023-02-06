package GraphUnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import GraphLib.Graph;
import GraphLib.GraphException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GraphUnitTests{

    @Test
    public void testInsertVertexOneEl() throws GraphException{
        
        Graph<Integer, Float> graph = new Graph<>(false);
        graph.insertVertex(3);
        assertTrue(graph.getVertexes().contains(3));

    }

    @Test
    public void testInsertVertex() throws GraphException{
        
        Graph<Integer, Float> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertVertex(13);
        assertTrue(graph.getVertexes().contains(3) && graph.getVertexes().contains(2) && graph.getVertexes().contains(13));

    }

    @Test 
    public void testInsertExistingElementVertex() throws GraphException {
        Graph<Integer, Float> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(3);
        assertTrue(graph.getVertexesNumber()==1);
    }

    @Test (expected = GraphException.class)
    public void testInsertVertexNULL() throws GraphException{
        Graph<Integer, Float> graph = new Graph<>(false);
        graph.insertVertex(null);
        
    }

    @Test
    public void testInsertEdgeNotOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge("edge1", 3, 2);

        assertTrue(
                    graph.getLabel(3, 2).equals("edge1") &&    
                    graph.getLabel(2, 3).equals("edge1")     
                  );
        
    }
    
    @Test 
    public void testInsertExistingEdgeNotOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge("edge1", 3, 2);
        graph.insertEdge("edge1", 3, 2);

        assertTrue(graph.getEdgesNumber()==2);
        
    }

    @Test
    public void testInsertExistingEdgeOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge("edge1", 3, 2);
        graph.insertEdge("edge1", 3, 2);

        assertTrue(graph.getEdgesNumber()==1);
        
    }

    @Test (expected = GraphException.class)
    public void testInsertEdgeNULL() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge(null, 3, 2);
    }

    @Test 
    public void testInsertEdgeOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge("edge1", 3, 2);
        assertTrue(graph.getLabel(3, 2)=="edge1"  &&  !graph.searchEdges(2, 3));

    }

    @Test
    public void testRemoveEdgeOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge("edge1", 3, 2);
        graph.removeEdge(3, 2);
        assertFalse(graph.searchEdges(3, 2));


    }

    @Test
    public void testRemoveEdgeNotOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.insertEdge("edge1", 3, 2);
        graph.removeEdge(3, 2);
        assertFalse(graph.searchEdges(3, 2) && graph.searchEdges(2, 3));


    }

    @Test 
    public void testRemoveVertex() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(2);
        graph.removeVertex(2);
        
        assertFalse(graph.searchVertex(2));

    }

    @Test
    public void testGraphNotOriented(){
        Graph<Integer, Float> graph = new Graph<>(false);

        assertFalse(graph.isOriented());
    }

    @Test
    public void testGraphOriented(){
        Graph<Integer, Float> graph = new Graph<>(true);

        assertTrue(graph.isOriented());
    }

    @Test 
    public void testSearchVertex() throws GraphException{
        Graph<Integer, Float> graph = new Graph<>(true);
        graph.insertVertex(3);

        assertTrue(graph.searchVertex(3));
        
    }

    @Test 
    public void testSearchVertexNoVertex() throws GraphException{
        Graph<Integer, Float> graph = new Graph<>(true);
        //graph.insertVertex(3);

        assertFalse(graph.searchVertex(3));
        
    }

    @Test
    public void testSearchEdges() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertEdge("edge", 3, 5);
        
        assertTrue(graph.searchEdges(3, 5));
    
    }

    @Test
    public void testSearchNoEdges() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        
        assertFalse(graph.searchEdges(3, 5));
    
    }

    @Test
    public void testGetVertexesNumber() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertVertex(7);

        assertTrue(graph.getVertexesNumber()==4);


    }
   
    @Test
    public void testGetVertexesNumberWrong() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);

        assertFalse(graph.getVertexesNumber()==4);


    }

    @Test
    public void testGetEdgesNumberNotOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertVertex(10);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);



        assertTrue(graph.getEdgesNumber()==4);
    }

    @Test
    public void testEdgesNumberOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertVertex(10);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);
        graph.insertEdge("edge3", 6, 3);



        assertTrue(graph.getEdgesNumber()==3);
    }

    @Test
    public void testGetVertexesOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);
        graph.insertEdge("edge3", 6, 3);


        final List<Integer> expectedList = new LinkedList<>();
        expectedList.add(3);
        expectedList.add(5);
        expectedList.add(6);

        assertTrue(graph.getVertexes().equals(expectedList));

    }

    @Test
    public void testGetEdges() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);
        graph.insertEdge("edge3", 6, 3);

        boolean flag = true;

        HashMap<Integer, String> nestedMapforVertex3 = new HashMap<>();
        HashMap<Integer, String> nestedMapforVertex6 = new HashMap<>();

        nestedMapforVertex3.put(5, "edge1");     //v1 = 3
        nestedMapforVertex3.put(6, "edge2");     //v1 = 3
        
        nestedMapforVertex6.put(3, "edge3");     //v2 = 6


        Map<Integer, Map<Integer, String>> expected = graph.getEdges();

        if( !  ( expected.get(3).equals(nestedMapforVertex3)   )     ){
            flag = false;
        }
        if( !  ( expected.get(6).equals(nestedMapforVertex6)   )     ){
            flag = false;
        }

        assertTrue(flag);
    }

    @Test
    public void testGetNeighboursOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);
        graph.insertEdge("edge3", 6, 3);

        List<Integer> vertex3neighbors = new LinkedList<>();
        vertex3neighbors.add(5);
        vertex3neighbors.add(6);

        assertTrue(vertex3neighbors.equals(graph.getNeighbours(3)));        

         
    }

    @Test
    public void testGetNeighboursNotOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);



        List<Integer> vertex3neighbors = new LinkedList<>();
        vertex3neighbors.add(5);
        vertex3neighbors.add(6);

        assertTrue(vertex3neighbors.equals(graph.getNeighbours(3)));        

         
    }

    @Test
    public void testGetNeighboursAlone() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);

        graph.insertEdge("edge", 5, 6);
        
        List<Integer> neighbours = graph.getNeighbours(3);
        assertTrue(neighbours.isEmpty());   
    }

    @Test (expected = GraphException.class)
    public void testGetNeighboursNoVertex() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);

        graph.insertEdge("edge", 5, 6);
        
        graph.getNeighbours(null);
    }

    @Test
    public void testGetLabelOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(true);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);
        graph.insertEdge("edge3", 6, 3);


        assertTrue(graph.getLabel(3, 5).equals("edge1"));
    }

    @Test
    public void testGetLabelNotOriented() throws GraphException{
        Graph<Integer, String> graph = new Graph<>(false);
        graph.insertVertex(3);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertEdge("edge1", 3, 5);
        graph.insertEdge("edge2", 3, 6);


        assertTrue(graph.getLabel(6, 3).equals("edge2"));
    }

}