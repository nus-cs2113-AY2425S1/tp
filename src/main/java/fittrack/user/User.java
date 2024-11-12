package fittrack.user;
import fittrack.enums.Gender;
import fittrack.fitnessgoal.Goal;
import fittrack.storage.Saveable;

import java.util.ArrayList;
import java.util.Iterator;

import static fittrack.storage.Storage.DATA_DELIMITER;
import static fittrack.storage.Storage.DATA_DELIMITER_REGEX;

/**
 * Represents a user in the system, including their gender, age, and goals.
 * The User class allows modification of user attributes, and user data is saved
 * and loaded from a serialized format for persistent storage.
 */
public class User extends Saveable {

    public Gender gender;  // User's gender
    public int age;         // User's age
    private final ArrayList<Goal> goals;  // List of goals associated with the user

    /**
     * Constructor to initialize the user with gender and age.
     *
     * @param gender The gender of the user (e.g., "male", "female").
     * @param age The age of the user as a string (to be parsed into an integer).
     */

    public User(String gender, String age) {
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.age = Integer.parseInt(age);
        this.goals = new ArrayList<>();
    }

    /**
     * Setter for gender.
     *
     * @param gender The gender of the user as a string.
     */
    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    /**
     * Setter for age.
     *
     * @param age The age of the user as a string.
     */
    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    /**
     * Getter for gender.
     *
     * @return The gender of the user.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Getter for age.
     *
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Adds a goal to the user's list of goals.
     *
     * @param goalDescription The description of the goal.
     */
    public void addGoal(String goalDescription) {
        this.goals.add(Goal.fromSaveString(goalDescription));
    }

    /**
     * Getter for the list of goals.
     *
     * @return The list of goals.
     */
    public ArrayList<Goal> getGoals() {
        return this.goals;  // Ensure 'goals' is correctly initialized and updated
    }


    /**
     * Deletes a goal from the user's list of goals based on the description.
     *
     * @param goalDescription The description of the goal to be deleted.
     * @return true if the goal was found and deleted, false otherwise.
     */
    public boolean deleteGoal(String goalDescription) {
        Iterator<Goal> iterator = this.goals.iterator();  // Use an iterator to safely remove an element while iterating
        while (iterator.hasNext()) {
            Goal goal = iterator.next();
            if (goal.getDescription().equals(goalDescription)) {
                iterator.remove();  // Remove the goal if the description matches
                return true;  // Return true indicating successful deletion
            }
        }
        return false;  // Return false if the goal was not found
    }

    /**
     * Clears all goals from the user's list.
     */
    public void clearGoals() {
        this.goals.clear();  // Clear all goals from the list
    }

    /**
     * Converts the user object to a string representation.
     *
     * @return A string representation of the user (gender and age).
     */
    @Override
    public String toString () {
        return gender + " " + age;  // Return gender and age as a string
    }

    /**
     * Converts the user object to a string representation suitable for saving to a file.
     * The format is: {@code User|<gender>|<age>}.
     *
     * @return A string representing the user in a saveable format.
     */
    @Override
    public String toSaveString() {
        return "User" + DATA_DELIMITER + gender.toString() + DATA_DELIMITER + age;
    }


    /**
     * Creates a User object from a saved string representation.
     * The string format must be {@code User|<gender>|<age>}
     *
     * @param saveString The string representation of a saved user.
     * @return A User object created from the saved data.
     * @throws IllegalArgumentException If the string format is invalid or contains unrecognized data.
     */
    public static User fromSaveString(String saveString) {
        // Split the string by the '|' delimiter
        String[] stringData = saveString.split(DATA_DELIMITER_REGEX);

        try {
            // Check if the format is correct
            if (stringData.length != 3 || !stringData[0].equals("User")) {
                throw new IllegalArgumentException("Invalid save data for user detected");
            }

            if (!stringData[1].equals("MALE") && !stringData[2].equals("FEMALE")) {
                throw new IllegalArgumentException("Invalid stored sex information for user detected");
            }

            if (Integer.parseInt(stringData[2]) >= 25) {
                throw new IllegalArgumentException("Invalid stored age information for user detected");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new User(stringData[1], stringData[2]);
    }
}
