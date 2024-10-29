package history;

import meal.Meal;
import meal.MealList;
import programme.Day;
import water.Water;

import java.util.logging.Logger;

public class DailyRecord {
    private static final Logger logger = Logger.getLogger(DailyRecord.class.getName());
    private Day day;
    private final MealList mealList;
    private final Water water;

    public DailyRecord() {
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
        this.mealList = new MealList();
        this.water = new Water();
    }

    public Day getDayFromRecord() {
        return day;
    }

    public MealList getMealList() {
        return mealList;
    }

    public Water getWater() {
        return water;
    }

    public void logDay(Day newDay) { //this replaces any current day recorded
        assert day != null : "day must not be null";

        this.day = newDay;
        logger.info("Day updated: " + day);
    }

    public void addMealToRecord(Meal meal) {
        assert meal != null;

        mealList.addMeal(meal);
        logger.info("meal added: " + meal);
    }

    public Meal deleteMealFromRecord(int index) {
        assert index >= 0;

        logger.info("meal deleted, index: " + index);
        return mealList.deleteMeal(index);
    }

    public void addWaterToRecord(float toAddWater) {
        assert toAddWater >= 0;

        water.addWater(toAddWater);
        logger.info("Water added: " + toAddWater);
    }

    public float removeWaterFromRecord(int index) {
        return water.deleteWater(index);
    }

    private int getCaloriesFromMeals() {
        int caloriesMeal = 0;
        for (Meal meal : mealList.getMeals()) {
            assert meal != null : "meal must not be null";
            caloriesMeal += meal.getCalories();
        }
        logger.info("Calories from meals calculated: " + caloriesMeal);
        return caloriesMeal;
    }

    private float getTotalWaterIntake() {
        float totalWater = 0;
        for (Float waterAmount : water.getWaterList()) {
            assert waterAmount != null : "water must not be null";
            totalWater += waterAmount;
        }
        logger.info("total water: " + totalWater);
        return totalWater;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int caloriesBurnt = day.getTotalCaloriesBurnt();
        int caloriesGained = getCaloriesFromMeals();

        result.append("Day: \n");
        if (day != null && day.getExercisesCount() > 0) {
            result.append(day.toString()).append("\n");
            result.append("Total Calories burnt: ").append(caloriesBurnt).append(" kcal\n\n");
        } else {
            result.append("No Day.\n\n");
        }

        result.append("Meals: \n");
        if (!mealList.getMeals().isEmpty()) {
            result.append(mealList).append("\n");
            result.append("Total Calories from Meals: ").append(caloriesGained).append(" kcal\n\n");
        } else {
            result.append("No Meals.\n\n");
        }

        result.append("Water Intake: \n");
        if (!water.getWaterList().isEmpty()) {
            result.append(water).append("\n");
            result.append("Total Water Intake: ").append(getTotalWaterIntake()).append(" liters");
        } else {
            result.append("No Water.\n\n");
        }

        result.append("Caloric Balance: ").append(caloriesGained - caloriesBurnt).append(" kcal");
        return result.toString();
    }
}
