package seedu.duke.exercisestation;

import seedu.duke.Gender;

public abstract class ExerciseStation {
    protected int points;

    public abstract int getPoints(Gender gender, int age);

    public abstract String getName();

    //@Override
    //public abstract String toString();
}
