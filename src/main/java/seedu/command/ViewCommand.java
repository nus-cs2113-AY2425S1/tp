package seedu.command;

import seedu.exceptions.InventraException;
import seedu.exceptions.InventraExcessArgsException;
import seedu.exceptions.InventraMissingArgsException;
import seedu.exceptions.InventraOutOfBoundsException;
import seedu.exceptions.InventraInvalidNumberException;
import seedu.exceptions.InventraExcessInputException;

import seedu.model.Inventory;
import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Command to view inventory items based on various criteria.
 * Allows viewing all items, specific items by index, or filtered items by keyword.
 */
public class ViewCommand extends Command {

    /**
     * Constructs a ViewCommand with the specified inventory and user interface.
     *
     * @param inventory the inventory to operate on
     * @param ui        the user interface for displaying information
     */
    public ViewCommand(Inventory inventory, Ui ui) {
        super(inventory, ui, null);  // Passing `null` for unused dependencies
    }

    /**
     * Executes the view command based on the provided arguments.
     * Supports viewing all items, a specific item by ID, or filtering items by a keyword.
     *
     * @param args the command arguments for the view operation
     * @throws InventraException if there are missing arguments or invalid input
     */
    public void execute(String[] args) throws InventraException {
        if (args.length < 2) {
            throw new InventraMissingArgsException("Flag or Item index");
        }

        String flag = args[1].trim();
        switch (flag) {
        case "-f":
            handleViewByKeyword(args);
            break;

        case "-a":
            if (args.length > 2) {
                throw new InventraExcessArgsException(2, args.length);
            }
            ui.showFieldsAndRecords(inventory); // View all items
            break;

        default:
            if (args.length > 2) {
                throw new InventraExcessArgsException(2, args.length);
            }
            handleViewById(flag);
            break;
        }
    }

    /**
     * Handles viewing a specific item by its ID.
     *
     * @param input the ID of the item to view
     * @throws InventraException if the ID is invalid or out of bounds
     */
    private void handleViewById(String input) throws InventraException {
        if (input.trim().isEmpty()) {
            throw new InventraMissingArgsException("Item index");
        }

        try {
            int id = Integer.parseInt(input);

            // Validate the provided ID
            if (id <= 0 || id > inventory.getRecords().size()) {
                throw new InventraOutOfBoundsException(id, 1, inventory.getRecords().size());
            }

            // Extract and display the specific record (adjust 1-based index to 0-based)
            Map<String, String> record = inventory.getRecords().get(id - 1);
            ui.showSingleRecordWithOriginalId(inventory.getFields(), record, id);
        } catch (NumberFormatException e) {
            throw new InventraInvalidNumberException(input);
        }
    }

    /**
     * Handles viewing items that match a specified keyword.
     * Filters items by checking each string field for the keyword.
     *
     * @param args the command arguments containing the keyword
     * @throws InventraException if the keyword is missing or exceeds input length limit
     */
    private void handleViewByKeyword(String[] args) throws InventraException {
        if (args.length < 3 || args[2].trim().isEmpty()) {
            throw new InventraMissingArgsException("Keyword for filtering");
        }

        String keyword = String.join(" ",
                java.util.Arrays.copyOfRange(args, 2, args.length)).toLowerCase();

        if(keyword.length() > 20) {
            throw new InventraExcessInputException(20, args.length);
        }

        List<Map<String, String>> records = inventory.getRecords();
        List<Map<String, String>> matchingRecords = new ArrayList<>();

        for (Map<String, String> record : records) {
            for (String field : record.keySet()) {
                if (inventory.isStringField(field) && record.get(field).toLowerCase().contains(keyword)) {
                    matchingRecords.add(record);
                    break;
                }
            }
        }

        if (!matchingRecords.isEmpty()) {
            ui.printMessage("Here are the records that match the keyword:");
            ui.showFieldsAndRecords(inventory.getFields(), matchingRecords);
        } else {
            ui.printMessage("Sorry, there are no records that match the keyword.");
        }
    }
}
