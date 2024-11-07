package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import java.util.ArrayList;

/**
 * Class responsible for generating the graphical representation of time-based exercise performance.
 * This class extends GraphPerformance and provides methods to create detailed graphs for time-based
 * training sessions.
 */
public class GraphPerformanceTime extends GraphPerformance {
    static final String INVALID_TIME_STRING = "NIL";
    static final String ALIGNMENT_SPACE_STRING = "       ";
    static final double LOWEST_NORMALISED_VALUE = 0.00;
    static final double HIGHEST_NORMALISED_VALUE = 1.00;
    static final double INCREMENT_SCALE = 0.05;
    static final double INCREMENT_HALF_SCALE = 0.025;
    private static final int SMALLEST_DOUBLE_DIGIT = 10;

    /**
     * Builds the header containing time values for each training session.
     *
     * @param exercise The exercise type for which the header is being generated.
     * @param sessionList The list of training sessions.
     * @param maxXHeaderLength The maximum length for each header element for alignment purposes.
     * @return A StringBuilder representing the formatted header with actual time values.
     */
    private static StringBuilder buildTimeHeader(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int maxXHeaderLength) {
        StringBuilder timeHeader = new StringBuilder(ALIGNMENT_SPACE_STRING); // Adjusted spacing for alignment
        for (TrainingSession session : sessionList) {
            int actualTime = session.getExercisePerformance(exercise);
            String displayTime = getDisplayTime(exercise, actualTime);
            timeHeader.append(centerText(displayTime, maxXHeaderLength));
        }
        timeHeader.append("\n");
        return timeHeader;
    }

    /**
     * Returns the formatted display string for the exercise performance time.
     *
     * @param exercise The exercise type.
     * @param actualTime The actual time recorded for the exercise performance.
     * @return A formatted string of the exercise time if valid; otherwise, returns INVALID_TIME_STRING.
     */
    private static String getDisplayTime(Exercise exercise, int actualTime) {
        String displayTime = "";
        if (actualTime == INVALID_TIME_VALUE) {
            displayTime = INVALID_TIME_STRING;
        } else {
            displayTime = processDisplayTime(exercise, actualTime);
        }
        return displayTime;
    }

    /**
     * Processes the time to display it in a readable format.
     *
     * @param exercise The exercise type.
     * @param actualTime The actual time recorded for the exercise performance.
     * @return A formatted string showing the time in seconds to 1 decimal place for Shuttle Run or
     * the time in minutes and seconds for Walk And Run.
     */
    private static String processDisplayTime(Exercise exercise, int actualTime) {
        String displayTime;
        if (exercise == Exercise.SHUTTLE_RUN) {
            displayTime = String.format("%.1fs", actualTime / 10.0); // Convert time back to seconds with 1 decimal
        } else {
            // invariant: exercise == Exercise.WALK_AND_RUN
            int minutes = actualTime / 60;
            int seconds = actualTime % 60;
            displayTime =  padStartingZero(minutes) + minutes + ":" + padStartingZero(seconds) + seconds;
        }
        return displayTime;
    }

    /**
     * Adds a starting zero to the time if it is a single-digit number.
     *
     * @param time The time value to check.
     * @return A string containing a zero if time < 10, otherwise an empty string.
     */
    private static String padStartingZero(int time){
        if(time < SMALLEST_DOUBLE_DIGIT) {
            return "0";
        }
        return "";
    }

    /**
     * Builds the main contents of the graph, including the Y-axis and asterisks for performance levels.
     *
     * @param exercise The exercise type.
     * @param sessionList The list of training sessions.
     * @param minPerformance The minimum performance value across sessions.
     * @param maxPerformance The maximum performance value across sessions.
     * @param maxXHeaderLength The maximum length for the X-axis header for alignment purposes.
     * @return A StringBuilder containing the main contents of the graph.
     */
    private static StringBuilder buildMainContents(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int minPerformance, int maxPerformance, int maxXHeaderLength) {
        StringBuilder mainContents = new StringBuilder();
        for (double i = HIGHEST_NORMALISED_VALUE; i >= LOWEST_NORMALISED_VALUE; i -= INCREMENT_SCALE) {
            mainContents.append(String.format("%.2f", i)).append("   "); // Y-axis label
            processResultToPoint(exercise, sessionList, minPerformance, maxPerformance, maxXHeaderLength,
                    i, mainContents);
            mainContents.append("\n");
        }
        return mainContents;
    }

