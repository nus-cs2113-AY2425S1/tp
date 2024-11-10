package seedu.manager.parser;

import org.junit.jupiter.api.Test;
import seedu.manager.command.AddCommand;
import seedu.manager.command.Command;
import seedu.manager.command.ExitCommand;
import seedu.manager.command.MarkEventCommand;
import seedu.manager.command.MarkItemCommand;
import seedu.manager.command.MarkParticipantCommand;
import seedu.manager.command.MenuCommand;
import seedu.manager.command.ListCommand;
import seedu.manager.command.RemoveCommand;
import seedu.manager.command.ViewCommand;
import seedu.manager.exception.InvalidCommandException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    //@@author jemehgoh
    @Test
    public void parseCommand_invalidString_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "Hello world!";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_exitWord_exit() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("exit");

        assertInstanceOf(ExitCommand.class, command);
    }

    //@@author glenn-chew
    @Test
    public void parseCommand_menuWord_menu() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("menu");

        assertInstanceOf(MenuCommand.class, command);
    }

    //@@author MatchaRRR
    @Test
    public void parseCommand_listWord_list() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("list");

        assertInstanceOf(ListCommand.class, command);
    }

    //@@author jemehgoh
    @Test
    public void addCommand_addEvent_add() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("add -e event -t 2024-09-10 12:34 -v Venue A -u high");

        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void addCommand_addNoParameter_invalid() throws IOException {
        Parser parser = new Parser();
        String commandString = "add";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void removeCommand_removeEvent_add() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("remove -e event");

        assertInstanceOf(RemoveCommand.class, command);
    }

    @Test
    public void removeCommand_removeNoParameter_invalid() throws IOException {
        Parser parser = new Parser();
        String commandString = "remove";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    //@@author jemehgoh
    @Test
    public void viewCommand_viewParticipant_view() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("view -e event -y participant");

        assertInstanceOf(ViewCommand.class, command);
    }

    @Test
    public void viewCommand_noParameter_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "view";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void viewCommand_invalidStatus_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "view -e event -y command";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    //@@author jemehgoh
    @Test
    public void parseCommand_markEvent_mark() throws IOException {
        Parser parser = new Parser();
        Command command = parser.parseCommand("mark -e event -s done");

        assertInstanceOf(MarkEventCommand.class, command);
    }

    @Test
    public void parseCommand_markEventNoStatus_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -e event";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_markEventInvalidStatus_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -e event -s yes";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_markParticipantPresent_mark() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -p John Doe -e event -s present";
        Command command = parser.parseCommand(commandString);

        assertInstanceOf(MarkParticipantCommand.class, command);
    }

    @Test
    public void parseCommand_markParticipantAbsent_mark() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -p John Doe -e event -s absent";
        Command command = parser.parseCommand(commandString);

        assertInstanceOf(MarkParticipantCommand.class, command);
    }

    @Test
    public void parseCommand_markParticipantNoStatus_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -p John Doe -e event";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_markParticipantInvalidStatus_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -p John Doe -e event -s done";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_markItem_mark() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -m paper -e event -s accounted";
        Command command = parser.parseCommand(commandString);

        assertInstanceOf(MarkItemCommand.class, command);
    }

    @Test
    public void parseCommand_markItemInvalidStatus_mark() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -m paper -e event -s done";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_markInvalidFlags_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "mark -s done";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    //@@author LTK-1606
    @Test
    public void parseCommand_copyCommandInvalidFlags_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "copy tutorial < lecture";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_findCommandInvalidFlags_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "find -s event 1 -p doe";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_findCommandInvalidInput_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "find -e -p doe";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_filterEventsInvalidFlag_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "filter -s high";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_sortEventsInvalidFlags_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "sort by name";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

    @Test
    public void parseCommand_sortEventsInvalidInput_throwsException() throws IOException {
        Parser parser = new Parser();
        String commandString = "sort -by fun";

        assertThrows(InvalidCommandException.class, () -> {
            parser.parseCommand(commandString);
        });
    }

}
