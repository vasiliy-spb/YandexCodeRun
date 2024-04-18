//package Поиск;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.PrintWriter;
//import java.util.List;
//import java.util.ArrayList;
//
//public class Search {
//    public static void main(String[] args) throws IOException {
//
//        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поиск/input.txt";
//        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поиск/output.txt";
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));
//
//        int t = Integer.parseInt(reader.readLine());
//
//        for (int i = 0; i < t; i++) {
//            String[] first = reader.readLine().split(" ");
//            int n = Integer.parseInt(first[0]);
//            if (n < 1 || n > 100_000) return;
//            int k = Integer.parseInt(first[1]);
//            if (k < 0 || k > Math.min(n-1,100)) return;
//            String[] numbers = reader.readLine().split(" ");
//
//            int indexStart = -1;
//            int indexFinish = -1;
//            long prefixSum = 0;
//
//            long currentSum = 0;
//            long totalSum = Long.MIN_VALUE;
//            for (int j = 0; j < n; j++) {
//                long number = Long.parseLong(numbers[j]);
//                if (indexStart < 0) prefixSum += number;
//                currentSum = currentSum + number;
//                if (currentSum < number) {
//                    currentSum = number;
//                    indexStart = j;
//                }
//                if (totalSum < currentSum) {
//                    totalSum = currentSum;
//                }
//
//            }
//
//            writer.write(String.valueOf(totalSum));
//            writer.newLine();
//        }
//
//        reader.close();
//        writer.close();
//
//    }
//}
