package seedu.duke.flashutils.utils;

import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.commands.AddCommand;
import seedu.duke.flashutils.commands.Command;
import seedu.duke.flashutils.commands.FlashbangCommand;
import seedu.duke.flashutils.commands.InvalidCommand;
import seedu.duke.flashutils.commands.QuitCommand;
import seedu.duke.flashutils.commands.SearchCommand;
import seedu.duke.flashutils.commands.ViewCommand;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseAddCommand() {
        String input = "add --m SampleModule --q What is Java? --a A programming language.";
        Command command = Parser.parseCommand(input);
        assertInstanceOf(AddCommand.class, command);

        AddCommand addCommand = (AddCommand) command;
        assertEquals("SampleModule", addCommand.getTargetSet().getModuleName());
        assertEquals("What is Java?", addCommand.getCardToAdd().getQuestion());
        assertEquals("A programming language.", addCommand.getCardToAdd().getAnswer());
    }

    @Test
    public void testParseViewCommand() {
        String input = "view --m SampleModule";
        Command command = Parser.parseCommand(input);
        assertInstanceOf(ViewCommand.class, command);

        ViewCommand viewCommand = (ViewCommand) command;
        assertEquals("SampleModule", viewCommand.getTargetSet().getModuleName());
    }

    @Test
    public void testParseFlashbangCommand() {
        String input = "flashbang --m SampleModule --t 100 seconds";
        Command command = Parser.parseCommand(input);
        assertInstanceOf(FlashbangCommand.class, command);

        FlashbangCommand flashbangCommand = (FlashbangCommand) command;
        assertEquals("SampleModule", flashbangCommand.getTargetSet().getModuleName());
    }

    @Test
    public void testParseQuitCommand() {
        String input = "quit";
        Command command = Parser.parseCommand(input);
        assertInstanceOf(QuitCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        String input = "invalid --m SampleModule";
        Command command = Parser.parseCommand(input);
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    public void testParseSearchCommand() {
        String input = "search --m SampleModule --s SearchTerm";
        Command command = Parser.parseCommand(input);
        assertInstanceOf(SearchCommand.class, command);
    }
}
