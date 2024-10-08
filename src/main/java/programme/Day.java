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

    public String getDayName() {
        return dayName;
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void insertExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }

    public void deleteExercise(int index) {
        exerciseList.remove(index - 1);
    }

    public String toString(boolean partOfProgramme) {
        StringBuilder str = new StringBuilder();
        str.append(dayName).append("\n");

        if (partOfProgramme) {
            for (Exercise exercise : exerciseList) {
                str.append(exercise.toString()).append("\n");
            }
        }else {
            for (Exercise exercise : exerciseList) {
                str.append("       - ").append(exercise.toString()).append("\n");
            }
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return toString(false);  // By default, assume it is part of a larger structure
    }
}
