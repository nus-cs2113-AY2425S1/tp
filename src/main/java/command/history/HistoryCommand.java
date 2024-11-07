// @@author andreusxcarvalho
package command.history;

import command.Command;
import command.CommandResult;
import history.History;
import programme.ProgrammeList;

import java.time.LocalDate;

/**
 * Represents an abstract command to handle history-related operations.
 * <p>
 * The {@code HistoryCommand} class serves as a base for all commands that operate
 * on workout history data, including viewing, listing, and deleting history records.
 * Subclasses must implement the {@link #execute(History)} method to define specific
 * history-related actions.
 * </p>
 */
public abstract class HistoryCommand extends Command {
    protected LocalDate date;

    /**
     * Constructs a {@code HistoryCommand} with a specified date.
     *
     * @param date the {@link LocalDate} associated with the command's operation
     */
    public HistoryCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Constructs a {@code HistoryCommand} without a specified date.
     * This constructor can be used for commands that do not require a specific date.
     */
    public HistoryCommand() {}

    /**
     * Executes the command with both a {@link ProgrammeList} and {@link History} context.
     * <p>
     * This method delegates to the {@link #execute(History)} method, which subclasses
     * must implement to define specific behavior.
     * </p>
     *
     * @param programmes the {@link ProgrammeList} (not used in this base class)
     * @param history the {@link History} object on which the command operates
     * @return the {@link CommandResult} indicating the outcome of the command
     */
    @Override
    public CommandResult execute(ProgrammeList programmes, History history) {
        return execute(history);
    }

    /**
     * Executes the command on the specified {@link History} object.
     * <p>
     * Subclasses must implement this method to perform specific operations on the history.
     * </p>
     *
     * @param history the {@link History} object on which the command operates
     * @return the {@link CommandResult} indicating the result of the command execution
     */
    public abstract CommandResult execute(History history);
}

