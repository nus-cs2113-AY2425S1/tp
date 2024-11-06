package fittrack.healthprofile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailyIntake {

    private LocalDate date;
    private ArrayList<FoodEntry> foodEntries;
    private ArrayList<WaterEntry> waterEntries;
    private int totalCalories;
    private int totalWater;

    public DailyIntake(LocalDate date) {
        this.date = date;
        this.foodEntries = new ArrayList<>();
        this.waterEntries = new ArrayList<>();
        this.totalCalories = 0;
        this.totalWater = 0;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addFood(FoodEntry food) {
        foodEntries.add(food);
        totalCalories += food.getCalories();  // Add the food's calories to the total
    }

    public void addWater(int amount) {
        waterEntries.add(new WaterEntry(amount));  // Add water entry with timestamp
        totalWater += amount;  // Add water amount to total
    }

    public void addCalories(int calories) {
        totalCalories += calories;
    }

    public void listIntake() {
        System.out.println("Food Entries for " + date + ":");
        for (FoodEntry food : foodEntries) {
            // Access the timestamp from FoodEntry
            System.out.println(food.getFoodName() + " - "
                + food.getCalories() + " calories, added on "
                + food.getFormattedDateTime());

        }
        System.out.println("Total Calories: " + totalCalories);

        System.out.println("Water Entries for " + date + ":");
        for (WaterEntry water : waterEntries) {
            System.out.println(water.getAmount() + " ml, added on "
                + water.getFormattedDateTime());
        }
        System.out.println("Total Water: " + totalWater + " ml");
    }

    public static class WaterEntry {
        private int amount;
        private LocalDateTime dateTime;  // Store the date and time of the water intake

        public WaterEntry(int amount) {
            this.amount = amount;
            this.dateTime = LocalDateTime.now();  // Automatically set the timestamp to current time
        }

        public int getAmount() {
            return amount;
        }

        public LocalDateTime getDateTime() {
            return dateTime;  // Return the timestamp of the water entry
        }

        // Method to format the timestamp without milliseconds
        public String getFormattedDateTime() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return dateTime.format(formatter);  // Format date-time without milliseconds
        }


        @Override
        public String toString() {
            return amount + " ml, added on " + getFormattedDateTime();
        }
    }
}
