package task_5_Кафе;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void checkTestcase1() {
        String input = "5\n" +
                "35\n" +
                "40\n" +
                "101\n" +
                "59\n" +
                "63";
        String expected = "235\n" +
                "0 1\n" +
                "5";
        runTest(input, expected);
    }

    @Test
    public void checkTestcase2() {

    }

    @Test
    public void checkTestcase3() {

    }

    private void runTest(String input, String expected) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())))) {
            assertEquals(expected, Solution.processData(reader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
