package seedu.exchangecoursemapper.exception;

public class Exception extends Throwable {
    public static String fileReadError() {
        return "Error reading the file.";
    }

    public static String courseSearchError() {
        return "Please provide the course code you would like to search for.";
    }

    public static String filterCourseLimitError() {
        return "Please note that we can only filter for your first inputted NUS Course!";
    }
}
