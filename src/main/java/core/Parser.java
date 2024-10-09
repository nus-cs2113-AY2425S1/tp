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
        ArrayList<ArrayList<ArrayList<String>>> progRoutines = new ArrayList<>();

        String[] days = argumentString.split("/d");
        String progName = days[0].trim();

        for (int dayIndex = 1; dayIndex < days.length; dayIndex++) {
            String dayString = days[dayIndex].trim();

            ArrayList<ArrayList<String>> dayExercises = new ArrayList<>();

            String[] exercises = dayString.split("/e");
            for (String exerciseDescription : exercises) {
                if (exerciseDescription.trim().isEmpty()) {
                    continue;
                }

                String[] exerciseArguments = parseArguments(exerciseDescription, " /n", " /s", " /r", " /w");

                if (exerciseArguments.length != 5) {
                    throw new IllegalArgumentException("Invalid exercise command. Please provide a name, " + "set, rep, and weight using '/n', '/s', '/r', '/w'.");
                }

                ArrayList<String> exerciseDetails = new ArrayList<>();
                exerciseDetails.add(exerciseArguments[1].trim()); // name
                exerciseDetails.add(exerciseArguments[2].trim()); // set
                exerciseDetails.add(exerciseArguments[3].trim()); // rep
                exerciseDetails.add(exerciseArguments[4].trim()); // weight

                dayExercises.add(exerciseDetails);
            }
            progRoutines.add(dayExercises);
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

    private Command createEditCommand(String argumentString){
        String[] args = argumentString.trim().split(" ");
        int progIndex = -1;
        int dayIndex = -1;
        int exerciseIndex = -1;

        String newName = "";
        int newSets = -1;
        int newReps = -1;
        int newWeight = -1;
        boolean isAdding = false;
        boolean isEditing = false;

        for (int i = 0 ; i < args.length ; i++){
            if (args[i].trim().isEmpty()) {
                continue;
            }

            String command = args[i].trim();
            String value = "";
            if (i + 1 < args.length && !args[i + 1].trim().isEmpty()) {
                value = args[i + 1].trim();
            }
            if (command.equals("/p") || command.equals("/d") || command.equals("/x") || command.equals("/u") || command.equals("/a")) {
                if (isAdding) {
                    System.out.printf("Adding exercise to Prog %d Day %d with n %s s %d r %d w %d %n", progIndex, dayIndex, newName, newSets, newReps, newWeight);
                    isAdding = false;
                }

                if (isEditing) {
                    System.out.printf("Editing exercise to Prog %d Day %d with n %s s %d r %d w %d %n", progIndex, dayIndex, newName, newSets, newReps, newWeight);
                    isEditing = false;
                }
            }

            switch (command) {

            case "/p":
                progIndex = parseIndex(value);
                System.out.println(progIndex);
                break;

            case "/d":
                dayIndex = parseIndex(value);
                System.out.println(progIndex);
                break;

            case "/x":
                exerciseIndex = parseIndex(value);
                System.out.println(exerciseIndex);
                break;

            case "/u":
                isEditing = true;
                exerciseIndex = parseIndex(value);
                System.out.println(exerciseIndex);
                break;

            case "/a":
                isAdding = true;
                break;

            case "/n":
                newName = value;
                break;
            case "/s":
                newSets = Integer.parseInt(value);
                break;
            case "/r":
                newReps = Integer.parseInt(value);
                break;
            case "/w":
                newWeight = Integer.parseInt(value);
                break;

            default: break;
            }



            i++;
        }

        if (isAdding) {
            System.out.printf("Adding exercise to Prog %d Day %d with n %s s %d r %d %n", progIndex, dayIndex, newName, newSets, newReps, newWeight);
            isAdding = false;
        }

        if (isEditing) {
            System.out.printf("Editing exercise to Prog %d Day %d with n %s s %d r %d %n", progIndex, dayIndex, newName, newSets, newReps, newWeight);
            isEditing = false;
        }

        return new InvalidCommand();
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
