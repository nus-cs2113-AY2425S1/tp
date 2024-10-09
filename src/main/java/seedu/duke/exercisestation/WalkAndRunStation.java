package seedu.duke.exercisestation;

import seedu.duke.Gender;


public class WalkAndRunStation extends ExerciseStation {
    private String Name = "Walk and Run Station";
    private int time;

    public WalkAndRunStation() {
        this.time = 0;
        this.points = 0;
    }

    public double getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int getPoints(Gender gender, int age) {
        points = 1;  // Replace with actual point calculation logic
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}
