package parser.command;

import command.Command;
import command.programme.LogCommand;
import command.ExitCommand;
import command.InvalidCommand;
import parser.FlagParser;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    CommandFactory is a factory class that creates different commands
    based on argumentString and commandString
    Subcommands "prog","history" etc. are seperated into their own factory classes
    Which command factory distributes inputs into based on the command word
 */
public class CommandFactory {
    private final ProgCommandFactory progFactory;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CommandFactory() {
        this.progFactory = new ProgCommandFactory();  // Dependency injection for better testability
    }

    public Command createCommand(String commandString, String argumentString) {
        return switch (commandString) {
        case ProgCommandFactory.COMMAND_WORD -> progFactory.parse(argumentString);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        default -> new InvalidCommand();
        };
    }
}
