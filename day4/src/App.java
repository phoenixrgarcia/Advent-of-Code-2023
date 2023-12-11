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

        int cardSum; // points this card gets
        int totalSum = 0; // points of 196 cards
        int numMatches; // number of matching numbers per card
        int numCard = 0; // what number of card we are on (-1) 0-195; Will act as index in our table

        int[] numMatchesTable = new int[196]; // table of how many matches per card
        int[] numCardsWonTable = new int[196]; // table of how many cards each card wins (ex: [195] = 0 cards won; [1] =
                                               // 50432)
        int ans2 = 0;

        while ((line = r.readLine()) != null) { // reads every card

            numMatches = 0;
            cardSum = 0;
            lineStrings = line.split(": |\\| ");
            left = new Scanner(lineStrings[1]);

            while (left.hasNextInt()) { // goes through every number we have

                leftNum = left.nextInt();
                right = new Scanner(lineStrings[2]);

                while (right.hasNextInt()) { // checks number we have against every winning number
                    rightNum = right.nextInt();
                    if (leftNum == rightNum) {
                        numMatches += 1;
                        cardSum = increaseScore(cardSum);
                        break;
                    }
                }
            }
            totalSum += cardSum;

            numMatchesTable[numCard] = numMatches; // assigns the number of matches to the table
            numCard += 1;
        }

        for (int i = 195; i >= 0; i--) {
            for (int j = numMatchesTable[i]; j > 0; j--) {
                numCardsWonTable[i] += 1;
                if (i + j < 195 && i + j > 0) {
                    numCardsWonTable[i] += numCardsWonTable[i + j]; // adds total 'cards' to table
                }
            }
            ans2 += numCardsWonTable[i];
        }
        ans2 += 196;

        System.out.println(totalSum);
        System.out.println(ans2);
        r.close();
    }
}
