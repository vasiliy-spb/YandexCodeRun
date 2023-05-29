package Произведение;


import java.util.*;


/*
Алгоритмы-числа
*/

public class HelpClass {

    public static long[] getNumbers(long N) {
        if (N <= 0) return new long[0];

        Set<Long> armstrongNumbers = new TreeSet<>();

        long[][] matrix = new long[10][20];
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 20; j++) {
                long x = i;
                long y = j;
                while (y > 1) {
                    x *= i;
                    y--;
                }
                matrix[i][j] = x;
            }
        }

        int[] array = longToArray(N);
        int size = array.length;

        int[] fillArray = new int[size];
        initializeArray(fillArray);

        decrementArray(fillArray, matrix, armstrongNumbers);

        Set<Long> armstrong = new TreeSet<>(armstrongNumbers);
        for (long l : armstrong)
            if (l < 1 || l >= N)
                armstrongNumbers.remove(l);

        long[] result = new long[armstrongNumbers.size()];
        int i = 0;
        for (long l : armstrongNumbers)
            result[i++] = l;
        return result;

    }

    public static void initializeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 9;
        }
    }

    public static void decrementArray(int[] array, long[][] matrix, Set<Long> armstrongNumbers) {
        long powSum;
        long powSum2;
        int[] sumArray;
        for (int i = 0; i < 1; i++) {

            checkArmstrong(array, matrix, armstrongNumbers);

            powSum = calculatePowSum(array, array.length, matrix);
            sumArray = longToArray(powSum);
            powSum2 = calculatePowSum(sumArray, sumArray.length, matrix);
            if (powSum2 == powSum) {
                armstrongNumbers.add(powSum);
            }

            while (array[i] > 0) {
                array[i] = array[i] - 1;

                checkArmstrong(array, matrix, armstrongNumbers);

                powSum = calculatePowSum(array, array.length, matrix);
                sumArray = longToArray(powSum);
                powSum2 = calculatePowSum(sumArray, sumArray.length, matrix);
                if (powSum2 == powSum) {
                    armstrongNumbers.add(powSum);
                }
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > 0) {
                    array[j] = array[j] - 1;
                    for (int k = 0; k < j; k++) {
                        array[k] = array[j];
                    }
                    i = -1;
                    break;
                }
            }
        }

    }

    private static void checkArmstrong(int[] array, long[][] matrix, Set<Long> armstrongNumbers) {
        long powSum;
        long powSum2;
        int[] sumArray;

        if (array[0] == 0 && array[array.length - 1] != 0) {
            int[] array2 = new int[array.length];
            for (int j = 0; j < array.length; j++) {
                array2[j] = array[j];
            }
            while (array2[0] == 0) {
                int[] array3 = new int[array2.length - 1];
                for (int j = 0; j < array3.length; j++) {
                    array3[j] = array2[j + 1];
                }
                powSum = calculatePowSum(array3, array3.length, matrix);
                sumArray = longToArray(powSum);
                powSum2 = calculatePowSum(sumArray, sumArray.length, matrix);
                if (powSum2 == powSum) {
                    armstrongNumbers.add(powSum);
                }
                array2 = array3;
            }
        }
    }

    public static long calculatePowSum(int[] array, int pow, long[][] matrix) {
        long result = 0;
        for (int i : array)
            result += matrix[i][pow];
        return result;
    }

    public static int[] longToArray(long number) {
        List<Integer> ints = new ArrayList<>();
        while (number > 0) {
            ints.add((int) (number % 10));
            number /= 10;
        }
        int size = ints.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = ints.get(size - i - 1);
        }
        return array;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
//
//        a = System.currentTimeMillis();
//        System.out.println(Arrays.toString(getNumbers(500000000)));
//        b = System.currentTimeMillis();
//        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
//        System.out.println("time = " + (b - a) / 1000);
//
//        a = System.currentTimeMillis();
//        System.out.println(Arrays.toString(getNumbers(Integer.MAX_VALUE)));
//        b = System.currentTimeMillis();
//        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
//        System.out.println("time = " + (b - a) / 1000);

//        a = System.currentTimeMillis();
//        System.out.println(Arrays.toString(getNumbers(2149681590L)));
//        b = System.currentTimeMillis();
//        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
//        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

    }
}

// 9 в 19-ой степени = 1350851717672992000


// Для Integer.MAX_VALUE:
// [1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208, 472335975, 534494836, 912985153]
//memory 444
//time = 52

// Числа Армстронга:
// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208, 472335975, 534494836, 912985153