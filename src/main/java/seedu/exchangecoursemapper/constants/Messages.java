package seedu.exchangecoursemapper.constants;


public class Messages {
    /* For UI layout */
    public static final String LINE_SEPARATOR = "-----------------------------------------------------";

    /* Messages for all invalid CLI commands */
    public static final String INVALID_COMMAND_MESSAGE = "Please provide a valid command!" +
            "\nType 'commands' to find out what we can help you with :)";

    /* Messages for FilterCoursesCommand */
    public static final String NO_MAPPABLE_COURSES_MESSAGE = "No courses found for the given course code.\n" +
            "It may not be mappable, or the given course code is not a course offered by NUS!";
    public static final String PARTNER_UNIVERSITY_HEADER = "Partner University: ";
    public static final String PARTNER_UNIVERSITY_COURSE_CODE_HEADER = "Partner University Course Code: ";

    /* Messages for DeleteCoursesCommand */
    public static final String DELETE_COURSE_PLAN_HEADER = "You have deleted the course from your plan: ";

    public static final String LIST_RELEVANT_PU = "The relevant universities are (non-case sensitive):\n" +
            "1. The University of Melbourne\n" +
            "2.The Australian National University\n" +
            "3. Victoria University of Wellington\n" +
            "4.The University of Western Australia\n\n" +
            "NOTE: Please indicate the partner universities FULL NAME!\n" +
            "EXAMPLE: Instead of \"VUW\" " +
            "please indicate \"Victoria University of Wellington\".";
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
                    delete <TASK_NUMBER>         - Delete a partner university.
                    list mapped                  - List all course mapping saved.
                    compare pu/<uni1> pu/<uni2>  - Compare course mappings between 2 universities.
                    find <NUS_COURSE_CODE>       - Find courses with subject code in your list.
                    bye                          - End the program.
                    To get more specific information of the commands, please use help <COMMAND>
                    """;
    // ListPersonalTrackerCommand
    public static final String NO_MODULES_MESSAGE =
            "No modules mapped yet or you may have " +
                    "changed the file/directory name." +
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
}
