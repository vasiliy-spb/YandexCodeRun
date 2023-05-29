package День_Рождения_Васи;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.math.RoundingMode;
import java.util.*;
import java.math.BigDecimal;

public class VasyasBirthday {
    public static void main(String[] args) throws IOException {

//        BigDecimal bd = new BigDecimal("3.777777777");
//        System.out.println(bd);
//        bd = bd.setScale(3,RoundingMode.DOWN);
//        System.out.println(bd);
//
//        if (5 > 3) return;


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // список блюд
        List<Dish> dishes = new ArrayList<>();

        // каталог цен
        Map<String,IngredientInPriceCat> priceCatalog = new HashMap<>();

        // каталог продуктов
        Map<String,IngredientInFoodCat> foodCatalog = new HashMap<>();

        // количество блюд, которые решил приготовить Вася
        int n = Integer.parseInt(reader.readLine());
        if (n < 1 || n > 1000) return;

        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().split(" ");

            // название блюда
            String dish = parts[0];
            if (dish.length() > 20) return;

            // количество друзей, которые хотят попробовать
            int countFriends = Integer.parseInt(parts[1]);
            if (countFriends < 1 || countFriends > 100) return;

            // количество ингредиентов, нужных для блюда
            int needIngredients = Integer.parseInt(parts[2]);
            if (needIngredients < 1 || needIngredients > 100) return;

            Dish currentDish = new Dish(dish,countFriends,needIngredients);

            // описание ингредиентов
            for (int j = 0; j < needIngredients; j++) {
                String[] partsIngr = reader.readLine().split(" ");

                // название ингредиента
                String ingredient = partsIngr[0];
                if (ingredient.length() > 20) return;

                // требуемое количество ингредиента
                long[] countNeeds = new long[1];
                countNeeds[0] = Long.parseLong(partsIngr[1]);
                if (countNeeds[0] < 1 || countNeeds[0] > 1000) return;

                // название единицы измерения количества (enum Units)
                String u = partsIngr[2];

                Units unit = toSameUnit(u,countNeeds);

                currentDish.getIngredients().add(new Dish.Ingredient(ingredient,countNeeds[0],unit));
            }

            dishes.add(currentDish);
        }

        // создаём каталог цен
        // количество позиций в каталоге
        int countIngredientsInPriceC = Integer.parseInt(reader.readLine());
        if (countIngredientsInPriceC < 1 || countIngredientsInPriceC > 1000) return;

        for (int i = 0; i < countIngredientsInPriceC; i++) {
            String[] parts = reader.readLine().split(" ");

            // название ингредиента
            String name = parts[0];
            if (name.length() > 20) return;

            // стоимость ингредиента
            int price = Integer.parseInt(parts[1]);
            if (price < 1 || price > 1000) return;

            // количество в одной упаковке в единицах
            long[] countInPack = {Long.parseLong(parts[2])};
            if (countInPack[0] < 1 || countInPack[0] > 1000) return;

            // единица измерения количества
            String u = parts[3];

            Units unit = toSameUnit(u,countInPack);

            priceCatalog.put(name,new IngredientInPriceCat(name, price, countInPack[0], unit));
        }

        // создаём каталог продуктов
        int countIngredientsInFoodC = Integer.parseInt(reader.readLine());
        if (countIngredientsInFoodC < 1 || countIngredientsInFoodC > 1000) return;

        for (int i = 0; i < countIngredientsInFoodC; i++) {
            String[] parts = reader.readLine().split(" ");

            // название ингредиента
            String name = parts[0];
            if (name.length() > 20) return;

            // количество ингредиента
            long[] count = {Long.parseLong(parts[1])};
            if (count[0] < 1 || count[0] > 1000) return;

            // единица измерения ингредиента
            String u = parts[2];

            String temp;

            // количество белков
            BigDecimal protein = new BigDecimal(parts[3]);
            if (protein.doubleValue() < 0 || protein.doubleValue() > 1000.0) return;

            // количество жиров
            BigDecimal fats = new BigDecimal(parts[4]);
            if (fats.doubleValue() < 0 || fats.doubleValue() > 1000.0) return;

            // количество углеводов
            BigDecimal carbohydrates = new BigDecimal(parts[5]);
            if (carbohydrates.doubleValue() < 0 || carbohydrates.doubleValue() > 1000.0) return;

            // энергетическая ценность
            BigDecimal energyValue = new BigDecimal(parts[6]);
            if (energyValue.doubleValue() < 0 || energyValue.doubleValue() > 10000.0) return;

            Units unit = toSameUnit(u,count);

            foodCatalog.put(name,new IngredientInFoodCat(name, count[0], unit, protein, fats, carbohydrates, energyValue));
        }
        reader.close();

