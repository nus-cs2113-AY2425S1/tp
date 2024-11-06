package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;

import java.util.ArrayList;

public class GraphPoints extends GraphBase {
    static final String SESSTION_DESCRIPTION_STRING = "Session Description";
    static final String DATE_STRING = "| Date ";
    static final String POINTS_STRING = "| Points";
    static final int SESSION_DESCRIPTION_LENGTH = 19; //Length of the String, "Session Description"
    static final int DATE_BLANK_LENGTH = 12; //Number of blank spaces to print after "| Date "

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
}
