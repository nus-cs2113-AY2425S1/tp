package wheresmymoney.exception;

public class InvalidInputException extends WheresMyMoneyException{
    /**
     * Creates WheresMyMoneyException
     *
     * @param errorMessage Error Message
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
