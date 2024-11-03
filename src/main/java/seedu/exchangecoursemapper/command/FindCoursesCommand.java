package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;
import seedu.exchangecoursemapper.exception.Exception;

import java.util.ArrayList;
import java.util.List;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;
import static seedu.exchangecoursemapper.constants.Regex.PIPE;

public class FindCoursesCommand extends PersonalTrackerCommand{

    private final Storage storage;
    private static final UI ui = new UI();

    public FindCoursesCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute(String userInput, Storage storage){
        try {
            String keyword = getKeyword(userInput);
            findCommand(keyword);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE_SEPARATOR);
        }
    }

    public String getKeyword(String userInput) {
        String lowerCaseInput = userInput.toLowerCase();
        String keyword = lowerCaseInput.replaceFirst("find", "").trim();

        if (keyword.isEmpty()) {
            throw new IllegalArgumentException(Exception.emptyKeyword());
        }

        return keyword;
    }

    public void findCommand(String keyword) {
        List<String> mappedCourses = storage.loadAllCourses();
        List<String> foundCourses = new ArrayList<>();

        matchKeyword(keyword, mappedCourses, foundCourses);

        if (foundCourses.isEmpty()) {
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
            }
        }
    }

    private static String getMappedCode(String course) {
        String[] mappedParts = course.split(PIPE);
        return mappedParts[0].trim();
    }
}
