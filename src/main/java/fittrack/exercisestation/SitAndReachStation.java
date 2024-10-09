package fittrack.exercisestation;

import fittrack.calculator.SitAndReachCalculator;
import fittrack.enums.Gender;
import fittrack.user.User;

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

    @Override
    public void setPerformance(int performanceResult) {
        this.length = performanceResult;
    }

    @Override
    public int getPoints(User user) {
        points = SitAndReachCalculator.calculatePoints(user.gender, user.age, length);
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}