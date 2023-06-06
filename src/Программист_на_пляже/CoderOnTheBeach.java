package Программист_на_пляже;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class CoderOnTheBeach {
    public static void main(String[] args) throws IOException {

        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Программист_на_пляже/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Программист_на_пляже/output.txt";

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        PrintWriter writer = new PrintWriter(outputFile);

        int testCount = Integer.parseInt(reader.readLine());
        if (testCount < 1 || testCount > 1000) return;

        for (int i = 0; i < testCount; i++) {
            int countSunbed = Integer.parseInt(reader.readLine());
            if (countSunbed < 2 || countSunbed > 1_000_000) return;
            String[] sunbeds = reader.readLine().split(" ");

            int[] sunbedNumbers = new int[sunbeds.length];
            for (int j = 0; j < sunbeds.length; j++) {
                sunbedNumbers[j] = Integer.parseInt(sunbeds[j]);
                if (sunbedNumbers[j] < 0 || sunbedNumbers[j] > 1_000_000_000) return;
            }

            Arrays.sort(sunbedNumbers);

            int a = sunbedNumbers[0];
            int b = sunbedNumbers[1];
            int min = a ^ b;
            for (int j = 2; j < sunbedNumbers.length; j++) {
                a = sunbedNumbers[j - 1];
                b = sunbedNumbers[j];
                int currentDiff = a ^ b;
                if (currentDiff < min) min = currentDiff;
            }

//            int a = Integer.parseInt(sunbeds[0]);
//            if (a < 0 || a > 1_000_000_000) return;
//            int b = Integer.parseInt(sunbeds[1]);
//            if (b < 0 || b > 1_000_000_000) return;
//            int min = a ^ b;
//            for (int j = 2; j < countSunbed; j++) {
//                a = Integer.parseInt(sunbeds[j - 1]);
//                b = Integer.parseInt(sunbeds[j]);
//                if (b < 0 || b > 1_000_000_000) return;
//                int currentDiff = a ^ b;
//                if (currentDiff < min) min = currentDiff;
//            }
            writer.println(min);
        }
        reader.close();
        writer.close();

    }
}
