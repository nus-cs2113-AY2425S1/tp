package history;

import programme.Exercise;
import dailyrecord.DailyRecord;
import java.util.logging.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;


public class History {

    private static final Logger logger = Logger.getLogger(History.class.getName());
    // LinkedHashMap to store Day with its respective date in the order of insertion
    private final LinkedHashMap<LocalDate, DailyRecord> history;

    // Constructor
    public History() {
        history = new LinkedHashMap<>();
    }

    public LinkedHashMap<LocalDate, DailyRecord> getHistory() {
        return history;
    }

    public void logRecord(LocalDate date, DailyRecord record) {
        history.put(date, record);
    }

    // Get a specific Day object by date (used for test comparisons)
    public DailyRecord getRecordByDate(LocalDate date) {
        return history.get(date);
    }

    public int getHistorySize() {
        return history.size();
    }

    // Method to summarize weekly workout activity
    public String getWeeklyWorkoutSummary() {
        if (history.isEmpty()) {
            return "No workout history available.";
        }

        StringBuilder weeklySummary = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7);

        int totalExercises = 0;

        // Iterate through history for the last week only
        for (Map.Entry<LocalDate, DailyRecord> entry : history.entrySet()) {
            LocalDate date = entry.getKey();
            DailyRecord dailyRecord = entry.getValue();

            if (!date.isBefore(oneWeekAgo) && !date.isAfter(today)) {
                // Similar formatting to the history view
                weeklySummary.append(dailyRecord.getDayFromRecord().toString());
                weeklySummary.append(String.format("Completed On: %s%n%n", date.format(formatter)));
                totalExercises += dailyRecord.getDayFromRecord().getExercisesCount();
            }
        }

        if (totalExercises == 0) {
            return "No workout history available for the past week.";
        }

        return weeklySummary.toString();
    }

    // Method to find the personal bests for each exercise
    public Map<String, Exercise> getPersonalBests() {
        Map<String, Exercise> personalBests = new LinkedHashMap<>();

        // Iterate through all the logged days
        for (DailyRecord dailyRecord : history.values()) {
            int exercisesCount = dailyRecord.getDayFromRecord().getExercisesCount();

            // Iterate over each exercise using the existing getExercise method
            for (int i = 0; i < exercisesCount; i++) {
                Exercise exercise = dailyRecord.getDayFromRecord().getExercise(i);
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
        for (DailyRecord dailyRecord : history.values()) {
            int exercisesCount = dailyRecord.getDayFromRecord().getExercisesCount();

            // Iterate over exercises in the day
            for (int i = 0; i < exercisesCount; i++) {
                Exercise exercise = dailyRecord.getDayFromRecord().getExercise(i);

                // If the exercise name matches, and it has a higher weight than the current best, update the best
                if (exercise.getName().equalsIgnoreCase(exerciseName)) {
                    if (personalBest == null || isBetter(exercise, personalBest)) {
                        personalBest = exercise;
                    }
                }
            }
        }

        if (personalBest != null) {
            return "Personal best for " + exerciseName + ": " + personalBest;
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


 \       if (history.isEmpty()) {
            return "No history available.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Iterate over the history LinkedHashMap in insertion order
        for (LocalDate date : history.keySet()) {
            DailyRecord dailyRecord = history.get(date);

            historyString.append(dailyRecord.toString());

            // Append the formatted date at the end
            historyString.append(String.format("Completed On: %s%n%n", date.format(formatter)));
        }

        return historyString.toString();
    }
}

