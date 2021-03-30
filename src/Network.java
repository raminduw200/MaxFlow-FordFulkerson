import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Network {
    protected final int source, target, numVertices;
    public boolean[] visited;
    // protected Edge[][] graph   Can't instantiate like this since  we need something like inside
    // array is type of Edge list. Something like [[type of these arrays are Edge], [], [], []]
    protected List<Edge>[] graph;

    public Network(int source, int target, int numVertices){
        this.source = source;
        this.target = target;
        this.numVertices = numVertices;
        initializeEmptyGraph();
    }

    private void initializeEmptyGraph(){
        graph = new List[numVertices];          // initialize the size of the first list.
        visited = new boolean[numVertices];     // initialize the size of the visited node array.
        for (int i = 0; i < numVertices; i++){
            // initialize an empty array lists inside the graph array.
            graph[i] = new LinkedList<Edge>();

            // initializing all nodes as not visited.
            visited[i] = false;
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
        0th index  -> [startingEdge (start --- flow/capacity ---> end), (other neighbour edges to starting node...)],
        1st index  -> [1 Edge (1  --- flow/capacity ---> end), (other neighbour edges to 1 node...)],
                     ...
                     ...
        nth index    -> [endingEdge (end -- flow/capacity --> start), (other neighbour edges to ending node...)]
                   ]
        in this List each index has edges which are starting from the index. 1st index has all neighbour nodes which
        are connected to the 1st node. Those are saved as edges.
         */
        graph[start].add(edge1);
        graph[end].add(edge2);
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }
}
