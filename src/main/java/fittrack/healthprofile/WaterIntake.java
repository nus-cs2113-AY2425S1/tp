package fittrack.healthprofile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WaterIntake {
    private ArrayList<WaterEntry> waterEntries;

    // Constructor to initialize the waterEntries list
    public WaterIntake() {
        this.waterEntries = new ArrayList<>();
    }

    // Class to represent a water entry along with its amount and timestamp
    public static class WaterEntry {
        int amount;
        LocalDateTime dateTime;

        public WaterEntry(int amount) {
            this.amount = amount;
            this.dateTime = LocalDateTime.now();
        }

        // Helper method to format the timestamp
        private String formatDateTime(LocalDateTime dateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return dateTime.format(formatter); // Format date-time without milliseconds
        }

        @Override
        public String toString() {
            return amount + " ml (" + formatDateTime(dateTime) + ")";
        }
    }

    // Method to add water intake entry with amount and timestamp
    public void addWater(int amount) {
        waterEntries.add(new WaterEntry(amount)); // Add the water entry with timestamp
        String formattedDateTime = new WaterEntry(amount).formatDateTime(LocalDateTime.now());
        System.out.println("Got it. I've added " + amount + "ml of water at " + formattedDateTime + ".");
    }

    // Method to delete a water intake entry by index
    public void deleteWater(int index) {
        if (index >= 0 && index < waterEntries.size()) {
            WaterEntry removedEntry = waterEntries.remove(index);
            System.out.println("Got it. I've deleted " + removedEntry + ".");
        } else {
            System.out.println("Invalid index for water entry.");
        }
    }

    // Method to list all water entries with their amounts and timestamps
    public void listWater() {
        if (waterEntries.isEmpty()) {
            System.out.println("No water intake recorded.");
        } else {
            System.out.println("Here is your water intake (in ml): ");
            for (int i = 0; i < waterEntries.size(); i++) {
                System.out.println((i + 1) + ". " + waterEntries.get(i));
            }
        }
    }
}
