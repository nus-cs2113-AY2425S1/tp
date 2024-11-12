package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;

/**
 * PersonalTrackerCommand abstract class is a general command class that
 * accesses the storage to modify and update saved course mapping plans of the user.
 * Concrete command subclasses will implement the {@link #execute(String userInput, Storage storage)}
 * method based on command specific behaviour.
 */
public abstract class PersonalTrackerCommand extends Command {
    /**
     * Executes the command based on the provided user input and updates
     * the storage accordingly.
     *
     * @param userInput the input provided by the user to execute the command.
     * @param storage the Storage instance used to save or retrieve data.
     */
    public abstract void execute(String userInput, Storage storage);
}
