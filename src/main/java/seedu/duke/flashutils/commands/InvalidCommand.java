package seedu.duke.flashutils.commands;

public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand() {
        this.errorMessage = "uh oh bad command";
    }

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        throw new IllegalArgumentException(errorMessage);
    }
}
