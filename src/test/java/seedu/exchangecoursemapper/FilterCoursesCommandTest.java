package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.FilterCoursesCommand;
import seedu.exchangecoursemapper.exception.ExchangeCourseMapperException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilterCoursesCommandTest {

    private FilterCoursesCommand filterCoursesCommand;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        filterCoursesCommand = new FilterCoursesCommand();
    }

    @Test
    public void getNusCourseCode_inputWithCourseCode_expectNusCourseCode() throws ExchangeCourseMapperException {
        String userInput = "filter cs3244";
        String nusCourseCode = filterCoursesCommand.getNusCourseCode(userInput);
        assertEquals("cs3244", nusCourseCode);
    }

    @Test
    public void getNusCourseCode_inputWithNoCourseCode_expectException() {
        String userInput = "filter";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            filterCoursesCommand.getNusCourseCode(userInput);
        });
        assertEquals("Please provide the course code you would like to search for.", e.getMessage());
    }

    @Test
    public void displayMappableCourses_mappableNusCourse_expectMappableCoursesList() throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String nusCourseCode = "cs3244";
        filterCoursesCommand.displayMappableCourses(jsonObject, nusCourseCode);
        String expectedOutput = """
                Partner University: Chulalongkorn University
                Partner University Course Code: 2603637
                -----------------------------------------------------
                Partner University: The University of Melbourne
                Partner University Course Code: COMP30027
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP3670
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP4620
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP4620
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void displayMappableCourses_mappableNusCourseInUpperCase_expectMappableCoursesList()
            throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String nusCourseCode = "CS3244";
        filterCoursesCommand.displayMappableCourses(jsonObject, nusCourseCode);
        String expectedOutput = """
                Partner University: Chulalongkorn University
                Partner University Course Code: 2603637
                -----------------------------------------------------
                Partner University: The University of Melbourne
                Partner University Course Code: COMP30027
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP3670
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP4620
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP4620
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void displayMappableCourses_nonMappableNusCourse_expectNoMappableCourses() throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String nusCourseCode = "ee2026";

        filterCoursesCommand.displayMappableCourses(jsonObject, nusCourseCode);

        String actualOutput = outputStreamCaptor.toString();
        assertEquals("No courses found for the given course code.",
                normalizeLineEndings(actualOutput));
    }

    @Test
    public void displayMappableCourses_nonMappableNusCourseInUpperCase_expectNoMappableCourses()
            throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        String nusCourseCode = "EE2026";

        filterCoursesCommand.displayMappableCourses(jsonObject, nusCourseCode);

        String actualOutput = outputStreamCaptor.toString();
        assertEquals("No courses found for the given course code.",
                normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_oneNusCourseCode_expectMappableCoursesList() {
        String input = "filter CS3241";

        filterCoursesCommand.execute(input);
        String expectedOutput = """
                Partner University: Chulalongkorn University
                Partner University Course Code: ICSE2190479
                -----------------------------------------------------
                Partner University: The University of Melbourne
                Partner University Course Code: COMP30019
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP4610
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_twoNusCourseCodes_expectException() {
        String userInput = "filter CS3241 Ee2026";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            filterCoursesCommand.execute(userInput);
        });
        assertEquals("Please note that we can only filter for only one NUS Course!",
                e.getMessage());
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
