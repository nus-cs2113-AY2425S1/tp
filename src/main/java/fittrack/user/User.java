package fittrack.user;
import fittrack.enums.Gender;
import fittrack.fitnessgoal.Goal;
import fittrack.healthprofile.CalorieIntake;
import fittrack.healthprofile.FoodIntake;
import fittrack.healthprofile.WaterIntake;
import fittrack.trainingsession.MoodLog;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User {

    public Gender gender;
    public int age;
    private final ArrayList<Goal> goals;
    private WaterIntake waterIntake;
    private FoodIntake foodIntake;
    private CalorieIntake calorieIntake;
    private final ArrayList<fittrack.trainingsession.MoodLog> moodLogs; // Correctly defined as MoodLog

    public User(String gender, String age) {
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.age = Integer.parseInt(age);
        this.goals = new ArrayList<>();
        this.waterIntake = new WaterIntake();
        this.foodIntake = new FoodIntake();
        this.calorieIntake = new CalorieIntake();
        this.moodLogs = new ArrayList<>();
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public boolean deleteGoal(String goalDescription) {
        return goals.removeIf(goal -> goal.getDescription().equals(goalDescription));
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    @Override
    public String toString () {
        return gender + " " + age;
    }
    
    public WaterIntake getWaterIntake() {
        return waterIntake;
    }

    public FoodIntake getFoodIntake() {
        return foodIntake;
    }

    public CalorieIntake getCalorieIntake() {
        return calorieIntake;
    }
    public void addMoodLog(fittrack.trainingsession.MoodLog moodLog) {
        moodLogs.add(moodLog);
    }

    public void editMoodLog(int moodId, String newMood, LocalDateTime newDateTime, String newDescription) {
        if (moodId < 0 || moodId >= moodLogs.size()) {
            System.out.println("Invalid mood ID. Please provide a numeric ID between 0 and " + (moodLogs.size() - 1));
            return;
        }
        // Proceed with editing the mood log
        MoodLog moodLog = moodLogs.get(moodId);
        moodLog.setMood(newMood);
        moodLog.setTimestamp(newDateTime);
        moodLog.setDescription(newDescription);
        System.out.println("Mood log updated: " + moodLog);
    }

    public void listMoodLogs() {
        if (moodLogs.isEmpty()) {
            System.out.println("No mood logs available.");
        } else {
            System.out.println("Your mood logs:");
            for (int i = 0; i < moodLogs.size(); i++) {
                System.out.println((i + 1) + ". " + moodLogs.get(i));
            }
        }
    }

    public void deleteMoodLog(int moodId) {
        if (moodId < 1 || moodId > moodLogs.size()) {
            throw new IndexOutOfBoundsException("Invalid mood ID.");
        }
        moodLogs.remove(moodId - 1); // Remove the mood log at the specified index (adjusting for 0-based index)
    }


    public ArrayList<fittrack.trainingsession.MoodLog> getMoodLogs() {
        return moodLogs;
    }
}
