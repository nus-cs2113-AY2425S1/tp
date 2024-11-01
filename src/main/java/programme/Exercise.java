package programme;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static common.Utils.isNull;

public class Exercise {
    private static final Logger logger = Logger.getLogger(Exercise.class.getName());

    private int sets;
    private int reps;
    private int weight;
    private int calories;
    private String name;

    public Exercise(int sets, int reps, int weight, int calories, String name) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.calories = calories;
        this.name = name;

        logger.log(Level.INFO, "Exercise created: {0}", this);
    }

    // @@author TVageesan

    /**
     * Updates the current Exercise's fields based on the non-null values in the provided ExerciseUpdate object.
     * <p>
     * For each non-null field in the UpdateExercise object, the corresponding field in this Exercise is updated.
     * </p>
     *
     * @param update the ExerciseUpdate containing fields to be updated in this Exercise.
     */
    public void updateExercise(ExerciseUpdate update) {
        assert update != null : "ExerciseUpdate object must be provided";

        if (!isNull(update.sets)) {
            logger.log(Level.INFO, "Updating sets from {0} to {1}", new Object[]{sets, update.sets});
            sets = update.sets;
        }

        if (!isNull(update.reps)) {
            logger.log(Level.INFO, "Updating reps from {0} to {1}", new Object[]{reps, update.reps});
            reps = update.reps;
        }

        if (!isNull(update.weight)) {
            logger.log(Level.INFO, "Updating weight from {0} to {1}", new Object[]{weight, update.weight});
            weight = update.weight;
        }

        if (!isNull(update.name)) {
            logger.log(Level.INFO, "Updating name from {0} to {1}", new Object[]{name, update.name});
            name = update.name;
        }

        if (!isNull(update.calories)) {
            logger.log(Level.INFO, "Updating calories from {0} to {1}", new Object[]{calories, update.calories});
            calories = update.calories;
        }

    }

    // @@author

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return String.format("%s: %d sets of %d at %d | Burnt %d cals", name, sets, reps, weight, calories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Exercise exercise = (Exercise) o;
        return sets == exercise.sets &&
                reps == exercise.reps &&
                weight == exercise.weight &&
                calories == exercise.calories &&
                Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sets, reps, weight, calories, name);
    }
}

