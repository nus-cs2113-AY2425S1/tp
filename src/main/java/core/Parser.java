package core;

import command.*;
import command.programme.*;

import java.util.ArrayList;


public class Parser {

    public Command parse(String fullCommand) {
        if (fullCommand == null || fullCommand.trim().isEmpty()) {
            // throw exception
            System.out.println("Command cannot be empty. Please enter a valid command.");
        }

        String[] inputArguments = fullCommand.split(" ", 3);
        String commandString = "";
        String argumentString = "";

        if (inputArguments.length > 2) {
            commandString = inputArguments[1];
            argumentString = inputArguments[2];
        }

        System.out.println(commandString);

        return switch (commandString) {
            case CreateCommand.COMMAND_WORD -> createCreateProgCommand(argumentString);
            case ViewCommand.COMMAND_WORD -> createViewCommand(argumentString);
            case ListCommand.COMMAND_WORD -> createListCommand();
            //case EditCommand.COMMAND_WORD -> createEditCommand(argumentString);
            case StartCommand.COMMAND_WORD -> createStartCommand(argumentString);
            case LogCommand.COMMAND_WORD -> createLogCommand(argumentString);
            //case ActiveCommand.COMMAND_WORD -> createActiveCommand(argumentString);
            case HistoryCommand.COMMAND_WORD -> createHistoryCommand();
            case ExitCommand.COMMAND_WORD -> createExitCommand();
            default -> createInvalidCommand();
        };
    }

    private Command createCreateProgCommand(String argumentString) {
        ArrayList<ArrayList<ArrayList<String>>> ProgRoutines = new ArrayList<>();

        String[] days = argumentString.split("/d");
        String progName = days[0].trim();

        for (String day : days) {
            String dayString = day.trim();
            int dayIndex = 0;

            String[] exercises = dayString.split("/e");
            for (String exerciseDescription : exercises) {
                int exerciseIndex = 0;
                String exerciseString = exerciseDescription.trim();

                String[] exerciseArguments = parseArguments(exerciseString, " /n", " /s", " /r", " /w");

                if (exerciseArguments.length != 4) {
                    throw new IllegalArgumentException("Invalid event command. " +
                            "Please provide a exercise name, set, rep and weight using '/n', '/s', '/r', '/w'.");
                }

                if (exerciseArguments[0].trim().isEmpty()) {
                    throw new IllegalArgumentException("Exercise name cannot be empty.");
                }
                String name = exerciseArguments[0].trim();

                if (exerciseArguments[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("Set cannot be empty.");
                }
                String set = exerciseArguments[1].trim();

                if (exerciseArguments[2].trim().isEmpty()) {
                    throw new IllegalArgumentException("Rep cannot be empty.");
                }
                String rep = exerciseArguments[2].trim();

                if (exerciseArguments[3].trim().isEmpty()) {
                    throw new IllegalArgumentException("Weight cannot be empty.");
                }
                String weight = exerciseArguments[3].trim();

                ProgRoutines.get(dayIndex).get(exerciseIndex).add(name);
                ProgRoutines.get(dayIndex).get(exerciseIndex).add(set);
                ProgRoutines.get(dayIndex).get(exerciseIndex).add(rep);
                ProgRoutines.get(dayIndex).get(exerciseIndex).add(weight);
                exerciseIndex++;
            }
            dayIndex++;
        }
        return new CreateCommand(progName, ProgRoutines);
    }

    private Command createViewCommand(String argumentString) {
        int progIndex = parseTaskIndex(argumentString);
        return new ViewCommand(progIndex);
    }

    private Command createListCommand() {
        return new ListCommand();
    }

    private Command createEditCommand(String argumentString) {
        return new InvalidCommand();
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

    private Command createActiveCommand(String argumentString) {
        int progIndex = parseTaskIndex(argumentString);
        //return new ActiveCommand(progIndex);
        return new InvalidCommand();
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