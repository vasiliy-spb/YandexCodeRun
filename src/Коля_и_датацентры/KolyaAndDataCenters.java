package Коля_и_датацентры;


import java.io.*;

import java.util.*;


public class KolyaAndDataCenters {

    public static void func() {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int i = 1; i < 101; i++) {
            map.put((int)(Math.random() * i) + 1,44444);
        }

        System.out.println(map);

        for (int i : map.keySet())
            System.out.print(i + ", ");


    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Коля_и_датацентры/test6.txt")));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        PrintWriter writer = new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Коля_и_датацентры/output.txt");

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine()," ");
//        String[] first = reader.readLine().split(" ");
        // число дата центров
//        int n = Integer.parseInt(first[0]);
        int n = Integer.parseInt(stringTokenizer.nextToken());
        // число серверов в каждом из дата центров
//        int m = Integer.parseInt(first[1]);
        int m = Integer.parseInt(stringTokenizer.nextToken());
        // число событий
//        int q = Integer.parseInt(first[2]);
        int q = Integer.parseInt(stringTokenizer.nextToken());
        if (q < 1 || q > 100_000) return;
        if ((n * m) < 1 || (n * m) > 1_000_000) return;

        // НомерДЦ — ДатаЦентр
        TreeMap<Integer, DataCenter> dataCenterMap = new TreeMap<>();

        // RA ДатаЦентра — TreeSet<DataCenter> | от меньшего RA к большему
        TreeMap<Long, TreeSet<DataCenter>> RADataCenterMap = new TreeMap<>();

        // RA ДатаЦентра — TreeSet<DataCenter> | от большего RA к меньшему
        TreeMap<Long, TreeSet<DataCenter>> RADataCenterMapReverse = new TreeMap<>(Comparator.reverseOrder());

        for (int i = 1; i <= n; i++) {
            DataCenter dc = new DataCenter(i, m, 0L, m);
            dataCenterMap.put(i, dc);
            TreeSet<DataCenter> set = new TreeSet<>((o1,o2)->o1.getNumber()-o2.getNumber());
            if (RADataCenterMap.containsKey(0L))
                set = RADataCenterMap.get(0L);
            set.add(dc);
            RADataCenterMap.put(0L, set);
            RADataCenterMapReverse.put(0L, set);
        }

//        System.out.println(dataCenterMap);
//        System.out.println();

        // в последующих q строках записаны события
        for (int iq = 0; iq < q; iq++) {
//            String line = reader.readLine();
//            System.out.println("—————————————————————");
//            System.out.println(line);
//            System.out.println("—————————————————————");
//            System.out.println("RADataCenterMap = " + RADataCenterMap);
//            System.out.println("RADataCenterMapReverse = " + RADataCenterMapReverse);

//            String[] eventString = line.split(" ");
            stringTokenizer = new StringTokenizer(reader.readLine()," ");
            int i;
            int j;
            DataCenter currentDataCenter;
            switch (stringTokenizer.nextToken()) {
                case "RESET":
//                    i = Integer.parseInt(eventString[1]);
                    i = Integer.parseInt(stringTokenizer.nextToken());
                    if (i < 1 || i > n) return;

                    currentDataCenter = dataCenterMap.get(i);
                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
                        RADataCenterMap.get(currentDataCenter.getRA()).remove(currentDataCenter);
                        RADataCenterMapReverse.get(currentDataCenter.getRA()).remove(currentDataCenter);
                    }

                    currentDataCenter.resetDataCenter();

                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
                        RADataCenterMap.get(currentDataCenter.getRA()).add(currentDataCenter);
                        RADataCenterMapReverse.get(currentDataCenter.getRA()).add(currentDataCenter);
                    } else {
                        TreeSet<DataCenter> treeSet = new TreeSet<>((o1,o2)->o1.getNumber()-o2.getNumber());
                        treeSet.add(currentDataCenter);
                        RADataCenterMap.put(currentDataCenter.getRA(), treeSet);
                        RADataCenterMapReverse.put(currentDataCenter.getRA(), treeSet);
                    }
                    break;
                case "DISABLE":
//                    i = Integer.parseInt(eventString[1]);
                    i = Integer.parseInt(stringTokenizer.nextToken());
