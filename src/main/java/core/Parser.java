package core;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;
import command.programme.CreateCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
import command.programme.ViewCommand;
import programme.Day;
import programme.Exercise;


import java.util.ArrayList;



public class Parser {
    public static final String PROGRAMME_CMD = "prog";

    public Command parse(String fullCommand) {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty. Please enter a valid command.");
        }

        String[] inputArguments = fullCommand.split(" ", 2);

        if (inputArguments.length != 2) {
            throw new IllegalArgumentException("Command cannot be empty. Please enter a valid command.");
        }

        String commandString = inputArguments[0];
        String argumentString = inputArguments[1];

        switch (commandString) {
        case PROGRAMME_CMD: return parseProgCommands(argumentString);
        case LogCommand.COMMAND_WORD: return createLogCommand(argumentString);
        case HistoryCommand.COMMAND_WORD: return createHistoryCommand();
        case ExitCommand.COMMAND_WORD: return createExitCommand();
        default: return createInvalidCommand();
        }
    }

    private Command parseProgCommands(String argumentString) {
        String[] inputArguments = argumentString.split(" ", 2);

        if (inputArguments.length != 2) {
            throw new IllegalArgumentException("Command cannot be empty. Please enter a valid command.");
        }

        String subCommandString = inputArguments[0];
        String arguments = inputArguments[1];

        switch (subCommandString) {
        case CreateCommand.COMMAND_WORD: return createCreateProgCommand(arguments);
        case ViewCommand.COMMAND_WORD: return createViewCommand(arguments);
        case ListCommand.COMMAND_WORD: return createListCommand();
        case "edit": return createEditCommand(arguments);
        case StartCommand.COMMAND_WORD: return createStartCommand(arguments);
        default: return createInvalidCommand();
        }
    }

    private Command createCreateProgCommand(String argumentString) {
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

    private Command createEditCommand(String argumentString) {
        // Regex: Split string by / except when followed by n, r, s, w
        String[] args = argumentString.split("/(?![nrsw])");

        int progIndex = -1;
        int dayIndex = -1;
        int exerciseIndex = -1;

        for (int i = 0; i < args.length; i++) {
            if (args[i].trim().isEmpty()) {
                continue;
            }

            String[] commandAndValue = args[i].trim().split(" ", 2);
            String command = commandAndValue[0].trim();
            String value = commandAndValue.length > 1 ? commandAndValue[1].trim() : "";

            switch (command) {
            case "p":
                progIndex = parseIndex(value);
                break;

            case "d": // Day index
                dayIndex = parseIndex(value);
                break;

            case "x": // Remove exercise at index
                exerciseIndex = parseIndex(value);
                System.out.println("Remove exercise index: " + exerciseIndex);
                break;

            case "u": // Update exercise (parse the value string to create a Exercise)
                String[] updateParts = value.split(" ", 2);
                exerciseIndex = parseIndex(updateParts[0]);
                Exercise updated = parseExercise(updateParts[1]);
                // The Exercise object in this case is used purely to update relevant fields
                System.out.println("Updated exercise: " + updated);
                break;

            case "a": // Add new exercise (parse the value string to create a Exercise)
                Exercise created = parseExercise(value);
                System.out.println("Added exercise: " + created);
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
            }
        }

        return new InvalidCommand();
    }

    private Command createViewCommand(String argumentString) {
        int progIndex = parseIndex(argumentString);
        return new ViewCommand(progIndex);
    }

    private Command createListCommand() {
        return new ListCommand();
    }

    private Command createStartCommand(String argumentString) {
        int progIndex = parseIndex(argumentString);
        return new StartCommand(progIndex);
    }

    private Command createLogCommand(String argumentString){
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

    private Command createHistoryCommand() {
        return new HistoryCommand();
    }

    private Command createExitCommand() {
        return new ExitCommand();
    }

    private Command createInvalidCommand() {
        return new InvalidCommand();
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
