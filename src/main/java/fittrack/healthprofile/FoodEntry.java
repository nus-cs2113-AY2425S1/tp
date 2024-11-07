package fittrack.healthprofile;

import fittrack.storage.Saveable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FoodEntry extends Saveable {
    private String foodName;
    private int calories;
    private LocalDateTime dateTime;  // Timestamp of when the food item is added

    public FoodEntry(String inputFoodName, int inputCalories, LocalDateTime inputDateTime) {
        this.foodName = inputFoodName ;
        this.calories = inputCalories;
        this.dateTime = inputDateTime;  }

    public String getFoodName() {
        return foodName;
    }

    public int getCalories() {
        return calories;
    }

    // Method to format the timestamp without milliseconds
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);  // Format date-time without milliseconds
    }

    public LocalDate getLocalDate() {
        return dateTime.toLocalDate();
    }

    @Override
    public String toString() {
        return foodName + " - " + calories + " calories, added on " + getFormattedDateTime();
    }

    @Override
    public String toSaveString() {
        return foodName + " | " + calories + " | " + getFormattedDateTime();
    }
}
