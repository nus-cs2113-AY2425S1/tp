package exceptions;

public class MissingFlagBuffBuddyException extends BuffBuddyException {

    public MissingFlagBuffBuddyException(String flag) {
        super("Required flag: " + flag + " is missing. Please provide this flag.");
    }
}

