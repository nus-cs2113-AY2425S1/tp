package meal;

public class Meal {

    private int calories;
    private String name;

    public Meal(String name, int calories) {
        this.name = name;
        this.calories = calories;
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

