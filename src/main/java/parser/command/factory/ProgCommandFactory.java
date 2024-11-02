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

import static parser.FlagDefinitions.DAY_FLAG;
import static parser.FlagDefinitions.DATE_FLAG;
import static parser.FlagDefinitions.EXERCISE_FLAG;
import static parser.FlagDefinitions.NAME_FLAG;
import static parser.FlagDefinitions.CALORIES_FLAG;
import static parser.FlagDefinitions.REPS_FLAG;
import static parser.FlagDefinitions.SETS_FLAG;
import static parser.FlagDefinitions.WEIGHT_FLAG;
import static parser.FlagDefinitions.PROGRAMME_FLAG;
import static parser.FlagDefinitions.UPDATE_EXERCISE_FLAG;
import static parser.FlagDefinitions.ADD_DAY_FLAG;
import static parser.FlagDefinitions.ADD_EXERCISE_FLAG;
import static parser.FlagDefinitions.REMOVE_DAY_FLAG;
import static parser.FlagDefinitions.REMOVE_EXERCISE_FLAG;

import static parser.ParserUtils.parseIndex;
import static parser.ParserUtils.splitArguments;

/**
 * The {@code ProgCommandFactory} class is a factory responsible for creating all program-related commands.
 * This class includes helper methods such as {@code parseDay} and {@code parseExercise} to handle
 * common parsing tasks across program-related commands, centralizing command creation and
 * simplifying command handling.
 *
 * <p>Supported Commands:</p>
 * <ul>
 *     <li>Create program command - handled by {@link #prepareCreateCommand}</li>
 *     <li>View program command - handled by {@link #prepareViewCommand}</li>
 *     <li>Edit program and exercises command - handled by {@link #prepareEditCommand}</li>
 *     <li>Start program command - handled by {@link #prepareStartCommand}</li>
 *     <li>Delete program command - handled by {@link #prepareDeleteCommand}</li>
 *     <li>Log program activity command - handled by {@link #prepareLogCommand}</li>
 * </ul>
 *
 */

public class ProgCommandFactory {
    public static final String COMMAND_WORD = "prog";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Parses the provided argument string to identify and create the appropriate command.
     *
     * @author nirala-ts
     * @param argumentString The command's argument string, which contains the subcommand and arguments.
     * @return The created {@code Command} object based on the subcommand specified in the argument string.
     */
    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        /*
         * Splits the full command input into the primary command and its associated arguments,
         * enabling identification of the command category.
         */
        String[] inputArguments = splitArguments(argumentString);
        String subCommandString = inputArguments[0];
        String arguments = inputArguments[1];

        logger.log(Level.INFO, "Successfully parsed sub-command: {0}, with arguments: {1}",
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

    /**
     * Prepares and returns a {@link CreateCommand} to create a new program with a specified name and days.
     *
     * @author nirala-ts
     * @param argumentString The argument string containing program details, including days and exercises.
     * @return A {@link CreateCommand} object that represents the request to create a new program.
     */
    private Command prepareCreateCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        ArrayList<Day> days = new ArrayList<>();

        // Splits the argument by DAY_FLAG to separate each day.
        // The first element represents the program name.
        String[] progParts = argumentString.split(DAY_FLAG);
        String progName = progParts[0].trim();
        if (progName.isEmpty()) {
            logger.log(Level.WARNING, "Programme name is empty");
            throw new IllegalArgumentException("Programme name cannot be empty. Please enter a name.");
        }

        for (int i = 1; i < progParts.length; i++) {
            String dayString = progParts[i].trim();
            Day day = parseDay(dayString);
            days.add(day);
        }

        logger.log(Level.INFO, "Successfully prepared CreateCommand with programme: {0}", progName);
        return new CreateCommand(progName, days);
    }

    /**
     * Parses a day string to create a {@link Day} object with exercises.
     *
     * @author nirala-ts
     * @param dayString The string containing the day's name and its exercises.
     * @return A {@link Day} object populated with exercises for a program.
     */
    private  Day parseDay(String dayString) {
        assert dayString != null : "Day string must not be null";

        // Splits the argument by EXERCISE_FLAG to separate each exercise.
        // The first element represents the exercise name.
        String[] dayParts  = dayString.split(EXERCISE_FLAG);
        String dayName = dayParts[0].trim();
        if (dayName.isEmpty()) {
            logger.log(Level.WARNING, "Day name is empty");
            throw new IllegalArgumentException("Day name cannot be empty. Please enter a valid day name.");
        }

        Day day = new Day(dayName);

        for (int j = 1; j < dayParts.length; j++) {
            String exerciseString = dayParts[j].trim();
            Exercise exercise = parseExercise(exerciseString);
            day.insertExercise(exercise);
        }

        logger.log(Level.INFO, "Successfully parsed day name: {0}", dayName);
        return day;
    }

