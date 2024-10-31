package parser.command.factory;

import command.Command;
import command.InvalidCommand;
import command.programme.CreateCommand;
import command.programme.ViewCommand;
import command.programme.edit.EditCommand;
import command.programme.edit.EditExerciseCommand;
import command.programme.edit.CreateExerciseCommand;
import command.programme.edit.DeleteExerciseCommand;
import command.programme.edit.CreateDayCommand;
import command.programme.edit.DeleteDayCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
import command.programme.DeleteCommand;
import command.programme.LogCommand;

import parser.FlagParser;
import programme.Day;
import programme.Exercise;
import programme.ExerciseUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static parser.ParserUtils.parseIndex;
import static parser.ParserUtils.splitArguments;

import static parser.FlagDefinitions.ADD_DAY_FLAG;
import static parser.FlagDefinitions.REMOVE_DAY_FLAG;
import static parser.FlagDefinitions.ADD_EXERCISE_FLAG;
import static parser.FlagDefinitions.REMOVE_EXERCISE_FLAG;
import static parser.FlagDefinitions.UPDATE_EXERCISE_FLAG;
import static parser.FlagDefinitions.SETS_FLAG;
import static parser.FlagDefinitions.CALORIES_FLAG;
import static parser.FlagDefinitions.REPS_FLAG;
import static parser.FlagDefinitions.WEIGHT_FLAG;
import static parser.FlagDefinitions.NAME_FLAG;

/*
    ProgCommandFactory is a factory class that creates all programme related commands
    This class also contains helper functions i.e. parseDay, parseExercise
    that are common to programme related commands.
 */

public class ProgCommandFactory {
    public static final String COMMAND_WORD = "prog";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = splitArguments(argumentString);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments[1];

        logger.log(Level.INFO, "Parsed sub-command: {0}, with arguments: {1}",
                new Object[]{subCommandString, arguments});

