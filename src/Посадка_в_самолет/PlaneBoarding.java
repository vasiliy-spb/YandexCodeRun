package Посадка_в_самолет;

import java.io.*;
import java.util.*;

public class PlaneBoarding {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Посадка_в_самолет/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Посадка_в_самолет/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        int n = Integer.parseInt(reader.readLine());
        // время и массив занятых проходов
        Map<Integer,boolean[]> map = new TreeMap<>();
        // номер ряда и занятые места в нём
        Map<Integer, List<Character>> seatMap = new TreeMap<>();

        int maxTime = 0;

        for (int i = 0; i < n; i++) {
            String[] rowSeats = reader.readLine().split(" ");
            int a = Integer.parseInt(rowSeats[0]);
            int place = Integer.parseInt(rowSeats[1].substring(0,rowSeats[1].length()-1));
            char ind = rowSeats[1].charAt(rowSeats[1].length()-1);
//            System.out.println(a + " " + place + "" + ind);
//            passengers[i] = new Passenger(i,a,place,ind,rowSeats[1]);
            int start;
            for (int j = 1; true; j++) {
                if (map.containsKey(j)) {
                    if (!map.get(j)[1]) {
                        start = j;
                        break;
                    }
                } else {
                    start = j;
                    break;
                }
            }
            if (a > 0) a--;
//            System.out.println("start = " + start);
            for (int t = start, line = 1; line <= place; t++) {
//                printMatrix(map);
//                System.out.println("t = " + t + ", line = " + line);
                if (map.containsKey(t)) {
//                    System.out.println("map.containsKey(t)");
                    boolean[] places = map.get(t);
                    if (!places[line]) {
//                        System.out.println("array number " + t + " = " + Arrays.toString(map.get(t)));
                        places[line] = true;
                        if (line == place) {
                            int currentTime = t + a + addTime(place, ind, seatMap);
//                            int minus = 0;
                            while (!canISeat(currentTime,map,t,line)) {
                                t++;
                                currentTime++;
//                                minus--;
                            }
                            for (int j = t; j <= currentTime; j++) {
//                                System.out.println(j);
                                if (map.containsKey(j)) {
//                                    if (map.get(j)[line]) {
//                                        currentTime++;
//                                        continue;
//                                    }
//                                    System.out.println("1 Во время " + j + " место " + line + " = " + map.get(j)[line]);
                                    map.get(j)[line] = true;
//                                    System.out.println("2 Во время " + j + " место " + line + " = " + map.get(j)[line]);
                                } else {
                                    boolean[] array = new boolean[31];
                                    array[line] = true;
                                    map.put(j,array);
                                }
                            }
                            if (seatMap.containsKey(place)) {
                                seatMap.get(place).add(ind);
                            } else {
                                List<Character> chars = new ArrayList<>();
                                chars.add(ind);
                                seatMap.put(place,chars);
                            }
                            if (currentTime > maxTime) {
                                maxTime = currentTime;
//                                System.out.println("currentTime = " + currentTime);
                            }
                            break;
                        } else {
//                            System.out.println("Here 1");
                            line++;
                        }
                    } else {
//                        System.out.println("places[line] = " + places[line]);
                    }
                } else {
//                    System.out.println("!map.containsKey(t)");
                    boolean[] places = new boolean[31];
                    places[line] = true;
//                    map.put(t,places);
//                    System.out.println("array number " + t + " = " + Arrays.toString(map.get(t)));
                    if (line == place) {
                        int currentTime = t + a + addTime(place, ind, seatMap);
//                        int minus = 0;
                        while (!canISeat(currentTime,map,t,line)) {
                            t++;
                            currentTime++;
//                            minus--;
                        }
                        for (int j = t; j <= currentTime; j++) {
//                            System.out.println(j);
                            if (map.containsKey(j)) {
//                                if (map.get(j)[line]) {
//                                    currentTime++;
//                                    continue;
//                                }
                                map.get(j)[line] = true;
                            } else {
                                boolean[] array = new boolean[31];
                                array[line] = true;
                                map.put(j,array);
                            }
                        }
                        if (seatMap.containsKey(place)) {
                            seatMap.get(place).add(ind);
                        } else {
                            List<Character> chars = new ArrayList<>();
                            chars.add(ind);
                            seatMap.put(place,chars);
                        }
                        if (currentTime > maxTime) {
                            maxTime = currentTime;
//                            System.out.println("currentTime = " + currentTime);
                        }
//                        map.put(t,places);
                        break;
                    } else {
//                        System.out.println("Here 2");
                        line++;
//                        map.put(t,places);
                    }
//                    boolean[] places = new boolean[31];
//                    places[line] = true;
                    map.put(t,places);

                }
            }
//            printMatrix(map);


        }
        reader.close();

//        maxTime = Math.max(maxTime,map.size());
//        System.out.println(map.size());
        writer.write(String.valueOf(maxTime));


        writer.close();
    }

    private static void printMatrix(Map<Integer,boolean[]> map) {
        System.out.println("————");
        for (int i : map.keySet()) {
            System.out.println(i + " = " + Arrays.toString(map.get(i)));
        }
        System.out.println("————");
    }

    private static boolean canISeat(int currentTime, Map<Integer,boolean[]> map, int t, int line) {
        for (int i = t; i <= currentTime; i++) {
            if (map.containsKey(i)) {
                if (map.get(i)[line]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int addTime(int place, char ind, Map<Integer, List<Character>> seatMap) {
        if (!seatMap.containsKey(place)) {
//            System.out.println("addTime return 0");
            return 0;
        }
        List<Character> chars = seatMap.get(place);
        int result = 0;
        switch(ind) {
            case 'A':
                if (chars.contains('B')) {
                    if (chars.contains('C')) {
                        result = 15;
                    } else {
                        result = 5;
                    }
                    break;
                }
                if (chars.contains('C')) {
                    result = 5;
                }
                break;
            case 'B':
                if (chars.contains('C')) {
                    result = 5;
                }
                break;
            case 'F':
                if (chars.contains('E')) {
                    if (chars.contains('D')) {
                        result = 15;
                    } else {
                        result = 5;
                    }
                    break;
                }
                if (chars.contains('D')) {
                    result = 5;
                }
                break;
            case 'E':
                if (chars.contains('D')) {
                    result = 5;
                }
                break;
        }
//        System.out.println("addTime return " + result);
        return result;
    }
}

/*

чтобы пассажиру с номером N дойти и сесть нужно время:
N + a + addTime + время ожидания в проходе


3
10 4C
20 2B
0 3F

 */


