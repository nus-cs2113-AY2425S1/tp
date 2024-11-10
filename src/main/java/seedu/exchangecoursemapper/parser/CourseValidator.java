package seedu.exchangecoursemapper.parser;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.constants.Messages;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.ui.UI;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.COURSES_ARRAY_LABEL;
import static seedu.exchangecoursemapper.constants.Assertions.EMPTY_NUS_COURSE_WARNING;
import static seedu.exchangecoursemapper.constants.Assertions.EMPTY_PU_WARNING;
import static seedu.exchangecoursemapper.constants.Assertions.EMPTY_PU_COURSE_WARNING;
import static seedu.exchangecoursemapper.constants.Assertions.EMPTY_JSON_OBJECT_WARNING;

/** A class containing all the relevant methods for course mapping validation. */
public class CourseValidator {

    private static final Logger logger = Logger.getLogger(CourseValidator.class.getName());

    private UI ui = new UI();;

    /**
     * Returns true if the user's course mapping is valid; otherwise, returns false.
     * The user's course mapping is checked against the JsonArray containing all the relevant course mapping
     * from the getPuCourseList() method.
     *
     * @param nusCourseInput a string containing the user's NUS course input.
     * @param puCourseInput  a string containing the user's PU course input.
     * @param courses a JsonArray containing all the relevant course mappings for the particular PU.
     * @param pu a string containing the user's PU input.
     * @return true if the user's course mapping is valid; otherwise, returns false.
     */
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
            //System.out.println(puCourseCode + " " + nusCourseCode);

            if (puCourseCode.equals(puCourseInput.toLowerCase())
                    && nusCourseCode.equals(nusCourseInput.toLowerCase())) {
                return true;
            }
        }

        System.out.println(Messages.INVALID_COURSE_MAPPING);
        ui.displayAvailableMappings(courses, pu);
        return false;
    }

    /**
     * Returns a JsonArray containing all the course mappings from the user's PU should the PU
     * is found within the JsonObject database. The method would throw an exception when the PU
     * is not found within the database.
     *
     * @param pu a string containing the user's PU input.
     * @param jsonObject a JSONObject containing all the course mappings from
     *                   all the partner universities in our database.
     * @return a JsonArray containing all course mappings for the user's input PU.
     */
    public JsonArray getPUCourseList(String pu, JsonObject jsonObject) {
        JsonArray courses;
        logger.log(Level.INFO, Logs.FIND_PARTNER_UNIVERSITY);
        String matchPu = jsonObject
                .keySet()
                .stream()
                .filter(key -> key.equalsIgnoreCase(pu))
                .findFirst()
                .orElse(null);

        logger.log(Level.INFO, Logs.UNIVERSITY_FOUND);
        if (matchPu != null) {
            logger.log(Level.INFO, Logs.RETRIEVE_COURSE_LIST);
            courses = jsonObject.getJsonObject(matchPu).getJsonArray(COURSES_ARRAY_LABEL);
        } else {
            ui.displayPartnerUniversities();
            throw new IllegalArgumentException(Logs.INVALID_UNIVERSITY_INPUT);
        }
        return courses;
    }


    /**
     * Returns true if the course mapping is valid; otherwise, returns false.
     *
     * @param nusCourseInput A string representing the user's input for the NUS course.
     * @param pu A string representing the user's PU input.
     * @param puCourseInput A string representing the user's input for PU course.
     * @param jsonObject A JsonObject containing the course mapping database for validation.
     * @return true if the course mapping is valid; otherwise, returns false.
     */
    public boolean isValidInput(String nusCourseInput, String pu,
                                String puCourseInput, JsonObject jsonObject) {

        assert nusCourseInput != null : EMPTY_NUS_COURSE_WARNING;
        assert pu != null : EMPTY_PU_WARNING;
        assert puCourseInput != null : EMPTY_PU_COURSE_WARNING;
        assert jsonObject != null : EMPTY_JSON_OBJECT_WARNING;

        logger.log(Level.INFO, Logs.CHECK_UNIVERSITY);
        JsonArray courses = getPUCourseList(pu, jsonObject);

        logger.log(Level.INFO, Logs.CHECK_COURSE_MAPPING);
        return isValidCourseMapping(nusCourseInput, puCourseInput, courses, pu);
    }
}
