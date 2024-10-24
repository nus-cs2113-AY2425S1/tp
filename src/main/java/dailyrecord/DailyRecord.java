package dailyrecord;

import meal.Meal;
import meal.MealList;
import programme.Day;
import water.Water;

import java.util.logging.Logger;

public class DailyRecord {
    private static final Logger logger = Logger.getLogger(Record.class.getName());
    private Day day;
    private MealList mealList;
    private Water water;

    public DailyRecord() { //should not be called
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
        this.mealList = new MealList();
        this.water = new Water();
    }

    public DailyRecord(Day day) {
        assert day != null;

        mealList = new MealList();
        water = new Water();
        this.day = day;
        logger.info("Record initialised with day: " + day);
    }

    public DailyRecord(Water water) {
        assert water != null;

        mealList = new MealList();
        this.water = water;
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
        logger.info("Record initialised with water list");
    }

    public DailyRecord(MealList mealList) {
        assert mealList != null;

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

    public void updateDay(Day newDay) { //this replaces any current day recorded
        assert day != null;

        this.day = newDay;
        logger.info("Day updated: " + day);
    }

    public void addMealList(Meal meal) {
        assert meal != null;

        mealList.addMeal(meal);
        logger.info("meal added: " + meal);
    }

    public void deleteMealFromMealList(int index) {
        assert index > 0;

        mealList.deleteMeal(index);
        logger.info("meal deleted, index: " + index);
    }

    public void updateWater(float toAddWater) {
        assert toAddWater > 0;

        water.addWater(toAddWater);
        logger.info("Water added: " + toAddWater);
    }

    public int getCaloriesFromMeal() {
        int caloriesMeal = 0;
        for (Meal meal : mealList.getMeals()) {
            caloriesMeal += meal.getCalories();
        }
        logger.info("Calories from meals caluated: " + caloriesMeal);
        return caloriesMeal;
    }

    public float getTotalWaterIntake() {
        float totalWater = 0;
        for (Float waterAmount : water.getWaterList()) {
            totalWater += waterAmount;
        }
        logger.info("total water: " + totalWater);
        return totalWater;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        if (day.getExercisesCount() > 0) {
            result.append("Day: ").append(day.toString()).append("\n");
        } else {
            result.append("Day: No record.\n");
        }

        if (!mealList.getMeals().isEmpty()) {
            result.append("Meals: ").append(mealList.toString()).append("\n");
            result.append("Total Calories from Meals: ").append(getCaloriesFromMeal()).append(" kcal\n");
        } else {
            result.append("Meals: No record.\n");
        }

        if (!water.getWaterList().isEmpty()) {
            result.append("Water Intake: ").append(water.toString()).append("\n");
            result.append("Total Water Intake: ").append(getTotalWaterIntake()).append(" liters\n");
        } else {
            result.append("Water Intake: No record.\n");
        }

        return result.toString();
    }
}
