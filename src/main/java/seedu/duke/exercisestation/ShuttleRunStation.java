package seedu.duke.exercisestation;

import seedu.duke.Gender;


public class ShuttleRunStation extends ExerciseStation {
    private String Name = "Shuttle Run Station";
    private int time;

    public ShuttleRunStation() {
        time = 0;
        points = 0;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int getPoints(Gender gender, int age) {
        points = 1;  // Replace 1 with actual point calculation logic
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}