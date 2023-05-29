//package Коля_и_датацентры;
//
//import java.io.*;
//import java.util.*;
//
//public class ToTest1 {
//}
//
//// Этот код сдавал на проверку (Превышен лимит времени на 71-м закрытом тесте)
//
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
//        // НомерДЦ — ДатаЦентр
//        TreeMap<Integer, DataCenter> dataCenterMap = new TreeMap<>();
//
//        // RA ДатаЦентра — TreeSet<DataCenter> | от меньшего RA к большему
//        TreeMap<Long, TreeSet<DataCenter>> RADataCenterMap = new TreeMap<>();
//
//        // RA ДатаЦентра — TreeSet<DataCenter> | от большего RA к меньшему
//        TreeMap<Long, TreeSet<DataCenter>> RADataCenterMapReverse = new TreeMap<>(Comparator.reverseOrder());
//
//        for (int i = 1; i <= n; i++) {
//            DataCenter dc = new DataCenter(i, m, 0L, m);
//            dataCenterMap.put(i, dc);
//            TreeSet<DataCenter> set = new TreeSet<>((o1,o2)->o1.getNumber()-o2.getNumber());
//            if (RADataCenterMap.containsKey(0L))
//                set = RADataCenterMap.get(0L);
//            set.add(dc);
//            RADataCenterMap.put(0L, set);
//            RADataCenterMapReverse.put(0L, set);
//        }
//
//        // в последующих q строках записаны события
//        for (int iq = 0; iq < q; iq++) {
//            String line = reader.readLine();
//
//            String[] eventString = line.split(" ");
//            int i;
//            int j;
//            DataCenter currentDataCenter;
//            switch (eventString[0]) {
//                case "RESET":
//                    i = Integer.parseInt(eventString[1]);
//                    if (i < 1 || i > n) return;
//
//                    currentDataCenter = dataCenterMap.get(i);
//                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
//                        RADataCenterMap.get(currentDataCenter.getRA()).remove(currentDataCenter);
//                        RADataCenterMapReverse.get(currentDataCenter.getRA()).remove(currentDataCenter);
//                    }
//
//                    currentDataCenter.resetDataCenter();
//
//                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
//                        RADataCenterMap.get(currentDataCenter.getRA()).add(currentDataCenter);
//                        RADataCenterMapReverse.get(currentDataCenter.getRA()).add(currentDataCenter);
//                    } else {
//                        TreeSet<DataCenter> treeSet = new TreeSet<>((o1,o2)->o1.getNumber()-o2.getNumber());
//                        treeSet.add(currentDataCenter);
//                        RADataCenterMap.put(currentDataCenter.getRA(), treeSet);
//                        RADataCenterMapReverse.put(currentDataCenter.getRA(), treeSet);
//                    }
//                    break;
//                case "DISABLE":
//                    i = Integer.parseInt(eventString[1]);
//                    j = Integer.parseInt(eventString[2]);
//                    if (i < 1 || i > n) return;
//                    if (j < 1 || j > m) return;
//
//                    currentDataCenter = dataCenterMap.get(i);
//                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
//                        RADataCenterMap.get(currentDataCenter.getRA()).remove(currentDataCenter);
//                        RADataCenterMapReverse.get(currentDataCenter.getRA()).remove(currentDataCenter);
//                    }
//
//                    currentDataCenter.disableServer(j);
//
//                    if (RADataCenterMap.containsKey(currentDataCenter.getRA())) {
//                        RADataCenterMap.get(currentDataCenter.getRA()).add(currentDataCenter);
//                        RADataCenterMapReverse.get(currentDataCenter.getRA()).add(currentDataCenter);
//                    } else {
//                        TreeSet<DataCenter> treeSet = new TreeSet<>((o1,o2)->o1.getNumber()-o2.getNumber());
//                        treeSet.add(currentDataCenter);
//                        RADataCenterMap.put(currentDataCenter.getRA(), treeSet);
//                        RADataCenterMapReverse.put(currentDataCenter.getRA(), treeSet);
//                    }
//                    break;
//                case "GETMAX":
//                    int numberMaxDataCenter = -1;
//
//                    TreeMap<Long, TreeSet<DataCenter>> RADataCenterMapReverse2 = new TreeMap<>(RADataCenterMapReverse);
//
//                    for (long l : RADataCenterMapReverse.keySet()) {
//                        if (!RADataCenterMapReverse2.containsKey(l)) continue;
//                        TreeSet<DataCenter> setDC = RADataCenterMapReverse.get(l);
//                        if (setDC == null || setDC.isEmpty()) {
//                            RADataCenterMapReverse2.remove(l);
//                            RADataCenterMap.remove(l);
//                            continue;
//                        }
//                        numberMaxDataCenter = setDC.first().getNumber();
//                        break;
//                    }
//                    RADataCenterMapReverse = RADataCenterMapReverse2;
//                    writer.write(String.valueOf(numberMaxDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//                case "GETMIN":
//                    int numberMinDataCenter = -1;
//
//                    TreeMap<Long, TreeSet<DataCenter>> RADataCenterMap2 = new TreeMap<>(RADataCenterMap);
//
//                    for (long l : RADataCenterMap.keySet()) {
//                        if (!RADataCenterMap2.containsKey(l)) continue;
//                        TreeSet<DataCenter> setDC = RADataCenterMap.get(l);
//                        if (setDC == null || setDC.isEmpty()) {
//                            RADataCenterMap2.remove(l);
//                            RADataCenterMapReverse.remove(l);
//                            continue;
//                        }
//                        numberMinDataCenter = setDC.first().getNumber();
//                        break;
//                    }
//                    RADataCenterMap = RADataCenterMap2;
//
//                    writer.write(String.valueOf(numberMinDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//            }
//        }
//        reader.close();
//        writer.close();
//
//    }
//
//}
//
//class DataCenter implements Comparable {
//    private int number;
//    private int countServers;
//    private long reboots;
//    private int availableServers;
//    private Set<Integer> serversOfflineSet;
//    private long RA;
//
//    public DataCenter(int number, int countServers, long reboots, int availableServers) {
//        this.number = number;
//        this.countServers = countServers;
//        this.reboots = reboots;
//        this.availableServers = availableServers;
//        this.serversOfflineSet = new HashSet<>();
//        updateRA();
//    }
//
//    public void resetDataCenter() {
//        reboots++;
//        serversOfflineSet.clear();
//        availableServers = countServers;
//        updateRA();
//    }
//
//    public void disableServer(int number) {
//        if (!serversOfflineSet.contains(number)) {
//            serversOfflineSet.add(number);
//            availableServers--;
//            updateRA();
//        }
//    }
//
//    public void updateRA() {
//        this.RA = reboots * availableServers;
//    }
//
//    public int getNumber() {
//        return number;
//    }
//
//    public long getRA() {
//        return RA;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DataCenter that = (DataCenter) o;
//        return number == that.number;
//    }
//
//    @Override
//    public int hashCode() {
//        return number;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        DataCenter dataCenterB = (DataCenter) o;
//        return this.number - dataCenterB.getNumber();
//    }
//}