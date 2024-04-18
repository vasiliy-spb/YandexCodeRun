package Купоны_на_скидку;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DiscountCoupons {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Купоны_на_скидку/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Купоны_на_скидку/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

//        List<Unit> units = new ArrayList<>();
//        List<Coupon> coupons = new ArrayList<>();

        String[] arr1 = reader.readLine().split(" ");

        // количество товаров
        int n = Integer.parseInt(arr1[0]);
        // количество купонов
        int m = Integer.parseInt(arr1[1]);
        // максимальное количество применимых купонов
        int k = Integer.parseInt(arr1[2]);
        if (n < 1 || n > 100 || m < 1 || m > 20 || 1 > k || k > Math.min(6,m)) return;

        Unit[] unitArray = new Unit[n];
        Coupon[] couponArray = new Coupon[m];

        // стоимость товаров до применения скидок
        String[] cost = reader.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            Unit unit = new Unit();
            unit.setNumber(i+1);
            unit.setCost(Integer.parseInt(cost[i]));
            List<Coupon> couponList = new ArrayList<>();
            String[] array = reader.readLine().split(" ");
            int c = Integer.parseInt(array[0]);
            for (int j = 0; j < c; j++) {
                int couponNumber = Integer.parseInt(array[j+1]);
                Coupon coupon = couponArray[couponNumber-1];
                if (coupon == null) {
//                    System.out.println("Создаём купон номер " + couponNumber);
                    coupon = new Coupon();
                }
                coupon.setNumber(couponNumber);
                TreeSet<Unit> unitList = coupon.getUnits();
                if (unitList == null)
                    unitList = new TreeSet<>((u1,u2) -> u2.getCost() - u1.getCost());
                unitList.add(unit);
                coupon.setUnits(unitList);
                couponArray[couponNumber-1] = coupon;
                couponList.add(coupon);
            }
            unit.setCoupons(couponList);
            unitArray[i] = unit;
        }
        // значения скидок для всех купонов (от 1 до m)
        String[] discount = reader.readLine().split(" ");
        reader.close();

        TreeSet<Coupon> couponsByTotalDiscount = new TreeSet<>((c1,c2) -> c2.getTotalDiscount() - c1.getTotalDiscount());
        for (int i = 0; i < m; i++) {
            Coupon coupon = couponArray[i];
            if (coupon == null) continue;
            int totalDiscount = 0;
            int disc = Integer.parseInt(discount[coupon.getNumber() - 1]);
            if (coupon.getUnits() == null) continue;
            for (Unit unit : coupon.getUnits()) {
                int price = unit.getCost() * disc / 100;
                totalDiscount += price;
                unit.setCost(price);
            }
            coupon.setTotalDiscount(totalDiscount);
            couponsByTotalDiscount.add(coupon);
        }

        int countCoupons = 0;
        StringBuilder result = new StringBuilder();
        for (Coupon coupon : couponsByTotalDiscount) {
            if (k == 0) break;
            countCoupons++;
            result.append(coupon.getNumber());
            result.append(" ");
            k--;
        }

//        System.out.println("—————————————————————");
//        for (Unit unit : unitArray) {
//            System.out.println("Для товара номер " + unit.getNumber() + " стоимостью " + unit.getCost() + " применимы купоны: ");
//            for (Coupon coupon : unit.getCoupons())
//                System.out.println("Купон номер " + coupon.getNumber() + " скидка = " + discount[coupon.getNumber()-1]);
//        }
//        System.out.println("—————————————————————");
//
//        for (int i = 0; i < m; i++) {
//            Coupon coupon = couponArray[i];
//            if (coupon == null) continue;
//            int disc = Integer.parseInt(discount[i]);
//            System.out.println("Скидка = " + disc);
//            if (coupon.getUnits() == null) continue;
//            System.out.println("———");
//            System.out.println("Товары с купоном номер " + coupon.getNumber() + " :");
//            for (Unit unit : coupon.getUnits())
//                System.out.println("Товар номер " + unit.getNumber() + " стоит " + unit.getCost());
//            System.out.println("———");
//
//            if (disc != 0) {
//                Unit unit = coupon.getUnits().first();
//                System.out.println("Стоимость товара номер " + unit.getNumber() + " до скидки = " + unit.getCost());
//                unit.setCost(unit.getCost() - unit.getCost() * disc / 100);
//                System.out.println("Применяем купон номер " + coupon.getNumber() + " к товару номер " + unit.getNumber());
//                System.out.println("Стоимость товара номер " + unit.getNumber() + " после скидки = " + unit.getCost());
//                countCoupons++;
//                result.append(i+1);
//                result.append(" ");
//            }
//            discount[i] = "0";
//        }
        result.insert(0,countCoupons + "\n");
        writer.write(result.toString().trim());
        writer.close();
    }
}

class Unit implements Comparable<Unit> {
    private int number;
    private int cost;
    private List<Coupon> coupons;

    public Unit() {
    }

    public int getNumber() {
        return number;
    }

    public int getCost() {
        return cost;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public int compareTo(Unit anotherUnit) {
        if (number == anotherUnit.getNumber()) {
            return 0;
        } else {
            return cost - anotherUnit.getCost();
        }
    }
}

class Coupon {
    private int number;
    private int discount;
    private TreeSet<Unit> units;
    private int totalDiscount;

    public Coupon() {
    }

    public int getNumber() {
        return number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public TreeSet<Unit> getUnits() {
        return units;
    }

    public void setUnits(TreeSet<Unit> units) {
        this.units = units;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}