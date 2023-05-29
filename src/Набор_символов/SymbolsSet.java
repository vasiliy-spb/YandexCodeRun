package Набор_символов;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SymbolsSet {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String string1 = reader.readLine();
        if (string1.length() < 1 || string1.length() > 100) return;
        String string2 = reader.readLine();
        if (string2.length() < 1 || string2.length() > 26) return;
        reader.close();

        int result = func2(string1,string2);

        writer.write(String.valueOf(result));

        writer.close();
    }

    public static int func2(String bigString, String smallString) {
        int y = smallString.length();
        int x = bigString.length();

        if (x < y) return 0;
        if (x == 1 && y == 1)
            return bigString.equals(smallString) ? 1 : 0;

        Set<Integer> set = new HashSet<>();

        for(int ch : smallString.toCharArray())
            set.add(ch);

        StringBuilder stringBuilder = new StringBuilder();

        boolean hasAll = false;
        int minSize = x + 1;

        for (int i = 0; i < x; i++) {
            int currentChar = bigString.charAt(i);
            if (!set.contains(currentChar)) {
                stringBuilder = new StringBuilder();
                continue;
            }
            stringBuilder.append(bigString.charAt(i));
            Set<Integer> set2 = new HashSet<>(set);
            hasAll = hasAllChars(new StringBuilder(stringBuilder),new HashSet<>(set2));
            while (hasAll) {
                if (minSize == x + 1)
                    minSize = stringBuilder.length();
                else
                    minSize = Math.min(minSize,stringBuilder.length());

                stringBuilder.deleteCharAt(0);
                hasAll = hasAllChars(new StringBuilder(stringBuilder),new HashSet<>(set2));
            }
        }

        return minSize == x + 1 ? 0 : minSize;
    }

    public static boolean hasAllChars(StringBuilder sb, Set<Integer> set) {
        Set<Integer> controlSet = new HashSet<>();
        for (int i : sb.chars().toArray())
            if (set.contains(i)) {
                controlSet.add(i);
            } else {
                return false;
            }

        return set.size() == controlSet.size();
    }

//    public static int func(String bigString, String smallString) {
//        int y = smallString.length();
//        int x = bigString.length();
//
//        if (x < y) return 0;
//        if (x == 1 && y == 1)
//            return bigString.equals(smallString) ? 1 : 0;
//
//        String begin = ".*[";
//        String middle = "]+.*[";
//        String end = "]+.*";
//
//        StringBuilder pString = new StringBuilder(begin + smallString);
//
//        int sizeRegex = smallString.length();
//        for (int i = 1; i < sizeRegex; i++) {
//            pString.append(middle);
//            pString.append(smallString);
//        }
//
//        pString.append(end);
//
//        Pattern pattern = Pattern.compile(pString.toString());
//
//        int minSize = x + 1;
//
//        StringBuilder stringBuilder = new StringBuilder();
//        char tempChar = ' ';
//
//        for (int i = 0; i < x; i++) {
//            stringBuilder.append(bigString.charAt(i));
//            Matcher matcher = pattern.matcher(stringBuilder.toString());
//            while (matcher.matches()) {
//                if (minSize > stringBuilder.length())
//                    minSize = stringBuilder.length();
//                tempChar = stringBuilder.charAt(0);
//                stringBuilder.deleteCharAt(0);
//                matcher = pattern.matcher(stringBuilder.toString());
//            }
//        }
//
//        if (minSize == x + 1)
//            return 0;
//        else
//            return minSize;
//    }

