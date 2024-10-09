package seedu.duke.exercisestation;

import seedu.duke.Gender;
import seedu.duke.calculator.PullUpCalculator;

public class PullUpStation extends ExerciseStation {
    private String Name = "Pull Up Station";
    private int reps;

    public PullUpStation() {
        this.reps = 0;
        this.points = 0;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public int getPoints(Gender gender, int age) {
        points = PullUpCalculator.calculatePoints(gender, age, reps);
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }

    //@Override
    //public String toString() {
    //    return Integer.toString(reps) + " | " + Integer.toString(points);
    //}
}
