package exceptions;

public class CliRentalException extends Exception {
    public CliRentalException(String message) {
        super(message);
    }
    public static CliRentalException unknownCommand() {
        return new CliRentalException("OOPS!!! Invalid command!");
    }
}
