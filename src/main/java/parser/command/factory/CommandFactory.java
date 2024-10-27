package parser.command.factory;

import command.Command;
import command.ExitCommand;
import command.InvalidCommand;
import java.util.logging.Logger;

/*
    CommandFactory is a factory class that creates different commands
    based on argumentString and commandString.
    Subcommands like "prog", "history", etc., are handled by their respective factory classes,
    which this factory distributes based on the command word.
 */
public class CommandFactory {
    private final ProgCommandFactory progFactory;
    private final MealCommandFactory mealFactory;
    private final WaterCommandFactory waterFactory;
    private final HistoryCommandFactory historyFactory;

    public CommandFactory() {
        this.progFactory = new ProgCommandFactory();
        this.mealFactory = new MealCommandFactory();
        this.waterFactory = new WaterCommandFactory();
        this.historyFactory = new HistoryCommandFactory();  // Add HistoryCommandFactory
    }

    public Command createCommand(String commandString, String argumentString) {
        return switch (commandString) {
        case ProgCommandFactory.COMMAND_WORD -> progFactory.parse(argumentString);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        case HistoryCommandFactory.COMMAND_WORD -> historyFactory.parse(argumentString);  // Route to historyFactory
        case MealCommandFactory.COMMAND_WORD -> mealFactory.parse(argumentString);
        case WaterCommandFactory.COMMAND_WORD -> waterFactory.parse(argumentString);
        default -> new InvalidCommand();
        };
    }
}
