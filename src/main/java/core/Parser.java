package core;

import command.Command;
import command.ExitCommand;
import command.HistoryCommand;
import command.LogCommand;
import command.InvalidCommand;
import command.programme.CreateCommand;
import command.programme.ListCommand;
import command.programme.StartCommand;
import command.programme.edit.EditCommand;
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
        case EditCommand.COMMAND_WORD: return createEditCommand(arguments);
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
                    throw new IllegalArgumentException("Invalid exercise command. Please provide a name, " +
                            "set, rep, and weight using '/n', '/s', '/r', '/w'.");
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
        int progIndex = parseTaskIndex(argumentString);
        return new ViewCommand(progIndex);
    }

    private Command createListCommand() {
        return new ListCommand();
    }

    private Command createEditCommand(String argumentString) {
        EditCommand editCommand = new EditCommand();
        String[] programmes = argumentString.trim().split("/p");
        for (String programme : programmes) {
            if (programme.trim().isEmpty()) {
                continue;
            }

            String[] progParts = programme.trim().split(" ",2);
            int progIndex = parseTaskIndex(progParts[0]);
            String[] days = progParts[1].split("/d");

            for (String day : days ) {
                if (day.trim().isEmpty()) {
                    continue;
                }

                String[] dayParts = day.trim().split(" ", 2);
                int dayIndex = parseTaskIndex(dayParts[0]);
                String[] commandArray = dayParts[1].split("/");
                int updatingExercise = -1;
                for (String command : commandArray){
                    if (command.trim().isEmpty()) {
                        continue;
                    }

                    String[] commandParts = command.trim().split(" ",2);
                    String commandFlag = commandParts[0].trim();
                    String commandArgs = commandParts[1].trim();

                    switch(commandFlag){
                    case "u":
                        updatingExercise = parseTaskIndex(commandArgs);
                        break;
                    case "w":
                        System.out.printf("Prog: %d Day: %d Exercise: %d Set Weight: %s %n",
                                progIndex,dayIndex,updatingExercise,commandArgs);
                        break;
                    case "s":
                        System.out.printf("Prog: %d Day: %d Exercise: %d Set Sets: %s %n",
                                progIndex,dayIndex,updatingExercise,commandArgs);
                        break;
                    case "r":
                        System.out.printf("Prog: %d Day: %d Exercise: %d Set Reps: %s %n",
                                progIndex,dayIndex,updatingExercise,commandArgs);
                        break;
                    case "x":
                        updatingExercise = -1;
                        System.out.printf("Prog : %d Day: %d Exercise: %s Deleting %n",
                                progIndex,dayIndex,commandArgs);
                        break;
                    case "a":
                        updatingExercise = -1;
                        System.out.printf("Prog : %d Day: %d Creating Exercise %s %n",
                                progIndex, dayIndex, commandArgs);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid programme edit command.");
                    }
                }

            }
        }
        return new InvalidCommand();
    }


    private Command createStartCommand(String argumentString) {
        int progIndex = parseTaskIndex(argumentString);
        return new StartCommand(progIndex);
    }

    private Command createLogCommand(String argumentString){
        String[] arguments = parseArguments(argumentString, "/p", "/d", " DATE" );

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
        String delimiterPattern = String.join(" | ", delimiters);
        return argumentString.split(delimiterPattern);
    }
}
