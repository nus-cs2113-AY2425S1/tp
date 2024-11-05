package fittrack.fitnessgoal;
import fittrack.storage.Saveable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Goal extends Saveable {

    private final String description;
    private final LocalDateTime deadline;

    // Constructor
    public Goal(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    // Getter for the goal description
    public String getDescription() {
        return description;
    }

    // Getter for the goal deadline
    public LocalDateTime getDeadline() {
        return deadline;
    }

    // Optionally, you might want to override the toString() method for easy printing
    public String toString() {
        return "Goal: " + description + (deadline != null ? ", Deadline: " + deadline : "");
    }

    // Saves in the format [ Goal | Description | Deadline | User ]
    @Override
    public String toSaveString() {
        return "Goal" + " | " + description + " | " + (deadline != null ?
                deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "");
    }

    // Parses a formatted string from a save-file to load a new goal.
    public static Goal fromSaveString(String saveString) {
        // Split the string by the " | " delimiter
        String[] stringData = saveString.split(" \\| ");

        // Check if the format is correct
        if (stringData.length < 2 || !stringData[0].equals("Goal")) {
            throw new IllegalArgumentException("Invalid save string format for Goal: " + saveString);
        }

        String goalDescription = stringData[1];

        try {
            LocalDateTime goalDeadline = LocalDateTime.parse(stringData[2],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return new Goal(goalDescription, goalDeadline);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid save string format for Goal: " + saveString);
        }

        return null;
    }
}
