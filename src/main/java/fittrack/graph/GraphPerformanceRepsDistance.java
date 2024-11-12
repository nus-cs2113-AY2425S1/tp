package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import java.util.ArrayList;

/**
 * This class is responsible for generating and displaying a performance graph that tracks the progression of
 * repetitions/distance for a specific exercise across multiple training sessions. It formats the graph with
 * asterisks representing the performance earned for each session.
 */
public class GraphPerformanceRepsDistance extends GraphPerformance {

    /**
     * Formats the representation of a session's performance in the graph based on the current performance level.
     * This method uses asterisks to represent performance and adds points information if needed.
     *
     * @param session The training session whose performance is being formatted.
     * @param currentPerf The current performance level being checked.
     * @param exercise The specific exercise for which the performance is being tracked.
     * @param maxXHeaderLength The maximum length of the header for proper alignment.
     * @return A formatted string representing the session's performance in the graph.
     */
    private static String formatSessionPerformance(TrainingSession session, int currentPerf,
            Exercise exercise, int maxXHeaderLength) {
        int exercisePerformance = session.getExercisePerformance(exercise);
        if (exercisePerformance >= currentPerf) {
            return centerText("*", maxXHeaderLength);
        } else if (exercisePerformance + 1 == currentPerf) {
            String points = session.getExercisePoints(exercise) + " points";
            return centerText(points, maxXHeaderLength);
        } else{
            return generateChar(maxXHeaderLength + 2, ' ');
        }
    }

    /**
     * Generates an individual row of the performance graph, checking all sessions to determine the performance level
     * and formatting the corresponding asterisks or points information.
     *
     * @param currentPerf The current performance level being displayed in the row.
     * @param numSessions The total number of sessions in the graph.
     * @param maxXHeaderLength The maximum length of the header for proper bar alignment.
     * @param sessionList The list of training sessions being displayed.
     * @param exercise The specific exercise for which the performance is being tracked.
     * @return A formatted string representing a single row in the performance graph.
     */
    private static String generatePerformanceRow(int currentPerf, int numSessions, int maxXHeaderLength,
            ArrayList<TrainingSession> sessionList, Exercise exercise) {
        StringBuilder rowContent = new StringBuilder();
        //Checking all sessions if they require the asterisks
        for (int j = 0; j < numSessions; j++) {
            rowContent.append(formatSessionPerformance(sessionList.get(j), currentPerf, exercise, maxXHeaderLength));
        }
        return rowContent.toString();
    }

    /**
     * Generates the main contents of the performance graph, including the Y-axis labels and the rows representing each
     * performance level for all training sessions. The graph is formatted with the appropriate spacing for alignment.
     *
     * @param maxExercisePerformance The maximum performance level for the exercise being tracked.
     * @param numSessions The total number of sessions to be displayed in the graph.
     * @param maxXHeaderLength The maximum length of the header for proper alignment of the graph.
     * @param sessionList The list of training sessions being displayed.
     * @param exercise The specific exercise for which the performance is being tracked.
     * @return A formatted string representing the entire performance graph.
     */
    private static String generateMainGraphPerformance(int maxExercisePerformance, int numSessions,
            int maxXHeaderLength, ArrayList<TrainingSession> sessionList, Exercise exercise) {
        StringBuilder mainContents = new StringBuilder();

        //Looping through all lines of points
        for(int currentPerf = maxExercisePerformance + 1; currentPerf > 0; currentPerf--){
            //Y Axis performance label
            mainContents.append(currentPerf);

            //Calculating offset to asterisks due to the Y Axis performance label
            int currentOffset = String.valueOf(currentPerf).length() - 1;
            int lineStartIndex = mainContents.length();

            //Looping through each training session, determining if they achieved a certain level of points
            mainContents.append(
                    generatePerformanceRow(currentPerf, numSessions, maxXHeaderLength, sessionList, exercise));

            //Removing the blank space offset due to the Y axis performance label
            int firstSpaceIndex = mainContents.indexOf(" ", lineStartIndex);
            for(int i = 0; i < currentOffset; i++){
                mainContents.deleteCharAt(firstSpaceIndex);
                firstSpaceIndex++;
            }
            //Adding a new line to the main contents
            mainContents.append(System.lineSeparator());
        }
        return mainContents.toString();
    }

    //Graph the reps within each exercise
    static String graphExerciseRepsDistance(Exercise exercise, ArrayList<TrainingSession> sessionList,
            int maxExercisePerformance, int maxXHeaderLength, int numSessions){

        return generateMainGraphPerformance(maxExercisePerformance, numSessions,
                maxXHeaderLength, sessionList, exercise);
    }
}
