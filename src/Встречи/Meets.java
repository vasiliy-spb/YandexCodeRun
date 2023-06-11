package Встречи;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Meets {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new FileReader("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Встречи/input.txt"));
        BufferedWriter writer = new BufferedWriter(new PrintWriter("/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Встречи/output.txt"));

        int n = Integer.parseInt(reader.readLine());
        if (n < 1 || n > 1000) return;

        Map<String, MeetUnit> allUnits = new HashMap<>();
        Map<Integer, List<Meet>> allMeets = new HashMap<>();
        String result;
        for (int i = 0; i < n; i++) {
            String[] request = reader.readLine().trim().split(" ");
            switch(request[0]) {
                case "APPOINT":
                    result = getAppointResult(request, allUnits, allMeets);
                    if (result == null) return;
                    break;
                case "PRINT":
                    result = getPrintResult(request, allMeets);
                    if (result == null) return;
                    break;
                default:
                    return;
            }
            if (result.length() > 0) {
                writer.write(result);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();
    }

    private static String getAppointResult(String[] request, Map<String, MeetUnit> allUnits, Map<Integer, List<Meet>> allMeets) {
        int day = Integer.parseInt(request[1]);
        if (day < 1 || day > 365) return null;
        LocalTime time = LocalTime.parse(request[2]);
        if (time.getHour() < 8 || time.getHour() > 21) return null;
        int duration = Integer.parseInt(request[3]);
        if (duration < 15 || duration > 120) return null;
        int k = Integer.parseInt(request[4]);
        if (k < 1 || k > 10) return null;
        List<MeetUnit> tempMeetUnits = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        String[] names = new String[request.length-5];
        for (int i = 5; i < 5 + k; i++) {
            String name = request[i];
            names[i-5] = name;
            if (name.length() < 1 || name.length() > 20) return null;

            MeetUnit meetUnit;
            if (allUnits.containsKey(name)) {
                meetUnit = allUnits.get(name);
                MeetUnit currentUnit = new MeetUnit(meetUnit);
                if (isPossibleMeet(currentUnit,day,time,duration)) {
                    tempMeetUnits.add(currentUnit);
                } else {
                    if (result.isEmpty()) {
                        result.append(name);
                    } else {
                        result.append(" ").append(name);
                    }
                }
            } else {
                List<MeetTime> meetTimes = new ArrayList<>();
                MeetTime meetTime = new MeetTime(day,time,time.plusMinutes(duration));
                meetTimes.add(meetTime);
                meetUnit = new MeetUnit(name,meetTimes);
                tempMeetUnits.add(meetUnit);
            }
        }
        if (result.isEmpty()) {
            for (MeetUnit meetUnit : tempMeetUnits) {
                allUnits.put(meetUnit.getName(), meetUnit);
            }
            result.append("OK");
            Meet meet = new Meet(day,time,duration,names);
            if (allMeets.containsKey(day)) {
                allMeets.get(day).add(meet);
            } else {
                List<Meet> meetList = new ArrayList<>();
                meetList.add(meet);
                allMeets.put(day,meetList);
            }
        } else {
            result.insert(0,"FAIL\n");
        }
        return result.toString();
    }

    private static boolean isPossibleMeet(MeetUnit meetUnit, int day, LocalTime begin, int duration) {
        LocalTime end = begin.plusMinutes(duration);

        for (MeetTime meetTime : meetUnit.getMeetTimes()) {
            if (meetTime.getDay() != day) continue;
            if ((meetTime.getFinish().isBefore(begin) || meetTime.getFinish().toSecondOfDay() == begin.toSecondOfDay())
                    || (meetTime.getStart().isAfter(end) || meetTime.getStart().toSecondOfDay() == end.toSecondOfDay())) {
                continue;
            } else {
                return false;
            }
        }
        MeetTime meetTime = new MeetTime(day,begin,end);
        meetUnit.getMeetTimes().add(meetTime);
        return true;
    }

    private static String getPrintResult(String[] request, Map<Integer, List<Meet>> allMeets) {
        if (!allMeets.containsKey(Integer.parseInt(request[1]))) return "";
        StringBuilder result = new StringBuilder();
        List<Meet> validMeets = new ArrayList<>();
        for (Meet meet : allMeets.get(Integer.parseInt(request[1]))) {
            String[] names = meet.getNames();
            for (String n : names) {
                if (n.equals(request[2])) {
                    validMeets.add(meet);
                }
            }
        }
        if (validMeets.isEmpty()) return "";
        validMeets.sort(new Comparator<Meet>() {
            @Override
            public int compare(Meet o1, Meet o2) {
                if (o1.getTime().toSecondOfDay() == o2.getTime().toSecondOfDay())
                    return (o1.getTime().toSecondOfDay() + o1.getDuration() * 60) - (o2.getTime().toSecondOfDay() + o2.getDuration() * 60);
                return o1.getTime().toSecondOfDay() - o2.getTime().toSecondOfDay();
            }
        });
        for (Meet meet : validMeets) {
            if (result.isEmpty())
                result.append(meet.toString());
            else result.append("\n").append(meet.toString());
        }
        return result.toString();
    }

}

class MeetUnit {
    private String name;
    private List<MeetTime> meetTimes;

    public MeetUnit(String name, List<MeetTime> meetTimes) {
        this.name = name;
        this.meetTimes = meetTimes;
    }

    public MeetUnit(MeetUnit source) {
        this.name = source.getName();
        this.meetTimes = new ArrayList<>();
        for(MeetTime meetTime : source.getMeetTimes()) {
            meetTimes.add(new MeetTime(meetTime.getDay(),
                    LocalTime.ofSecondOfDay(meetTime.getStart().toSecondOfDay()),
                    LocalTime.ofSecondOfDay(meetTime.getFinish().toSecondOfDay())));
        }
    }

    public String getName() {
        return name;
    }

    public List<MeetTime> getMeetTimes() {
        return meetTimes;
    }
}

class MeetTime {
    private int day;
    private LocalTime start;
    private LocalTime finish;

    public MeetTime(int day, LocalTime start, LocalTime finish) {
        this.day = day;
        this.start = start;
        this.finish = finish;
    }

    public int getDay() {
        return day;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getFinish() {
        return finish;
    }
}

class Meet {
    private int day;
    private LocalTime time;
    private int duration;
    private String[] names;

    public Meet(int day, LocalTime time, int duration, String[] names) {
        this.day = day;
        this.time = time;
        this.duration = duration;
        this.names = names;
    }

    public int getDay() {
        return day;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public String[] getNames() {
        return names;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(time).append(" ").append(duration);
        for (String s : names)
            result.append(" ").append(s);
        return result.toString();
    }
}