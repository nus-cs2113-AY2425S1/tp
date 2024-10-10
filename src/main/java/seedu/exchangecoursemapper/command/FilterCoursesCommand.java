package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.exception.Exception;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class FilterCoursesCommand extends Command {
    public static final String COURSES_ARRAY_LABEL = "courses";
    public static final String NUS_COURSE_CODE_KEY = "nus_course_code";
    public static final String PU_COURSE_CODE_KEY = "pu_course_code";
    public static final String LINE_SEPARATOR = "---------------------------------";
    public static final String SPACE = " ";

    @Override
    public void execute(String userInput) {
        try (JsonReader jsonReader = Json.createReader(new FileReader(FILE_PATH))) {
            JsonObject jsonObject = jsonReader.readObject();
            String courseToFind = getNusCourseCode(userInput);
            displayMappableCourses(jsonObject, courseToFind.toLowerCase());
        } catch (IOException e) {
            System.err.println(Exception.fileReadError());
        } catch (IndexOutOfBoundsException e) {
            System.err.println(Exception.courseSearchError());
        }
    }

    public String getNusCourseCode(String userInput) throws IndexOutOfBoundsException {
        String[] userInputDetails = userInput.split(SPACE);
        return userInputDetails[1];
    }

    public void displayMappableCourses(JsonObject jsonObject, String courseToFind) {
        Set<String> universityNames = jsonObject.keySet();
        boolean isCourseFound = false;
        for (String universityName : universityNames) {
            JsonArray courses = jsonObject.getJsonObject(universityName).getJsonArray(COURSES_ARRAY_LABEL);

            for (int i = 0; i < courses.size(); i += 1) {
                JsonObject course = courses.getJsonObject(i);
                String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY);

                // Check if the value in "nus_course_code" matches the input
                if (nusCourseCode.toLowerCase().equals(courseToFind)) {
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
