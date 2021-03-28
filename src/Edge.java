public class Edge {
    private final int startNode, endNode, capacity;
    private int flow;
    public Edge residualEdge;

    public Edge(int startNode, int endNode, int capacity) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.capacity = capacity;
    }

    public int capacityLeft(){
        return this.capacity - this.flow;
    }

    public void increaseByBottleNeckCapacity(int bottleNeckCapacity){
        this.flow += bottleNeckCapacity;
        this.residualEdge.flow -= bottleNeckCapacity;
    }

    @Override
    public String toString() {
        return  String.format("Edge : %2s ---- %3d/%3d ----> %2s", startNode, flow, capacity, endNode);
    }
}
