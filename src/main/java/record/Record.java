package record;

import meal.Meal;
import meal.MealList;
import programme.Day;
import storage.Storage;
import water.Water;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Record {
    private static final Logger logger = Logger.getLogger(Record.class.getName());
    private Day day;
    private MealList mealList;
    private Water water;

    public Record() { //should not be called
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
        this.mealList = new MealList();
        this.water = new Water();

    }

    public Record(Day day) {
        assert day != null;

        mealList = new MealList();
        water = new Water();
        this.day = day;
    }

    public Record(Water water) {

        assert water != null;
        mealList = new MealList();
        this.water = water;
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
    }

    public Record(MealList mealList) {
        assert mealList != null;

        this.mealList = mealList;
        water = new Water();
        day = new Day("Empty Day"); //This will be replaced when a Day is recorded
    }

    public void updateDay(Day newDay) { //this replaces any current day recorded
        assert day != null;
        this.day = newDay;
    }

    public void updateMealList(Meal meal) {
        assert meal != null;
        mealList.addMeal(meal);
    }

    public void updateWater(float toAddWater) {
        assert toAddWater > 0;
        water.addWater(toAddWater);
    }

    public int getCaloriesFromMeal() {
        int caloriesMeal = 0;
        for (Meal meal : mealList.getMeals()) {
            caloriesMeal += meal.getCalories();
        }
        return caloriesMeal;
    }

    public float getTotalWaterIntake() {
        float totalWater = 0;
        for (Float waterAmount : water.getWaterList()) {
            totalWater += waterAmount;
        }
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