//                    j = Integer.parseInt(eventString[2]);
                    j = Integer.parseInt(stringTokenizer.nextToken());
                    if (i < 1 || i > n) return;
                    if (j < 1 || j > m) return;

                    currentDataCenter = dataCenterMap.get(i);
                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
                        RADataCenterMap.get(currentDataCenter.getRA()).remove(currentDataCenter);
                        RADataCenterMapReverse.get(currentDataCenter.getRA()).remove(currentDataCenter);
                    }

                    currentDataCenter.disableServer(j);

                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
                        RADataCenterMap.get(currentDataCenter.getRA()).add(currentDataCenter);
                        RADataCenterMapReverse.get(currentDataCenter.getRA()).add(currentDataCenter);
                    } else {
                        TreeSet<DataCenter> treeSet = new TreeSet<>((o1,o2)->o1.getNumber()-o2.getNumber());
                        treeSet.add(currentDataCenter);
                        RADataCenterMap.put(currentDataCenter.getRA(), treeSet);
                        RADataCenterMapReverse.put(currentDataCenter.getRA(), treeSet);
                    }
                    break;
                case "GETMAX":
                    int numberMaxDataCenter = -1;

                    TreeMap<Long, TreeSet<DataCenter>> RADataCenterMapReverse2 = new TreeMap<>(RADataCenterMapReverse);

                    for (long l : RADataCenterMapReverse.keySet()) {
                        if (!RADataCenterMapReverse2.containsKey(l)) continue;
                        TreeSet<DataCenter> setDC = RADataCenterMapReverse.get(l);
                        if (setDC == null || setDC.isEmpty()) {
                            RADataCenterMapReverse2.remove(l);
                            RADataCenterMap.remove(l);
                            continue;
                        }
                        numberMaxDataCenter = setDC.first().getNumber();
//                        System.out.println(RADataCenterMapReverse);
//                        System.out.println(setDC);
                        break;
                    }
                    RADataCenterMapReverse = RADataCenterMapReverse2;
//                    writer.write(String.valueOf(numberMaxDataCenter));
                    writer.println(numberMaxDataCenter);
//                    writer.newLine();
                    writer.flush();
                    break;
                case "GETMIN":
                    int numberMinDataCenter = -1;

                    TreeMap<Long, TreeSet<DataCenter>> RADataCenterMap2 = new TreeMap<>(RADataCenterMap);

                    for (long l : RADataCenterMap.keySet()) {
                        if (!RADataCenterMap2.containsKey(l)) continue;
                        TreeSet<DataCenter> setDC = RADataCenterMap.get(l);
                        if (setDC == null || setDC.isEmpty()) {
                            RADataCenterMap2.remove(l);
                            RADataCenterMapReverse.remove(l);
                            continue;
                        }
                        numberMinDataCenter = setDC.first().getNumber();
//                        System.out.println(RADataCenterMap);
//                        System.out.println(setDC);
                        break;
                    }
                    RADataCenterMap = RADataCenterMap2;

//                    writer.write(String.valueOf(numberMinDataCenter));
                    writer.println(numberMinDataCenter);
//                    writer.newLine();
                    writer.flush();
                    break;
            }
//            System.out.println("dataCenterMap = " + dataCenterMap);
//            System.out.println("RADataCenterMap = " + RADataCenterMap);
//            System.out.println("RADataCenterMapReverse = " + RADataCenterMapReverse);
//            System.out.println("DataCenter 4 RA = " + dataCenterMap.get(4).getRA());
//            System.out.println("DataCenter 7 RA = " + dataCenterMap.get(7).getRA());

        }
        reader.close();
        writer.close();

    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
