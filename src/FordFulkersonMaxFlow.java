import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FordFulkersonMaxFlow extends Network{
    private int maxFlow;
    private final boolean showWork;
    private ArrayList<Edge> augmentingPaths;

    public FordFulkersonMaxFlow(int source, int target, int numVertices, boolean showWork) {
        super(source, target, numVertices);
        this.showWork = showWork;
        this.augmentingPaths = new ArrayList<Edge>();
    }

    public int getMaxFlow(){
        for(int flow = depthFirstSearch(getSource(), Integer.MAX_VALUE);
            flow != 0;
            flow = depthFirstSearch(getSource(), Integer.MAX_VALUE)) {
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
            /*
            According to the Skew Symmetry of a flow in a flow network, f(u, v) = -f(v, u).
            To stop the recursion of a flow, these conditions being checked.
            While selecting a path recursion can be happen as below,
                        node 1 ---> node 2 ---> node 1 ---> node 2
            this cause stack overflow.
            To avoid that below conditions being checked.
            Remaining capacity can be negative for residual edges since the residual edge capacity is 0.
             */
            if (remainingCapacity > 0 && !visited[edge.getEndNode()]){
                // passing flow and remaining capacity recursively to get the bottle neck value of a path if exists.
                int bottleNeckCapacity = depthFirstSearch(edge.getEndNode(), Math.min(flow, remainingCapacity));

                if (bottleNeckCapacity > 0){
                    edge.increaseByBottleNeckCapacity(bottleNeckCapacity);
                    if (showWork) {
                        Edge copyEdge = null;
                        try {
                            copyEdge = (Edge)edge.clone();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        augmentingPaths.add(copyEdge);
                    }
                    return bottleNeckCapacity;
                }
            }
        }
        // if there is no augmenting path return zero.
        return 0;
    }

    public void showWork(){
        if (showWork){
            System.out.println("\n\n----------Solving process---------");
            for (int i = augmentingPaths.size()-1; i != -1; i--) {
                Edge edge = augmentingPaths.get(i);
                System.out.print("  " + edge);
                if (edge.getEndNode() == target) {
                    System.out.println();
                }
            }
        }
    }

    @Override
    public void resetGraph(){
        super.resetGraph();
        augmentingPaths.removeAll(augmentingPaths);
        maxFlow = 0;
    }

}
