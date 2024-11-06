package seedu.exchangecoursemapper.storage;

import seedu.exchangecoursemapper.command.Command;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.parser.CourseValidator;
import seedu.exchangecoursemapper.ui.UI;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Logs.DATA_MISMATCH;
import static seedu.exchangecoursemapper.constants.Logs.PARSING_ERROR_LINE;
import static seedu.exchangecoursemapper.constants.Logs.FAILURE_READ_JSON_FILE;

public class DataIntegrityChecker extends Command {

    private static final Logger logger = Logger.getLogger(DataIntegrityChecker.class.getName());
    private static final UI ui = new UI();
    private final CourseValidator courseValidator;

    public DataIntegrityChecker() {
        this.courseValidator = new CourseValidator();
    }

    /**
     * Validates the integrity of all entries in the file.
     * Logs any integrity issues but does not terminate the program.
     *
     * @param entries List of all entries in the file to be validated.
     * @return true if all entries are valid, false if any entry is invalid.
     */
    public boolean validateFileIntegrity(List<String> entries) {
        JsonObject databaseJson;
        try {
            // Load database JSON data
            databaseJson = createJsonObject();
        } catch (IOException e) {
            System.out.println(FAILURE_READ_JSON_FILE);
            return false;
        }

        return isAllLineValid(entries, databaseJson);
    }

    private boolean isAllLineValid(List<String> entries, JsonObject databaseJson) {
        boolean isValid = true;
        for (int i = 0; i < entries.size(); i++) {
            String entry = entries.get(i);
            int lineNumber = i + 1;

            if (!isLineValid(entry, lineNumber, databaseJson)) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Validates a single course entry against the JSON database.
     *
     * @param entry      The course entry in string format.
     * @param lineNumber The line number in the file for reference.
     * @param databaseJson The JSON object containing the course data for validation.
     * @return true if the course entry is valid; otherwise, false.
     */
    private boolean isLineValid(String entry, int lineNumber, JsonObject databaseJson) {
        try {
            // Parse the entry
            Course course = Course.parseCourseEntry(entry);

            // Use CourseValidator to validate the course entry
            if (!courseValidator.isValidInput(course.getNusCourseCode(), course.getPartnerUniversity(),
                    course.getPuCourseCode(), databaseJson)) {
                logger.log(Level.WARNING, DATA_MISMATCH, new Object[]{lineNumber, entry});
                ui.printInvalidCourseEntry(lineNumber, entry);
                return false;
            }

        } catch (Exception e) {
            // Handle parsing errors
            logger.log(Level.SEVERE, PARSING_ERROR_LINE, new Object[]{lineNumber, entry});
            ui.printInvalidEntryFormat(lineNumber, entry);
            return false;
        }

        return true;
    }
}
