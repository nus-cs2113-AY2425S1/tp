package seedu.manager.parser;

import seedu.manager.command.Command;
import seedu.manager.command.EchoCommand;
import org.junit.jupiter.api.Test;
import seedu.manager.command.ExitCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ParserTest {

    @Test
    public void parseCommand_nonEmptyString_echo() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("Hello world!");

        assertInstanceOf(EchoCommand.class, command);
    }

    @Test
    public void parseCommand_exitWord_exit() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("exit");

        assertInstanceOf(ExitCommand.class, command);
    }
}
