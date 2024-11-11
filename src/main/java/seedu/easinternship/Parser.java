package seedu.easinternship;

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

/**
 * Interprets user input and converts it
 * into executable commands. It identifies the command type and parses any associated arguments or flags.
 */
public class Parser {
    private final Ui ui = new Ui();
    private final Logger logger = Logger.getLogger("EasInternship");

    private final Map<String, Supplier<Command>> commandConstructors = new HashMap<>();

    public Parser() {
        initializeCommands();
    }

    /**
     * Populates the {@code commandConstructors} map with command keywords and their constructors.
     */
    private void initializeCommands() {
        commandConstructors.put("add", AddCommand::new);
        commandConstructors.put("delete", DeleteCommand::new);
        commandConstructors.put("update", UpdateCommand::new);
        commandConstructors.put("sort", SortCommand::new);
        commandConstructors.put("filter", FilterCommand::new);
        commandConstructors.put("list", ListCommand::new);
        commandConstructors.put("help", HelpCommand::new);
        commandConstructors.put("remove", RemoveCommand::new);
        commandConstructors.put("favourite", FavouriteCommand::new);
        commandConstructors.put("calendar", CalendarCommand::new);
    }

    /**
     * Parses the user's input string to determine the command to execute.
     *
     * @param input the raw input string from the user; must not be blank.
     * @return the corresponding {@code Command} object to be executed, or {@code null} if the {@code input} is invalid.
     */
    public Command parseCommand(String input) {
        if (input.isBlank()) {
            ui.showOutput("Please enter a command");
            return null;
        }

        String[] inputArgs = input.trim().split(" ", 2);
        String inputCommand = inputArgs[0].trim();

        if (!commandConstructors.containsKey(inputCommand)) {
            ui.showUnknownCommand(inputCommand);
            logger.log(Level.WARNING, "Invalid Command: " + inputCommand);
            return null;
        }

        Supplier<Command> commandConstructor = commandConstructors.get(inputCommand);
        logger.log(Level.INFO, "Command Parsed: " + inputCommand);
        Command command = commandConstructor.get();
        return command;
    }

    /**
     * Parses the input data for the given command, extracting any arguments or flags.
     *
     * @param command the {@code Command} object for which to parse data
     * @param input   the raw input string from the user
     * @return an {@code ArrayList<String>} containing the parsed arguments, or {@code null} if the input is invalid.
     */
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

        if (command instanceof AddCommand || command instanceof FilterCommand || command instanceof SortCommand) {
            return parseFlagData(inputData);
        }
        if (command instanceof DeleteCommand) {
            return parseDeleteCommandData(inputData);
        }
        if (command instanceof UpdateCommand) {
            return parseUpdateCommandData(inputData);
        }
        if (command instanceof FavouriteCommand) {
            return parseFavouriteCommandData(inputData);
        }
        assert false : "Should never be able to reach this statement if all commands are accounted for";
        return null;
    }

    /**
     * Parses input data containing flags and their corresponding values.
     *
     * @param inputData the input data string containing flags
     * @return an {@code ArrayList<String>} of parsed flags and their corresponding data, or {@code null} if there is an
     *     empty flag
     */
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

    /**
     * Parses input data for the delete command, extracting the internship ID.
     *
     * @param inputData the input data string containing the internship ID
     * @return an {@code ArrayList<String>} containing a single element - the internship ID.
     */
    private ArrayList<String> parseDeleteCommandData(String inputData) {
        ArrayList<String> commandArgs = new ArrayList<>();
        commandArgs.add(inputData.trim());
        return commandArgs;
    }

    /**
     * Parses input data for the update command, extracting the internship ID and flags.
     *
     * @param inputData the input data string containing the internship ID and flags
     * @return an {@code ArrayList<String>} containing the internship ID followed by parsed flags and their
     *     corresponding data. or {@code null} if there are no/empty flags.
     */
    private ArrayList<String> parseUpdateCommandData(String inputData) {
        String[] splitArray = inputData.trim().split(" ", 2);
        String id = splitArray[0].trim();
        if (splitArray.length < 2) {
            ui.showEmptyFlags();
            return null;
        }
        String fields = splitArray[1].trim();
        ArrayList<String> commandArgs = parseFlagData(fields);
        if (commandArgs == null) {
            return null;
        }
        commandArgs.add(0, id);
        return commandArgs;
    }

    /**
     * Parses input data for the favourite command, extracting the list of internship IDs.
     *
     * @param inputData the input data string containing internship IDs separated by commas; must not be empty.
     * @return an {@code ArrayList<String>} containing the internship IDs, or {@code null} if the {@code inputData}
     *     is empty.
     */
    private ArrayList<String> parseFavouriteCommandData(String inputData) {
        if (inputData.trim().isEmpty()) {
            return null;
        }
        ArrayList<String> commandArgs = new ArrayList<>(Arrays.asList(inputData.trim().split(",")));
        commandArgs.replaceAll(String::trim);
        return commandArgs;
    }

}
