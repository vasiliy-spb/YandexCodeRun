package Камни_и_украшения;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class JewelStone {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

//        Pattern pattern = Pattern.compile("^[a-z]+$");
//        Matcher matcher;

        int countJewelStone = 0;

        String jewelsString = reader.readLine();
//        System.out.println("jewelsString : " + jewelsString);
        if (jewelsString.length() > 100) return;
//        matcher = pattern.matcher(jewelsString);
//        if (!matcher.matches()) return;
        char[] jewels = jewelsString.toCharArray();

        String stoneString = reader.readLine();
//        System.out.println("stoneString : " + stoneString);
        if (stoneString.length() > 100) return;
//        matcher = pattern.matcher(stoneString);
//        if (!matcher.matches()) return;
        char[] stone = stoneString.toCharArray();
        reader.close();

        Map<Integer,Integer> countJewels = new HashMap<>();
        for (int c : stone) {
            if (countJewels.containsKey(c)) {
                countJewels.put(c, countJewels.get(c) + 1);
            } else {
                countJewels.put(c, 1);
            }
        }

        Set<Integer> jewelSet = new HashSet<>();
        for (int c : jewels)
            jewelSet.add(c);

        for (int c : jewelSet)
            if (countJewels.containsKey(c))
                countJewelStone += countJewels.get(c);

        writer.write(String.valueOf(countJewelStone));
        writer.close();
    }
}


// 97 - 122

