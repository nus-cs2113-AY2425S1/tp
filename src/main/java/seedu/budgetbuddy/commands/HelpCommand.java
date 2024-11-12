package seedu.budgetbuddy.commands;

import seedu.budgetbuddy.Ui;

/**
 * Represents a command to display a help message to new users
 */
public class HelpCommand extends Command{
    /**
     * Determine if given command is the help command
     * @param command Input command from user
     * @return true if command matches "help". else, false.
     */
    public static boolean isCommand(String command){
        return command.startsWith("help");
    }

    /**
     * Executes help command by displaying help message to user.
     */
    @Override
    public void execute(){
        Ui.displayHelpMessage();
    }
}
