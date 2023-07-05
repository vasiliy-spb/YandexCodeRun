package Закрытый_ключ;

import java.io.*;

public class PrivateKey2 {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Закрытый_ключ/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Закрытый_ключ/output.txt";

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        String[] numbers = reader.readLine().split(" ");
        long x = Long.parseLong(numbers[0]);
//        if (x < 1 || x > 1_000_000_000_000L) return;
        long y = Long.parseLong(numbers[1]);
//        if (y < 1 || y > 1_000_000_000_000L) return;
        reader.close();

        if (y % x != 0) {
            writer.write("0");
            return;
        }

        y /= x;

        long result = 0L;

        for (int i = 2; (long) i * i <= y; i++) {
            if (y % i == 0) {
                result++;
                while (y % i == 0) {
                    y /= i;
                }
            }
        }

        if (y != 1) {
            result++;
        }

        result = 1L << result;
        writer.write(String.valueOf(result));
        writer.close();
    }
}
