package seedu.exceptions;

public class InvalidID extends Exception {
    public InvalidID() {
        super("Invalid ID!\nPlease use list to find internship ID");
    }
    public InvalidID(int internshipIndex) {
        super("Invalid ID: " + (internshipIndex + 1) + "\nPlease use list to find internship ID");
    }
}
