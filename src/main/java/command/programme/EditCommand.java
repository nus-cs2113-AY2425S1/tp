package command.programme;
import command.Command;
import command.programme.exercise.ExerciseCommand;
import command.programme.exercise.CreateExerciseCommand;
import command.programme.exercise.DeleteExerciseCommand;
import command.programme.exercise.EditExerciseCommand;

import core.Ui;
import programme.Exercise;
import programme.ProgrammeList;
import core.History;

import java.util.ArrayList;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    private ArrayList<ExerciseCommand> subCommands;

    public EditCommand(){
        subCommands = new ArrayList<>();
    }

    public void addCreate(int progId, int dayId, Exercise created){
        CreateExerciseCommand c = new CreateExerciseCommand(progId, dayId, created);
        subCommands.add(c);
    }

    public void addDelete(int progId, int dayId, int exerciseId){
        DeleteExerciseCommand c = new DeleteExerciseCommand(progId, dayId, exerciseId);
        subCommands.add(c);
    }

    public void addEdit(int progId, int dayId, int exerciseId, Exercise updated){
        EditExerciseCommand c = new EditExerciseCommand(progId, dayId, exerciseId, updated);
        subCommands.add(c);
    }

    @Override
    public void execute(Ui ui, ProgrammeList pList, History history){
        for (ExerciseCommand c : subCommands){
            String result =  c.execute(pList);
            ui.showMsg(result);
        }
    }
}
