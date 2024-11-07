package fittrack.healthprofile;

import fittrack.storage.Saveable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class WaterEntry extends Saveable {
    private final int amount;
    private final LocalDateTime dateTime;  // Store the date and time of the water intake

    public WaterEntry(int inputAmount, LocalDateTime inputDateTime) {
        this.amount = inputAmount;
        this.dateTime = inputDateTime;  // Automatically set the timestamp to current time
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;  // Return the timestamp of the water entry
    }

    public Object getLocalDate() {
        return dateTime.toLocalDate();
    }

    // Method to format the timestamp without milliseconds
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);  // Format date-time without milliseconds
    }

    @Override
    public String toString() {
        return amount + " ml, added on " + getFormattedDateTime();
    }

    @Override
    public String toSaveString() {
        return "Water" + "|" + amount + "|" + getFormattedDateTime();
    }

    public static WaterEntry fromSaveString(String saveString) {
        String[] stringData = saveString.split("\\|");

        // Check if the format is correct
        if (stringData.length < 3 || !stringData[0].equals("Water")) {
            throw new IllegalArgumentException("Invalid save string format for Water Entry");
        }

        int waterAmount = Integer.parseInt(stringData[1]);

        try {
            LocalDateTime waterEntryTime = LocalDateTime.parse(stringData[2],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return new WaterEntry(waterAmount, waterEntryTime);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid save string format for Water Entry");
        }

        return null;
    }

}
