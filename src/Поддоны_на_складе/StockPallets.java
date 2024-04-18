//package Поддоны_на_складе;
//
//import java.io.*;
//
//import java.util.TreeMap;
//import java.util.TreeSet;
//import java.util.StringTokenizer;
//import java.util.Map;
//import java.util.Comparator;
//
//public class StockPallets {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
////        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/Test")));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st;
//
////        long time1 = System.currentTimeMillis();
//
//        int n = Integer.parseInt(reader.readLine());
//
//        if (n < 2 || n > 300_000) return;
//
//        Map<Integer, TreeSet<Integer>> pallets = new TreeMap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(reader.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            if (x < 1 || x > 1_000_000_000) return;
//            int y = Integer.parseInt(st.nextToken());
//            if (y < 1 || y > 1_000_000_000) return;
//            if (x > y) {
//                int tmp = x;
//                x = y;
//                y = tmp;
//            }
//
//            if (pallets.containsKey(x)) {
//                pallets.get(x).add(y);
//            } else {
//                TreeSet<Integer> set = new TreeSet<>((o1, o2) -> (o2 - o1));
//                set.add(y);
//                pallets.put(x, set);
//            }
//
//        }
//        reader.close();
//
//        Map<Integer, TreeSet<Integer>> pallets2 = new TreeMap<>(pallets);
//
//        for (int x : pallets.keySet()) {
//            TreeSet<Integer> setY = pallets.get(x);
//            int currentY = setY.first();
//            for (int x2 : pallets.keySet()) {
//                if (x2 >= x) continue;
//                int currentY2 = pallets.get(x2).first();
//                if (currentY2 < currentY) {
//                    pallets2.remove(x2);
//                }
//            }
//        }
//
//        pallets = pallets2;
//        boolean oversize = false;
//        int countOverSizePallet = 0;
//        TreeSet<Integer> tempSet;
//        for (int x : pallets.keySet()) {
//            tempSet = pallets.get(x);
//            for (int y : new TreeSet<>(tempSet)) {
//                oversize = checkSize(pallets,x,y);
//                if (oversize) {
//                    countOverSizePallet++;
//                } else {
//                    tempSet.remove(y);
//                }
//            }
//        }
//
////        long time2 = System.currentTimeMillis();
////        System.out.println("Time: " + (time2 - time1));
//
//        writer.write(String.valueOf(countOverSizePallet));
//
//        writer.close();
//
//    }
//
//    private static boolean checkSize(Map<Integer, TreeSet<Integer>> pallets, int a, int b) {
//        for (int x : pallets.keySet()) {
//            for (int y : pallets.get(x)) {
//                if (a == x && b == y)
//                    continue;
//                if (a < x && b < y)
//                    return false;
//            }
//        }
//        return true;
//    }
//
//
////    public static void main(String[] args) throws IOException {
////
////        if (func() == 0) return;
////
////
//////        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
////        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/Test")));
////        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
////
//////        Scanner sc = new Scanner(System.in);
////
////        StringTokenizer st;
////
////        long time1 = System.currentTimeMillis();
////
////        int n = Integer.parseInt(reader.readLine());
//////        int n = sc.nextInt();
//////        sc.next();
////        if (n < 2 || n > 300_000) return;
////        int countOverSizePallet = 0;
//////        int maxLength = 0;
//////        int widthMaxLength = 0;
//////        int maxWidth = 0;
//////        int lengthMaxWidth = 0;
////
//////        int a = 0;
//////        int b = 0;
////
////        Set<Pallet> pallets = new HashSet<>();
//////        List<Integer> width = new ArrayList<>();
//////        List<Integer> length = new ArrayList<>();
////
////        for (int i = 0; i < n; i++) {
////            boolean needToAdd = false;
//////            String palletString = reader.readLine();
//////            String[] palletSize = reader.readLine().split(" ");
////            st = new StringTokenizer(reader.readLine());
//////            int x = Integer.parseInt(palletSize[0]);
////            int x = Integer.parseInt(st.nextToken());
//////            int x = sc.nextInt();
//////            sc.next();
////            if (x < 1 || x > 1_000_000_000) return;
//////            int y = Integer.parseInt(palletSize[1]);
////            int y = Integer.parseInt(st.nextToken());
//////            int y = sc.nextInt();
//////            sc.next();
////            if (y < 1 || y > 1_000_000_000) return;
////            if (x > y) {
////                int tmp = x;
////                x = y;
////                y = tmp;
////            }
//////            if (x <= a && y <= b) continue;
//////            if (x < maxWidth && y < lengthMaxWidth) continue;
//////            if (y < maxLength && x < widthMaxLength) continue;
//////            if (a < x && b < y) {
//////                a = x;
//////                b = y;
//////            }
////
////            Pallet currentPallet = new Pallet(x, y);
////            needToAdd = checkPallets(pallets, currentPallet);
////            if (needToAdd)
////                pallets.add(currentPallet);
//////            width.add(x);
//////            length.add(y);
//////            if (y > maxLength) {
//////                maxLength = y;
//////                widthMaxLength = x;
//////            }
//////            if (x > maxWidth) {
//////                maxWidth = x;
//////                lengthMaxWidth = y;
//////            }
////
////        }
////        reader.close();
////
////        countOverSizePallet = pallets.size();
//////        sc.close();
////
//////        System.out.println("Поддонов на проверку = " + width.size());
////
//////        System.out.println("Поддоны: ");
////
//////        one: for (int i = 0; i < pallets.size(); i++) {
//////            System.out.println(pallets.get(i).getA() + " : " + pallets.get(i).getB());
//////            if(pallets.get(i).getA() == maxWidth) {
//////                countOverSizePallet++;
//////                continue;
//////            }
//////            if (pallets.get(i).getB() == maxLength) {
//////                countOverSizePallet++;
//////                continue;
//////            }
//////            for (int j = 0; j < pallets.size(); j++) {
//////                if (j == i) continue;
//////                if (pallets.get(i).getA() >= pallets.get(j).getA() || pallets.get(i).getB() >= pallets.get(j).getB()) {
//////                    countOverSizePallet++;
//////                    continue one;
//////                }
//////            }
//////        }
////
//////        one: for (int i = 0; i < pallets.size(); i++) {
////////            System.out.println(pallets.get(i).getA() + " : " + pallets.get(i).getB());
//////            if(pallets.get(i).getA() == maxWidth) {
//////                countOverSizePallet++;
//////                continue;
//////            }
//////            if (pallets.get(i).getB() == maxLength) {
//////                countOverSizePallet++;
//////                continue;
//////            }
//////            for (int j = 0; j < pallets.size(); j++) {
//////                if (j == i) continue;
//////                if (pallets.get(i).getA() < pallets.get(j).getA() && pallets.get(i).getB() < pallets.get(j).getB()) {
//////                    continue one;
//////                }
//////            }
//////            countOverSizePallet++;
//////        }
////
////
//////        System.out.println("Поддоны: ");
//////        one: for (int i = 0; i < width.size(); i++) {
////////            System.out.println(width.get(i) + " : " + length.get(i));
//////            int currX = width.get(i);
//////            int currY = length.get(i);
////////            if (currX < a && currY < b) continue;
//////
//////            if (currX == maxWidth) {
//////                countOverSizePallet += 1;
//////                width.remove(i);
//////                length.remove(i);
//////                i--;
//////                continue;
//////            }
//////            if (currY == maxLength) {
//////                countOverSizePallet += 1;
//////                width.remove(i);
//////                length.remove(i);
//////                i--;
//////                continue;
//////            }
//////            for (int j = 0; j < width.size(); j++) {
//////                if (i == j) continue;
//////                if (currX < width.get(j) && currY < length.get(j)) {
//////                    width.remove(i);
//////                    length.remove(i);
//////                    i--;
//////                    continue one;
//////                }
//////            }
//////            countOverSizePallet += 1;
//////        }
////
////        long time2 = System.currentTimeMillis();
////
////        writer.write(String.valueOf(countOverSizePallet));
////        writer.close();
////
////        System.out.println("Time: " + (time2 - time1));
////
////
////    }
//
////    private static boolean checkPallets(Set<Pallet> pallets, Pallet currentPallet) {
////        for (Pallet pallet : pallets) {
////            if (currentPallet.getA() < pallet.getA() && currentPallet.getB() < pallet.getB()) {
////                return false;
////            }
////        }
////        Set<Pallet> pallets2 = new HashSet<>(pallets);
////        for (Pallet pallet : pallets2) {
////            if (pallet.getA() < currentPallet.getA() && pallet.getB() < currentPallet.getB())
////                pallets.remove(pallet);
////        }
////        return true;
////    }
//}
//
////class Pallet {
////    private int a;
////    private int b;
////
////    public Pallet(int a, int b) {
////        this.a = a;
////        this.b = b;
////    }
////
////    public int getA() {
////        return this.a;
////    }
////
////    public int getB() {
////        return this.b;
////    }
////}
