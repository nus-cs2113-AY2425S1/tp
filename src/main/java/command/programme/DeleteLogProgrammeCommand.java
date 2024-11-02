// @@author TVageesan

package command.programme;

import command.CommandResult;
import history.DailyRecord;
import history.History;
import programme.Day;
import programme.ProgrammeList;

import java.time.LocalDate;

public class DeleteLogProgrammeCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "unlog";

    private final LocalDate date;

    public DeleteLogProgrammeCommand(LocalDate date){
        assert date != null : "Date must not be null";
        this.date = date;
    }

    @Override
    public CommandResult execute(ProgrammeList programmes, History history){
        DailyRecord dailyRecord = history.getRecordByDate(date);
        Day deleted = dailyRecord.deleteDayFromRecord();

        if (deleted == null){
            return new CommandResult("No workout log found.");
        }

        String result =  String.format("Deleted:%n%s", deleted);
        return new CommandResult(result);
    }
}
