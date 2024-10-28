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

    public DailyRecord(Day day) {
        assert day != null : "day must not be null";

        mealList = new MealList();
        water = new Water();
        this.day = day;
        logger.info("Record initialised with day: " + day);
    }

    public DailyRecord(Water water) {
        assert water != null : "water must not be null";

        mealList = new MealList();
        this.water = water;
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
        logger.info("Record initialised with water list");
    }

    public DailyRecord(MealList mealList) {
        assert mealList != null : "mealList must not be null";

        this.mealList = mealList;
        water = new Water();
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
        logger.info("Record initialised with meal list");
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

    public void deleteMealFromRecord(int index) {
        assert index > 0;

        mealList.deleteMeal(index);
        logger.info("meal deleted, index: " + index);
    }

    public void addWaterToRecord(float toAddWater) {
        assert toAddWater > 0;

        water.addWater(toAddWater);
        logger.info("Water added: " + toAddWater);
    }

    public void removeWaterfromRecord(int index) {
        water.deleteWater(index);
    }

    public int getCaloriesFromMeal() {
        int caloriesMeal = 0;
        for (Meal meal : mealList.getMeals()) {
            assert meal != null : "meal must not be null";
            caloriesMeal += meal.getCalories();
        }
        logger.info("Calories from meals calculated: " + caloriesMeal);
        return caloriesMeal;
    }

    public float getTotalWaterIntake() {
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

        if (day != null && day.getExercisesCount() > 0) {
            result.append("Day: \n").append(day.toString()).append("\n");
        } else {
            result.append("Day: No record.\n\n");
        }

        if (!mealList.getMeals().isEmpty()) {
            result.append("Meals: \n").append(mealList).append("\n");
            result.append("Total Calories from Meals: ").append(getCaloriesFromMeal()).append(" kcal\n\n");
        } else {
            result.append("Meals: No record.\n\n");
        }

        if (!water.getWaterList().isEmpty()) {
            result.append("Water Intake: ").append(water).append("\n");
            result.append("Total Water Intake: ").append(getTotalWaterIntake()).append(" liters");
        } else {
            result.append("Water Intake: No record.");
        }

        return result.toString();
    }
}
