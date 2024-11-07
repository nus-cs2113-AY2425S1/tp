package fittrack.healthprofile;

import fittrack.fitnessgoal.Goal;
import fittrack.storage.Saveable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
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
        return "Food" + "|" + foodName + "|" + calories + "|" + getFormattedDateTime();
    }


    public static FoodEntry fromSaveString(String saveString) {
        String[] stringData = saveString.split("\\|");

        // Check if the format is correct
        if (stringData.length < 4 || !stringData[0].equals("Food")) {
            throw new IllegalArgumentException("Invalid save string format for Food Entry");
        }

        String foodDescription = stringData[1];
        int calories = Integer.parseInt(stringData[2]);

        try {
            LocalDateTime foodEntryTime = LocalDateTime.parse(stringData[3],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return new FoodEntry(foodDescription, calories, foodEntryTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid save string format for Food Entry");
        }

        return null;
    }
}