//    public static int maxSubString(String string1, String string2) {
//        int y = string1.length();
//        int x = string2.length();
//
//        int[][] matrix = new int[y][x];
//
//        int sameSymbols = -1;
//
//        Map<Integer,Integer> symbols = new HashMap<>();
//
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                int add = 0;
//                if (string2.charAt(j) == string1.charAt(i))
//                    if (!symbols.containsKey((int)string2.charAt(j))) {
//                        add = 1;
////                        System.out.println("i = " + i + ", j = " + j + ", add = " + add);
//                    }
////                else {
////                        matrix[i][j] = Math.max(matrix[i][j-1],symbols.get((int)string2.charAt(j))) + add;
////                        continue;
////                    }
//
////                matrix[i][j] = Math.max(matrix[i][j-1],symbols.get((int)string2.charAt(j)));
//
//                if (string2.charAt(j) == string1.charAt(i) && symbols.containsKey((int)string2.charAt(j))) {
//                    matrix[i][j] = Math.max(matrix[i][j-1],symbols.get((int)string2.charAt(j)));
//                }
//
//                if (i > 0) {
//                    if (j > 0)
//                        matrix[i][j] = Math.max(matrix[i-1][j],matrix[i][j-1]) + add;
//                    else
//                        matrix[i][j] = matrix[i - 1][j] + add;
//                } else {
//                    if (j > 0)
//                        matrix[i][j] = matrix[i][j-1] + add;
//                    else
//                        matrix[i][j] = add;
//                }
//
//                symbols.put((int)string2.charAt(j),matrix[i][j]);
//
//                if (i == y-1) {
////                    System.out.println("Here");
//                    if(matrix[i][j] == y) {
////                        System.out.println("Here2");
//                        if (sameSymbols < 0) {
//                            sameSymbols = j + 1;
////                            System.out.println("SameSymbols = " + sameSymbols);
//                        }
//                    }
//                }
//
//            }
//            symbols.clear();
//        }
//
////        System.out.println(symbols);
//
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                System.out.print(matrix[i][j] + "\t");
//            }
//            System.out.println();
//        }
//
//
//        return Math.max(sameSymbols, 0);
//    }

