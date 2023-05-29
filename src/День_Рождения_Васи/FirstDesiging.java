//package День_Рождения_Васи;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.*;
//
//public class FirstDesiging {
//}
//
//class Main {
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
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
//
//            // количество ингредиента
//            int[] count = {Integer.parseInt(parts[1])};
//            if (count[0] < 1 || count[0] > 1000) return;
//
//            // единица измерения ингредиента
//            String u = parts[2];
//
//            // количество белков
//            double protein = Double.parseDouble(parts[3]);
//            if (protein < 0 || protein > 1000.0) return;
//
//            // количество жиров
//            double fats = Double.parseDouble(parts[4]);
//            if (fats < 0 || fats > 1000.0) return;
//
//            // количество углеводов
//            double carbohydrates = Double.parseDouble(parts[5]);
//            if (carbohydrates < 0 || carbohydrates > 1000.0) return;
//
//            // энергетическая ценность
//            double energyValue = Double.parseDouble(parts[6]);
//            if (energyValue < 0 || energyValue > 10000.0) return;
//
//            Units unit = toSameUnit(u,count);
//
//            foodCatalog.put(name,new IngredientInFoodCat(name, count[0], unit, protein, fats, carbohydrates, energyValue));
//        }
//        reader.close();
//
//        // ОТСЮДА НАЧИНАЕТСЯ БИЗНЕС-ЛОГИКА
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
//        // считаем состав и энергетическую ценность каждого блюда
//        for (Dish dish : dishAndICount.keySet()) {
////            System.out.println();
////            System.out.println(dish.getName() + " содержит:");
//            Map<String,Integer> compound = dishAndICount.get(dish);
//
//            BigDecimal protein = new BigDecimal("0");
//            BigDecimal fats = new BigDecimal("0");
//            BigDecimal carbohydrates = new BigDecimal("0");
//            BigDecimal energyValue = new BigDecimal("0");
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
//                protein = protein.add(new BigDecimal(String.valueOf(ingredientFC.getProtein())).divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),4, RoundingMode.HALF_UP).multiply(new BigDecimal(String.valueOf(count))));
//                fats = fats.add(new BigDecimal(String.valueOf(ingredientFC.getFats())).divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),4, RoundingMode.HALF_UP).multiply(new BigDecimal(String.valueOf(count))));
//                carbohydrates = carbohydrates.add(new BigDecimal(String.valueOf(ingredientFC.getCarbohydrates())).divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),4, RoundingMode.HALF_UP).multiply(new BigDecimal(String.valueOf(count))));
//                energyValue = energyValue.add(new BigDecimal(String.valueOf(ingredientFC.getEnergyValue())).divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),4, RoundingMode.HALF_UP).multiply(new BigDecimal(String.valueOf(count))));
//
//
//            }
//            protein = protein.setScale(3,RoundingMode.DOWN);
//            fats = fats.setScale(3,RoundingMode.DOWN);
//            carbohydrates = carbohydrates.setScale(3,RoundingMode.DOWN);
//            energyValue = energyValue.setScale(3,RoundingMode.DOWN);
//
//            Locale.setDefault(Locale.ENGLISH);
//            String value = String.format("%s %f %f %f %f", dish.getName(), protein, fats, carbohydrates, energyValue);
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
//
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
