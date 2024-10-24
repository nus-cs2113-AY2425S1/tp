package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;

import static seedu.exchangecoursemapper.constants.Commands.PLAN_INDEX_TO_DELETE;
import static seedu.exchangecoursemapper.constants.Commands.ZERO_INDEX_OFFSET;

public class DeleteCoursesCommand extends PersonalTrackerCommand {
    @Override
    public void execute(String userInput, Storage storage) {
        try {
            String[] descriptionSubstrings = parseDeleteCommand(userInput);
            deleteCourse(descriptionSubstrings, storage);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] parseDeleteCommand(String userInput) {
        String input = userInput.trim().replaceAll(" +", " ");
        String[] descriptionSubstrings = input.split(" ");
        if (descriptionSubstrings.length < 2 || descriptionSubstrings[1].trim().isEmpty()) {
            throw new IllegalArgumentException(Exception.noInputAfterDelete());
        }
        return descriptionSubstrings;
    }

    public void printDeleteMessage(Course deleteCourse) {
        System.out.println("You have deleted the course from your plan: " + deleteCourse.formatOutput());
    }

    public void deleteCourse(String[] descriptionSubstrings, Storage storage) {
        try {
            int listIndex = Integer.parseInt(descriptionSubstrings[PLAN_INDEX_TO_DELETE]) - ZERO_INDEX_OFFSET;
            Course courseToDelete = storage.getCourse(listIndex);
            storage.deleteCourse(listIndex);
            printDeleteMessage(courseToDelete);
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Exception.invalidCourseListIndex());
        }
    }

}
