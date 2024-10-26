package meal;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MealList {

    private static final Logger logger = Logger.getLogger(MealList.class.getName());
    private ArrayList<Meal> meals;

    public MealList() {
        meals = new ArrayList<>();
        logger.log(Level.INFO, "MealList created with an empty list.");
    }

    public void addMeal(Meal meal) {
        assert meal != null : "Meal cannot be null";
        meals.add(meal);
        logger.log(Level.INFO, "Added meal: {0}. Current list: {1}", new Object[]{meal, meals});
    }

    public Meal deleteMeal(int index) {
        if (index < 0 || index >= meals.size()) {
            logger.log(Level.WARNING, "Invalid index for deletion: {0}", index);
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for the meal list.");
        }

        Meal mealToBeDeleted = meals.get(index);
        meals.remove(index);
        logger.log(Level.INFO, "Deleted meal: {0} at index {1}. Current list: {2}",
                new Object[]{mealToBeDeleted, index, meals});
        return mealToBeDeleted;
    }

    public ArrayList<Meal> getMeals() {
        logger.log(Level.INFO, "Retrieved meal list: {0}", meals);
        return meals;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int count = 1;

        for (Meal meal : meals) {
            output.append(count).append(": ").append(meal.getName()).append("\n");
            count++;
        }

        return output.toString().trim(); // Trim to remove trailing newline
    }
}
