package seedu.healthmate.core;

import seedu.healthmate.services.UI;

/**
 * Manages a user's health goal (weight loss, steady state, bulking) and
 * calculates target calorie intake based on user data and selected goal.
 */
public class HealthGoal {

    private static final String WEIGHT_LOSS = "WEIGHT_LOSS";
    private static final String STEADY_STATE = "STEADY_STATE";
    private static final String BULKING = "BULKING";

    private static final double WEIGHT_LOSS_MODIFIER = 0.9;
    private static final double STEADY_STATE_MODIFIER = 1.1;
    private static final double BULKING_MODIFIER = 1.4;

    private static final String[] healthGoals = {WEIGHT_LOSS, STEADY_STATE, BULKING};

    private String currentHealthGoal;


    /**
     * Constructor for HealthGoal.
     * Initializes the health goal with the provided input.
     *
     * @param healthGoalInput the desired health goal.
     */
    public HealthGoal(int healthGoalInput) {
        saveHealthGoal(healthGoalInput);
    }

    public HealthGoal(String healthGoalInput) {
        saveHealthGoal(healthGoalInput);
    }

    /**
     * Saves the current health goal based on input.
     *
     * @param healthGoalInput the input health goal (e.g., WEIGHT_LOSS).
     */
    public void saveHealthGoal(int healthGoalInput) {

        if (1 > healthGoalInput | healthGoalInput > 3) {
            UI.printReply("Invalid Health Goal", "Save Health Goal Error: ");
            return;
        }

        currentHealthGoal = healthGoals[healthGoalInput - 1];

    }

    public void saveHealthGoal(String healthGoalInput) {
        if (healthGoalInput.equals("")) {
            UI.printReply("Empty Health Goal", "Save Health Goal Error: ");
        }
        assert healthGoalInput != null : "Health goal input cannot be null";
        switch (healthGoalInput) {
        case WEIGHT_LOSS:
            this.currentHealthGoal = WEIGHT_LOSS;
            break;
        case STEADY_STATE:
            this.currentHealthGoal = STEADY_STATE;
            break;
        case BULKING:
            this.currentHealthGoal = BULKING;
            break;
        default:
            UI.printReply("Invalid Health Goal", "Save Health Goal Error: ");
        }
    }

    /**
     * Gets the current health goal.
     *
     * @return the current health goal as a String.
     */
    public String getCurrentHealthGoal() {
        return currentHealthGoal;
    }

    /**
     * Calculates the target calories based on user data and health goal.
     *
     * @param height  the user's height in cm.
     * @param weight  the user's weight in kg.
     * @param isMale  true if the user is male, false if female.
     * @param age     the user's age.
     * @return the target calories based on the health goal and user data.
     */
    public int getTargetCalories(double height, double weight, boolean isMale, int age) {
        assert height > 0 : "Height must be positive";
        assert weight > 0 : "Weight must be positive";
        assert age > 0 : "Age must be positive";

        double rawCaloriesTarget;
        if (isMale) {
            rawCaloriesTarget = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            rawCaloriesTarget = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        assert currentHealthGoal != null : "Current health goal must be set";

        switch (currentHealthGoal) {
        case WEIGHT_LOSS:
            return (int)(rawCaloriesTarget * WEIGHT_LOSS_MODIFIER);
        case STEADY_STATE:
            return (int)(rawCaloriesTarget * STEADY_STATE_MODIFIER);
        case BULKING:
            return (int)(rawCaloriesTarget * BULKING_MODIFIER);
        default:
            return -1;
        }
    }

    /**
     * Returns a string representation of the current health goal.
     *
     * @return the current health goal as a String.
     */
    @Override
    public String toString() {
        assert currentHealthGoal != null : "Current health goal cannot be null when converting to string";
        return currentHealthGoal;
    }
}
