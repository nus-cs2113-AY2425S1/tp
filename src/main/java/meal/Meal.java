package meal;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Meal {

    private static final Logger logger = Logger.getLogger(Meal.class.getName());

    private int calories;
    private  String name;

    public Meal(String name, int calories) {
        assert name != null && !name.isEmpty() : "Meal name cannot be null or empty";
        assert calories >= 0 : "Calories cannot be negative";

        this.name = name;
        this.calories = calories;

        logger.log(Level.INFO, "Meal created: {0} with {1} Kcal", new Object[]{name, calories});
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " | " + calories + "Kcal";
    }
}
