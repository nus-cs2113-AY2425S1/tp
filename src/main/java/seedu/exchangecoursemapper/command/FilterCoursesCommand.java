package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.exception.Exception;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;


import static seedu.exchangecoursemapper.constants.Commands.COMMAND_WORD_INDEX;
import static seedu.exchangecoursemapper.constants.Commands.ZERO_INDEX_OFFSET;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES_MAX_ARGS;
import static seedu.exchangecoursemapper.constants.JsonKey.COURSES_ARRAY_LABEL;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;
import static seedu.exchangecoursemapper.constants.Messages.NO_MAPPABLE_COURSES_MESSAGE;
import static seedu.exchangecoursemapper.constants.Regex.REPEATED_SPACES;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

public class FilterCoursesCommand extends Command {
    private static final Logger logger = Logger.getLogger(FilterCoursesCommand.class.getName());

    @Override
    public void execute(String userInput) {
        try (JsonReader jsonReader = Json.createReader(new FileReader(FILE_PATH))) {
            JsonObject jsonObject = jsonReader.readObject();
            logger.log(Level.INFO, Logs.SUCCESS_READ_JSON_FILE);
            assert jsonObject != null : Assertions.NULL_JSON_FILE;
            assert !jsonObject.isEmpty() : Assertions.EMPTY_JSON_FILE;
            String courseToFind = getNusCourseCode(userInput);
            displayMappableCourses(jsonObject, courseToFind.toLowerCase());
        } catch (IOException e) {
            System.err.println(Exception.fileReadError());
        }
    }

    public String getNusCourseCode(String userInput) {
        String input = userInput.trim().replaceAll(REPEATED_SPACES, SPACE);
        String[] inputDetails = input.split(SPACE);
        if (inputDetails.length == COMMAND_WORD_INDEX + ZERO_INDEX_OFFSET) {
            throw new IllegalArgumentException(Exception.missingNusCourseCode());
        }
        if (inputDetails.length > FILTER_COURSES_MAX_ARGS) {
            throw new IllegalArgumentException(Exception.filterCoursesLimitExceeded());
        }
        assert inputDetails[1] != null : Assertions.NO_NUS_COURSE_CODE_PARSED;
        return inputDetails[1];
    }

    public void displayMappableCourses(JsonObject jsonObject, String courseToFind) {
        Set<String> universityNames = jsonObject.keySet();
        boolean isCourseFound = false;
        for (String universityName : universityNames) {
            assert universityName != null && !universityName.isEmpty();
            JsonArray courses = jsonObject.getJsonObject(universityName).getJsonArray(COURSES_ARRAY_LABEL);
            int numberOfCourses = courses.size();
            isCourseFound = isCourseFound(courseToFind, universityName, numberOfCourses, courses, isCourseFound);
        }

        if (!isCourseFound) {
            System.out.println(NO_MAPPABLE_COURSES_MESSAGE);
        }
    }

    public boolean isCourseFound(String courseToFind, String universityName, int numberOfCourses,
                                         JsonArray courses, boolean isCourseFound) {
        for (int i = 0; i < numberOfCourses; i += 1) {
            JsonObject course = courses.getJsonObject(i);
            assert course != null : Assertions.NO_COURSE_INFORMATION;
            String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY);

            if (nusCourseCode.equalsIgnoreCase(courseToFind)) {
                String puCourseCode = course.getString(PU_COURSE_CODE_KEY);
                System.out.println("Partner University: " + universityName);
                System.out.println("Partner University Course Code: " + puCourseCode);
                System.out.println(LINE_SEPARATOR);
                isCourseFound = true;
            }
        }
        return isCourseFound;
    }
}
