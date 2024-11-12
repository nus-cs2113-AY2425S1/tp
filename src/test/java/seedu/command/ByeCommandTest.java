package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.main.Main;
import seedu.message.CommandResultMessages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {

    private ByeCommand byeCommand;

    @BeforeEach
    public void setUp() {
        byeCommand = new ByeCommand();
    }

    @Test
    public void execute_setsRunningToFalse_returnsEndMessage() {
        // Arrange
        Main.setRunning(true); // Ensure the application is initially running

        // Act
        List<String> result = byeCommand.execute();

        // Assert
        assertEquals(false, Main.getRunning()); // Check if running state is false
        assertEquals(List.of(CommandResultMessages.END_MESSAGE), result); // Check the returned message
    }
}
