package core;

import command.Command;
import command.InvalidCommand;

public class Parser {

    public Command parse(String fullCommand) {
        return new InvalidCommand();
    }
}
