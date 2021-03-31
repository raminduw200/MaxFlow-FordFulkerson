package coursera;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkersonMaxFlow {
    private boolean[] visited;
    private Edge[] augmentingPaths;
    private int flow;

    public FordFulkersonMaxFlow(Network graph, int source, int target) {
        flow = 0;
        while(breadthFirstSearch(graph, source, target)){
            /*
              initialize the bottle neck value to maximum since we are searching for the minimum
              flow the augmenting path.
              First loop will go through the augmenting path and update the bottle neck value of
              the that path.
             */
            int bottleNeck = Integer.MAX_VALUE;
            for (int v = target; v != source; v = augmentingPaths[v].neighbour(v)){
                bottleNeck = Math.min(bottleNeck, augmentingPaths[v].residualCapacity(v));
            }

            /*
            This will update flow of each edges on the augmenting path from the above calculated
            bottle neck value.
             */
            for (int v = target; v != source; v = augmentingPaths[v].neighbour(v)) {
                augmentingPaths[v].addBottleNeck(v, bottleNeck);
            }

            //  maximum flow is the sum of the all bottle neck values of the all augmenting paths.
            flow += bottleNeck;
        }
    }

    /*
    Breath First Search will return if there is an augmenting path connect from source to target.
     */
    private boolean breadthFirstSearch(Network graph, int source, int target){
        augmentingPaths = new Edge[graph.getNumVertices()];
        visited = new boolean[graph.getNumVertices()];          //  This will keep track of visited nodes.

        Queue<Integer> queue = new LinkedList<>();              //  Queue data structure used BFS.
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()){
            int start = queue.remove();

            // This will iterate through all connected nodes from the node: start.
            for (Edge edge : graph.iterate(start)){
                int end = edge.neighbour(start);

                /*
                According to the Skew Symmetry of a flow in a flow network, f(u, v) = -f(v, u).
                To stop the recursion of a flow, these conditions being checked.
                While selecting a path recursion can be happen as below,
                            node 1 ---> node 2 ---> node 1 ---> node 2
                this cause stack overflow.
                To avoid that below conditions being checked.
                 */
                if (edge.residualCapacity(end) > 0 && !visited[end]){
                    augmentingPaths[end] = edge;
                    visited[end] = true;
                    queue.add(end);
                }
            }
        }
        return visited[target];     //  If the target node is visited that mean there is a path from source to target.
    }

    public int maxFlow(){
        return flow;
    }

}
