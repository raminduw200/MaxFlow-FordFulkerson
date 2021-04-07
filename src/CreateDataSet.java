import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateDataSet {
    private static int numVertices = 4;
    private static int fileNo = 1;
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {
        Random rand = new Random();
        while (true) {
            numVertices -=1;
            for (int i = 0; i < numVertices; i++) {
                int randomCapacity = rand.nextInt((numVertices + 1) + 1) + 1;
                int startNode = 0;
                int endNode = rand.nextInt((numVertices) + 1);

                if (startNode != endNode) {
                    Edge edge = new Edge(startNode, endNode, randomCapacity);
                    if (!edges.contains(edge))
                        edges.add(edge);
                }

            }

            for (int i = 0; i < numVertices; i++) {
                int randomCapacity = rand.nextInt((numVertices + 1) + 1) + 1;
                int startNode = rand.nextInt((numVertices) + 1);
                int endNode = numVertices;

                if (startNode != endNode) {
                    Edge edge = new Edge(startNode, endNode, randomCapacity);
                    if (!edges.contains(edge))
                        edges.add(edge);
                }

            }

            for (int i = 0; i < numVertices * rand.nextInt((50) + 1); i++) {
                int randomCapacity = rand.nextInt((numVertices + 1) + 1) + 1;
                int startNode = rand.nextInt((numVertices) + 1);
                int endNode = rand.nextInt((numVertices) + 1);

                if (startNode != endNode) {
                    Edge edge = new Edge(startNode, endNode, randomCapacity);
                    if (!edges.contains(edge))
                        edges.add(edge);
                }

            }

            try {
                FileWriter myWriter = new FileWriter(String.format("benchmarks/test/Test_%s.txt", fileNo));
                myWriter.write(numVertices+1 + "\n");
                for (Edge e : edges)
                    myWriter.write(String.format("%s %s %s\n", e.getStartNode(), e.getEndNode(), e.getCapacity()));
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            numVertices = (numVertices + 1) * 2;
            fileNo += 1;

            if (fileNo == 10)
                break;
        }
    }
}
