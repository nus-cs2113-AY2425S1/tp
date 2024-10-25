package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.storage.Storage;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.JsonKey.*;


public class AddCoursesCommand extends PersonalTrackerCommand {

    private static final Logger logger = Logger.getLogger(AddCoursesCommand.class.getName());

    private static boolean isValidCourseMapping(String nusCourseInput, String puCourseInput,
                                                JsonArray courses, String pu) {
        for (int i = 0; i < courses.size(); i++) {
            JsonObject course = courses.getJsonObject(i);
            String puCourseCode = course.getString(PU_COURSE_CODE_KEY).toLowerCase();
            String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY).toLowerCase();

            if (puCourseCode.equals(puCourseInput)
                    && nusCourseCode.equals(nusCourseInput)) {
                return true;
            }
        }

        System.out.println("Invalid course mapping!");
        displayAvailableMappings(courses,pu);
        return false;
    }

    private static void displayAvailableMappings(JsonArray courses,String pu) {
        System.out.println("The available mappings for " + pu + " are :");
        for (int i = 0; i < courses.size(); i++) {
            JsonObject course = courses.getJsonObject(i);
            String puCourseCode = course.getString(PU_COURSE_CODE_KEY).toLowerCase();
            String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY).toLowerCase();
            String nusCourseName = course.getString(NUS_COURSE_NAME_KEY).toLowerCase();
            String puCourseName = course.getString(PU_COURSE_NAME_KEY).toLowerCase();

            System.out.println();
            System.out.println(nusCourseCode + " " + nusCourseName + " | " + puCourseCode + " " + puCourseName);
        }
    }

    private static JsonArray getPUCourseList(String pu, JsonObject jsonObject) {
        JsonArray courses;
        String matchPu = jsonObject.keySet().stream().filter(key -> key.equalsIgnoreCase(pu)).findFirst().orElse(null);

        if (matchPu != null) {
            courses = jsonObject.getJsonObject(matchPu).getJsonArray(COURSES_ARRAY_LABEL);
        } else {
            System.out.println("Invalid university input!");
            return null;
        }
        return courses;
    }

    @Override
    public void execute(String userInput, Storage storage) {
        try {
            JsonObject jsonObject = super.createJsonObject();      //to add to isValidInputChecker
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
            boolean isValidInput = isValidInput(nusCourse, pu, puCourse, jsonObject);

            if (isValidInput) {
                Course courseToStore = new Course(puCourse, nusCourse, pu);
                storage.addCourse(courseToStore);
                printAddMessage(courseToStore);
            } else {
                System.out.println("Please add a new course mapping!");
            }

        } catch (IllegalArgumentException | IOException e) {
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

        input = input.replaceAll("(?i)/pu", "/pu").
                replaceAll("(?i)/coursepu", "/coursepu")
                .trim()
                .replaceAll(" +", " ");

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

    public boolean isValidInput(String nusCourseInput, String pu, String puCourseInput, JsonObject jsonObject) {
        JsonArray courses = getPUCourseList(pu, jsonObject);
        if (courses == null) {
            return false;
        }
        return isValidCourseMapping(nusCourseInput, puCourseInput, courses,pu);
    }
}
