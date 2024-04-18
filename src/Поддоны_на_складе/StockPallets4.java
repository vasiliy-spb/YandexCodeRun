package Поддоны_на_складе;

// на основе StockPallets3

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StockPallets4 {
    public static void main(String[] args) throws IOException {

        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/output.txt";

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        int n = Integer.parseInt(reader.readLine());
//        if (n < 2 || n > 300_000) return;

        Map<Integer, List<Integer>> mapXY = new TreeMap<>();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        int count = 0;

        Map<Integer, Integer> xAndMaxY = new TreeMap<>((o1, o2) -> o2 - o1);

        one:
        for (int i = 0; i < n; i++) {
            String[] arr = reader.readLine().split(" ");
            int x = Integer.parseInt(arr[0]);
//            if (x < 1 || x > 1_000_000_000) return;
            int y = Integer.parseInt(arr[1]);
//            if (y < 1 || y > 1_000_000_000) return;
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }

            if (minX != Integer.MAX_VALUE && x < minX) {
                if (minY != Integer.MAX_VALUE && y < minY) {
                    continue;
                }
            }

            for (int a : xAndMaxY.keySet()) {
                if (a <= x) break;
                if (y < xAndMaxY.get(a))
                    continue one;
            }

            if (xAndMaxY.containsKey(x)) {
                if (y > xAndMaxY.get(x)) {
                    xAndMaxY.put(x, y);
                }
            } else {
                xAndMaxY.put(x, y);
            }

            if (mapXY.containsKey(x)) {
                mapXY.get(x).add(y);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(y);
                mapXY.put(x,list);
            }

            if (x < minX) minX = x;
            if (y < minY) minY = y;
        }
        reader.close();

        for (int x : mapXY.keySet()) {
            for (int a : xAndMaxY.keySet()) {
                if (a <= x) {
                    break;
                }
                List<Integer> palletList = mapXY.get(x);
                int b = xAndMaxY.get(a);
                palletList.removeIf(y -> y < b);
            }
            count += mapXY.get(x).size();
        }

        writer.write(String.valueOf(count));
        writer.close();
    }
}
