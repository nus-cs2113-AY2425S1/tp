package programme;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day {
    private final String name;
    private final ArrayList<Exercise> exercises;

    private final Logger logger = Logger.getLogger(Day.class.getName());

    public Day(String name, ArrayList<Exercise> exercises) {
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        assert exercises != null : "Exercises list cannot be null";

        this.exercises = exercises;
        this.name = name;

        logger.log(Level.INFO, "Day created: {0}", this);
    }

    public Day(String name){
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";

        this.name = name;
        this.exercises = new ArrayList<>();

        logger.log(Level.INFO, "Day created with empty exercise list: {0}", this);
    }

    public int getExercisesCount() {
        logger.log(Level.INFO, "Number of exercises: {0}", exercises.size());
        return exercises.size();
    }

    public Exercise getExercise(int index){
        if (index < 0 || index >= exercises.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for exercise list.");
        }

        logger.log(Level.INFO, "Retrieving exercise at index {0}: {1}", new Object[]{index, exercises.get(index)});
        return exercises.get(index);
    }

    public Exercise updateExercise(int exerciseId, Exercise exercise) {
        if (exerciseId < 0 || exerciseId >= exercises.size()) {
            throw new IndexOutOfBoundsException("Index " + exerciseId + " is out of bounds for exercise list.");
        }
        Exercise toBeUpdated = exercises.get(exerciseId);

        logger.log(Level.INFO, "Updating exercise at index {0} from {1} to {2}",
                new Object[]{exerciseId, toBeUpdated, exercise});

        toBeUpdated.updateExercise(exercise);
        exercises.set(exerciseId, toBeUpdated);
        return toBeUpdated;
    }

    public void insertExercise(Exercise exercise) {
        assert exercise != null : "Exercise to insert cannot be null";
        exercises.add(exercise);
        logger.log(Level.INFO, "Inserted exercise {0}", exercise);
    }

    public Exercise deleteExercise(int index) {
        if (index < 0 || index >= exercises.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for exercise list.");
        }
        Exercise toBeDeleted = exercises.get(index);
        exercises.remove(index);
        logger.log(Level.INFO, "Deleted exercise at index {0}: {1}", new Object[]{index, toBeDeleted});
        return toBeDeleted;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append("\n\n");

        for (int i = 0; i < exercises.size(); i++) {
            Exercise exercise = exercises.get(i);
            result.append(String.format("%d. %s%n", i + 1, exercise));
        }

        return result.append("\n").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Day day = (Day) o;
        return Objects.equals(name, day.name) &&
                Objects.equals(exercises, day.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exercises);
    }
}

