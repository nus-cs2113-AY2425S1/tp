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

    public ListSchoolCommand() {
        ui = new UI();
    }

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

    public static void displaySchoolList(JsonObject jsonObject) {
        Set<String> universityNames = jsonObject.keySet();
        System.out.println(LINE_SEPARATOR);
        for (String universityName : universityNames) {
            assert universityName != null && !universityName.isEmpty();
            logger.log(Level.INFO, Logs.LIST_SCHOOLS_NAMES);
            ui.printUniversityList(universityName);
        }
        System.out.println(LINE_SEPARATOR);
    }
}
