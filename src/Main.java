import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int j = 1; j < 10; j++) {
            InputReader fileReader = new InputReader(String.format("bridge_%s.txt", j));
            List<Integer> inputs = fileReader.getIntegerEdgesList();

            int n = fileReader.getNumVertices();
            int s = 0;
            int t = n - 1;

            FordFulkersonMaxFlow solve = new FordFulkersonMaxFlow(s, t, n);
            for (int i = 0; i < inputs.size(); i += 3)
                solve.addEdge(inputs.get(i), inputs.get(i + 1), inputs.get(i + 2));

//        for (List edges: solve.graph)
//            System.out.println(edges);

            System.out.println("Max Flow is : " + solve.getMaxFlow());
        }
    }
}
