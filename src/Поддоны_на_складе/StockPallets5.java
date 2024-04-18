package Поддоны_на_складе;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StockPallets5 {
    public static void main(String[] args) throws IOException {

//        Polygon polygonN = new Polygon(new int[]{0},new int[]{0},1);
//        polygonN.addPoint(0,5);
//        polygonN.addPoint(10,5);
//        polygonN.addPoint(10,0);
//        System.out.println(polygonN.contains(3,3));

        Rectangle rectangle1 = new Rectangle(0,0,2,5);
        Rectangle rectangle2 = new Rectangle(0,0,5,2);
        Rectangle rectangle3 = rectangle1.union(rectangle2);
        System.out.println(rectangle3);
        Point point0 = new Point(4,4);
        System.out.println(rectangle3.contains(point0));

        Polygon polygon0 = new Polygon();



        if (4 > 3) return;


        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Поддоны_на_складе/output.txt";

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        int n = Integer.parseInt(reader.readLine());

        Polygon polygon = new Polygon(new int[]{0}, new int[]{0},1);

        Point maxPoint = new Point();
        List<Point> pointList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] arr = reader.readLine().split(" ");
            int x = Integer.parseInt(arr[0]);
            int y = Integer.parseInt(arr[1]);
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }

            Point point3 = new Point(x,y);
            if (polygon.contains(point3)) continue;
            polygon.addPoint(x,y);
            if (x >= maxPoint.x && y >= maxPoint.y) {
                maxPoint.x = x;
                maxPoint.y = y;
            }
            pointList.add(point3);
            Point point1 = new Point(x,0);
            Point point2 = new Point(0,y);
            if (!polygon.contains(point1))
                polygon.addPoint(x,0);
            if (!polygon.contains(point2))
                polygon.addPoint(0,y);
        }
        reader.close();

        for (int i = 0; i < polygon.npoints; i++) {
            System.out.println(polygon.xpoints[i] + " : " + polygon.ypoints[i]);
        }

//        System.out.println("pointList.size = " + pointList.size());
//        pointList.forEach(System.out::println);
//        pointList.removeIf(point -> polygon.contains(point.x+1,point.y+1));
//        System.out.println("pointList.size = " + pointList.size());
//        pointList.forEach(System.out::println);

        int count = 0;
        for (Point point : pointList) {
            if (!polygon.inside(point.x + 1, point.y + 1)) {
                count++;
                System.out.println(point);
            }
        }

        writer.write(String.valueOf(count));
        writer.close();
    }
}
