// @@author andreusxcarvalho
package command.history;

import command.Command;
import command.CommandResult;
import history.History;
import programme.ProgrammeList;

import java.time.LocalDate;

/**
 * Represents a command to show the full history or perform history-related operations.
 */
public abstract class HistoryCommand extends Command {
    protected LocalDate date;

    public HistoryCommand(LocalDate date){
        this.date = date;
    }

    public HistoryCommand(){}

    @Override
    public CommandResult execute(ProgrammeList programmes, History history) {
        return execute(history);
    }

    public abstract CommandResult execute(History history);
}



