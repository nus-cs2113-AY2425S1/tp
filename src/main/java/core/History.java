package core;

import programme.Day;
import programme.Exercise;


import java.util.ArrayList;


public class History {
    private ArrayList<Day> historyList;
    private ArrayList<String> dateList;  // To store dates for each day

    // Constructor
    public History() {
        historyList = new ArrayList<>();
        dateList = new ArrayList<>();  // Initialize the date list
    }

    // Logs a completed day into the history list with a given date
    public void logDay(Day day, String date) {
        for (int i = 0; i < historyList.size(); i++) {
            if (historyList.get(i).getDayName().equals(date)) {
                // If the day with the same date is found, replace it
                historyList.set(i, day);
                dateList.set(i, date);
                return;
            }
        }
        // If the day is new, add it to the history and date lists
        historyList.add(day);
        dateList.add(date);
    }

    // Returns a formatted string representing the history
    public String showHistory() {
        StringBuilder historyString = new StringBuilder();
        if (historyList.isEmpty()) {
            return "No workout history available.";
        }

        for (int i = 0; i < historyList.size(); i++) {
            Day d = historyList.get(i);
            String date = dateList.get(i);  // Get corresponding date

            historyString.append("Day: ").append(d.getDayName()).append(" (").append(date).append(")").append("\n");
            historyString.append("Exercises:").append("\n");
            for (Exercise e : d.getExerciseList()) {
                historyString.append("    - ").append(e.getExerciseName()).append(": ")
                        .append(e.getSets()).append(" sets, ")
                        .append(e.getReps()).append(" reps, ")
                        .append(e.getWeight()).append(" kg\n");
            }
            historyString.append("\n");  // Add spacing between different days
        }
        return historyString.toString();
    }
}

