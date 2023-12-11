import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "prompt6.txt";
        BufferedReader r = new BufferedReader(new FileReader(filename));
        Scanner line;

        int[] times = new int[4];
        int[] distances = new int[4];

        line = new Scanner(r.readLine());
        line.next();
        for (int i = 0; i < 4; i++) {
            times[i] = line.nextInt();
        }
        line.close();

        line = new Scanner(r.readLine());
        line.next();
        for (int i = 0; i < 4; i++) {
            distances[i] = line.nextInt();
        }
        line.close();
        r.close();

        int ans1 = 1;
        int numWays;
        long time;
        long distance;
        boolean checkOne = false;

        for (int i = 0; i < 4; i++) {
            numWays = 0;
            time = times[i];
            distance = distances[i];

            for (int j = 0; j < time; j++) {
                if (j * (time - j) > distance) {
                    numWays++;
                }
            }
            if (numWays == 1) {
                checkOne = true;
            }
            ans1 *= numWays;
        }

        if (!checkOne && ans1 == 1) {
            System.out.println("0");
        } else {
            System.out.println(ans1);
        }

        time = 0;
        for (int i = 0; i < 4; i++) {
            time += times[i] * Math.pow(10, 6 - 2 * i);
        }

        distance = 0;
        for (int i = 0; i < 4; i++) {
            distance += distances[i] * Math.pow(10, 12 - 4 * i);
        }

        long criticalPoint = -1;
        long logicNum;
        for (long i = 0; i < time; i++) {
            logicNum = i * (time - i);
            if (logicNum > distance) {
                criticalPoint = i;
                break;
            }
        }

        long ans2 = 0;
        if (criticalPoint != -1) {
            ans2 = (time - (2 * criticalPoint) + 1);
        }
        System.out.println(ans2);

    }
}