//    public static int maxSequence(String string1, String string2) {
//        int y = string1.length();
//        int x = string2.length();
//
//        int[][] matrix = new int[y][x];
//
//        int sameSymbols = 0;
//        int result = -1;
//
//        Set<Integer> symbols = new HashSet<>();
//
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                if (string2.charAt(j) == string1.charAt(i)) {
//                    int add = 0;
//                    if (!symbols.contains((int)string2.charAt(j)))
//                        add = 1;
//                    matrix[i][j] = sameSymbols + add;
//                } else {
//                    matrix[i][j] = 0;
//                }
//
//                if (matrix[i][j] > sameSymbols)
//                    sameSymbols = matrix[i][j];
//
//                symbols.add((int)string2.charAt(j));
//
//
//                if (sameSymbols == y)
//                    if (result < 0)
//                        result = j + 1;
//
//            }
//            symbols.clear();
//        }
//
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                System.out.print(matrix[i][j] + "\t");
//            }
//            System.out.println();
//        }
//
//        return Math.max(result, 0);
//
//    }

//    public static int minSubstring(String bigString, String smallString) {
//        int y = smallString.length();
//        int x = bigString.length();
//
//        if (x < y) return 0;
//        if (x == 1 && y == 1)
//            return bigString.equals(smallString) ? 1 : 0;
//
//        int sameSymbols = 0;
//        int startSubstring = x + 1;
//        int endSubstring = -1;
//
//        Map<Integer,Integer> map = new HashMap<>();
//
//        int subIndex = -1;
//
//        for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                int a = bigString.charAt(i);
//                int b = smallString.charAt(j);
//
//                if (a == b) {
//                    if (subIndex < 0)
//                        subIndex = i + 1;
//                    if (map.containsKey(a)) {
//                        if (map.get(a) < i)
//                            map.put(a,i);
//                    } else {
//                        sameSymbols++;
//                        map.put(a,i);
//                    }
//                    break;
//                }
//            }
//
//            if (sameSymbols == y) {
////                subIndex = i;
//                break;
//            }
//
//        }
//
//
//        int result;
//        for(int key : map.keySet()) {
//            if (map.get(key) < startSubstring) {
//                startSubstring = map.get(key);
//            }
//            if (map.get(key) > endSubstring) {
//                endSubstring = map.get(key);
//            }
//        }
//
////        subIndex = startSubstring;
////        System.out.println("subIndex = " + subIndex);
//
//        int bestResult = 0;
//        if (subIndex > 0 && subIndex < bigString.length()-1) {
//            String newString = bigString.substring(subIndex);
//            if (newString.length() >= smallString.length())
//                bestResult = minSubstring(newString, smallString);
//        }
//
//        result = endSubstring - (startSubstring - 1);
//
//        if (bestResult >= smallString.length())
//            if (result >= smallString.length()) {
//                result = Math.min(result, bestResult);
//            } else {
//                result = bestResult;
//            }
//
//        return result;
//
//
//    }

//    public static int minSubstring(String bigString, String smallString) {
//        int y = smallString.length();
//        int x = bigString.length();
//
//        if (x < y) return 0;
//        if (x == 1 && y == 1)
//            return bigString.equals(smallString) ? 1 : 0;
//
//        int sameSymbols = 0;
//        int startSubstring = x + 1;
//        int endSubstring = -1;
//
//        Map<Integer, List<Integer>> map = new HashMap<>();
//
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                int a = smallString.charAt(i);
//                int b = bigString.charAt(j);
//
//                if (a == b) {
//                    if (map.containsKey(b)) {
//                        map.get(b).add(j);
//                    } else {
//                        sameSymbols++;
//                        List<Integer> l = new ArrayList<>();
//                        l.add(j);
//                        map.put(b,l);
//                        if (sameSymbols == y)
//                            break;
//                    }
//                }
//
//            }
//        }
//
//        System.out.println(map);
//
//        Map<Integer, List<Integer>> map2 = new HashMap<>(map);
//
//        for(int key : map.keySet()) {
//            List<Integer> list = map.get(key);
//            if (list.size() == 1) {
//                if (list.get(0) < startSubstring)
//                    startSubstring = list.get(0);
//                if (list.get(0) > endSubstring)
//                    endSubstring = list.get(0);
//                map2.remove(key);
//            }
//        }
//
//        map = new HashMap<>(map2);
//
//        System.out.println(map);
//
//        Map<Integer,Integer> before = new HashMap<>();
//        int maxBefore = 0;
//
//        Map<Integer,Integer> after = new HashMap<>();
//        int maxAfter = 0;
//
//        System.out.println("start = " + startSubstring);
//        System.out.println("end = " + endSubstring);
//
//        int size = map.size();
//        one : for (int k = 0; k < size; k++) {
//            map2 = new HashMap<>(map);
//
//            for(int key : map.keySet()) {
//                List<Integer> list = map2.remove(key);
//                boolean onlyBefore = true;
//                boolean onlyAfter = true;
////            int minDistance = x + 1;
//                for(int i : list) {
//                    int distance;
//
//                    if (i < startSubstring) {
//                        System.out.println("Here");
//                        onlyAfter = false;
//                        distance = startSubstring - i;
//                        if (before.containsKey(key)) {
//                            if (distance < before.get(key))
//                                before.put(key,distance);
//                        } else {
//                            before.put(key,distance);
//                        }
//                        if (distance > maxBefore)
//                            maxBefore = distance;
//                    }
//
//                    if (i > endSubstring) {
//                        onlyBefore = false;
//                        distance = i - endSubstring;
//                        if (after.containsKey(key)) {
//                            if (distance < after.get(key))
//                                after.put(key,distance);
//                        } else {
//                            after.put(key,distance);
//                        }
//                        if (distance > maxAfter)
//                            maxAfter = distance;
//                    }
//                }
//
//                if (onlyBefore) {
//                    startSubstring -= before.remove(key);
//                    map = new HashMap<>(map2);
//                    continue one;
//                }
//
//                if (onlyAfter) {
//                    endSubstring += after.remove(key);
//                    map = new HashMap<>(map2);
//                    continue one;
//                }
//
//            }
//        }
//
//        int add = 0;
//        if (maxBefore == maxAfter) {
//            add = maxBefore;
//        } else {
//            add = Math.min(maxBefore, maxAfter);
//        }
//
//        System.out.println("start = " + startSubstring);
//        System.out.println("end = " + endSubstring);
//        System.out.println("add = " + add);
//        System.out.println("result = " + ((endSubstring - (startSubstring - 1)) + add));
//
//        return (endSubstring - (startSubstring - 1)) + add;
//
//    }

//    public static int maxSequence(String string1, String string2) {
//        int y = string1.length();
//        int x = string2.length();
//
//        if (x < y) return 0;
//        if (x == 1 && y == 1)
//            return string1.equals(string2) ? 1 : 0;
//
//        int sameSymbols = 0;
//        int start = -1;
//        int end = -1;
//
//        Set<Integer> symbols = new HashSet<>();
//
//        Map<Integer,List<Integer>> firstFind = new HashMap<>();
//
//        for (int i = 0; i < y; i++) {
//            for (int j = 0; j < x; j++) {
//                int a = string1.charAt(i);
//                int b = string2.charAt(j);
//
//                if (b == a) {
//                    if (!symbols.contains(b)) {
//                        sameSymbols++;
//                        symbols.add(b);
//                    }
//
//                    if (firstFind.containsKey(b)) {
//                        firstFind.get(b).add(j);
//                    } else {
//                        List<Integer> l = new ArrayList<>();
//                        l.add(j);
//                        firstFind.put(b,l);
//                    }
//
//                    if (sameSymbols == y) {
//                        end = j;
//                        break;
//                    }
//                }
//
//            }
//        }
//
//        if (sameSymbols < y) return 0;
//
//        int result = 0;
//
//        Map<Integer,Integer> map = new HashMap<>();
//
//        for(int key : firstFind.keySet()) {
//            for (int i : firstFind.get(key)) {
//                if (i > start && i < end)
//                    map.put(key,i);
//            }
//            if (!map.containsKey(key)) {
//                int ind = x + 1;
//                for (int i : firstFind.get(key))
//                    if (i < ind)
//                        ind = i;
//                map.put(key,ind);
//            }
//        }
//
//        start = x + 1;
//
//        for(int key : map.keySet())
//            if (map.get(key) < start) {
//                start = map.get(key);
//            } else {
//                if (map.get(key) > end)
//                    end = map.get(key);
//            }
//
//        if (start >= 0)
//            result = end - (start - 1);
//
//        System.out.println("start = " + start);
//        System.out.println("end = " + end);
//        System.out.println("result = " + result);
//
//        return result;
//
//    }

}

