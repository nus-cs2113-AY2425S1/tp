package exceptions;


public class PaserExceptions extends BuffBuddyExceptions {
    public PaserExceptions(String message) {
        super(message);
    }

    public static PaserExceptions invalidFloat(String no) {
      return new PaserExceptions("Invalid Float: " + no);
    }

    public static PaserExceptions invalidInt(String no) {
      return new PaserExceptions("Invalid Integer: " + no);
    }

    public static PaserExceptions invalidDate(String date) {
      return new PaserExceptions("Invalid Date: " + date + ", Date in format dd-MM-yyyy.");
    }

    public static PaserExceptions missingCommand( ) {
      return new PaserExceptions("Missing Command, please refer to User Guide");
    }

    public static PaserExceptions missingArguments() {
      return new PaserExceptions("Missing Arguments, please refer to User Guide");
    }

    public static PaserExceptions indexOutOfBounds(String indexString) {
      return new PaserExceptions("Index out of bounds: " + indexString + "Please check the list again.");
    }
}
