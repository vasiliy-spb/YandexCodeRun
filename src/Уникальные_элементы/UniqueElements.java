package Уникальные_элементы;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Map;
import java.util.HashMap;

public class UniqueElements {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        if (n < 1 || n > 100_000) return;

        String aString = reader.readLine();
        reader.close();
        String[] parts = aString.split(" ");
        int countUniqueNumber = 0;

        Map<Integer,Integer> numberMap = new HashMap<>();

        for (int i = 0; i < parts.length; i++) {
            int currentNumber = Integer.parseInt(parts[i]);
            if (currentNumber < 1 || currentNumber > 1_000_000_000)
                return;
            if (numberMap.containsKey(currentNumber))
                numberMap.put(currentNumber,numberMap.get(currentNumber) + 1);
            else
                numberMap.put(currentNumber,1);
        }

        for(int i : numberMap.keySet())
            if (numberMap.get(i) == 1)
                countUniqueNumber++;

        writer.write(String.valueOf(countUniqueNumber));
        writer.close();

    }
}
