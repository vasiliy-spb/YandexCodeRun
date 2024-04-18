package task_213_Автодополнение;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void checkTestcase1() {
        String input = "3\n" +
                "hello world hello";
        int expected = 11;
        runTest(input, expected);
    }

    @Test
    public void checkTestcase2() {
        String input = "5\n" +
                "an apple a big apple";
        int expected = 13;
        runTest(input, expected);
    }

    @Test
    public void checkTestcase3() {
        String input = "5\n" +
                "aaaaa aaaab aaaaa abaaa abaaa";
        int expected = 22;
        runTest(input, expected);
    }

    private void runTest(String input, int expected) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));) {
            int result = Solution.processData(reader);
            System.out.println();
            System.out.println("Expected output:\n" + expected);
            System.out.println("Current output:\n" + result);
            System.out.println();
            assertEquals(expected, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
