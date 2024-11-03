package seedu.exchangecoursemapper.constants;

public class Assertions {
    public static final String NULL_JSON_FILE = "JSON object should not be null";
    public static final String EMPTY_JSON_FILE = "JSON file is empty";
    public static final String NO_NUS_COURSE_CODE_PARSED = "Nus course code should not be null";
    public static final String NO_COURSE_INFORMATION = "Course information should not be null";
    public static final String EMPTY_USER_INPUT = "User input should not be empty";
    public static final String EMPTY_PU_NAME = "Pu name should not be empty";
    public static final String NULL_UNIVERSITY_OBJECT = "University object should not be null";
    public static final String NO_COURSE_OBJECT = "Course object should not be empty";
    public static final String MISSING_FIELDS = "Parsed input have missing fields.";
    public static final String MISSING_USER_INPUT = "NUS course code, PU and PU course code are missing.";
    public static final String MISSING_KEYWORDS_ADD_COMMAND = "Missing keywords: '/coursepu' or '/pu'.";
    public static final String ADJACENT_KEYWORDS = "Adjacent keywords with no description " +
            "of the PU course code or PU.";
    public static final String NULL_INPUT = "Input should not be null.";
    public static final String EMPTY_SCHOOL_NAME = "No school name found";
    public static final String NULL_JSON_OBJECT = "JsonObject is not be null";
    public static final String NULL_SCHOOL_NAME = "School name is not be null";
    public static final String NULL_STORAGE = "Storage cannot be null";
    public static final String NULL_LIST = "Mapped modules list should not be null";
    public static final String EMPTY_NUS_COURSE_WARNING = "NUS course should not be empty";
    public static final String EMPTY_PU_WARNING = "Partner university should not be empty";
    public static final String EMPTY_PU_COURSE_WARNING = "Partner university course should not be empty";
    public static final String EMPTY_JSON_OBJECT_WARNING = "JSON object should not be empty";
  
    // CompareMappedCommand assertions
    public static final String UNIVERSITY1_NOT_NULL = "University 1 name should not be null";
    public static final String UNIVERSITY2_NOT_NULL = "University 2 name should not be null";
    public static final String COMMON_CODES_NOT_NULL = "Common course codes set should not be null";
    public static final String UNI1_MODULES_NOT_NULL = "University 1 modules list should not be null";
    public static final String UNI2_MODULES_NOT_NULL = "University 2 modules list should not be null";
    public static final String UNI1_UNIQUE_CODES_NOT_NULL = "University 1 unique course codes should not be null";
    public static final String UNI2_UNIQUE_CODES_NOT_NULL = "University 2 unique course codes should not be null";
    public static final String UNI_MODULES_NOT_NULL = "University 1 modules list should not be null";
    public static final String UNIVERSITY_NOT_NULL = "University name should not be null";
    public static final String UNIQUE_CODES_NOT_NULL = "Unique course codes set should not be null";
    public static final String LOADED_LIST_NOT_NULL = "Loaded modules list should not be null";

    //FindCoursesCommand
    public static final String COURSE_STRING_NOT_NULL = "Course string cannot be null";
}