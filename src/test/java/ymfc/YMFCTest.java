package ymfc;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YMFCTest {
    void writeInput(String inputLine) {
        ByteArrayInputStream input = new ByteArrayInputStream(inputLine.getBytes());
        System.setIn(input);
    }

    @Test
    public void testMain() {
        writeInput("bye");
        YMFC.main(null);

        assertTrue(true);
    }
}
