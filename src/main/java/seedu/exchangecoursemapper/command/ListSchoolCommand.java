package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListSchoolCommand extends Command {
    private static final Logger logger = Logger.getLogger(ListSchoolCommand.class.getName());

    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, "Executing ListSchoolCommand");
        try (JsonReader jsonReader = Json.createReader(new FileReader(FILE_PATH))) {
            JsonObject jsonObject = jsonReader.readObject();
            logger.log(Level.INFO, "Successfully read JSON file");
            assert jsonObject != null : "JSON object should not be null";
            assert !jsonObject.isEmpty() : "JSON file is empty, no universities to display";
            displaySchoolList(jsonObject);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read the file");
            System.err.println(Exception.fileReadError());
        }
        logger.log(Level.INFO, "Execution of ListSchoolCommand complete");
    }

    private static void displaySchoolList(JsonObject jsonObject) {
        Set<String> universityNames = jsonObject.keySet();
        System.out.println(LINE_SEPARATOR);
        for (String universityName : universityNames) {
            assert universityName != null && !universityName.isEmpty();
            logger.log(Level.INFO, "Displaying university names ...");
            System.out.println(universityName);
        }
        System.out.println(LINE_SEPARATOR);
    }
}