package history;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import programme.Day;
import programme.Exercise;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;


public class History {

    private static final Logger logger = Logger.getLogger(History.class.getName());
    // LinkedHashMap to store Day with its respective date in the order of insertion
    private final LinkedHashMap<LocalDate, Day> history;  // Use LocalDate and LinkedHashMap to preserve insertion order

    // Constructor
    public History() {
        history = new LinkedHashMap<>();
    }

    // Logs a completed day into the history with a given date
    public void logDay(Day day, LocalDate date) {
        history.put(date, day);  // This will overwrite if a day with the same date exists
    }

    // Get a specific Day object by date (used for test comparisons)
    public Day getDayByDate(LocalDate date) {
        return history.get(date);
    }

    // Converts the History object to a JSON string
    public JsonObject toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom serializer for LocalDate
                .setPrettyPrinting()
                .create();

        JsonObject historyJson = new JsonObject();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (LocalDate date : history.keySet()) {
            Day day = history.get(date);
            // Add each entry in the LinkedHashMap to the JsonObject, using the date as the key
            historyJson.add(date.format(formatter), gson.toJsonTree(day));
        }
        logger.log(Level.INFO, "History converted to Json for saving.");
        return historyJson;
    }

    // Creates a History object from a JSON string
    public static History fromJson(JsonObject jsonObject) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new DateSerializer())  // Custom deserializer for LocalDate
                .create();
        History history = new History();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Iterate through the JSON keys (dates) and deserialize them as LocalDate and Day objects
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey(), formatter);  // Convert key to LocalDate
            Day day = gson.fromJson(entry.getValue(), Day.class);  // Deserialize the Day object
            history.history.put(date, day);  // Add to the LinkedHashMap
        }
        logger.log(Level.INFO, "historyJson converted from Json for loading.");
        return history;
    }

    // Method to summarize weekly workout activity


    public String getWeeklySummary() {
        if (history.isEmpty()) {
            return "No workout history available.";
        }

        StringBuilder weeklySummary = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minus(7, ChronoUnit.DAYS);

        int totalExercises = 0;

        // Iterate through history for the last week only
        for (Map.Entry<LocalDate, Day> entry : history.entrySet()) {
            LocalDate date = entry.getKey();
            Day day = entry.getValue();

            if (!date.isBefore(oneWeekAgo) && !date.isAfter(today)) {
                // Similar formatting to the history view
                weeklySummary.append(day.toString());
                weeklySummary.append(String.format("Completed On: %s%n%n", date.format(formatter)));
                totalExercises += day.getExercisesCount();
            }
        }

        if (totalExercises == 0) {
            return "No workout history available for the past week.";
        }

        return weeklySummary.toString();
    }

    // Method to find the personal bests for each exercise
    public Map<String, Exercise> getPersonalBests() {
        Map<String, Exercise> personalBests = new LinkedHashMap<>();  // Changed to LinkedHashMap to preserve order

        // Iterate through all the logged days
        for (Day day : history.values()) {
            int exercisesCount = day.getExercisesCount();  // Get the number of exercises for the day

            // Iterate over each exercise using the existing getExercise method
            for (int i = 0; i < exercisesCount; i++) {
                Exercise exercise = day.getExercise(i);
                String exerciseName = exercise.getName();

                // If this exercise is not in the map or the new exercise has a higher weight
                if (!personalBests.containsKey(exerciseName) || isBetter(exercise, personalBests.get(exerciseName))) {
                    personalBests.put(exerciseName, exercise);  // Replace with the new personal best
                }
            }
        }
        return personalBests;  // Returning a LinkedHashMap ensures insertion order is maintained
    }

    // Method to get personal best for a specific exercise
    public String getPersonalBestForExercise(String exerciseName) {
        Exercise personalBest = null;

        // Iterate through all logged days to find the best result for the specific exercise
        for (Day day : history.values()) {
            int exercisesCount = day.getExercisesCount();

            // Iterate over exercises in the day
            for (int i = 0; i < exercisesCount; i++) {
                Exercise exercise = day.getExercise(i);

                // If the exercise name matches and it has a higher weight than the current best, update the best
                if (exercise.getName().equalsIgnoreCase(exerciseName)) {
                    if (personalBest == null || isBetter(exercise, personalBest)) {
                        personalBest = exercise;
                    }
                }
            }
        }

        if (personalBest != null) {
            return "Personal best for " + exerciseName + ": " + personalBest.toString();
        } else {
            return "No personal best found for " + exerciseName;
        }
    }

    // Compare two exercises based on weight
    private boolean isBetter(Exercise current, Exercise best) {
        return current.getWeight() > best.getWeight();
    }

    // Standard toString method for History class that represents the history
    @Override
    public String toString() {
        StringBuilder historyString = new StringBuilder();

        if (history.isEmpty()) {
            return "No workout history available.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Iterate over the history LinkedHashMap in insertion order
        for (LocalDate date : history.keySet()) {
            Day day = history.get(date);

            // Use the Day class's toString directly
            historyString.append(day.toString());

            // Append the formatted date at the end
            historyString.append(String.format("Completed On: %s%n%n", date.format(formatter)));
        }

        return historyString.toString();
    }
}

