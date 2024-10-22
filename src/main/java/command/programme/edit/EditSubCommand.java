package command.programme.edit;

import command.CommandResult;
import command.programme.ProgrammeCommand;
import history.History;
import programme.ProgrammeList;

public abstract class EditSubCommand extends ProgrammeCommand {

    public EditSubCommand(int progId, int dayId){
        super(progId, dayId);
    }

    public EditSubCommand(int progId){
        super(progId);
    }

    public int getDayId() {
        return dayId;
    }

    public abstract String execute(ProgrammeList pList);

    @Override
    public CommandResult execute(ProgrammeList pList, History history){
        String result = execute(pList);
        return new CommandResult(result);
    }
}