/*
ababababababababababababababababababababababababababababababababababababqwertyuiopasdfghjklzxcvbnmab
qwertyuiopasdfghjklzxcvbnm
26

abcdcab
dc
2

abbacdfabc
abc
3

aaaaaaaaaaaaaacdab
ba
2

aaaaddcbddaaaa
abc
5

baaaaddcddbaaa
abc
5
-- 4

accb
cab
4

ddcdabdddddbcadd
abc
3

ddcdabdddddbcadd
db
2

ddcdabdddddbcadd
bd
2

qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm
qwertyuiopasdfg
15

dddaddddcggggggggchakkkkkkkkkkkalllcoooooo
ac
3

-------------------
Правильные тесты:
dcaabddddddbacssssabbcsssssaccccbj
abc
3

dcaaabddddddbaucssssabbbcsssssacccccbj
abc
5

__________________________________________________________________________
Это  правильный тест:
acceb
cab
Ответ: 0
__________________________________________________________________________

public static int maxSequence(String string1, String string2) {
        int y = string1.length();
        int x = string2.length();

        int[][] matrix = new int[y][x];

        int sameSymbols = -1;
        int end = -1;
        int start = -1;

        Set<Integer> symbols = new HashSet<>();

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (string2.charAt(j) == string1.charAt(i)) {
                    int add = 0;
                    if (!symbols.contains((int)string2.charAt(j)))
                        add = 1;
                    matrix[i][j] = sameSymbols + add;
                } else {
                    matrix[i][j] = 0;
                }

                if (matrix[i][j] > sameSymbols) {
                    sameSymbols = matrix[i][j];
                    if (sameSymbols == 1)
                        start = j;
                }

                symbols.add((int)string2.charAt(j));


                if (sameSymbols == y)
                    if (end < 0)
                        end = j;


            }
            symbols.clear();
        }

        int result = 0;

        if (start > 0)
            result = (end - start) + 1;

        return Math.max(result, 0);

    }



 */