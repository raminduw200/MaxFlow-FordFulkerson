import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private int numVertices;
    private List<Integer> integerEdgesList;



    public void readFile() throws FileNotFoundException {
        Scanner s = new Scanner(new File("benchmarks/bridge_1.txt"));

        integerEdgesList = new ArrayList<Integer>();
        while (s.hasNext()){
            integerEdgesList.add(Integer.valueOf(s.next()));
        }
        s.close();
    }
}
