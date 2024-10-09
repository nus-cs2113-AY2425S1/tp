package fittrack.exercisestation;

import fittrack.calculator.SitAndReachCalculator;
import fittrack.enums.Gender;

public class SitAndReachStation extends ExerciseStation {
    private String Name = "Sit and Reach Station";
    private int length;

    public SitAndReachStation() {
        this.length = 0;
        this.points = 0;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getPoints(Gender gender, int age) {
        points = SitAndReachCalculator.calculatePoints(gender, age, length);
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}