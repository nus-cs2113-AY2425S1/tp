package fittrack.healthprofile;

import java.util.ArrayList;

public class FoodIntake {
    private ArrayList<FoodItem> foodItems;

    // Constructor to initialize the foodItems list
    public FoodIntake() {
        this.foodItems = new ArrayList<>();
    }

    // Class to represent a food item along with its calorie count
    public static class FoodItem {
        String foodName;
        int calories;

        public FoodItem(String foodName, int calories) {
            this.foodName = foodName;
            this.calories = calories;
        }

        @Override
        public String toString() {
            return foodName + " " + calories + " calories";
        }
    }

    // Method to add a food item with its calorie count
    public void addFood(String foodName, int calories) {
        foodItems.add(new FoodItem(foodName, calories));
        System.out.println("Got it. I've added food item: " + foodName + " " + calories + "( calories).");
    }

    // Method to delete a food item by index
    public void deleteFood(int index) {
        if (index >= 0 && index < foodItems.size()) {
            FoodItem removedFood = foodItems.remove(index);
            System.out.println("Got it. I've deleted food item: " + removedFood);
        } else {
            System.out.println("Invalid index for food entry.");
        }
    }

    // Method to list all food items and their calorie counts
    public void listFood() {
        if (foodItems.isEmpty()) {
            System.out.println("No food items recorded.");
        } else {
            System.out.println("Here is your food intake list: ");
            for (int i = 0; i < foodItems.size(); i++) {
                System.out.println((i + 1) + ". " + foodItems.get(i));
            }
        }
    }

    // Method to list daily food intake summary (food and calories together)
    public void listDailyIntake() {
        System.out.println("Your daily intake summary:");

        // Water Intake placeholder (just for completeness)
        System.out.println("Water Intake:\nNo water intake recorded.\n");

        // Food Intake
        System.out.println("Food Intake:");
        if (foodItems.isEmpty()) {
            System.out.println("No food items recorded.");
        } else {
            System.out.println("Here is your food intake list:");
            for (int i = 0; i < foodItems.size(); i++) {
                System.out.println((i + 1) + ". " + foodItems.get(i));
            }
        }
    }
}
