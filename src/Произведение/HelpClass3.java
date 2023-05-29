package Произведение;

/*

Генератор

Даны два натуральных числа N и K. Требуется вывести  все цепочки x1, x2, ..., xN такие, что xi - натуральное и 1 ≤ xi ≤ K.
Входные данные

Вводятся два натуральных числа N и K (N, K ≤ 6).
Выходные данные

Выведите все требуемые цепочки в произвольном порядке – по одной на строке. Никакая цепочка не должна встречаться более одного раза.

 */

import java.util.*;

class HelpClass3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Set<String> set = generateSetString(N,K);
        for(String str : set)
            System.out.println(str);
    }

    private static Set<String> generateSetString(int n, int k) {
        Set<String> strings = new TreeSet<>();
        int[] array = new int[n];
        Arrays.fill(array,1);
        createSet(array,strings,k);
        return strings;
    }

    private static void createSet(int[] array, Set<String> strings, int k) {
        for (int i = 0; i < array.length; i++) {

            addStringToSet(array,strings);

            while(array[i] < k) {
                array[i]++;
                addStringToSet(array,strings);
            }

            if (i < array.length-1 && array[i+1] < k) {
                array[i+1]++;
                for (int j = i; j >= 0; j--) {
                    array[j] = 1;
                }
                i = -1;
            }
        }
    }

    private static void addStringToSet(int[] array, Set<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int x : array)
            stringBuilder.append(x).append(" ");
        strings.add(stringBuilder.reverse().toString().trim());

    }

}