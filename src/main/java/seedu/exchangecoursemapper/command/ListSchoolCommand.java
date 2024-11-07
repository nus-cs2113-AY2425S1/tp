package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.ui.UI;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListSchoolCommand extends CheckInformationCommand {
    private static final Logger logger = Logger.getLogger(ListSchoolCommand.class.getName());
    private static UI ui;

    /**
     * Class Constructor
     */
    public ListSchoolCommand() {
        ui = new UI();
    }

    /**
     * Executes the command to list schools by fetching them from the JSON file.
     * Adds additional logging for debugging and error handling.
     *
     * @param userInput A string containing the user's input.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        JsonObject jsonObject;
        try {
            jsonObject = fetchSchoolData();
            validateJsonObject(jsonObject);
            displaySchoolList(jsonObject);
        } catch (IOException e) {
            logger.log(Level.WARNING, Logs.FAILURE_READ_JSON_FILE);
            System.out.println(Exception.fileReadError());
            return;
        }
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }

    /**
     * Returns the JSON object containing school data.
     *
     * @return A JsonObject representing the school data.
     * @throws IOException If there is an issue reading the JSON file.
     */
    private JsonObject fetchSchoolData() throws IOException {
        logger.log(Level.INFO, Logs.READING_JSON_FILE);
        JsonObject jsonObject = super.createJsonObject();
        logger.log(Level.INFO, Logs.SUCCESS_READ_JSON_FILE);
        return jsonObject;
    }

    /**
     * Executes the validation of the  provided JSON object to ensure it contains data.
     *
     * @param jsonObject The JSON object to validate.
     * @throws AssertionError if jsonObject is null or empty.
     */
    private void validateJsonObject(JsonObject jsonObject) {
        assert jsonObject != null : Assertions.NULL_JSON_FILE;
        assert !jsonObject.isEmpty() : Assertions.EMPTY_JSON_FILE;
        logger.log(Level.INFO, Logs.JSON_FILE_CONTAINS_DATA);
    }

    /**
     * Returns the list of university names from the JSON object.
     *
     * @param jsonObject The JSON object containing university names.
     */
    public static void displaySchoolList(JsonObject jsonObject) {
        Set<String> universityNames = jsonObject.keySet();

        System.out.println(LINE_SEPARATOR);

        for (String universityName : universityNames) {
            if (universityName == null || universityName.isEmpty()) {
                logger.log(Level.WARNING, Logs.POSSIBLE_NULL_JSON_KEY);
                System.out.println(Logs.POSSIBLE_NULL_JSON_KEY);
            } else {
                logger.log(Level.INFO, Logs.LIST_SCHOOLS_NAMES);
                ui.printUniversityList(universityName);
            }
        }
        System.out.println(LINE_SEPARATOR);
    }
}
