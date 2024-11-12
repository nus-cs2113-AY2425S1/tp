package seedu.command;

import seedu.exceptions.InventraException;
import seedu.exceptions.InventraExcessArgsException;
import seedu.exceptions.InventraInvalidFlagException;
import seedu.exceptions.InventraInvalidHeaderException;
import seedu.exceptions.InventraInvalidNumberException;
import seedu.exceptions.InventraInvalidTypeException;
import seedu.exceptions.InventraLessArgsException;
import seedu.exceptions.InventraMissingArgsException;
import seedu.exceptions.InventraOutOfBoundsException;
import seedu.exceptions.InventraNegativeValueException;
import seedu.model.Inventory;
import seedu.storage.Csv;
import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UpdateCommand is a command class responsible for updating the inventory records or field headers
 * in the system. It supports updating specific records or renaming fields within the inventory.
 *
 * Usage:
 * - update -d id, fieldname, newvalue: updates a specific field of a record.
 * - update -h old, new: renames an existing field (header).
 */
public class UpdateCommand extends Command {

    private static final String VALID_NAME_REGEX = "^[a-zA-Z0-9_\\- .;:'\"&()<!>{}%@*$#=~]+$";

    /**
     * Constructs an UpdateCommand with the given inventory, UI, and CSV handler.
     *
     * @param inventory the inventory to be updated
     * @param ui        the UI handler for displaying messages
     * @param csv       the CSV handler for updating the storage file
     */
    public UpdateCommand(Inventory inventory, Ui ui, Csv csv) {
        super(inventory, ui, csv);
    }

    /**
     * Executes the update command based on the specified arguments.
     *
     * @param args the command arguments
     * @throws InventraException if there are errors in command format or validation
     */
    public void execute(String[] args) throws InventraException {
        if (args.length < 2) {
            throw new InventraMissingArgsException("Flag or update details");
        }
        String flag = args[1];
        switch (flag) {
        case "-d":
            if (args.length < 3) {
                throw new InventraMissingArgsException("update " +
                        "-d <id>, <fieldname>, <newvalue>");
            }
            handleUpdateRecord(args[2]);
            csv.updateCsv(inventory);
            break;
        case "-h":
            if (args.length < 3) {
                throw new InventraMissingArgsException("update -h <old>, <new>");
            }
            handleUpdateField(args[2]);
            csv.updateCsvHeaders(inventory);
            break;
        default:
            throw new InventraInvalidFlagException("Use 'update -d <id>, " +
                    "<field_name>, <new value>' or 'update -h <old header name> <new header name>'");
        }
    }

    /**
     * Handles the updating of a field (header) within the inventory.
     *
     * @param fieldData the field data in the format "old_field, new_field"
     * @throws InventraException if there are errors in field validation or name matching
     */
    private void handleUpdateField(String fieldData) throws InventraException {
        if (!fieldData.contains(",")) {
            throw new InventraMissingArgsException(
                    "Expected format: <old_field>, <new_field>.\n Ensure you separate the old and " +
                            "new field names with a comma."
            );
        }

        String[] fields = fieldData.split(",\\s*");

        if (fields.length < 2) {
            throw new InventraLessArgsException(2, fields.length);
        } else if (fields.length > 2) {
            throw new InventraExcessArgsException(2, fields.length);
        }

        String oldFieldName = fields[0].trim();
        String newFieldName = fields[1].trim();

        if (!newFieldName.matches(VALID_NAME_REGEX)) {
            throw new InventraInvalidTypeException("New field name", newFieldName,
                    "alphanumeric or limited symbols _ - .;:'\"&()<!>{}%@*$#=~$");
        }
        if (oldFieldName.isEmpty() || newFieldName.isEmpty()) {
            throw new InventraInvalidTypeException("Field names", "cannot be empty", "provide valid field names");
        }

        // Allow update if new field name is valid, even if old field name exceeds limit
        if (newFieldName.length() > 20) {
            throw new InventraInvalidTypeException("Field name length",
                    "exceeds 20 characters", "use shorter names");
        }

        // Bypass validation for oldFieldName to allow corrective action
        if (!inventory.getFields().contains(oldFieldName)) {
            throw new InventraInvalidHeaderException(oldFieldName);
        }

        List<String> updatedFields = updateFields(oldFieldName, newFieldName);
        Map<String, String> updatedFieldTypes = updateFieldTypes(oldFieldName, newFieldName);
        List<Map<String, String>> updatedRecordsForHeaderChange =
                updateRecordsForHeaderChange(oldFieldName, newFieldName);

        inventory.setFields(updatedFields);
        inventory.setFieldTypes(updatedFieldTypes);
        inventory.setRecords(updatedRecordsForHeaderChange);
    }