    /**
     * Processes the performance results and marks the graph with asterisks or spaces.
     *
     * @param exercise The exercise type.
     * @param sessionList The list of training sessions.
     * @param minPerformance The minimum performance value across sessions.
     * @param maxPerformance The maximum performance value across sessions.
     * @param maxXHeaderLength The maximum length for alignment.
     * @param i The current normalized value being checked.
     * @param mainContents The StringBuilder to append the results to.
     */
    private static void processResultToPoint(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int minPerformance, int maxPerformance, int maxXHeaderLength, double i, StringBuilder mainContents) {
        for (TrainingSession session : sessionList) {
            double normalizedPerformance = getNormalizePerformance(exercise, minPerformance, maxPerformance, session);
            addAsteriskToTimeGraph(minPerformance, maxPerformance, maxXHeaderLength, i, mainContents,
                    normalizedPerformance);
        }
    }

    /**
     * Adds an asterisk to the graph if the performance matches the current row's normalized value.
     *
     * @param minPerformance The minimum performance value.
     * @param maxPerformance The maximum performance value.
     * @param maxXHeaderLength The maximum length for alignment.
     * @param normalizedValue The current normalized value being processed.
     * @param mainContents The StringBuilder to append to.
     * @param normalizedPerformance The normalized performance value of the current session.
     */
    private static void addAsteriskToTimeGraph(int minPerformance, int maxPerformance, int maxXHeaderLength,
            double normalizedValue, StringBuilder mainContents, double normalizedPerformance) {
        boolean isAllPerformanceSame = maxPerformance == minPerformance;
        if (normalizedPerformance == INVALID_TIME_VALUE) {
            mainContents.append(generateChar(maxXHeaderLength + 2, ' '));
        } else if (isAllPerformanceSame && normalizedValue == HIGHEST_NORMALISED_VALUE) {
            mainContents.append(centerText("*", maxXHeaderLength));
        } else if (Math.abs(normalizedPerformance - normalizedValue) < INCREMENT_HALF_SCALE) {
            // mark space with * if normalized value == current row level with tolerance of 1/2 the scale
            mainContents.append(centerText("*", maxXHeaderLength));
        } else if (normalizedPerformance == 0 && Math.abs(normalizedPerformance - normalizedValue) < INCREMENT_SCALE) {
            // mark space with * if normalised value == 0
            mainContents.append(centerText("*", maxXHeaderLength));
        } else {
            // do not mark space with *
            mainContents.append(generateChar(maxXHeaderLength + 2, ' ')); // Spacer for alignment
        }
    }

    /**
     * Normalizes the performance value for a session.
     *
     * @param exercise The exercise type.
     * @param minPerformance The minimum performance value.
     * @param maxPerformance The maximum performance value.
     * @param session The training session containing the performance data.
     * @return The normalized performance value, or INVALID_TIME_VALUE if the data is invalid.
     */
    private static double getNormalizePerformance(Exercise exercise, int minPerformance,
            int maxPerformance, TrainingSession session) {
        double performance = session.getExercisePerformance(exercise);

        if (performance == INVALID_TIME_VALUE) {
            return INVALID_TIME_VALUE;
        }
        double normalizedPerformance = (performance - minPerformance) / (double) (maxPerformance - minPerformance);
        return normalizedPerformance;
    }

    /**
     * Generates the main graph content for the exercise time-based graph.
     *
     * @param exercise The exercise type.
     * @param sessionList The list of training sessions.
     * @param minPerformance The minimum performance value.
     * @param maxPerformance The maximum performance value.
     * @param maxXHeaderLength The maximum length for X-axis alignment.
     * @return A string representing the full graph including the header and main contents.
     */
    static String generateMainGraphPerformance(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int minPerformance, int maxPerformance, int maxXHeaderLength) {
        // Build header with actual time values for each session
        StringBuilder timeHeader = buildTimeHeader(exercise, sessionList, maxXHeaderLength);

        // Build Y-axis and main graph contents
        StringBuilder mainContents = buildMainContents(exercise, sessionList, minPerformance, maxPerformance,
                maxXHeaderLength);

        return timeHeader.toString() + mainContents;
    }
}


