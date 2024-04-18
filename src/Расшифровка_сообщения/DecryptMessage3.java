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
//import java.util.Set;
//import java.util.HashSet;
//
//public class DecryptMessage3 {
//
//    public static final char[] alphabet = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/input.txt"));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/output.txt"));
//
////        String text = reader.readLine();
////        if (text.length() < 1 || text.length() > 1_000_000) return;
////        String[] words = text.split(" ");
//        Map<Character,List<StringBuilder>> wordsMap = new HashMap<>();
//
////        for (String s : words) {
////            if (wordsMap.containsKey(s.charAt(0))) {
////                wordsMap.get(s.charAt(0)).add(new StringBuilder(s));
////            } else {
////                List<StringBuilder> list = new ArrayList<>();
////                list.add(new StringBuilder(s));
////                wordsMap.put(s.charAt(0),list);
////            }
////        }
//        StringBuilder result = new StringBuilder();
//        StringBuilder text = new StringBuilder(reader.readLine());
//        if (text.length() < 1 || text.length() > 1_000_000) return;
//        StringBuilder currentString = new StringBuilder();
////        System.out.println("text: " + text);
//        for (int i = 0; i < text.length(); i++) {
////            System.out.println("currentString: " + currentString);
//            if (text.charAt(i) == ' ') {
//                if (wordsMap.containsKey(currentString.charAt(0))) {
//                    wordsMap.get(currentString.charAt(0)).add(currentString);
//                } else {
//                    List<StringBuilder> list = new ArrayList<>();
//                    list.add(currentString);
//                    wordsMap.put(currentString.charAt(0),list);
//                }
////                System.out.println("Словарь: " + currentString);
//                currentString = new StringBuilder();
//            } else {
//                currentString.append(text.charAt(i));
//            }
//        }
//        if (wordsMap.containsKey(currentString.charAt(0))) {
//            wordsMap.get(currentString.charAt(0)).add(currentString);
//        } else {
//            List<StringBuilder> list = new ArrayList<>();
//            list.add(currentString);
//            wordsMap.put(currentString.charAt(0),list);
//        }
//
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 1 || n > 1_000_000) return;
//        for (int i = 0; i < n; i++) {
////            String currentWord = reader.readLine();
//            StringBuilder currentWord = new StringBuilder(reader.readLine());
//            boolean needRotate = true;
////            needRotate = checkWord(currentWord,wordsMap.get(currentWord.charAt(0)));
//            StringBuilder rotatedWord = new StringBuilder();
//            if (needRotate) {
//                for (int j = 0; j <= 13; j++) {
//                    if (canRotate(currentWord,j,wordsMap)) {
//                        rotatedWord = rotateWord(currentWord, j);
//                        needRotate = checkWord(rotatedWord, wordsMap.get(rotatedWord.charAt(0)));
//                        if (!needRotate) {
////                        System.out.println("Прерываем цикл с j = " + j);
//                            break;
//                        }
//                    }
//                    if (canRotate(currentWord,-j,wordsMap)) {
//                        rotatedWord = rotateWord(currentWord, -j);
//                        needRotate = checkWord(rotatedWord, wordsMap.get(rotatedWord.charAt(0)));
//                        if (!needRotate) {
////                        System.out.println("Прерываем цикл с j = " + j);
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
//
//        writer.write(result.toString());
//        reader.close();
//        writer.close();
//    }
//
//    private static boolean canRotate(StringBuilder currentWord, int index, Map<Character, List<StringBuilder>> wordsMap) {
////        int ch = currentWord.charAt(0) + index;
////        if (ch < 97) ch = 122 - (97 - ch - 1);
////        if (ch > 122) ch = 97 + (ch - 122 - 1);
////        return wordsMap.containsKey((char)ch);
//        return wordsMap.containsKey(alphabet[currentWord.charAt(0) + index]);
//    }
//
//    private static StringBuilder rotateWord(StringBuilder currentWord, int index) {
////        System.out.println("rotateWord: currentWord = " + currentWord);
////        char[] result = new char[currentWord.length()];
////        String result = currentWord;
////        Set<Integer> charSet = new HashSet<>();
//        StringBuilder res = new StringBuilder();
//        for (int i = 0; i < currentWord.length(); i++) {
////            if (charSet.contains((int)currentWord.charAt(i))) continue;
////            int ch = currentWord.charAt(i) + index;
////            if (ch < 97) ch = 122 - (97 - ch - 1);
////            if (ch > 122) ch = 97 + (ch - 122 - 1);
////            result[i] = (char) ch;
////            result[i] = alphabet[currentWord.charAt(i) + index];
//            res.append(alphabet[currentWord.charAt(i) + index]);
////            result = currentWord.replace(currentWord.charAt(i),(char)ch);
////            charSet.add(ch);
//
//        }
////        System.out.println("rotateWord: result = " + new String(result));
////        return new String(result);
////        return result;
//        return res;
//    }
//
//    private static boolean checkWord(StringBuilder currentWord, List<StringBuilder> chars) {
//        if (chars == null || chars.isEmpty()) return true;
////        for (String s : chars)
////            if (s.equals(currentWord))
////                return false;
////        return true;
//
//        for (StringBuilder sb : chars)
//            if (sb.toString().equals(currentWord.toString())) {
////                System.out.println("checkWord: в словаре есть слово = " + currentWord);
//                return false;
//            }
////        System.out.println("checkWord: в словаре нет слова = " + currentWord);
//        return true;
////        return !chars.contains(currentWord);
//    }
//
//}
