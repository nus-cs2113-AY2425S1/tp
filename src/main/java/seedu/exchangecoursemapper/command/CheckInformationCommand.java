package seedu.exchangecoursemapper.command;

/**
 * CheckInformationCommand class is a general command class that
 * lists out information stored in the database or in storage based on user request.
 * Concrete command subclasses will implement the {@link #execute(String userInput)}
 * method based on command specific behaviour.
 */
public abstract class CheckInformationCommand extends Command{
    /**
     * Executes the command based on the provided user input.
     *
     * @param userInput the input provided by the user to execute the command.
     */
    public abstract void execute(String userInput);
}
