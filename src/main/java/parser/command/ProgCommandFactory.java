package parser.command;

import command.Command;
import command.InvalidCommand;
import parser.FlagParser;
import programme.Day;
import programme.Exercise;

import command.programme.CreateCommand;
import command.programme.ViewCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
import command.programme.EditCommand;
import command.programme.DeleteCommand;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static parser.ParserUtils.parseIndex;
import static parser.ParserUtils.splitArguments;


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
        default -> new InvalidCommand();
        };
    }
    private Command prepareEditCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] args = argumentString.split("/(?![nrswe])");
        EditCommand editCommand = new EditCommand();

        int progIndex = -1;
        int dayIndex = -1;
        int exerciseIndex;

        for (String arg : args) {
            if (arg.trim().isEmpty()) {
                continue;
            }

            String[] argParts = arg.trim().split(" ", 2);
            String flag = argParts[0].trim();
            String value = argParts.length > 1 ? argParts[1].trim() : "";

            logger.log(Level.INFO, "Processing flag: {0} with value: {1}", new Object[]{flag, value});

            switch (flag) {
            case "p":
                progIndex = parseIndex(value);
                break;

            case "d": // Day index
                dayIndex = parseIndex(value);
                break;

            case "x": // Remove exercise at index
                exerciseIndex = parseIndex(value);
                editCommand.addDeleteExercise(progIndex, dayIndex, exerciseIndex);
                break;

            case "xd":
                editCommand.addDeleteDay(progIndex, parseIndex(value));
                break;

            case "u": // Update exercise (parse the value string to create an Exercise)
                String[] updateParts = value.split(" ", 2);
                exerciseIndex = parseIndex(updateParts[0]);
                Exercise updated = parseExercise(updateParts[1]);
                editCommand.addEditExercise(progIndex, dayIndex, exerciseIndex, updated);
                break;

            case "a": // Add new exercise (parse the value string to create an Exercise)
                Exercise created = parseExercise(value);
                editCommand.addCreateExercise(progIndex, dayIndex, created);
                break;

            case "ad":
                Day day = parseDay(value);
                editCommand.addCreateDay(progIndex, day);
                break;

            default:
                throw new IllegalArgumentException("Unknown flag: " + flag);
            }
        }
        return editCommand;
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

    private  Day parseDay(String dayString) {
        assert dayString != null : "Day string must not be null";

        String[] dayParts  = dayString.split("/e");
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

        String name = flagParser.getStringByFlag("/n");
        int sets = flagParser.getIntegerByFlag("/s");
        int reps = flagParser.getIntegerByFlag("/r");
        int weight = flagParser.getIntegerByFlag("/w");

        logger.log(Level.INFO, "Parsed exercise successfully with name: {0}, set: {1}, rep: {2}" +
                " weight: {3}", new Object[]{name, sets, reps, weight});

        return new Exercise(sets, reps, weight, name);
    }
}

