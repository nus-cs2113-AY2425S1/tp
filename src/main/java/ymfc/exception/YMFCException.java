package ymfc.exception;

public class YMFCException extends Exception {
    public YMFCException() {
        super();
    }

    public YMFCException(String message) {
        super(message);
    }

    public YMFCException(String message, Throwable cause) {
        super(message, cause);
    }

    public YMFCException(Throwable cause) {
        super(cause);
    }

    protected YMFCException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
