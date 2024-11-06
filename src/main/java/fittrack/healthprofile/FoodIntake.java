package fittrack.healthprofile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FoodIntake {
    private ArrayList<FoodItem> foodItems;

    public FoodIntake() {
        this.foodItems = new ArrayList<>();
    }

    public static class FoodItem {
        String foodName;
        int calories;
        LocalDateTime dateTime;

        public FoodItem(String foodName, int calories) {
            this.foodName = foodName;
            this.calories = calories;
            this.dateTime = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return foodName + " (" + calories + " calories) at " + formatDateTime(dateTime);
        }
    }

    public void addFood(String foodName, int calories) {
        foodItems.add(new FoodItem(foodName, calories));

        // Format the current timestamp for printing without milliseconds
        String formattedDateTime = formatDateTime(LocalDateTime.now());

        System.out.println("Got it. I've added food item: " + foodName
            + " (" + calories + " calories, " + formattedDateTime + ").");
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);  // Format date-time without milliseconds
    }

    public void deleteFood(int index) {
        if (index >= 0 && index < foodItems.size()) {
            FoodItem removedFood = foodItems.remove(index);
            System.out.println("Got it. I've deleted food item: " + removedFood);
        } else {
            System.out.println("Invalid index for food entry.");
        }
    }

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

    public void listDailyIntake() {
        System.out.println("Here is your daily intake summary:");

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
