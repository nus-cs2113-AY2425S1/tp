package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.parser.CourseValidator;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddCoursesCommand extends PersonalTrackerCommand {

    private static final Logger logger = Logger.getLogger(AddCoursesCommand.class.getName());
    private CourseValidator courseValidator;

    public AddCoursesCommand() {
        courseValidator = new CourseValidator();
    }

    @Override
    public void execute(String userInput, Storage storage) {
        try {
            JsonObject jsonObject = super.createJsonObject();
            logger.log(Level.INFO, Logs.SUCCESS_READ_JSON_FILE);
            assert jsonObject != null : Assertions.NULL_JSON_FILE;
            assert !jsonObject.isEmpty() : Assertions.EMPTY_JSON_FILE;

            logger.log(Level.INFO, Logs.TRIM_STRING);
            String description = trimString(userInput);
            logger.log(Level.INFO, Logs.PARSE_ADD_COMMANDS);
            String[] descriptionSubstrings = parseAddCommand(description);

            assert descriptionSubstrings.length == 3 : Assertions.MISSING_FIELDS;
            logger.log(Level.INFO, Logs.EXTRACT_COURSES);
            String nusCourse = descriptionSubstrings[0].trim().toLowerCase();
            String pu = descriptionSubstrings[1].trim().toLowerCase();
            String puCourse = descriptionSubstrings[2].trim().toLowerCase();

            logger.log(Level.INFO, Logs.FORMAT);
            boolean isValidInput = courseValidator.isValidInput(nusCourse, pu, puCourse, jsonObject);

            if (isValidInput) {
                logger.log(Level.INFO, Logs.ADD_APPROVED_MAPPING);
                Course courseToStore = new Course(puCourse, nusCourse, pu);
                storage.addCourse(courseToStore);
                printAddMessage(courseToStore);
            } else {
                System.out.println(Logs.ADD_NEW_COURSE_MAPPING);
            }

        } catch (IllegalArgumentException | IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public String trimString(String string) {
        String trimmedString = string.trim();

        assert !trimmedString.isEmpty() : Assertions.MISSING_USER_INPUT;
        String[] outputSubstrings = trimmedString.split(" ", 2);

        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            logger.log(Level.WARNING, Logs.MISSING_INPUT_AFTER_KEYWORD);
            throw new IllegalArgumentException(Exception.noInputAfterAdd());
        }

        logger.log(Level.INFO, Logs.RETURN_TRIMMED_INPUT);
        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {

        input = input.replaceAll("(?i)/pu", "/pu").replaceAll("(?i)/coursepu", "/coursepu").trim().replaceAll(" +", " ");

        if ((!input.contains("/pu") || !input.contains("/coursepu"))) {
            logger.log(Level.WARNING, Logs.MISSING_KEYWORDS);
            throw new IllegalArgumentException(Exception.missingKeyword());
        }

        if (input.contains("/pu/coursepu") || input.contains("/coursepu/pu")) {
            logger.log(Level.WARNING, Logs.ADJACENT_KEYWORDS);
            throw new IllegalArgumentException(Exception.adjacentInputError());
        }

        String[] inputSubstrings = input.split(" /coursepu | /pu ");

        if (inputSubstrings.length < 3) {
            logger.log(Level.WARNING, Logs.INVALID_COURSE_CODE);
            throw new IllegalArgumentException(Exception.invalidCourseCodes());
        }

        return inputSubstrings;
    }

    public void printAddMessage(Course addCourse) {
        System.out.println("You have successfully added the course: " + addCourse.formatOutput());
    }

}
