package fittrack.user;
import fittrack.enums.Gender;
import fittrack.fitnessgoal.Goal;
import java.util.ArrayList;
import java.util.Iterator;

public class User {

    public Gender gender;
    public int age;
    private final ArrayList<Goal> goals;

    public User(String gender, String age) {
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.age = Integer.parseInt(age);
        this.goals = new ArrayList<>();
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

    public void addGoal(String goalDescription) {
        this.goals.add(Goal.fromSaveString(goalDescription));
    }


    public ArrayList<Goal> getGoals() {
        return this.goals;  // Ensure 'goals' is correctly initialized and updated
    }


    public boolean deleteGoal(String goalDescription) {
        Iterator<Goal> iterator = this.goals.iterator();
        while (iterator.hasNext()) {
            Goal goal = iterator.next();
            if (goal.getDescription().equals(goalDescription)) {
                iterator.remove(); // Safely remove the goal using the iterator
                return true; // Successfully deleted
            }
        }
        return false; // Goal not found
    }





    public void clearGoals() {
        this.goals.clear();
    }


    @Override
    public String toString () {
        return gender + " " + age;
    }

}
