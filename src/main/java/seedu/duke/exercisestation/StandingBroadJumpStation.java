package seedu.duke.exercisestation;

import seedu.duke.Gender;
import seedu.duke.calculator.StandingBroadJumpCalculator;

public class StandingBroadJumpStation extends ExerciseStation {
    private String Name = "Standing Broad Jump Station";
    private int distance;

    public StandingBroadJumpStation() {
        this.distance = 0;
        this.points = 0;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int getPoints(Gender gender, int age) {
        points = StandingBroadJumpCalculator.calculatePoints(gender, age, distance);
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}