package fittrack.exercisestation;

import fittrack.calculator.ShuttleRunCalculator;
import fittrack.user.User;

public class ShuttleRunStation extends ExerciseStation {
    private String name = "Shuttle Run Station";
    private int time;

    public ShuttleRunStation() {
        time = 500;
        points = 0;
    }

    public int getTime() {
        return time;
    }

    @Override
    public void setPerformance(int performanceResult) {
        this.time = performanceResult;
    }

    @Override
    public int getPoints(User user) {
        points = ShuttleRunCalculator.calculatePoints(user.gender, user.age, time);
        return points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Time: " + time + "s | " + points + " points";
    }
}
