package fittrack.exercisestation;

import fittrack.calculator.SitUpCalculator;
import fittrack.enums.Gender;
import fittrack.user.User;

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

    @Override
    public void setPerformance(int performanceResult) {
        this.reps = performanceResult;
    }

    @Override
    public int getPoints(User user) {
        points = SitUpCalculator.calculatePoints(user.gender, user.age, reps);
        return points;
    }

    @Override
    public String getName() {
        return Name;
    }
}