import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Scanner;

public class App {

    public static int getGameNum(String in) { // takes in string such as "Game 1" and returns int 1
        return Integer.parseInt(in.substring(5));
    }

    public static void main(String[] args) throws Exception {
        String filename = "prompt2.txt";

        int limitRed = 12;
        int limitGreen = 13;
        int limitBlue = 14;

        int fewestRed;
        int fewestGreen;
        int fewestBlue;

        String line = "";
        String[] lineStrings;

        int gameNum;
        int answer1 = 0;
        int answer2 = 0;

        BufferedReader r = new BufferedReader(new FileReader(filename));
        Scanner inside;
        int cubeCount;
        String color;
        Boolean isGoodGame;

        while ((line = r.readLine()) != null) { // reads each game
            isGoodGame = true;
            fewestRed = 0;
            fewestGreen = 0;
            fewestBlue = 0;

            lineStrings = line.splitWithDelimiters(": ", 0);
            gameNum = getGameNum(lineStrings[0]);
            lineStrings = lineStrings[2].splitWithDelimiters("; ", 0); // extracts each round in each game

            for (int i = 0; i < lineStrings.length; i += 2) { // looks at each round
                inside = new Scanner(lineStrings[i]);
                while (inside.hasNext()) { // reads each value in the round
                    cubeCount = inside.nextInt();
                    color = inside.next();

                    // part 1
                    if (isGoodGame) {
                        if ((color.equals("red") || color.equals("red,")) && cubeCount > limitRed) {
                            isGoodGame = false;
                            // break;
                        } else if ((color.equals("green") || color.equals("green,")) && cubeCount > limitGreen) {
                            isGoodGame = false;
                            // break;
                        } else if ((color.equals("blue") || color.equals("blue,")) && cubeCount > limitBlue) {
                            isGoodGame = false;
                            // break;
                        }
                    }

                    // part 2
                    if ((color.equals("red") || color.equals("red,")) && cubeCount > fewestRed) {
                        fewestRed = cubeCount;
                    } else if ((color.equals("green") || color.equals("green,")) && cubeCount > fewestGreen) {
                        fewestGreen = cubeCount;
                    } else if ((color.equals("blue") || color.equals("blue,")) && cubeCount > fewestBlue) {
                        fewestBlue = cubeCount;
                    }
                }
            }
            // part1
            if (isGoodGame) {
                answer1 += gameNum;
            }

            // part2
            answer2 += fewestRed * fewestGreen * fewestBlue;
        }
        System.out.println(answer1);
        System.out.println(answer2);
    }
}
