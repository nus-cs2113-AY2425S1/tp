package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.CourseRepository;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Assertions.MISSING_FIELDS;
import static seedu.exchangecoursemapper.constants.Assertions.NULL_INPUT;
import static seedu.exchangecoursemapper.constants.Commands.DELETE_COMMAND_PARAMETER_INDEX;
import static seedu.exchangecoursemapper.constants.Commands.DELETE_COURSES_MAX_ARGS;
import static seedu.exchangecoursemapper.constants.Commands.ZERO_INDEX_OFFSET;
import static seedu.exchangecoursemapper.constants.Logs.EXECUTING_COMMAND;
import static seedu.exchangecoursemapper.constants.Logs.PARSE_DELETE_COMMAND;
import static seedu.exchangecoursemapper.constants.Logs.RETURN_PARSED_DELETE_COMMAND;
import static seedu.exchangecoursemapper.constants.Logs.GET_INDEX_OF_COURSE_TO_DELETE;
import static seedu.exchangecoursemapper.constants.Logs.GET_COURSE_TO_DELETE;
import static seedu.exchangecoursemapper.constants.Logs.DELETE_COURSE_MAPPING;
import static seedu.exchangecoursemapper.constants.Regex.REPEATED_SPACES;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

/**
 * DeleteCoursesCommand extends from PersonalTrackerCommand, and accesses the Storage to delete a course mapping plan
 * at the user specified index in the Storage list.
 */
public class DeleteCoursesCommand extends PersonalTrackerCommand {
    private static final Logger logger = Logger.getLogger(DeleteCoursesCommand.class.getName());
    private CourseRepository courseRepository;
    private UI ui;

    /**
     * Class Constructor.
     * */
    public DeleteCoursesCommand() {
        logger.setLevel(Level.SEVERE);
        ui = new UI();
        courseRepository = new CourseRepository();
    }


    /**
     * Executes the delete courses command, which deletes a course mapping plan from the list in the Storage,
     * based on a user specified list index.
     *
     * @param userInput A string containing the user's input.
     * @param storage refers to the storage class from the execute function.
     */
    @Override
    public void execute(String userInput, Storage storage) {
        logger.log(Level.INFO, EXECUTING_COMMAND);
        try {
            if(!courseRepository.isFileValid()){
                return;
            }
            courseRepository.removeDuplicateEntries();
            logger.log(Level.INFO, PARSE_DELETE_COMMAND);
            String[] inputDetails = parseDeleteCommand(userInput);
            assert inputDetails.length == DELETE_COURSES_MAX_ARGS : MISSING_FIELDS;
            int listIndex = getListIndex(inputDetails);
            deleteCourse(listIndex, storage);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, e.getMessage());
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Parse the user input and extract out the list index of the course mapping the user wants to delete.
     *
     * @param userInput a string containing the user input.
     * @return a String[] containing the extracted information: list index of the course mapping to be deleted.
     */
    public String[] parseDeleteCommand(String userInput) {
        String input = userInput.trim().replaceAll(REPEATED_SPACES, SPACE);
        assert !input.isEmpty() : NULL_INPUT;
        String[] inputDetails = input.split(SPACE);
        if (inputDetails.length < DELETE_COURSES_MAX_ARGS ||
                inputDetails[DELETE_COMMAND_PARAMETER_INDEX].trim().isEmpty()) {
            throw new IllegalArgumentException(Exception.noInputAfterDelete());
        }
        if (inputDetails.length > DELETE_COURSES_MAX_ARGS) {
            throw new IllegalArgumentException(Exception.deleteCoursesLimitExceeded());
        }
        logger.log(Level.INFO, RETURN_PARSED_DELETE_COMMAND);
        return inputDetails;
    }

    /**
     * Returns the index of the course to be deleted.
     *
     * @param inputDetails a String[] containing the extracted information: list index of the course
     *                              mapping to be deleted.
     * @return an integer that represents the list index of the course to be deleted.
     */
    public int getListIndex(String[] inputDetails) {
        try {
            assert inputDetails.length == DELETE_COURSES_MAX_ARGS |
                    inputDetails[DELETE_COMMAND_PARAMETER_INDEX].trim().isEmpty() : MISSING_FIELDS;
            logger.log(Level.INFO, GET_INDEX_OF_COURSE_TO_DELETE);
            return Integer.parseInt(inputDetails[DELETE_COMMAND_PARAMETER_INDEX]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Exception.nonNumericListIndexInput());
        }
    }

    /**
     * Executes the main logic of the deletion of a course mapping plan.
     *
     * @param listIndex an integer that represents the list index of the course to be deleted.
     * @param storage refers to the storage class from the execute function.
     */
    public void deleteCourse(int listIndex, Storage storage) {
        try {
            logger.log(Level.INFO, GET_COURSE_TO_DELETE);
            int listIndexWithOffset = listIndex - ZERO_INDEX_OFFSET;
            Course courseToDelete = storage.getCourse(listIndexWithOffset);
            logger.log(Level.INFO, DELETE_COURSE_MAPPING);
            storage.deleteCourse(listIndexWithOffset);
            ui.printDeleteMessage(courseToDelete);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Exception.invalidCourseListIndex());
        }
    }
}
