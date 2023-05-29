package Сортировка_положительных_чисел_в_обратном_порядке;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SortPositiveNumbers {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String server = reader.readLine();
        String port = reader.readLine();
        String a = reader.readLine();
        String b = reader.readLine();
        reader.close();

        String request = server + ":" + port + "?a=" + a + "&b=" + b;

        URL url = new URL(request);
        URLConnection connection = url.openConnection();

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        List<Integer> numbers = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(input);
        input.close();
        for (int i = 0; i < array.size(); i++) {
            Integer value = ((Long) array.get(i)).intValue();
            if (value > 0)
                numbers.add(value);
        }

        Collections.sort(numbers);
        Collections.reverse(numbers);

        for (Integer i : numbers) {
            writer.write(String.valueOf(i));
            writer.newLine();
        }

        writer.close();
    }
}
