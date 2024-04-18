//package Автодополнение;
//
//// на основе Autocomplete2
//// Это решение превышает лимит времени
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.PrintWriter;
//import java.util.Map;
////import java.util.HashMap;
//import java.util.Set;
////import java.util.HashSet;
//import java.util.TreeMap;
//import java.util.TreeSet;
//
//public class Autocomplete3 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Автодополнение/input5.txt"));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Автодополнение/output.txt"));
//
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 1 || n > 100_000) return;
//
//        String[] words = reader.readLine().split(" ");
//        reader.close();
//
//        Map<Character,Set<String>> wordMap = new TreeMap<>();
//
//        int totalKeyPass = 0;
//
//        StringBuilder wordBuilder;
//        for (String word : words) {
////            System.out.println("word = " + word);
//            if (word.length() == 1) {
//                totalKeyPass++;
//                continue;
//            }
//            wordBuilder = new StringBuilder();
//            char firstChar = word.charAt(0);
//            if (wordMap.containsKey(firstChar)) {
//                Set<String> stringList = new TreeSet<>(wordMap.get(firstChar));
//                for (int i = 0; i < word.length(); i++) {
//                    wordBuilder.append(word.charAt(i));
//                    if (stringList == null || stringList.isEmpty()) continue;
//                    int countMatch = 0;
//                    String matchString = null;
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
//                wordMap.get(firstChar).add(word);
//            } else {
////                System.out.println("wordMap не имеет ключа = " + firstChar);
//                Set<String> set = new TreeSet<>();
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
