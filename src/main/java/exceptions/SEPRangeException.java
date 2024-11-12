package exceptions;

/**
 * This class handles exceptions for range boundaries when
 * dealing with add operations.
 */
public class SEPRangeException extends SEPException {
    public SEPRangeException(String message) {
        super(message);
    }

    public static SEPRangeException rejectGpaRange() {
        return new SEPRangeException("GPA should be between 0 and 5.");
    }

    /**
     * Throws an exception if the number of preference rankings exceeds the allowed limit.
     * As of now, only up to 3 preference rankings are accepted.
     *
     * @return A SEPRangeException with a message indicating the preference rankings limit.
     */
    public static SEPRangeException rejectRankingsRange() {
        return new SEPRangeException("Preferences should not contain more than three rankings.");
    }

    /**
     * This method ensures that the preferences would only be able to go up to 92,
     * as there are up to 92 universities to be allocated to students.
     *
     * @return A SEPRangeException if preference rankings are less than 1 or more than 92.
     */
    public static SEPRangeException rejectPreferenceRange() {
        return new SEPRangeException("Preferences should be between 1 and 92.");
    }
}
