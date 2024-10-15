package programme;

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

    // Where the 'update' Exercise object has a non-null field, update current exercise to that value
    public void updateExercise(Exercise update){
        sets = isNull(update.sets) ? sets : update.sets;
        reps = isNull(update.reps) ? reps: update.reps;
        weight = isNull(update.weight) ? weight : update.weight;
        name = isNull(update.name) ? name : update.name;
    }

    private boolean isNull(int val){
        return (val == -1);
    }

    private boolean isNull(String val){
        return val.isEmpty();
    }

    @Override
    public String toString() {
        return name.replace("_"," ") + ": " + sets + " sets of " + reps + " reps at " + weight + " kg";
    }
}
