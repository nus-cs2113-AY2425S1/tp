package seedu.manager.parser;

import org.junit.jupiter.api.Test;
import seedu.manager.command.Command;
import seedu.manager.command.ExitCommand;
import seedu.manager.command.InvalidCommand;
import seedu.manager.command.MenuCommand;
import seedu.manager.command.ListCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ParserTest {

    @Test
    public void parseCommand_invalidString_invalid() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("Hello world!");

        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void parseCommand_exitWord_exit() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("exit");

        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void parseCommand_menuWord_menu() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("menu");

        assertInstanceOf(MenuCommand.class, command);
    }

    @Test
    public void parseCommand_listWord_list() {
        Parser parser = new Parser();
        Command command = parser.parseCommand("list");

        assertInstanceOf(ListCommand.class, command);
    }
}
