//package День_Рождения_Васи;
//
//import java.io.*;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.*;
//
//public class Temp2 {
//}
//
//package День_Рождения_Васи;
//
//        import java.io.BufferedReader;
//        import java.io.BufferedWriter;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.io.OutputStreamWriter;
//
//        import java.math.RoundingMode;
//        import java.text.NumberFormat;
//        import java.text.DecimalFormat;
//        import java.util.*;
//        import java.math.BigDecimal;
//        import java.util.regex.Pattern;
//        import java.util.regex.Matcher;
//
//public class VasyasBirthday {
//    public static void main(String[] args) throws IOException {
//
////        String pa = "3.12345";
////        String tmp;
////
////        if (pa.contains(".")) {
////            tmp = pa.substring(pa.indexOf(".") + 1);
////            System.out.println(tmp);
////            if (tmp.length() > 6)
////                return;
////        }
////        if (pa.contains(",")) {
////            tmp = pa.substring(pa.indexOf(",") + 1);
////            if (tmp.length() > 6)
////                return;
////        }
//
////        Pattern pat = Pattern.compile("^[a-z0-9_]+$");
////        Matcher matc;
////        String sss = "_b_s_";
////        matc = pat.matcher(sss);
////        if (!matc.matches())
////            System.out.println("Не подходит");
//
////        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
////        numberFormat.setRoundingMode(RoundingMode.FLOOR);
////        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
////        decimalFormat.applyPattern("#.###");
//
////        List<String> dat = new ArrayList<>();
////
////        double a = 0.50000002;
////        double b = 54.467777;
////        double c = 2.25550;
////        double d = 3.300;
////        double e = 5.550000;
////        double f = 4.44440000;
//
////        Double doub = new Double(decimalFormat.format(a));
////
////        System.out.println(new Double(decimalFormat.format(a)));
////        System.out.println(new Double(decimalFormat.format(b)));
////        System.out.println(new Double(decimalFormat.format(c)));
////        System.out.println(new Double(decimalFormat.format(d)));
////        System.out.println(new Double(decimalFormat.format(e)));
////        System.out.println(new Double(decimalFormat.format(f)));
//
////        dat.add(String.valueOf(a));
////        dat.add(String.valueOf(b));
////        dat.add(String.valueOf(c));
////        dat.add(String.valueOf(d));
////        dat.add(String.valueOf(e));
//////        dat.add(String.valueOf(f));
////
////        for (String s : dat)
////            System.out.println(s);
//
////        int nu = 0;
////
////        for (String str : dat) {
////            if(str.contains(".")) {
////                str = str.substring(str.indexOf(".")+1);
////                if (nu < str.length())
////                    nu = Math.min(str.length(), 3);
////            }
////        }
//
////        System.out.println(nu);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
////        Pattern pattern = Pattern.compile("^[a-z0-9_]+$");
////        Matcher matcher;
//
//        // список блюд
//        List<Dish> dishes = new ArrayList<>();
//
//        // каталог цен
//        Map<String,IngredientInPriceCat> priceCatalog = new HashMap<>();
//
//        // каталог продуктов
//        Map<String,IngredientInFoodCat> foodCatalog = new HashMap<>();
//
//        // количество блюд, которые решил приготовить Вася
//        int n = Integer.parseInt(reader.readLine());
//        if (n < 1 || n > 1000) return;
//
//        for (int i = 0; i < n; i++) {
//            String[] parts = reader.readLine().split(" ");
//
//            // название блюда
//            String dish = parts[0];
//            if (dish.length() > 20) return;
////            matcher = pattern.matcher(dish);
////            if (!matcher.matches()) return;
//
//            // количество друзей, которые хотят попробовать
//            int countFriends = Integer.parseInt(parts[1]);
//            if (countFriends < 1 || countFriends > 100) return;
//
//            // количество ингредиентов, нужных для блюда
//            int needIngredients = Integer.parseInt(parts[2]);
//            if (needIngredients < 1 || needIngredients > 100) return;
//
//            Dish currentDish = new Dish(dish,countFriends,needIngredients);
//
//            // описание ингредиентов
//            for (int j = 0; j < needIngredients; j++) {
//                String[] partsIngr = reader.readLine().split(" ");
//
//                // название ингредиента
//                String ingredient = partsIngr[0];
//                if (ingredient.length() > 20) return;
////                matcher = pattern.matcher(ingredient);
////                if (!matcher.matches()) return;
//
//                // требуемое количество ингредиента
//                int[] countNeeds = new int[1];
//                countNeeds[0] = Integer.parseInt(partsIngr[1]);
//                if (countNeeds[0] < 1 || countNeeds[0] > 1000) return;
//
//                // название единицы измерения количества (enum Units)
//                String u = partsIngr[2];
//
//                Units unit = toSameUnit(u,countNeeds);
//
//                currentDish.getIngredients().add(new Dish.Ingredient(ingredient,countNeeds[0],unit));
//            }
//
//            dishes.add(currentDish);
//        }
//
//        // создаём каталог цен
//        // количество позиций в каталоге
//        int countIngredientsInPriceC = Integer.parseInt(reader.readLine());
//        if (countIngredientsInPriceC < 1 || countIngredientsInPriceC > 1000) return;
//
//        for (int i = 0; i < countIngredientsInPriceC; i++) {
//            String[] parts = reader.readLine().split(" ");
//
//            // название ингредиента
//            String name = parts[0];
//            if (name.length() > 20) return;
////            matcher = pattern.matcher(name);
////            if (!matcher.matches()) return;
//
//            // стоимость ингредиента
//            int price = Integer.parseInt(parts[1]);
//            if (price < 1 || price > 1000) return;
//
//            // количество в одной упаковке в единицах
//            int[] countInPack = {Integer.parseInt(parts[2])};
//            if (countInPack[0] < 1 || countInPack[0] > 1000) return;
//
//            // единица измерения количества
//            String u = parts[3];
//
//            Units unit = toSameUnit(u,countInPack);
//
//            priceCatalog.put(name,new IngredientInPriceCat(name, price, countInPack[0], unit));
//        }
//
//        // создаём каталог продуктов
//        int countIngredientsInFoodC = Integer.parseInt(reader.readLine());
//        if (countIngredientsInFoodC < 1 || countIngredientsInFoodC > 1000) return;
//
//        for (int i = 0; i < countIngredientsInFoodC; i++) {
//            String[] parts = reader.readLine().split(" ");
//
//            // название ингредиента
//            String name = parts[0];
//            if (name.length() > 20) return;
////            matcher = pattern.matcher(name);
////            if (!matcher.matches()) return;
//
//            // количество ингредиента
//            int[] count = {Integer.parseInt(parts[1])};
//            if (count[0] < 1 || count[0] > 1000) return;
//
//            // единица измерения ингредиента
//            String u = parts[2];
//
//            String temp;
//
//            // количество белков
//            double protein = Double.parseDouble(parts[3]);
//            if (protein < 0 || protein > 1000.0) return;
//            if (parts[3].contains(".")) {
//                temp = parts[3].substring(parts[3].indexOf(".") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//            if (parts[3].contains(",")) {
//                temp = parts[3].substring(parts[3].indexOf(",") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//
//            // количество жиров
//            double fats = Double.parseDouble(parts[4]);
//            if (fats < 0 || fats > 1000.0) return;
//            if (parts[4].contains(".")) {
//                temp = parts[4].substring(parts[4].indexOf(".") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//            if (parts[4].contains(",")) {
//                temp = parts[4].substring(parts[4].indexOf(",") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//
//            // количество углеводов
//            double carbohydrates = Double.parseDouble(parts[5]);
//            if (carbohydrates < 0 || carbohydrates > 1000.0) return;
//            if (parts[5].contains(".")) {
//                temp = parts[5].substring(parts[5].indexOf(".") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//            if (parts[5].contains(",")) {
//                temp = parts[5].substring(parts[5].indexOf(",") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//
//            // энергетическая ценность
//            double energyValue = Double.parseDouble(parts[6]);
//            if (energyValue < 0 || energyValue > 10000.0) return;
//            if (parts[6].contains(".")) {
//                temp = parts[6].substring(parts[6].indexOf(".") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//            if (parts[6].contains(",")) {
//                temp = parts[6].substring(parts[6].indexOf(",") + 1);
//                if (temp.length() > 6)
//                    return;
//            }
//
//            Units unit = toSameUnit(u,count);
//
//            foodCatalog.put(name,new IngredientInFoodCat(name, count[0], unit, protein, fats, carbohydrates, energyValue));
//        }
//        reader.close();
//
//        // ОТСЮДА НАЧИНАЕТСЯ БИЗНЕС-ЛОГИКА
//
//        // проверяем, чтобы все корректность единиц измерения
////        for (Dish dish : dishes) {
////            List<Dish.Ingredient> ingredients = dish.getIngredients();
////            for (Dish.Ingredient ingredient : ingredients) {
////                String name = ingredient.getName();
////                if (!ingredient.getUnit().equals(priceCatalog.get(name).getUnit()) ||
////                        !ingredient.getUnit().equals(foodCatalog.get(name).getUnit()) ||
////                        !priceCatalog.get(name).getUnit().equals(foodCatalog.get(name).getUnit())) {
//////                    System.out.println("Некорректные единицы измерения для продукта " + name);
////                    return;
////                }
////            }
////        }
//
//        // список блюд
////        List<Dish> dishes
////
//        // каталог цен
////        Map<String,IngredientInPriceCat> priceCatalog
////
//        // каталог продуктов
////        Map<String,IngredientInFoodCat> foodCatalog
//
//        int amountMoney = 0;
//
//        // список блюд
////        dishes — отсюда считаем какое нужно количество каждого ингредиента
//
//        // каталог цен
////        priceCatalog — отсюда считаем сколько надо упаковок и какая у них цена
//
//        // каталог продуктов
////        foodCatalog — отсюда берём энергетическую ценность (указана на то количество, которое указано в этом же каталоге)
//
//        // блюдо - список ингредиентов и их полное количество
//        Map<Dish,Map<String,Integer>> dishAndICount = new HashMap<>();
//
//        // сколько нужно каждого ингредиента для всего количество всех блюд
//        Map<String, Integer> mapIngredientFullCount = new HashMap<>();
//
////        // энергетическая ценность каждого блюда
////        Map<String,String> dishEnergyValue = new HashMap<>();
//
//        // для каждого блюда
//        for (Dish dish : dishes) {
////            System.out.println();
////            System.out.println("Для " + dish.getName() + " нужны:");
//            // создаём каррту название-количество
//            Map<String, Integer> tempMap = new HashMap<>();
//            // сколько порций надо приготовить
//            int multiply = dish.getCount();
//
//            // для каждого ингредиента нужного для блюда
//            for (Dish.Ingredient ingredient : dish.getIngredients()) {
////                System.out.println(ingredient.getName());
//                // считаем, какое количество нужно, чтобы приготовить нужное число порций
//                int summaryCount = ingredient.getNeedsCount() * multiply;
//
//                if (mapIngredientFullCount.containsKey(ingredient.getName())) {
//                    mapIngredientFullCount.put(ingredient.getName(), mapIngredientFullCount.get(ingredient.getName()) + summaryCount);
//                } else {
//                    mapIngredientFullCount.put(ingredient.getName(), summaryCount);
//                }
//                tempMap.put(ingredient.getName(), ingredient.getNeedsCount());
//            }
//            dishAndICount.put(dish,tempMap);
//        }
//
//        List<String> result = new ArrayList<>();
//
//        // считаем, сколько нужно упаковок каждого ингредиента и стоимость
//        for (String ingredient : mapIngredientFullCount.keySet()) {
//            int needsCount = mapIngredientFullCount.get(ingredient);
//
//            IngredientInPriceCat ingredientPC = priceCatalog.remove(ingredient);
//
//            int packCount = needsCount / ingredientPC.getCountInPack();
//            if (needsCount % ingredientPC.getCountInPack() > 0)
//                packCount++;
//
//            amountMoney += packCount * ingredientPC.getPrice();
//
//            result.add(ingredient + " " + packCount);
//        }
//        for (String ingredient : priceCatalog.keySet()) {
//            result.add(ingredient + " 0");
//        }
//
////        int num;
//        // считаем состав и энергетическую ценность каждого блюда
//        for (Dish dish : dishAndICount.keySet()) {
////            System.out.println();
////            System.out.println(dish.getName() + " содержит:");
//            Map<String,Integer> compound = dishAndICount.get(dish);
//
//            BigDecimal protein = new BigDecimal("0.0");
//            BigDecimal fats = new BigDecimal("0.0");
//            BigDecimal carbohydrates = new BigDecimal("0.0");
//            BigDecimal energyValue = new BigDecimal("0.0");
//            List<String> data = new ArrayList<>();
//
//            for (String name : compound.keySet()) {
////                System.out.println(name);
//                int count = compound.get(name);
//                IngredientInFoodCat ingredientFC = foodCatalog.get(name);
//
////                protein += (ingredientFC.getProtein() / ingredientFC.getCount()) * count;
////                fats += (ingredientFC.getFats() / ingredientFC.getCount()) * count;
////                carbohydrates += (ingredientFC.getCarbohydrates() / ingredientFC.getCount()) * count;
////                energyValue += (ingredientFC.getEnergyValue() / ingredientFC.getCount()) * count;
//
////                System.out.println("protein = " + ingredientFC.getProtein());
////                System.out.println("getCount = " + ingredientFC.getCount());
////                System.out.println("count = " + count);
//
//                protein = protein.add(new BigDecimal((ingredientFC.getProtein() / ingredientFC.getCount()*count)));
//                fats = fats.add(new BigDecimal((ingredientFC.getFats() / ingredientFC.getCount()) * count));
//                carbohydrates = carbohydrates.add(new BigDecimal((ingredientFC.getCarbohydrates() / ingredientFC.getCount()) * count));
//                energyValue = energyValue.add(new BigDecimal((ingredientFC.getEnergyValue() / ingredientFC.getCount()) * count));
//
////                System.out.println("proteinResult = " + protein);
////                System.out.println();
//
////                protein = protein.add(BigDecimal.valueOf(ingredientFC.getProtein()));
////                protein = protein.divide(BigDecimal.valueOf(ingredientFC.getCount()));
////                protein = protein.multiply(BigDecimal.valueOf(count));
////
////                fats = fats.add(BigDecimal.valueOf(ingredientFC.getFats()));
////                fats = fats.divide(BigDecimal.valueOf(ingredientFC.getCount()));
////                fats = fats.multiply(BigDecimal.valueOf(count));
////
////                carbohydrates = carbohydrates.add(BigDecimal.valueOf(ingredientFC.getCarbohydrates()));
////                carbohydrates = carbohydrates.divide(BigDecimal.valueOf(ingredientFC.getCount()));
////                carbohydrates = carbohydrates.multiply(BigDecimal.valueOf(count));
////
////                energyValue = energyValue.add(BigDecimal.valueOf(ingredientFC.getEnergyValue()));
////                energyValue = energyValue.divide(BigDecimal.valueOf(ingredientFC.getCount()));
////                energyValue = energyValue.multiply(BigDecimal.valueOf(count));
//
//            }
//
////            Locale.setDefault(Locale.ENGLISH);
////            data.add(String.valueOf(Double.parseDouble(String.format("%f",protein))));
////            data.add(String.valueOf(Double.parseDouble(String.format("%f",fats))));
////            data.add(String.valueOf(Double.parseDouble(String.format("%f",carbohydrates))));
////            data.add(String.valueOf(Double.parseDouble(String.format("%f",energyValue))));
////            data.add(String.format("%f",protein));
////            data.add(String.format("%f",fats));
////            data.add(String.format("%f",carbohydrates));
////            data.add(String.format("%f",energyValue));
//
////            num = 0;
//
////            for (String str : data) {
//////                System.out.println("str = " + str);
////                if(str.contains(".")) {
////                    str = str.substring(str.indexOf(".")+1);
//////                    System.out.println("str = " + str);
////                    if (num < str.length())
////                        num = Math.min(str.length(), 3);
////                }
////            }
//
////            System.out.println("num = " + num);
////            String format = "%s %." + num + "f %." + num + "f %." + num + "f %." + num + "f";
////            Locale.setDefault(Locale.ENGLISH);
////            String value = String.format(format, dish.getName(), protein, fats, carbohydrates, energyValue);
////            String value2 = String.format("%s %f %f %f %f", dish.getName(), protein, fats, carbohydrates, energyValue);
//
//            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
//            numberFormat.setRoundingMode(RoundingMode.FLOOR);
//            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
//            decimalFormat.applyPattern("#.###");
//
////            String value = dish.getName() +
////                    " " + new Double(decimalFormat.format(protein)) +
////                    " " + new Double(decimalFormat.format(fats)) +
////                    " " + new Double(decimalFormat.format(carbohydrates)) +
////                    " " + new Double(decimalFormat.format(energyValue));
//
//            String value = dish.getName() +
//                    " " + new BigDecimal(decimalFormat.format(protein)) +
//                    " " + new BigDecimal(decimalFormat.format(fats)) +
//                    " " + new BigDecimal(decimalFormat.format(carbohydrates)) +
//                    " " + new BigDecimal(decimalFormat.format(energyValue));
//
//
////            String value = dish.getName() + " " +
////                    protein + " " +
////                    fats + " " +
////                    carbohydrates + " " +
////                    energyValue;
//
//            result.add(value);
//        }
//
//        writer.write(String.valueOf(amountMoney));
//        writer.newLine();
//        for (String str : result) {
//            writer.write(str);
//            writer.newLine();
//        }
//
//        writer.close();
//
//    }
//
//    private static Units toSameUnit(String u, int[] countNeeds) {
//        switch (u) {
//            case "kg":
//                countNeeds[0] *= 1000;
//                return Units.g;
//            case "l":
//                countNeeds[0] *= 1000;
//                return Units.ml;
//            case "tens":
//                countNeeds[0] *= 10;
//                return Units.cnt;
//        }
//        return Units.valueOf(u);
//    }
//}
//
//class Dish {
//    private String name;
//    private int count;
//    private int countIngredients;
//    private List<Ingredient> ingredients;
//
//    public Dish(String name, int count, int countIngredients) {
//        this.name = name;
//        this.count = count;
//        this.countIngredients = countIngredients;
//        this.ingredients = new ArrayList<>();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public int getCountIngredients() {
//        return countIngredients;
//    }
//
//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    static class Ingredient {
//        private String name;
//        private int needsCount;
//        private Units unit;
//
//        public Ingredient(String name, int needsCount, Units unit) {
//            this.name = name;
//            this.needsCount = needsCount;
//            this.unit = unit;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public int getNeedsCount() {
//            return needsCount;
//        }
//
//        public Units getUnit() {
//            return unit;
//        }
//    }
//}
//
//class IngredientInFoodCat {
//    private String name;
//    private int count;
//    private Units unit;
//    private double protein;
//    private double fats;
//    private double carbohydrates;
//    private double energyValue;
//
//    public IngredientInFoodCat(String name, int count, Units unit, double protein, double fats, double carbohydrates, double energyValue) {
//        this.name = name;
//        this.count = count;
//        this.unit = unit;
//        this.protein = protein;
//        this.fats = fats;
//        this.carbohydrates = carbohydrates;
//        this.energyValue = energyValue;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public Units getUnit() {
//        return unit;
//    }
//
//    public double getProtein() {
//        return protein;
//    }
//
//    public double getFats() {
//        return fats;
//    }
//
//    public double getCarbohydrates() {
//        return carbohydrates;
//    }
//
//    public double getEnergyValue() {
//        return energyValue;
//    }
//}
//
//class IngredientInPriceCat {
//    private String name;
//    private int price;
//    private int countInPack;
//    private Units unit;
//
//    public IngredientInPriceCat(String name, int price, int countInPack, Units unit) {
//        this.name = name;
//        this.price = price;
//        this.countInPack = countInPack;
//        this.unit = unit;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public int getPrice() {
//        return this.price;
//    }
//
//    public int getCountInPack() {
//        return this.countInPack;
//    }
//
//    public Units getUnit() {
//        return this.unit;
//    }
//
//}
//
//
//
//enum Units {
//    g, kg,
//    l, ml,
//    cnt, tens;
//}
//
//
///*
//
//    g, kg — граммы и килограммы, соответственно;
//    l, ml — литры и миллилитры, соответственно;
//    cnt, tens — одна штука и десять штук, соответственно.
//
//
//ВЫВОД:
//
//1. количество денег
//2. название ингредиента (из первого каталога) [пробел] количество упаковок, которое надо купить
//3. название блюда [пробел] белки [пробел] жиры [пробел] углеводы [пробел] энергетическая ценность
//
//
//
// */