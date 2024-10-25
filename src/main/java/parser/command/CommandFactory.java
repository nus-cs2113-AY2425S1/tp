package parser.command;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.InvalidCommand;

import java.util.logging.Logger;

/*
    CommandFactory is a factory class that creates different commands
    based on argumentString and commandString
    Subcommands "prog","history" etc. are seperated into their own factory classes
    Which command factory distributes inputs into based on the command word
 */
public class CommandFactory {
    private final ProgCommandFactory progFactory;
    private final MealCommandFactory mealFactory;
    private final WaterCommandFactory waterFactory;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CommandFactory() {
        this.progFactory = new ProgCommandFactory();  // Dependency injection for better testability
        this.mealFactory = new MealCommandFactory();
        this.waterFactory = new WaterCommandFactory();
    }

    public Command createCommand(String commandString, String argumentString) {
        return switch (commandString) {
        case ProgCommandFactory.COMMAND_WORD -> progFactory.parse(argumentString);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand();
        case MealCommandFactory.COMMAND_WORD -> mealFactory.parse(argumentString);
        case WaterCommandFactory.COMMAND_WORD -> waterFactory.parse(argumentString);
        default -> new InvalidCommand();
        };
    }
}
