package Мобилки;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.*;

public class Mobiles {

    public static void main(String[] args) throws IOException {
        String input = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Мобилки/input.txt";
        String output = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Мобилки/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(input));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(output));

        int n = Integer.parseInt(reader.readLine());
        if(n < 2 || n > 200_000) return;
        if (n % 2 != 0) return;

        long totalDevelopment = 0;
        long totalManagement = 0;

        String[] development = reader.readLine().split(" ");
        String[] management = reader.readLine().split(" ");

        Map<Integer,User> allUsers = new TreeMap<>();
        Map<Integer,List<User>> diffDevelop = new TreeMap<>();
        Map<Integer,List<User>> diffManager = new TreeMap<>();

        int countDeveloper = 0;
        int countManager = 0;

        for (int i = 1; i <= n; i++) {
            int developer = Integer.parseInt(development[i-1]);
            int manager = Integer.parseInt(management[i-1]);

            User user = new User(i,developer,manager);

            allUsers.put(i,user);

            if (user.getDiff() == 0) {
                if (diffDevelop.containsKey(0)) {
                    diffDevelop.get(0).add(user);
                } else {
                    List<User> list = new ArrayList<>();
                    list.add(user);
                    diffDevelop.put(0, list);
                }
                countDeveloper++;
                totalDevelopment += user.getDevSkill();
            } else {
                if (user.getType() == UserType.DEVELOPER) {
                    if (diffDevelop.containsKey(user.getDiff())) {
                        diffDevelop.get(0).add(user);
                    } else {
                        List<User> list = new ArrayList<>();
                        list.add(user);
                        diffDevelop.put(user.getDiff(), list);
                    }
                    countDeveloper++;
                    totalDevelopment += user.getDevSkill();
                } else {
                    if (diffManager.containsKey(user.getDiff())) {
                        diffManager.get(user.getDiff()).add(user);
                    } else {
                        List<User> list = new ArrayList<>();
                        list.add(user);
                        diffManager.put(user.getDiff(), list);
                    }
                    countManager++;
                    totalManagement += user.getManagSkill();
                }
            }
        }

        while (countDeveloper > countManager) {
            User user = diffDevelop.get(0).remove(0);
            user.setType(UserType.MANAGER);
            if (diffManager.containsKey(0)) {
                diffManager.get(0).add(user);
            } else {
                List<User> list = new ArrayList<>();
                list.add(user);
                diffManager.put(0, list);
            }
            totalDevelopment -= user.getDevSkill();
            totalManagement += user.getManagSkill();
            countDeveloper--;
            countManager++;
        }

        int m = Integer.parseInt(reader.readLine());
        if (m < 1 || m > 100_000) return;
        for (int i = 0; i < m; i++) {
            String[] arr = reader.readLine().split(" ");
            int num = Integer.parseInt(arr[0]);
            if (num < 1 || num > n) return;
            int type = Integer.parseInt(arr[1]);
            if (type < 1 || type > 2) return;
            int d = Integer.parseInt(arr[2]);
            if (d < 1 || d > 10_000) return;

            User user = allUsers.get(num);

            int diffBefore = user.getDiff();
            int userDevSkillBefore = user.getDevSkill();
            int userManagSkillBefore = user.getManagSkill();
            switch (type) {
                case 1:
                    user.setDevSkill(user.getDevSkill() + d);
                    user.recalculateDiff();

                    if (user.getType() == UserType.DEVELOPER || user.getDevSkill() <= user.getManagSkill()) {
                        diffDevelop.get(diffBefore).remove(user);
                        if (diffDevelop.containsKey(user.getDiff())) {
                            diffDevelop.get(user.getDiff()).add(user);
                        } else {
                            List<User> list = new ArrayList<>();
                            list.add(user);
                            diffDevelop.put(user.getDiff(),list);
                        }
                        totalDevelopment += d;
                    } else {
                        int indexList = 0;
                        one: for(int diff : diffDevelop.keySet()) {
                            if (diff > user.getDiff())
                                break;
                            List<User> users = diffDevelop.get(diff);
                            Iterator<User> iterator = users.iterator();
                            while (iterator.hasNext()) {
                                User userToChange = iterator.next();
                                if (swapManagerToDeveloper(user,userToChange)) {
                                    diffManager.get(diffBefore).remove(user);
                                    users.remove(userToChange);
//                                    diffDevelop.get(userToChange.getDiff()).remove(userToChange);
                                    if (diffManager.containsKey(userToChange.getDiff())) {
                                        diffManager.get(userToChange.getDiff()).add(userToChange);
                                    } else {
                                        List<User> list = new ArrayList<>();
                                        list.add(user);
                                        diffManager.put(userToChange.getDiff(),list);
                                    }
                                    if (diffDevelop.containsKey(user.getDiff())) {
                                        diffDevelop.get(user.getDiff()).add(user);
                                    } else {
                                        List<User> list = new ArrayList<>();
                                        list.add(user);
                                        diffDevelop.put(user.getDiff(),list);
                                    }
                                    indexList = diff;

                                    totalManagement -= userManagSkillBefore;
                                    totalDevelopment += user.getDevSkill();

                                    totalDevelopment -= userToChange.getDevSkill();
                                    totalManagement += userToChange.getManagSkill();

                                    break one;
                                }
                            }
                        }
                        if (diffDevelop.get(indexList) != null && diffDevelop.get(indexList).isEmpty())
                            diffDevelop.remove(indexList);
                    }
                    break;
                case 2:
                    user.setManagSkill(user.getManagSkill() + d);
                    user.recalculateDiff();

                    if (user.getType() == UserType.MANAGER || user.getManagSkill() <= user.getDevSkill()) {
                        diffManager.get(diffBefore).remove(user);
                        if (diffManager.containsKey(user.getDiff())) {
                            diffManager.get(user.getDiff()).add(user);
                        } else {
                            List<User> list = new ArrayList<>();
                            list.add(user);
                            diffManager.put(user.getDiff(),list);
                        }
                        totalManagement += d;
                    } else {
                        int indexList = 0;

                        one: for (int diff : diffManager.keySet()) {
                            if (diff > user.getDiff())
                                break;
                            List<User> users = diffManager.get(diff);
                            Iterator<User> iterator = users.iterator();
                            while (iterator.hasNext()) {
                                User userToChange = iterator.next();
                                if (swapDeveloperToManager(user,userToChange)) {
                                    diffDevelop.get((diffBefore)).remove(user);
                                    users.remove(userToChange);
//                                    diffManager.get(userToChange.getDiff()).remove(userToChange);
                                    if (diffDevelop.containsKey(userToChange.getDiff())) {
                                        diffDevelop.get(userToChange.getDiff()).add(userToChange);
                                    } else {
                                        List<User> list = new ArrayList<>();
                                        list.add(user);
                                        diffDevelop.put(userToChange.getDiff(),list);
                                    }
                                    if (diffManager.containsKey(user.getDiff())) {
                                        diffManager.get(user.getDiff()).add(user);
                                    } else {
                                        List<User> list = new ArrayList<>();
                                        list.add(user);
                                        diffManager.put(user.getDiff(),list);
                                    }
                                    indexList = diff;

                                    totalDevelopment -= userDevSkillBefore;
                                    totalManagement += user.getManagSkill();

                                    totalManagement -= userToChange.getManagSkill();
                                    totalDevelopment += userToChange.getDevSkill();

                                    break one;
                                }
                            }
                        }

                        if (diffManager.get(indexList) != null && diffManager.get(indexList).isEmpty())
                            diffManager.remove(indexList);
                    }
            }
            writer.write(String.valueOf(totalManagement + totalDevelopment));
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    private static boolean swapDeveloperToManager(User developer, User manager) {
        if (developer.getDiff() > manager.getDiff()) {
            developer.setType(UserType.MANAGER);
            manager.setType(UserType.DEVELOPER);
            return true;
        }
        return false;
    }

    private static boolean swapManagerToDeveloper(User manager, User developer) {
        if (manager.getDiff() > developer.getDiff()) {
            manager.setType(UserType.DEVELOPER);
            developer.setType(UserType.MANAGER);
            return true;
        }
        return false;
    }


//    public static void main(String[] args) throws IOException {
//        String input = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Мобилки/input.txt";
//        String output = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Мобилки/output.txt";
//        BufferedReader reader = new BufferedReader(new FileReader(input));
//        BufferedWriter writer = new BufferedWriter(new PrintWriter(output));
//
//        Map<Integer,int[]> map = new HashMap<>();
//        long totalDevelopment = 0;
//        long totalManagement = 0;
//
//        int n = Integer.parseInt(reader.readLine());
//        if(n < 2 || n > 200_000) return;
//        if (n % 2 != 0) return;
//
//        String[] development = reader.readLine().split(" ");
//        String[] management = reader.readLine().split(" ");
//
//
//        for (int i = 0; i < n; i++) {
//            int dev = Integer.parseInt(development[i]);
//            int manag = Integer.parseInt(management[i]);
//            int[] a = {dev,manag};
//            map.put(i,a);
//            if (dev > manag) totalDevelopment += dev;
//            else totalManagement += manag;
//        }
//
////        System.out.println("totalDevelopment = " + totalDevelopment);
////        System.out.println("totalManagement = " + totalManagement);
////        System.out.println("———————————");
//
//        int m = Integer.parseInt(reader.readLine());
//        if (m < 1 || m > 100_000) return;
//        for (int i = 0; i < m; i++) {
////            System.out.println("totalDevelopment = " + totalDevelopment);
////            System.out.println("totalManagement = " + totalManagement);
//            String[] arr = reader.readLine().split(" ");
//            int num = Integer.parseInt(arr[0]);
//            if (num < 1 || num > n) return;
//            int type = Integer.parseInt(arr[1]);
//            if (type < 1 || type > 2) return;
//            int d = Integer.parseInt(arr[2]);
//            if (d < 1 || d > 10_000) return;
//            int[] user = map.get(num-1);
//            System.out.println(num -1);
////            System.out.println("before: " + user[0] + " " + user[1]);
//
////            if (user[0] > user[1]) {
////                System.out.println("был totalDevelopment = " + totalDevelopment);
////                System.out.println("user[0] = " + user[0]);
////                totalDevelopment -= user[0];
////                System.out.println("стал totalDevelopment = " + totalDevelopment);
////            } else {
////                System.out.println("был totalManagement = " + totalManagement);
////                System.out.println("user[1] = " + user[1]);
////                totalManagement -= user[1];
////                System.out.println("стал totalManagement = " + totalManagement);
////            }
////            System.out.println("totalDevelopment = " + totalDevelopment);
////            System.out.println("totalManagement = " + totalManagement);
//            if (user[0] > user[1]) totalDevelopment += d;
//            else totalManagement += d;
//            user[type-1] += d;
////            if (user[0] > user[1]) {
////                System.out.println("был totalDevelopment = " + totalDevelopment);
////                System.out.println("user[0] = " + user[0]);
////                totalDevelopment += user[0];
////                System.out.println("стал totalDevelopment = " + totalDevelopment);
////            } else {
////                System.out.println("был totalManagement = " + totalManagement);
////                System.out.println("user[1] = " + user[1]);
////                totalManagement += user[1];
////                System.out.println("стал totalManagement = " + totalManagement);
////            }
//
//
//            long result = totalDevelopment + totalManagement;
//
////            switch (type) {
////                case 1:
////                    if (user[0] > user[1]) totalDevelopment -= user[0];
////                    else totalManagement -= user[1];
////                    user[0] += d;
////                    if (user[0] > user[1]) totalDevelopment += user[0];
////                    else totalManagement += user[1];
////                    break;
////                case 2:
////                    if (user[1] >= user[0]) totalManagement -= user[1];
////                    else totalDevelopment -= user[0];
////                    user[1] += d;
////                    if (user[0] > user[1]) totalDevelopment += user[0];
////                    else totalManagement += user[1];
////                    break;
////            }
//            System.out.println("after: " + user[0] + " " + user[1]);
//            System.out.println(totalDevelopment + " + " + totalManagement + " = " + result);
////            writer.write(String.valueOf(totalDevelopment + totalManagement));
//            writer.write(String.valueOf(result));
//            writer.newLine();
//        }
//
//        reader.close();
//        writer.close();
//    }
}

