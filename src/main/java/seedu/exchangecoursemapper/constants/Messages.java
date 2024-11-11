package seedu.exchangecoursemapper.constants;


public class Messages {
    /* For UI layout */
    public static final String LINE_SEPARATOR = "-----------------------------------------------------";

    /* Messages for all invalid CLI commands */
    public static final String INVALID_COMMAND_MESSAGE = "Please provide a valid command!" +
            "\nType 'commands' to find out what we can help you with :)";

    /* Messages for FilterCoursesCommand */
    public static final String NO_MAPPABLE_COURSES_MESSAGE = "No mappable courses found for the given course code.";
    public static final String FILTER_RESULTS_HEADER = "Filter results for ";
    public static final String END_OF_FILTER_RESULTS_NOTICE = "End of filter results";
    public static final String PARTNER_UNIVERSITY_HEADER = "Partner University: ";
    public static final String PARTNER_UNIVERSITY_COURSE_CODE_HEADER = "Partner University Course Code: ";

    /* Messages for DeleteCoursesCommand */
    public static final String DELETE_COURSE_PLAN_HEADER = "You have deleted the course from your plan: ";


    public static final String LIST_RELEVANT_PU = "The relevant universities are (non-case sensitive):\n" +
            "1. The University of Melbourne - unimelb \n" +
            "2.The Australian National University - anu\n" +
            "3. Victoria University of Wellington - wgtn\n" +
            "4.The University of Western Australia - uwa\n\n" +
            "NOTE: Please indicate the full name or the official abbreviation of the partner universities.\n";

    public static final String COMMANDS_LIST =
            """
                    Here are the available commands:
                    filter <subject code> - Filter courses by subject code.
                    set <SCHOOL_NAME>     - Set a partner university for course mapping.
                    list schools          - List all available partner universities.
                    add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE> \
                    - Add mapped courses between NUS and partner universities.
                    obtain <SCHOOL_NAME> /email  - Obtain partner university contact email.
                    obtain <SCHOOL_NAME> /number - Obtain partner university contact number.
                    delete <LIST_INDEX>          - Delete a course mapping.
                    list mapped                  - List all course mapping saved.
                    compare pu/<uni1> pu/<uni2>  - Compare course mappings between 2 universities.
                    find <NUS_COURSE_CODE>       - Find courses with subject code in your list.
                    bye                          - End the program.
                    help <COMMAND>               - To get more specific information of the commands.
                    """;
    // ListPersonalTrackerCommand
    public static final String NO_MODULES_MESSAGE =
            "No modules mapped yet or you may have " +
                    "changed the file/directory name.\n" +
            "Please start adding courses and check that the " +
                    "file/directory has not been changed.";
    public static final String MAPPED_MODULES_HEADER = "Mapped Modules:";

    // CompareMappedCommand messages
    public static final String INVALID_INPUT_FORMAT = "Please provide two universities in the format:  " +
            "compare pu/<University 1> pu/<University 2>";
    public static final String COMPARISON_RESULTS_HEADER = "Comparison of Course Mappings between ";
    public static final String COMMON_MAPPINGS_HEADER = "Common Mappings:";
    public static final String NO_COMMON_MAPPINGS = "No common mappings found.";
    public static final String UNIQUE_MAPPINGS_HEADER = "Unique to ";
    public static final String NO_UNIQUE_MAPPINGS = "No unique mappings for ";
    public static final String INVALID_COURSE_MAPPING = "Invalid course mapping!";

    //Obtain contacts messages
    public static final String EMAIL_TAG = "Email for ";
    public static final String NUMBER_TAG = "Phone number for ";
    public static final String INDEX_OUT_OF_BOUNDS = "Course index out of bounds";

    public static final String CORRUPT_HELP = "Please fix the course entry or remove the line completely before " +
            "executing a new file command.";
    public static final String ERROR_COURSE_ENTRY = "Error: Invalid course entry at line ";
    public static final String ERROR_ENTRY_FORMAT = "Error: Unable to parse course entry at line ";
    public static final String COURSE_NOT_FOUND = "Course not found in database: ";
    public static final String INVALID_FORMAT = "Invalid format: ";
    public static final String MYLIST_JSON = " in myList.json";
    public static final String CHECK_SPELLING = "Please check the spelling or refer to the list of available " +
            "universities using the 'list schools' command.";
    public static final String UNKNOWN_UNI = "Error: The university \"";
    public static final String NOT_RECOGNIZED = "\" is not recognized.";

    public static final String DUPLICATE_FOUND = "The following duplicate courses were found and removed from your " +
            "Personal Tracker:";
    public static final String DUPLICATE_REMOVED = "Duplicates have been removed, and the data file has been updated.";
    public static final String ACCEPTED_FORMAT = "Valid format: <NUS_COURSE_CODE> | <FULL_NAME_OF_PU> | " +
            "<PU_COURSE_CODE> \nAll entries are in lowercase";
}
