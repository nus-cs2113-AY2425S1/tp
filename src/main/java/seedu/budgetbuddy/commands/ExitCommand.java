package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.Ui;

/**
 * Represents a command to exit the BudgetBuddy application.
 * This command triggers the display of the exit message when executed.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {

    }

    /**
     * Determines if the given command string matches the exit command.
     *
     * @param command The command string entered by the user.
     * @return true if the command is the exit command, false otherwise.
     */
    public static boolean isCommand(String command) {
        return command.equals("bye");
    }

    /**
     * Executes the exit command by displaying the exit message.
     */
    @Override
    public void execute() {
        Ui.displayExitMessage();
    }
}
