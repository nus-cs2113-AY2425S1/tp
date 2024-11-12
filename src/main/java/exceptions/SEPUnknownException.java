package exceptions;

public class SEPUnknownException extends SEPException {
    public SEPUnknownException(String message) {
        super(message);
    }

    /**
     * Returns a SEPUnknownException with a message indicating that the file type
     * is unknown or unsupported.
     *
     * @return A SEPUnknownException with the appropriate message.
     */
    public static SEPUnknownException rejectUnknownFileType() {
        return new SEPUnknownException("Unknown/Unsupported file type detected! " +
                "\nYou can continue to use the program.");
    }
}
