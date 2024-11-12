package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.storage.CourseRepository;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;
import seedu.exchangecoursemapper.parser.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonObject;

import static seedu.exchangecoursemapper.constants.Assertions.UNIVERSITY1_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.UNIVERSITY2_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.COMMON_CODES_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.UNI1_MODULES_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.UNI2_MODULES_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.UNI1_UNIQUE_CODES_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.UNI2_UNIQUE_CODES_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.UNIVERSITY_NOT_NULL ;
import static seedu.exchangecoursemapper.constants.Assertions.UNIQUE_CODES_NOT_NULL ;
import static seedu.exchangecoursemapper.constants.Assertions.UNI_MODULES_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.LOADED_LIST_NOT_NULL;
import static seedu.exchangecoursemapper.constants.Assertions.NULL_STORAGE;
import static seedu.exchangecoursemapper.constants.Logs.EXECUTE_COMPARE_MAPPED;
import static seedu.exchangecoursemapper.constants.Logs.INIT_STORAGE_COMPARE_MAPPED;
import static seedu.exchangecoursemapper.constants.Logs.LOADED_MODULES;
import static seedu.exchangecoursemapper.constants.Logs.FILTERING_UNIVERSITY;
import static seedu.exchangecoursemapper.constants.Logs.COURSE_CODE_EXTRACTION;
import static seedu.exchangecoursemapper.constants.Logs.COMMON_COURSE_CODES;
import static seedu.exchangecoursemapper.constants.Logs.UNIQUE_COURSE_CODES;
import static seedu.exchangecoursemapper.constants.Logs.DISPLAYING_RESULTS;
import static seedu.exchangecoursemapper.constants.Logs.DISPLAY_COMPLETE;
import static seedu.exchangecoursemapper.constants.Logs.DISPLAYING_UNIQUE_MAPPINGS;


/**
 * A command that compares the course mappings between two partner universities.
 * This command validates universities, checks for duplicates, and displays common and unique course mappings.
 */
public class CompareMappedCommand extends CheckInformationCommand {

    private static final Logger logger = Logger.getLogger(CompareMappedCommand.class.getName());
    private static final CourseRepository courseRepository = new CourseRepository();
    private static final Parser parser = new Parser();
    private static final UI ui = new UI();
    private final Storage storage;

