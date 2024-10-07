package parser;

import command.Command;
import command.EchoCommand;

/**
 * Represents the command parser for EventManagerCLI
 */
public class Parser {

    /**
     * Returns an EchoCommand with a given user command string
     *
     * @param command The given command string from the user
     */
    public Command parseCommand (String command){
        return new EchoCommand(command);
    }
}
