package seedu.exchangecoursemapper.constants;

public class Messages {
    public static final String LINE_SEPARATOR = "-----------------------------------------------------";
    public static final String NO_MAPPABLE_COURSES_MESSAGE = "No courses found for the given course code.";
    public static final String INVALID_COMMAND_MESSAGE = "Please provide a valid command!" +
            "\nType 'commands' to find out what we can help you with :)";
    public static final String LIST_RELEVANT_PU = "The relevant universities are (non-case sensitive):\n" +
            "1. Chulalongkorn University\n" +
            "2. The University of Melbourne\n" +
            "3.The Australian National University\n" +
            "4. Victoria University of Wellington\n" +
            "5.The University of Western Australia\n\n" +
            "NOTE: Please indicate the partner universities FULL NAME!\n" +
            "NOTE: Instead of \"Australian National University,\" " +
            "please indicate \"The Australian National University.\"";
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
                    """;

    // CompareMappedCommand messages
    public static final String INVALID_INPUT_FORMAT = "Please provide two universities in the format:  " +
            "compare pu/<University 1> pu/<University 2>";
    public static final String COMPARISON_RESULTS_HEADER = "Comparison of Course Mappings between ";
    public static final String COMMON_MAPPINGS_HEADER = "Common Mappings:";
    public static final String NO_COMMON_MAPPINGS = "No common mappings found.";
    public static final String UNIQUE_MAPPINGS_HEADER = "Unique to ";
    public static final String NO_UNIQUE_MAPPINGS = "No unique mappings for ";
}
