package exceptions;

/**
 * This class handles exceptions when output is empty.
 */
public class SEPEmptyException extends SEPException {
    public SEPEmptyException(String message) {
        super(message);
    }

    public static SEPEmptyException rejectEmptyStudentList() {
        return new SEPEmptyException("No students available for generation.");
    }

    public static SEPEmptyException rejectStudentNotFound() {
        return new SEPEmptyException("Student not found.");
    }

    public static SEPEmptyException rejectUniversityNotFound() {
        return new SEPEmptyException("The specified university was not found. Please check the university index.");
    }

    /**
     * Returns a SEPEmptyException with a message indicating that the file was not found.
     *
     * @return A SEPEmptyException with the appropriate message.
     */
    public static SEPEmptyException rejectFileNotFound() {
        return new SEPEmptyException("This file don't exist...Don't make me go " +
                "through your folder for nothing leh :(.\nProcess Outcome: No data is loaded. " +
                "You can continue using the program.");
    }

    public static SEPEmptyException rejectEmptyFile() {
        return new SEPEmptyException("Nothing to read from the file. Please ensure there is content next time.");
    }

    public static SEPEmptyException rejectAllocationIncomplete() {
        return new SEPEmptyException("Allocation incomplete, try running allocate before reverting! :>");
    }

    public static SEPEmptyException rejectAllocateEmpty() {
        return new SEPEmptyException("Unable to allocate with empty student list, try adding students! :>");
    }

    public static SEPEmptyException rejectAllocateNoChange() {
        return new SEPEmptyException("No changes made in the allocation. \n" +
                "If you've edited the original student list, try run `revert` first then `allocate`! :>");
    }
}
