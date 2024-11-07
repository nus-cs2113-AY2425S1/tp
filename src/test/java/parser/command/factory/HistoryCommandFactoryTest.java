package parser.command.factory;

import command.Command;
import command.InvalidCommand;
import command.history.HistoryCommand;
import command.history.ViewPersonalBestCommand;
import command.history.ListPersonalBestsCommand;
import command.history.WeeklySummaryCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class HistoryCommandFactoryTest {

    private HistoryCommandFactory historyCommandFactory;

    @BeforeEach
    public void setUp() {
        historyCommandFactory = new HistoryCommandFactory();
    }

    @Test
    public void testParseHistoryCommand() {
        // Test that "history" returns a HistoryCommand instance
        Command command = historyCommandFactory.parse("view");
        assertInstanceOf(HistoryCommand.class, command, "Expected HistoryCommand instance for 'history view'.");
    }

    @Test
    public void testParseListPersonalBestsCommand() {
        // Test that "history pb" with no additional arguments returns a ListPersonalBestsCommand instance
        Command command = historyCommandFactory.parse("pb");
        assertInstanceOf(ListPersonalBestsCommand.class, command, "Expected ListPersonalBestsCommand instance for 'history pb'.");
    }

    @Test
    public void testParseViewPersonalBestCommand() {
        // Test that "history pb [exercise]" returns a ViewPersonalBestCommand instance
        Command command = historyCommandFactory.parse("pb Bench_Press");
        assertInstanceOf(ViewPersonalBestCommand.class, command, "Expected ViewPersonalBestCommand instance for 'history pb Bench_Press'.");
    }

    @Test
    public void testParseWeeklySummaryCommand() {
        // Test that "history wk" returns a WeeklySummaryCommand instance
        Command command = historyCommandFactory.parse("wk");
        assertInstanceOf(WeeklySummaryCommand.class, command, "Expected WeeklySummaryCommand instance for 'history wk'.");
    }

    @Test
    public void testParseInvalidCommand() {
        // Test that an invalid subcommand returns an InvalidCommand instance
        Command command = historyCommandFactory.parse("unknown");
        assertInstanceOf(InvalidCommand.class, command, "Expected InvalidCommand instance for unknown subcommand.");
    }
}

