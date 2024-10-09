package seedu.duke;

import java.time.LocalDateTime;

public class TrainingSession{

    private LocalDateTime sessionDatetime;
    private String sessionDescription;

    private ExerciseStation[] exerciseData = {new PullUpStation(), new ShuttleRunStation(), new SitAndReachStation(),
            new SitUpStation(), new StandingBroadJumpStation(), new WalkAndRunStation()};

    TrainingSession(String datetime, String sessionDescription){
        this.sessionDatetime = LocalDateTime.parse(datetime + "-ss-ns");
        this.sessionDescription = sessionDescription;
    }

    //Edits session data
    public void editExercise(int exerciseNum, int reps){
        exerciseData[exerciseNum].setReps(reps);
        System.out.print("Exercise edited! Here's your new input: " + System.lineSeparator() +
                exerciseData[exerciseNum]);
    }
}
