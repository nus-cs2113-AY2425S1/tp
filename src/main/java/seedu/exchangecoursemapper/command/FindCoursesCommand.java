package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;
import seedu.exchangecoursemapper.exception.Exception;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.exchangecoursemapper.constants.Assertions.COURSE_STRING_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.EMPTY_USER_INPUT;
import static seedu.exchangecoursemapper.constants.Logs.*;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;
import static seedu.exchangecoursemapper.constants.Regex.PIPE;

public class FindCoursesCommand extends PersonalTrackerCommand{

    private final Storage storage;
    private static final UI ui = new UI();
    private static final Logger logger = Logger.getLogger(FindCoursesCommand.class.getName());

    public FindCoursesCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute(String userInput, Storage storage) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            logger.log(Level.INFO, EXECUTE_GET_KEYWORD);
            String keyword = getKeyword(userInput);
            logger.log(Level.INFO, EXECUTE_FIND_COMMAND);
            findCommand(keyword);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, MISSING_KEYWORD);
            System.out.println(e.getMessage());
            System.out.println(LINE_SEPARATOR);
        }
    }

    public String getKeyword(String userInput) {
        assert userInput != null: EMPTY_USER_INPUT;

        String lowerCaseInput = userInput.toLowerCase();
        String keyword = lowerCaseInput.replaceFirst("find", "").trim();

        if (keyword.isEmpty()) {
            throw new IllegalArgumentException(Exception.emptyKeyword());
        }

        return keyword;
    }

    public void findCommand(String keyword) {
        List<String> mappedCourses = storage.loadAllCourses();
        if (mappedCourses.isEmpty()) {
            ui.printEmptyList();
            return;
        }

        List<String> foundCourses = new ArrayList<>();

        matchKeyword(keyword, mappedCourses, foundCourses);

        if (foundCourses.isEmpty()) {
            logger.log(Level.INFO, NO_MATCH_FOUND);
            ui.printNoMatchFound();
        } else {
            for (int i = 0; i < foundCourses.size(); i++) {
                ui.printFoundCourses(foundCourses.get(i));
            }
            ui.printLineSeparator();
        }
    }

    private static void matchKeyword(String keyword, List<String> mappedCourses, List<String> foundCourses) {
        for (String course : mappedCourses) {
            String nusCourseCode = getMappedCode(course);
            if (nusCourseCode.equals(keyword)) {
                foundCourses.add(course);
                logger.log(Level.INFO, MATCH_FOUND);
            }
        }
    }

    private static String getMappedCode(String course) {
        assert course != null: COURSE_STRING_NOT_NULL;

        String[] mappedParts = course.split(PIPE);
        logger.log(Level.INFO, EXTRACTED_COURSE_CODE);
        return mappedParts[0].trim();
    }
}
