import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args){
        int[] array = {1,2,3,4,5,6,7,8,9};

        int result = 0;
        for (int i : array)
            result += i;
        System.out.println(result);

        result = sum(array);
        System.out.println(result);

        // -----------------------------------------------------------------------

        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        list.add("fifth");

        int count = countOfElements(list);

        System.out.println("Количество элементов в списке = " + count);

        // -----------------------------------------------------------------------

        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(-3451);
        numbers.add(13);
        numbers.add(1235);
        numbers.add(18542);
        numbers.add(8888881);
        numbers.add(888);
        numbers.add(4);

        int maxNumber = findMaxNumber(numbers);
        System.out.println("Максимальное число в списке = " + maxNumber);


    }

    public static int sum(int[] array) {
        if (array.length == 0) return 0;
        else return array[0] + sum(Arrays.stream(array).skip(1).toArray());
    }

    public static int countOfElements(List list) {
        if (list.size() == 0) return 0;
        else {
            list.remove(0);
            return 1 + countOfElements(list);
        }
    }

    public static int findMaxNumber(List<Integer> list) {
        int max = list.get(0);
        for (int i : list)
            if (i > max)
                max = i;
        return max;
    }

}