    /**
     * Constructs a CompareMappedCommand with the specified storage.
     *
     * @param storage The storage to be used for loading and saving courses.
     */
    public CompareMappedCommand(Storage storage) {
        assert storage != null : NULL_STORAGE;
        this.storage = storage;
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, INIT_STORAGE_COMPARE_MAPPED);
    }

    /**
     * Validates if the provided university name exists in the database JSON.
     *
     * @param universityName The name of the university to validate.
     * @param databaseJson   The JSON object containing valid university names.
     * @return true if the university name is valid; false otherwise.
     */
    private boolean isValidUniversity(String universityName, JsonObject databaseJson) {
        Set<String> universityKeys = databaseJson.keySet().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        return universityKeys.contains(universityName.toLowerCase());
    }

    /**
     * Executes the CompareMappedCommand by validating user input, checking for duplicates,
     * loading courses, filtering by university, and displaying comparison results.
     *
     * @param userInput The user input containing the names of two universities to compare.
     */
    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, EXECUTE_COMPARE_MAPPED);

        if (!courseRepository.isFileValid()) {
            return;
        }

        courseRepository.removeDuplicateEntries();

        String[] inputs = userInput.split("pu/");
        if (inputs.length != 3) {
            ui.printInvalidInputFormat();
            return;
        }

        String university1 = parser.parsePUAbbreviations(inputs[1].trim().toLowerCase());
        String university2 = parser.parsePUAbbreviations(inputs[2].trim().toLowerCase());

        JsonObject databaseJson;
        try {
            databaseJson = createJsonObject();
        } catch (IOException e) {
            System.out.println(Exception.fileReadError());
            return;
        }

        if (!isValidUniversity(university1, databaseJson)) {
            ui.printUnknownUniversity(university1);
            return;
        }
        if (!isValidUniversity(university2, databaseJson)) {
            ui.printUnknownUniversity(university2);
            return;
        }

        List<Course> allModules = storage.loadAllCourses();
        assert allModules != null : LOADED_LIST_NOT_NULL;
        logger.log(Level.INFO, LOADED_MODULES);

        List<Course> uni1Modules = filterModulesByUniversity(allModules, university1);
        List<Course> uni2Modules = filterModulesByUniversity(allModules, university2);

        Set<String> uni1CourseCodes = extractCourseCodes(uni1Modules);
        Set<String> uni2CourseCodes = extractCourseCodes(uni2Modules);

        Set<String> commonCourseCodes = getCommonCourseCodes(uni1CourseCodes, uni2CourseCodes);
        Set<String> uni1UniqueCourseCodes = getUniqueCourseCodes(uni1CourseCodes, commonCourseCodes);
        Set<String> uni2UniqueCourseCodes = getUniqueCourseCodes(uni2CourseCodes, commonCourseCodes);

        displayComparisonResults(university1, university2, commonCourseCodes, uni1Modules, uni2Modules,
                uni1UniqueCourseCodes, uni2UniqueCourseCodes);
    }

    /**
     * Filters courses by university name.
     *
     * @param modules    The list of all courses.
     * @param university The name of the university to filter by.
     * @return A list of courses that match the specified university.
     */
    private List<Course> filterModulesByUniversity(List<Course> modules, String university) {
        logger.log(Level.INFO, FILTERING_UNIVERSITY + university);
        return modules.stream()
                .filter(module -> module.getPartnerUniversity().equals(university))
                .toList();
    }

    /**
     * Extracts course codes from a list of courses.
     *
     * @param modules The list of courses.
     * @return A set of course codes for the specified courses.
     */
    private Set<String> extractCourseCodes(List<Course> modules) {
        logger.log(Level.INFO, COURSE_CODE_EXTRACTION);
        return modules.stream()
                .map(Course::getNusCourseCode)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves common course codes between two sets of course codes.
     *
     * @param codes1 The first set of course codes.
     * @param codes2 The second set of course codes.
     * @return A set of common course codes.
     */
    private Set<String> getCommonCourseCodes(Set<String> codes1, Set<String> codes2) {
        logger.log(Level.INFO, COMMON_COURSE_CODES);
        Set<String> commonCodes = new HashSet<>(codes1);
        commonCodes.retainAll(codes2);
        return commonCodes;
    }

    /**
     * Retrieves unique course codes by removing common codes from all codes.
     *
     * @param allCodes   The set of all course codes.
     * @param commonCodes The set of common course codes.
     * @return A set of unique course codes.
     */
    private Set<String> getUniqueCourseCodes(Set<String> allCodes, Set<String> commonCodes) {
        logger.log(Level.INFO, UNIQUE_COURSE_CODES);
        Set<String> uniqueCodes = new HashSet<>(allCodes);
        uniqueCodes.removeAll(commonCodes);
        return uniqueCodes;
    }

    /**
     * Displays the comparison results for common and unique mappings between two universities.
     *
     * @param university1          The first university.
     * @param university2          The second university.
     * @param commonCourseCodes    The set of common course codes between the universities.
     * @param uni1Modules          The courses from the first university.
     * @param uni2Modules          The courses from the second university.
     * @param uni1UniqueCourseCodes The unique course codes for the first university.
     * @param uni2UniqueCourseCodes The unique course codes for the second university.
     */
    private void displayComparisonResults(String university1, String university2, Set<String> commonCourseCodes,
                                          List<Course> uni1Modules, List<Course> uni2Modules,
                                          Set<String> uni1UniqueCourseCodes, Set<String> uni2UniqueCourseCodes) {

        assert university1 != null : UNIVERSITY1_NOT_NULL;
        assert university2 != null : UNIVERSITY2_NOT_NULL;
        assert commonCourseCodes != null : COMMON_CODES_NOT_NULL;
        assert uni1Modules != null : UNI1_MODULES_NOT_NULL;
        assert uni2Modules != null : UNI2_MODULES_NOT_NULL;
        assert uni1UniqueCourseCodes != null : UNI1_UNIQUE_CODES_NOT_NULL;
        assert uni2UniqueCourseCodes != null : UNI2_UNIQUE_CODES_NOT_NULL;

        logger.log(Level.INFO, DISPLAYING_RESULTS + university1 + " and " + university2);
        ui.printCommonMappings(university1, university2, commonCourseCodes, uni1Modules, uni2Modules);
        displayUniqueMappings(university1, uni1Modules, uni1UniqueCourseCodes);
        displayUniqueMappings(university2, uni2Modules, uni2UniqueCourseCodes);
        logger.log(Level.INFO, DISPLAY_COMPLETE + university1 + " and " + university2);
    }

    /**
     * Displays the unique mappings for a specified university.
     *
     * @param university       The name of the university.
     * @param modules          The list of courses associated with the university.
     * @param uniqueCourseCodes The set of unique course codes for the university.
     */
    private void displayUniqueMappings(String university, List<Course> modules, Set<String> uniqueCourseCodes) {

        assert university != null : UNIVERSITY_NOT_NULL;
        assert modules != null : UNI_MODULES_NOT_NULL;
        assert uniqueCourseCodes != null : UNIQUE_CODES_NOT_NULL;

        logger.log(Level.INFO, DISPLAYING_UNIQUE_MAPPINGS + university);
        ui.printUniqueMappings(university, modules, uniqueCourseCodes);
    }
}

