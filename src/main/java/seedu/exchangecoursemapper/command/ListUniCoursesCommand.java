package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Assertions;
import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.exception.UnknownUniversityException;
import seedu.exchangecoursemapper.parser.Parser;
import seedu.exchangecoursemapper.ui.UI;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Messages.LINE_SEPARATOR;

public class ListUniCoursesCommand extends CheckInformationCommand {
    private static final Logger logger = Logger.getLogger(ListUniCoursesCommand.class.getName());
    private static final UI ui = new UI();
    private static final Parser parser = new Parser();

    /**
     * Executes the command to retrieve and list courses for a specified PU based on user input.
     * This method reads a JSON file to create a JSON Object, then retrieves
     * the PU name from user input and then list out all the relevant course details
     * from that specified university.
     *
     * @param userInput A string containing the input from the user.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        try {
            JsonObject jsonObject = fetchData();
            String puName = getPuName(userInput);
            getUniCourses(jsonObject, puName);
        } catch (IOException e) {
            handleFileReadError();
        } catch (UnknownUniversityException e) {
            handleUnknownUniversityError(e);
        } catch (IllegalArgumentException e) {
            handleIllegalArgumentError(e);
        }
    }

    /**
     * Fetches data by creating and returning a JsonObject instance.
     * Logs the success of reading the JSON file and performs assertions to check that
     * the JSON object is not null or empty.
     *
     * @return A JsonObject containing the data from the JSON file.
     * @throws IOException if an I/O error occurs while creating the JsonObject.
     */
    private JsonObject fetchData() throws IOException {
        JsonObject jsonObject = super.createJsonObject();
        logger.log(Level.INFO, Logs.SUCCESS_READ_JSON_FILE);
        assert jsonObject != null : Assertions.NULL_JSON_FILE;
        assert !jsonObject.isEmpty() : Assertions.EMPTY_JSON_FILE;
        return jsonObject;
    }

    /**
     * Returns a string containing the name of the partner university.
     *
     * @param userInput A string containing an input from the user.
     * @return A string containing the name of a partner university.
     */
    public String getPuName (String userInput) {
        assert userInput != null : Assertions.EMPTY_USER_INPUT;

        String puName = userInput.replaceFirst("list courses", "").trim();
        logger.log(Level.INFO, Logs.EXTRACT_PU_NAME);

        if (puName.isEmpty()) {
            logger.log(Level.WARNING, Logs.NO_PU_NAME);
            throw new IllegalArgumentException(Exception.emptyUniversityName());
        }
        return puName;
    }

    /**
     * Retrieves and list out the courses offered by the specified partner university.
     * This method searches for the specified university within the provided JsonObject.
     * If the university is found, it will list out the courses it offers.
     * If not, it will throw an exception.
     *
     * @param jsonObject The {@link JsonObject} containing the details of all university.
     * @param puName The name of partner university in interest.
     * @throws UnknownUniversityException If the university is not found within the JsonObject.
     */
    public void getUniCourses (JsonObject jsonObject, String puName) throws UnknownUniversityException {
        assert jsonObject != null : Assertions.NULL_JSON_FILE;
        assert puName != null : Assertions.EMPTY_PU_NAME;

        String convertedPuName = parser.parsePUAbbreviations(puName).toLowerCase();
        Set<String> universityNames = jsonObject.keySet();

        logger.log(Level.INFO, Logs.SEARCH_UNIVERSITY, puName);
        String universityName = findUniversityName(universityNames, convertedPuName);

        if (universityName == null) {
            handleUnknownUniversity(puName);
        } else {
            listCourses(jsonObject, universityName);
        }
    }

    /**
     * Searches for a university name in the set of universities in the data and returns it.
     * If no match is found, it returns null.
     *
     * @param universityNames A set of university names in the data.
     * @param convertedPuName The lower case name of the university to be searched for.
     * @return The matching university name.
     */
    private String findUniversityName(Set<String> universityNames, String convertedPuName) {
        for (String universityName : universityNames) {
            assert universityName != null && !universityName.isEmpty();
            if (universityName.toLowerCase().equals(convertedPuName)) {
                logger.log(Level.INFO, Logs.UNIVERSITY_FOUND, universityName);
                return universityName;
            }
        }
        return null;
    }

    /**
     * Throws an exception if the university is unknown.
     *
     * @param puName The name of the partner university.
     * @throws UnknownUniversityException An exception to be thrown when the given puName is not in the database.
     */
    private void handleUnknownUniversity(String puName) throws UnknownUniversityException {
        logger.log(Level.WARNING, Logs.UNKNOWN_UNIVERSITY, puName);
        throw new UnknownUniversityException(Exception.unknownUniversity(puName));
    }

    /**
     * Lists the courses offered by a specific university.
     * This method retrieves the university object and its courses.
     * It then iterates through the courses and prints their details.
     *
     * @param jsonObject A {@link JsonObject} containing the details of all universities.
     * @param universityName The name of the university whose courses are to be listed.
     */
    private void listCourses(JsonObject jsonObject, String universityName) {
        JsonObject universityObject = getUniversityObject(jsonObject, universityName);
        JsonArray courseArray = getCourseArray(universityObject, universityName);

        logger.log(Level.INFO, Logs.LISTING_COURSES);
        iterateCourses(courseArray);
    }

    /**
     * Retrieves the university object from the provided JSON object.
     *
     * @param jsonObject A {@link JsonObject} containing all the universities.
     * @param universityName The name of the university whose object is to be retrieved.
     * @return A {@link JsonObject} representing the specific university.
     */
    private JsonObject getUniversityObject(JsonObject jsonObject, String universityName) {
        JsonObject universityObject = jsonObject.getJsonObject(universityName);
        assert universityObject != null : Assertions.NULL_UNIVERSITY_OBJECT;
        return universityObject;
    }

    /**
     * Returns a course array from the given universityObject.
     * This method fetches the {@link JsonArray} of courses from the {@link JsonObject}
     * universityObject representing the university.
     *
     * @param universityObject A {@link JsonObject} that represents the university containing a list of courses.
     * @param universityName The name of the university.
     * @return A {@link JsonArray} containing the courses offered by the university.
     */
    private JsonArray getCourseArray(JsonObject universityObject, String universityName) {
        JsonArray courseArray = universityObject.getJsonArray("courses");
        if (courseArray == null) {
            logger.log(Level.WARNING, Logs.NO_COURSES_FOUND);
            throw new IllegalArgumentException(Exception.noCourseAvailable(universityName));
        }
        return courseArray;
    }

    /**
     * Iterates through the courses offered by PU and prints the course details.
     *
     * @param courseArray A {@link JsonArray} that contains a list of courses as JsonObjects.
     */
    private void iterateCourses(JsonArray courseArray) {
        for (int i = 0; i < courseArray.size(); i++) {
            JsonObject courseObject = courseArray.getJsonObject(i);
            assert courseObject != null : Assertions.NO_COURSE_OBJECT;
            ui.printListUniCoursesCommand(courseObject);
        }
        ui.printTheEnd();
    }

    private static void handleIllegalArgumentError(IllegalArgumentException e) {
        logger.log(Level.WARNING, Logs.NULL_UNIVERSITY);
        System.err.println(e.getMessage());
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleUnknownUniversityError(UnknownUniversityException e) {
        logger.log(Level.WARNING, Logs.UNKNOWN_UNIVERSITY, e.getMessage());
        System.err.println(e.getMessage());
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleFileReadError() {
        logger.log(Level.WARNING, Logs.FAILURE_READ_JSON_FILE);
        System.err.println(Exception.fileReadError());
        System.out.println(LINE_SEPARATOR);
    }
}
