package fittrack.healthprofile;

import java.time.LocalDate;
import java.util.ArrayList;

import static fittrack.messages.Messages.SEPARATOR;


/**
 * Manages daily food and water intake by storing food and water entries and
 * providing methods to add, delete, and list daily intake records.
 */
public class FoodWaterIntake {

    private ArrayList<FoodEntry> foodList = new ArrayList<>();
    private ArrayList<WaterEntry> waterList = new ArrayList<>();

    /**
     * Adds a food entry to the food list.
     *
     * @param food The food entry to be added.
     */
    public void addFood(FoodEntry food) {
        foodList.add(food);
    }

    /**
     * Deletes a food entry from the food list by index.
     *
     * @param foodIndex The index of the food entry to delete.
     */
    public void deleteFood(int foodIndex) {

        if (foodIndex >= 0 && foodIndex < foodList.size()) {
            FoodEntry removed = foodList.remove(foodIndex);
            System.out.println("Got it. I've deleted food item: " + removed);
        } else {
            System.out.println("Invalid index for food entry.");
        }
    }

    /**
     * Adds a water entry to the water list.
     *
     * @param water The water entry to be added.
     */
    public void addWater(WaterEntry water) {
        waterList.add(water);
    }

    /**
     * Deletes a water entry from the water list by index.
     *
     * @param waterIndex The index of the water entry to delete.
     */
    public void deleteWater(int waterIndex) {
        if (waterIndex >= 0 && waterIndex < waterList.size()) {
            WaterEntry removed = waterList.remove(waterIndex);
            System.out.println("Got it. I've deleted water item: " + removed);
        } else {
            System.out.println("Invalid index for water entry.");
        }
    }

    /**
     * Lists all food and water intake entries for the current day.
     */
    public void listDailyIntake() {

        System.out.println(SEPARATOR);
        listDailyFoodIntake();
        System.out.print(System.lineSeparator());
        listDailyWaterIntake();
        System.out.println(SEPARATOR);
    }

    /**
     * Lists all food entries recorded for the current day, including total calories.
     */
    public void listDailyFoodIntake() {
        LocalDate currentDate = LocalDate.now();

        if (foodList.isEmpty()) {
            System.out.println("No food items recorded.");
            return;
        }

        int dailyTotalCalories = 0;
        int listIndex = 1;

        System.out.println("Food Entries for " + currentDate + ":");

        // Print each food entry if it was added on the current date
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

    /**
     * Lists all water intake entries recorded for the current day, including total water intake.
     */
    public void listDailyWaterIntake() {
        LocalDate currentDate = LocalDate.now();

        if (waterList.isEmpty()) {
            System.out.println("No water intake recorded.");
            return;
        }

        int dailyTotalWater = 0;
        int listIndex = 1;

        System.out.println("Water Entries for " + currentDate + ":");

        // Print each water entry if it was added on the current date
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

    /**
     * Returns the list of all recorded food entries.
     *
     * @return ArrayList of FoodEntry objects.
     */
    public ArrayList<FoodEntry> getFoodList() {
        return foodList;
    }

    /**
     * Returns the list of all recorded water entries.
     *
     * @return ArrayList of WaterEntry objects.
     */
    public ArrayList<WaterEntry> getWaterList() {
        return waterList;
    }
}
