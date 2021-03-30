package coursera;

public class Edge {
    private final int start, end;
    private final int capacity;
    private int flow;

    public Edge(int start, int end, int capacity) {
        this.start = start;
        this.end = end;
        this.capacity = capacity;
    }

    public int residualCapacityTo(int node){
        if (node == start) return flow;
        else if (node == end) return capacity - flow;
        throw new IllegalArgumentException();
    }

    public void addResidualFlowTo(int node, int flow){
        if (node == start) this.flow -= flow;
        else if (node == end) this.flow += flow;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }
}
