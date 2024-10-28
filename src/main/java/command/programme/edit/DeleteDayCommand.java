package command.programme.edit;

import command.CommandResult;
import programme.Day;
import programme.ProgrammeList;

public class DeleteDayCommand  extends EditCommand {

    public DeleteDayCommand(int progId, int dayId) {
        super(progId, dayId);
    }

    public CommandResult execute(ProgrammeList programmes) {
        Day deleted = programmes.deleteDay(progId, dayId);
        String result = String.format("Deleted day: %s%n", deleted);
        return new CommandResult(result);
    }
}
