package seedu.exchangecoursemapper.constants;

public class HelpMessages {
    public static final String COMMAND_LIST_COURSES = "The list courses function allows users to key in the " +
            "partner university (PU)\nthey are interested in and it will return NUS courses and the " +
            "mappable PU courses.\n" +
            "The format to use this function is shown below:\n" +
            "list courses [PU_NAME]\n" +
            "For example, set the university of western australia";
    public static final String COMMAND_FILTER = "The filter function allows users to input a NUS course that they " +
            "want to map\nand get a list of the mappable courses that includes NUS courses in the format of:\n" +
            "Partner University: [PU_NAME]\n" +
            "Partner University Course Code: [PU_COURSE_CODE]\n" +
            "The format to use this feature is shown below:\n" +
            "filter [NUS_COURSE_CODE]\n" +
            "For example, filter cs2040";
    public static final String COMMAND_LIST_SCHOOLS = "The list school function allows users to get a list of the\n" +
            "possible partner universities that they can go.\n" +
            "To use this feature, please key in: list schools";
    public static final String COMMAND_COMMANDS = "The commands function shows a list of commands that could be " +
            "used throughout the application.\n" +
            "To use this feature, please key in: commands";
    public static final String COMMAND_ADD = "The add function allows users to add a course mapping plan into\n" +
            "their personalised tracker for future use.\n" +
            "The format to use this feature is shown below:\n" +
            "add <NUS_COURSE_CODE> /pu <NAME_OF_PU> /coursepu <PU_COURSE_CODE>\n" +
            "For example, add CS2040 /pu The university of western australia /coursepu CITS2200";
    public static final String COMMAND_BYE = "To exit the program.";
    public static final String COMMAND_OBTAIN = "The obtain function allows users to obtain information of Partner\n" +
            "Universities such as PU's email and PU's contact number.\n" +
            "To format to use this feature is shown below:\n" +
            "1. obtain <SCHOOL_NAME> /email - To obtain PU's email\n" +
            "2. obtain <SCHOOL_NAME> /number - To obtain PU's contact number\n" +
            "For example, obtain the university of western australia /number";
    public static final String COMMAND_DELETE = "The delete function allows users to delete a course mapping plan in" +
            " their personalised tracker.\n" +
            "The format to use this feature is shown below:\n" +
            "delete [MAPPING_NUMBER]\n" +
            "For example, delete 1 - which means deleting mapping 1";
    public static final String COMMAND_LIST_MAPPED = "The list mapped function allows users to list out all the\n" +
            "course mapping their have added into their personalised tracker using the add function.\n" +
            "To use this feature, please key in: list mapped";
    public static final String COMMAND_COMPARE_PU = "The compare pu function allows users to compare the course\n" +
            "mappings they have in their personalised tracker.\n" +
            "It will compare the course mappings between two different partner university.\n" +
            "To format to use this feature is shown below:\n" +
            "compare pu/[PU_1] pu/[PU_2]\n" +
            "For example, compare pu/the university of western australia pu/the university of melbourne";
    public static final String COMMAND_FIND = "The find function allows users to input a NUS course that they\n" +
            "want to find and get the relevant course mapping plans in their personal tracker.\n" +
            "The format to use this feature is shown below:\n" +
            "find [NUS_COURSE_CODE]\n" +
            "For example, find cs2040";
}
