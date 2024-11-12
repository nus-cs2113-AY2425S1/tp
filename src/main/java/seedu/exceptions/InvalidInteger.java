package seedu.exceptions;

public class InvalidInteger extends Exception{
    public InvalidInteger(String arg) {
        super("Invalid Integer: " + arg + "\nPlease use list to find internship ID");
    }
}
