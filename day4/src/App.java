import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {

    public static int increaseScore(int currScore) {
        return currScore == 0 ? 1 : currScore * 2;
    }

    public static void main(String[] args) throws Exception {
        /*
         * Assumptions
         * -------
         * Winning numbers are only included once
         * format stays the same
         */

        String filename = "prompt4.txt";
        BufferedReader r = new BufferedReader(new FileReader(filename));
        String line;
        String[] lineStrings;

        Scanner left;
        int leftNum;
        Scanner right;
        int rightNum;

        int cardSum;
        int totalSum = 0;

        while ((line = r.readLine()) != null) { // reads every card

            cardSum = 0;
            lineStrings = line.split(": |\\| ");
            left = new Scanner(lineStrings[1]);

            while (left.hasNextInt()) { // goes through every number we have

                leftNum = left.nextInt();
                right = new Scanner(lineStrings[2]);

                while (right.hasNextInt()) { // checks number we have against every winning number
                    rightNum = right.nextInt();
                    if (leftNum == rightNum) {
                        cardSum = increaseScore(cardSum);
                        break;
                    }
                }
            }
            totalSum += cardSum;
        }

        System.out.println(totalSum);

    }
}
