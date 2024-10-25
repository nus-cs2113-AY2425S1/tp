package seedu.exchangecoursemapper.constants;

public class Logs {
    public static final String EXECUTING_COMMAND  = "Executing command";
    public static final String SUCCESS_READ_JSON_FILE = "Successfully read JSON file";
    public static final String FAILURE_READ_JSON_FILE = "Failed to read JSON file";
    public static final String COMPLETE_EXECUTION  = "Execution complete";
    public static final String LIST_SCHOOLS_NAMES = "Displaying university names ...";
    public static final String LIST_MAPPABLE_COURSES = "Displaying mappable PU courses ...";
    public static final String NO_NUS_COURSE_CODE_FILTER = "No NUS course code provided to filter";
    public static final String FILTER_COURSES_LIMIT = "More than one NUS course code provided to filter";
    public static final String UNKNOWN_UNIVERSITY = "Unknown university encountered";
    public static final String EXTRACT_PU_NAME = "Extracted PU name";
    public static final String NO_PU_NAME = "Partner University name is empty";
    public static final String SEARCH_UNIVERSITY = "Searching university ...";
    public static final String UNIVERSITY_FOUND = "University found";
    public static final String NO_COURSES_FOUND = "No course found for PU";
    public static final String LISTING_COURSES = "Listing courses ...";
    public static final String COURSE_DETAILS = "Printing course details ...";
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
}
