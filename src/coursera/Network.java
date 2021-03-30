package coursera;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final int numVertices;
    private List<Edge>[] graph;

    public Network(int numVertices) {
        this.numVertices = numVertices;
        initializeGraph();
    }

    public void initializeGraph(){
        graph = new List[numVertices];
        for (int i = 0; i < numVertices; i++){
            graph[i] = new ArrayList<Edge>();
        }
    }

    public void addEdge(Edge edge){
        int start = edge.getStart();
        int end = edge.getEnd();
        graph[start].add(edge);
        graph[end].add(edge);
    }

    public Iterable<Edge> graph(int start){
        return graph[start];
    }
}
