package task_5_Кафе;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String result = processData(reader);
        PrintWriter writer = new PrintWriter("output.txt");
        writer.println(result);
        writer.close();
    }

    public static String processData(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(reader.readLine());
        }
        reader.close();

        return calculateExpenses(prices, n);
    }
    private static String calculateExpenses(int[] prices, int n) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

            }
        }
        return "";
    }
}
