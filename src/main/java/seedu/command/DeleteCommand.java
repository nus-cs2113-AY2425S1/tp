package seedu.command;

import seedu.exceptions.InventraException;
import seedu.exceptions.InventraInvalidFlagException;
import seedu.exceptions.InventraInvalidNumberException;
import seedu.exceptions.InventraMissingArgsException;
import seedu.exceptions.InventraOutOfBoundsException;
import seedu.exceptions.InventraRangeOutOfBoundsException;
import seedu.model.Inventory;
import seedu.ui.Ui;
import seedu.storage.Csv;

import java.util.List;
import java.util.Map;

/**
 * Represents a command to delete records or fields in the inventory.
 * Supports deletion of a single record, a range of records, all records, or entire fields.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a {@code DeleteCommand} with the specified inventory, UI, and CSV handler.
     *
     * @param inventory the inventory to be modified by the delete command.
     * @param ui        the UI handler for displaying deletion messages.
     * @param csv       the CSV handler to update after deletions.
     */
    public DeleteCommand(Inventory inventory, Ui ui, Csv csv) {
        super(inventory, ui, csv);
    }

    /**
     * Executes the delete command based on the provided arguments.
     *
     * Supported flags:
     * <ul>
     *     <li>No flag: Deletes a single record by index.</li>
     *     <li>-e: Deletes the entire table, including all records and fields.</li>
     *     <li>-a: Deletes all records while retaining fields.</li>
     *     <li>-h: Deletes a specific field and its column data across all records.</li>
     *     <li>-r: Deletes a range of records specified by start and end indexes.</li>
     * </ul>
     *
     * @param args the arguments containing the delete command and its options.
     * @throws InventraException if there are missing arguments, invalid flags, or index out of bounds.
     */
    public void execute(String[] args) throws InventraException {
        if (args.length < 2) {
            throw new InventraMissingArgsException("Record Number");
        }

        String part = args[1].trim();
        if (part.isEmpty()) {
            throw new InventraMissingArgsException("Record Number");
        }

        if (part.contains("-") && !part.startsWith("-")) {
            // If the input contains a range without the -r flag
            throw new InventraInvalidFlagException(
                    "Invalid usage of range. Use 'delete -r <start>-<end>'" +
                            " for deleting a range of records."
            );
        }

        if (!part.startsWith("-")) {
            int index = parseIndex(part);
            deleteSingleRecord(index);
            ui.printMessage("Record deleted successfully.");
        } else {
            switch (part) {
            case "-e":
                deleteEntireTable();
                ui.printMessage("Deleted entire table.");
                break;
            case "-a":
                deleteAllRecords();
                ui.printMessage("Deleted all records.");
                break;
            case "-h":
                if (args.length < 3 || args[2].trim().isEmpty()) {
                    throw new InventraMissingArgsException("Field name");
                }
                deleteHeaderAndColumn(args[2].trim());
                ui.printMessage("Deleted header and it's column.");
                break;
            case "-r":
                if (args.length < 3 || args[2].trim().isEmpty()) {
                    throw new InventraMissingArgsException("Range");
                }
                String[] numbers = args[2].trim().split("-");
                if (numbers.length != 2) {
                    throw new InventraInvalidFlagException("Invalid range format. Expected format: <start>-<end>");
                }
                deleteRangeRecords(parseIndex(numbers[0]), parseIndex(numbers[1]));
                break;
            default:
                throw new InventraInvalidFlagException("use 'help delete' to receive details about all " +
                        "available flags and their functions");
            }
        }

    }

    /**
     * Deletes the entire table, including all fields and records.
     * Clears the inventory fields, field types, and records, and updates the CSV to reflect the changes.
     */
    private void deleteEntireTable() {
        inventory.getFields().clear();
        inventory.getFieldTypes().clear();
        deleteAllRecords();
        csv.updateCsvAfterDeletion(inventory); // Update the CSV file to reflect the empty table
    }

    /**
     * Checks if the specified index is within bounds for the given size.
     *
     * @param index the index to check.
     * @param size  the size of the list or collection.
     * @return true if the index is within bounds; false otherwise.
     */
    private boolean isWithinBounds(int index, int size) {
        return (index > 0 && index <= size);
    }

    /**
     * Deletes a range of records from the inventory based on start and end indexes.
     * Updates the CSV to reflect the deletion.
     *
     * @param start the starting index (inclusive).
     * @param end   the ending index (inclusive).
     * @throws InventraRangeOutOfBoundsException if the start or end index is out of bounds.
     */
    private void deleteRangeRecords(int start, int end) throws InventraRangeOutOfBoundsException {
        List<Map<String, String>> records = inventory.getRecords();
        if (!isWithinBounds(start, records.size()) || !isWithinBounds(end, records.size())) {
            throw new InventraRangeOutOfBoundsException(start, end, 1, records.size());
        }
        if (end < start) {
            throw new InventraRangeOutOfBoundsException(start, end, 1, records.size());
        }
        inventory.getRecords().subList(start - 1, end).clear();
        csv.updateCsvAfterDeletion(inventory);
    }

    /**
     * Deletes all records in the inventory while retaining the field structure.
     * Updates the CSV to reflect the deletion.
     */
    private void deleteAllRecords() {
        inventory.getRecords().clear();
        csv.updateCsvAfterDeletion(inventory); // Update the CSV file to reflect the empty records
    }

    /**
     * Deletes a specific field and its associated column data across all records in the inventory.
     *
     * @param fieldName the name of the field to delete.
     * @throws InventraInvalidFlagException if the field name does not exist in the inventory.
     */
    private void deleteHeaderAndColumn(String fieldName) throws InventraInvalidFlagException {
        if (!inventory.getFields().contains(fieldName)) {
            throw new InventraInvalidFlagException("Header '"
                    + fieldName + "' does not exist.");
        }
        inventory.getFields().remove(fieldName);
        inventory.getFieldTypes().remove(fieldName);

        for (Map<String, String> record : inventory.getRecords()) {
            record.remove(fieldName);
        }
        csv.updateCsvAfterDeletion(inventory);
    }

    /**
     * Parses a string to obtain an integer index for record deletion.
     *
     * @param indexString the string to parse.
     * @return the parsed index.
     * @throws InventraInvalidNumberException if the string is not a valid integer.
     * @throws InventraInvalidFlagException   if the string contains invalid characters.
     */
    private int parseIndex(String indexString) throws InventraInvalidNumberException, InventraInvalidFlagException {
        if (indexString.contains(",") || indexString.contains("-")) {
            throw new InventraInvalidFlagException("Invalid flag or range format: "
                    + indexString);
        }
        try {
            return Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new InventraInvalidNumberException(indexString);
        }
    }

    /**
     * Deletes a single record at the specified index in the inventory.
     * Updates the CSV to reflect the deletion.
     *
     * @param recordIndex the index of the record to delete (1-based indexing).
     * @throws InventraOutOfBoundsException if the index is out of bounds.
     */
    private void deleteSingleRecord(int recordIndex) throws InventraOutOfBoundsException {
        List<Map<String, String>> records = inventory.getRecords();

        if (isWithinBounds(recordIndex, records.size())) {
            records.remove(recordIndex - 1); // Convert to zero based indexing
            csv.updateCsvAfterDeletion(inventory);
        } else {
            throw new InventraOutOfBoundsException(recordIndex, 1, records.size());
        }
    }
}
