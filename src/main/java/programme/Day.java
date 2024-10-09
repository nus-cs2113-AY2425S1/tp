package programme;

import java.util.ArrayList;

public class Day {
    private String dayName;
    private ArrayList<Exercise> exerciseList;

    public Day(String dayName, ArrayList<Exercise> exerciseList) {
        this.dayName = dayName;
        this.exerciseList = exerciseList;
    }

    public Day(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void insertExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public void deleteExercise(int index) {
        exerciseList.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(dayName).append(":\n");

        for (int i = 0; i < exerciseList.size(); i++) {
            Exercise exercise = exerciseList.get(i);
            result.append(String.format("  %d. %s%n", i + 1, exercise));
        }

        return result.toString();
    }
}
