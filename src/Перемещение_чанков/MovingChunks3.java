package Перемещение_чанков;

import java.io.*;
import java.io.IOException;
import java.util.TreeSet;

public class MovingChunks3 {
    public static void main(String[] args) throws IOException {
        String inputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Перемещение_чанков/input.txt";
        String outputFile = "/Users/vasiliy/IdeaProjects/YandexCodeRun/src/Перемещение_чанков/output.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new PrintWriter(outputFile));

        String[] array = reader.readLine().split(" ");
        int n = Integer.parseInt(array[0]);
        int m = Integer.parseInt(array[1]);
        int q = Integer.parseInt(array[2]);

        int big = 100_001;

        String[] pNumbers = reader.readLine().split(" ");
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(pNumbers[i]);
        }

        TreeSet<Chunk> chunks = new TreeSet<>();

        chunks.add(new Chunk(0, big));
        chunks.add(new Chunk(n + 1, big));

        for (int i = 0; i < n; ++i) {
            if (i == n - 1 || numbers[i] != numbers[i + 1]) {
                chunks.add(new Chunk(i + 1, numbers[i]));
            }
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < q; ++i) {
            String[] request = reader.readLine().split(" ");
            int a = Integer.parseInt(request[0]);
            int b = Integer.parseInt(request[1]);
            int l = Integer.parseInt(request[2]);
            int r = Integer.parseInt(request[3]);

            Chunk chunk1 = chunks.higher(new Chunk(l, -big));
            Chunk chunk2 = chunks.higher(new Chunk(r, -big));

            if (chunk1.getKey() == chunk2.getKey() && chunk1.getValue() == a) {
                Chunk prev = chunks.lower(chunk1);
                if (prev.getKey() + 1 != l) {
                    chunks.add(new Chunk(l - 1, a));
                }
                if (prev.getKey() + 1 == l && prev.getValue() == b) {
                    chunks.remove(prev);
                }
                if (chunk1.getKey() == r) {
                    Chunk next = chunks.higher(chunk1);
                    if (next.getValue() == b) {
                        r = next.getKey();
                    }
                    chunks.remove(chunk2);
                }
                chunks.add(new Chunk(r, b));

                result.append(1).append("\n");
            } else {
                result.append(0).append("\n");
            }
        }

        writer.write(result.toString().trim());
        writer.close();
    }

}

class Chunk implements Comparable<Chunk> {
    private int key;
    private int value;

    public Chunk(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Chunk chunk) {
        if (key != chunk.getKey()) {
            return key - chunk.getKey();
        } else {
            return value - chunk.getValue();
        }
    }
}