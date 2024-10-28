package command.history;

import command.Command;
import command.CommandResult;
import history.DailyRecord;
import history.History;
import programme.ProgrammeList;

import java.time.LocalDate;
import static common.Utils.formatDate;

public class DeleteRecordCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final LocalDate date;

    public DeleteRecordCommand(LocalDate date){
        this.date = date;
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        DailyRecord deletedRecord = history.deleteRecord(date);
        if (deletedRecord == null) {
            return new CommandResult("Could not find any Record at " + formatDate(date));
        }
        return new CommandResult("Deleted Record: " + deletedRecord);
    }
}