    /**
     * Parses an exercise string to create an {@link Exercise} object with required attributes.
     *
     * @author nirala-ts
     * @param argumentString The string containing exercise details and flags.
     * @return An {@link Exercise} object initialized with the specified attributes.
     * @throws IllegalArgumentException If required flags are missing.
     * */
    private Exercise parseExercise(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        FlagParser flagParser = new FlagParser(argumentString);

        // Ensures the exercise contains all necessary information before creating a new Exercise
        flagParser.validateRequiredFlags(SETS_FLAG, REPS_FLAG, WEIGHT_FLAG, CALORIES_FLAG, NAME_FLAG);

        int sets = flagParser.getIntegerByFlag(SETS_FLAG);
        int reps = flagParser.getIntegerByFlag(REPS_FLAG);
        int weight = flagParser.getIntegerByFlag(WEIGHT_FLAG);
        int calories = flagParser.getIntegerByFlag(CALORIES_FLAG);
        String name = flagParser.getStringByFlag(NAME_FLAG);

        logger.log(Level.INFO, "Successfully parsed exercise details - Name: {0}, Sets: {1}, Reps: {2}, " +
                        "Weight: {3}, Calories: {4}", new Object[]{name, sets, reps, weight, calories});

        return new Exercise(sets, reps, weight, calories, name);
    }

