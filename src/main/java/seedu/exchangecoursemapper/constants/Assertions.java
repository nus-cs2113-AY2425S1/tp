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
}

