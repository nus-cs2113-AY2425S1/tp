package exceptions;

public class EmptyInputBuffBuddyException extends BuffBuddyException {

    public EmptyInputBuffBuddyException(String inputType) {
        super(inputType + " cannot be empty. Please enter a valid " + inputType + ".");
    }
}

