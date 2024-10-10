package programme;

import com.google.gson.Gson;

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

    //Takes in an 'Update' Exercise object with the fields to be updated and assigns new values accordingly
    //If the field is "null" (i.e. -1 or " "), ignore that field for the update.
    public void updateExercise(Exercise update){
        if (update.sets != -1){
            sets = update.sets;
        }

        if (update.reps != -1){
            reps = update.reps;
        }

        if (update.weight != -1){
            weight = update.weight;
        }

        if (!update.name.isEmpty()){
            name = update.name;
        }
    }

    @Override
    public String toString() {
        return name + ": " + sets + " sets of " + reps + " reps at " + weight + " kg";
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Exercise fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Exercise.class);
    }
}
