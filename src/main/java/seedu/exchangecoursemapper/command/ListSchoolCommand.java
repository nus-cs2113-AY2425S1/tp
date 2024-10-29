package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListSchoolCommand extends CheckInformationCommand {
    private static final Logger logger = Logger.getLogger(ListSchoolCommand.class.getName());

    /**
     * Executes the listing of schools to output a list of possible schools from the JSON file .
     *
     * @param userInput A string containing the user's input.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            JsonObject jsonObject = super.createJsonObject();
            logger.log(Level.INFO, Logs.SUCCESS_READ_JSON_FILE);
            assert jsonObject != null : Assertions.NULL_JSON_FILE;
            assert !jsonObject.isEmpty() : Assertions.EMPTY_JSON_FILE;
            displaySchoolList(jsonObject);
        } catch (IOException e) {
            logger.log(Level.WARNING, Logs.FAILURE_READ_JSON_FILE);
            System.out.println(Exception.fileReadError());
        }
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }

    /**
     * Displays the list of university names found in the provided JSON object.
     * Prints out each university name to the console.
     *
     * @param jsonObject the JSON object containing university names as keys.
     * @throws AssertionError if a university name is null or empty.
     */
    public static void displaySchoolList(JsonObject jsonObject) {
        Set<String> universityNames = jsonObject.keySet();
        System.out.println(LINE_SEPARATOR);
        for (String universityName : universityNames) {
            assert universityName != null && !universityName.isEmpty();
            logger.log(Level.INFO, Logs.LIST_SCHOOLS_NAMES);
            System.out.println(universityName);
        }
        System.out.println(LINE_SEPARATOR);
    }
}
