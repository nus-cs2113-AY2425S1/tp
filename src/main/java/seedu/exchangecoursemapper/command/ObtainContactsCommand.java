package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.exception.Exception;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Regex.BACKSLASH;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

public class ObtainContactsCommand extends Command {
    public static final String EMAIL = "email";
    public static final String NUMBER = "number";
    private static final Logger logger = Logger.getLogger(ObtainContactsCommand.class.getName());

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
            handleContactType(schoolInfo, matchingSchool, contactType);
        } catch (IOException e) {
            logger.log(Level.WARNING, Logs.FAILURE_READ_JSON_FILE);
            System.err.println(Exception.fileReadError());
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        logger.log(Level.INFO, Logs.COMPLETE_EXECUTION);
    }

    public String getSchoolName(String userInput) {
        String inputWithoutCommand = userInput.substring(userInput.indexOf(SPACE) + 1).trim();
        String[] inputParts = inputWithoutCommand.split(BACKSLASH);
        assert inputParts.length > 0 : Assertions.EMPTY_SCHOOL_NAME;
        return inputParts[0].trim();
    }

    public String getContactType(String userInput) {
        String inputWithoutCommand = userInput.substring(userInput.indexOf(SPACE) + 1).trim();
        String[] inputParts = inputWithoutCommand.split(BACKSLASH);

        if (inputParts.length != 2) {
            throw new IllegalArgumentException(Exception.invalidInputFormat());
        }
        return inputParts[1].trim();
    }

    public void handleContactType(JsonObject schoolInfo, String schoolName, String contactType) {
        switch (contactType) {
        case EMAIL:
            String email = schoolInfo.getString(EMAIL);
            System.out.println("Email for " + schoolName + ": " + email);
            break;
        case NUMBER:
            String number = schoolInfo.getString(NUMBER);
            System.out.println("Phone number for " + schoolName + ": " + number);
            break;
        default:
            logger.warning("Invalid contact type requested: " + contactType);
            System.out.println(Exception.invalidContactType());
        }
    }

    public String findMatchingSchool(JsonObject jsonObject, String schoolName) {
        assert jsonObject != null : "JsonObject is not be null";
        assert schoolName != null : "School name is not be null";
        for (String key : jsonObject.keySet()) {
            if (key.toLowerCase().equals(schoolName)) {
                return key;
            }
        }
        System.out.println("Unknown university - " + schoolName);
        return null;
    }
}
