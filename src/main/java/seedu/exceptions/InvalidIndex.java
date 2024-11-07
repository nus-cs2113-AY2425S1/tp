package seedu.exceptions;

public class InvalidIndex extends Exception {
    public InvalidIndex() {
        super("Invalid ID!\nPlease use list to find internship ID");
    }
    public InvalidIndex(int internshipIndex) {
        super("Invalid ID: " + (internshipIndex + 1) + "\nPlease use list to find internship ID");
    }
}
