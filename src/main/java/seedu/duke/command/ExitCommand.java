package seedu.duke.command;

import seedu.duke.financial.FinancialList;

/**
 * The ExitCommand class is responsible for handling the termination of the application.
 * It extends the Command class and overrides the execute method to print a goodbye message.
 * This command signifies the end of the program's execution.
 */
public class ExitCommand extends Command {

    final String goodByeMessage = "--------------------------------------------\n" +
                "Goodbye! Hope to see you again soon :)\n" +
                "--------------------------------------------\n";

    /**
     * Executes the ExitCommand which prints a goodbye message to the console.
     */
    @Override
    public void execute(FinancialList list) {
        System.out.println(goodByeMessage);
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return true, as this command always exits the program.
     */
    public boolean isExit() {
        return true;
    }

}
