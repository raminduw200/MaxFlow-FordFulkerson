package coursera;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final int numVertices;
    private List<Edge>[] graph;

    public Network(int numVertices) {
        this.numVertices = numVertices;
        initializeEmptyGraph();
    }

    public void initializeEmptyGraph() {
        // initialize the size of the first list.
        graph = new List[numVertices];
        for (int i = 0; i < numVertices; i++){
            // initialize an empty array lists inside the graph array.
            graph[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(Edge edge){
        int start = edge.getStart();
        int end = edge.getEnd();
        /*
        Adding edges to the graph where the graph looks,
                   [
        0th index  -> [startingEdge (start --- flow/capacity ---> end), (other neighbour edges to starting node...)],
        1st index  -> [1 Edge (1  --- flow/capacity ---> end), (other neighbour edges to 1 node...)],
                     ...
                     ...
        nth index    -> [endingEdge (end -- flow/capacity --> start), (other neighbour edges to ending node...)]
                   ]
        in this List each index has edges which are starting from the index. 1st index has all neighbour nodes which
        are connected to the 1st node. Those are saved as edges.
         */
        graph[start].add(edge);
        graph[end].add(edge);
    }

    /*
     Since using breadth first search need to take all nodes which are connected to a node.
     This will iterate through all connected nodes.
     */
    public Iterable<Edge> iterate(int start){
        return graph[start];
    }

    public int getNumVertices() {
        return numVertices;
    }
}
