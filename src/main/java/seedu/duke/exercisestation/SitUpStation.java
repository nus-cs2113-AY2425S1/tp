package seedu.duke.exercisestation;

import seedu.duke.Gender;
import seedu.duke.calculator.SitUpCalculator;

public class SitUpStation extends ExerciseStation {
    private String Name = "Sit Up Station";
    private int reps;

    public SitUpStation() {
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
        points = SitUpCalculator.calculatePoints(gender, age, reps);
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}