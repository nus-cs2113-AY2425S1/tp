package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.exception.ExchangeCourseMapperException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;


import static seedu.exchangecoursemapper.constants.Commands.COMMAND_WORD_INDEX;
import static seedu.exchangecoursemapper.constants.Commands.ZERO_INDEX_OFFSET;
import static seedu.exchangecoursemapper.constants.Commands.FILTER_COURSES_MAX_ARGS;
import static seedu.exchangecoursemapper.constants.JsonKey.COURSES_ARRAY_LABEL;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.Messages.FILTER_COURSES_LIMIT_MESSAGE;
import static seedu.exchangecoursemapper.constants.Messages.NO_NUS_COURSE_CODE_INPUT_MESSAGE;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;
import static seedu.exchangecoursemapper.constants.Regex.REPEATED_SPACES;
import static seedu.exchangecoursemapper.constants.Regex.SPACE;

public class FilterCoursesCommand extends Command {
    @Override
    public void execute(String userInput) {
        try (JsonReader jsonReader = Json.createReader(new FileReader(FILE_PATH))) {
            JsonObject jsonObject = jsonReader.readObject();
            String courseToFind = getNusCourseCode(userInput);
            displayMappableCourses(jsonObject, courseToFind.toLowerCase());
        } catch (IOException e) {
            System.err.println(Exception.fileReadError());
        } catch (ExchangeCourseMapperException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNusCourseCode(String userInput) throws ExchangeCourseMapperException {
        String input = userInput.trim().replaceAll(REPEATED_SPACES, SPACE);
        String[] inputDetails = input.split(SPACE);
        if (inputDetails.length == COMMAND_WORD_INDEX + ZERO_INDEX_OFFSET) {
            throw new ExchangeCourseMapperException(NO_NUS_COURSE_CODE_INPUT_MESSAGE);
        }
        if (inputDetails.length > FILTER_COURSES_MAX_ARGS) {
            throw new ExchangeCourseMapperException(FILTER_COURSES_LIMIT_MESSAGE);
        }
        return inputDetails[1];
    }

    public void displayMappableCourses(JsonObject jsonObject, String courseToFind) {
        Set<String> universityNames = jsonObject.keySet();
        boolean isCourseFound = false;
        for (String universityName : universityNames) {
            JsonArray courses = jsonObject.getJsonObject(universityName).getJsonArray(COURSES_ARRAY_LABEL);

            for (int i = 0; i < courses.size(); i += 1) {
                JsonObject course = courses.getJsonObject(i);
                String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY);

                if (nusCourseCode.equalsIgnoreCase(courseToFind)) {
                    String puCourseCode = course.getString(PU_COURSE_CODE_KEY);
                    System.out.println("Partner University: " + universityName);
                    System.out.println("Partner University Course Code: " + puCourseCode);
                    System.out.println(LINE_SEPARATOR);
                    isCourseFound = true;
                }
            }
        }

        if (!isCourseFound) {
            System.out.println("No courses found for the given course code.");
        }
    }
}
