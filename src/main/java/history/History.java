// @@author andreusxcarvalho
package history;

import programme.Exercise;

import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class History {
    private static final Logger logger = Logger.getLogger(History.class.getName());
    private final LinkedHashMap<LocalDate, DailyRecord> history;

    public History() {
        history = new LinkedHashMap<>();
    }

    public DailyRecord getRecordByDate(LocalDate date) {
        DailyRecord record = history.get(date);
        if (record == null) {
            record = new DailyRecord();
            logRecord(date, record);
        }
        return record;
    }

    public LinkedHashMap<LocalDate, DailyRecord> getHistory() {
        return history;
    }

    public String getWeeklyWorkoutSummary() {
        if (history.isEmpty()) {
            return "No workout history available.";
        }

        StringBuilder weeklySummary = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minusDays(7);
        int totalExercises = 0;

        for (Map.Entry<LocalDate, DailyRecord> entry : history.entrySet()) {
            LocalDate date = entry.getKey();
            DailyRecord dailyRecord = entry.getValue();

            if (!date.isBefore(oneWeekAgo) && !date.isAfter(today)) {
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

    public void logRecord(LocalDate date, DailyRecord record) {
        history.put(date, record);
    }

    // @@author TVageesan
    /**
     * Deletes the daily record for a specified date.
     * <p>
     * Checks if a record exists in the {@code history} map for the given date.
     * If present, removes the record and returns it. If not present, returns {@code null}.
     * </p>
     *
     * @param date the date of the record to delete
     * @return the deleted {@code DailyRecord} if it existed, or {@code null} if no record exists for the specified date
     */
    public DailyRecord deleteRecord(LocalDate date) {
        assert date != null : "Date must not be null";
        if (!history.containsKey(date)) {
            return null;
        }
        return history.remove(date);
    }
    // @@author

    public int getHistorySize() {
        return history.size();
    }

    // Returns a preformatted string of personal bests for all exercises
    public String getFormattedPersonalBests() {
        Map<String, Exercise> personalBests = getPersonalBestsMap();

        if (personalBests.isEmpty()) {
            return "No personal bests found.";
        }

        StringBuilder bestsMessage = new StringBuilder("Personal bests for all exercises:\n");
        for (Map.Entry<String, Exercise> entry : personalBests.entrySet()) {
            bestsMessage.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue().toString())
                    .append("\n");
        }

        return bestsMessage.toString();
    }

    // Helper method to generate a map of personal bests for each exercise
    private Map<String, Exercise> getPersonalBestsMap() {
        Map<String, Exercise> personalBests = new LinkedHashMap<>();

        for (DailyRecord dailyRecord : history.values()) {
            int exercisesCount = dailyRecord.getDayFromRecord().getExercisesCount();

            for (int i = 0; i < exercisesCount; i++) {
                Exercise exercise = dailyRecord.getDayFromRecord().getExercise(i);
                String exerciseName = exercise.getName();

                if (!personalBests.containsKey(exerciseName) || isBetter(exercise, personalBests.get(exerciseName))) {
                    personalBests.put(exerciseName, exercise);
                }
            }
        }
        return personalBests;
    }

    private boolean isBetter(Exercise current, Exercise best) {
        return current.getWeight() > best.getWeight();
    }

    // Returns a formatted string for the personal best of a specified exercise
    public String getPersonalBestForExercise(String exerciseName) {
        Exercise personalBest = null;

        for (DailyRecord dailyRecord : history.values()) {
            int exercisesCount = dailyRecord.getDayFromRecord().getExercisesCount();

            for (int i = 0; i < exercisesCount; i++) {
                Exercise exercise = dailyRecord.getDayFromRecord().getExercise(i);

                if (exercise.getName().equalsIgnoreCase(exerciseName)) {
                    if (personalBest == null || isBetter(exercise, personalBest)) {
                        personalBest = exercise;
                    }
                }
            }
        }

        return personalBest != null
                ? "Personal best for " + exerciseName + ": " + personalBest
                : "No personal best found for " + exerciseName;
    }

    @Override
    public String toString() {
        StringBuilder historyString = new StringBuilder();
        int count = 0;
        int size = history.size();

        if (history.isEmpty()) {
            return "No history available.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (LocalDate date : history.keySet()) {
            historyString.append("\n");
            historyString.append(String.format("Completed On: %s%n%n", date.format(formatter)));
            DailyRecord dailyRecord = history.get(date);
            historyString.append(dailyRecord.toString());
            count++;
            if (count < size) {
                historyString.append("\n\n==============\n");
            }
        }

        return historyString.toString();
    }
}

