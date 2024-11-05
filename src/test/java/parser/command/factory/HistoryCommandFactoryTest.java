package parser.command.factory;

import command.Command;
import command.history.HistoryCommandTest;
import command.history.ListPersonalBestsCommand;
import command.history.ViewPersonalBestCommand;
import command.history.WeeklySummaryCommand;
import command.InvalidCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryCommandFactoryTest {

    private HistoryCommandFactory historyCommandFactory;

    @BeforeEach
    public void setUp() {
        historyCommandFactory = new HistoryCommandFactory();
    }

    @Test
    public void testParseHistoryCommand() {
        String argumentString = "history";
        HistoryCommandTest expectedCommand = new HistoryCommandTest();

        Command result = historyCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected HistoryCommand.");
    }

    @Test
    public void testParseListPersonalBestsCommand() {
        String argumentString = "history pb";
        ListPersonalBestsCommand expectedCommand = new ListPersonalBestsCommand();

        Command result = historyCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected ListPersonalBestsCommand.");
    }

    @Test
    public void testParseViewPersonalBestCommand() {
        String argumentString = "history pb Bench_Press";
        ViewPersonalBestCommand expectedCommand = new ViewPersonalBestCommand("Bench_Press");

        Command result = historyCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected ViewPersonalBestCommand.");
    }

    @Test
    public void testParseWeeklySummaryCommand() {
        String argumentString = "history wk";
        WeeklySummaryCommand expectedCommand = new WeeklySummaryCommand();

        Command result = historyCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result, "Parsed command should be equal to the expected WeeklySummaryCommand.");
    }

    @Test
    public void testParseInvalidCommand() {
        String argumentString = "history unknown";
        Command result = historyCommandFactory.parse(argumentString);

        // Updated assertion to check type instead of instance
        assertInstanceOf(InvalidCommand.class, result, "Parsed command should be of type InvalidCommand.");
    }

}

