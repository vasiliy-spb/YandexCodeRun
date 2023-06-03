package Программист_на_пляже;

public class CoderOnTheBeach {
    public static void main(String[] args) {

        int a = 0b00000010;
        int b = 0b00000100;

        int result = a ^ b;
        System.out.println(Integer.toBinaryString(result));
    }
}
