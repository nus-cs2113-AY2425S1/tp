package fittrack.trainingsession;

import fittrack.exercisestation.ExerciseStation;
import fittrack.exercisestation.PullUpStation;
import fittrack.exercisestation.ShuttleRunStation;
import fittrack.exercisestation.SitAndReachStation;
import fittrack.exercisestation.SitUpStation;
import fittrack.exercisestation.StandingBroadJumpStation;
import fittrack.exercisestation.WalkAndRunStation;
import fittrack.user.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TrainingSession{

    static final int NUM_OF_EXERCISES = 6;
    static final int MAX_POINT = 5;
    static final int GOLD_GRADE = 3;
    static final int GOLD_POINT = 21;
    static final int SILVER_GRADE = 2;
    static final int SILVER_POINT = 15;
    static final int BRONZE_GRADE = 1;
    static final int BRONZE_POINT = 6;

    static final String GOLD_STRING = "Gold";
    static final String SILVER_STRING = "Silver";
    static final String BRONZE_STRING = "Bronze";
    static final String NO_AWARD = "No Award";

    private LocalDateTime sessionDatetime;
    private String sessionDescription;
    private User user;

    private ExerciseStation[] exerciseData = {new PullUpStation(), new ShuttleRunStation(), new SitAndReachStation(),
        new SitUpStation(), new StandingBroadJumpStation(), new WalkAndRunStation()};

    public TrainingSession(String datetime, String sessionDescription, User user) {
        this.sessionDatetime = LocalDateTime.parse(datetime);
        this.sessionDescription = sessionDescription;
        this.user = user;
    }

    private int processReps(int exerciseNum, String reps){
        if(exerciseNum == 1) {
            reps = reps.replace("\\.", "");
            return Integer.parseInt(reps);
        } else if(exerciseNum == 5) {
            String[] minutesSeconds = reps.split(":");
            int minutesInSeconds = Integer.parseInt(minutesSeconds[0])*60;
            int seconds = Integer.parseInt(minutesSeconds[1]);
            return minutesInSeconds + seconds;
        } else{
            return Integer.parseInt(reps);
        }
    }

    //Edits session data
    public void editExercise(int exerciseNum, String reps) {
        assert exerciseNum >= 0 && exerciseNum <= 5;
        int actualReps = processReps(exerciseNum, reps);
        exerciseData[exerciseNum].setPerformance(actualReps);
        exerciseData[exerciseNum].getPoints(user);
        System.out.print("Exercise edited! Here's your new input: " +
                exerciseData[exerciseNum] + System.lineSeparator());
    }

    //Returns string for award attained
    private String award(int minPoint, int totalPoints) {
        if(minPoint >= GOLD_GRADE && totalPoints >= GOLD_POINT) {
            return GOLD_STRING;
        } else if(minPoint >= SILVER_GRADE && totalPoints >= SILVER_POINT) {
            return SILVER_STRING;
        } else if(minPoint >= BRONZE_GRADE && totalPoints >= BRONZE_POINT) {
            return BRONZE_STRING;
        } else{
            return NO_AWARD;
        }
    }

    public String getSessionDescription() {
        return (this.sessionDescription + " | " +
                this.sessionDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    public void printSessionDescription() {
        System.out.print(this.sessionDescription + " | " +
                this.sessionDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + System.lineSeparator());
    }

    //Print out all exercise data, including the total points and award given
    public void viewSession() {
        int totalPoints = 0;
        int minPoint = MAX_POINT;
        int exercisePoint;

        System.out.print("Training Session: " + this.sessionDescription + System.lineSeparator() +
                "Training Datetime: " + this.sessionDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + System.lineSeparator());

        for(int i = 0; i < NUM_OF_EXERCISES; i++) {
            exercisePoint = exerciseData[i].getPoints(user);
            totalPoints += exercisePoint;
            if(minPoint > exercisePoint) {
                minPoint = exercisePoint;
            }
            System.out.print(exerciseData[i].getName() + " | " +
                    exerciseData[i] + System.lineSeparator());
        }

        System.out.print("Total points: " + totalPoints + System.lineSeparator() +
                "Overall Award: " + award(minPoint, totalPoints) + System.lineSeparator());
    }
}
