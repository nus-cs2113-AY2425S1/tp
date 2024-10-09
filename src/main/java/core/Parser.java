package core;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;
import command.programme.CreateCommand;
import command.programme.ViewCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
import command.programme.EditCommand;

import programme.Day;
import programme.Exercise;

import java.util.ArrayList;



public class Parser {
    public static final String PROGRAMME_CMD = "prog";

    public Command parse(String fullCommand) {
        String[] inputArguments = fullCommand.split(" ", 2);

        String commandString = inputArguments[0];
        String argumentString = "";

        if (inputArguments.length > 1 ){
            argumentString = inputArguments[1];
        }

        switch (commandString) {
        case PROGRAMME_CMD: return parseProgammeCommands(argumentString);
        case LogCommand.COMMAND_WORD: return prepareLogCommand(argumentString);
        case HistoryCommand.COMMAND_WORD: return new HistoryCommand();
        case ExitCommand.COMMAND_WORD: return new ExitCommand();
        default: return new InvalidCommand();
        }
    }

    private Command parseProgammeCommands(String argumentString) {
        String[] inputArguments = argumentString.split(" ", 2);

        String subCommandString = inputArguments[0];
        String arguments = "";

        if (inputArguments.length > 1 ){
            arguments = inputArguments[1];
        }

        switch (subCommandString) {
        case CreateCommand.COMMAND_WORD: return prepareCreateCommand(arguments);
        case ViewCommand.COMMAND_WORD: return prepareViewCommand(arguments);
        case ListCommand.COMMAND_WORD: return new ListCommand();
        case "edit": return prepareEditCommand(arguments);
        case StartCommand.COMMAND_WORD: return prepareStartCommand(arguments);
        default: return new InvalidCommand();
        }
    }

    private Command prepareCreateCommand(String argumentString) {
        String[] progParts = argumentString.split("/d");
        String progName = progParts[0].trim();
        ArrayList<Day> days = new ArrayList<>();

        for (int i = 1; i < progParts.length; i++) {
            String dayString = progParts[i].trim();
            String[] dayParts  = dayString.split("/e");
            String dayName = dayParts[0].trim();

            Day day = new Day(dayName);

            for (int j = 1; j < dayParts.length; j++) {
                String exerciseString = dayParts[j].trim();
                Exercise exercise = parseExercise(exerciseString);
                day.insertExercise(exercise);
            }

            days.add(day);
        }

        return new CreateCommand(progName, days);
    }

    private Command prepareEditCommand(String argumentString) {
        // Regex: Split string by / except when followed by n, r, s, w
        String[] args = argumentString.split("/(?![nrsw])");
        EditCommand editCommand = new EditCommand();

        int progIndex = -1;
        int dayIndex = -1;
        int exerciseIndex = -1;

        for (String arg : args) {
            if (arg.trim().isEmpty()) {
                continue;
            }

            String[] commandAndValue = arg.trim().split(" ", 2);
            String flag = commandAndValue[0].trim();
            String value = commandAndValue.length > 1 ? commandAndValue[1].trim() : "";

            switch (flag) {
            case "p":
                progIndex = parseIndex(value);
                break;

            case "d": // Day index
                dayIndex = parseIndex(value);
                break;

            case "x": // Remove exercise at index
                exerciseIndex = parseIndex(value);
                editCommand.addDelete(progIndex, dayIndex, exerciseIndex);
                break;

            case "u": // Update exercise (parse the value string to create an Exercise)
                String[] updateParts = value.split(" ", 2);
                exerciseIndex = parseIndex(updateParts[0]);
                Exercise updated = parseExercise(updateParts[1]);
                editCommand.addEdit(progIndex, dayIndex, exerciseIndex, updated);
                break;

            case "a": // Add new exercise (parse the value string to create an Exercise)
                Exercise created = parseExercise(value);
                editCommand.addCreate(progIndex, dayIndex, created);
                break;

            default:
                System.out.println("Unknown flag: " + flag);
                break;
            }
        }

        return editCommand;
    }

    private Exercise parseExercise(String exerciseString) {
        String name = "";
        int reps = -1;
        int sets = -1;
        int weight = -1;

        String[] args = exerciseString.split(" ");

        for (int i = 0; i < args.length; i+=2) {
            String command = args[i];
            String value = args[i+1];
            switch (command) {
            case "/n":
                name = value;
                break;
            case "/s":
                sets = Integer.parseInt(value);
                break;
            case "/r":
                reps = Integer.parseInt(value);
                break;
            case "/w":
                weight = Integer.parseInt(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid command flag " + command);
            }
        }

        return new Exercise(sets, reps, weight, name);
    }


    private Command prepareViewCommand(String argumentString) {
        int progIndex = parseIndex(argumentString);
        return new ViewCommand(progIndex);
    }

    private Command prepareStartCommand(String argumentString) {
        int progIndex = parseIndex(argumentString);
        return new StartCommand(progIndex);
    }

    private Command prepareLogCommand(String argumentString){
        String[] arguments = parseArguments(argumentString, "/p", "/d");

        if (arguments.length != 3) {
            throw new IllegalArgumentException("Invalid event command. " +
                    "Please provide a programme index, day index, and date using '/p' and '/d' and 'DATE'.");
        }

        int progIndex = parseIndex(arguments[0].trim());
        int dayIndex = parseIndex(arguments[1].trim());


        String date = arguments[2].trim();

        return new LogCommand(progIndex, dayIndex, date);
    }

    private int parseIndex(String indexString) {
        try {
            int index = Integer.parseInt(indexString.trim()) - 1;
            if (index < 0) {
                throw new IllegalArgumentException("Task index must be a positive number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task index. Please provide a valid number.");
        }
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join(" | ", delimiters);
        return argumentString.split(delimiterPattern);
    }
}
