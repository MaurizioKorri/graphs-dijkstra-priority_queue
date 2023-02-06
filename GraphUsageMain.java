import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import DijkstraAlgorithm.DijkstraAlgo;
import DijkstraAlgorithm.Vertex;
import GraphLib.Graph;

import static java.lang.Float.parseFloat;


public class GraphUsageMain {
    public static  void main(String[] args) throws FileNotFoundException {

        Graph<String,Float> graph = new Graph<>(false);

        String fromCity = null;
        String toCity = null;
        float distance = 0;

        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader("C:\\Users\\korri\\Desktop\\ASD\\progettoASD21_22\\laboratorio-algoritmi-2021-2022\\es4\\es4\\italian_dist_graph.csv"));
            String line = reader.readLine();

            while(line != null){
                String[] tokens = line.split(",");
                
                fromCity = tokens[0];
                toCity = tokens[1];
                distance = parseFloat(tokens[2]);
                graph.insertVertex(fromCity);
                graph.insertVertex(toCity);
                graph.insertEdge(distance,fromCity,toCity);

                line = reader.readLine();

            }
            reader.close();
        } catch( Exception e){e.printStackTrace();}

        System.out.println("Graph filled");

        try{

            HashMap<String, Vertex> minimumSpanningTrees = DijkstraAlgo.findShortestPath(graph, "torino");
            System.out.println("Minimum path from torino to catania :"+ minimumSpanningTrees.get("catania").DijkstraDistance/1000 + "km");
            System.out.println(DijkstraAlgo.minPath(minimumSpanningTrees, "catania"));
        
        } catch (Exception e) { e.printStackTrace();}
    }

}
