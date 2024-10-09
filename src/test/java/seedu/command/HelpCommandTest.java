package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HelpCommandTest {

    private HelpCommand helpCommand;

    @BeforeEach
    public void setUp() {
        helpCommand = new HelpCommand();
    }

    @Test
    public void execute_withNoCommands_emptyResult() {
        // Set up with an empty list of commands
        helpCommand.setCommands(new ArrayList<>());

        // Execute the command
        List<String> result = helpCommand.execute();

        // Verify the result is empty
        assertTrue(result.isEmpty());
    }

    @Test
    public void execute_withMultipleCommands_multipleResult() {
        // Create a list of commands
        List<Command> commands = new ArrayList<>();
        commands.add(new TestCommand());
        commands.add(new AnotherCommand());

        helpCommand.setCommands(commands);

        // Execute the command
        List<String> result = helpCommand.execute();

        // Verify the result
        assertEquals(2, result.size());
        assertTrue(result.contains(TestCommand.COMMAND_GUIDE));
        assertTrue(result.contains(AnotherCommand.COMMAND_GUIDE));
    }
}

// Sample implementation for AnotherCommand (for testing)
class AnotherCommand extends Command {
    public static final String COMMAND_WORD = "another";
    public static final String COMMAND_GUIDE = "another: Another command for testing.";

    @Override
    public List<String> execute() {
        return List.of();
    }

    @Override
    protected String[] getMandatoryKeywords() {
        return new String[]{};
    }

    @Override
    protected String[] getExtraKeywords() {
        return new String[]{};
    }

    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}
