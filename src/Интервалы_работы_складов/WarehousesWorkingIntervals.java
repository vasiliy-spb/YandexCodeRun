package Интервалы_работы_складов;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class WarehousesWorkingIntervals {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Интервалы_работы_складов/input4.txt"));
        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Интервалы_работы_складов/output.txt"));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Map<Integer, Set<Warehouse>> warehouseMap = new TreeMap<>();

        while (reader.ready()) {
            String[] fields = reader.readLine().split(",");
            int i = Integer.parseInt(fields[0]);
            if (i < 0 || i > 9) return;
            convertToOutFormat(i, fields, dtf, warehouseMap);
        }
        reader.close();
        for (int i : warehouseMap.keySet()) {
            Set<Warehouse> warehouseSet = warehouseMap.get(i);
            warehouseSet = mergeNewIntersection2(warehouseSet);
//            System.out.println("———————————————————————————————————");
            for (Warehouse warehouse : warehouseSet) {
//                System.out.println("должен напечатать в файл: " + warehouse);
                writer.write(warehouse.toString());
                writer.newLine();
            }
        }
        writer.close();
    }

    private static Set<Warehouse> mergeNewIntersection2(Set<Warehouse> warehouseSet) {
        Set<Warehouse> result = new TreeSet<>(new Comparator<Warehouse>() {
            @Override
            public int compare(Warehouse w1, Warehouse w2) {
                if (w1.getId() == w2.getId()) {
                    if (w1.getType().ordinal() == w2.getType().ordinal()) {
                        if (w1.getStart().toEpochDay() == w2.getStart().toEpochDay()) {
                            return 0;
                        } else {
                            return (int) (w1.getStart().toEpochDay() - w2.getStart().toEpochDay());
                        }
                    } else {
                        return w1.getType().ordinal() - w2.getType().ordinal();
                    }
                } else {
                    return w1.getId() - w2.getId();
                }
            }
        });
        for (Warehouse currentWarehouse : warehouseSet) {
//            System.out.println("Внешний цикл: " + currentWarehouse);
            result.add(currentWarehouse);
//            System.out.println("добавил текущий");
            for (Warehouse warehouse : warehouseSet) {
//                System.out.println("Внутренний цикл: " + warehouse);
                if (currentWarehouse.equals(warehouse)) {
                    continue;
                }
                if (currentWarehouse.getType().equals(warehouse.getType())) {
                    LocalDate start = warehouse.getStart();
                    LocalDate finish = warehouse.getFinish();
                    LocalDate neoStart = currentWarehouse.getStart();
                    LocalDate neoFinish = currentWarehouse.getFinish();

                    if (neoFinish.isBefore(start) || neoStart.isAfter(finish)) {
                        continue;
                    }

                    if (neoStart.isBefore(start) && neoFinish.isAfter(finish)) {
                        warehouse.setStart(neoStart);
                        warehouse.setFinish(neoFinish);
//                        warehouseIterator.remove();
//                        System.out.println("удалил текущий");
                        result.remove(currentWarehouse);
                        result.add(warehouse);
                        continue;
                    }
                    if (neoStart.isBefore(start) && neoFinish.toEpochDay() <= finish.toEpochDay()) {
                        warehouse.setStart(neoStart);
//                        warehouseIterator.remove();
//                        System.out.println("удалил текущий");
                        result.remove(currentWarehouse);
                        result.add(warehouse);
                        continue;
                    }
                    if (neoStart.toEpochDay() >= start.toEpochDay() && neoFinish.toEpochDay() <= finish.toEpochDay()) {
//                        warehouseIterator.remove();
//                        System.out.println("удалил текущий");
                        result.remove(currentWarehouse);
                        result.add(warehouse);
                        continue;
                    }
                    if (neoStart.toEpochDay() >= start.toEpochDay() && neoFinish.isAfter(finish)) {
                        warehouse.setFinish(neoFinish);
//                        warehouseIterator.remove();
//                        System.out.println("удалил текущий");
                        result.remove(currentWarehouse);
                        result.add(warehouse);
                    }
                }
            }
        }
//        System.out.println("доходит досюда");
        return result;
    }

    private static void mergeNewIntersection(Set<Warehouse> warehouseSet) {
        Iterator<Warehouse> warehouseIterator = warehouseSet.iterator();
        while (warehouseIterator.hasNext()) {
            Warehouse currentWarehouse = warehouseIterator.next();
            for (Warehouse warehouse : warehouseSet) {
                if (currentWarehouse.equals(warehouse))
                    continue;
                if (currentWarehouse.getType().equals(warehouse.getType())) {
                    LocalDate start = warehouse.getStart();
                    LocalDate finish = warehouse.getFinish();
                    LocalDate neoStart = currentWarehouse.getStart();
                    LocalDate neoFinish = currentWarehouse.getFinish();

                    if (neoFinish.isBefore(start) || neoStart.isAfter(finish))
                        continue;

                    if (neoStart.isBefore(start) && neoFinish.isAfter(finish)) {
                        warehouse.setStart(neoStart);
                        warehouse.setFinish(neoFinish);
                        warehouseIterator.remove();
                        continue;
                    }
                    if (neoStart.isBefore(start) && neoFinish.toEpochDay() <= finish.toEpochDay()) {
                        warehouse.setStart(neoStart);
                        warehouseIterator.remove();
                        continue;
                    }
                    if (neoStart.toEpochDay() >= start.toEpochDay() && neoFinish.toEpochDay() <= finish.toEpochDay()) {
                        warehouseIterator.remove();
                        continue;
                    }
                    if (neoStart.toEpochDay() >= start.toEpochDay() && neoFinish.isAfter(finish)) {
                        warehouse.setFinish(neoFinish);
                        warehouseIterator.remove();
                    }
                }
            }
        }
    }


    private static void convertToOutFormat(int id, String[] fields, DateTimeFormatter dtf, Map<Integer, Set<Warehouse>> warehouseMap) {
        String[] periods = fields[1].split(" ");
        WarehouseType currentType;
        Warehouse currentWarehouse = new Warehouse(id, LocalDate.parse(periods[0], dtf), LocalDate.parse(periods[1], dtf));
        switch (fields[2]) {
            case "KGT":
                currentType = WarehouseType.KGT;
                currentWarehouse.setType(currentType);
                mergeIntersection(id, currentWarehouse, warehouseMap);
                break;
            case "COLD":
                currentType = WarehouseType.COLD;
                currentWarehouse.setType(currentType);
                mergeIntersection(id, currentWarehouse, warehouseMap);
                break;
            case "OTHER":
                currentType = WarehouseType.OTHER;
                currentWarehouse.setType(currentType);
                mergeIntersection(id, currentWarehouse, warehouseMap);
                break;
            case "NULL":
                Warehouse warehouse;
                for (int i = 0; i < 3; i++) {
                    warehouse = new Warehouse(currentWarehouse);
                    warehouse.setType(WarehouseType.values()[i]);
                    mergeIntersection(id, warehouse, warehouseMap);
                }
        }
    }

    private static void mergeIntersection(int id, Warehouse currentWarehouse, Map<Integer, Set<Warehouse>> warehouseMap) {
        if (!warehouseMap.containsKey(id)) {
            Set<Warehouse> warehouseSet = new TreeSet<>(new Comparator<Warehouse>() {
                @Override
                public int compare(Warehouse w1, Warehouse w2) {
                    if (w1.getId() == w2.getId()) {
                        if (w1.getType().ordinal() == w2.getType().ordinal()) {
                            if (w1.getStart().toEpochDay() == w2.getStart().toEpochDay()) {
                                return 0;
                            } else {
                                return (int) (w1.getStart().toEpochDay() - w2.getStart().toEpochDay());
                            }
                        } else {
                            return w1.getType().ordinal() - w2.getType().ordinal();
                        }
                    } else {
                        return w1.getId() - w2.getId();
                    }
                }
            });
            warehouseSet.add(currentWarehouse);
            warehouseMap.put(id, warehouseSet);
        } else {
            Set<Warehouse> currentWarehouseSet = warehouseMap.get(id);
            for (Warehouse warehouse : currentWarehouseSet) {
                if (currentWarehouse.getType().equals(warehouse.getType())) {
                    LocalDate start = warehouse.getStart();
                    LocalDate finish = warehouse.getFinish();
                    LocalDate neoStart = currentWarehouse.getStart();
                    LocalDate neoFinish = currentWarehouse.getFinish();

                    if (neoFinish.isBefore(start) || neoStart.isAfter(finish))
                        continue;

                    if (neoStart.isBefore(start) && neoFinish.isAfter(finish)) {
                        warehouse.setStart(neoStart);
                        warehouse.setFinish(neoFinish);
                        return;
                    }
                    if (neoStart.isBefore(start) && neoFinish.toEpochDay() <= finish.toEpochDay()) {
                        warehouse.setStart(neoStart);
                        return;
                    }
                    if (neoStart.toEpochDay() >= start.toEpochDay() && neoFinish.toEpochDay() <= finish.toEpochDay()) {
                        return;
                    }
                    if (neoStart.toEpochDay() >= start.toEpochDay() && neoFinish.isAfter(finish)) {
                        warehouse.setFinish(neoFinish);
                        return;
                    }
                }
            }
            currentWarehouseSet.add(currentWarehouse);
        }
    }
}

class Warehouse {
    private final int id;
    private LocalDate start;
    private LocalDate finish;
    private WarehouseType type;

    public Warehouse(int id, LocalDate start, LocalDate finish, WarehouseType type) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.type = type;
    }

    public Warehouse(int id, LocalDate start, LocalDate finish) {
        this.id = id;
        this.start = start;
        this.finish = finish;
    }

    public Warehouse(Warehouse source) {
        this.id = source.getId();
        this.start = LocalDate.ofEpochDay(source.getStart().toEpochDay());
        this.finish = LocalDate.ofEpochDay(source.getFinish().toEpochDay());
        this.type = source.getType();
    }

    public int getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return id + "," + start + " " + finish + "," + type;
    }
}

enum WarehouseType {
    KGT, COLD, OTHER;
}