package exceptions;

/**
 * This class handles duplicate student entries.
 */
public class SEPDuplicateException extends SEPException {
    public SEPDuplicateException(String message) {
        super(message);
    }

    /**
     * Throws an exception when attempting to add a student that already exists
     * in the student list.
     *
     * @return A SEPDuplicateException indicating the student is a duplicate.
     */
    public static SEPDuplicateException rejectDuplicateStudent() {
        return new SEPDuplicateException("Student is already inside the student list.");
    }
}
