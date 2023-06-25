package Форматирование_календаря;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class CalendarFormatting {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Форматирование_календаря/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Форматирование_календаря/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        StringBuilder result = new StringBuilder();

        String[] in = reader.readLine().split(" ");
        reader.close();

        int dayOfMonth = Integer.parseInt(in[0]);
        int dayOfWeek = 1;
        int start = 0;

        switch (in[1]) {
            case "Monday":
                start = 1;
                break;
            case "Tuesday":
                start = 0;
                break;
            case "Wednesday":
                start = -1;
                break;
            case "Thursday":
                start = -2;
                break;
            case "Friday":
                start = -3;
                break;
            case "Saturday":
                start = -4;
                break;
            case "Sunday":
                start = -5;
                break;
        }
        for (int i = start; i <= dayOfMonth; i++, dayOfWeek++) {
            if (dayOfWeek > 7) dayOfWeek -= 7;
            if (i > 0) {
                if (i <= 9) {
                    result.append(".");
                }
                result.append(i);
            } else {
                result.append("..");
            }
            if (dayOfWeek == 7) {
                result.append("\n");
            } else {
                result.append(" ");
            }
        }

        writer.write(result.toString());
        writer.close();
    }
}
