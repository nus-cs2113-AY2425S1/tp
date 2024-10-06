package seedu.manager.parser;

import seedu.manager.command.Command;
import seedu.manager.command.EchoCommand;
import org.junit.jupiter.api.Test;
import seedu.manager.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    public void parseCommand_nonEmptyString_echo() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("Hello world!");

        assertTrue(command instanceof EchoCommand);
    }
}
