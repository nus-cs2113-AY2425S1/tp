package fittrack.exercisestation;

import fittrack.user.User;

public class ShuttleRunStation extends ExerciseStation {
    private String name = "Shuttle Run Station";
    private int time;

    public ShuttleRunStation() {
        time = 0;
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
        points = 1;  // Replace 1 with actual point calculation logic
        return points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return Integer.toString(time) + " | " + Integer.toString(points);
    }
}