    /**
     * Prepares and returns a {@link ViewCommand} to view a specific program by its index.
     *
     * @author nirala-ts
     * @param argumentString The string containing the program index to view.
     * @return A {@link ViewCommand} initialized with the specified program index.
     */
    private Command prepareViewCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString);

        logger.log(Level.INFO, "Successfully prepared ViewCommand");
        return new ViewCommand(progIndex);
    }

    /**
     * Prepares and returns a {@link StartCommand} to activate a specific program by its index.
     *
     * @author nirala-ts
     * @param argumentString The string containing the program index to start.
     * @return A {@link StartCommand} initialized with the specified program index.
     */
    private Command prepareStartCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString);

        logger.log(Level.INFO, "Successfully prepared StartCommand");
        return new StartCommand(progIndex);
    }

    /**
     * Prepares and returns a {@link DeleteCommand} to remove a specific program by its index.
     *
     * @author nirala-ts
     * @param argumentString The string containing the program index to delete.
     * @return A {@link DeleteCommand} initialized with the specified program index.
     */
    private Command prepareDeleteCommand(String argumentString){
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString);

        logger.log(Level.INFO, "Successfully prepared DeleteCommand");
        return new DeleteCommand(progIndex);
    }

    /**
     * Prepares and returns a {@link LogCommand} to log activity in a specific program on a particular date.
     *
     * @author nirala-ts
     * @param argumentString The string containing flags for the date, program index, and day index.
     * @return A {@link LogCommand} initialized with the specified date and indices.
     * @throws IllegalArgumentException If required flags are missing.
     */
    private Command prepareLogCommand(String argumentString) {
        FlagParser flagParser = new FlagParser(argumentString);

        flagParser.validateRequiredFlags(DAY_FLAG);

        LocalDate date = flagParser.getDateByFlag(DATE_FLAG);
        int progIndex = flagParser.getIndexByFlag(PROGRAMME_FLAG);
        int dayIndex = flagParser.getIndexByFlag(DAY_FLAG);

        logger.log(Level.INFO, "Successfully prepared LogCommand with Date: {0}, " +
                        "Programme index: {1}, Day index: {2}", new Object[]{progIndex, dayIndex, date});

        return new LogCommand(progIndex, dayIndex, date);
    }

    /**
     * Prepares and returns an appropriate {@link EditCommand} based on the flags parsed
     * from the provided argument string.
     *
     * @author TVageesan
     * @param argumentString A {@link String} containing arguments to parse.
     * @return The specific {@link EditCommand} object that corresponds to the flag detected.
     * @throws IllegalArgumentException If no recognized edit command flag is present in the argument string.
     */
    private EditCommand prepareEditCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        // Parse by flags except for all exercise related flags - preserve exerciseString for later parsing as needed
        FlagParser flagParser = new FlagParser (
                argumentString,
                NAME_FLAG, REPS_FLAG,SETS_FLAG,WEIGHT_FLAG,EXERCISE_FLAG,CALORIES_FLAG
        );

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

    /**
     * Creates and returns an {@link EditExerciseCommand} for updating an exercise in a program.
     *
     * @author TVageesan
     * @param flagParser A {@link FlagParser} containing parsed flags and arguments.
     * @return An {@link EditExerciseCommand} object to edit an existing exercise.
     * @throws IllegalArgumentException If required flags are missing.
     */
    private EditExerciseCommand prepareEditExerciseCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags(DAY_FLAG);
        String editString = flagParser.getStringByFlag("/u");

        String[] editParts = splitArguments(editString);
        int exerciseIndex = parseIndex(editParts[0]);
        String exerciseString = editParts[1];

        return new EditExerciseCommand(
            flagParser.getIndexByFlag(PROGRAMME_FLAG),
            flagParser.getIndexByFlag(DAY_FLAG),
            exerciseIndex,
            parseExerciseUpdate(exerciseString)
        );
    }


    /**
     * Creates and returns a {@link CreateExerciseCommand} for adding a new exercise to a day in a program.
     *
     * @author TVageesan
     * @param flagParser A {@link FlagParser} containing parsed flags and arguments.
     * @return A {@link CreateExerciseCommand} object to create a new exercise.
     * @throws IllegalArgumentException If required flags are missing.
     */
    private CreateExerciseCommand prepareCreateExerciseCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags(DAY_FLAG);
        String exerciseString = flagParser.getStringByFlag(ADD_EXERCISE_FLAG);
        return new CreateExerciseCommand(
            flagParser.getIndexByFlag(PROGRAMME_FLAG),
            flagParser.getIndexByFlag(DAY_FLAG),
            parseExercise(exerciseString)
        );
    }

    /**
     * Creates and returns a {@link DeleteExerciseCommand} for removing an exercise from a day in a program.
     *
     * @author TVageesan
     * @param flagParser A {@link FlagParser} containing parsed flags and arguments.
     * @return A {@link DeleteExerciseCommand} object to delete an existing exercise.
     * @throws IllegalArgumentException If required flags are missing.
     */
    private DeleteExerciseCommand prepareDeleteExerciseCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags(DAY_FLAG, REMOVE_EXERCISE_FLAG);
        return new DeleteExerciseCommand(
                flagParser.getIndexByFlag(PROGRAMME_FLAG),
                flagParser.getIndexByFlag(DAY_FLAG),
                flagParser.getIndexByFlag(REMOVE_EXERCISE_FLAG)
        );
    }

    /**
     * Creates and returns a {@link CreateDayCommand} for adding a new day to a program.
     *
     * @author TVageesan
     * @param flagParser A {@link FlagParser} containing parsed flags and arguments.
     * @return A {@link CreateDayCommand} object to create a new day.
     */
    private CreateDayCommand prepareCreateDayCommand(FlagParser flagParser) {
        String dayString = flagParser.getStringByFlag(ADD_DAY_FLAG);
        return new CreateDayCommand(
                flagParser.getIndexByFlag(PROGRAMME_FLAG),
                parseDay(dayString)
        );
    }

    /**
     * Creates and returns a {@link DeleteDayCommand} for removing a day from a program.
     *
     * @author TVageesan
     * @param flagParser A {@link FlagParser} containing parsed flags and arguments.
     * @return A {@link DeleteDayCommand} object to delete an existing day.
     * @throws IllegalArgumentException If required flags are missing.
     */
    private DeleteDayCommand prepareDeleteDayCommand(FlagParser flagParser) {
        flagParser.validateRequiredFlags(REMOVE_DAY_FLAG);
        return new DeleteDayCommand(
                flagParser.getIndexByFlag(PROGRAMME_FLAG),
                flagParser.getIndexByFlag(REMOVE_DAY_FLAG)
        );
    }

    // @@author TVageesan
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

