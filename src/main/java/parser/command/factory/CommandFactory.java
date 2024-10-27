package parser.command.factory;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.InvalidCommand;

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

    public CommandFactory() {
        this.progFactory = new ProgCommandFactory();
        this.mealFactory = new MealCommandFactory();
        this.waterFactory = new WaterCommandFactory();
    }

    public Command createCommand(String commandString, String argumentString) {
        return switch (commandString) {
        case ProgCommandFactory.COMMAND_WORD -> progFactory.parse(argumentString);
        case MealCommandFactory.COMMAND_WORD -> mealFactory.parse(argumentString);
        case WaterCommandFactory.COMMAND_WORD -> waterFactory.parse(argumentString);
        case HistoryCommand.COMMAND_WORD -> new HistoryCommand(); //Remove when adding HistoryCommandFactory
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        default -> new InvalidCommand();
        };
    }
}
