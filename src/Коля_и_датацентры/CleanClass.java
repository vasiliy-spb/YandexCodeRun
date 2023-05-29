package Коля_и_датацентры;

import java.io.*;
import java.util.*;

public class CleanClass {
}

//public class KolyaAndDataCenters {
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
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
//        long[] maxRA = {0};
//        long[] minRA = {0,0};
//
//        TreeMap<Integer,DataCenter> dataCenterMap = new TreeMap<>();
//
//        DataCenter basicDataCenter = new DataCenter(1,m,0L,m);
//        dataCenterMap.put(1,basicDataCenter);
//
//        DataCenter[] maxRADataCenter = {basicDataCenter};
//        DataCenter[] minRADataCenter = {basicDataCenter};
//
//        TreeSet<DataCenter> likeMaxDataCenter = new TreeSet<>();
//        TreeSet<DataCenter> likeMinDataCenter = new TreeSet<>();
//
//
//        // в последующих q строках записаны события
//        for (int iq = 0; iq < q; iq++) {
//            String[] eventString = reader.readLine().split(" ");
//            int i;
//            int j;
//            switch (eventString[0]) {
//                case "RESET":
//                    i = Integer.parseInt(eventString[1]);
//                    if (i < 1 || i > n) return;
//
//                    if (dataCenterMap.containsKey(i)) {
//                        DataCenter currentDataCenter = dataCenterMap.get(i);
//                        currentDataCenter.rebootDataCenter();
//                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,currentDataCenter,dataCenterMap);
//                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,currentDataCenter,dataCenterMap,n,m);
//                    } else {
//                        DataCenter newDataCenter = new DataCenter(i,m,0L,m);
//                        newDataCenter.rebootDataCenter();
//                        dataCenterMap.put(i,newDataCenter);
//                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,newDataCenter,dataCenterMap);
//                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,newDataCenter,dataCenterMap,n,m);
//                    }
//                    break;
//                case "DISABLE":
//                    i = Integer.parseInt(eventString[1]);
//                    j = Integer.parseInt(eventString[2]);
//                    if (i < 1 || i > n) return;
//                    if (j < 1 || j > m) return;
//
//                    if (dataCenterMap.containsKey(i)) {
//                        DataCenter currentDataCenter = dataCenterMap.get(i);
//                        currentDataCenter.offServer(j);
//                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,currentDataCenter,dataCenterMap);
//                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,currentDataCenter,dataCenterMap,n,m);
//                    } else {
//                        DataCenter newDataCenter = new DataCenter(i,m,0L,m);
//                        newDataCenter.offServer(j);
//                        dataCenterMap.put(i,newDataCenter);
//                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,newDataCenter,dataCenterMap);
//                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,newDataCenter,dataCenterMap,n,m);
//                    }
//                    break;
//                case "GETMAX":
//                    int numberMaxDataCenter = maxRADataCenter[0].getNumber();
//                    writer.write(String.valueOf(numberMaxDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//                case "GETMIN":
//                    refreshMin(minRA,dataCenterMap,minRADataCenter,n,m);
//                    int numberMinDataCenter = minRADataCenter[0].getNumber();
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
//    private static void refreshMin(long[] minRA, TreeMap<Integer, DataCenter> dataCenterTreeMap, DataCenter[] minDataCenter, int n, int m) {
//        if (minRA[0] == minRA[1]) return;
//        if (minRA[1] < minRA[0] && dataCenterTreeMap.size() < n) {
//            for (int i = 1; i <= n; i++) {
//                if (!dataCenterTreeMap.containsKey(i)) {
//                    minDataCenter[0] = new DataCenter(i, m, 0L, m);
//                    dataCenterTreeMap.put(i, minDataCenter[0]);
//                    minRA[0] = minDataCenter[0].getRA();
//                    break;
//                }
//            }
//        }
//    }
//
//
//    public static void checkMaxRA(long[] maxRA, DataCenter[] maxDataCenter, TreeSet<DataCenter> likeMaxDataCenterSet, DataCenter currentDataCenter, TreeMap<Integer,DataCenter> dataCenterTreeMap) {
//        if (maxRA[0] < currentDataCenter.getRA()) {
//            maxRA[0] = currentDataCenter.getRA();
//            maxDataCenter[0] = currentDataCenter;
//            likeMaxDataCenterSet.clear();
//        } else if (maxRA[0] == currentDataCenter.getRA() && maxDataCenter[0] != currentDataCenter) {
//            if (currentDataCenter.getNumber() < maxDataCenter[0].getNumber()) {
//                likeMaxDataCenterSet.add(maxDataCenter[0]);
//                maxDataCenter[0] = currentDataCenter;
//            } else {
//                likeMaxDataCenterSet.add(currentDataCenter);
//            }
//        }
//
//        if (maxRA[0] > maxDataCenter[0].getRA()) {
//            if (!likeMaxDataCenterSet.isEmpty()) {
//                maxDataCenter[0] = likeMaxDataCenterSet.first();
//                likeMaxDataCenterSet.remove(maxDataCenter[0]);
//            } else {
//                long a = -1;
//                for (DataCenter dc : dataCenterTreeMap.values())
//                    if (dc.getRA() > a)
//                        maxDataCenter[0] = dc;
//            }
//            maxRA[0] = maxDataCenter[0].getRA();
//        }
//    }
//
//
//    public static void checkMinRA(long[] minRA, DataCenter[] minDataCenter, TreeSet<DataCenter> likeMinDataCenterSet, DataCenter currentDataCenter,TreeMap<Integer,DataCenter>dataCenterTreeMap, int n, int m) {
//        if (minRA[0] > currentDataCenter.getRA()) {
//            minRA[0] = currentDataCenter.getRA();
//            minDataCenter[0] = currentDataCenter;
//            likeMinDataCenterSet.clear();
//        } else if (minRA[0] == currentDataCenter.getRA() && minDataCenter[0] != currentDataCenter) {
//            if (currentDataCenter.getNumber() < minDataCenter[0].getNumber()) {
//                likeMinDataCenterSet.add(minDataCenter[0]);
//                minDataCenter[0] = currentDataCenter;
//            } else {
//                likeMinDataCenterSet.add(currentDataCenter);
//            }
//        }
//        if (minRA[0] < minDataCenter[0].getRA()) {
//            if (!likeMinDataCenterSet.isEmpty()) {
//                minDataCenter[0] = likeMinDataCenterSet.first();
//            } else {
//                long a = Long.MAX_VALUE;
//                for (DataCenter dc : dataCenterTreeMap.values())
//                    if (dc.getRA() < a)
//                        minDataCenter[0] = dc;
//            }
//            minRA[0] = minDataCenter[0].getRA();
//        }
//
//        refreshMin(minRA,dataCenterTreeMap,minDataCenter,n,m);
//    }
//
//}
//
//class DataCenter implements Comparable{
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
//    public void rebootDataCenter() {
//        reboots += 1L;
//        serversOfflineSet.clear();
//        availableServers = countServers;
//        updateRA();
//    }
//
//    public void offServer(int number) {
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
//    public Set<Integer> getServersOfflineSet() {
//        return serversOfflineSet;
//    }
//
//    public long getReboots() {
//        return reboots;
//    }
//
//    public int getAvailableServers() {
//        return availableServers;
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
//        return Objects.hash(number);
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        DataCenter dataCenterB = (DataCenter) o;
//        return this.number - dataCenterB.getNumber();
//    }
//}
