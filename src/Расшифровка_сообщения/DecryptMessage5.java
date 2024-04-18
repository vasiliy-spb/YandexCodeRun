package Расшифровка_сообщения;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.*;

public class DecryptMessage5 {

    public static void main(String[] args) throws IOException {
        int g = 'n';

        int e = 'a' + 26 - 'n';
        int e2 = 'n' - 'a';
        System.out.println(e);
        System.out.println(e2);

        if (4 > 3) return;

        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/input.txt"));
        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Расшифровка_сообщения/output.txt"));

        StringBuilder result = new StringBuilder();
        String text = reader.readLine();
        if (text.length() < 1 || text.length() > 1_000_000) return;
        String[] words = text.split(" ");
        Map<Character,List<String>> wordsMap = new HashMap<>();
//        Map<String, String> decryptMap = new HashMap<>();
        Map<Integer, List<String>> differentBetweenStartChars = new HashMap<>();

        for (String s : words) {
//            System.out.println("добавляем в словарь слово: " + s);
            if (wordsMap.containsKey(s.charAt(0))) {
                wordsMap.get(s.charAt(0)).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                wordsMap.put(s.charAt(0),list);
            }
            int diff;
            if (s.length() > 1) {
                diff = s.charAt(0) - s.charAt(1);
            } else {
                diff = 100;
            }
            if (differentBetweenStartChars.containsKey(diff)) {
//                System.out.println("diff = " + diff + ", такой ключ уже есть");
                differentBetweenStartChars.get(diff).add(s);
            } else {
//                System.out.println("diff = " + diff + ", такого ключа ещё нет");
                List<String> list = new ArrayList<>();
                list.add(s);
                differentBetweenStartChars.put(diff,list);
            }

        }

        int totalLength = 1_000_000;
//        System.out.println("НАЧИНАЕМ РАСШИФРОВКУ");
        int n = Integer.parseInt(reader.readLine());
        if (n < 1 || n > 1_000_000) return;
        for (int i = 0; i < n; i++) {
            String currentWord = reader.readLine();
            totalLength -= currentWord.length();
            if (totalLength < 0) return;
            if (currentWord.length() < 1 || currentWord.length() > 1_000_000) return;
            char[] charArray = currentWord.toCharArray();
//            System.out.println("Слово = " + currentWord);

            List<String> list;
            if (charArray.length > 1) {
                list = differentBetweenStartChars.get(charArray[0]-charArray[1]);
                if (list == null) {
                    list = differentBetweenStartChars.get(charArray[0]-charArray[1] + 26);
                }
                if (list == null) {
                    list = differentBetweenStartChars.get(charArray[0]-charArray[1] - 26);
                }
            } else {
                list = differentBetweenStartChars.get(100);
            }
            for (String s : list) {
                if (s.length() == charArray.length) {
                    boolean res = compareDistanceBetweenChars(s.toCharArray(), charArray);
                    if (res) {
//                        System.out.println("Расшифровывается как = " + s);
                        result.append(s).append("\n");
                    }
                }
            }
        }

        writer.write(result.toString().trim());
        reader.close();
        writer.close();
    }

    private static boolean compareDistanceBetweenChars(char[] original, char[] charArray) {
//        System.out.println("original = " + String.valueOf(original));
//        System.out.println("charArray = " + String.valueOf(charArray));
        if (original.length == 1) return true;
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
        for (int i = 1; i < original.length; i++) {
            boolean down;
            int a1 = original[i-1];
            int b1 = original[i];
            int a2 = charArray[i-1];
            int b2 = charArray[i];
            int diff1;
            int diff2;
            if (a1 == b1) {
                if (a2 == b2) {
                    continue;
                } else {
                    return false;
                }
            }

            if (a1 < b1) {
                diff1 = b1 - a1;
                down = false;
            } else {
                diff1 = a1 - b1;
                down = true;
            }
            if (!down) {
                if (b2 > a2) {
                    diff2 = b2 - a2;
                } else {
                    diff2 = b2 + 26 - a2;
                }
            } else {
                if (b2 > a2) {
                    diff2 = a2 + 26 - b2;
                } else {
                    diff2 = a2 - b2;
                }
            }
//            System.out.println("b1 = "  + b1);
//            System.out.println("a1 = "  + a1);
//            System.out.println("b2 = "  + b2);
//            System.out.println("a2 = "  + a2);
//            if (a1 - b1 != a2 - b2) return false;
            if (diff1 != diff2) return false;
        }
        return true;
    }
}

// 97 a
// 122 z
// 110 n

/*




b  a  b
98 97 98
  1  25

a  z   a
97 122 97
  1  25

 */