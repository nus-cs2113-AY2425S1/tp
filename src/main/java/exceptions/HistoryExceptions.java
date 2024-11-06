package exceptions;

public class HistoryExceptions extends BuffBuddyExceptions {
    public HistoryExceptions(String message) {
        super(message);
    }

    public static HistoryExceptions dayNotFound() {
      return new HistoryExceptions("Day does not exist, cannot be deleted");
    }

    public static HistoryExceptions exerciseNameNotFound() {
      return new HistoryExceptions("Exercise name not provided. Please specify the exercise to view your personal best.");
    }
}
