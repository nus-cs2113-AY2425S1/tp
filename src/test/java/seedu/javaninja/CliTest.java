import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.Cli;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CliTest {

    private Cli cli;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        String simulatedUserInput = "Sample input\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        cli = new Cli(inputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void readInput_returnsUserInput() {
        String input = cli.readInput();
        assertEquals("Sample input", input, "Cli should return the user input correctly.");
    }

    @Test
    public void printStartMessage_displaysCorrectOutput() {
        cli.printStartMessage();
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Welcome to Java Ninja!"), "Output should contain the welcome message.");
        assertTrue(printedOutput.contains("'view' to view topics"), "Output should contain the 'view' command instructions.");
    }

    @Test
    public void printHelp_displaysHelpInstructions() {
        cli.printHelp();
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("List of available commands:"), "Output should contain the list of commands.");
        assertTrue(printedOutput.contains("'help' - Show this help message"), "Output should contain 'help' command instructions.");
    }

    @Test
    public void printGoodByeMessage_displaysGoodbyeMessage() {
        cli.printGoodByeMessage();
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Thank you for using Java Ninja!"), "Output should contain the goodbye message.");
    }

    @Test
    public void printMessage_displaysCustomMessage() {
        cli.printMessage("This is a test message.");
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("This is a test message."), "Output should contain the custom message.");
    }

    @Test
    public void printOptions_displaysOptionsList() {
        cli.printOptions(List.of("Option 1", "Option 2"));
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("- Option 1"), "Output should contain 'Option 1'.");
        assertTrue(printedOutput.contains("- Option 2"), "Output should contain 'Option 2'.");
    }

    @Test
    public void printPastResults_displaysResults() {
        String results = "Score: 90%\nScore: 75%";
        cli.printPastResults(results);
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Score: 90%"), "Output should contain 'Score: 90%'.");
        assertTrue(printedOutput.contains("Score: 75%"), "Output should contain 'Score: 75%'.");
    }

    @Test
    public void printEnclosure_displaysEnclosureLine() {
        cli.printEnclosure();
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("----------------------"), "Output should contain the enclosure line.");
    }
}
