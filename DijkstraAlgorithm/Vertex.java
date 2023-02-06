package DijkstraAlgorithm;

public class Vertex<T> {
    /*the vertex visited before in the path */
    public T parent;
    /*minimum distance from the source */
    public float DijkstraDistance;
    public T item;


    public Vertex(T item) {
        this.item = item;
        this.DijkstraDistance = Float.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "Vertex {" +
                " item = " + item +
                ", distance = "+ DijkstraDistance +
                '}';
    }

}

