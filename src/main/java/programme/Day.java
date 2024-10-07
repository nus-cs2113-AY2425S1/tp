package programme;

import java.util.ArrayList;

public class Day {
    private String dayName;
    private ArrayList<Exercise> exerciseList;

    public Day(String dayName, ArrayList<Exercise> exerciseList) {
        this.dayName = dayName;
        this.exerciseList = exerciseList;
    }

    public Day( ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public String getDayName() {
        return dayName;
    }

    public void insertExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public void deleteExercise(int index) {
        exerciseList.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(dayName).append("\n");
        for (Exercise exercise : exerciseList) {
            str.append("       - ").append(exercise.toString()).append("\n");
        }
        return str.toString();
    }
}
