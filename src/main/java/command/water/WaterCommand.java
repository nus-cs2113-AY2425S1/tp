//@@author Bev-low
package command.water;

import command.Command;
import command.CommandResult;
import history.History;
import programme.ProgrammeList;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents an abstract command related to water tracking.
 * This class serves as a base for specific water-related commands that operate on a given date.
 */
public abstract class WaterCommand extends Command {

    private static final Logger logger = Logger.getLogger(WaterCommand.class.getName());

    protected LocalDate date;

    /**
     * Constructs a water command with the specified date.
     *
     * @param date The date associated with the command. Must not be {@code null}.
     * @throws AssertionError if {@code date} is {@code null}.
     */
    public WaterCommand(LocalDate date) {
        assert date != null : "Date cannot be null";

        this.date = date;

        logger.log(Level.INFO, "WaterCommand initialized with date: {0}", date);
    }

    /**
     * Executes the water-related command using the specified history.
     * This method is intended to be implemented by subclasses to provide specific command behavior.
     *
     * @param history The {@code History} object containing records to operate on.
     * @return A {@code CommandResult} with the outcome of the command execution.
     */
    public abstract CommandResult execute(History history);

    /**
     * Executes the water-related command using both a {@code ProgrammeList} and {@code History}.
     *
     * @param programmes The {@code ProgrammeList} object, representing a list of programmes.
     * @param history The {@code History} object containing records to operate on.
     * @return A {@code CommandResult} with the outcome of the command execution.
     */
    @Override
    public CommandResult execute(ProgrammeList programmes, History history) {
        logger.log(Level.INFO, "Executing WaterCommand with ProgrammeList and History.");
        return execute(history);
    }
}
