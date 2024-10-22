package fittrack.exercisestation;

import fittrack.user.User;

public abstract class ExerciseStation {
    protected int points;
    static final int INVALID_TIME = -1;
    static final int DEFAULT_POINT = 0;

    public abstract void setPerformance(int performanceResult);
    public abstract int getPoints(User user);

    public abstract String getName();

    @Override
    public abstract String toString();
}