    /**
     * Updates the fields list, replacing the old field name with the new field name.
     *
     * @param oldFieldName the old field name
     * @param newFieldName the new field name
     * @return a list of updated fields
     */
    private List<String> updateFields(String oldFieldName, String newFieldName) {
        List<String> updatedFields = new ArrayList<>();
        List<String> oldFields = inventory.getFields();

        for (String field : oldFields) {
            if (field.equals(oldFieldName)) {
                updatedFields.add(newFieldName);
            } else {
                updatedFields.add(field);
            }
        }

        return updatedFields;
    }

    /**
     * Updates the field types, replacing the old field name with the new field name.
     *
     * @param oldFieldName the old field name
     * @param newFieldName the new field name
     * @return a map of updated field types
     */
    private Map<String, String> updateFieldTypes(String oldFieldName, String newFieldName) {
        Map<String, String> updatedFieldTypes = new HashMap<>();
        Map<String, String> oldFieldTypes = inventory.getFieldTypes();

        for (Map.Entry<String, String> entry : oldFieldTypes.entrySet()) {
            if (entry.getKey().equals(oldFieldName)) {
                updatedFieldTypes.put(newFieldName, entry.getValue());
            } else {
                updatedFieldTypes.put(entry.getKey(), entry.getValue());
            }
        }

        return updatedFieldTypes;
    }

    /**
     * Updates the records, replacing the old field name with the new field name.
     *
     * @param oldFieldName the old field name
     * @param newFieldName the new field name
     * @return a list of updated records
     */
    private List<Map<String, String>> updateRecordsForHeaderChange(String oldFieldName, String newFieldName) {
        List<Map<String, String>> oldRecords = this.inventory.getRecords();
        List<Map<String, String>> updatedRecords = new ArrayList<>();

        for (int l = 0; l < oldRecords.size(); l++) {
            Map<String, String> newRecordMap = new HashMap<>();
            Map<String, String> oldRecordMap = oldRecords.get(l);
            for (Map.Entry<String, String> entry : oldRecordMap.entrySet()) {
                if (oldFieldName.equals(entry.getKey())) {
                    newRecordMap.put(newFieldName, entry.getValue());
                } else {
                    newRecordMap.put(entry.getKey(), entry.getValue());
                }
            }

            updatedRecords.add(newRecordMap);
        }
        return updatedRecords;
    }

    private boolean isFieldValid(String oldFieldName) {
        List<String> fields = inventory.getFields();
        return fields.contains(oldFieldName);
    }

    /**
     * Handles updating a specific field of a record based on the provided record ID and new value.
     *
     * @param enteredString the entered record ID, field name, and new value in the format "id, fieldname, newvalue"
     * @throws InventraException if validation fails for the field or value
     */
    private void handleUpdateRecord(String enteredString) throws InventraException {
        String[] userInputs = enteredString.split(",\\s*");

        if (userInputs.length != 3) {
            if (userInputs.length < 3) {
                throw new InventraLessArgsException(3, userInputs.length);  // Use InventraLessArgsException
            } else {
                throw new InventraExcessArgsException(3, userInputs.length);
            }
        }

        String indexNumberString = userInputs[0].trim();
        String fieldName = userInputs[1].trim();
        String newValue = userInputs[2].trim();

        if (!newValue.matches(VALID_NAME_REGEX) ) {
            throw new InventraInvalidTypeException("New Value", newValue, "alphanumeric or " +
                    "limited symbols _ - .;:'\"&()<!>{}%@*$#=~$");
        }

        if (indexNumberString.isEmpty() || fieldName.isEmpty() || newValue.isEmpty()) {
            throw new InventraInvalidTypeException("Inputs",
                    "cannot be empty or just spaces", "provide valid inputs");
        }

        int indexNumber = parseIndex(indexNumberString);

        if (indexNumber <= 0 || indexNumber > inventory.getRecords().size()) {
            throw new InventraOutOfBoundsException(indexNumber, 1, inventory.getRecords().size());
        }

        if (!isFieldValid(fieldName)) {
            throw new InventraInvalidHeaderException(fieldName);
        }

        String type = inventory.getFieldTypes().get(fieldName);
        String validationMessage = validateValue(newValue, type, fieldName);
        if (validationMessage != null) {
            ui.showValidationError(validationMessage);
        }

        List<Map<String, String>> updatedRecords = updateRecords(indexNumber, fieldName, newValue);

        this.inventory.setRecords(updatedRecords);
    }

