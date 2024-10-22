package fittrack.exercisestation;

import fittrack.calculator.WalkAndRunCalculator;
import fittrack.user.User;

public class WalkAndRunStation extends ExerciseStation {
    private String name = "Walk and Run Station";
    private int time;

    public WalkAndRunStation() {
        this.time = INVALID_TIME;
        this.points = DEFAULT_POINT;
    }

    public String getTime() {
        if(time == INVALID_TIME) {
            return "NA";
        }
        return Integer.toString(time);
    }

    @Override
    public void setPerformance(int performanceResult) {
        this.time = performanceResult;
    }

    @Override
    public int getPoints(User user) {
        if(time == INVALID_TIME) {
            return DEFAULT_POINT;
        }
        points = WalkAndRunCalculator.calculatePoints(user.gender, user.age, time);
        return points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Time: " + getTime() + " | " + points + " points";
    }
}
