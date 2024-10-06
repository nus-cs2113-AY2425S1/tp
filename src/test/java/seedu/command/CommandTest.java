package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CommandTest {

    private TestCommand testCommand;

    @BeforeEach
    public void setUp() {
        testCommand = new TestCommand();
    }

    @Test
    public void setArguments_twoNewArgument_argumentsSetCorrectly() {
        // Prepare test arguments
        Map<String, String> arguments = new HashMap<>();
        arguments.put("arg1/", "value1");
        arguments.put("arg2/", "value2");

        // Set the arguments using the method
        testCommand.setArguments(arguments);

        // Validate that the arguments were set correctly
        assertEquals(arguments, testCommand.arguments);
    }

    @Test
    public void execute_commandExecuted_expectedOutput() {
        // Expected message list
        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add("Test command executed.");

        // Actual message list
        List<String> actualMessages = testCommand.execute();

        // Assert that the actual messages match the expected messages
        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    public void getArgumentKeys_noInput_returnConcatenatedArray() {
        // Expected keys
        String[] expectedKeys = {"arg1/", "arg2/"};

        // Actual keys
        String[] actualKeys = testCommand.getArgumentKeys();

        // Assert that the actual keys match the expected keys
        assertArrayEquals(expectedKeys, actualKeys);
    }
}
