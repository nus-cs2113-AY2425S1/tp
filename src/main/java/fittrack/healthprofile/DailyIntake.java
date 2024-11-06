package fittrack.healthprofile;

import java.util.ArrayList;
import java.time.LocalDate;

public class DailyIntake {

  private LocalDate date;
  private ArrayList<FoodEntry> foodEntries;
  private int totalCalories;
  private int totalWater;

  public DailyIntake(LocalDate date) {
    this.date = date;
    this.foodEntries = new ArrayList<>();
    this.totalCalories = 0;
    this.totalWater = 0;
  }

  public LocalDate getDate() {
    return date;
  }

  public void addFood(FoodEntry food) {
    foodEntries.add(food);
    totalCalories += food.getCalories();  // Add the food's calories to the total
  }

  public void addWater(int amount) {
    totalWater += amount;
  }

  public void addCalories(int calories) {
    totalCalories += calories;
  }

  public void listIntake() {
    System.out.println("Food Entries for " + date + ":");
    for (FoodEntry food : foodEntries) {
      System.out.println(food.getFoodName() + " - " + food.getCalories() + " calories");
    }
    System.out.println("Total Calories: " + totalCalories);
    System.out.println("Total Water: " + totalWater + " ml");
  }
}
