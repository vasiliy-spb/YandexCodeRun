package Библиотека;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Library {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        long readToday = 1;
        long countHappyDay = 0;

        String[] numbers = reader.readLine().split(" ");
        reader.close();
        int k = Integer.parseInt(numbers[0]);
        int m = Integer.parseInt(numbers[1]);
        int d = Integer.parseInt(numbers[2]);
        if (k < 1 || k > 1_000_000_000) return;
        if (m < 0 || m > 1_000_000_000) return;
        if (d < 1 || d > 7) return;

        long availableBook = m;
        long weekK = k * 5L;
        long week1000K = k * 5000L;
        long readWeek = 28L;
        long read1000Week = 0;

        // ------------------
        // медленное решение:
//        while(true) {
//            if (d >= 1 && d <= 5) {
//                availableBook += k;
//            }
//            if (availableBook < readToday) {
//                break;
//            }
//            availableBook -= readToday;
//            countHappyDay++;
//
//            d++;
//            if (d > 7) {
//                d %= 7;
//            }
//            readToday++;
//        }
        // ------------------

//        long time1 = System.currentTimeMillis();

        for (int i = 0; i < 7; i++) {
            if (d >= 1 && d <= 5) {
                availableBook += k;
            }
            if (availableBook < readToday) {
                break;
            }
            availableBook -= readToday;
            countHappyDay++;

            d++;
            if (d > 7) {
                d %= 7;
            }
            readToday++;
        }

//         за тысячу недель
        if (availableBook > readToday) {
            long reservAvailableBook = 0;
            long reservReadToday = 0;
            long reservCountHappyDay = 0;
            for (int i = 0; availableBook >= readToday; countHappyDay += 7000L, i++) {
                reservAvailableBook = availableBook;
                reservReadToday = readToday;
                reservCountHappyDay = countHappyDay;

                availableBook += week1000K;
                readToday += 7000L;
                read1000Week = countFinishedBookFor1000Week(i);
                availableBook -= read1000Week;
            }
            if (availableBook < readToday) {
                availableBook = reservAvailableBook;
                readToday = reservReadToday;
                countHappyDay = reservCountHappyDay;
                readWeek = countFinishedBookForNextWeek(countHappyDay);
            }
        }

        if (availableBook > readToday)
            for (; availableBook >= 0; countHappyDay += 7) {
                availableBook += weekK;
                readToday += 7;
                readWeek = countFinishedBookForNextWeek(countHappyDay);
                availableBook -= readWeek;
            }

        if (availableBook < 0) {
            availableBook += readWeek;
            availableBook -= weekK;
            readToday -= 7;
            countHappyDay -= 7;

            for (int i = 0; i < 7; i++) {
                if (d >= 1 && d <= 5) {
                    availableBook += k;
                }
                if (availableBook < readToday) {
                    break;
                }
                availableBook -= readToday;
                countHappyDay++;

                d++;
                if (d > 7) {
                    d %= 7;
                }
                readToday++;
            }
        }


//        long time2 = System.currentTimeMillis();
//        System.out.println("Time: " + (time2 - time1));

        writer.write(String.valueOf(countHappyDay));
        writer.close();
    }

    // сколько книг будет прочитано за следующие 1000 недель
    public static long countFinishedBookFor1000Week(int i) {
        long a = 8 + i * 7000L;
        long b = a + 6999;
        long result = (a + b) * (b - a + 1) / 2L;
        return result;
    }

    // сколько книг будет прочитано за следующую неделю
    public static long countFinishedBookForNextWeek(long countHappyDay) {
        long a = 1 + countHappyDay;
        long b = a + 6;
        long result = (a + b) * (b - a + 1) / 2L;
        return result;
    }

}

/*


readToday = 7 * 4 - за первую неделю
readToday += 7 * 7 - за вторую неделю
readToday += 7 * 7 - за третью неделю

availableBook = m + k * 5 - за первую неделю
availableBook += k * 5 - за вторую неделю
availableBook += k * 5 - за третью неделю


readToday 4879 = за первые 100 недель
readToday 9779 = за вторые 100 недель
readToday 14 679 = за третьи 100 недель


Будет прочитано:
55 =  за первые 10 дней                         на 55
210 - 55 = 155 =  за вторые 10 дней             на 55 + 100
465 - 210 = 255  =  за третьи 10 дней           на 55 + 200
820 - 465 = 355  = за четвёртые 10 дней         на 55 + 300

455
555
655
755
855
955

5050        за первые 100 дней          на 5050
15 050      за вторые 100 дней          на 5050 + 10 000
25 050      за третьи 100 дней          на 5050 + 20 000
35 050      за четвёртые 100 дней       на 5050 + 30 000





? 35350 будет прочитано за 700 дней

 */