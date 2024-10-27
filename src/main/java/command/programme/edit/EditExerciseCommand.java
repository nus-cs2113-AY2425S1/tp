package command.programme.edit;

import command.CommandResult;
import programme.Day;
import programme.Exercise;
import programme.ProgrammeList;

public class EditExerciseCommand extends EditCommand {
    private final Exercise update;

    public EditExerciseCommand(int progId, int dayId, int exerciseId, Exercise update) {
        super(progId,dayId, exerciseId);
        this.update = update;
    }

    public CommandResult execute(ProgrammeList programmes) {
        Day day = programmes.getDay(progId, dayId);
        Exercise updated = day.updateExercise(exerciseId, update);
        String result = String.format("Update exercise %d to: %s%n",exerciseId, updated);
        return new CommandResult(result);
    }
}
