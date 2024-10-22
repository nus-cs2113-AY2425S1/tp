package fittrack.exercisestation;

import fittrack.user.User;

public abstract class ExerciseStation {

    static final int INVALID_TIME = -1;
    static final int DEFAULT_POINT = 0;
    static final int TIME_DIVISOR = 60;
    static final int DOUBLE_DIGIT = 10;

    protected int points;

    public abstract void setPerformance(int performanceResult);
    public abstract int getPoints(User user);

    public abstract String getName();

    @Override
    public abstract String toString();
}
