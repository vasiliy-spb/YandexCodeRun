//package Расшифровка_сообщения;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.PrintWriter;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.List;
//import java.util.ArrayList;
//
//public class DecryptMessage4 {
//
//    public static final char[] alphabet = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/input.txt"));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/output.txt"));
//
//        Map<Character, List<StringBuilder>> wordsMap = new HashMap<>();
//
//        StringBuilder text = new StringBuilder(reader.readLine());
//        if (text.length() < 1 || text.length() > 1_000_000) return;
//        StringBuilder currentString = new StringBuilder();
////        for (int i = 0; i < text.length(); i++) {
////            if (text.charAt(i) == ' ') {
////                if (wordsMap.containsKey(currentString.charAt(0))) {
////                    wordsMap.get(currentString.charAt(0)).add(currentString);
////                } else {
////                    List<StringBuilder> list = new ArrayList<>();
////                    list.add(currentString);
////                    wordsMap.put(currentString.charAt(0),list);
////                }
////                currentString = new StringBuilder();
////            } else {
////                currentString.append(text.charAt(i));
////            }
////        }
//        int space = 0;
//        for (int i = 0; i < text.length(); i += currentString.length()+1) {
//            space = text.indexOf(" ",i);
////            if (space == -1) space = text.length();
//            if (space == -1 ) currentString = new StringBuilder(text.substring(i));
//            else currentString = new StringBuilder(text.substring(i, space));
////            System.out.println("————");
////            System.out.println("currentString = " + currentString);
////            System.out.println("i = " + i);
////            System.out.println("space = " + space);
//            if (wordsMap.containsKey(currentString.charAt(0))) {
//                wordsMap.get(currentString.charAt(0)).add(currentString);
//            } else {
//                List<StringBuilder> list = new ArrayList<>();
//                list.add(currentString);
//                wordsMap.put(currentString.charAt(0), list);
//            }
//        }
//        if (wordsMap.containsKey(currentString.charAt(0))) {
//            wordsMap.get(currentString.charAt(0)).add(currentString);
//        } else {
//            List<StringBuilder> list = new ArrayList<>();
//            list.add(currentString);
//            wordsMap.put(currentString.charAt(0), list);
//        }
//
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 1 || n > 1_000_000) return;
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            StringBuilder currentWord = new StringBuilder(reader.readLine());
//            boolean needRotate = true;
//            StringBuilder rotatedWord = new StringBuilder();
//            if (needRotate) {
//                for (int j = 0; j <= 13; j++) {
//                    if (canRotate(currentWord, j, wordsMap)) {
//                        rotatedWord = rotateWord(currentWord, j);
//                        needRotate = checkWord(rotatedWord, wordsMap.get(rotatedWord.charAt(0)));
//                        if (!needRotate) {
//                            break;
//                        }
//                    }
//                    if (canRotate(currentWord, -j, wordsMap)) {
//                        rotatedWord = rotateWord(currentWord, -j);
//                        needRotate = checkWord(rotatedWord, wordsMap.get(rotatedWord.charAt(0)));
//                        if (!needRotate) {
//                            break;
//                        }
//                    }
//                }
//            }
//
////            System.out.println("currentWord = " + currentWord);
////            System.out.println("Печатаем: " + rotatedWord);
////            writer.write(rotatedWord.toString());
////            writer.newLine();
//            result.append(rotatedWord).append("\n");
//            continue;
//
////            System.out.printf("Расшифровка слова %s не найдена в словаре\n", currentWord);
//        }
//        reader.close();
//        writer.write(result.toString());
//        writer.close();
//    }
//
//    private static boolean canRotate(StringBuilder currentWord, int index, Map<Character, List<StringBuilder>> wordsMap) {
//        return wordsMap.containsKey(alphabet[currentWord.charAt(0) + index]);
//    }
//
//    private static StringBuilder rotateWord(StringBuilder currentWord, int index) {
//        StringBuilder res = new StringBuilder();
//        for (int i = 0; i < currentWord.length(); i++) {
//            res.append(alphabet[currentWord.charAt(i) + index]);
//
//        }
//        return res;
//    }
//
//    private static boolean checkWord(StringBuilder currentWord, List<StringBuilder> chars) {
//        if (chars == null || chars.isEmpty()) return true;
//        for (StringBuilder sb : chars)
//            if (sb.toString().equals(currentWord.toString()))
//                return false;
//        return true;
//    }
//
//}
