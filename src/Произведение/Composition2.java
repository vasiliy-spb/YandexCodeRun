//package Произведение;
//
//// Это решение работает, но слишком долго в некоторых ситуациях
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.OutputStreamWriter;
//
//import java.util.*;
//import java.io.FileInputStream;
//
//
//public class Composition2 {
//    public static void main(String[] args) throws IOException {
//
////        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        // С тестом из файла test3 работает слишком долго (результата не дождаться)
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Произведение/test3.txt")));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        String[] first = reader.readLine().split(" ");
//        int N = Integer.parseInt(first[0]); // длина массива
//        final int M = Integer.parseInt(first[1]); // произведение, которое нужно простроить
//        int K = Integer.parseInt(first[2]); // количество множителей
//        if (K < 1 || K > N || N < 1 || N > 5000 || M < 0 || M > 1_000_000_000) return;
//        String[] second = reader.readLine().split(" ");
//        reader.close();
//        int[] A = new int[N];
//        for (int i = 0; i < N; i++) {
//            A[i] = Integer.parseInt(second[i]);
//            if (A[i] < 0 || A[i] > 1_000_000_000) return;
//        }
//
//        long time1 = System.currentTimeMillis();
//
//        int[] indexes = currentSet(A,M,K);
//
//        long time2 = System.currentTimeMillis();
//
//        StringBuilder resultString = new StringBuilder();
//        for (int i : indexes)
//            resultString.append(i).append(" ");
//
//        writer.write(resultString.toString().trim());
//
//        writer.newLine();
//        writer.write("Time: " + String.valueOf(time2-time1));
//
//        writer.close();
//    }
//
//    public static boolean checkComposition(int[] array, int target) {
//        int sum = 1;
//        for (int j : array) {
//            sum *= j;
//            if (sum > target) return false;
//        }
//        return sum == target;
//    }
//
//    public static int[] currentSet(int[] parentArray, int summary, int countMultipliers) {
//
//        if (parentArray.length == countMultipliers) {
//            int[] result = new int[countMultipliers];
//            for (int i = 0; i < countMultipliers; i++) {
//                result[i] = i+1;
//            }
//            return result;
//        }
//
//        int length = parentArray.length;
//
//        boolean flag = false;
//
//        Map<Integer, Integer> indexMap = new HashMap<>();
//
//        int[] currentArray = new int[countMultipliers];
//
//        for (int i = 0; i < countMultipliers; i++)
//            indexMap.put(i, 0);
//
//        boolean uniqueSet = false;
//
//        for (int i = 0; i < countMultipliers; i++) {
//            uniqueSet = fillArray(parentArray, currentArray, indexMap);
//            if (uniqueSet) {
//                flag = checkComposition(currentArray, summary);
//                if (flag) return returnResult(indexMap, countMultipliers);
//                uniqueSet = false;
//            }
//
//            while (indexMap.get(i) < length - 1) {
//                indexMap.put(i, indexMap.get(i) + 1);
//                uniqueSet = fillArray(parentArray, currentArray, indexMap);
//                if (uniqueSet) {
//                    flag = checkComposition(currentArray, summary);
//                    if (flag) return returnResult(indexMap, countMultipliers);
//                    uniqueSet = false;
//                }
//            }
//
//
//            if (i < countMultipliers - 1 && indexMap.get(i + 1) < length - 1) {
//                indexMap.put(i + 1, indexMap.get(i + 1) + 1);
//                for (int j = i; j >= 0; j--) {
//                    indexMap.put(j, 0);
//                }
//                i = -1;
//            }
//
//        }
//
//
//        return new int[0];
//    }
//
//    private static int[] returnResult(Map<Integer, Integer> indexMap, int countMultipliers) {
//        int[] result = new int[countMultipliers];
//        for (int j = 0; j < countMultipliers; j++) {
//            result[j] = indexMap.get(j) +1;
//        }
//        return result;
//    }
//
//    public static boolean fillArray(int[] parentArray, int[] array, Map<Integer, Integer> map) {
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < array.length; i++) {
//            if (set.contains(map.get(i)))
//                return false;
//            array[i] = parentArray[map.get(i)];
//            set.add(map.get(i));
//        }
//        return true;
//    }
//
//}
//
//
///*
//
//Ввод
//7 27 2
//9 1 1 27 3 27 3
//Вывод
//4 2
//
//
//Ввод
//7 60 4
//30 1 1 3 10 6 4
//Вывод
//5 6 3 2
//
//
//
//Ввод
//6 2145 4
//2 3 5 7 11 13
//Вывод
//2 3 5 6
// */