        return switch (subCommandString) {
        case CreateCommand.COMMAND_WORD -> prepareCreateCommand(arguments);
        case ViewCommand.COMMAND_WORD -> prepareViewCommand(arguments);
        case ListCommand.COMMAND_WORD -> new ListCommand();
        case EditCommand.COMMAND_WORD -> prepareEditCommand(arguments);
        case StartCommand.COMMAND_WORD -> prepareStartCommand(arguments);
        case DeleteCommand.COMMAND_WORD ->  prepareDeleteCommand(arguments);
        case LogCommand.COMMAND_WORD -> prepareLogCommand(arguments);
        default -> new InvalidCommand();
        };
    }

    // @@author TVageesan
    private Command prepareEditCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";
        FlagParser flagParser = new FlagParser(argumentString, "/n", "/r","/s","/w","/e","/c");
        if (flagParser.hasFlag(UPDATE_EXERCISE_FLAG)) {
            return prepareEditExerciseCommand(flagParser);
        }
        if (flagParser.hasFlag(ADD_EXERCISE_FLAG)) {
            return prepareCreateExerciseCommand(flagParser);
        }
        if (flagParser.hasFlag(REMOVE_EXERCISE_FLAG)) {
            return prepareDeleteExerciseCommand(flagParser);
        }
        if (flagParser.hasFlag(ADD_DAY_FLAG)) {
            return prepareCreateDayCommand(flagParser);
        }
        if (flagParser.hasFlag(REMOVE_DAY_FLAG)) {
            return prepareDeleteDayCommand(flagParser);
        }

        throw new IllegalArgumentException("Missing edit command flag. Please provide a valid command flag.");
    }

    private EditExerciseCommand prepareEditExerciseCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags("/d");
        String editString = flagParser.getStringByFlag("/u");

        String[] editParts = splitArguments(editString);
        int exerciseIndex = parseIndex(editParts[0]);
        String exerciseString = editParts[1];

        return new EditExerciseCommand(
            flagParser.getIndexByFlag("/p"),
            flagParser.getIndexByFlag("/d"),
            exerciseIndex,
            parseExerciseUpdate(exerciseString)
        );
    }

    private CreateExerciseCommand prepareCreateExerciseCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags("/d");
        String exerciseString = flagParser.getStringByFlag("/a");
        return new CreateExerciseCommand(
            flagParser.getIndexByFlag("/p"),
            flagParser.getIndexByFlag("/d"),
            parseExercise(exerciseString)
        );
    }

    private DeleteExerciseCommand prepareDeleteExerciseCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags("/d", "/x");
        return new DeleteExerciseCommand(
                flagParser.getIndexByFlag("/p"),
                flagParser.getIndexByFlag("/d"),
                flagParser.getIndexByFlag("/x")
        );
    }

    private CreateDayCommand prepareCreateDayCommand(FlagParser flagParser) {
        String dayString = flagParser.getStringByFlag("/ad");
        return new CreateDayCommand(
                flagParser.getIndexByFlag("/p"),
                parseDay(dayString)
        );
    }

    private DeleteDayCommand prepareDeleteDayCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags("/xd");
        return new DeleteDayCommand(
                flagParser.getIndexByFlag("/p"),
                flagParser.getIndexByFlag("/xd")
        );
    }

    private Command prepareCreateCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        ArrayList<Day> days = new ArrayList<>();
        String[] progParts = argumentString.split("/d");
        String progName = progParts[0].trim();
        if (progName.isEmpty()) {
            throw new IllegalArgumentException("Programme name cannot be empty. Please enter a name.");
        }

        for (int i = 1; i < progParts.length; i++) {
            String dayString = progParts[i].trim();
            Day day = parseDay(dayString);
            days.add(day);
        }

        logger.log(Level.INFO, "CreateCommand prepared with programme: {0}", progName);
        return new CreateCommand(progName, days);
    }

    // @@author

    private Command prepareViewCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString);

        logger.log(Level.INFO, "ViewCommand prepared successfully");
        return new ViewCommand(progIndex);
    }

    private Command prepareStartCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString);

        logger.log(Level.INFO, "StartCommand prepared successfully");
        return new StartCommand(progIndex);
    }

    private Command prepareDeleteCommand(String argumentString){
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString);

        logger.log(Level.INFO, "DeleteCommand prepared successfully");
        return new DeleteCommand(progIndex);
    }

    private Command prepareLogCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);

        flagParser.validateRequiredFlags("/d");

        LocalDate date = flagParser.getDateByFlag("/t");
        int progIndex = flagParser.getIndexByFlag("/p");
        int dayIndex = flagParser.getIndexByFlag("/d");

        logger.log(Level.INFO, "LogCommand prepared with Date: {0}, Programme index: {1}, Day index: {2}",
                new Object[]{progIndex, dayIndex, date});

        return new LogCommand(progIndex, dayIndex, date);
    }

    // @@author TVageesan

    private  Day parseDay(String dayString) {
        assert dayString != null : "Day string must not be null";

        String[] dayParts  = dayString.split(EXERCISE_FLAG);
        String dayName = dayParts[0].trim();
        if (dayName.isEmpty()) {
            throw new IllegalArgumentException("Day name cannot be empty. Please enter a valid day name.");
        }

        Day day = new Day(dayName);

        for (int j = 1; j < dayParts.length; j++) {
            String exerciseString = dayParts[j].trim();
            Exercise exercise = parseExercise(exerciseString);
            day.insertExercise(exercise);
        }

        logger.log(Level.INFO, "Parsed day successfully: {0}", dayName);
        return day;
    }

    private Exercise parseExercise(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        FlagParser flagParser = new FlagParser(argumentString);
        flagParser.validateRequiredFlags(SETS_FLAG, REPS_FLAG, WEIGHT_FLAG, CALORIES_FLAG, NAME_FLAG);

        return new Exercise(
                flagParser.getIntegerByFlag(SETS_FLAG),
                flagParser.getIntegerByFlag(REPS_FLAG),
                flagParser.getIntegerByFlag(WEIGHT_FLAG),
                flagParser.getIntegerByFlag(CALORIES_FLAG),
                flagParser.getStringByFlag(NAME_FLAG)
        );
    }

    private ExerciseUpdate parseExerciseUpdate(String argumentString){
        assert argumentString != null : "Argument string must not be null";

        FlagParser flagParser = new FlagParser(argumentString);

        return new ExerciseUpdate(
                flagParser.getIntegerByFlag(SETS_FLAG),
                flagParser.getIntegerByFlag(REPS_FLAG),
                flagParser.getIntegerByFlag(WEIGHT_FLAG),
                flagParser.getIntegerByFlag(CALORIES_FLAG),
                flagParser.getStringByFlag(NAME_FLAG)
        );
    }
}

