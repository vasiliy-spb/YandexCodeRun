package Поддоны_на_складе;

// не всегда работает верно

import java.io.*;
import java.util.*;

public class CleanCode {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(reader.readLine());

        if (n < 2 || n > 300_000) return;

        Map<Integer, TreeSet<Integer>> pallets = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            if (x < 1 || x > 1_000_000_000) return;
            int y = Integer.parseInt(st.nextToken());
            if (y < 1 || y > 1_000_000_000) return;
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }

            if (pallets.containsKey(x)) {
                pallets.get(x).add(y);
            } else {
                TreeSet<Integer> set = new TreeSet<>((o1, o2) -> (o2 - o1));
                set.add(y);
                pallets.put(x, set);
            }

        }
        reader.close();

        Map<Integer, TreeSet<Integer>> pallets2 = new TreeMap<>(pallets);

        for (int x : pallets.keySet()) {
            TreeSet<Integer> setY = pallets.get(x);
            int currentY = setY.first();
            for (int x2 : pallets.keySet()) {
                if (x2 >= x) continue;
                int currentY2 = pallets.get(x2).first();
                if (currentY2 < currentY) {
                    pallets2.remove(x2);
                }
            }
        }

        pallets = pallets2;
        boolean oversize = false;
        int countOverSizePallet = 0;
        for (int x : pallets.keySet()) {
            for (int y : pallets.get(x)) {
                oversize = checkSize(pallets,x,y);
                if (oversize) {
                    countOverSizePallet++;
                }
            }
        }


        writer.write(String.valueOf(countOverSizePallet));

        writer.close();

    }

    private static boolean checkSize(Map<Integer, TreeSet<Integer>> pallets, int a, int b) {
        for (int x : pallets.keySet()) {
            for (int y : pallets.get(x)) {
                if (a == x && b == y)
                    continue;
                if (a < x && b < y)
                    return false;
            }
        }
        return true;
    }
}
