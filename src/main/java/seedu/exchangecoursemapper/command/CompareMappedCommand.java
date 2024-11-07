package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.courses.Course;
import seedu.exchangecoursemapper.exception.Exception;
import seedu.exchangecoursemapper.storage.CourseRepository;
import seedu.exchangecoursemapper.storage.Storage;
import seedu.exchangecoursemapper.ui.UI;

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


public class CompareMappedCommand extends CheckInformationCommand {

    private static final Logger logger = Logger.getLogger(CompareMappedCommand.class.getName());
    private static final CourseRepository courseRepository = new CourseRepository();
    private static final UI ui = new UI();
    private final Storage storage;

    public CompareMappedCommand(Storage storage) {
        assert storage != null : NULL_STORAGE;
        this.storage = storage;
        logger.log(Level.INFO, INIT_STORAGE_COMPARE_MAPPED);
    }

    private boolean isValidUniversity(String universityName, JsonObject databaseJson) {
        Set<String> universityKeys = databaseJson.keySet(); // Get all university names from the JSON keys
        return universityKeys.contains(universityName);
    }

    @Override
    public void execute(String userInput) {
        logger.log(Level.INFO, EXECUTE_COMPARE_MAPPED);

        if(!courseRepository.isFileValid()){
            return;
        }

        String[] inputs = userInput.split("pu/");
        if (inputs.length != 3) {
            ui.printInvalidInputFormat();
            return;
        }

        String university1 = inputs[1].trim();
        String university2 = inputs[2].trim();

        // Load the database JSON
        JsonObject databaseJson;
        try {
            databaseJson = createJsonObject();
        } catch (IOException e) {
            System.out.println(Exception.fileReadError());
            return;
        }

        // Validate universities
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

    private List<Course> filterModulesByUniversity(List<Course> modules, String university) {
        logger.log(Level.INFO, FILTERING_UNIVERSITY + university);
        return modules.stream()
                .filter(module -> module.getPartnerUniversity().equals(university))
                .toList();
    }

    private Set<String> extractCourseCodes(List<Course> modules) {
        logger.log(Level.INFO, COURSE_CODE_EXTRACTION);
        return modules.stream()
                .map(Course::getNusCourseCode)
                .collect(Collectors.toSet());
    }

    private Set<String> getCommonCourseCodes(Set<String> codes1, Set<String> codes2) {
        logger.log(Level.INFO, COMMON_COURSE_CODES);
        Set<String> commonCodes = new HashSet<>(codes1);
        commonCodes.retainAll(codes2);
        return commonCodes;
    }

    private Set<String> getUniqueCourseCodes(Set<String> allCodes, Set<String> commonCodes) {
        logger.log(Level.INFO, UNIQUE_COURSE_CODES);
        Set<String> uniqueCodes = new HashSet<>(allCodes);
        uniqueCodes.removeAll(commonCodes);
        return uniqueCodes;
    }

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
        ui.printCommonMappings(university1,university2,commonCourseCodes,uni1Modules,uni2Modules);
        displayUniqueMappings(university1, uni1Modules, uni1UniqueCourseCodes);
        displayUniqueMappings(university2, uni2Modules, uni2UniqueCourseCodes);
        logger.log(Level.INFO, DISPLAY_COMPLETE + university1 + " and " + university2);
    }

    private void displayUniqueMappings(String university, List<Course> modules, Set<String> uniqueCourseCodes) {

        assert university != null : UNIVERSITY_NOT_NULL;
        assert modules != null : UNI_MODULES_NOT_NULL;
        assert uniqueCourseCodes != null : UNIQUE_CODES_NOT_NULL;

        logger.log(Level.INFO, DISPLAYING_UNIQUE_MAPPINGS + university);
        ui.printUniqueMappings(university,modules,uniqueCourseCodes);
    }
}
