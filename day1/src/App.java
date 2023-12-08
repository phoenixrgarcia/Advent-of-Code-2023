import java.io.BufferedReader;
import java.io.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "prompt1.txt";
        String[] stringNums = new String[] { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        int firstNumIndex = 0;
        int firstNumIs = 0;
        int firstStringIndex = 0;
        int firstStringIs = 0;
        int lastNumIndex = 0;
        int lastNumIs = 0;
        int lastStringIndex = 0;
        int lastStringIs = 0;
        int sum = 0;

        BufferedReader r = new BufferedReader(new FileReader(filename));

        String line = r.readLine();
        while (line != null) {
            firstNumIndex = 999;
            firstStringIndex = 999;
            lastNumIndex = -1;
            lastStringIndex = -1;

            for (int i = 1; i < 10; i++) {
                if (line.indexOf(String.valueOf(i)) < firstNumIndex && line.indexOf(String.valueOf(i)) != -1) {
                    firstNumIndex = line.indexOf(String.valueOf(i));
                    firstNumIs = i;
                }
                if (line.lastIndexOf(String.valueOf(i)) > lastNumIndex) {
                    lastNumIndex = line.lastIndexOf(String.valueOf(i));
                    lastNumIs = i;
                }
                if (line.indexOf(stringNums[i - 1]) < firstStringIndex && line.indexOf(stringNums[i - 1]) != -1) {
                    firstStringIndex = line.indexOf(stringNums[i - 1]);
                    firstStringIs = i;
                }
                if (line.lastIndexOf(stringNums[i - 1]) > lastStringIndex) {
                    lastStringIndex = line.lastIndexOf(stringNums[i - 1]);
                    lastStringIs = i;
                }
            }
            if (firstNumIndex < firstStringIndex) {
                sum += 10 * firstNumIs;
            } else {
                sum += 10 * firstStringIs;
            }
            if (lastNumIndex > lastStringIndex) {
                sum += lastNumIs;
            } else {
                sum += lastStringIs;
            }
            line = r.readLine();
        }
        System.out.println(sum);
        r.close();
    }
}
