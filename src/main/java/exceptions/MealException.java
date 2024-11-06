package exceptions;

public class MealException extends BuffBuddyExceptions {
    public MealException(String message) {
        super(message);
    }

    public static MealException doesNotExist() {
        return new MealException("Meal does not exist");
    }
}
