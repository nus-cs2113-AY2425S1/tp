package seedu.exchangecoursemapper.constants;

public class HelpMessages {
    public static final String COMMAND_SET = "The set function allows users to key in the partner university(PU)\n" +
            "they are interested in and it will return NUS courses and the mappable PU courses.\n " +
            "The format to use this function is shown below:\n" +
            "set [PU_NAME]\n" +
            "e.g. set the university of western australia";
    public static final String COMMAND_FILTER = "The filter function allows users to input a NUS course that they\n" +
            "want to map and get a list of the mappable courses from PUs.\n" +
            "The format to use this feature is shown below:\n" +
            "filter [NUS_COURSE_CODE]\n" +
            "e.g. filter cs2040";
    public static final String COMMAND_LIST_SCHOOLS = "The list school function allows users to get a list of the\n" +
            "possible partner universities that they can go.\n" +
            "To use this feature, please key in: list schools";
    public static final String COMMAND_COMMANDS = "The commands function shows a list of commands that could be" +
            "used throughout the application.\n" +
            "To use this feature, please key in: commands";
    public static final String COMMAND_ADD = "The add function allows users to add a course mapping plan into\n" +
            "their personalised tracker for future use." +
            "The format to use this feature is shown below:\n" +
            "add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE>\n" +
            "e.g. add CS2040 /pu The university of western australia /coursepu CITS2200";
    public static final String COMMAND_BYE = "To exit the program.";
    public static final String COMMAND_OBTAIN = "The obtain function allows users to obtain information of Partner\n" +
            "Universities such as PU's email and PU's contact number.\n" +
            "To format to use this feature is shown below:\n" +
            "1. obtain <SCHOOL_NAME> /email - To obtain PU's email\n" +
            "2. obtain <SCHOOL_NAME> /number - To obtain PU's contact number\n" +
            "e.g. obtain the university of western australia /number";
}
