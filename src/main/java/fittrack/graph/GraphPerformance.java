package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import java.util.ArrayList;

public abstract class GraphPerformance extends GraphBase {

    static final int INVALID_TIME_VALUE = -1;
    static final int DATETIME_LENGTH = 16; //Length of Date format without spaces

    //Returns line with properly spaced descriptions for the X axis
    private static String formatXHeaderDesc(int numSessions,
            ArrayList<TrainingSession> sessionList, int maxXHeaderLength) {
        StringBuilder descriptions = new StringBuilder();
        for (int i = 0; i < numSessions; i++) {
            descriptions.append(centerText(sessionList.get(i).getSessionDescription(), maxXHeaderLength));
        }
        return descriptions.toString();
    }

    //Returns line with properly spaced descriptions for the X axis
    private static String formatXHeaderDate(int numSessions,
            ArrayList<TrainingSession> sessionList, int maxXHeaderLength) {
        StringBuilder dates = new StringBuilder();
        for (int i = 0; i < numSessions; i++) {
            dates.append(centerText(sessionList.get(i).getSessionDatetime(), maxXHeaderLength));
        }
        return dates.toString();
    }

    //Getting the X axis for the performance graph
    static String generateXHeader(int numSessions, ArrayList<TrainingSession> sessionList,
            int maxXHeaderLength, int yOffset){
        String xHeadersDate = formatXHeaderDate(numSessions, sessionList, maxXHeaderLength);
        String xHeadersDesc = formatXHeaderDesc(numSessions, sessionList, maxXHeaderLength);

        //Adding any offset needed due to Y axis labels
        return generateChar(yOffset, ' ') +  xHeadersDesc + System.lineSeparator()
                + generateChar(yOffset, ' ') + xHeadersDate;
    }

    //Getting the maximum height for the graph
    static int getMaxExercisePerformance(ArrayList<TrainingSession> sessionList, Exercise exercise,
            int numTrainingSessions){
        int maxExercisePerformance = 0;
        for(int i = 0; i < numTrainingSessions; i++) {
            int exercisePerformance = sessionList.get(i).getExercisePerformance(exercise);
            if (exercisePerformance > maxExercisePerformance) {
                maxExercisePerformance = exercisePerformance;
            }
        }
        return maxExercisePerformance;
    }

    // Return minimum height of graph
    static int getMinExercisePerformance(ArrayList<TrainingSession> sessionList, Exercise exercise,
            int numTrainingSessions){
        int minExercisePerformance = sessionList.get(0).getExercisePerformance(exercise);
        for (int i = 1; i < numTrainingSessions; i++) {
            int exercisePerformance = sessionList.get(i).getExercisePerformance(exercise);
            if (exercisePerformance < minExercisePerformance && exercisePerformance != INVALID_TIME_VALUE) {
                minExercisePerformance = exercisePerformance;
            }
        }
        return minExercisePerformance;
    }

    public static void graphExercisePerformance(Exercise exercise, ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your progression for " + exercise.toString() + " over your training sessions:"
                + System.lineSeparator() + System.lineSeparator());

        StringBuilder graph = new StringBuilder();
        int numSessions = sessionList.size();
        int maxExercisePerformance = getMaxExercisePerformance(sessionList, exercise, numSessions);
        int minExercisePerformance = getMinExercisePerformance(sessionList, exercise, numSessions);
        int maxXHeaderLength = Math.max(DATETIME_LENGTH, TrainingSession.getLongestSessionDescription());
        int yOffset;

        // Prepare Y axis and main content
        if (exercise == Exercise.WALK_AND_RUN || exercise == Exercise.SHUTTLE_RUN) {
            yOffset = 6;
        } else {
            yOffset = String.valueOf(maxExercisePerformance).length() - 1;
            graph.append(GraphPerformanceRepsDistance.graphExerciseRepsDistance(exercise, sessionList,
                    maxExercisePerformance, maxXHeaderLength, numSessions));
        }

        // Prepare X-axis headers
        graph.append(generateXHeader(numSessions, sessionList, maxXHeaderLength, yOffset));
        // Print graph to CLI
        System.out.print(graph + System.lineSeparator() + System.lineSeparator());
    }
}

