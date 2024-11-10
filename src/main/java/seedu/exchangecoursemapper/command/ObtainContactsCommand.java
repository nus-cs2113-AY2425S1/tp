package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.constants.Messages;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.parser.SchoolContactValidator;
import seedu.exchangecoursemapper.ui.UI;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Regex.BACKSLASH;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;
import static seedu.exchangecoursemapper.constants.JsonKey.EMAIL_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUMBER_KEY;

public class ObtainContactsCommand extends CheckInformationCommand {
    private static final Logger logger = Logger.getLogger(ObtainContactsCommand.class.getName());
    private static UI ui;
    private static SchoolContactValidator schoolContactValidator;

    /**
     * Class Constructor
     */
    public ObtainContactsCommand() {
        logger.setLevel(Level.OFF);
        ui = new UI();
        schoolContactValidator = new SchoolContactValidator();
    }

    /**
     * Executes the command to retrieve contact information for a university.
     * Reads from the JSON file and outputs the email or phone number based on the input.
     *
     * @param userInput Contains a school name and contact type separated by a backslash.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            JsonObject jsonObject = super.createJsonObject();
            logger.log(Level.INFO, Logs.SUCCESS_READ_JSON_FILE);

            assert jsonObject != null : Assertions.NULL_JSON_FILE;
            assert !jsonObject.isEmpty() : Assertions.EMPTY_JSON_FILE;

            String schoolName = getSchoolName(userInput).toLowerCase();
            String contactType = getContactType(userInput);
            String matchingSchool = findMatchingSchool(jsonObject, schoolName);
            JsonObject schoolInfo = jsonObject.getJsonObject(matchingSchool);

            if (schoolInfo == null) {
                return;
            }
            checkValidContact(schoolInfo, matchingSchool, contactType);
        } catch (IOException e) {
            logger.log(Level.WARNING, Logs.FAILURE_READ_JSON_FILE);
            System.err.println(Exception.fileReadError());
            return;
        } catch (IllegalArgumentException e) {
            logger.log(Level.OFF, e.getMessage());
            return;
        }
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }

    /**
     * Returns the school name from the user input.
     *
     * @param userInput the input string provided by the user.
     * @return the school name as a string.
     * @throws AssertionError if the school name is empty.
     */
    public String getSchoolName(String userInput) {
        String inputWithoutCommand = userInput.substring(userInput.indexOf(SPACE) + 1).trim();
        String[] inputParts = inputWithoutCommand.split(BACKSLASH);
        assert inputParts.length > 0 : Assertions.EMPTY_SCHOOL_NAME;
        return inputParts[0].trim();
    }

    /**
     * Returns the contact type from the user input.
     *
     * @param userInput the input string provided by the user.
     * @return the contact type as a string.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public String getContactType(String userInput) {
        String inputWithoutCommand = userInput.substring(userInput.indexOf(SPACE) + 1).trim();
        String[] inputParts = inputWithoutCommand.split(BACKSLASH);

        if (inputParts.length != 2) {
            System.out.println(Exception.invalidInputFormat());
            logger.log(Level.WARNING, "Invalid input format.");
            throw new IllegalArgumentException(Exception.invalidInputFormat());
        }
        return inputParts[1].trim();
    }


    /**
     * Returns the school name that matches the provided school name.
     *
     * @param jsonObject the JSON object containing school information.
     * @param schoolName the name of the school to search for.
     * @return the matching school name or null if not found.
     */
    public static String getMatchingSchoolName(JsonObject jsonObject, String schoolName) {
        for (String key : jsonObject.keySet()) {
            if (key.toLowerCase().equals(schoolName.toLowerCase())) {
                return key;
            }
        }
        return null;
    }

    /**
     * Returns the matching school in the provided JSON object.
     *
     * @param jsonObject the JSON object containing all school information.
     * @param schoolName the school name to search for.
     * @return the matching school name if found, or the input school name if not found.
     * @throws AssertionError if jsonObject or schoolName is null.
     */
    public String findMatchingSchool(JsonObject jsonObject, String schoolName) {
        assert jsonObject != null : Assertions.NULL_JSON_OBJECT;
        assert schoolName != null : Assertions.NULL_SCHOOL_NAME;

        if (schoolContactValidator.isSchoolValid(jsonObject, schoolName)) {
            String key = getMatchingSchoolName(jsonObject, schoolName);
            if (key != null) {
                return key;
            }
        } else {
            logger.log(Level.WARNING, "Unknown university - {0}", schoolName);
            System.out.println("Unknown university - " + schoolName);
        }

        return schoolName;
    }

    /**
     * Executes the checking of contact type and retrieves the corresponding contact information
     *
     * @param schoolInfo  the JSON object containing the school's information.
     * @param schoolName  the name of the school as a string
     * @param contactType the type of contact information to retrieve as a string.
     */
    public void checkValidContact(JsonObject schoolInfo, String schoolName, String contactType) {
        if (schoolContactValidator.isValidContactType(contactType)) {
            contactTypeIdentifier(schoolInfo, schoolName, contactType);
        } else {
            logger.log(Level.WARNING, "Invalid contact type requested: " + contactType);
            System.out.println(Exception.invalidContactType());
        }
    }

    /**
     * Returns the contact type and displays the appropriate contact information.
     *
     * @param schoolInfo  the JSON object containing the school's information.
     * @param schoolName  the name of the school as a string.
     * @param contactType the contact type (either "email" or "number").
     */
    public static void contactTypeIdentifier(JsonObject schoolInfo, String schoolName, String contactType) {
        switch (contactType) {
        case EMAIL_KEY:
            String email = schoolInfo.getString(EMAIL_KEY);
            logger.log(Level.INFO, Logs.EMAIL_SUCCESS);
            ui.printContactInformation(Messages.EMAIL_TAG, schoolName, email);
            break;
        case NUMBER_KEY:
            String number = schoolInfo.getString(NUMBER_KEY);
            logger.log(Level.INFO, Logs.NUMBER_SUCCESS);
            ui.printContactInformation(Messages.NUMBER_TAG, schoolName, number);
            break;
        default:
            break;
        }
    }
}
