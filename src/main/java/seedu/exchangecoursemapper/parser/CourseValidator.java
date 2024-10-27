package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.exception.Exception;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_NAME_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_NAME_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.COURSES_ARRAY_LABEL;
import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;
import static seedu.exchangecoursemapper.constants.Messages.LIST_RELEVANT_PU;

public class CourseValidator {

    private static final Logger logger = Logger.getLogger(CourseValidator.class.getName());

    public boolean isValidCourseMapping(String nusCourseInput, String puCourseInput,
                                                JsonArray courses, String pu) {

        if (nusCourseInput.isEmpty() || puCourseInput.isEmpty()) {
            throw new IllegalArgumentException(Exception.emptyCourse());
        }
        if (courses == null || courses.isEmpty()) {
            throw new IllegalArgumentException(Exception.noCourseAvailable(pu));
        }

        for (int i = 0; i < courses.size(); i++) {
            logger.log(Level.INFO, Logs.FIND_COURSE_MAPPING);
            JsonObject course = courses.getJsonObject(i);
            String puCourseCode = course.getString(PU_COURSE_CODE_KEY).toLowerCase();
            String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY).toLowerCase();

            if (puCourseCode.equals(puCourseInput.toLowerCase()) && nusCourseCode.equals(nusCourseInput.toLowerCase())) {
                return true;
            }
        }

        System.out.println("Invalid course mapping!");
        displayAvailableMappings(courses, pu);
        return false;
    }

    private void displayAvailableMappings(JsonArray courses, String pu) {
        System.out.println("The available mappings for " + pu + " are:");
        System.out.println(LINE_SEPARATOR);

        for (int i = 0; i < courses.size(); i++) {
            JsonObject course = courses.getJsonObject(i);
            String puCourseCode = course.getString(PU_COURSE_CODE_KEY).toLowerCase();
            String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY).toLowerCase();
            String nusCourseName = course.getString(NUS_COURSE_NAME_KEY).toLowerCase();
            String puCourseName = course.getString(PU_COURSE_NAME_KEY).toLowerCase();

            System.out.println(nusCourseCode + " " + nusCourseName + " | " + puCourseCode
                    + " " + puCourseName + System.lineSeparator());
        }
        System.out.println(LINE_SEPARATOR);
    }

    public JsonArray getPUCourseList(String pu, JsonObject jsonObject) {
        JsonArray courses;
        logger.log(Level.INFO, Logs.FIND_PARTNER_UNIVERSITY);
        String matchPu = jsonObject.keySet().stream().filter(key -> key.equalsIgnoreCase(pu)).findFirst().orElse(null);

        logger.log(Level.INFO, Logs.UNIVERSITY_FOUND);
        if (matchPu != null) {
            logger.log(Level.INFO, Logs.RETRIEVE_COURSE_LIST);
            courses = jsonObject.getJsonObject(matchPu).getJsonArray(COURSES_ARRAY_LABEL);
        } else {
            displayPartnerUniversities();
            throw new IllegalArgumentException(Logs.INVALID_UNIVERSITY_INPUT);
        }
        return courses;
    }

    private void displayPartnerUniversities() {
        logger.log(Level.INFO, Logs.INVALID_UNIVERSITY_INPUT);
        System.out.println(Logs.INVALID_UNIVERSITY_INPUT);

        logger.log(Level.INFO, Logs.DISPLAY_PARTNER_UNIVERSITIES);
        System.out.println(LINE_SEPARATOR);
        System.out.println(LIST_RELEVANT_PU);
        System.out.println(LINE_SEPARATOR);
    }

    public boolean isValidInput(String nusCourseInput, String pu, String puCourseInput, JsonObject jsonObject) {

        assert nusCourseInput != null : "NUS course should not be empty";
        assert pu != null : "Partner university should not be empty";
        assert puCourseInput != null : "Partner university course should not be empty";
        assert jsonObject != null : "JSON object should not be empty";

        logger.log(Level.INFO, Logs.CHECK_UNIVERSITY);
        JsonArray courses = getPUCourseList(pu, jsonObject);

        logger.log(Level.INFO, Logs.CHECK_COURSE_MAPPING);
        return isValidCourseMapping(nusCourseInput, puCourseInput, courses, pu);
    }
}
