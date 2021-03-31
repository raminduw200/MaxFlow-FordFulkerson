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

    /*
    end     --->    start (Backward Edge)
    start   --->    end (Forward Edge)
     */
    public int residualCapacity(int node) {
        if (node == start) {
            return flow;                //  Backward Edge
        } else if (node == end) {
            return capacity - flow;     //  Forward Edge
        }
        throw new IllegalArgumentException("Invalid node.");
    }

    public void addBottleNeck(int node, int bottleNeck) {
        /*
        on the forward edge bottle neck capacity will be added if an augmenting path exists.
        on the backward edge bottle neck capacity will be deducted if an augmenting path exists.
        */
        if (node == start) {            //  Backward Edge
            this.flow -= bottleNeck;
        } else if (node == end) {       //  Forward Edge
            this.flow += bottleNeck;
        }
    }

    // Return the connected node.
    public int neighbour(int node) {
        if (node == start) {            //  Backward Edge
            return end;
        }
        else if (node == end) {         //  Forward Edge
            return start;
        }
        else throw new IllegalArgumentException("Invalid node.");
    }

    // ------------- Getters -------------
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getCapacity() {
        return capacity;
    }

    // ------------- toString -------------
    @Override
    public String toString() {
        return  String.format("Edge : %2s ----%3d /%3d----> %2s", start, flow, capacity, end);
    }
}
