package Опять_JSON_перекладывать;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.List;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MergeJSON2 {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Опять_JSON_перекладывать/input.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        if (n < 1 || n > 200) return;
        int m = Integer.parseInt(firstLine[1]);
        if (m < 1 || m > 40000) return;

        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < n; i++) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader.readLine());
            List<JSONObject> objectList = (List<JSONObject>) jsonObject.get("offers");

            for (JSONObject jo : objectList) {
                if (m > 0) {
                    jsonArray.add(jo);
                    m--;
                }
//                System.out.println("offer_id = " + jo.get("offer_id"));
//                System.out.println("market_sku = " + jo.get("market_sku"));
//                System.out.println("price = " + jo.get("price"));
            }
        }
        reader.close();

        JSONObject newObject = new JSONObject();
        newObject.put("offers",jsonArray);
        writer.write(newObject.toJSONString());

        writer.close();
    }
}
