public class FoodItem {
    private String foodName;
    private int calories;

    public FoodItem(String foodName, int calories) {
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
