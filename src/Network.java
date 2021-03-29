import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int source, target, numVertices;
    private int maxFlow;
    private boolean[] visited;
    // private Edge[][] graph   Can't instantiate like this since the we need something like inside
    // array is type of Edge list. Something like [[type of these arrays are Edge], [], [], []]
    private List<Edge>[] graph;

    public Graph(int source, int target, int numVertices){
        this.source = source;
        this.target = target;
        this.numVertices = numVertices;
        initializeEmptyGraph();
        this.visited = new boolean[numVertices];
    }

    private void initializeEmptyGraph(){
        for (int i = 0; i < numVertices; i++){
            this.graph[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(int start, int end, int capacity){
        /*
        Each edge has a residual edge. Because of that creates another edge
        parallel to the adding edge which the residual edge.
        Residual edge capacity is zero at the beginning.
        If there is a flow on the edge, that will be the capacity of residual edge.
         */
        Edge edge1 = new Edge(start, end, capacity);
        Edge edge2 = new Edge(end, start, 0);

        edge1.residualEdge = edge2;
        edge2.residualEdge = edge1;

        /*
        Adding edges to the graph where the graph looks,
                    [
            start -> [startingEdge (start --- flow/capacity ---> end), (other neighbour edges to starting node...)],
                     ...
                     ...
            end   -> [endingEdge (end -- flow/capacity --> start), (other neighbour edges to ending node...)]
         */
        graph[start].add(edge1);
        graph[end].add(edge2);
    }

    public List<Edge>[] getGraph() {
        return graph;
    }
}
