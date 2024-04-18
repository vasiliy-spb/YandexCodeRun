package task_4_Ход_конём;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int result = processData(reader);
        PrintWriter writer = new PrintWriter("output.txt");
        writer.println(result);
        writer.close();
    }

    public static int processData(BufferedReader reader) throws IOException {
        String[] params = reader.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int m = Integer.parseInt(params[1]);
        reader.close();
        return calculatePosibleMoves(n, m);
    }

    final static int[] directionsI = {2, 1};
    final static int[] directionsJ = {1, 2};

    private static int calculatePosibleMoves(int n, int m) {
        int[][] board = new int[n][m];
        board[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 2; k++) {
                    int nextI = i + directionsI[k];
                    int nextJ = j + directionsJ[k];
                    if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < m)
                        board[nextI][nextJ] += board[i][j];
                }
            }
        }
        return board[n - 1][m - 1];
    }
}
