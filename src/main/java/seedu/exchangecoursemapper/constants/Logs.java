package seedu.exchangecoursemapper.constants;

public class Logs {
    public static final String EXECUTING_COMMAND  = "Executing command";
    public static final String JSON_FILE_CONTAINS_DATA = "Validated JSON data is not null and not empty.";
    public static final String SUCCESS_READ_JSON_FILE = "Successfully read JSON file";
    public static final String READING_JSON_FILE = "Attempting to read JSON data.";
    public static final String FAILURE_READ_JSON_FILE = "Failed to read JSON file";
    public static final String COMPLETE_EXECUTION  = "Execution complete";
    public static final String LIST_SCHOOLS_NAMES = "Displaying university names ...";
    public static final String LIST_MAPPABLE_COURSES = "Displaying mappable PU courses ...";
    public static final String POSSIBLE_NULL_JSON_KEY = "Encountered an empty or null university name.";
    public static final String NO_NUS_COURSE_CODE_FILTER = "No NUS course code provided to filter";
    public static final String FILTER_COURSES_LIMIT = "More than one NUS course code provided to filter";
    public static final String NUMBER_SUCCESS = "Number successfully retrieved";
    public static final String EMAIL_SUCCESS = "Email successfully retrieved";

    // ListUniCoursesCommand
    public static final String UNKNOWN_UNIVERSITY = "Unknown university encountered";
    public static final String EXTRACT_PU_NAME = "Extracted PU name";
    public static final String NO_PU_NAME = "Partner University name is empty";
    public static final String SEARCH_UNIVERSITY = "Searching university ...";
    public static final String UNIVERSITY_FOUND = "University found";
    public static final String NO_COURSES_FOUND = "No course found for PU";
    public static final String LISTING_COURSES = "Listing courses ...";
    public static final String NULL_UNIVERSITY = "No university name is given in the user input";

    public static final String RECEIVED_INPUT = "User input received: {0}";
    public static final String INVALID_INPUT = "Invalid command: {0}";
    public static final String EMPTY_INPUT_DETAILS = "Input details should not be empty after splitting";
    public static final String NULL_INPUT= "User input should not be null";
    public static final String TRIM_STRING = "Trim spaces and remove 'add' command.";
    public static final String PARSE_ADD_COMMANDS = "Check user input and split it into substrings.";
    public static final String EXTRACT_COURSES = "Extract from NUS course code, PU and PU course code from array.";
    public static final String FORMAT = "Format output";
    public static final String MISSING_KEYWORDS = "Missing /pu or /coursepu keyword.";
    public static final String ADJACENT_KEYWORDS = "Adjacent keywords.";
    public static final String INVALID_COURSE_CODE = "Invalid course code or partner university.";
    public static final String MISSING_INPUT_AFTER_KEYWORD = "No input after add keyword.";
    public static final String RETURN_TRIMMED_INPUT = "Return trimmed input without 'add' command";
    public static final String COMMAND_PARSED = "Command parsed successfully";
    public static final String INVALID_COMMAND = "Command is invalid.";
    public static final String EXECUTION_FAILED = "Command execution failed unexpectedly.";
    public static final String ADD_NEW_COURSE_MAPPING = "Please add a new course mapping!";
    public static final String INVALID_UNIVERSITY_INPUT = "Invalid university input!";
    public static final String ADD_APPROVED_MAPPING = "Add approved mapping into storage.";
    public static final String CHECK_UNIVERSITY = "Check user university input";
    public static final String CHECK_COURSE_MAPPING = "Check user NUS course and PU course mapping";
    public static final String FIND_COURSE_MAPPING = "Loop through course jsonObject to find the same " +
            "PU course code and NUS course code";
    public static final String DISPLAY_PARTNER_UNIVERSITIES = "Display available partner universities";
    public static final String FIND_PARTNER_UNIVERSITY = "Find partner university in Json file";
    public static final String RETRIEVE_COURSE_LIST = "Retrieve course list for partner university";

    // Delete Course
    public static final String DELETE_COURSE_MAPPING = "Deleting course mapping from storage..";
    public static final String RETURN_PARSED_DELETE_COMMAND = "Returning separated user input...";
    public static final String GET_COURSE_TO_DELETE = "Obtaining information on the course to be deleted.";
    public static final String GET_INDEX_OF_COURSE_TO_DELETE = "Obtaining list index of the course to be deleted.";

    // ListPersonalTrackerCommand
    public static final String INIT_STORAGE_LIST_PT = "ListPersonalTrackerCommand initialized with storage.";
    public static final String EXECUTE = "Executing ListPersonalTrackerCommand to list mapped modules.";
    public static final String NO_MODULES = "No modules loaded from the personal tracker.";
    public static final String DISPLAY_MODULES = "Displaying mapped modules from personal tracker.";
    public static final String EXECUTE_COMPLETE = "Completed execution of ListPersonalTrackerCommand.";


    // CompareMappedCommand logs
    public static final String INIT_STORAGE_COMPARE_MAPPED = "CompareMappedCommand initialized with storage.";
    public static final String EXECUTE_COMPARE_MAPPED = "Executing CompareMappedCommand for comparing mapped modules.";
    public static final String LOADED_MODULES = "Loaded mapped modules for comparison.";
    public static final String FILTERING_UNIVERSITY = "Filtering modules for university: ";
    public static final String COURSE_CODE_EXTRACTION= "Extracting course codes from modules.";
    public static final String COMMON_COURSE_CODES = "Identified common course codes between the two universities.";
    public static final String UNIQUE_COURSE_CODES = "Identified unique course codes for each university.";
    public static final String DISPLAY_COMPLETE = "Completed displaying comparison results for ";
    public static final String DISPLAYING_RESULTS = "Displaying comparison results between ";
    public static final String DISPLAYING_UNIQUE_MAPPINGS = "Displaying unique mappings for ";

    //FindCoursesCommand
    public static final String EXECUTE_FIND_COMMAND = "Executing findCommand with keyword";
    public static final String MISSING_KEYWORD = "Keyword is missing.";
    public static final String NO_MATCH_FOUND = "No courses found matching keyword.";
    public static final String MATCH_FOUND = "Match found for course code";
    public static final String EXTRACTED_COURSE_CODE = "Extracted mapped course code.";

    // CourseRepository logs
    public static final String COURSE_ENTRY = "Successfully added course: {0}";
    public static final String COURSE_SIZE = "Loaded all courses, total courses: {0}";
    public static final String INVALID_DELETE_INDEX = "Invalid index for deletion: {0}";
    public static final String DELETE_INDEX = "Deleted course at index {0}";

    // DataIntegrityChecker logs
    public static final String DATA_MISMATCH = "Data mismatch on line {0}: {1}";
    public static final String PARSING_ERROR_LINE = "Parsing error on line {0}: {1}";

    // FileHandler logs
    public static final String INITIALISE_FILE = "Initialized file at {0}";
    public static final String INITIALISE_FILE_FAIL = "Initialized file at {0}";
    public static final String LINES_SIZE = "Loaded all lines from file, total lines: {0}";
    public static final String LINE_READ_FAIL = "Failed to read lines from file";
    public static final String SAVE_LINE_SIZE = "Saved all lines to file, total lines: {0}";
    public static final String LINE_WRITE_FAIL = "Failed to write lines to file";
    public static final String APPEND_LINE = "Appended line to file: {0}";
    public static final String APPEND_LINE_FAIL = "Failed to append line to file";

}
