package Частый_элемент;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Map;
import java.util.HashMap;


public class PopularElement {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer,Integer> numbersMap = new HashMap<>();
        int maxCount = 0;
        int maxNumber = -1;

                String nString = reader.readLine();
        int N = Integer.parseInt(nString);
        if (N < 1 || N > 300_000) return;
        String numberString = reader.readLine();
        reader.close();
        String[] numberArray = numberString.split(" ");
        int[] numbers = new int[numberArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            numbers[i] = Integer.parseInt(numberArray[i]);
            if (numbers[i] < 0 || numbers[i] > 1_000_000_000) return;
            if (numbersMap.containsKey(numbers[i])) {
                int prev = numbersMap.get(numbers[i]) + 1;
                numbersMap.put(numbers[i],prev);
                if (prev > maxCount) {
                    maxCount = prev;
                    maxNumber = numbers[i];
                }
            }
            else {
                numbersMap.put(numbers[i], 1);
                if (maxCount < 1)
                    maxCount = 1;
            }
        }

        for (int i : numbersMap.keySet())
            if (numbersMap.get(i) == maxCount)
                if (i > maxNumber)
                    maxNumber = i;

        writer.write(String.valueOf(maxNumber));
        writer.close();

    }
}
