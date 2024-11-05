package seedu.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UITest {
    private UI ui;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        ui = new UI();
    }

    @Test
    public void printMessage_inputContent_outputContent() {
        String message = "Hello, world!";
        ui.printMessage(message);
        assertEquals("\tHello, world!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void printMiddleMessage_inputContent_outputContent() {
        String message = "This is a middle message.";
        ui.printMiddleMessage(message);
        assertEquals("\tThis is a middle message.", outContent.toString());
    }

    @Test
    public void printMessages_listInput_outputContent() {
        ui.printMessages("First message", "Second message");
        assertEquals("\tFirst message" + System.lineSeparator() +
                "\tSecond message" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void showCommandResult_inputContent_outputContent() {
        List<String> results = Arrays.asList("Command executed successfully.", "Action completed.");
        ui.showCommandResult(results);
        assertEquals(
                "\t-------------------------------------" + System.lineSeparator() +
                        "\tCommand executed successfully." + System.lineSeparator() +
                        "\tAction completed." + System.lineSeparator() +
                        "\t-------------------------------------" + System.lineSeparator(),
                outContent.toString()
        );
    }

    @Test
    public void showCommandResult_nullInputContent_outputContent() {
        ui.showCommandResult(null);
        assertEquals("",
                outContent.toString()
        );
    }


    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }
}
