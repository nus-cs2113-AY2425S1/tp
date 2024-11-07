//@@author 3CCLY
package ymfc.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    void readCommand_validInput_returnInput() {
        // Parse a test string into the Ui class
        String testString = "Test String";
        ByteArrayInputStream testInput = new ByteArrayInputStream(testString.getBytes());
        Ui ui = new Ui(testInput);
        assertEquals(testString, ui.readCommand());
    }

    @Test
    void testPrintErrorMessage() {
        String testString = "Test String";

        // Capture System.out printing
        ByteArrayOutputStream message = new ByteArrayOutputStream();
        PrintStream testingStream = new PrintStream(message);
        PrintStream systemStream = System.out;
        System.setOut(testingStream);

        Ui ui = new Ui(System.in);
        ui.printErrorMessage(testString);

        System.out.flush();
        System.setOut(systemStream);

        assertEquals(testString + System.lineSeparator(), message.toString());
    }
}
