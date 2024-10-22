package parser;

import command.Command;
import command.InvalidCommand;
import programme.Day;
import programme.Exercise;

import command.programme.CreateCommand;
import command.programme.ViewCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
import command.programme.EditCommand;
import command.programme.DeleteCommand;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static parser.IndexParser.parseIndex;

public class ProgCommandParser {
    public static final String COMMAND_WORD = "prog";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Command parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] inputArguments = argumentString.split(" ", 2);

        String subCommandString = inputArguments[0];
        String arguments = "";

        if (inputArguments.length > 1 ){
            arguments = inputArguments[1];
        }

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

        FlagParser flagParser = new FlagParser(argumentString);
        EditCommand editCommand = new EditCommand();

        final int progIndex = Optional.ofNullable(flagParser.getFlagValue("/p"))
                .map(value -> parseIndex(value, "Invalid programme index."))
                .orElse(-1);

        final int dayIndex = Optional.ofNullable(flagParser.getFlagValue("/d"))
                .map(value -> parseIndex(value, "Invalid day index."))
                .orElseGet(() -> Optional.ofNullable(flagParser.getFlagValue("/xd"))
                        .map(value -> parseIndex(value, "Invalid day index in /xd flag."))
                        .orElse(-1));

        Optional.ofNullable(flagParser.getFlagValue("/ad"))
                .ifPresent(value -> {
                    Day day = parseDay(value);
                    editCommand.addCreateDay(progIndex, day);
                });

        Optional.ofNullable(flagParser.getFlagValue("/a"))
                .ifPresent(value -> {
                    Exercise created = parseExercise(value);
                    editCommand.addCreate(progIndex, dayIndex, created);
                });

        String flagValue = flagParser.getFlagValue("/xd");
        if (flagValue != null) {
            editCommand.addDeleteDay(progIndex, dayIndex);
        }

        Optional.ofNullable(flagParser.getFlagValue("/x"))
                .ifPresent(value -> {
                    int exerciseIndex = parseIndex(value, "Invalid exercise index for deletion.");
                    editCommand.addDelete(progIndex, dayIndex, exerciseIndex);
                });

        Optional.ofNullable(flagParser.getFlagValue("/u"))
                .ifPresent(value -> {
                    String[] updateParts = value.split(" ", 2);
                    int exerciseIndex = parseIndex(updateParts[0], "Invalid exercise index for update.");
                    Exercise updated = parseExercise(updateParts[1]);
                    editCommand.addEdit(progIndex, dayIndex, exerciseIndex, updated);
                });


        logger.log(Level.INFO, "EditCommand prepared successfully");
        return editCommand;
    }

    private Command prepareCreateCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        ArrayList<Day> days = new ArrayList<>();
        String[] progParts = argumentString.split("/d");
        String progName = progParts[0].trim();
        if (progName.isEmpty()) {
            throw new IllegalArgumentException("Programme name cannot be empty. Please enter a valid programme name.");
        }

        for (int i = 1; i < progParts.length; i++) {
            String dayString = progParts[i].trim();
            Day day = parseDay(dayString);
            days.add(day);
        }

        logger.log(Level.INFO, "CreateCommand prepared with programme: {0}", progName);
        return new CreateCommand(progName, days);
    }

    private Day parseDay(String dayString) {
        assert dayString != null : "Day string must not be null";

        String[] dayParts  = dayString.split("/e");
        String dayName = dayParts[0].trim();

        //if day name empty then throw error??? or leave it for multi line typing
        Day day = new Day(dayName);

        for (int j = 1; j < dayParts.length; j++) {
            String exerciseString = dayParts[j].trim();
            Exercise exercise = parseExercise(exerciseString);
            day.insertExercise(exercise);
        }

        logger.log(Level.INFO, "Parsed day successfully: {0}", dayName);
        return day;
    }

    //check for invalid flags???
    private Exercise parseExercise(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        FlagParser flagParser = new FlagParser(argumentString);
        String[] requiredFlags = {"/n", "/s", "/r", "/w"};
        validateRequiredFlags(flagParser, requiredFlags);

        String name = flagParser.getFlagValue("/n");
        int sets = parseIndex(flagParser.getFlagValue("/s"), "Invalid sets value. ");
        int reps = parseIndex(flagParser.getFlagValue("/r"), "Invalid reps value. ");
        int weight = parseIndex(flagParser.getFlagValue("/s"), "Invalid weight value. ");

        logger.log(Level.INFO, "Parsed exercise successfully with name: {0}, set: {1}, rep: {2}" +
                " weight: {3}", new Object[]{name, sets, reps, weight});

        return new Exercise(sets, reps, weight, name);
    }

    private void validateRequiredFlags(FlagParser flagParser, String[] requiredFlags) {
        assert requiredFlags != null : "Required flags string must not be null";

        for (String flag : requiredFlags) {
            if (!flagParser.hasFlag(flag)) {
                throw new IllegalArgumentException("Required flag: " + flag + "is missing. Please provide the flag.");
            }
        }
    }

    private Command prepareViewCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString, "Invalid programme index. ");
        return new ViewCommand(progIndex);
    }

    private Command prepareStartCommand(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString, "Invalid programme index. ");
        return new StartCommand(progIndex);
    }

    private Command prepareDeleteCommand(String argumentString){
        assert argumentString != null : "Argument string must not be null";

        int progIndex = parseIndex(argumentString, "Invalid programme index. ");
        return new DeleteCommand(progIndex);
    }
}