//                "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Коля_и_датацентры/test6.txt")));
////        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//
//        String[] first = reader.readLine().split(" ");
//        // число дата центров
//        int n = Integer.parseInt(first[0]);
//        // число серверов в каждом из дата центров
//        int m = Integer.parseInt(first[1]);
//        // число событий
//        int q = Integer.parseInt(first[2]);
//        if (q < 1 || q > 100_000) return;
//        if ((n * m) < 1 || (n * m) > 1_000_000) return;
//
//        /////////////////////////////////////////////////////////////////////////////////////////
//
//        // НомерДЦ — ДатаЦентр
//        TreeMap<Integer, DataCenter> dataCenterMap = new TreeMap<>();
//
//        // RA ДатаЦентра — TreeSet<DataCenter>
//        TreeMap<Long, TreeSet<DataCenter>> RADataCenterMap = new TreeMap<>();
//
//        TreeMap<Long, TreeSet<DataCenter>> RADataCenterMapReverse = new TreeMap<>(Comparator.reverseOrder());
//
////        long[] maxRA = {0};
////        long[] minRA = {0};
//
//        for (int i = 1; i <= n; i++) {
//            DataCenter dc = new DataCenter(i,m,0L,m);
//            dataCenterMap.put(i,dc);
//            TreeSet<DataCenter> set = new TreeSet<>();
//            set.add(dc);
//            RADataCenterMap.put(0L,set);
//            RADataCenterMapReverse.put(0L,set);
//        }
//
//
//        /////////////////////////////////////////////////////////////////////////////////////////
//
////        long[] maxRA = {0};
////        long[] minRA = {0,0};
////
////        TreeMap<Integer,DataCenter> dataCenterMap = new TreeMap<>();
////
////        DataCenter[] basicDataCenter = {new DataCenter(1, m, 0L, m)};
////        dataCenterMap.put(1,basicDataCenter[0]);
////
////        DataCenter[] maxRADataCenter = {basicDataCenter[0]};
////        DataCenter[] minRADataCenter = {basicDataCenter[0]};
////
////        TreeSet<DataCenter> likeMaxDataCenter = new TreeSet<>();
////        TreeSet<DataCenter> likeMinDataCenter = new TreeSet<>();
//
//
//        // в последующих q строках записаны события
//        for (int iq = 0; iq < q; iq++) {
//            String line = reader.readLine();
////            System.out.println(line);
//
//            String[] eventString = line.split(" ");
//            int i;
//            int j;
//            switch (eventString[0]) {
//                case "RESET":
//                    i = Integer.parseInt(eventString[1]);
//                    if (i < 1 || i > n) return;
//
////                    minRA[0] = minRADataCenter[0].getRA();
////                    maxRA[0] = maxRADataCenter[0].getRA();
//
//                    if (dataCenterMap.containsKey(i)) {
//                        DataCenter[] currentDataCenter = {dataCenterMap.get(i)};
//                        if (RADataCenterMap.containsKey(currentDataCenter[0].getRA())) {
//                            RADataCenterMap.get(currentDataCenter[0].getRA()).remove(currentDataCenter[0]);
//                            RADataCenterMapReverse.get(currentDataCenter[0].getRA()).remove(currentDataCenter[0]);
//                        }
//                        currentDataCenter[0].resetDataCenter();
//
//                        if (RADataCenterMap.containsKey(currentDataCenter[0].getRA())) {
//                            RADataCenterMap.get(currentDataCenter[0].getRA()).add(currentDataCenter[0]);
//                            RADataCenterMapReverse.get(currentDataCenter[0].getRA()).add(currentDataCenter[0]);
//                        } else {
//                            TreeSet<DataCenter> treeSet = new TreeSet<>();
//                            treeSet.add(currentDataCenter[0]);
//                            RADataCenterMap.put(currentDataCenter[0].getRA(), treeSet);
//                            RADataCenterMapReverse.put(currentDataCenter[0].getRA(), treeSet);
//                        }
////                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,n,currentDataCenter,m,maxRA,likeMaxDataCenter);
////                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,currentDataCenter,m,minRA,likeMinDataCenter);
//                    } else {
//                        System.out.println("here");
//                        DataCenter[] newDataCenter = {new DataCenter(i, m, 0L, m)};
//                        newDataCenter[0].resetDataCenter();
//                        dataCenterMap.put(i, newDataCenter[0]);
//
//                        if (RADataCenterMap.containsKey(newDataCenter[0].getRA())) {
//                            RADataCenterMap.get(newDataCenter[0].getRA()).add(newDataCenter[0]);
//                            RADataCenterMapReverse.get(newDataCenter[0].getRA()).add(newDataCenter[0]);
//                        } else {
//                            TreeSet<DataCenter> treeSet = new TreeSet<>();
//                            treeSet.add(newDataCenter[0]);
//                            RADataCenterMap.put(newDataCenter[0].getRA(), treeSet);
//                            RADataCenterMapReverse.put(newDataCenter[0].getRA(), treeSet);
//                        }
////                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,n,newDataCenter,m,maxRA,likeMaxDataCenter);
////                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,newDataCenter,m,minRA,likeMinDataCenter);
//                    }
//                    break;
//                case "DISABLE":
//                    i = Integer.parseInt(eventString[1]);
//                    j = Integer.parseInt(eventString[2]);
//                    if (i < 1 || i > n) return;
//                    if (j < 1 || j > m) return;
//
////                    minRA[0] = minRADataCenter[0].getRA();
////                    maxRA[0] = maxRADataCenter[0].getRA();
//
//                    if (dataCenterMap.containsKey(i)) {
//                        DataCenter[] currentDataCenter = {dataCenterMap.get(i)};
//                        if (RADataCenterMap.containsKey(currentDataCenter[0].getRA())) {
//                            RADataCenterMap.get(currentDataCenter[0].getRA()).remove(currentDataCenter[0]);
//                            RADataCenterMapReverse.get(currentDataCenter[0].getRA()).remove(currentDataCenter[0]);
//                        }
//                        currentDataCenter[0].disableServer(j);
//
//                        if (RADataCenterMap.containsKey(currentDataCenter[0].getRA())) {
//                            RADataCenterMap.get(currentDataCenter[0].getRA()).add(currentDataCenter[0]);
//                            RADataCenterMapReverse.get(currentDataCenter[0].getRA()).add(currentDataCenter[0]);
//                        } else {
//                            TreeSet<DataCenter> treeSet = new TreeSet<>();
//                            treeSet.add(currentDataCenter[0]);
//                            RADataCenterMap.put(currentDataCenter[0].getRA(), treeSet);
//                            RADataCenterMapReverse.put(currentDataCenter[0].getRA(), treeSet);
//                        }
////                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,n,currentDataCenter,m,maxRA,likeMaxDataCenter);
////                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,currentDataCenter,m,minRA,likeMinDataCenter);
//                    } else {
//                        System.out.println("here");
//                        DataCenter[] newDataCenter = {new DataCenter(i, m, 0L, m)};
//                        newDataCenter[0].disableServer(j);
//                        dataCenterMap.put(i, newDataCenter[0]);
//
//                        if (RADataCenterMap.containsKey(newDataCenter[0].getRA())) {
//                            RADataCenterMap.get(newDataCenter[0].getRA()).add(newDataCenter[0]);
//                            RADataCenterMapReverse.get(newDataCenter[0].getRA()).add(newDataCenter[0]);
//                        } else {
//                            TreeSet<DataCenter> treeSet = new TreeSet<>();
//                            treeSet.add(newDataCenter[0]);
//                            RADataCenterMap.put(newDataCenter[0].getRA(), treeSet);
//                            RADataCenterMapReverse.put(newDataCenter[0].getRA(), treeSet);
//                        }
////                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,n,newDataCenter,m,maxRA,likeMaxDataCenter);
////                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,newDataCenter,m,minRA,likeMinDataCenter);
//                    }
//                    break;
//                case "GETMAX":
//                    int numberMaxDataCenter = -1;
//
//                    TreeMap<Long, TreeSet<DataCenter>> RADataCenterMapReverse2 = new TreeMap<>(RADataCenterMapReverse);
//
//                    System.out.println("RADataCenterMapReverse = " + RADataCenterMapReverse);
//                    System.out.println("RADataCenterMapReverse2 = " + RADataCenterMapReverse2);
//
//                    for (Long l : RADataCenterMapReverse.keySet()) {
//                        if (!RADataCenterMapReverse2.containsKey(l)) continue;
//                        TreeSet<DataCenter> setDC = RADataCenterMapReverse2.get(l);
//                        if (setDC == null || setDC.isEmpty()) {
//                            RADataCenterMapReverse2.remove(l);
//                            RADataCenterMap.remove(l);
//                            continue;
//                        }
//                        numberMaxDataCenter = setDC.first().getNumber();
//                    }
//                    RADataCenterMapReverse = RADataCenterMapReverse2;
//
//
////                    for (int k = 0; k < RADataCenterMap.size()-1; k++) {
////                        if (RADataCenterMap.get(RADataCenterMap.lastKey() - k).isEmpty())
////                            continue;
////                        numberMaxDataCenter = RADataCenterMap.get(RADataCenterMap.lastKey() - k).first().getNumber();
////                    }
////                    int numberMaxDataCenter = maxRADataCenter[0].getNumber();
//                    writer.write(String.valueOf(numberMaxDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//                case "GETMIN":
//                    int numberMinDataCenter = -1;
//
//                    TreeMap<Long, TreeSet<DataCenter>> RADataCenterMap2 = new TreeMap<>(RADataCenterMap);
//
//                    for (Long l : RADataCenterMap.keySet()) {
//                        if (!RADataCenterMap2.containsKey(l)) continue;
//                        TreeSet<DataCenter> setDC = RADataCenterMap2.get(l);
//                        if (setDC == null || setDC.isEmpty()) {
//                            RADataCenterMap2.remove(l);
//                            RADataCenterMapReverse.remove(l);
//                            continue;
//                        }
//                        numberMinDataCenter = setDC.first().getNumber();
//                    }
//                    RADataCenterMap = RADataCenterMap2;
//
////                    int numberMinDataCenter = -1;
////                    for (int k = 0; k < RADataCenterMap.size()-1; k++) {
////                        if (RADataCenterMap.get(RADataCenterMap.firstKey() + k).isEmpty())
////                            continue;
////                        numberMinDataCenter = RADataCenterMap.get(RADataCenterMap.firstKey() + k).first().getNumber();
////                        break;
////                    }
////                    int numberMinDataCenter = minRADataCenter[0].getNumber();
//                    writer.write(String.valueOf(numberMinDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//            }
////            System.out.println("dataCenterMap = " + dataCenterMap);
////            System.out.println("RADataCenterMap = " + RADataCenterMap);
////            System.out.println("RADataCenterMapReverse = " + RADataCenterMapReverse);
//
////            System.out.println("MIN DC = " + minRADataCenter[0].getNumber() + ", RA = " + minRADataCenter[0].getRA());
////            System.out.println("MAX DC = " + maxRADataCenter[0].getNumber() + ", RA = " + maxRADataCenter[0].getRA());
////            System.out.println("--------------");
//        }
////        System.out.println("DC 4 RA = " + dataCenterMap.get(4).getRA());
//        reader.close();
//        writer.close();
//
//    }

    public static void checkMax(long[] maxRA, DataCenter[] maxDataCenter, TreeSet<DataCenter> likeMaxDataCenter, TreeMap<Integer, DataCenter> dataCenterMap, int n, DataCenter[] currentDataCenter, int m, long[] minRA, TreeSet<DataCenter> likeMinDataCenter) {
//        System.out.println("--------------------- checkMax --------------------------");
//        System.out.println("currentDataCenter = " + currentDataCenter[0].getNumber());
//        System.out.println("currentDataCenter.getRA = " + currentDataCenter[0].getRA());
//        System.out.println("maxRA = " + maxRA[0]);
//        System.out.println("maxDataCenter = " + maxDataCenter[0].getNumber());
//        System.out.println("maxDataCenter.getRA = " + maxDataCenter[0].getRA());
//        System.out.println("likeMaxDataCenter = " + likeMaxDataCenter);
//        System.out.println("dataCenterMap: " + dataCenterMap);
//        System.out.println("--------------------- идёт проверка --------------------------");

        if (currentDataCenter[0].getRA() > minRA[0])
            likeMinDataCenter.remove(currentDataCenter[0]);

        if (currentDataCenter[0] != maxDataCenter[0]) {
            if (currentDataCenter[0].getRA() > maxRA[0]) {
                maxDataCenter[0] = currentDataCenter[0];
                likeMaxDataCenter.clear();
                maxRA[0] = maxDataCenter[0].getRA();
            } else if (currentDataCenter[0].getRA() == maxRA[0]) {
                if (currentDataCenter[0].getNumber() < maxDataCenter[0].getNumber()) {
                    likeMaxDataCenter.add(maxDataCenter[0]);
                    maxDataCenter[0] = currentDataCenter[0];
                } else {
                    likeMaxDataCenter.add(currentDataCenter[0]);
                }
            }
//            System.out.println("---перед return: ");
//            System.out.println("currentDataCenter = " + currentDataCenter[0].getNumber());
//            System.out.println("currentDataCenter.getRA = " + currentDataCenter[0].getRA());
//            System.out.println("maxRA = " + maxRA[0]);
//            System.out.println("maxDataCenter = " + maxDataCenter[0].getNumber());
//            System.out.println("maxDataCenter.getRA = " + maxDataCenter[0].getRA());
//            System.out.println("likeMaxDataCenter = " + likeMaxDataCenter);
//            System.out.println("dataCenterMap: " + dataCenterMap);
            return;
        }

        if (currentDataCenter[0] == maxDataCenter[0]) {
            if (currentDataCenter[0].getRA() > maxRA[0]) {
                maxRA[0] = currentDataCenter[0].getRA();
                likeMaxDataCenter.clear();
            }
            if (currentDataCenter[0].getRA() < maxRA[0]) {
                if (!likeMaxDataCenter.isEmpty()) {
                    maxDataCenter[0] = likeMaxDataCenter.first();
                    likeMaxDataCenter.remove(maxDataCenter[0]);
                } else {
                    long max = currentDataCenter[0].getRA(); // было: long max = 0;
                    int minNumber = n + 1; // дописал: + 1
                    for (DataCenter dc : dataCenterMap.values()) {
                        if (dc.getRA() > max) {
//                            if (dc.getNumber() < minNumber) {
                            minNumber = dc.getNumber();
                            max = dc.getRA();
//                            }
                        }
                    }
                    if (minNumber < n + 1)
                        maxDataCenter[0] = dataCenterMap.get(minNumber);
                    maxRA[0] = maxDataCenter[0].getRA();
                    findLikeMax(maxDataCenter, dataCenterMap, likeMaxDataCenter);
                }
            }
        }
//        System.out.println("currentDataCenter = " + currentDataCenter[0].getNumber());
//        System.out.println("currentDataCenter.getRA = " + currentDataCenter[0].getRA());
//        System.out.println("maxRA = " + maxRA[0]);
//        System.out.println("maxDataCenter = " + maxDataCenter[0].getNumber());
//        System.out.println("maxDataCenter.getRA = " + maxDataCenter[0].getRA());
//        System.out.println("likeMaxDataCenter = " + likeMaxDataCenter);
//        System.out.println("dataCenterMap: " + dataCenterMap);

        /*
если текущий == максимальный
    если RA текущего стало > maxRA
        maxRA = RA текущего
        список похожих_на_максимальный очищается
    если RA текущего стало < maxRA
        если есть похожие_на_максимальный
            максимальный = то похожий_на_максимальный с самым маленьким номером
            удаляем максимальный ДЦ из списка похожих

        если нет похожих_на_максимальный
            то проходимся по списку всех участников событий
                максимальный ДЦ = ДЦ с минимальным номером среди тех, у кого самый большой RA
                maxRA = RA максимального ДЦ
         */

    }

    public static void checkMin(long[] minRA, DataCenter[] minDataCenter, TreeSet<DataCenter> likeMinDataCenter, TreeMap<Integer, DataCenter> dataCenterMap, int n, DataCenter[] currentDataCenter, int m, long[] maxRA, TreeSet<DataCenter> likeMaxDataCenter) {

//        System.out.println("--------------------- checkMin --------------------------");
//        System.out.println("currentDataCenter = " + currentDataCenter[0].getNumber());
//        System.out.println("currentDataCenter.getRA = " + currentDataCenter[0].getRA());
//        System.out.println("minRA = " + minRA[0]);
//        System.out.println("minDataCenter = " + minDataCenter[0].getNumber());
//        System.out.println("minDataCenter.getRA = " + minDataCenter[0].getRA());
//        System.out.println("likeMinDataCenter = " + likeMinDataCenter);
//        System.out.println("dataCenterMap: " + dataCenterMap);
//        System.out.println("--------------------- идёт проверка --------------------------");

        if (currentDataCenter[0].getRA() < maxRA[0])
            likeMaxDataCenter.remove(currentDataCenter[0]);

        if (currentDataCenter[0] != minDataCenter[0]) {
            if (currentDataCenter[0].getRA() < minRA[0]) {
                minDataCenter[0] = currentDataCenter[0];
                minRA[0] = minDataCenter[0].getRA();
                likeMinDataCenter.clear();
            } else if (currentDataCenter[0].getRA() == minRA[0]) {
                if (currentDataCenter[0].getNumber() < minDataCenter[0].getNumber()) {
                    likeMinDataCenter.add(minDataCenter[0]);
                    minDataCenter[0] = currentDataCenter[0];
                } else {
                    likeMinDataCenter.add(currentDataCenter[0]);
                }
            }
//            System.out.println("---перед return: ");
//            System.out.println("currentDataCenter = " + currentDataCenter[0].getNumber());
//            System.out.println("currentDataCenter.getRA = " + currentDataCenter[0].getRA());
//            System.out.println("minRA = " + minRA[0]);
//            System.out.println("minDataCenter = " + minDataCenter[0].getNumber());
//            System.out.println("minDataCenter.getRA = " + minDataCenter[0].getRA());
//            System.out.println("likeMinDataCenter = " + likeMinDataCenter);
//            System.out.println("dataCenterMap: " + dataCenterMap);
            return;
        }

        if (currentDataCenter[0] == minDataCenter[0]) {
            if (currentDataCenter[0].getRA() < minRA[0]) {
                minRA[0] = currentDataCenter[0].getRA();
                likeMinDataCenter.clear();
            } else if (currentDataCenter[0].getRA() > minRA[0]) {
                if (!likeMinDataCenter.isEmpty()) {
                    minDataCenter[0] = likeMinDataCenter.first();
                    likeMinDataCenter.remove(minDataCenter[0]);
                    minRA[0] = minDataCenter[0].getRA();
                } else {
                    if (dataCenterMap.size() < n) {
                        for (int i = 1; i <= n; i++) {
                            if (!dataCenterMap.containsKey(i)) {
                                minDataCenter[0] = new DataCenter(i, m, 0L, m);
                                minRA[0] = minDataCenter[0].getRA();
                                dataCenterMap.put(i, minDataCenter[0]);
                                break;
                            }
                            if (dataCenterMap.containsKey(i)) {
                                if (dataCenterMap.get(i).getRA() == minRA[1]) {
                                    minDataCenter[0] = dataCenterMap.get(i);
                                    minRA[0] = minDataCenter[0].getRA();
                                    findLikeMin(minDataCenter, dataCenterMap, likeMinDataCenter);
                                }
                            }
                        }
                    } else {
                        long min = currentDataCenter[0].getRA();
                        int minNumber = n + 1;
                        for (DataCenter dc : dataCenterMap.values()) {
                            if (dc.getRA() < min) {
//                                if (dc.getNumber() < minNumber) {
                                min = dc.getRA();
                                minNumber = dc.getNumber();
//                                }
                            }
                        }
                        if (minNumber < n + 1)
                            minDataCenter[0] = dataCenterMap.get(minNumber);
                        minRA[0] = minDataCenter[0].getRA();
                        findLikeMin(minDataCenter, dataCenterMap, likeMinDataCenter);
                    }
                }
            }
        }
//        System.out.println("currentDataCenter = " + currentDataCenter[0].getNumber());
//        System.out.println("currentDataCenter.getRA = " + currentDataCenter[0].getRA());
//        System.out.println("minRA = " + minRA[0]);
//        System.out.println("minDataCenter = " + minDataCenter[0].getNumber());
//        System.out.println("minDataCenter.getRA = " + minDataCenter[0].getRA());
//        System.out.println("likeMinDataCenter = " + likeMinDataCenter);
//        System.out.println("dataCenterMap: " + dataCenterMap);

    }

    private static void findLikeMax(DataCenter[] maxDataCenter, TreeMap<Integer, DataCenter> dataCenterMap, TreeSet<DataCenter> likeMaxDataCenter) {
        long max = maxDataCenter[0].getRA();
        likeMaxDataCenter.clear();
        for (DataCenter dc : dataCenterMap.values()) {
            if (dc == maxDataCenter[0])
                continue;
            if (dc.getRA() == max)
                likeMaxDataCenter.add(dc);
        }
    }

    private static void findLikeMin(DataCenter[] minDataCenter, TreeMap<Integer, DataCenter> dataCenterMap, TreeSet<DataCenter> likeMinDataCenter) {
        long min = minDataCenter[0].getRA();
        likeMinDataCenter.clear();
        for (DataCenter dc : dataCenterMap.values()) {
            if (dc == minDataCenter[0])
                continue;
            if (dc.getRA() == min)
                likeMinDataCenter.add(dc);
        }
    }


}

