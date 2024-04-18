//package Поддоны_на_складе;
//
//import java.io.*;
//import java.util.*;
//
//// это самое быстрое решение, но всё равно работает долго (Time limit exceeded)
//
//public class StockPallets3 {
//    public static void main(String[] args) throws IOException {
//
//        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/input.txt";
//        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/output.txt";
//
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));
//
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 2 || n > 300_000) return;
//
//        Map<Integer, List<Pallet>> mapXPallet = new TreeMap<>();
//
//        int minX = Integer.MAX_VALUE;
//        int minY = Integer.MAX_VALUE;
//
//        int count = 0;
//
//        Map<Integer, Integer> xAndMaxY = new TreeMap<>((o1, o2) -> o2 - o1);
//
//        int id = 0;
//
//        one:
//        for (int i = 0; i < n; i++) {
//            String[] arr = reader.readLine().split(" ");
//            int x = Integer.parseInt(arr[0]);
//            if (x < 1 || x > 1_000_000_000) return;
//            int y = Integer.parseInt(arr[1]);
//            if (y < 1 || y > 1_000_000_000) return;
//            if (x > y) {
//                int tmp = x;
//                x = y;
//                y = tmp;
//            }
//
//            if (minX != Integer.MAX_VALUE && x < minX) {
//                if (minY != Integer.MAX_VALUE && y < minY) {
//                    continue;
//                }
//            }
//
//            for (int a : xAndMaxY.keySet()) {
//                if (a <= x) break;
//                if (y < xAndMaxY.get(a))
//                    continue one;
//            }
//
//            if (xAndMaxY.containsKey(x)) {
//                if (y > xAndMaxY.get(x)) {
//                    xAndMaxY.put(x, y);
//                }
//            } else {
//                xAndMaxY.put(x, y);
//            }
//
//            Pallet pallet = new Pallet(id++,x,y);
//            if (mapXPallet.containsKey(x)) {
//                mapXPallet.get(x).add(pallet);
//            } else {
//                List<Pallet> list = new ArrayList<>();
//                list.add(pallet);
//                mapXPallet.put(x,list);
//            }
//
//            if (x < minX) minX = x;
//            if (y < minY) minY = y;
//        }
//        reader.close();
//
////        System.out.println("mapXPallet.size = " + mapXPallet.size());
////        System.out.println("mapXPallet");
////        for (List<Pallet> lp : mapXPallet.values())
////            for (Pallet p : lp)
////                System.out.print(p.getX() + " : " + p.getY() + " | ");
////        System.out.println();
////        System.out.println("xAndMaxY.size = " + xAndMaxY.size());
////        System.out.println("xAndMaxY");
////        for (int i : xAndMaxY.keySet())
////            System.out.print(i + " : " + xAndMaxY.get(i) + " | ");
////        System.out.println();
//
//        one: for (int x : mapXPallet.keySet()) {
////            System.out.println("x = " + x);
//            two: for (int a : xAndMaxY.keySet()) {
//                if (a <= x) {
////                    System.out.println("Проходят: ");
////                    for (Pallet p : mapXPallet.get(x))
////                        System.out.print(p.getX() + " : " + p.getY() + " | ");
////                    System.out.println();
//                    count += mapXPallet.get(x).size();
//                    continue one;
//                }
////                System.out.println("Рамка= " + a + " : " + xAndMaxY.get(a));
//                List<Pallet> palletList = mapXPallet.get(x);
//                for (Pallet pallet : new ArrayList<>(palletList)) {
////                    System.out.println("Поддон " + x + " : " + pallet.getY());
//                    if (pallet.getY() < xAndMaxY.get(a)) {
////                        System.out.println("Поддон меньше");
//                        palletList.remove(pallet);
//                    }
////                    System.out.println("Поддон больше");
//                }
////                palletList.removeIf(pallet -> pallet.getY() < xAndMaxY.get(a));
//            }
//            count += mapXPallet.get(x).size();
//        }
//
//        writer.write(String.valueOf(count));
//        writer.close();
//    }
//}
//
//class Pallet {
//    private int id;
//    private int x;
//    private int y;
//
//    public Pallet(int id, int x, int y) {
//        this.id = id;
//        this.x = x;
//        this.y = y;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//}