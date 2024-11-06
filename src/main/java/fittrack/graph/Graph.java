package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
import java.util.ArrayList;

//import fittrack.user.User;
//import java.time.LocalDateTime;

public class Graph {

    static final String SESSTION_DESCRIPTION_STRING = "Session Description";
    static final String DATE_STRING = "| Date ";
    static final String POINTS_STRING = "| Points";
    static final int SESSION_DESCRIPTION_LENGTH = 19; //Length of the String, "Session Description"
    static final int DATE_BLANK_LENGTH = 12; //Number of blank spaces to print after "| Date "
    static final int DATETIME_LENGTH = 16; //Length of Date format without spaces

    //Returns String of specified char
    private static String generateChar(int num, char character){
        return String.valueOf(character).repeat(Math.max(0, num));
    }

    //Return String for "Session Description" header with blanks if required
    private static String generateSessionDescHeaderPoints(int descriptionLength) {
        return SESSTION_DESCRIPTION_STRING + generateChar(descriptionLength - SESSION_DESCRIPTION_LENGTH + 1,
                ' ');
    }

    //Print header for points graph
    private static String generateGraphHeaderPoints(int descriptionLength) {
        String sessionDescHeader = generateSessionDescHeaderPoints(descriptionLength);
        String header = sessionDescHeader + DATE_STRING + generateChar(DATE_BLANK_LENGTH, ' ') + POINTS_STRING;
        String headerSeparator = generateChar(sessionDescHeader.length(), '-') + "|" +
                generateChar(DATE_BLANK_LENGTH + DATE_STRING.length() - 1, '-') + "|";

        return header + System.lineSeparator() + headerSeparator + System.lineSeparator();
    }

    //Print rows for points graph
    private static String generateGraphRowsPoints(TrainingSession session, Exercise exercise, int descriptionLength) {
        int description = session.getSessionDescription().length();
        int blankDescLength = descriptionLength - description;
        String descDatetime = session.getSessionDescription() + generateChar(blankDescLength, ' ') + " | "
                + session.getSessionDatetime() + " | ";

        int totalPoints;
        StringBuilder asterisks = new StringBuilder();
        if(exercise == null) {
            totalPoints = session.getTotalPoints();
        } else {
            totalPoints = session.getExercisePoints(exercise);
        }
        asterisks.append("*".repeat(Math.max(0, totalPoints)));
        return descDatetime + asterisks + " (" + totalPoints + ")" + System.lineSeparator();
    }

    //Prints the overall graph for points
    private static String generatePointGraphs(ArrayList<TrainingSession> sessionList, Exercise exercise){
        //Getting longest possible training session description
        int sessionDescriptionLength = Math.max(SESSION_DESCRIPTION_LENGTH,
                TrainingSession.getLongestSessionDescription());

        StringBuilder pointGraph = new StringBuilder();
        pointGraph.append(generateGraphHeaderPoints(sessionDescriptionLength));
        for(TrainingSession session : sessionList) {
            pointGraph.append(generateGraphRowsPoints(session, exercise, sessionDescriptionLength));
        }
        return pointGraph.toString();
    }

    //Graph the total points across training sessions
    public static void graphSessions(ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your point progression over the various training sessions:" + System.lineSeparator());
        System.out.print(generatePointGraphs(sessionList, null) + System.lineSeparator());
    }

    //Graph the points within each exercise
    public static void graphExercisePoints(Exercise exercise, ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your point progression for " + exercise.toString() + " over your training sessions:"
                + System.lineSeparator());
        System.out.print(generatePointGraphs(sessionList, exercise) + System.lineSeparator());
    }

    //Centers the target text with blank spaces as padding on both sides depending on the maxLength given
    private static String centerText(String text, int maxLength) {
        int paddingBack =  (maxLength - text.length()) / 2;
        int paddingFront =  maxLength - text.length() - paddingBack;
        //Add 1 for additional spacing from adjacent elements
        return generateChar(paddingFront + 1, ' ') + text + generateChar(paddingBack + 1, ' ');
    }

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
    private static String generateXHeader(int numSessions, ArrayList<TrainingSession> sessionList,
            int maxXHeaderLength, int yOffset){

        String xHeadersDate = formatXHeaderDate(numSessions, sessionList, maxXHeaderLength);
        String xHeadersDesc = formatXHeaderDesc(numSessions, sessionList, maxXHeaderLength);

        //Adding any offset needed due to Y axis labels
        return generateChar(yOffset, ' ') +  xHeadersDesc + System.lineSeparator()
                + generateChar(yOffset, ' ') + xHeadersDate;
    }


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

    //Getting the maximum height for the graph
    private static int getMaxExercisePerformance(ArrayList<TrainingSession> sessionList, Exercise exercise,
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
    private static int getMinExercisePerformance(ArrayList<TrainingSession> sessionList, Exercise exercise,
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


    //Graph the reps within each exercise
    public static void graphExercisePerformance(Exercise exercise, ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your progression for " + exercise.toString() + " over your training sessions:"
                + System.lineSeparator() + System.lineSeparator());

        int numSessions = sessionList.size();
        int maxExercisePerformance = getMaxExercisePerformance(sessionList, exercise, numSessions);
        int yOffset = String.valueOf(maxExercisePerformance).length() - 1;
        int maxXHeaderLength = Math.max(DATETIME_LENGTH, TrainingSession.getLongestSessionDescription());

        String xHeaders = generateXHeader(numSessions, sessionList, maxXHeaderLength, yOffset);
        String mainContents = generateMainGraphPerformance(maxExercisePerformance, numSessions,
                maxXHeaderLength, sessionList, exercise);

        System.out.print(mainContents + xHeaders);
    }

    //For testing purposes
    /*
    public static void main(String[] args){
        ArrayList<TrainingSession> trainingSessions = new ArrayList<>();
        User user = new User("male", "13"); // Assuming a default User constructor exists

        for (int i = 0; i < 5; i++) {
            TrainingSession session = new TrainingSession(
                    LocalDateTime.now().minusDays(i),
                    "Session " + (i + 1),
                    user
            );

            // Edit exercises with varied reps
            session.editExercise(Exercise.PULL_UP, String.valueOf(6 + i * 2));
            session.editExercise(Exercise.SHUTTLE_RUN, "11.2");
            session.editExercise(Exercise.SIT_AND_REACH, String.valueOf(30 + i * 3));
            session.editExercise(Exercise.SIT_UP, String.valueOf(20 + i * 4));
            session.editExercise(Exercise.STANDING_BROAD_JUMP, String.valueOf(150 + i * 10));
            session.editExercise(Exercise.WALK_AND_RUN, "12:30");

            trainingSessions.add(session);
        }
        graphSessions(trainingSessions);
        graphExercisePoints(Exercise.WALK_AND_RUN, trainingSessions);
        graphExercisePerformance(Exercise.SIT_AND_REACH, trainingSessions);
    }*/
}
