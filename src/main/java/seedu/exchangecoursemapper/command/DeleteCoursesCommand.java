package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Commands.PLAN_INDEX_TO_DELETE;
import static seedu.exchangecoursemapper.constants.Commands.ZERO_INDEX_OFFSET;
import static seedu.exchangecoursemapper.constants.Regex.REPEATED_SPACES;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

public class DeleteCoursesCommand extends PersonalTrackerCommand {
    private static final Logger logger = Logger.getLogger(DeleteCoursesCommand.class.getName());

    @Override
    public void execute(String userInput, Storage storage) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            logger.log(Level.INFO, Logs.PARSE_ADD_COMMANDS);
            String[] descriptionSubstrings = parseDeleteCommand(userInput);
            assert descriptionSubstrings.length == 2 : Assertions.MISSING_FIELDS;
            deleteCourse(descriptionSubstrings, storage);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public String[] parseDeleteCommand(String userInput) {
        String input = userInput.trim().replaceAll(REPEATED_SPACES, SPACE);
        assert !input.isEmpty() : Assertions.NULL_INPUT;
        String[] descriptionSubstrings = input.split(SPACE);
        if (descriptionSubstrings.length < 2 || descriptionSubstrings[1].trim().isEmpty()) {
            throw new IllegalArgumentException(Exception.noInputAfterDelete());
        }
        if (descriptionSubstrings.length > 2) {
            throw new IllegalArgumentException(Exception.deleteCoursesLimitExceeded());
        }
        logger.log(Level.INFO, Logs.RETURN_PARSED_DELETE_COMMAND);
        return descriptionSubstrings;
    }

    public void printDeleteMessage(Course deleteCourse) {
        System.out.println("You have deleted the course from your plan: " + deleteCourse.formatOutput());
    }

    public void deleteCourse(String[] descriptionSubstrings, Storage storage) {
        try {
            assert descriptionSubstrings.length == 2 |  descriptionSubstrings[1].trim().isEmpty() :
                    Assertions.MISSING_FIELDS;
            int listIndex = Integer.parseInt(descriptionSubstrings[PLAN_INDEX_TO_DELETE]) - ZERO_INDEX_OFFSET;
            logger.log(Level.INFO, Logs.GET_COURSE_TO_DELETE);
            Course courseToDelete = storage.getCourse(listIndex);
            logger.log(Level.INFO, Logs.DELETE_COURSE_MAPPING);
            storage.deleteCourse(listIndex);
            printDeleteMessage(courseToDelete);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Exception.invalidCourseListIndex());
        }
    }

}
