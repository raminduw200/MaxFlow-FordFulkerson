public class Edge {
    private final int startNode, endNode, capacity;
    private int flow;
    public Edge residualEdge;

    public Edge(int startNode, int endNode, int capacity) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.capacity = capacity;
        this.flow = 0;
    }

    /*
    on the forward edge bottle neck capacity will be added if an augmenting path exists.
    on the residual edge bottle neck capacity will be deducted if an augmenting path exists.
     */
    public void increaseByBottleNeckCapacity(int bottleNeckCapacity){
        this.flow += bottleNeckCapacity;
        this.residualEdge.flow -= bottleNeckCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    @Override
    public String toString() {
        return  String.format("Edge : %2s ----%3d /%3d----> %2s", startNode, flow, capacity, endNode);
    }
}
