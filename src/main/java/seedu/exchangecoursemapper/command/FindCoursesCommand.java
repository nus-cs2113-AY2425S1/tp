package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.CourseRepository;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;
import seedu.exchangecoursemapper.exception.Exception;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.exchangecoursemapper.constants.Assertions.COURSE_STRING_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.EMPTY_USER_INPUT;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class FindCoursesCommand extends PersonalTrackerCommand{

    private static final UI ui = new UI();
    private static final CourseRepository courseRepository = new CourseRepository();
    private static final Logger logger = Logger.getLogger(FindCoursesCommand.class.getName());
    private final Storage storage;

    /**
     * Constructs a FindCoursesCommand with the specified storage.
     *
     * @param storage The Storage instance used to retrieve course data.
     */
    public FindCoursesCommand(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes to search for course mappings that match a keyword in personalised tracker.
     *
     * @param userInput the input provided by the user to execute the command.
     * @param storage the Storage instance used to save or retrieve data.
     */
    @Override
    public void execute(String userInput, Storage storage) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            if(!courseRepository.isFileValid() | courseRepository.hasDuplicateEntries()){
                return;
            }

            String keyword = getKeyword(userInput);
            logger.log(Level.INFO, Logs.EXECUTE_FIND_COMMAND);
            findCommand(keyword);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, Logs.MISSING_KEYWORD);
            System.out.println(e.getMessage());
            System.out.println(LINE_SEPARATOR);
        }
    }

    /**
     * Parses and returns the extracted keyword from user input.
     *
     * @param userInput A string containing the input from users.
     * @return An extracted string containing the keyword.
     */
    public String getKeyword(String userInput) {
        assert userInput != null: EMPTY_USER_INPUT;

        String lowerCaseInput = userInput.toLowerCase();
        String keyword = lowerCaseInput.replaceFirst("find", "").trim();

        if (keyword.isEmpty()) {
            throw new IllegalArgumentException(Exception.emptyKeyword());
        }

        return keyword;
    }

    /**
     * Finds and prints the course mappings that matches with the keyword.
     *
     * @param keyword A string containing the keyword users want to search with.
     */
    public void findCommand(String keyword) {
        List<Course> mappedCourses = storage.loadAllCourses();
        if (mappedCourses.isEmpty()) {
            ui.printEmptyList();
            return;
        }

        List<Course> foundCourses = new ArrayList<>();
        matchKeyword(keyword, mappedCourses, foundCourses);
        printFindCommand(foundCourses);
    }

    /**
     * Prints the course mappings that contains the keyword.
     * If there is no courses that matches, it throws and IllegalArgumentException.
     *
     * @param foundCourses Contains all the mapped courses that contains the keyword.
     */
    private static void printFindCommand(List<Course> foundCourses) {
        if (foundCourses.isEmpty()) {
            logger.log(Level.WARNING, Logs.NO_MATCH_FOUND);
            throw new IllegalArgumentException(Exception.noMatchFound());
        } else {
            for (int i = 0; i < foundCourses.size(); i++) {
                ui.printFoundCourses(foundCourses.get(i));
            }
            ui.printLineSeparator();
        }
    }

    /**
     * Loops and matches the keyword with NUS course codes in personalized tracker.
     *
     * @param keyword A string representing the keyword to search for.
     * @param mappedCourses To represent all the mapped course mappings in the tracker.
     * @param foundCourses To contain mappings that matches with the keyword.
     */
    private static void matchKeyword(String keyword, List<Course> mappedCourses, List<Course> foundCourses) {
        for (Course course : mappedCourses) {
            String nusCourseCode = getMappedCode(course);
            if (nusCourseCode.equals(keyword)) {
                foundCourses.add(course);
                logger.log(Level.INFO, Logs.MATCH_FOUND);
            }
        }
    }

    /**
     * Returns the NUS course code of the mapped courses in personalized tracker.
     *
     * @param course A string containing NUS course code, PU name and PU course code in personalized tracker.
     * @return Extracted NUS course code in tracker
     */
    private static String getMappedCode(Course course) {
        assert course != null : COURSE_STRING_NOT_NULL;
        String nusCourseCode = course.getNusCourseCode();
        logger.log(Level.INFO, Logs.EXTRACTED_COURSE_CODE);
        return nusCourseCode;
    }
}
