package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;
//import fittrack.user.User;

//import java.time.LocalDateTime;
import java.util.ArrayList;

public class Graph {

    static final String SESSTION_DESCRIPTION_STRING = "Session Description";
    static final String DATE_STRING = "| Date ";
    static final String POINTS_STRING = "| Points";
    static final int SESSION_DESCRIPTION_LENGTH = 19; //Length of the String, "Session Description"
    static final int DATE_BLANK_LENGTH = 12; //Number of blank spaces to print after "| Date "
    static final int DATETIME_LENGTH = 16; //Length of Date format without spaces

    //Returns String of specified char
    private static String printChar(int num, char character){
        return String.valueOf(character).repeat(Math.max(0, num));
    }

    //Return String for "Session Description" header with blanks if required
    private static String getSessionDescHeaderPoints(int descriptionLength) {
        return SESSTION_DESCRIPTION_STRING + printChar(descriptionLength - SESSION_DESCRIPTION_LENGTH + 1,
                ' ');
    }

    //Print header for points graph
    private static void printGraphHeaderPoints(int descriptionLength) {
        String sessionDescHeader = getSessionDescHeaderPoints(descriptionLength);
        String header = sessionDescHeader + DATE_STRING + printChar(DATE_BLANK_LENGTH, ' ') + POINTS_STRING;
        String headerSeparator = printChar(sessionDescHeader.length(), '-') + "|" +
                printChar(DATE_BLANK_LENGTH + DATE_STRING.length() - 1, '-') + "|";

        System.out.print(header + System.lineSeparator() + headerSeparator + System.lineSeparator());
    }

    //Print rows for points graph
    private static void printGraphRowsPoints(TrainingSession session, Exercise exercise, int descriptionLength) {
        int description = session.getSessionDescription().length();
        int blankDescLength = descriptionLength - description;
        String descDatetime = session.getSessionDescription() + printChar(blankDescLength, ' ') + " | "
                + session.getSessionDatetime() + " | ";

        int totalPoints;
        StringBuilder asterisks = new StringBuilder();
        if(exercise == null) {
            totalPoints = session.getTotalPoints();
        } else {
            totalPoints = session.getExercisePoints(exercise);
        }
        asterisks.append("*".repeat(Math.max(0, totalPoints)));
        System.out.print(descDatetime + asterisks + " (" + totalPoints + ")" + System.lineSeparator());
    }

    //Prints the overall graph for points
    private static void printPointGraphs(ArrayList<TrainingSession> sessionList, Exercise exercise){
        //Getting longest possible training session description
        int sessionDescriptionLength = Math.max(SESSION_DESCRIPTION_LENGTH,
                TrainingSession.getLongestSessionDescription());

        //Print header for point table
        printGraphHeaderPoints(sessionDescriptionLength);
        //Print rows of point table
        for(TrainingSession session : sessionList) {
            printGraphRowsPoints(session, exercise, sessionDescriptionLength);
        }
    }

    //Graph the total points across training sessions
    public static void graphSessions(ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your point progression over the various training sessions:" + System.lineSeparator());
        printPointGraphs(sessionList, null);
    }

    //Graph the points within each exercise
    public static void graphExercisePoints(Exercise exercise, ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your point progression for " + exercise.toString() + " over your training sessions:"
                + System.lineSeparator());
        printPointGraphs(sessionList, exercise);
    }

    //Getting the X axis for the graph
    private static String getXHeader(int numTrainingSessions, ArrayList<TrainingSession> sessionList,
            int maxXHeaderLength, int yOffset){

        StringBuilder xHeadersDate = new StringBuilder();
        StringBuilder xHeadersDesc = new StringBuilder();
        int dateLengthDifference = maxXHeaderLength - DATETIME_LENGTH;

        for(int i = 0; i < numTrainingSessions; i++){
            String description = sessionList.get(i).getSessionDescription();
            int descriptionLength = description.length();
            int descLengthDifference = maxXHeaderLength - descriptionLength;

            xHeadersDesc.append(printChar((int) (Math.ceil((double) descLengthDifference /2) + 1), ' '));
            xHeadersDesc.append(description);
            xHeadersDesc.append(printChar(descLengthDifference/2 + 1, ' '));

            String datetime = sessionList.get(i).getSessionDatetime();
            xHeadersDate.append(printChar((int) (Math.ceil((double) dateLengthDifference/2) + 1), ' '));
            xHeadersDate.append(datetime);
            xHeadersDate.append(printChar(dateLengthDifference/2 + 1, ' '));
        }
        xHeadersDesc.insert(0, printChar(yOffset, ' '));
        xHeadersDate.insert(0, printChar(yOffset, ' '));
        return xHeadersDesc +  System.lineSeparator() + xHeadersDate;
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

    private static String getMainContents (int maxExercisePerformance, int numTrainingSessions,
            int maxXHeaderLength, ArrayList<TrainingSession> sessionList, Exercise exercise) {
        StringBuilder mainContents = new StringBuilder();
        int asterisksDifference = maxXHeaderLength - 1;

        //Looping through all lines of points
        for(int currentPerf = maxExercisePerformance + 1; currentPerf > 0; currentPerf--){
            //Getting yAxis
            mainContents.append(currentPerf);
            //Calculating offset to asterisks due to the yAxis
            int currentOffset = String.valueOf(currentPerf).length() - 1;
            int lineStartIndex = mainContents.length();
            //Looping through each training session, determining if they achieved a certain level of points
            for(int j = 0; j < numTrainingSessions; j++){
                if(sessionList.get(j).getExercisePerformance(exercise) >= currentPerf) {
                    mainContents.append(printChar((int) (Math.ceil((double) asterisksDifference / 2) + 1), ' '));
                    mainContents.append("*");
                    mainContents.append(printChar(asterisksDifference / 2 + 1, ' '));
                } else{
                    mainContents.append(printChar(maxXHeaderLength + 2, ' '));
                }
            }
            //Removing the blank space offset due to the yAxis
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
    public static void graphExercisePerformance(Exercise exercise, ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your progression for " + exercise.toString() + " over your training sessions:"
                + System.lineSeparator());

        int numTrainingSessions = sessionList.size();
        int maxExercisePerformance = getMaxExercisePerformance(sessionList, exercise, numTrainingSessions);
        int yOffset = String.valueOf(maxExercisePerformance).length() - 1;
        int maxXHeaderLength = Math.max(DATETIME_LENGTH, TrainingSession.getLongestSessionDescription());

        String xHeaders = getXHeader(numTrainingSessions, sessionList, maxXHeaderLength, yOffset);
        String mainContents =
                getMainContents(maxExercisePerformance, numTrainingSessions, maxXHeaderLength, sessionList, exercise);
        System.out.print(mainContents + xHeaders);
    }

    //For testing purposes
    /*public static void main(String[] args){
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
