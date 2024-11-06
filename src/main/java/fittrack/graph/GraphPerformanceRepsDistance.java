package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import java.util.ArrayList;

public class GraphPerformanceRepsDistance extends GraphPerformance {

    //Formats the asterisks and blank space for a session based on the current performance level (row of graph)
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

    //Getting an individual row for the performance graph
    private static String generatePerformanceRow(int currentPerf, int numSessions, int maxXHeaderLength,
            ArrayList<TrainingSession> sessionList, Exercise exercise) {
        StringBuilder rowContent = new StringBuilder();
        //Checking all sessions if they require the asterisks
        for (int j = 0; j < numSessions; j++) {
            rowContent.append(formatSessionPerformance(sessionList.get(j), currentPerf, exercise, maxXHeaderLength));
        }
        return rowContent.toString();
    }

    //Getting the Y axis and main contents for the performance graph
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
