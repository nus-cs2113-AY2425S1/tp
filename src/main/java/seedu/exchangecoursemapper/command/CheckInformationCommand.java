package seedu.exchangecoursemapper.command;

public abstract class CheckInformationCommand extends Command{
    /**
     * Executes the command based on the provided user input.
     *
     * @param userInput the input provided by the user to execute the command.
     */
    public abstract void execute(String userInput);
}