        // ОТСЮДА НАЧИНАЕТСЯ БИЗНЕС-ЛОГИКА

//        BigDecimal amountMoney = new BigDecimal("0");
        long amountMoney = 0;

        // список блюд
//        dishes — отсюда считаем какое нужно количество каждого ингредиента

        // каталог цен
//        priceCatalog — отсюда считаем сколько надо упаковок и какая у них цена

        // каталог продуктов
//        foodCatalog — отсюда берём энергетическую ценность (указана на то количество, которое указано в этом же каталоге)

        // блюдо - список ингредиентов и их полное количество
        Map<Dish,Map<String,BigDecimal>> dishAndICount = new HashMap<>();

        // сколько нужно каждого ингредиента для всего количество всех блюд
        Map<String, BigDecimal> mapIngredientFullCount = new HashMap<>();

        // для каждого блюда
        for (Dish dish : dishes) {
            // создаём каррту название-количество
            Map<String, BigDecimal> tempMap = new HashMap<>();
            // сколько порций надо приготовить
            BigDecimal multiply = new BigDecimal(String.valueOf(dish.getCount()));

            // для каждого ингредиента нужного для блюда
            for (Dish.Ingredient ingredient : dish.getIngredients()) {
                // считаем, какое количество нужно, чтобы приготовить нужное число порций
                BigDecimal summaryCount = new BigDecimal(String.valueOf(ingredient.getNeedsCount())).multiply(multiply);

                if (mapIngredientFullCount.containsKey(ingredient.getName())) {
                    mapIngredientFullCount.put(ingredient.getName(), mapIngredientFullCount.get(ingredient.getName()).add(summaryCount));
                } else {
                    mapIngredientFullCount.put(ingredient.getName(), summaryCount);
                }
                tempMap.put(ingredient.getName(), new BigDecimal(String.valueOf(ingredient.getNeedsCount())));
            }
            dishAndICount.put(dish,tempMap);
        }

        List<String> result = new ArrayList<>();

        // считаем, сколько нужно упаковок каждого ингредиента и стоимость
        for (String ingredient : mapIngredientFullCount.keySet()) {
            BigDecimal needsCount = mapIngredientFullCount.get(ingredient);

            IngredientInPriceCat ingredientPC = priceCatalog.remove(ingredient);

            BigDecimal packCount = needsCount.divide(new BigDecimal(String.valueOf(ingredientPC.getCountInPack())),0,RoundingMode.UP);

//            amountMoney = amountMoney.add(packCount.multiply(new BigDecimal(String.valueOf(ingredientPC.getPrice()))));
            amountMoney += packCount.multiply(new BigDecimal(String.valueOf(ingredientPC.getPrice()))).longValue();

            result.add(ingredient + " " + packCount.longValue());
        }
        for (String ingredient : priceCatalog.keySet()) {
            result.add(ingredient + " 0");
        }

        // считаем состав и энергетическую ценность каждого блюда
        for (Dish dish : dishAndICount.keySet()) {
            Map<String,BigDecimal> compound = dishAndICount.get(dish);

            BigDecimal protein = new BigDecimal("0.000");
            BigDecimal fats = new BigDecimal("0.000");
            BigDecimal carbohydrates = new BigDecimal("0.000");
            BigDecimal energyValue = new BigDecimal("0.000");

            for (String name : compound.keySet()) {
                BigDecimal count = compound.get(name);
                IngredientInFoodCat ingredientFC = foodCatalog.get(name);

                protein = protein.add((ingredientFC.getProtein().divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),16,RoundingMode.DOWN)).multiply(count));
                fats = fats.add((ingredientFC.getFats().divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),16,RoundingMode.DOWN)).multiply(count));
                carbohydrates = carbohydrates.add((ingredientFC.getCarbohydrates().divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),16,RoundingMode.DOWN)).multiply(count));
                energyValue = energyValue.add((ingredientFC.getEnergyValue().divide(new BigDecimal(String.valueOf(ingredientFC.getCount())),16,RoundingMode.DOWN)).multiply(count));

            }

