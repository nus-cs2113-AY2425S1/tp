package fittrack.healthprofile;

public class FoodEntry {

    private String foodName;
    private int calories;

    public FoodEntry(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCalories() {
        return calories;
    }
}
