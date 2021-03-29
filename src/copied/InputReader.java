package copied;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
    private int numVertices;
    private List<Integer> integerEdgesList;

    public InputReader(String fileName) {
        try {
            readFile(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File("benchmarks/" + fileName));

        integerEdgesList = new ArrayList<Integer>();
        while (s.hasNext()){
            integerEdgesList.add(Integer.valueOf(s.next()));
        }
        s.close();

        numVertices = integerEdgesList.remove(0);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public List<Integer> getIntegerEdgesList() {
        return integerEdgesList;
    }
}
