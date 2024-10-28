package command.programme.edit;

import command.CommandResult;
import programme.Day;
import programme.Exercise;
import programme.ProgrammeList;

public class CreateExerciseCommand  extends EditCommand {

    private final Exercise created;

    public CreateExerciseCommand(int progId, int dayId, Exercise created) {
        super(progId,dayId);
        this.created = created;
    }

    public CommandResult execute(ProgrammeList programmes) {
        Day day = programmes.getDay(progId, dayId);
        day.insertExercise(created);
        String result = String.format("Created new exercise: %s%n", created);
        return new CommandResult(result);
    }
}
