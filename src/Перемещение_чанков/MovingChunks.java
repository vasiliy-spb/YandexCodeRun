//package Перемещение_чанков;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.PrintWriter;
//
//public class MovingChunks {
//    public static void main(String[] args) throws IOException {
//        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Перемещение_чанков/input.txt";
//        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Перемещение_чанков/output.txt";
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));
//
//        String[] arr1 = reader.readLine().split(" ");
//        int n = Integer.parseInt(arr1[0]);
//        int m = Integer.parseInt(arr1[1]);
//        int q = Integer.parseInt(arr1[2]);
////        if (n < 1 || n > 100_000) return;
////        if (q < 1 || q > 100_000) return;
////        if (m < 2 || m > 100_000) return;
//
//        // чанк под номером i+1 находится на сервере pNumbers[i]
//        String[] pNumbers = reader.readLine().split(" ");
//        int[] numbers = new int[pNumbers.length];
//
//        StringBuilder result = new StringBuilder();
//        for (int i = 1; i <= q; i++) {
//            String[] request = reader.readLine().split(" ");
//            int a = Integer.parseInt(request[0]);
//            int b = Integer.parseInt(request[1]);
//            int l = Integer.parseInt(request[2]);
//            int r = Integer.parseInt(request[3]);
////            if (a < 1 || a > m || a == b || b < 1 || b > m || l < 1 || l > r || r > n) return;
//
//            boolean success = moveChunks(pNumbers,a,b,l,r,numbers);
//            if (success) {
//                result.append(1).append("\n");
//            } else {
//                result.append(0).append("\n");
//            }
//        }
//        reader.close();
//
//        writer.write(result.toString().trim());
//        writer.close();
//    }
//
//    private static boolean moveChunks(String[] pNumbers, int a, int b, int l, int r,int[] numbers) {
//        for (int i = l-1,j = 0; i < r; i++,j++) {
//            int curr = numbers[i];
//            if (curr == 0) {
//                curr = Integer.parseInt(pNumbers[i]);
//                numbers[i] = curr;
//            }
//            if (curr != a)
//                return false;
//        }
//        for (int i = l-1; i < r; i++) {
//            numbers[i] = b;
//        }
//        return true;
//    }
//}
