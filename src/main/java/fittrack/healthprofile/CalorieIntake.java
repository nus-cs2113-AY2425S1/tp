package fittrack.healthprofile;

import java.util.ArrayList;

public class CalorieIntake {
    private ArrayList<Integer> calorieEntries;

    public CalorieIntake() {
        this.calorieEntries = new ArrayList<>();
    }

    public void addCalories(int calories) {
        calorieEntries.add(calories);
        System.out.println("Got it. I've added calorie intake: " + calories + " calories.");
    }

    public void deleteCalories(int index) {
        if (index >= 0 && index < calorieEntries.size()) {
            int removedCalories = calorieEntries.remove(index);
            System.out.println("Got it. I've deleted the calorie intake. " + removedCalories + " calories.");
        } else {
            System.out.println("Invalid index for calorie entry.");
        }
    }
}
