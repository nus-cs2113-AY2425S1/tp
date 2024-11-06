//@@author Bev-Low

package exceptions;


public class ParserExceptions extends BuffBuddyExceptions {
    public ParserExceptions(String message) {
        super(message);
    }

    public static ParserExceptions invalidFloat(String no) {
        return new ParserExceptions("Invalid Float: " + no);
    }

    public static ParserExceptions invalidInt(String no) {
        return new ParserExceptions("Invalid Integer: " + no);
    }

    public static ParserExceptions invalidDate(String date) {
        return new ParserExceptions("Invalid Date: " + date + ", Date in format dd-MM-yyyy.");
    }

    public static ParserExceptions missingCommand( ) {
        return new ParserExceptions("Missing Command, please refer to User Guide");
    }

    public static ParserExceptions missingArguments() {
        return new ParserExceptions("Missing Arguments, please refer to User Guide");
    }

    public static ParserExceptions indexOutOfBounds(String indexString) {
        return new ParserExceptions("Index out of bounds: " + indexString + "Please check the list again.");
    }
}
