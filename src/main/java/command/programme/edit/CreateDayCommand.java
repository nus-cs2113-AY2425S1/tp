package command.programme.edit;
import command.CommandResult;
import programme.Day;
import programme.ProgrammeList;

public class CreateDayCommand  extends EditCommand {
    private final Day created;

    public CreateDayCommand(int progId, Day day) {
        super(progId);
        created = day;
    }

    @Override
    public CommandResult execute(ProgrammeList programmes) {
        programmes.insertDay(progId, created);
        String result = String.format("Created: %s%n",created.toString());
        return new CommandResult(result);
    }
}
