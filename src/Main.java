import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    private static int n, s, t;
    private static final Scanner sc = new Scanner(System.in);
    private static FordFulkersonMaxFlow solve;

    private static final boolean showFlow = false;
    private static final boolean showGraph = false;
    private static final boolean showOptions = false;
    private static final String datasetName = "ladder";

    public static void main(String[] args) {

        System.out.println(String.format("|%10s|%10s|%20s|%10s|", "Dataset", "Nodes", "Time (Nano Seconds)", "Max Flow"));
        System.out.println("|----------+----------+--------------------+----------|");

        for (int j = 1; j < 10; j++) {
            //  Read the inputs from the file.
            InputReader fileReader = new InputReader(String.format(datasetName + "_%s.txt", j));
            List<Integer> inputs = fileReader.getIntegerEdgesList();

            //  Initializing number of nodes, Source and Target
            n = fileReader.getNumVertices();
            s = 0;
            t = n - 1;

            //  Initializing the graph by adding edges.
            solve = new FordFulkersonMaxFlow(s, t, n, showFlow);
            for (int i = 0; i < inputs.size(); i += 3)
                solve.addEdge(inputs.get(i), inputs.get(i + 1), inputs.get(i + 2));


            long startTime = System.nanoTime();
            int answer = solve.getMaxFlow();
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println(String.format("|%10s|%10s|%20s|%10s|", datasetName + j, n, totalTime, answer));

            solve.showWork();
            solve.printGraph(showGraph);
        }

        if (showOptions) {
            while (true) {
                promptInfo();
                int option = intInput();
                int[] startEndNodes = startEndNode();
                int start = startEndNodes[0];
                int end = startEndNodes[1];

                while (start < s || end > t) {
                    System.out.println("Start node must be greater than source and end node must be less than target node");
                    startEndNodes = startEndNode();
                    start = startEndNodes[0];
                    end = startEndNodes[1];
                }

                if (option == 1) {
                    System.out.println("Capacity : ");
                    int capacity = intInput();
                    solve.addEdge(start, end, capacity);
                } else if (option == 2) {
                    if (solve.deleteEdge(start, end)) {
                        System.out.println("Edge deleted.");
                    } else {
                        System.out.println("Edge doesn't exists");
                    }
                } else if (option == 3) {
                    Edge edge = solve.searchEdge(start, end);
                    if (edge != null)
                        System.out.println(edge);
                    else
                        System.out.println("Edge does not exists.");
                } else if (option == -1) {
                    break;
                } else {
                    System.out.println("Please select option 1 or 2 or 3.");
                    option = intInput();
                }
                solve.resetGraph();
                System.out.println("Max flow : " + solve.getMaxFlow());

                solve.showWork();
                solve.printGraph(showGraph);
            }
        }
    }

    private static int intInput(){
        while (!sc.hasNextInt()) {
            System.out.println("Please enter option 1 or 2 or 3.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static int[] startEndNode() {
        int start;
        int end;
        System.out.println("Start Node : ");
        start = intInput();
        System.out.println("End Node : ");
        end = intInput();
        return new int[]{start, end};
    }

    private static void promptInfo(){
        System.out.println("\n\nOptions :\n" +
                String.format("\t1.%-20s  1 ", "Add Edge") +
                String.format("\n\t2.%-20s  2", "Delete Edge") +
                String.format("\n\t3.%-20s  3", "Search Edge") +
                String.format("\n\t4.%-19s  -1", "Exit")
        );
    }
}
