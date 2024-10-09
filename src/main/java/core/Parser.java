package core;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;
import command.programme.CreateCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
//import command.programme.EditCommand;
import command.programme.ViewCommand;
import programme.Exercise;

import java.util.ArrayList;


public class Parser {

    public Command parse(String fullCommand) {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty. Please enter a valid command.");
        }

        String[] inputArguments = fullCommand.split(" ", 3);
        String commandString = "";
        String argumentString = "";

        if (inputArguments.length > 2) {
            commandString = inputArguments[1];
            argumentString = inputArguments[2];
        }

        switch (commandString) {
        case CreateCommand.COMMAND_WORD: return createCreateProgCommand(argumentString);
        case ViewCommand.COMMAND_WORD: return createViewCommand(argumentString);
        case ListCommand.COMMAND_WORD: return createListCommand();
        //case EditCommand.COMMAND_WORD: return createEditCommand(argumentString);
        case StartCommand.COMMAND_WORD: return createStartCommand(argumentString);
        case LogCommand.COMMAND_WORD: return createLogCommand(argumentString);
        //case ActiveCommand.COMMAND_WORD: return createActiveCommand(argumentString);
        case HistoryCommand.COMMAND_WORD: return createHistoryCommand();
        case ExitCommand.COMMAND_WORD: return createExitCommand();
        default: return createInvalidCommand();
        }
    }

    private Command createCreateProgCommand(String argumentString) {
        ArrayList<ArrayList<Exercise>> progRoutines = new ArrayList<>();

        String[] days = argumentString.split(" /d");
        String progName = days[0].trim();

        for (int dayIndex = 1; dayIndex < days.length; dayIndex++) {
            String dayString = days[dayIndex].trim();

            ArrayList<Exercise> dayExercises = new ArrayList<>();

            String[] exercises = dayString.split("/e");
            for (String exerciseDescription : exercises) {
                if (exerciseDescription.trim().isEmpty()) {
                    continue;
                }

                String[] exerciseArguments = parseArguments(exerciseDescription, " /n", " /s", " /r", " /w");

                if (exerciseArguments.length != 5) {
                    throw new IllegalArgumentException("Invalid exercise command. Please provide a name, " +
                            "set, rep, and weight using '/n', '/s', '/r', '/w'.");
                }

                String name = exerciseArguments[1].trim();
                int set = Integer.parseInt(exerciseArguments[2].trim());
                int rep = Integer.parseInt(exerciseArguments[3].trim());
                int weight = Integer.parseInt(exerciseArguments[4].trim());
                Exercise exercise = new Exercise(set, rep, weight, name);

                dayExercises.add(exercise);
            }
            progRoutines.add(dayExercises);
        }
        return new CreateCommand(progName, progRoutines);
    }


    private Command createViewCommand(String argumentString) {
        int progIndex = parseTaskIndex(argumentString);
        return new ViewCommand(progIndex);
    }

    private Command createListCommand() {
        return new ListCommand();
    }



    private Command createStartCommand(String argumentString) {
        int progIndex = parseTaskIndex(argumentString);
        return new StartCommand(progIndex);
    }

    private Command createLogCommand(String argumentString){
        String[] arguments = parseArguments(argumentString, " /p", " /d", " DATE" );

        if (arguments.length != 3) {
            throw new IllegalArgumentException("Invalid event command. " +
                    "Please provide a programme index, day index, and date using '/p' and '/d' and 'DATE'.");
        }

        if (arguments[0].trim().isEmpty()) {
            throw new IllegalArgumentException("Programme index cannot be empty.");
        }
        int progIndex = parseTaskIndex(arguments[0].trim());

        if (arguments[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Day index cannot be empty.");
        }
        int dayIndex = parseTaskIndex(arguments[1].trim());

        if (arguments[2].trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty.");
        }
        String date = arguments[2].trim();

        return new LogCommand(progIndex, dayIndex, date);
    }

    //private Command createActiveCommand(String argumentString) {
    //int progIndex = parseTaskIndex(argumentString);
    //return new ActiveCommand(progIndex);
    //return new InvalidCommand();
    //}

    private Command createHistoryCommand() {
        return new HistoryCommand();
    }

    private Command createExitCommand() {
        return new ExitCommand();
    }

    private Command createInvalidCommand() {
        return new InvalidCommand();
    }

    private int parseTaskIndex(String taskIndex) {
        if (taskIndex.trim().isEmpty()) {
            throw new IllegalArgumentException("Task index cannot be empty.");
        }
        try {
            int index = Integer.parseInt(taskIndex.trim()) - 1;
            if (index < 0) {
                throw new IllegalArgumentException("Task index must be a positive number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task index. Please provide a valid number.");
        }
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }
}
