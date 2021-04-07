import java.io.ObjectInputStream;

public class Edge implements Cloneable{
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
        flow += bottleNeckCapacity;
        residualEdge.flow -= bottleNeckCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void resetEdge(){
        flow = 0;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public String toString() {
        if (this.capacity == 0)
            return  String.format("REdge: %2s --%3d /%3d--> %2s", startNode, flow, capacity, endNode);
        else
            return  String.format("Edge : %2s --%3d /%3d--> %2s", startNode, flow, capacity, endNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (getStartNode() != edge.getStartNode()) return false;
        return getEndNode() == edge.getEndNode();
    }

    @Override
    public int hashCode() {
        int result = getStartNode();
        result = 31 * result + getEndNode();
        return result;
    }
}
