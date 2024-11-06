package fittrack.healthprofile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FoodEntry {
    private String foodName;
    private int calories;
    private LocalDateTime dateTime;  // Timestamp of when the food item is added

    public FoodEntry(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
        this.dateTime = LocalDateTime.now();  // Set the timestamp to current time when food is added
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;  // Return the timestamp of the food entry
    }

    // Method to format the timestamp without milliseconds
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);  // Format date-time without milliseconds
    }

    @Override
    public String toString() {
        return foodName + " - " + calories + " calories, added on " + getFormattedDateTime();
    }
}
