package seedu.exchangecoursemapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.PersonalTrackerCommand;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.parser.CourseValidator;
import seedu.exchangecoursemapper.storage.Storage;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.exchangecoursemapper.constants.JsonKey.COURSES_ARRAY_LABEL;


public class CourseValidatorTest {
    private CourseValidator courseValidator;
    private JsonObject jsonObject;

    @BeforeEach
    public void setUp() throws IOException {
        courseValidator = new CourseValidator();

        PersonalTrackerCommand personalTrackerCommand = new PersonalTrackerCommand() {
            @Override
            public void execute(String userInput, Storage storage) {
            }
        };
        jsonObject = personalTrackerCommand.createJsonObject();
    }

    @Test
    void isValidInput_normalCaseInput_success() {
        String nusCourseInput = "CS2102";
        String pu = "The University of Melbourne";
        String puCourseInput = "INFO20003";
        boolean isValidInput = courseValidator.isValidInput(nusCourseInput,pu,puCourseInput,jsonObject);
        assertTrue(isValidInput);
    }

    @Test
    void isValidInput_smallCaseInput_success() {
        String nusCourseInput = "cs2102";
        String pu = "the university of melbourne";
        String puCourseInput = "info20003";
        boolean isValidInput = courseValidator.isValidInput(nusCourseInput,pu,puCourseInput,jsonObject);
        assertTrue(isValidInput);
    }

    @Test
    void getPUCourseList_universityAvailable_success() {
        String pu = "the university of melbourne";
        String matchPu = jsonObject.keySet()
                .stream()
                .filter(key -> key.equalsIgnoreCase(pu))
                .findFirst()
                .orElse(null);

        JsonArray successCourseList = jsonObject.getJsonObject(matchPu).getJsonArray(COURSES_ARRAY_LABEL);
        JsonArray puCourseList = courseValidator.getPUCourseList(pu,jsonObject);
        assertEquals(successCourseList,puCourseList);
    }

    @Test
    void getPUCourseList_invalidUniversity_illegalArgumentException() {
        String pu = "ABC University";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()
                -> courseValidator.getPUCourseList(pu,jsonObject));
        assertEquals(Logs.INVALID_UNIVERSITY_INPUT, exception.getMessage());
    }

    @Test
    void isValidCourseMapping_invalidPUCourse_returnFalse(){
        String nusCourseInput = "cs2102";
        String pu = "The University of Melbourne";
        String puCourseInput = "info21992";
        JsonArray courses = courseValidator.getPUCourseList(pu,jsonObject);
        boolean isValidCourseMapping = courseValidator.isValidCourseMapping
                (nusCourseInput,puCourseInput,courses,pu);
        assertFalse(isValidCourseMapping);
    }

    @Test
    void isValidCourseMapping_invalidNUSCourse_returnFalse(){
        String nusCourseInput = "cs2101";
        String pu = "The University of Melbourne";
        String puCourseInput = "info20003";
        JsonArray courses = courseValidator.getPUCourseList(pu,jsonObject);
        boolean isValidCourseMapping = courseValidator.isValidCourseMapping
                (nusCourseInput,puCourseInput,courses,pu);
        assertFalse(isValidCourseMapping);
    }

    @Test
    void isValidCourseMapping_validCourseMapping_returnTrue(){
        String nusCourseInput = "cs2102";
        String pu = "The University of Melbourne";
        String puCourseInput = "info20003";
        JsonArray courses = courseValidator.getPUCourseList(pu,jsonObject);
        boolean isValidCourseMapping = courseValidator.isValidCourseMapping
                (nusCourseInput,puCourseInput,courses,pu);
        assertTrue(isValidCourseMapping);
    }
}
