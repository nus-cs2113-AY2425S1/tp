package fittrack.user;
import fittrack.enums.Gender;
import fittrack.fitnessgoal.Goal;
import fittrack.healthprofile.CalorieIntake;
import fittrack.healthprofile.FoodIntake;
import fittrack.healthprofile.WaterIntake;
import java.util.ArrayList;

public class User {

    public Gender gender;
    public int age;
    private final ArrayList<Goal> goals;
    private WaterIntake waterIntake;
    private FoodIntake foodIntake;
    private CalorieIntake calorieIntake;

    public User(String gender, String age) {
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.age = Integer.parseInt(age);
        this.goals = new ArrayList<>();
        this.waterIntake = new WaterIntake();
        this.foodIntake = new FoodIntake();
        this.calorieIntake = new CalorieIntake();
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
}
