package fittrack.healthprofile;

import java.util.ArrayList;

public class WaterIntake {
    private ArrayList<Integer> waterEntries;

    public WaterIntake() {
        this.waterEntries = new ArrayList<>();
    }

    public void addWater(int amount) {
        waterEntries.add(amount);
        System.out.println("Got it. I've added " + amount + "ml of water.");
    }

    public void deleteWater(int index) {
        if (index >= 0 && index < waterEntries.size()) {
            int removedAmount = waterEntries.remove(index);
            System.out.println("Got it. I've deleted " + removedAmount + "ml of water.");
        } else {
            System.out.println("Invalid index for water entry.");
        }
    }

    public void listWater() {
        if (waterEntries.isEmpty()) {
            System.out.println("No water intake recorded.");
        } else {
            System.out.println("Here is your water intake (in ml): ");
            for (int i = 0; i < waterEntries.size(); i++) {
                System.out.println((i + 1) + ". " + waterEntries.get(i) + "ml");
            }
        }
    }
}







