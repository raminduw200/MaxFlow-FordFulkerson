package coursera;

import java.util.Arrays;
import java.util.List;

/**********************************************************************************
 * Name     :   Ramindu Walgama
 * UOW ID   :   w1790183
 * IIT ID   :   2019730
 *
 * Description : This is the implementation of Ford Fulkerson Maximum Flow Problem
 *               Algorithm using Breadth First Search (BFS). Uses Queue data
 *               structure to implement the BFS.
 * References  : Algorithms fourth edition by Robert SedgeWick and Kevin Wayne
 *               https://www.coursera.org/learn/algorithms-part1
 *               https://www.coursera.org/learn/algorithms-part2
 **********************************************************************************/

public class Main {
    public static void main(String[] args) {
        for (int j = 1; j < 10; j++) {
            //  Read the inputs from the file and assign to variables.
            InputReader fileReader = new InputReader(String.format("ladder_%s.txt", j));
            List<Integer> inputs = fileReader.getIntegerEdgesList();

            int n = fileReader.getNumVertices();
            int s = 0;
            int t = n - 1;

            Network network = new Network(n);

            //  Adding edges to the graph.
            for (int i = 0; i < inputs.size(); i += 3) {
                Edge edge = new Edge(inputs.get(i), inputs.get(i + 1), inputs.get(i + 2));
                network.addEdge(edge);
            }

//            for (List<Edge> edges : network.getGraph()){
//                System.out.println(edges);
//            }

            //  Solve the problem and return the answer.
            long startTime = System.currentTimeMillis();
            FordFulkersonMaxFlow solve = new FordFulkersonMaxFlow(network, s, t);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Time spend : " + totalTime);
            System.out.println("Max Flow is : " + solve.maxFlow());

//            for (List<Edge> edges : network.getGraph()){
//                    System.out.println(edges);
//            }
        }
    }
}
