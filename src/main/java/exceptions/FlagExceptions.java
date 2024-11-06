//@@author Bev-Low

package exceptions;

public class FlagExceptions extends BuffBuddyExceptions {
    public FlagExceptions(String message) {
        super(message);
    }

    public static FlagExceptions missingFlag(String flag) {
        return new FlagExceptions("Flag " + flag + " not present");
    }

    public static FlagExceptions missingArguments() {
        return new FlagExceptions("Missing arugments after flag, please refer to User Guide");
    }
}
