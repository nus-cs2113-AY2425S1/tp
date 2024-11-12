package seedu.parser;

import seedu.command.ViewCommand;
import seedu.command.HelpCommand;
import seedu.command.AddCommand;
import seedu.command.DeleteCommand;
import seedu.command.UpdateCommand;
import seedu.exceptions.InventraException;
import seedu.exceptions.InventraExcessArgsException;
import seedu.exceptions.InventraInvalidCommandException;
import seedu.model.Inventory;
import seedu.ui.Ui;
import seedu.storage.Csv;

/**
 * Parses and processes user commands.
 * Executes specific commands based on the input, such as add, delete, update, view, help, or exit.
 */
public class CommandParser {

    /**
     * Parses and executes the given input command.
     * Supports commands such as "add", "delete", "update", "view", "help", and "exit".
     *
     * @param input     the user input command
     * @param inventory the inventory instance for managing records
     * @param ui        the user interface instance for displaying messages
     * @param csv       the CSV instance for handling file storage operations
     */
    public static void parseCommand(String input, Inventory inventory, Ui ui, Csv csv) {
        // Normalize input by trimming
        input = input.trim().replaceAll("\\s+", " ");

        String[] parts = input.split("\\s+", 3);
        String command = parts[0];

        try {
            switch (command) {
            case "add":
                new AddCommand(inventory, ui, csv).execute(parts);
                break;
            case "delete":
                new DeleteCommand(inventory, ui, csv).execute(parts);
                break;
            case "update":
                new UpdateCommand(inventory, ui, csv).execute(parts);
                break;
            case "view":
                new ViewCommand(inventory, ui).execute(parts);
                break;
            case "help":
                new HelpCommand(ui).execute(parts);
                break;
            case "exit":
                if (parts.length > 1) {
                    throw new InventraExcessArgsException(1, parts.length);
                }
                ui.printMessage("Program exit successfully.");
                break;
            default:
                throw new InventraInvalidCommandException(command);
            }
        } catch (InventraException e) {
            System.out.println(e.getMessage());
        }
    }
}
