package fittrack.healthprofile;

import java.time.LocalDate;
import java.util.ArrayList;

import static fittrack.messages.Messages.SEPARATOR;

public class FoodWaterIntake {

    private ArrayList<FoodEntry> foodList = new ArrayList<>();
    private ArrayList<WaterEntry> waterList = new ArrayList<>();

    public void addFood(FoodEntry food) {
        foodList.add(food);
    }

    public void deleteFood(int foodIndex) {

        if (foodIndex >= 0 && foodIndex < foodList.size()) {
            FoodEntry removed = foodList.remove(foodIndex);
            System.out.println("Got it. I've deleted food item: " + removed);
        } else {
            System.out.println("Invalid index for food entry.");
        }
    }

    public void addWater(WaterEntry water) {
        waterList.add(water);
    }

    public void deleteWater(int waterIndex) {
        if (waterIndex >= 0 && waterIndex < waterList.size()) {
            WaterEntry removed = waterList.remove(waterIndex);
            System.out.println("Got it. I've deleted water item: " + removed);
        } else {
            System.out.println("Invalid index for water entry.");
        }
    }

    // Lists all food/water intake for the current day from 00:00 local time
    public void listDailyIntake() {

        System.out.println(SEPARATOR);
        listDailyFoodIntake();
        System.out.print(System.lineSeparator());
        listDailyWaterIntake();
        System.out.println(SEPARATOR);
    }

    public void listDailyFoodIntake() {
        LocalDate currentDate = LocalDate.now();

        if (foodList.isEmpty()) {
            System.out.println("No food items recorded.");
            return;
        }

        int dailyTotalCalories = 0;
        int listIndex = 1;

        System.out.println("Food Entries for " + currentDate + ":");

        // Determine if food entry is from current date. If it is, print.
        for (FoodEntry entry : foodList) {
            if (entry.getLocalDate().equals(currentDate)) {
                System.out.print(listIndex + ". ");
                System.out.println(entry.getFoodName() + " - " + entry.getCalories()
                        + " calories, added on " + entry.getFormattedDateTime());
                dailyTotalCalories += entry.getCalories();
                listIndex++;
            }
        }
        System.out.println("Total daily Calories: " + dailyTotalCalories);
    }

    public void listDailyWaterIntake() {
        LocalDate currentDate = LocalDate.now();

        if (waterList.isEmpty()) {
            System.out.println("No water intake recorded.");
            return;
        }

        int dailyTotalWater = 0;
        int listIndex = 1;

        System.out.println("Water Entries for " + currentDate + ":");

        // Determine if food entry is from current date
        for (WaterEntry entry : waterList) {
            if (entry.getLocalDate().equals(currentDate)) {
                System.out.print(listIndex + ". ");
                System.out.println(entry.getAmount() + " ml, added on "
                        + entry.getFormattedDateTime());
                dailyTotalWater += entry.getAmount();
                listIndex++;
            }
        }
        System.out.println("Total daily water: " + dailyTotalWater + " ml");
    }

    public ArrayList<FoodEntry> getFoodList() {
        return foodList;
    }

    public ArrayList<WaterEntry> getWaterList() {
        return waterList;
    }
}
