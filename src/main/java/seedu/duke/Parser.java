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
import seedu.commands.FavouriteCommand;
import seedu.commands.CalendarCommand;

import seedu.ui.Ui;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
    private static final Ui ui = new Ui();
    private static final Logger logger = Logger.getLogger("EasInternship");

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
        commands.put("favourite", FavouriteCommand::new);
        commands.put("calendar", CalendarCommand::new);
    }

    public Command parseCommand(String input) {
        if (input.isBlank()) {
            ui.showOutput("Please enter a command");
            return null;
        }

        String[] inputArgs = input.trim().split(" ", 2);

        assert inputArgs[0].equals(inputArgs[0].trim());
        String inputCommand = inputArgs[0].trim();

        if (!commands.containsKey(inputCommand)) {
            ui.showUnknownCommand(inputCommand);
            logger.log(Level.WARNING, "Invalid Command: " + inputCommand);
            return null;
        }

        Supplier<Command> commandSupplier = commands.get(inputCommand);
        logger.log(Level.INFO, "Command Parsed: " + inputCommand);
        return commandSupplier.get();
    }

    public ArrayList<String> parseData(Command command, String input) {
        if (command instanceof ListCommand || command instanceof HelpCommand || command instanceof CalendarCommand) {
            return new ArrayList<>();
        }

        String[] inputArgs = input.trim().split(" ", 2);

        if (inputArgs.length < 2) {
            if (!(command instanceof SortCommand)) {
                ui.showOutput("Please input some ID or flag following the command");
                logger.log(Level.WARNING, "Invalid Command: " + input);
                return null;
            }
            return new ArrayList<>();
        }

        String inputData = inputArgs[1];

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
        if (command instanceof FavouriteCommand) {
            return parseFavouriteCommandData(inputData);
        }
        assert false : "Should never be able to reach this statement if all commands are accounted for";
        return null;
    }

    private ArrayList<String> parseFlagData(String inputData) {
        ArrayList<String> commandArgs = new ArrayList<>(Arrays.asList(inputData.trim().split("-")));
        if (commandArgs.isEmpty()) {
            ui.showOutput("Empty flag detected\n" + "Please input a flag following the '-' symbol");
            return null;
        }
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
        commandArgs.set(0, inputData.trim());
        return commandArgs;
    }

    private ArrayList<String> parseUpdateCommandData(String inputData) {
        String[] splitArray = inputData.trim().split(" ", 2);
        assert splitArray[0].equals(splitArray[0].trim());
        String id = splitArray[0].trim();
        try {
            String fields = splitArray[1].trim();

            ArrayList<String> commandArgs = parseFlagData(fields);
            if (commandArgs == null) {
                return null;
            }

            commandArgs.add(0, id);
            return commandArgs;
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showEmptyFlags();
            return null;
        }
    }

    private ArrayList<String> parseSortCommandData(String inputData) {
        return parseFlagData(inputData);
    }

    private ArrayList<String> parseFilterCommandData(String inputData) {
        return parseFlagData(inputData);
    }

    private ArrayList<String> parseFavouriteCommandData(String inputData) {
        if (inputData.trim().isEmpty()) {
            return null;
        }
        ArrayList<String> commandArgs = new ArrayList<>(Arrays.asList(inputData.trim().split(",")));
        commandArgs.replaceAll(String::trim);
        return commandArgs;
    }

}
