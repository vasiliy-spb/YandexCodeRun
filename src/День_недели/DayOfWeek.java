package День_недели;

import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.util.Locale;

public class DayOfWeek {
    public static void main(String[] args) throws IOException {

        File inFile = new File("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/День_недели/input.txt");
        File exitFile = new File("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/День_недели/output.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        PrintWriter printWriter = new PrintWriter(exitFile);
//        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d LLLL yyyy", Locale.ENGLISH);
        DateFormat df = DateFormat.getInstance();
        while(reader.ready()) {
            LocalDate date = LocalDate.parse(reader.readLine(),dtf);

            String dayOfWeek = date.getDayOfWeek().toString();
            String result;
            switch (dayOfWeek) {
                case "MONDAY":
                    result = "Monday";
                    break;
                case "TUESDAY":
                    result = "Tuesday";
                    break;
                case "WEDNESDAY":
                    result = "Wednesday";
                    break;
                case "THURSDAY":
                    result = "Thursday";
                    break;
                case "FRIDAY":
                    result = "Friday";
                    break;
                case "SATURDAY":
                    result = "Saturday";
                    break;
                case "SUNDAY":
                    result = "Sunday";
                    break;
                default:
                    return;
            }
            printWriter.println(result);
        }
        reader.close();
        printWriter.close();






//        File file = new File("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/День_недели/input.txt");
//        File outFile = new File("output.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//
////        List<String> dates = new ArrayList<>();
////        while (reader.ready()) {
////            dates.add(reader.readLine());
////        }
////        reader.close();
//
////        for (String s : dates)
////            System.out.println("Строки: " + s);
//
////        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
//        PrintWriter printWriter = new PrintWriter(outFile);
//
//
////        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        while (reader.ready()) {
//            String[] parts = reader.readLine().split(" ");
//            int day = Integer.parseInt(parts[0]);
//            if (day < 1 || day > 31) return;
//            int year = Integer.parseInt(parts[2]);
//            if (year < 1980 || year > 2100) return;
//            switch (parts[1]) {
//                case "January":
//                    parts[1] = "-1-";
//                    break;
//                case "February":
//                    if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
//                        if (day > 29)
//                            return;
//                    } else {
//                        if (day > 28)
//                            return;
//                    }
//                    parts[1] = "-2-";
//                    break;
//                case "March":
//                    parts[1] = "-3-";
//                    break;
//                case "April":
//                    if (day > 30)
//                        return;
//                    parts[1] = "-4-";
//                    break;
//                case "May":
//                    parts[1] = "-5-";
//                    break;
//                case "June":
//                    if (day > 30)
//                        return;
//                    parts[1] = "-6-";
//                    break;
//                case "July":
//                    parts[1] = "-7-";
//                    break;
//                case "August":
//                    parts[1] = "-8-";
//                    break;
//                case "September":
//                    if (day > 30)
//                        return;
//                    parts[1] = "-9-";
//                    break;
//                case "October":
//                    parts[1] = "-10-";
//                    break;
//                case "November":
//                    if (day > 30)
//                        return;
//                    parts[1] = "-11-";
//                    break;
//                case "December":
//                    parts[1] = "-12-";
//                    break;
//                default:
//                    return;
//            }
//            String resultDate = parts[0] + parts[1] + parts[2];
//
//            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//            Date d;
//            try {
//                d = format.parse(resultDate);
//            } catch (ParseException pe) {
////                System.out.println("Here");
//                return;
//            }
//
//            int dayOfWeek = d.getDay();
//            String result = "";
//
//            switch (dayOfWeek) {
//                case 1:
//                    result = "Monday";
//                    break;
//                case 2:
//                    result = "Tuesday";
//                    break;
//                case 3:
//                    result = "Wednesday";
//                    break;
//                case 4:
//                    result = "Thursday";
//                    break;
//                case 5:
//                    result = "Friday";
//                    break;
//                case 6:
//                    result = "Saturday";
//                    break;
//                case 0:
//                    result = "Sunday";
//                    break;
//            }
////            System.out.println("День недели: " + result);
//            printWriter.println(result);
////            writer.write(result + "\n");
////            writer.flush();
//        }
//        reader.close();
////        writer.close();
//        printWriter.close();

    }
}
