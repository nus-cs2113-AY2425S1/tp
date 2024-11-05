package fittrack.healthprofile;

import java.util.ArrayList;

public class FoodIntake {
    private ArrayList<String> foodItems;

    public FoodIntake() {
        this.foodItems = new ArrayList<>();
    }

    public void addFood(String food) {
        foodItems.add(food);
        System.out.println("Got it. I've added food item: " + food);
    }

    public void deleteFood(int index) {
        if (index >= 0 && index < foodItems.size()) {
            String removedFood = foodItems.remove(index);
            System.out.println("Got it. I've deleted food item: " + removedFood);
        } else {
            System.out.println("Invalid index for food entry.");
        }
    }

    public void listFood() {
        if (foodItems.isEmpty()) {
            System.out.println("No food items recorded.");
        } else {
            System.out.println("Here is your food intake list: ");
            for (int i = 0; i < foodItems.size(); i++) {
                System.out.println((i + 1) + ". " + foodItems.get(i));
            }
        }
    }
}
