package programme;

public class Exercise {
    private int sets;
    private int reps;
    private int weight;
    private String exerciseName;

    public Exercise(int sets, int reps, int weight, String exerciseName) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.exerciseName = exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setExeciseName(String execiseName) {
        this.exerciseName = execiseName;
    }

    @Override
    public String toString(){
        return exerciseName + " - " + sets + " x " + reps + " X " + weight + " kgs ";
    }
}