//            System.out.println("protein = " + protein);

            protein = protein.setScale(3,RoundingMode.DOWN);
            fats = fats.setScale(3,RoundingMode.DOWN);
            carbohydrates = carbohydrates.setScale(3,RoundingMode.DOWN);
            energyValue = energyValue.setScale(3,RoundingMode.DOWN);

//            System.out.println("protein after scale = " + protein);
//            System.out.println("protein double value = " + protein.doubleValue());
//            System.out.println("protein double value = " + new BigDecimal(String.valueOf(protein.doubleValue())));

            String value = dish.getName() +
                    " " + protein +
                    " " + fats +
                    " " + carbohydrates +
                    " " + energyValue;

            result.add(value);
        }

        writer.write(String.valueOf(amountMoney));
        writer.newLine();
        for (String str : result) {
            writer.write(str);
            writer.newLine();
        }

        writer.close();

    }

    private static Units toSameUnit(String u, long[] countNeeds) {
        switch (u) {
            case "kg":
                countNeeds[0] *= 1000;
                return Units.g;
            case "l":
                countNeeds[0] *= 1000;
                return Units.ml;
            case "tens":
                countNeeds[0] *= 10;
                return Units.cnt;
        }
        return Units.valueOf(u);
    }
}

class Dish {
    private String name;
    private long count;
    private long countIngredients;
    private List<Ingredient> ingredients;

    public Dish(String name, long count, long countIngredients) {
        this.name = name;
        this.count = count;
        this.countIngredients = countIngredients;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public long getCountIngredients() {
        return countIngredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    static class Ingredient {
        private String name;
        private long needsCount;
        private Units unit;

        public Ingredient(String name, long needsCount, Units unit) {
            this.name = name;
            this.needsCount = needsCount;
            this.unit = unit;
        }

        public String getName() {
            return name;
        }

        public long getNeedsCount() {
            return needsCount;
        }

        public Units getUnit() {
            return unit;
        }
    }
}

class IngredientInFoodCat {
    private String name;
    private long count;
    private Units unit;
    private BigDecimal protein;
    private BigDecimal fats;
    private BigDecimal carbohydrates;
    private BigDecimal energyValue;

    public IngredientInFoodCat(String name, long count, Units unit, BigDecimal protein, BigDecimal fats, BigDecimal carbohydrates, BigDecimal energyValue) {
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.energyValue = energyValue;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public Units getUnit() {
        return unit;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public BigDecimal getFats() {
        return fats;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    public BigDecimal getEnergyValue() {
        return energyValue;
    }
}

class IngredientInPriceCat {
    private String name;
    private long price;
    private long countInPack;
    private Units unit;

    public IngredientInPriceCat(String name, long price, long countInPack, Units unit) {
        this.name = name;
        this.price = price;
        this.countInPack = countInPack;
        this.unit = unit;
    }

    public String getName() {
        return this.name;
    }

    public long getPrice() {
        return this.price;
    }

    public long getCountInPack() {
        return this.countInPack;
    }

    public Units getUnit() {
        return this.unit;
    }

}



enum Units {
    g, kg,
    l, ml,
    cnt, tens;
}


/*

    g, kg — граммы и килограммы, соответственно;
    l, ml — литры и миллилитры, соответственно;
    cnt, tens — одна штука и десять штук, соответственно.


ВЫВОД:

1. количество денег
2. название ингредиента (из первого каталога) [пробел] количество упаковок, которое надо купить
3. название блюда [пробел] белки [пробел] жиры [пробел] углеводы [пробел] энергетическая ценность



 */