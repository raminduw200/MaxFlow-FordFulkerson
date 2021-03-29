import java.util.Arrays;
import java.util.List;

public class FordFulkersonMaxFlow extends Network{
    private static int source, target, numVertices;
    private int maxFlow;

    public FordFulkersonMaxFlow(int source, int target, int numVertices) {
        super(source, target, numVertices);
    }

    public int getMaxFlow(){
        for(int flow = depthFirstSearch(source, Integer.MAX_VALUE); flow != 0; flow = depthFirstSearch(source, Integer.MAX_VALUE)){
            Arrays.fill(visited, false);
            maxFlow += flow;
        }
        return maxFlow;
    }

    private int depthFirstSearch(int endNode, int flow){
        if(endNode == target){
            return flow;
        }

        List<Edge> edgesList = graph[endNode];
        visited[endNode] = true;

        for (Edge edge : edgesList){
            int remainingCapacity = edge.getCapacity() - edge.getFlow();
            // remaining capacity can be negative for residual edges since the residual edge capacity is 0.
            if (remainingCapacity > 0 && !visited[endNode]){
                // passing flow and remaining capacity recursively to get the bottle neck value of a path if exists.
                int bottleNeckCapacity = depthFirstSearch(edge.getEndNode(), Math.min(flow, remainingCapacity));

                if (bottleNeckCapacity > 0){
                    edge.increaseByBottleNeckCapacity(bottleNeckCapacity);
                    return bottleNeckCapacity;
                }
            }
        }
        // if there is no augmenting path return zero.
        return 0;
    }


}
