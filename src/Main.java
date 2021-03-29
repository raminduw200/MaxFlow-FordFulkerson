import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputReader fileReader = new InputReader("bridge_1.txt");
        List<Integer> inputs = fileReader.getIntegerEdgesList();

        int n = fileReader.getNumVertices();
        int s = 0;
        int t = n-1;

        FordFulkersonMaxFlow solve = new FordFulkersonMaxFlow(s, t, n);
        for (int i = 0; i < inputs.size(); i +=3)
            solve.addEdge(inputs.get(i), inputs.get(i+1), inputs.get(i+2));

//        System.out.println(Arrays.toString(graph));
        for (List edges: solve.graph)
            System.out.println(edges);

        System.out.println("Max Flow is : " + solve.getMaxFlow());
    }
}
