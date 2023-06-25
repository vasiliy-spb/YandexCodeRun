package Оценка_разнообразия;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

public class DiversityScoring {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Оценка_разнообразия/input3.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Оценка_разнообразия/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        int n = Integer.parseInt(reader.readLine());

        Map<Long,Long> map = new HashMap<>();
        Map<Long,Long> catIndex = new HashMap<>();

        long minDistance = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            String[] arr = reader.readLine().split(" ");
            long id = Long.parseLong(arr[0]);
            long cat = Long.parseLong(arr[1]);
            map.put(id,cat);
            catIndex.put(cat,-1L);
        }

        String[] arr2 = reader.readLine().split(" ");
        reader.close();

        for (long i = 0; i < arr2.length; i++) {
            long currentId = Long.parseLong(arr2[(int) i]);
            long currentCat = map.get(currentId);
            long prevIndex = catIndex.get(currentCat);
            if (prevIndex == -1) {
                catIndex.put(currentCat,i);
            } else {
                if (i - prevIndex < minDistance) {
                    minDistance = i - prevIndex;
                }
                catIndex.put(currentCat,i);
            }
        }

        if (minDistance == Long.MAX_VALUE) minDistance = n;
        writer.write(String.valueOf(minDistance));
        writer.close();
    }
}
