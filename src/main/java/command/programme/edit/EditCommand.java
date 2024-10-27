package command.programme.edit;
import command.CommandResult;
import command.programme.ProgrammeCommand;
import history.History;
import programme.ProgrammeList;

import java.util.logging.Logger;


public abstract class EditCommand extends ProgrammeCommand {
    public static final String COMMAND_WORD = "edit";
    protected final Logger logger = Logger.getLogger(this.getClass().getName());
    protected int exerciseId;

    public EditCommand(int progId, int dayId, int exerciseId) {
        super(progId, dayId);
        this.exerciseId = exerciseId;
    }

    public EditCommand(int progId, int dayId){
        super(progId, dayId);
    }

    public EditCommand(int progId){
        super(progId);
    }

    public abstract CommandResult execute( ProgrammeList programmes);

    @Override
    public CommandResult execute(ProgrammeList programmes, History history) {
        return execute(programmes);
    }
}
