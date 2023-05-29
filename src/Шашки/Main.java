package Шашки;// Шашки

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean takeIsPossible = false;
        Pattern pattern = Pattern.compile("^\\d+[ ]\\d+$");
        Matcher matcher;
        String line = reader.readLine();
        matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            return;
        }

        String[] nm = line.split(" ");
        int N = Integer.parseInt(nm[0]);
        if (N < 1 || N > 1000)
            return;
        int M = Integer.parseInt(nm[1]);
        if (M < 1 || M > 1000)
            return;

        int[][] desk = new int[M][N];

        int w;
        try {
            w = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            return;
        }
        if (w < 0 || w > (N * M) / 2)
            return;
        List<Checker> white = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            line = reader.readLine();
            matcher = pattern.matcher(line);
            if (!matcher.matches())
                return;
            String[] parts = line.split(" ");
            int x = Integer.parseInt(parts[0]);
            if (x < 1 || x > N)
                return;
            int y = Integer.parseInt(parts[1]);
            if (y < 1 || y > M)
                return;
            if ((x + y)%2 != 0)
                return;
            if (desk[y-1][x-1] != 0)
                return;
            desk[y-1][x-1] = 1;
            white.add(new Checker(x,y));
        }

        int b;
        try {
            b = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            return;
        }
        if (b < 0 || b > ((N * M) / 2) - w)
            return;
        List<Checker> black = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            line = reader.readLine();
            matcher = pattern.matcher(line);
            if (!matcher.matches())
                return;
            String[] parts = line.split(" ");
            int x = Integer.parseInt(parts[0]);
            if (x < 1 || x > N)
                return;
            int y = Integer.parseInt(parts[1]);
            if (y < 1 || y > M)
                return;
            if ((x + y)%2 != 0)
                return;
            if (desk[y-1][x-1] != 0)
                return;
            desk[y-1][x-1] = 2;
            black.add(new Checker(x,y));
        }
        if ((w + b) < 2 || (w + b) > (N * M + 1)/2)
            return;

        String move = reader.readLine();
        reader.close();

        if (move.equals("white"))
            takeIsPossible = canTake(desk,white);
        else if (move.equals("black"))
            takeIsPossible = canTake(desk,black);
        else return;

        if (takeIsPossible)
            writer.write("Yes");
        else
            writer.write("No");

        writer.close();
    }

    public static boolean canTake(int[][] desk, List<Checker> checkers) {
        for (Checker checker : checkers) {
            int x = checker.getX() - 1;
            int y = checker.getY() - 1;
            if (x - 2 >= 0 && y - 2 >= 0 && desk[y - 2][x - 2] == 0) {
                if (desk[y][x] != desk[y - 1][x - 1] && desk[y - 1][x - 1] > 0)
                    return true;
            }
            if (x - 2 >= 0 && y + 2 <= desk.length - 1 && desk[y + 2][x - 2] == 0) {
                if (desk[y][x] != desk[y + 1][x - 1] && desk[y + 1][x - 1] > 0)
                    return true;
            }
            if (x + 2 <= desk[0].length - 1 && y - 2 >= 0 && desk[y - 2][x + 2] == 0) {
                if (desk[y][x] != desk[y - 1][x + 1] && desk[y - 1][x + 1] > 0)
                    return true;
            }
            if (x + 2 <= desk[0].length - 1 && y + 2 <= desk.length - 1 && desk[y + 2][x + 2] == 0) {
                if (desk[y][x] != desk[y + 1][x + 1] && desk[y + 1][x + 1] > 0)
                    return true;
            }
        }
        return false;
    }

}

class Checker {
    private int x;
    private int y;
    public Checker(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}