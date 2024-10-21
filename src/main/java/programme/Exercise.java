package programme;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercise {
    private static final Logger logger = Logger.getLogger(Exercise.class.getName());

    private int sets;
    private int reps;
    private int weight;
    private String name;

    public Exercise(int sets, int reps, int weight, String name) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.name = name;

        logger.log(Level.INFO, "Exercise created: {0}", this);
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    // Where the 'update' Exercise object has a non-null field, update current exercise to that value
    public void updateExercise(Exercise update) {
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
    }

    private boolean isNull(int val) {
        return val == -1;
    }

    private boolean isNull(String val) {
        return val == null || val.isEmpty();
    }

    @Override
    public String toString() {
        return name.replace("_", " ") + ": " + sets + " sets of " + reps + " reps at " + weight + " kg";
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
                Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sets, reps, weight, name);
    }
}