class DataCenter implements Comparable {
    private int number;
    private int countServers;
    private long reboots;
    private int availableServers;
    private Set<Integer> serversOfflineSet;
    private long RA;

    public DataCenter(int number, int countServers, long reboots, int availableServers) {
        this.number = number;
        this.countServers = countServers;
        this.reboots = reboots;
        this.availableServers = availableServers;
        this.serversOfflineSet = new HashSet<>();
        updateRA();
    }

    public void resetDataCenter() {
//        System.out.print("В дата центре № " + this.number + " перезагрузка. ");
        reboots++;
        serversOfflineSet.clear();
        availableServers = countServers;
//        System.out.println("Сейчас оффлан сервера: " + serversOfflineSet);
        updateRA();
    }

    public void disableServer(int number) {
//        System.out.print("В дата центре № " + this.number + " выключили сервер " + number);
//        System.out.println(". Сейчас оффлан сервера: " + serversOfflineSet);
        if (!serversOfflineSet.contains(number)) {
            serversOfflineSet.add(number);
            availableServers--;
            updateRA();
        }
    }

    public void updateRA() {
        this.RA = reboots * availableServers;
//        System.out.println("В дата центре № " + this.number + ", RA = " + RA);
    }

    public int getNumber() {
        return number;
    }

    public long getRA() {
        return RA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataCenter that = (DataCenter) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public int compareTo(Object o) {
        DataCenter dataCenterB = (DataCenter) o;
        return this.number - dataCenterB.getNumber();
    }
}


/*

3 3 12
DISABLE 1 2
DISABLE 2 1
DISABLE 3 3
GETMAX
RESET 1
RESET 2
DISABLE 1 2
DISABLE 1 3
DISABLE 2 2
GETMAX
RESET 3
GETMIN




  start  DISABLE(1 2)  DISABLE(2 1)  DISABLE(3 3)  GETMAX  RESET(1)  RESET(2) DISABLE(1 2)  DISABLE(1 3)  DISABLE(2 2)  GETMAX  RESET(3)  GETMIN
1   0                                                 0!       3                   2            1                          1                 1
2   0                                                 0                 3                                       2          2                 2
3   0                                                 0                                                                    0        3        3


------------------------------------------------------------------------------------------------------------------------------------------------------

5 3 12
DISABLE 1 2
DISABLE 2 1
DISABLE 3 3
GETMAX
RESET 1
RESET 2
DISABLE 1 2
DISABLE 1 3
DISABLE 2 2
GETMAX
RESET 3
GETMIN



  start  DISABLE(1 2)  DISABLE(2 1)  DISABLE(3 3)  GETMAX  RESET(1)  RESET(2) DISABLE(1 2)  DISABLE(1 3)  DISABLE(2 2)  GETMAX  RESET(3)  GETMIN
1   0       0                                         0         3                   2           1                           1               1
2   0                       0                         0                 3                                       2           2               2
3   0                                     0           0                                                                     0       3       3
4   0                                                 0                                                                     0               0
5   0                                                 0                                                                     0               0





 */


/*

Ввод:
5 5 10
RESET 1
RESET 2
RESET 3
RESET 4
GETMAX
GETMIN
DISABLE 1 1
DISABLE 2 1
GETMAX
GETMIN

Вывод:
1
5
3
5

----

Ввод:
10 5 19
RESET 1
RESET 2
RESET 3
RESET 4
RESET 5
RESET 6
RESET 7
RESET 8
RESET 10
GETMAX
GETMIN
RESET 9
DISABLE 6 1
DISABLE 2 1
DISABLE 3 1
DISABLE 4 1
DISABLE 5 1
GETMAX
GETMIN

Вывод:
1
9
1
2

----

Ввод:

10 5 30
RESET 1
RESET 2
RESET 3
RESET 4
RESET 5
RESET 6
RESET 7
RESET 8
RESET 10
GETMAX
GETMIN
RESET 9
DISABLE 6 1
DISABLE 2 1
DISABLE 3 1
DISABLE 4 1
DISABLE 5 1
GETMAX
GETMIN
DISABLE 2 1
GETMAX
GETMIN
DISABLE 2 4
GETMAX
GETMIN
RESET 2
DISABLE 2 1
RESET 3
GETMAX
GETMIN

Вывод:

1
9
1
2
1
2
1
2
1
3

-----------------------
test6.txt
Ввод:

10 5 33
RESET 1
RESET 2
RESET 3
RESET 4
RESET 5
RESET 6
RESET 7
RESET 8
RESET 10
GETMAX
GETMIN
RESET 9
DISABLE 6 1
DISABLE 2 1
DISABLE 3 1
DISABLE 4 1
DISABLE 5 1
GETMAX
GETMIN
DISABLE 2 1
GETMAX
GETMIN
DISABLE 2 4
GETMAX
GETMIN
RESET 2
DISABLE 2 1
RESET 3
GETMAX
GETMIN
RESET 1
GETMAX
GETMIN

Вывод:

1
9
1
2
1
2
1
2
3
1
1
4



 */
