package fittrack.healthprofile;

import java.util.ArrayList;

public class CalorieIntake {
    private ArrayList<Integer> calorieEntries;

    public CalorieIntake() {
        this.calorieEntries = new ArrayList<>();
    }

    public void addCalories(int calories) {
        calorieEntries.add(calories);
        System.out.println("Added " + calories + " calories.");
    }

    public void deleteCalories(int index) {
        if (index >= 0 && index < calorieEntries.size()) {
            int removedCalories = calorieEntries.remove(index);
            System.out.println("Deleted " + removedCalories + " calories.");
        } else {
            System.out.println("Invalid index for calorie entry.");
        }
    }

    public void listCalories() {
        if (calorieEntries.isEmpty()) {
            System.out.println("No calorie intake recorded.");
        } else {
            System.out.println("Calorie intake:");
            for (int i = 0; i < calorieEntries.size(); i++) {
                System.out.println((i + 1) + ". " + calorieEntries.get(i) + " calories");
            }
        }
    }
}
