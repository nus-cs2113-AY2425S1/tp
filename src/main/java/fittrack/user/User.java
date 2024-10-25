package fittrack.user;

import fittrack.enums.Gender;
import fittrack.fitnessgoal.Goal;
import java.util.ArrayList;

public class User {

  public Gender gender;
  public int age;
  private final ArrayList<Goal> goals; // Updated goals to ArrayList<Goal>

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

  // Adds a Goal object to the goals list
  public void addGoal(Goal goal) {
    goals.add(goal);
  }

  // Deletes a goal by its description and returns true if the goal was found and removed
  public boolean deleteGoal(String goalDescription) {
    // Use the correct method to get the description of the goal
    return goals.removeIf(goal -> goal.getDescription().equals(goalDescription));
  }

  // Returns the list of Goal objects
  public ArrayList<Goal> getGoals() {
    return goals;
  }
}
