//package Коля_и_датацентры;
//
//import java.io.*;
//
//import java.util.*;
//
//public class DirtyCode {
//}
//
//
//
//
//public class KolyaAndDataCenters {
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Коля_и_датацентры/test.txt")));
////        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
////        DataCenter standartDataCenter = new DataCenter(0,m);
//        long[] maxRA = {0};
//        long[] minRA = {0,0};
//
//        TreeMap<Integer,DataCenter> dataCenterMap = new TreeMap<>();
//
//        DataCenter[] basicDataCenter = {new DataCenter(1, m, 0L, m)};
//        dataCenterMap.put(1,basicDataCenter[0]);
//
//        DataCenter[] maxRADataCenter = {basicDataCenter[0]};
//        DataCenter[] minRADataCenter = {basicDataCenter[0]};
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
//                        DataCenter[] currentDataCenter = {dataCenterMap.get(i)};
//                        currentDataCenter[0].rebootDataCenter();
//
//                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,basicDataCenter,n,currentDataCenter,m);
//                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,currentDataCenter,m);
////                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,currentDataCenter,dataCenterMap);
////                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,currentDataCenter,dataCenterMap,n,m);
//                    } else {
//                        DataCenter[] newDataCenter = {new DataCenter(i, m, 0L, m)};
//                        newDataCenter[0].rebootDataCenter();
//                        dataCenterMap.put(i,newDataCenter[0]);
//
//                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,basicDataCenter,n,newDataCenter,m);
//                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,newDataCenter,m);
////                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,newDataCenter,dataCenterMap);
////                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,newDataCenter,dataCenterMap,n,m);
//                    }
//                    break;
//                case "DISABLE":
//                    i = Integer.parseInt(eventString[1]);
//                    j = Integer.parseInt(eventString[2]);
//                    if (i < 1 || i > n) return;
//                    if (j < 1 || j > m) return;
//
//                    if (dataCenterMap.containsKey(i)) {
////                        System.out.println("DISABLE " + i + " " + j);
//                        DataCenter[] currentDataCenter = {dataCenterMap.get(i)};
//                        currentDataCenter[0].offServer(j);
//
//                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,basicDataCenter,n,currentDataCenter,m);
//                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,currentDataCenter,m);
////                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,currentDataCenter,dataCenterMap);
////                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,currentDataCenter,dataCenterMap,n,m);
//                    } else {
//                        DataCenter[] newDataCenter = {new DataCenter(i, m, 0L, m)};
//                        newDataCenter[0].offServer(j);
//                        dataCenterMap.put(i,newDataCenter[0]);
//
//                        checkMin(minRA,minRADataCenter,likeMinDataCenter,dataCenterMap,basicDataCenter,n,newDataCenter,m);
//                        checkMax(maxRA,maxRADataCenter,likeMaxDataCenter,dataCenterMap,n,newDataCenter,m);
////                        checkMaxRA(maxRA,maxRADataCenter,likeMaxDataCenter,newDataCenter,dataCenterMap);
////                        checkMinRA(minRA,minRADataCenter,likeMinDataCenter,newDataCenter,dataCenterMap,n,m);
//                    }
//                    break;
//                case "GETMAX":
//                    int numberMaxDataCenter = maxRADataCenter[0].getNumber();
//                    writer.write(String.valueOf(numberMaxDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//                case "GETMIN":
////                    refreshMin(minRA,dataCenterMap,minRADataCenter,n,m);
//                    int numberMinDataCenter = minRADataCenter[0].getNumber();
//                    writer.write(String.valueOf(numberMinDataCenter));
//                    writer.newLine();
//                    writer.flush();
//                    break;
//            }
////            System.out.println("dataCenterMap = " + dataCenterMap);
////            if (dataCenterMap.containsKey(4)) System.out.println("min = " + minRADataCenter[0].getNumber() + ", 4 = " + dataCenterMap.get(4).getRA());
////            if (dataCenterMap.containsKey(5)) System.out.println("min = " + minRADataCenter[0].getNumber() + ", 5 = " + dataCenterMap.get(5).getRA());
////            System.out.println("MINcenter.Number " + minRADataCenter[0].getNumber() + " его RA = " + minRADataCenter[0].getRA());
////            System.out.println("MAXcenter.Number " + maxRADataCenter[0].getNumber() + " его RA = " + maxRADataCenter[0].getRA());
//        }
//        reader.close();
//        writer.close();
//
//    }
//
//    public static void checkMax(long[] maxRA, DataCenter[] maxDataCenter, TreeSet<DataCenter> likeMaxDataCenter, TreeMap<Integer,DataCenter> dataCenterMap, int n, DataCenter[] currentDataCenter, int m) {
//
//        if (currentDataCenter[0] != maxDataCenter[0]) {
//            if (currentDataCenter[0].getRA() > maxRA[0]) {
//                maxDataCenter[0] = currentDataCenter[0];
//                likeMaxDataCenter.clear();
//                maxRA[0] = maxDataCenter[0].getRA();
//            } else if (currentDataCenter[0].getRA() == maxRA[0]) {
//                if (currentDataCenter[0].getNumber() < maxDataCenter[0].getNumber()) {
//                    likeMaxDataCenter.add(maxDataCenter[0]);
//                    maxDataCenter[0] = currentDataCenter[0];
//                } else {
//                    likeMaxDataCenter.add(currentDataCenter[0]);
//                }
//            }
//            return;
//        }
//
//        if (currentDataCenter[0] == maxDataCenter[0]) {
//            if (currentDataCenter[0].getRA() > maxRA[0]) {
//                maxRA[0] = currentDataCenter[0].getRA();
//                likeMaxDataCenter.clear();
//            }
//            if (currentDataCenter[0].getRA() < maxRA[0]) {
//                if (!likeMaxDataCenter.isEmpty()) {
//                    maxDataCenter[0] = likeMaxDataCenter.first();
//                    likeMaxDataCenter.remove(maxDataCenter[0]);
//                } else {
//                    long max = 0;
//                    int minNumber = n;
//                    for (DataCenter dc : dataCenterMap.values()) {
//                        if (dc.getRA() >= max) {
//                            if (dc.getNumber() < minNumber) {
//                                minNumber = dc.getNumber();
//                                max = dc.getRA();
//                            }
//                        }
//                    }
//                    maxDataCenter[0] = dataCenterMap.get(minNumber);
//                    maxRA[0] = maxDataCenter[0].getRA();
//
//                    likeMaxDataCenter.clear();
//                    for (DataCenter dc : dataCenterMap.values()) {
//                        if (dc == maxDataCenter[0]) continue;
//                        if (dc.getRA() == max)
//                            likeMaxDataCenter.add(dc);
//                    }
//                }
//            }
//        }
//
//        /*
//если текущий == максимальный
//    если RA текущего стало > maxRA
//        maxRA = RA текущего
//        список похожих_на_максимальный очищается
//    если RA текущего стало < maxRA
//        если есть похожие_на_максимальный
//            максимальный = то похожий_на_максимальный с самым маленьким номером
//            удаляем максимальный ДЦ из списка похожих
//
//        если нет похожих_на_максимальный
//            то проходимся по списку всех участников событий
//                максимальный ДЦ = ДЦ с минимальным номером среди тех, у кого самый большой RA
//                maxRA = RA максимального ДЦ
//         */
//
//    }
//
//    public static void checkMin(long[] minRA, DataCenter[] minDataCenter, TreeSet<DataCenter> likeMinDataCenter, TreeMap<Integer,DataCenter> dataCenterMap, DataCenter[] basicDataCenter, int n, DataCenter[] currentDataCenter, int m) {
//
//        if (currentDataCenter[0] != minDataCenter[0]) {
//            if (currentDataCenter[0].getRA() < minRA[0]) {
//                minDataCenter[0] = currentDataCenter[0];
//                likeMinDataCenter.clear();
//            }
//            if (currentDataCenter[0].getRA() == minRA[0]) {
//                if (currentDataCenter[0].getNumber() < minDataCenter[0].getNumber()) {
//                    likeMinDataCenter.add(minDataCenter[0]);
//                    minDataCenter[0] = currentDataCenter[0];
//                } else {
//                    likeMinDataCenter.add(currentDataCenter[0]);
//                }
//            }
//            return;
//        }
//
//        if (currentDataCenter[0] == minDataCenter[0]) {
//            if (currentDataCenter[0].getRA() < minRA[0]) {
//                minRA[0] = currentDataCenter[0].getRA();
//                likeMinDataCenter.clear();
//            } else if (currentDataCenter[0].getRA() > minRA[0]) {
//                if (!likeMinDataCenter.isEmpty()) {
//                    minDataCenter[0] = likeMinDataCenter.first();
//                    likeMinDataCenter.remove(minDataCenter[0]);
//                    minRA[0] = minDataCenter[0].getRA();
//                } else {
//                    if (dataCenterMap.size() < n) {
//                        for (int i = 1; i <= n; i++) {
//                            if (!dataCenterMap.containsKey(i)) {
////                                basicDataCenter[0] = new DataCenter(i,m,0L,m);
////                                minDataCenter[0] = basicDataCenter[0];
//                                minDataCenter[0] = new DataCenter(i,m,0L,m);
//                                minRA[0] = minDataCenter[0].getRA();
//                                dataCenterMap.put(i,minDataCenter[0]);
//                                break;
//                            }
//                        }
//                    } else {
//                        long min = minRA[0];
//                        int minNumber = n;
//                        for (DataCenter dc : dataCenterMap.values()) {
//                            if (dc.getRA() <= min)
//                                if (dc.getNumber() < minNumber) {
//                                    min = dc.getRA();
//                                    minNumber = dc.getNumber();
//                                }
//                        }
//                        minDataCenter[0] = dataCenterMap.get(minNumber);
//                        minRA[0] = minDataCenter[0].getRA();
//                        likeMinDataCenter.clear();
//                        for (DataCenter dc : dataCenterMap.values()) {
//                            if (dc == minDataCenter[0]) continue;
//                            if (dc.getRA() == min)
//                                likeMinDataCenter.add(dc);
//                        }
//                    }
//                }
//            }
//        }
//
//        /*
//        есть базовый (для сравнения)
//
//если текущий == минимальный
//    если RA текущего стало < minRA
//        minRA = RA текущего
//        список похожих_на_минимальный очищается
//    если RA текущего стало > minRA
//        если есть похожие_на_минимальный
//            минимальный = похожий_на_минимальный с самым маленьким номером
//            удаляем минимальный ДЦ из списка похожих
//            minRA = RA минимального
//        если нет похожие_на_минимальный
//            если список всех участников событий < n (количества ДЦ)
//                то проходимся по списку всех участников событий (int i = 1; i <= n; i++)
//                    если i нету в map_всех_участников_событий
//                        то создаём новый ДЦ с номером i
//                        минимальный ДЦ = новый ДЦ
//                        minRA = RA минимального ДЦ
//                        кладём новый ДЦ в map_всех_участников_событий
//                        break; (обязательно!)
//            если список всех участников событий == n (количества ДЦ)
//                то проходимся по списку всех участников событий (int i = 1; i <= n; i++)
//                    минимальный ДЦ = ДЦ с минимальным номером среди тех, у кого самый маленький RA
//                    minRA = RA минимального ДЦ
//         */
//    }
//
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
////        System.out.print("В дата центре № " + this.number + " перезагрузка. ");
//        reboots += 1L;
//        serversOfflineSet.clear();
//        availableServers = countServers;
//        updateRA();
////        System.out.println("Сейчас оффлан сервера: " + serversOfflineSet);
//    }
//
//    public void offServer(int number) {
//        if (!serversOfflineSet.contains(number)) {
//            serversOfflineSet.add(number);
//            availableServers--;
//            updateRA();
//        }
////        System.out.print("В дата центре № " + this.number + " выключили сервер " + number);
////        System.out.println(". Сейчас оффлан сервера: " + serversOfflineSet);
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
//
//
//
///*
//
//3 3 12
//DISABLE 1 2
//DISABLE 2 1
//DISABLE 3 3
//GETMAX
//RESET 1
//RESET 2
//DISABLE 1 2
//DISABLE 1 3
//DISABLE 2 2
//GETMAX
//RESET 3
//GETMIN
//
//
//RA = reboot * available
//
//RA1 = 0     3   2
//RA2 = 0     3
//RA3 = 0
//
//
//
//
//При событии:
//
//есть базовый (для сравнения)
//
//если текущий != минимальный
//    если RA текущего < minRA
//        текущий становится минимальным
//        список похожих_на_минимальный очищается
//    если RA текущего == minRA
//        если номер текущего < номера минимального, то текущий становится минимальным
//
//если текущий == минимальный
//    если RA текущего стало < minRA
//        minRA = RA текущего
//        список похожих_на_минимальный очищается
//    если RA текущего стало > minRA
//        если есть похожие_на_минимальный
//            минимальный = похожий_на_минимальный с самым маленьким номером
//            удаляем минимальный ДЦ из списка похожих
//            minRA = RA минимального
//        если нет похожие_на_минимальный
//            если список всех участников событий < n (количества ДЦ)
//                то проходимся по списку всех участников событий (int i = 1; i <= n; i++)
//                    если i != map_всех_участников_событий
//                        то создаём новый ДЦ с номером i
//                        минимальный ДЦ = новый ДЦ
//                        minRA = RA минимального ДЦ
//                        кладём новый ДЦ в map_всех_участников_событий
//                        break; (обязательно!)
//            если список всех участников событий == n (количества ДЦ)
//                то проходимся по списку всех участников событий (int i = 1; i <= n; i++)
//                    минимальный ДЦ = ДЦ с минимальным номером среди тех, у кого самый маленький RA
//                    minRA = RA минимального ДЦ
//
//
//если текущий != максимальный
//    если RA текущего > maxRA
//        текущий становится максимальным
//        список похожих_на_максимальный очищается
//        maxRA = RA текущего
//    если RA текущего == maxRA
//        если номер текущего < номера максимального, то текущий становится максимальным
//
//если текущий == максимальный
//    если RA текущего стало > maxRA
//        maxRA = RA текущего
//        список похожих_на_максимальный очищается
//    если RA текущего стало < maxRA
//        если есть похожие_на_максимальный
//            максимальный = то похожий_на_максимальный с самым маленьким номером
//            удаляем максимальный ДЦ из списка похожих
//        если нет похожих_на_максимальный
//            то проходимся по списку всех участников событий
//                максимальный ДЦ = ДЦ с минимальным номером среди тех, у кого самый большой RA
//                maxRA = RA максимального ДЦ
//
//
//
//
//
//
//
//
//
//
// */