class User {
    private int id;
    private int devSkill;
    private int managSkill;
    private int diff;
    private UserType type;

    public User(int id, int devSkill, int managSkill) {
        this.id = id;
        this.devSkill = devSkill;
        this.managSkill = managSkill;
        if (devSkill > managSkill) {
            this.diff = devSkill - managSkill;
            this.type = UserType.DEVELOPER;
        } else {
            this.diff = managSkill - devSkill;
            this.type = UserType.MANAGER;
        }
    }

    public int getId() {
        return id;
    }

    public int getDevSkill() {
        return devSkill;
    }

    public void setDevSkill(int devSkill) {
        this.devSkill = devSkill;
    }

    public int getManagSkill() {
        return managSkill;
    }

    public void setManagSkill(int managSkill) {
        this.managSkill = managSkill;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void recalculateDiff() {
        if (devSkill > managSkill) {
            this.diff = devSkill - managSkill;
        } else {
            this.diff = managSkill - devSkill;
        }
    }

}

enum UserType {
    DEVELOPER,
    MANAGER;
}


/*




7 15 3 4 = 15 3
10 10 0 6 = 10 6
34

11 15 3 4 = 15 3
10 10 0 6 = 10 6
34

11 15 3 10 = 15 10
10 10 0 6 = 10 0
35

11 15 3 10 = 15 3
10 20 0 6 = 10 6
43

 */