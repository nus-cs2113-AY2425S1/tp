package seedu.duke;

import seedu.commands.Command;
import seedu.commands.AddCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.UpdateCommand;
import seedu.commands.SortCommand;
import seedu.commands.FilterCommand;
import seedu.commands.ListCommand;
import seedu.commands.HelpCommand;
import seedu.commands.RemoveCommand;

import seedu.ui.Ui;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.Supplier;

public class Parser {
    private static final Ui ui = new Ui();
    private final Map<String, Supplier<Command>> commands = new HashMap<>();

    public Parser() {
        // Initialize command map
        initializeCommands();
    }

    // Initialize map with suppliers
    private void initializeCommands() {
        commands.put("add", AddCommand::new);
        commands.put("delete", DeleteCommand::new);
        commands.put("update", UpdateCommand::new);
        commands.put("sort", SortCommand::new);
        commands.put("filter", FilterCommand::new);
        commands.put("list", ListCommand::new);
        commands.put("help", HelpCommand::new);
        commands.put("remove", RemoveCommand::new);
    }

    public Command parseCommand(String input) {
        String inputCommand = input.split(" ")[0];
        if (commands.containsKey(inputCommand)) {
            Supplier<Command> commandSupplier = commands.get(inputCommand);
            return commandSupplier.get();
        }
        return null;
    }

    public ArrayList<String> parseData(Command command, String input) {
        if (command instanceof ListCommand || command instanceof HelpCommand) {
            return new ArrayList<>();
        }

        try {
            String inputData = input.trim().split(" ", 2)[1];
            if (command instanceof AddCommand) {
                return parseAddCommandData(inputData);
            }
            if (command instanceof DeleteCommand) {
                return parseDeleteCommandData(inputData);
            }
            if (command instanceof UpdateCommand) {
                return parseUpdateCommandData(inputData);
            }
            if (command instanceof SortCommand) {
                return parseSortCommandData(inputData);
            }
            if (command instanceof FilterCommand) {
                return parseFilterCommandData(inputData);
            }
            if (command instanceof RemoveCommand) {
                return parseUpdateCommandData(inputData);
            }
            throw new IllegalArgumentException("Unknown command type");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showOutput("Please input some ID or flag following the command");
            return null;
        }
    }

    private ArrayList<String> parseFlagData(String inputData) {
        ArrayList<String> commandArgs = new ArrayList<>(Arrays.asList(inputData.split("-")));
        commandArgs.remove(0);
        commandArgs.replaceAll(String::trim);
        return commandArgs;
    }

    private ArrayList<String> parseAddCommandData(String inputData) {
        return parseFlagData(inputData);
    }

    private ArrayList<String> parseDeleteCommandData(String inputData) {
        ArrayList<String> commandArgs = new ArrayList<>();
        commandArgs.add(inputData);
        commandArgs.set(0, commandArgs.get(0).trim());
        return commandArgs;
    }

    private ArrayList<String> parseUpdateCommandData(String inputData) {
        String[] splitArray = inputData.split(" ", 2);
        try {
            String id = splitArray[0];
            Integer.parseInt(id);
            String fields = splitArray[1];
            if (fields.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            ArrayList<String> commandArgs = parseFlagData(fields);
            commandArgs.add(0, id);
            return commandArgs;
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showEmptyFlags();
            return null;
        } catch (NumberFormatException e) {
            ui.showOutput("Please input some ID for the command");
            return null;
        }
    }

    private ArrayList<String> parseSortCommandData(String inputData) {
        return parseFlagData(inputData);
    }

    private ArrayList<String> parseFilterCommandData(String inputData) {
        return parseFlagData(inputData);
    }

}
