package seedu.exchangecoursemapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exchangecoursemapper.command.FilterCoursesCommand;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    public void parseFilterCommand_inputWithOneCourse_expectSeparatedInput() {
        String userInput = "filter cs3241";
        String[] descriptionSubstrings = filterCoursesCommand.parseFilterCommand(userInput);
        assertArrayEquals(new String[]{"filter", "cs3241"}, descriptionSubstrings);
    }

    @Test
    public void parseFilterCommand_inputWithTwoCourses_expectException() {
        String userInput = "filter ee2026 cs3241";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            filterCoursesCommand.parseFilterCommand(userInput);
        });
        assertEquals("Please note that we can only filter for only one NUS Course!",
                e.getMessage());
    }

    @Test
    public void parseDeleteCommand_inputWithNoIndexes_expectException() {
        String userInput = "filter ";
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            filterCoursesCommand.parseFilterCommand(userInput);
        });
        assertEquals("Please provide the course code you would like to search for.",
                e.getMessage());
    }

    @Test
    public void getNusCourseCode_inputWithCourseCode_expectNusCourseCode() {
        String userInput = "filter cs3244";
        String[] descriptionSubstrings = filterCoursesCommand.parseFilterCommand(userInput);
        String nusCourseCode = filterCoursesCommand.getNusCourseCode(descriptionSubstrings);
        assertEquals("cs3244", nusCourseCode);
    }

    @Test
    public void getNusCourseCode_inputWithNonSocCourseCode_expectNusCourseCode() {
        String userInput = "filter gess1000";
        String[] descriptionSubstrings = filterCoursesCommand.parseFilterCommand(userInput);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            filterCoursesCommand.getNusCourseCode(descriptionSubstrings);
        });
        assertEquals("We can only filter for CS/CG/EE/BT/IS coded courses!", e.getMessage());
    }

    @Test
    public void getNusCourseCode_inputWithInvalidNusCourseCode_expectNusCourseCode() {
        String userInput = "filter eeeeeeeeeeee";
        String[] descriptionSubstrings = filterCoursesCommand.parseFilterCommand(userInput);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            filterCoursesCommand.getNusCourseCode(descriptionSubstrings);
        });
        assertEquals("Please follow this format for the NUS SoC course code input (not case-sensitive):\n" +
                "CS/EE/BT/IS followed by a 4-digit sequence e.g CS3241\n" +
                "Some courses may end with a character too e.g. CS1010J", e.getMessage());
    }

    @Test
    public void displayMappableCourses_mappableNusCourse_expectMappableCoursesList() throws FileNotFoundException {
        JsonObject jsonObject = createDatabaseJsonObject();

        String nusCourseCode = "cs3244";
        filterCoursesCommand.displayMappableCourses(jsonObject, nusCourseCode);
        String expectedOutput = """
                -----------------------------------------------------
                Filter results for cs3244:
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
                -----------------------------------------------------
                End of filter results
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void displayMappableCourses_nonMappableNusCourse_expectNoMappableCourses() throws FileNotFoundException {
        JsonObject jsonObject = createDatabaseJsonObject();

        String nusCourseCode = "ee2026";
        filterCoursesCommand.displayMappableCourses(jsonObject, nusCourseCode);
        String expectedOutput = """
                -----------------------------------------------------
                Filter results for ee2026:
                -----------------------------------------------------
                -----------------------------------------------------
                No mappable courses found for the given course code.
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_oneNusCourseCode_expectMappableCoursesList() {
        String input = "filter CS3241";
        filterCoursesCommand.execute(input);
        String expectedOutput = """
                -----------------------------------------------------
                Filter results for cs3241:
                -----------------------------------------------------
                Partner University: The University of Melbourne
                Partner University Course Code: COMP30019
                -----------------------------------------------------
                Partner University: The Australian National University
                Partner University Course Code: COMP4610
                -----------------------------------------------------
                -----------------------------------------------------
                End of filter results
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput), normalizeLineEndings(actualOutput));
    }

    @Test
    public void execute_twoNusCourseCodes_expectErrorMessage() {
        String userInput = "filter CS3241 Ee2026";
        filterCoursesCommand.execute(userInput);
        String expectedOutput = """
                -----------------------------------------------------
                Please note that we can only filter for only one NUS Course!
                -----------------------------------------------------
                """;
        String actualOutput = outputStreamCaptor.toString();
        assertEquals(normalizeLineEndings(expectedOutput),
                normalizeLineEndings(actualOutput));
    }

    public JsonObject createDatabaseJsonObject() throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("./data/database.json"));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        return jsonObject;
    }

    String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n").trim();
    }
}
