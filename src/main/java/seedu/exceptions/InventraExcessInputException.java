package seedu.exceptions;

public class InventraExcessInputException extends InventraException {
  private final int expected;
  private final int actual;

  public InventraExcessInputException(int expected, int actual) {
    this.expected = expected;
    this.actual = actual;
  }

  @Override
  public String getMessage() {
    return String.format("Input length exceeds the expected number. Expected less than %d, but received: %d.", expected, actual);
  }
}
