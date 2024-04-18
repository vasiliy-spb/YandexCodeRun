package Поиск;

import java.io.*;

public class Search2 {
    public static void main(String[] args) throws IOException {

        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поиск/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поиск/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        int t = Integer.parseInt(reader.readLine());

        for (int p = 0; p < t; p++) {
            String[] first = reader.readLine().split(" ");
            int n = Integer.parseInt(first[0]);
            if (n < 1 || n > 100_000) return;
            int k = Integer.parseInt(first[1]);
            if (k < 0 || k > Math.min(n-1,100)) return;
            String[] numbersString = reader.readLine().split(" ");
            int[] numbers = new int[n];

            int[][] left = new int[n+2][k+2];
            int[][] right = new int[n+2][k+2];

            int res = Integer.parseInt(numbersString[0]);

            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(numbersString[i]);
                res = Math.max(res,numbers[i]);
            }

            if (res <= 0) {
                writer.write(String.valueOf(res));
                writer.newLine();
                continue;
            }

            for (int i = 0; i <= k; ++i) {
                left[n+1][i] = 0;
                right[n+1][i] = 0;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    left[i][j] = Math.max(0,left[i-1][j] + numbers[i-1]);
                    if (j != 0) {
                        left[i][j] = Math.max(left[i][j],left[i-1][j-1]);
                    }
                    res = Math.max(res,left[i][j]);
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    left[i][j] = left[i-1][j] + numbers[i-1];
                    if (j != 0) {
                        left[i][j] = Math.max(left[i][j],left[i-1][j-1]);
                    }
                }
            }

            for (int i = n; i >= 1; i--) {
                for (int j = 0; j <= k; j++) {
                    right[i][j] = right[i+1][j] + numbers[i-1];
                    if (j != 0) {
                        right[i][j] = Math.max(right[i][j],right[i+1][j-1]);
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= k; j++) {
                    if (i != 1) {
                        left[i][j] = Math.max(left[i][j],left[i-1][j]);
                    }
                    if (j != 0) {
                        left[i][j] = Math.max(left[i][j],left[i][j-1]);
                    }
                }
            }

            for (int i = n; i >= 1; i--) {
                for (int j = 0; j <= k; j++) {
                    if (i != n) {
                        right[i][j] = Math.max(right[i][j],right[i+1][j]);
                    }
                    if (j != 0) {
                        right[i][j] = Math.max(right[i][j],right[i][j-1]);
                    }
                }
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= k; j++) {
                    res = Math.max(res,left[i][j] + right[i+1][k-j]);
                }
            }

            writer.write(String.valueOf(res));
            writer.newLine();
        }

        reader.close();
        writer.close();

    }
}
