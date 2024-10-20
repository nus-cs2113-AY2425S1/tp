package programme;

import java.util.Objects;

public class Exercise {
    private int sets;
    private int reps;
    private int weight;
    private String name;

    public Exercise(int sets, int reps, int weight, String name) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.name = name;
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
        sets = isNull(update.sets) ? sets : update.sets;
        reps = isNull(update.reps) ? reps : update.reps;
        weight = isNull(update.weight) ? weight : update.weight;
        name = isNull(update.name) ? name : update.name;
    }

    private boolean isNull(int val) {
        return (val == -1);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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

