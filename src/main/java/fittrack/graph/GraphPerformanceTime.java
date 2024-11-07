package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import java.util.ArrayList;

public class GraphPerformanceTime extends GraphPerformance {
    static final String INVALID_TIME_STRING = "NIL";
    static final String ALIGNMENT_SPACE_STRING = "       ";
    static final double LOWEST_NORMALISED_VALUE = 0.00;
    static final double HIGHEST_NORMALISED_VALUE = 1.00;
    static final double INCREMENT_SCALE = 0.05;
    static final double INCREMENT_HALF_SCALE = 0.025;

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

    private static String getDisplayTime(Exercise exercise, int actualTime) {
        String displayTime = "";
        if (actualTime == INVALID_TIME_VALUE) {
            displayTime = INVALID_TIME_STRING;
        } else {
            displayTime = processDisplayTime(exercise, actualTime);
        }
        return displayTime;
    }

    private static String processDisplayTime(Exercise exercise, int actualTime) {
        String displayTime;
        if (exercise == Exercise.SHUTTLE_RUN) {
            displayTime = String.format("%.1fs", actualTime / 10.0); // Convert time back to seconds with 1 decimal
        } else {
            // invariant: exercise == Exercise.WALK_AND_RUN
            int minutes = actualTime / 60;
            int seconds = actualTime % 60;
            displayTime =  minutes + ":" + seconds;
        }
        return displayTime;
    }

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

    private static void processResultToPoint(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int minPerformance, int maxPerformance, int maxXHeaderLength, double i, StringBuilder mainContents) {
        for (TrainingSession session : sessionList) {
            double normalizedPerformance = getNormalizePerformance(exercise, minPerformance, maxPerformance, session);
            addAsteriskToTimeGraph(minPerformance, maxPerformance, maxXHeaderLength, i, mainContents,
                    normalizedPerformance);
        }
    }

    private static void addAsteriskToTimeGraph(int minPerformance, int maxPerformance, int maxXHeaderLength,
            double normalizedValue, StringBuilder mainContents, double normalizedPerformance) {
        boolean isAllPerformanceSame = maxPerformance == minPerformance;

        if (isAllPerformanceSame && normalizedValue < INCREMENT_SCALE) {
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

    private static double getNormalizePerformance(Exercise exercise, int minPerformance,
            int maxPerformance, TrainingSession session) {
        double performance = session.getExercisePerformance(exercise);

        if (performance == INVALID_TIME_VALUE) {
             return INVALID_TIME_VALUE;
        }
        double normalizedPerformance = (performance - minPerformance) / (double) (maxPerformance - minPerformance);
        return normalizedPerformance;
    }

    static String graphExerciseTime(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int minPerformance, int maxPerformance, int maxXHeaderLength) {
        // Build header with actual time values for each session
        StringBuilder timeHeader = buildTimeHeader(exercise, sessionList, maxXHeaderLength);

        // Build Y-axis and main graph contents
        StringBuilder mainContents = buildMainContents(exercise, sessionList, minPerformance, maxPerformance,
                maxXHeaderLength);

        return timeHeader.toString() + mainContents;
    }
}


