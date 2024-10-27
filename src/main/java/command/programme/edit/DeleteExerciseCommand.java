package command.programme.edit;
import command.CommandResult;
import programme.Day;
import programme.Exercise;
import programme.ProgrammeList;

public class DeleteExerciseCommand extends EditCommand {

    public DeleteExerciseCommand(int progId, int dayId, int exerciseId) {
        super(progId, dayId, exerciseId);
    }

    @Override
    public CommandResult execute(ProgrammeList programmes) {
        Day day = programmes.getDay(progId, dayId);
        Exercise deleted = day.deleteExercise(exerciseId);
        String result = String.format("Deleted exercise %d: %s%n", exerciseId, deleted);
        return new CommandResult(result);
    }
}
