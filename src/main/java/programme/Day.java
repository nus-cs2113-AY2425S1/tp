// @@author Atulteja
package programme;

import exceptions.ProgrammeExceptions;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a day in a workout programme, containing a list of exercises.
 * Provides methods to add, delete, and retrieve exercises, as well as calculate the total calories burnt.
 */
public class Day {
    private static final Logger logger = Logger.getLogger(Day.class.getName());

    private final String name;
    private final ArrayList<Exercise> exercises;

    /**
     * Deep copies a new Day by copying the name and exercises from an existing Day.
     *
     * @param day the Day to copy
     */
    public Day(Day day) {
        this.name = day.name;
        this.exercises = new ArrayList<>(day.exercises);
    }

    /**
     * Constructs a Day with a specified name and an empty list of exercises.
     *
     * @param name the name of the day
     * @throws AssertionError if the name is null or empty
     */
    public Day(String name){
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";

        this.name = name;
        this.exercises = new ArrayList<>();

        logger.log(Level.INFO, "Day created with empty exercise list: {0}", this);
    }

    /**
     * Constructs a Day with a specified name and a list of exercises.
     *
     * @param name      the name of the day
     * @param exercises the list of exercises for the day
     * @throws AssertionError if the name is null or empty, or if exercises is null
     */
    public Day(String name, ArrayList<Exercise> exercises) {
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        assert exercises != null : "Exercises list cannot be null";

        this.exercises = exercises;
        this.name = name;

        logger.log(Level.INFO, "Day created: {0}", this);
    }

    public String getName() {
        return name;
    }

    public int getExercisesCount() {
        logger.log(Level.INFO, "Number of exercises: {0}", exercises.size());
        return exercises.size();
    }

    /**
     * Returns the exercise at the specified index.
     *
     * @param index the index of the exercise to retrieve
     * @return the exercise at the specified index
     * @throws ProgrammeExceptions if the index is out of bounds
     */
    public Exercise getExercise(int index){
        if (index < 0 || index >= exercises.size()) {
            throw ProgrammeExceptions.doesNotExist("exercise");
        }

        logger.log(Level.INFO, "Retrieving exercise at index {0}: {1}", new Object[]{index, exercises.get(index)});
        return exercises.get(index);
    }

    /**
     * Inserts an exercise into the day's exercise list.
     *
     * @param exercise the exercise to insert
     * @throws AssertionError if the exercise is null
     */
    public void insertExercise(Exercise exercise) {
        assert exercise != null : "Exercise to insert cannot be null";
        exercises.add(exercise);
        logger.log(Level.INFO, "Inserted exercise {0}", exercise);
    }

    /**
     * Deletes the exercise at the specified index.
     *
     * @param index the index of the exercise to delete
     * @return the deleted exercise
     * @throws ProgrammeExceptions if the index is out of bounds
     */
    public Exercise deleteExercise(int index) {
        if (index < 0 || index >= exercises.size()) {
            throw ProgrammeExceptions.doesNotExist("exercise");
        }
        Exercise toBeDeleted = exercises.get(index);
        exercises.remove(index);
        logger.log(Level.INFO, "Deleted exercise at index {0}: {1}", new Object[]{index, toBeDeleted});
        return toBeDeleted;
    }

    /**
     * Calculates the total calories burnt from all exercises in the day.
     *
     * @return the total calories burnt
     */
    public int getTotalCaloriesBurnt(){
        int totalCalories = 0;
        for (Exercise exercise : exercises) {
            totalCalories += exercise.getCalories();
        }
        return totalCalories;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append("\n");

        for (int i = 0; i <      exercises.size(); i++) {
            Exercise exercise = exercises.get(i);
            result.append(String.format("%d. %s%n", i + 1, exercise));
        }

        return result.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, exercises);
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
}
