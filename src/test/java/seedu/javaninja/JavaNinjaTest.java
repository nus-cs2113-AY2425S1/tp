import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.JavaNinja;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaNinjaTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture console output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testRun_withQuitCommand_exitsGracefully() {
        // Simulate user input: "quit"
        String simulatedInput = "quit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Run JavaNinja
        JavaNinja javaNinja = new JavaNinja();
        javaNinja.run();

        // Check if output contains expected farewell message
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Goodbye!"), "Expected farewell message in output.");
    }

    @Test
    public void testRun_withInvalidCommand_showsHelpMessage() {
        // Simulate user input with an invalid command, followed by "quit" to exit
        String simulatedInput = "invalidCommand\nquit\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Run JavaNinja
        JavaNinja javaNinja = new JavaNinja();
        javaNinja.run();

        // Check if output contains expected error message for invalid command
        String output = outputStreamCaptor.toString();
        System.out.println(output);
        assertTrue(output.contains("Invalid input"), "Expected invalid command message in output.");
    }
}
