package seedu.exchangecoursemapper.exception;

public class Exception extends Throwable {
    public Exception(String message) {
        super(message);
    }

    public static String fileReadError() {
        return "Error reading the file.";
    }

    public static String emptyUniversityName() {
        return "Please provide a University name.";
    }

    public static String noInputAfterAdd() {
        return "Please provide the nus course code, " +
                "name of partner university (PU) and the PU course code.";
    }

    public static String missingKeyword() {
        return "Please provide all of the valid commands: /pu, /coursepu!";
    }

    public static String adjacentInputError() {
        return "Commands shouldn't be adjacent to one another!";
    }

    public static String invalidCourseCodes() {
        return "Please provide a valid NUS course code or PU or PU's course code!";
    }

    public static String missingNusCourseCode() {
        return "Please provide the course code you would like to search for.";
    }

    public static String filterCoursesLimitExceeded() {
        return "Please note that we can only filter for only one NUS Course!";
    }

    public static String nonSocNusCourseGiven() {
        return "We can only filter for CS/CG/EE/BT/IS coded courses!";
    }

    public static String invalidNusCourseCodeFormat() {
        return "Please follow this format for the NUS SoC course code input (not case-sensitive):\n" +
                "CS/EE/BT/IS followed by a 4-digit sequence e.g CS3241" +
                "Some courses may end with a character too e.g. EE3131C";
    }

    public static String invalidCommand() {
        return "Invalid command.\nPlease check the commands available by typing commands.";
    }

    public static String noInputAfterDelete() {
        return "Please provide the index of the course plan you would like to delete.";
    }

    public static String deleteCoursesLimitExceeded() {
        return "Please provide just one index of the course plan you would like to delete.";
    }

    public static String invalidCourseListIndex() {
        return "Please provide a valid index of the course plan you would like to delete.\n" +
                "Type `list mapped` to check your current list of saved plans!";
    }

    public static String nonNumericListIndexInput() {
        return "Please provide a valid numeric index of the course plan you would like to delete.";
    }

    public static String invalidContactType() {
        return "Invalid contact type.";
    }

    public static String invalidInputFormat() {
        return "Invalid input format";
    }

    public static String emptyCourse(){
        return "NUS or Partner University course input cannot be empty.";
    }

    public static String noCourseAvailable(String pu) {
        return "No courses available for the partner university: " + pu;
    }

    public static String emptyKeyword() {
        return "Keyword to search for is empty.";
    }

    public static String unknownUniversity(String puName) {
        return "Unknown university: " + puName;
    }

    public static String noMatchFound() {
        return "No match found.";
    }

}
