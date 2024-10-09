package fittrack.trainingsession;

import fittrack.exercisestation.*;

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

    private ExerciseStation[] exerciseData = {new PullUpStation(), new ShuttleRunStation(), new SitAndReachStation(),
            new SitUpStation(), new StandingBroadJumpStation(), new WalkAndRunStation()};

    public TrainingSession(String datetime, String sessionDescription){
        this.sessionDatetime = LocalDateTime.parse(datetime + "-ss-ns");
        this.sessionDescription = sessionDescription;
    }

    //Edits session data
    public void editExercise(int exerciseNum, int reps){
        exerciseData[exerciseNum].setPerformance(reps);
        System.out.print("Exercise edited! Here's your new input: " + System.lineSeparator() +
                exerciseData[exerciseNum]);
    }

    //Returns string for award attained
    private String award(int minPoint, int totalPoints){
        if(minPoint >= GOLD_GRADE && totalPoints >= GOLD_POINT){
            return GOLD_STRING;
        }
        else if(minPoint >= SILVER_GRADE && totalPoints >= SILVER_POINT){
            return SILVER_STRING;
        }
        else if(minPoint >= BRONZE_GRADE && totalPoints >= BRONZE_POINT){
            return BRONZE_STRING;
        }
        else{
            return NO_AWARD;
        }
    }

    //Print out all exercise data, including the total points and award given
    public void viewSession(){
        int totalPoints = 0;
        int minPoint = MAX_POINT;
        int exercisePoint;

        System.out.print("Training Session: " + this.sessionDescription + System.lineSeparator() +
                "Training Datetime: " + this.sessionDatetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + System.lineSeparator());

        for(int i = 0; i < NUM_OF_EXERCISES; i++){
            exercisePoint = exerciseData[i].getPoints();
            totalPoints += exercisePoint;
            if(minPoint < exercisePoint){
                minPoint = exercisePoint;
            }
            System.out.print(exerciseData[i].getName() + " | " +
                    exerciseData[i] + System.lineSeparator());
        }

        System.out.print("Total points: " + totalPoints + System.lineSeparator() +
                "Overall Award: " + award(minPoint, totalPoints));
    }
}
