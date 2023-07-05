//package Закрытый_ключ;
//
//import java.io.*;
//
// это решение превышает лимит времени
//
//public class PrivateKey {
//    public static void main(String[] args) throws IOException {
//
//        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Закрытый_ключ/input.txt";
//        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Закрытый_ключ/output.txt";
//
//        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));
//
//        String[] numbers = reader.readLine().split(" ");
//        long x = Long.parseLong(numbers[0]);
////        if (x < 1 || x > 1_000_000_000_000L) return;
//        long y = Long.parseLong(numbers[1]);
////        if (y < 1 || y > 1_000_000_000_000L) return;
//        reader.close();
//
//        long countMatch = 0L;
//        long add = 0L;
//        for (long i = x; i <= y; i += x) {
//            for (long j = i; j <= y; j += x) {
//                long a = findGCD(i,j);
//                if (a != x) continue;
//                long b = findLCM(i,j);
//                if (b != y) continue;
//                if (a == x && b == y) {
//                    if (i == j) add++;
//                    else countMatch++;
////                    System.out.println(i);
////                    System.out.println(j);
//                }
//            }
//        }
//        writer.write(String.valueOf(countMatch * 2 + add));
//        writer.close();
//    }
//
//    private static long findLCM(long x, long y) {
//        if (y > x) {
//            long temp = x;
//            x = y;
//            y = temp;
//        }
//        long add = x;
//        while (x % y != 0) {
//            x += add;
//        }
//        return x;
//    }
//
//    private static long findGCD(long x, long y) {
//        if (y == 0)
//            return x;
//        if (y > x) {
//            long temp = x;
//            x = y;
//            y = temp;
//        }
//
//        while(y != 0) {
//            long temp = y;
//            y = x % y;
//            x = temp;
//        }
//        return x;
//
////        return findGCD(y, x % y);
//    }
//}
