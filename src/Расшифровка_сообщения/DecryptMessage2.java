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
//public class DecryptMessage2 {
//
//    public static final char[] alphabet = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//
//    public static void main(String[] args) throws IOException {
//
////        System.out.print("{");
////        for (int i = 97; i <= 122; i++) {
////            System.out.printf("'%c',", (char) i);
////        }
////        System.out.println("}");
//
////        for (int i = 0; i < 70; i++) {
////            System.out.print("'0',");
////        }
//
////        char[] alphabet = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//
////        System.out.println(alphabet[71]);
////
////        if (4>3) return;
//
//
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/input.txt"));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/output.txt"));
//
//        StringBuilder result = new StringBuilder();
//        String text = reader.readLine();
//        if (text.length() < 1 || text.length() > 1_000_000) return;
//        String[] words = text.split(" ");
//        Map<Character,List<String>> wordsMap = new HashMap<>();
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
//            boolean needRotate;
//            needRotate = checkWord(currentWord,wordsMap.get(currentWord.charAt(0)));
//            String rotatedWord = new String(currentWord);
//            if (needRotate) {
//                for (int j = 1; j <= 13; j++) {
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
//            result.append(rotatedWord).append("\n");
////            writer.write(rotatedWord);
////            writer.newLine();
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
//    private static boolean canRotate(String currentWord, int index, Map<Character, List<String>> wordsMap) {
////        int ch = currentWord.charAt(0) + index;
////        if (ch < 97) ch = 122 - (97 - ch - 1);
////        if (ch > 122) ch = 97 + (ch - 122 - 1);
////        return wordsMap.containsKey((char)ch);
//        return wordsMap.containsKey(alphabet[currentWord.charAt(0) + index]);
//    }
//
//    private static String rotateWord(String currentWord, int index) {
////        System.out.println("rotateWord: currentWord = " + currentWord);
//        char[] result = new char[currentWord.length()];
////        String result = currentWord;
////        Set<Integer> charSet = new HashSet<>();
//        for (int i = 0; i < currentWord.length(); i++) {
////            if (charSet.contains((int)currentWord.charAt(i))) continue;
////            int ch = currentWord.charAt(i) + index;
////            if (ch < 97) ch = 122 - (97 - ch - 1);
////            if (ch > 122) ch = 97 + (ch - 122 - 1);
////            result[i] = (char) ch;
//            result[i] = alphabet[currentWord.charAt(i) + index];
////            result = currentWord.replace(currentWord.charAt(i),(char)ch);
////            charSet.add(ch);
//
//        }
////        System.out.println("rotateWord: result = " + new String(result));
//        return new String(result);
////        return result;
//    }
//
//    private static boolean checkWord(String currentWord, List<String> chars) {
//        if (chars == null || chars.isEmpty()) return true;
////        for (String s : chars)
////            if (s.equals(currentWord))
////                return false;
////        return true;
//        return !chars.contains(currentWord);
//    }
//
//}
