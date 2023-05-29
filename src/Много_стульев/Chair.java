package Много_стульев;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Chair {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String nm = reader.readLine();
        String[] nAndM = nm.split(" ");
        int N = Integer.parseInt(nAndM[0]);
        if (N < 1 || N > 100000) return;
        int M = Integer.parseInt(nAndM[1]);
        if (M < 1 || M > 100000) return;

        String salePriceString = reader.readLine();
        String[] salePriceArray = salePriceString.split(" ");
        int[] salePrice = new int[salePriceArray.length];

        boolean correctPrice = toInts(salePriceArray,salePrice);
        if (!correctPrice) return;

        String buyPriceString = reader.readLine();
        reader.close();
        String[] buyPriceArray = buyPriceString.split(" ");
        int[] buyPrice = new int[buyPriceArray.length];

        correctPrice = toInts(buyPriceArray,buyPrice);
        if (!correctPrice) return;
        
        Arrays.sort(buyPrice);
        Arrays.sort(salePrice);

        List<Integer> goodBuyPrice = new ArrayList<>();

        for (int i = buyPrice.length-1; i >= 0; i--) {
            if (buyPrice[i] > salePrice[0])
                goodBuyPrice.add(buyPrice[i]);
        }

        long summaryProfit = 0;

        for (int i = 0; i < goodBuyPrice.size(); i++) {
            if (i >= salePrice.length) break;
            int currentSum = goodBuyPrice.get(i) - salePrice[i];
            if (currentSum > 0)
                summaryProfit += currentSum;
        }

        writer.write(String.valueOf(summaryProfit));
        writer.close();

    }

    public static boolean toInts(String[] array, int[] result) {
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.parseInt(array[i]);
            if (result[i] < 0 || result[i] > 1_000_000_000) return false;
        }
        return true;
    }


}
