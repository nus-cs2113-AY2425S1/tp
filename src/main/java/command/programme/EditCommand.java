package command.programme;

import command.CommandResult;
import command.programme.edit.CreateDayCommand;
import command.programme.edit.DeleteDayCommand;
import command.programme.edit.EditSubCommand;
import command.programme.edit.DeleteExerciseCommand;
import command.programme.edit.EditExerciseCommand;
import command.programme.edit.CreateExerciseCommand;


import programme.Exercise;
import programme.Day;
import programme.ProgrammeList;

import history.History;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditCommand extends ProgrammeCommand {

    public static final String COMMAND_WORD = "edit";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final ArrayList<EditSubCommand> subCommands;

    public EditCommand() {
        subCommands = new ArrayList<>();
        logger.log(Level.INFO, "EditCommand initialized with empty subCommands");
    }

    public ArrayList<EditSubCommand> getSubCommands() {
        return subCommands;
    }

    public void addCreateExercise(int progId, int dayId, Exercise created) {
        assert progId >= 0 : "Program ID must be non-negative";
        assert dayId >= 0 : "Day ID must be non-negative";
        assert created != null : "Created exercise must not be null";

        CreateExerciseCommand c = new CreateExerciseCommand(progId, dayId, created);
        subCommands.add(c);

        logger.log(Level.INFO, "Added CreateExerciseCommand: Program ID = {0}, Day ID = {1}, Exercise = {2}",
                new Object[]{progId, dayId, created});
    }

    public void addDeleteExercise(int progId, int dayId, int exerciseId) {
        assert progId >= 0 : "Program ID must be non-negative";
        assert dayId >= 0 : "Day ID must be non-negative";
        assert exerciseId >= 0 : "Exercise ID must be non-negative";

        DeleteExerciseCommand c = new DeleteExerciseCommand(progId, dayId, exerciseId);
        subCommands.add(c);

        logger.log(Level.INFO, "Added DeleteExerciseCommand: Program ID = {0}, Day ID = {1}, Exercise ID = {2}",
                new Object[]{progId, dayId, exerciseId});
    }

    public void addEditExercise(int progId, int dayId, int exerciseId, Exercise updated) {
        assert progId >= 0 : "Program ID must be non-negative";
        assert dayId >= 0 : "Day ID must be non-negative";
        assert exerciseId >= 0 : "Exercise ID must be non-negative";
        assert updated != null : "Updated exercise must not be null";

        EditExerciseCommand c = new EditExerciseCommand(progId, dayId, exerciseId, updated);
        subCommands.add(c);

        logger.log(
                Level.INFO,
                "Added EditExerciseCommand: Program ID = {0}, Day ID = {1}, Exercise ID = {2}, Updated Exercise = {3}",
                new Object[]{progId, dayId, exerciseId, updated}
        );
    }

    public void addCreateDay(int progId, Day day) {
        assert progId >= 0 : "Program ID must be non-negative";
        assert day != null : "Day must not be null";

        CreateDayCommand c = new CreateDayCommand(progId, day);
        subCommands.add(c);

        logger.log(Level.INFO, "Added CreateDayCommand: Program ID = {0}, Day = {1}",
                new Object[]{progId, day});
    }

    public void addDeleteDay(int progId, int dayId) {
        assert progId >= 0 : "Program ID must be non-negative";
        assert dayId >= 0 : "Day ID must be non-negative";

        DeleteDayCommand c = new DeleteDayCommand(progId, dayId);
        subCommands.add(c);

        logger.log(Level.INFO, "Added DeleteDayCommand: Program ID = {0}, Day ID = {1}",
                new Object[]{progId, dayId});
    }

    @Override
    public CommandResult execute(ProgrammeList pList, History history) {
        assert pList != null : "ProgrammeList must not be null";
        assert history != null : "History must not be null";
        StringBuilder result = new StringBuilder();

        logger.log(Level.INFO, "Executing EditCommand with {0} subCommands", subCommands.size());

        for (EditSubCommand c : subCommands) {
            String subResult = c.execute(pList);
            result.append(subResult);
            logger.log(Level.INFO, "SubCommand executed: {0}", result);
        }

        logger.log(Level.INFO, "EditCommand execution completed");

        return new CommandResult(result.toString());
    }
}
