package Средний_элемент;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = reader.readLine();
        reader.close();
        Pattern pattern = Pattern.compile("^-?\\d+[ ]-?\\d+[ ]-?\\d+$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) return;

        String[] numbers = input.split(" ");
        int a = Integer.parseInt(numbers[0]);
        if (a < -1000 || a > 1000) return;
        int b = Integer.parseInt(numbers[1]);
        if (b < -1000 || b > 1000) return;
        int c = Integer.parseInt(numbers[2]);
        if (c < -1000 || c > 1000) return;

        int min = a;
        int middle = b;
        int max = c;
        if (c < a) {
            min = c;
            max = a;
        }
        if (b < min) {
            middle = min;
            min = b;
        }
        if (b > max) {
            middle = max;
            max = b;
        }

        writer.write(String.valueOf(middle));
        writer.close();

    }
}
