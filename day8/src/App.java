import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // create map of "AAAL", "AAAR" and the destinations
        // create file reader to read instructions
        // create for loop to go till we get to ZZZ

        String line;
        String[] lineStrings;
        String key;
        String left;
        String right;

        String currentNode;
        int nextDirection;
        int steps = 0;

        Map<String, String> nodes = new HashMap<String, String>();
        BufferedReader r = new BufferedReader(new FileReader("prompt8.txt"));

        r.readLine();
        r.readLine();
        while ((line = r.readLine()) != null) {
            lineStrings = line.split(" = \\(|, |\\)", -1);
            key = lineStrings[0];
            left = lineStrings[1];
            right = lineStrings[2];

            nodes.put(key + "L", left);
            nodes.put(key + "R", right);
        }
        r.close();

        r = new BufferedReader(new FileReader("prompt8.txt"));
        currentNode = "AAA";
        nextDirection = r.read();
        while (true) {
            // if reached the end of the instructions
            if ((char) (nextDirection) != 'R' && (char) (nextDirection) != 'L') {
                r.close();
                r = new BufferedReader(new FileReader("prompt8.txt"));
                nextDirection = r.read();
            }
            currentNode = nodes.get(currentNode + (char) nextDirection);
            steps++;
            nextDirection = r.read();

            if (currentNode.equals("ZZZ")) {
                break;
            }
        }
        System.out.println(steps);
    }
}
