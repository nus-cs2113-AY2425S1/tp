package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.constants.Assertions;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddCoursesCommand extends Command {

    private static final Logger logger = Logger.getLogger(AddCoursesCommand.class.getName());

    Storage storage = new Storage();

    @Override
    public void execute(String userInput) {
        try {
            logger.log(Level.INFO, Logs.TRIM_STRING);
            String description = trimString(userInput);
            logger.log(Level.INFO, Logs.PARSE_ADD_COMMANDS);
            String[] descriptionSubstrings = parseAddCommand(description);

            assert descriptionSubstrings.length == 3: Assertions.MISSING_FIELDS;
            logger.log(Level.INFO, Logs.EXTRACT_COURSES);
            String nusCourse = descriptionSubstrings[0].trim();
            String pu = descriptionSubstrings[1].trim();
            String puCourse = descriptionSubstrings[2].trim();


            logger.log(Level.INFO, Logs.FORMAT);
            String courseToStore = nusCourse + " | " + pu + " | " + puCourse;
            logger.log(Level.INFO, Logs.ADD_TO_STORAGE + courseToStore);
            storage.addCourse(courseToStore);

            printAddMessage(courseToStore);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String trimString(String string) {
        String trimmedString = string.trim();

        assert !trimmedString.isEmpty(): Assertions.MISSING_USER_INPUT;
        String[] outputSubstrings = trimmedString.split(" ", 2);

        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            logger.log(Level.WARNING, Logs.MISSING_INPUT_AFTER_KEYWORD);
            throw new IllegalArgumentException(Exception.noInputAfterAdd());
        }

        logger.log(Level.INFO, Logs.RETURN_TRIMMED_INPUT);
        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {

        input = input.replaceAll("(?i)/pu", "/pu")
                .replaceAll("(?i)/coursepu", "/coursepu")
                .trim().replaceAll(" +", " ");


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

    public void printAddMessage(String addCourse) {
        System.out.println("You have successfully added the course: " + addCourse);
    }
}
