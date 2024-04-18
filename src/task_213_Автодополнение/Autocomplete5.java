//package Автодополнение;
//
//// на основе Autocomplete2
//// самый быстрый вариант
//// Это решение превышает лимит времени
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.PrintWriter;
//import java.util.TreeMap;
//import java.util.TreeSet;
//import java.util.StringTokenizer;
//import java.util.Map;
//import java.util.HashMap;
//
//
//public class Autocomplete5 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Автодополнение/input.txt"));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Автодополнение/output.txt"));
//
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 1 || n > 100_000) return;
//
////        String[] words = reader.readLine().split(" ");
//        StringTokenizer tokenizer = new StringTokenizer(reader.readLine()," ");
//        reader.close();
//
//        TreeMap<Character,TreeSet<String>> wordMap = new TreeMap<>();
//
//        int totalKeyPass = 0;
//
//        StringBuilder wordBuilder;
//        while (tokenizer.hasMoreTokens()) {
////        for (String word : words) {
////            System.out.println("word = " + word);
//            String word = tokenizer.nextToken();
//            if (word.length() == 1) {
//                totalKeyPass++;
//                continue;
//            }
//            wordBuilder = new StringBuilder();
//            char firstChar = word.charAt(0);
//            String matchString = null;
//
//            TreeSet<String> stringList = wordMap.get(firstChar);
//            if (stringList != null) {
////                TreeSet<String> currentList = wordMap.get(firstChar);
////                TreeSet<String> stringList = new TreeSet<>(currentList);
//                for (int i = 0; i < word.length(); i++) {
//                    wordBuilder.append(word.charAt(i));
//                    if (stringList.isEmpty()) continue;
//                    int countMatch = 0;
//                    for (String s : stringList) {
//                        if (s.startsWith(wordBuilder.toString())) {
//                            countMatch++;
//                            matchString = s;
//                        }
//                    }
//                    if (countMatch == 0) {
////                        System.out.println("countMatch == 0");
//                        totalKeyPass += word.length();
//                        break;
//                    }
//                    if (countMatch == 1) {
//                        if (matchString.equals(word)) {
////                        if (matchString.hashCode() == word.hashCode()) {
////                            System.out.println("countMatch == 1, equals = true");
//                            totalKeyPass += wordBuilder.length();
//                        } else {
////                            System.out.println("countMatch == 1, equals = false");
//                            totalKeyPass += word.length();
//                        }
//                        break;
//                    }
////                    if (countMatch > 1) continue;
//                    if (word.length() == wordBuilder.length()) {
//                        totalKeyPass += wordBuilder.length();
//                    }
//
//                }
////                wordMap.get(firstChar).add(word);
//                stringList.add(word);
//            } else {
////                System.out.println("wordMap не имеет ключа = " + firstChar);
//                TreeSet<String> set = new TreeSet<>();
//                set.add(word);
//                wordMap.put(firstChar,set);
//                totalKeyPass += word.length();
//            }
//        }
//
//        writer.write(String.valueOf(totalKeyPass));
//        writer.close();
//    }
//}
