package fittrack.exercisestation;

import fittrack.user.User;

public class WalkAndRunStation extends ExerciseStation {
    private String name = "Walk and Run Station";
    private int time;

    public WalkAndRunStation() {
        this.time = 0;
        this.points = 0;
    }

    public double getTime() {
        return time;
    }

    @Override
    public void setPerformance(int performanceResult) {
        this.time = performanceResult;
    }

    @Override
    public int getPoints(User user) {
        points = 1;  // Replace with actual point calculation logic
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