    private List<Map<String, String>> updateRecords(
            int indexNumber,
            String fieldName,
            String newValue
    ) throws InventraOutOfBoundsException {
        List<Map<String, String>> oldRecords = this.inventory.getRecords();
        List<Map<String, String>> updatedRecords = new ArrayList<>();

        for (int l = 0; l < oldRecords.size(); l++) {
            Map<String, String> newRecordMap = new HashMap<>();
            Map<String, String> oldRecordMap = oldRecords.get(l);

            if (l == (indexNumber - 1)) { //adjusting for user input index and stored index
                for (Map.Entry<String, String> entry : oldRecordMap.entrySet()) {
                    if (fieldName.equals(entry.getKey())) {
                        newRecordMap.put(entry.getKey(), newValue);
                    } else {
                        newRecordMap.put(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                newRecordMap.putAll(oldRecordMap);
            }

            updatedRecords.add(newRecordMap);
        }
        return updatedRecords;
    }

    /**
     * Validates the value according to its expected data type.
     *
     * @param value the value to validate
     * @param type  the expected type of the value (e.g., string, integer, float, date)
     * @param field the field name associated with the value
     * @return null if valid, otherwise an error message
     * @throws InventraException if validation fails
     */
    public String validateValue(String value, String type, String field) throws InventraException {
        assert value != null && !value.isEmpty() : "Value should not be null or empty";
        assert type != null && !type.isEmpty() : "Field type should not be null or empty";
        assert field != null && !field.isEmpty() : "Field name should not be null or empty";

        switch (type) {
        case "s": // String
            if (value.matches("\\d+")) {
                throw new InventraInvalidTypeException(field, value, "non-numeric string");
            }
            return null; // Any string is valid

        case "i": // Integer
            try {
                if (value.length() > 9) { // Restrict integer length to 9 digits
                    throw new InventraInvalidTypeException(
                            String.format("Error: Invalid type for field " +
                                            "'%s'%nExpected value of type 'integer (up to 9 digits)', got: '%s'",
                                    field, value)
                    );
                }
                int intValue = Integer.parseInt(value); // Validates actual integer
                if (intValue < 0) {
                    throw new InventraNegativeValueException(field, value);
                }
                return null; // Valid integer
            } catch (NumberFormatException e) {
                throw new InventraInvalidTypeException(field, value, "integer");
            }

        case "f": // Float
            try {
                float floatValue = Float.parseFloat(value);
                if (floatValue < 0) {
                    throw new InventraNegativeValueException(field, value);
                }
                return null; // Valid float
            } catch (NumberFormatException e) {
                throw new InventraInvalidTypeException(field, value, "float");
            }

        case "d": // Date
            if (!value.matches("\\d{2}/\\d{2}/\\d{4}") && !value.matches("\\d{2}/\\d{2}/\\d{2}")) {
                throw new InventraInvalidTypeException(
                        field, value, "date (expected format: DD/MM/YYYY or DD/MM/YY)"
                );
            }
            String[] parts = value.split("/");
            try {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);
                if (day <= 0 || day > 31 || month <= 0 || month > 12 || year < 0) {
                    throw new InventraInvalidTypeException(
                            field, value, "valid date in DD/MM/YYYY or DD/MM/YY format"
                    );
                }
                return null; // Valid date
            } catch (NumberFormatException e) {
                throw new InventraInvalidTypeException(
                        field, value, "valid date (DD/MM/YYYY or DD/MM/YY)"
                );
            }

        case "n": // Null
            if (!value.equalsIgnoreCase("null")) {
                throw new InventraInvalidTypeException(field, value, "null");
            }
            return null; // Valid null

        default:
            return ui.getUnknownTypeMessage(field);
        }
    }

    /**
     * Parses a string as an integer index, ensuring it is a valid integer.
     *
     * @param indexString the string representing an index
     * @return the parsed integer index
     * @throws InventraInvalidNumberException if the index string is not a valid integer
     */
    private int parseIndex(String indexString) throws InventraInvalidNumberException {
        try {
            return Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new InventraInvalidNumberException(indexString);
        }
    }
}
