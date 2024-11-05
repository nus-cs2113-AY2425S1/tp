package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.CourseRepository;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Commands.PLAN_INDEX_TO_DELETE;
import static seedu.exchangecoursemapper.constants.Commands.ZERO_INDEX_OFFSET;
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
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            if(!courseRepository.isFileValid()){
                return;
            }
            logger.log(Level.INFO, Logs.PARSE_ADD_COMMANDS);
            String[] descriptionSubstrings = parseDeleteCommand(userInput);
            assert descriptionSubstrings.length == 2 : Assertions.MISSING_FIELDS;
            deleteCourse(descriptionSubstrings, storage);
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

    /**
     * Executes the main logic of the deletion of a course mapping plan.
     *
     * @param descriptionSubstrings a String[] containing the extracted information: list index of the course
     *                              mapping to be deleted.
     * @param storage refers to the storage class from the execute function.
     */
    public void deleteCourse(String[] descriptionSubstrings, Storage storage) {
        try {
            assert descriptionSubstrings.length == 2 |  descriptionSubstrings[1].trim().isEmpty() :
                    Assertions.MISSING_FIELDS;
            int listIndex = Integer.parseInt(descriptionSubstrings[PLAN_INDEX_TO_DELETE]) - ZERO_INDEX_OFFSET;
            logger.log(Level.INFO, Logs.GET_COURSE_TO_DELETE);
            Course courseToDelete = storage.getCourse(listIndex);
            logger.log(Level.INFO, Logs.DELETE_COURSE_MAPPING);
            storage.deleteCourse(listIndex);
            ui.printDeleteMessage(courseToDelete);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Exception.invalidCourseListIndex());
        }
    }

}
