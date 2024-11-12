// @@author andreusxcarvalho
package history;

import programme.Exercise;

import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manages and tracks the workout history, including daily workout records, weekly summaries,
 * and personal best exercises.
 * <p>
 * This class provides methods to add, retrieve, delete, and summarize workout records, as well as
 * manage personal bests for exercises. Workout records are stored in a chronological order
 * using a {@link LinkedHashMap} to preserve the insertion order by date.
 * </p>
 */
public class History {
    private static final Logger logger = Logger.getLogger(History.class.getName());
    private final LinkedHashMap<LocalDate, DailyRecord> history;

    /**
     * Initializes a new empty workout history.
     */
    public History() {
        history = new LinkedHashMap<>();
    }

    //@@author Bev-Low
    /**
     * Retrieves the daily record for a specific date.
     * <p>
     * Checks if a record exists in the {@code history} map for the specified date.
     * If a record is found, it is returned. If no record exists, a new {@link DailyRecord} is
     * created, added to the {@code history} map, and then returned.
     * </p>
     *
     * @param date the {@link LocalDate} of the record to retrieve
     * @return the {@link DailyRecord} for the specified date
     */
    public DailyRecord getRecordByDate(LocalDate date) {
        DailyRecord record = history.get(date);
        if (record == null) {
            record = new DailyRecord();
            logRecord(date, record);
        }
        return record;
    }

    /**
     * checks if history is empty.
     *
     * @return true if it is empty
     */
    public boolean isEmpty() {
        return history.isEmpty();
    }
    // @@author

    /**
     * Returns the entire workout history.
     *
     * @return the {@code LinkedHashMap} containing dates and corresponding daily records
     */
    public LinkedHashMap<LocalDate, DailyRecord> getHistory() {
        return history;
    }

    /**
     * Generates a summary of the workout history for the past week.
     *
     * <p>This method retrieves workout data from the {@code history} map, which
     * contains records of daily activities. It filters out any {@code DailyRecord}
     * entries that do not contain a workout {@code Day}, so only records with
     * workout data are included in the weekly summary.</p>
     *
     * @return A formatted string summarizing the workout history for the past
     *         week, or a message indicating no workout history is available if
     *         no relevant records are found.
     */
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

            // Only include records that have a workout (Day)
            if (!date.isBefore(oneWeekAgo) && !date.isAfter(today) && dailyRecord.getDayFromRecord() != null) {
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

    /**
     * Logs a daily record on a specific date into the history.
     *
     * @param date   the date of the workout record
     * @param record the daily record to add to the history
     */
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

    /**
     * Checks if a workout record exists for a specific date.
     *
     * @param date the date to check for a workout record
     * @return {@code true} if a record exists for the specified date, {@code false} otherwise
     */
    public boolean hasRecord(LocalDate date) {
        return history.containsKey(date);
    }

    /**
     * Returns the number of records in the workout history.
     *
     * @return the size of the workout history
     */
    public int getHistorySize() {
        return history.size();
    }

    /**
     * Returns a formatted string of personal bests for all exercises.
     *
     * @return a string listing personal bests for each exercise, or a message indicating no personal bests found
     */
    public String getFormattedPersonalBests() {
        Map<String, Exercise> personalBests = getPersonalBestsMap();

        if (personalBests.isEmpty()) {
            return "No personal bests found.";
        }

        StringBuilder bestsMessage = new StringBuilder("Personal bests for all exercises:\n");
        for (Map.Entry<String, Exercise> entry : personalBests.entrySet()) {
            bestsMessage.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue().toStringPb())
                    .append("\n");
        }

        return bestsMessage.toString();
    }

    /**
     * Builds a map of the personal best exercise for each type based on weight.
     *
     * <p>Filters out any {@code DailyRecord} entries without a valid workout {@code Day} to avoid null
     * pointer exceptions.</p>
     *
     * @return a map of exercise names and their corresponding best {@link Exercise} entries
     */
    private Map<String, Exercise> getPersonalBestsMap() {
        Map<String, Exercise> personalBests = new LinkedHashMap<>();

        for (DailyRecord dailyRecord : history.values()) {
            // Skip this record if it does not have a Day (workout data)
            if (dailyRecord.getDayFromRecord() == null) {
                continue;
            }

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

    /**
     * Compares two exercises to determine if the current exercise is better based on weight.
     *
     * @param current the current exercise to evaluate
     * @param best    the existing best exercise to compare against
     * @return {@code true} if the current exercise is better, {@code false} otherwise
     */
    private boolean isBetter(Exercise current, Exercise best) {
        return current.getWeight() > best.getWeight();
    }

    /**
     * Retrieves a formatted personal best entry for a specific exercise.
     *
     * <p>Filters out any {@code DailyRecord} entries without a valid workout {@code Day} to avoid
     * null pointer exceptions.</p>
     *
     * @param exerciseName the name of the exercise to look up
     * @return a formatted string showing the personal best for the specified exercise, or a message if not found
     */
    public String getPersonalBestForExercise(String exerciseName) {
        Exercise personalBest = null;

        for (DailyRecord dailyRecord : history.values()) {
            // Skip this record if it does not have a Day (workout data)
            if (dailyRecord.getDayFromRecord() == null) {
                continue;
            }

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

        if (personalBest != null) {
            return String.format("Personal best for %s: %s", exerciseName, personalBest.toStringPb());
        } else {
            return String.format("No personal best found for %s", exerciseName);
        }
    }

    //@@author Bev-Low
    /**
     * Converts the {@code history} map, which stores {@link DailyRecord} objects associated with their
     * completion dates, into a formatted string representation for printing.
     * <p>
     * This method iterates over each entry in the {@code history} map and formats each
     * {@code LocalDate} key and corresponding {@code DailyRecord} value into a readable string.
     * Each record is separated by a line of equals signs to improve readability.
     * <p>
     * If the history is empty, this method returns a message indicating that no history is available.
     *
     * @return a formatted string representation of the history, displaying each {@code DailyRecord}
     *         with its completion date. If the history is empty, returns "No history available."
     */
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
    //@@author
}

