package Карты;

// решение не проходит по времени

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;

public class Cards {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Карты/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Карты/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        int n = Integer.parseInt(reader.readLine());
        if (n < 3 || n > 50_000) return;
        String[] nums = reader.readLine().split(" ");
        reader.close();
        long sumNum = Long.parseLong(nums[0]);
        long sumSquare = Long.parseLong(nums[1]);
        long sumCube = Long.parseLong(nums[2]);

        long totalSum = (n * (n + 1L)) / 2;
        long totalSquare = (n * (n + 1L) * (2 * n + 1L)) / 6;
        long totalCube = (((long) n * n) * ((n + 1L) * (n + 1L))) / 4;

        long sumOfThree = totalSum - sumNum;
        long squareOfThree = totalSquare - sumSquare;
        long cubeOfThree = totalCube - sumCube;

        Set<Long> checkedNumbers = new HashSet<>();

        long three = 0;
        long start = sumOfThree - 3;

        String[] result = new String[1];
        boolean found = false;

        while (three == 0) {
            long current = start;
            for (long i = current; i > 0; i--) {
                if (checkedNumbers.contains(i)) continue;
                checkedNumbers.add(i);
                if (i * i * i >= cubeOfThree || i * i >= squareOfThree) {
                    start = i - 1;
                    continue;
                }

                found = findOtherNumbers(i, sumOfThree, squareOfThree, cubeOfThree, result);
                if (found) {
                    three = i;
                    break;
                }

            }
        }

        result[0] += " " + three;
        writer.write(result[0]);
        writer.close();
    }

    private static boolean findOtherNumbers(long three, long sumOfThree, long squareOfThree, long cubeOfThree, String[] result) {
        long sumOfTwo = sumOfThree - three;
        long squareOfTwo = squareOfThree - three * three;
        long cubeOfTwo = cubeOfThree - three * three * three;

        for (long i = sumOfTwo - 1; i > 0; i--) {
            if (i * i >= squareOfTwo || i * i * i >= cubeOfTwo) continue;
            if (checkFirst(sumOfTwo - i,i,squareOfTwo,cubeOfTwo) > 0) {
                result[0] = sumOfTwo - i + " " + i;
                return true;
            }
        }
        return false;
    }

    private static long checkFirst(long a, long b, long squareOfTwo, long cubeOfTwo) {
        long squareB = b * b;
        long cubeB = squareB * b;
        long squareA = a * a;
        long cubeA = squareA * a;
        if (squareOfTwo - squareB == squareA) {
            if (cubeOfTwo - cubeB == cubeA) {
                return a;
            }
        }
        return -1;
    }


}
