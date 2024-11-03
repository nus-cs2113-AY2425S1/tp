package fittrack.trainingsession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MoodLog {
    private String mood;
    private LocalDateTime timestamp;
    private String description;

    public MoodLog(String mood, LocalDateTime timestamp, String description) {
        this.mood = mood;
        this.timestamp = timestamp;
        this.description = description;
    }

    // Getters and setters
    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = timestamp.format(formatter);
        return String.format("Mood: %s, Timestamp: %s, Description: %s", mood, formattedDateTime, description);
    }
}
