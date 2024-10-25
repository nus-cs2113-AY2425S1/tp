package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.constants.Assertions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.json.JsonArray;

import static seedu.exchangecoursemapper.constants.JsonKey.COURSES_ARRAY_LABEL;
import static seedu.exchangecoursemapper.constants.JsonKey.NUS_COURSE_CODE_KEY;
import static seedu.exchangecoursemapper.constants.JsonKey.PU_COURSE_CODE_KEY;


public class AddCoursesCommand extends PersonalTrackerCommand {

    private static final Logger logger = Logger.getLogger(AddCoursesCommand.class.getName());

    @Override
    public void execute(String userInput, Storage storage) {
        try {
            JsonObject jsonObject = super.createJsonObject();      //to add to isValidInputChecker
            logger.log(Level.INFO, Logs.TRIM_STRING);
            String description = trimString(userInput);
            logger.log(Level.INFO, Logs.PARSE_ADD_COMMANDS);
            String[] descriptionSubstrings = parseAddCommand(description);

            assert descriptionSubstrings.length == 3: Assertions.MISSING_FIELDS;
            logger.log(Level.INFO, Logs.EXTRACT_COURSES);
            String nusCourse = descriptionSubstrings[0].trim();
            String pu = descriptionSubstrings[1].trim();
            String puCourse = descriptionSubstrings[2].trim();
            isValidInput(nusCourse,pu,puCourse,jsonObject);


            logger.log(Level.INFO, Logs.FORMAT);
            Course courseToStore = new Course(puCourse, nusCourse, pu);
            storage.addCourse(courseToStore);

            printAddMessage(courseToStore);

        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String trimString(String string) {
        String trimmedString = string.trim();

        assert !trimmedString.isEmpty(): Assertions.MISSING_USER_INPUT;
        String[] outputSubstrings = trimmedString.split(" ", 2);

        if (outputSubstrings.length < 2 || outputSubstrings[1].trim().isEmpty()) {
            logger.log(Level.WARNING, Logs.MISSING_INPUT_AFTER_KEYWORD);
            throw new IllegalArgumentException(Exception.noInputAfterAdd());
        }

        logger.log(Level.INFO, Logs.RETURN_TRIMMED_INPUT);
        return outputSubstrings[1];
    }

    public String[] parseAddCommand(String input) {

        input = input.replaceAll("(?i)/pu", "/pu")
                .replaceAll("(?i)/coursepu", "/coursepu")
                .trim().replaceAll(" +", " ");


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

    public boolean isValidInput(String nusCourseInput, String pu, String puCourseInput, JsonObject jsonObject){

        boolean isPuValid = jsonObject.keySet().stream()
                .anyMatch(key -> key.equalsIgnoreCase(pu));

        if (!isPuValid){
            System.out.println("Invalid University Input. The relevant universities are ");
            return false;
        }

        JsonArray courses = jsonObject.getJsonObject(pu).getJsonArray(COURSES_ARRAY_LABEL);
        for (int i = 0; i < courses.size(); i++) {
            JsonObject course = courses.getJsonObject(i);
            String puCourseCode = course.getString(PU_COURSE_CODE_KEY).toLowerCase();
            String nusCourseCode = course.getString(NUS_COURSE_CODE_KEY).toLowerCase();

            if (!nusCourseInput.equals(nusCourseCode)){
                System.out.println("Invalid partner university course code!");
                return false;
            }

            if (!puCourseInput.equals(puCourseCode)){
                System.out.println("Invalid nus course code!");
                return false;
            }
        }

        return true;
    }
}
