//package Расшифровка_сообщения;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.BufferedWriter;
//import java.io.PrintWriter;
//import java.util.*;
//
////public class DecryptMessage2
//
//public class DecryptMessage {
//
//    public static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//    public static final int offset = 71;
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/input.txt"));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/output.txt"));
//
//        StringBuilder result = new StringBuilder();
//        String text = reader.readLine();
//        if (text.length() < 1 || text.length() > 1_000_000) return;
//        String[] words = text.split(" ");
//        Map<Character,List<String>> wordsMap = new HashMap<>();
//        Map<String, String> decryptMap = new HashMap<>();
//
//        for (String s : words) {
//            if (wordsMap.containsKey(s.charAt(0))) {
//                wordsMap.get(s.charAt(0)).add(s);
//            } else {
//                List<String> list = new ArrayList<>();
//                list.add(s);
//                wordsMap.put(s.charAt(0),list);
//            }
//        }
//
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 1 || n > 1_000_000) return;
//        for (int i = 0; i < n; i++) {
//            String currentWord = reader.readLine();
//            boolean needRotate = true;
//            String rotatedWord = new String(currentWord);
//            if (decryptMap.containsKey(currentWord)) {
//                rotatedWord = decryptMap.get(currentWord);
//                needRotate = false;
//            }
//            if (needRotate) {
//                for (int j = 0; j <= 13; j++) {
//                    if (canRotate(currentWord,j,wordsMap)) {
//                        rotatedWord = rotateWord(currentWord, j);
//                        needRotate = checkWord(rotatedWord, wordsMap.get(rotatedWord.charAt(0)));
//                        if (!needRotate) {
//                            break;
//                        }
//                    }
//                    if (canRotate(currentWord,-j,wordsMap)) {
//                        rotatedWord = rotateWord(currentWord, -j);
//                        needRotate = checkWord(rotatedWord, wordsMap.get(rotatedWord.charAt(0)));
//                        if (!needRotate) {
//                            break;
//                        }
//                    }
//                }
//            }
//            if (!decryptMap.containsKey(currentWord))
//                decryptMap.put(currentWord,rotatedWord);
//            result.append(rotatedWord).append("\n");
//
//        }
//
//        writer.write(result.toString());
//        reader.close();
//        writer.close();
//    }
//
//    private static boolean canRotate(String currentWord, int index, Map<Character, List<String>> wordsMap) {
//        int length = currentWord.length();
//        if (wordsMap.containsKey(alphabet[currentWord.charAt(0) + index - offset])) {
//            for (String s : wordsMap.get(alphabet[currentWord.charAt(0) + index - offset]))
//                if (length == s.length())
//                    return true;
//        }
//        return false;
//    }
//
//    private static String rotateWord(String currentWord, int index) {
////        char[] result = new char[currentWord.length()];
////        for (int i = 0; i < currentWord.length(); i++) {
////            result[i] = alphabet[currentWord.charAt(i) + index - offset];
////        }
////        return new String(result);
//
////        char[] result = currentWord.toCharArray();
////        for (int i = 0; i < result.length; i++) {
////            result[i] = alphabet[result[i] + index - offset];
////        }
////        return new String(result);
//
//        byte[] result = currentWord.getBytes();
//        for (int i = 0; i < result.length; i++) {
//            result[i] = (byte)alphabet[result[i] + index - offset];
//        }
//        return new String(result);
//    }
//
//    private static boolean checkWord(String currentWord, List<String> chars) {
//        if (chars == null || chars.isEmpty()) return true;
////        for (String s : chars)
////            if (s.hashCode() == currentWord.hashCode())
////                return false;
////        return true;
//        return !chars.contains(currentWord);
//    }
//
//}
