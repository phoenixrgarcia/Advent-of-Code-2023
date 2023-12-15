import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class App {

    static void customBubbleSort(String[] arr, int n) { // inspired by geeks for geeks
        String temp;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (leftWinsHand(arr[j], arr[j + 1])) { // main change to bubble sort
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

    }

    private static boolean leftWinsHand(String leftHand, String rightHand) { // find if leftHand beats rightHand
        int leftType = getHandType(leftHand);
        int rightType = getHandType(rightHand);

        if (leftType > rightType) {
            return true;
        } else if (leftType == rightType) {
            return leftWinsHighCard(leftHand, rightHand);
        }
        return false;
    }

    private static boolean leftWinsHighCard(String leftHand, String rightHand) {
        int leftCard;
        int rightCard;
        for (int i = 0; i < 5; i++) {
            leftCard = normalizeCard(leftHand.charAt(i));
            rightCard = normalizeCard(rightHand.charAt(i));
            if (leftCard > rightCard) {
                return true;
            } else if (rightCard > leftCard) {
                return false;
            }
        }
        return false;
    }

    private static int normalizeCard(char charAt) {
        if (charAt == 'A') {
            return 14;
        } else if (charAt == 'K') {
            return 13;
        } else if (charAt == 'Q') {
            return 12;
        } else if (charAt == 'J') {
            return 11;
        } else if (charAt == 'T') {
            return 10;
        } else
            return Integer.parseInt(Character.toString(charAt));
    }

    private static int getHandType(String hand) {
        /*
         * returns:
         * 6: five of a kind
         * 5: four of a kind
         * 4: full house
         * 3: three of a kind
         * 2: two pair
         * 1: one pair
         * 0: high card
         */
        char c1 = hand.charAt(0);
        char c2 = hand.charAt(1);
        char c3 = hand.charAt(2);
        char c4 = hand.charAt(3);
        char c5 = hand.charAt(4);

        Map<Character, Integer> cardBuckets = new LinkedHashMap<Character, Integer>(5);
        cardBuckets.put(c1, (cardBuckets.getOrDefault(c1, 0) + 1));
        cardBuckets.put(c2, (cardBuckets.getOrDefault(c2, 0) + 1));
        cardBuckets.put(c3, (cardBuckets.getOrDefault(c3, 0) + 1));
        cardBuckets.put(c4, (cardBuckets.getOrDefault(c4, 0) + 1));
        cardBuckets.put(c5, (cardBuckets.getOrDefault(c5, 0) + 1));

        Set<Character> keys = cardBuckets.keySet();

        char[] charArr = new char[5];
        int i = 0;
        for (Character key : keys) {
            charArr[i] = key;
            i++;
        }
        c1 = charArr[0];
        c2 = charArr[1];
        c3 = charArr[2];
        c4 = charArr[3];
        c5 = charArr[4];

        if (cardBuckets.get(c1) == 5) {
            return 6;
        } else if (cardBuckets.get(c1) == 4 || cardBuckets.get(c2) == 4) {
            return 5;
        } else if (cardBuckets.get(c1) == 3 && cardBuckets.get(c2) == 2
                || cardBuckets.get(c1) == 2 && cardBuckets.get(c2) == 3) {
            return 4;
        } else if (cardBuckets.get(c1) == 3 || cardBuckets.get(c2) == 3 || cardBuckets.get(c3) == 3) {
            return 3;
        } else if (cardBuckets.get(c1) == 2 && cardBuckets.get(c2) == 2
                || cardBuckets.get(c1) == 2 && cardBuckets.get(c3) == 2
                || cardBuckets.get(c2) == 2 && cardBuckets.get(c3) == 2) {
            return 2;
        } else if (cardBuckets.get(c1) == 2 || cardBuckets.get(c2) == 2 || cardBuckets.get(c3) == 2
                || cardBuckets.get(c4) == 2) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        // read hand and points into map <string, int>
        // read hands into String[]
        // sort hands array
        // sum up

        String fileName = "prompt7.txt";
        BufferedReader r = new BufferedReader(new FileReader(fileName));

        // create containers.
        Map<String, Integer> handBet = new HashMap<String, Integer>();
        String[] hands = new String[1000];

        // read values in
        String line;
        String hand;
        int bet;
        int index = 0;

        while (r.ready()) {
            line = r.readLine();
            hand = line.substring(0, 5);
            bet = Integer.parseInt(line.substring(6));

            handBet.put(hand, bet);
            hands[index] = hand;
            index++;
        }

        r.close();

        customBubbleSort(hands, 1000);

        int totalSum = 0;
        for (int i = 0; i < 1000; i++) {
            totalSum += (i + 1) * (handBet.get(hands[i]));
        }

        System.out.println(totalSum);

    }
}
