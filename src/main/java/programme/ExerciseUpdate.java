// @@author TVageesan

package programme;

/**
 * Represents an immutable set of updates for an Exercise object.
 * <p>
 * This class encapsulates the data fields to update specific attributes of an Exercise.
 * Any field with a non-null value indicates a field to be updated in the target Exercise.
 * </p>
 */
public class ExerciseUpdate {

    protected final int sets;
    protected final int reps;
    protected final int weight;
    protected final int calories;
    protected final String name;

    /**
     * Constructs an ExerciseUpdate with specified fields. Each non-null parameter
     * represents a field intended to be updated in the target Exercise.
     *
     * @param sets     the updated number of sets, or NULL_INTEGER if not updating
     * @param reps     the updated number of reps, or NULL_INTEGER if not updating
     * @param weight   the updated weight, or NULL_INTEGER if not updating
     * @param calories the updated calorie count, or NULL_INTEGER if not updating
     * @param name     the updated name, or NULL_STRING if not updating
     */
    public ExerciseUpdate(int sets, int reps, int weight, int calories, String name) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.calories = calories;
        this.name = name;
    }
}
