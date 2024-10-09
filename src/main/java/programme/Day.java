package programme;

import java.util.ArrayList;

public class Day {
    private String name;
    private ArrayList<Exercise> exercises;

    public Day(String name, ArrayList<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }

    public Day(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Day(String name){
        this.name = name;
        this.exercises = new ArrayList<>();
    }

    public void insertExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void deleteExercise(int index) {
        exercises.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(":\n");

        for (int i = 0; i < exercises.size(); i++) {
            Exercise exercise = exercises.get(i);
            result.append(String.format("%d. %s%n", i + 1, exercise));
        }

        return result.toString();
    }
}
