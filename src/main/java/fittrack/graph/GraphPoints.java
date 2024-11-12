package fittrack.graph;

import fittrack.enums.Exercise;
import fittrack.trainingsession.TrainingSession;

import java.util.ArrayList;

/**
 * This class is responsible for generating and displaying point progression graphs for training sessions.
 * It handles the generation of graphical representations of the total points or the points for specific exercises
 * across various training sessions.
 */
public class GraphPoints extends GraphBase {
    static final String SESSTION_DESCRIPTION_STRING = "Session Description";
    static final String DATE_STRING = "| Date ";
    static final String POINTS_STRING = "| Points";
    static final int SESSION_DESCRIPTION_LENGTH = 19; //Length of the String, "Session Description"
    static final int DATE_BLANK_LENGTH = 12; //Number of blank spaces to print after "| Date "

    /**
     * Generates the header for the session description column in the points graph.
     * This ensures that the column is properly aligned based on the length of the session description.
     *
     * @param descriptionLength The length of the session description to calculate the necessary padding.
     * @return A formatted string representing the header for the session description column.
     */
    private static String generateSessionDescHeaderPoints(int descriptionLength) {
        return SESSTION_DESCRIPTION_STRING + generateChar(descriptionLength - SESSION_DESCRIPTION_LENGTH + 1,
                ' ');
    }

    /**
     * Generates the entire header for the points graph, which includes the session description,
     * date, and points columns. It also adds a separator line beneath the header.
     *
     * @param descriptionLength The length of the session description, used to align the header.
     * @return A formatted string representing the complete header for the points graph.
     */
    private static String generateGraphHeaderPoints(int descriptionLength) {
        String sessionDescHeader = generateSessionDescHeaderPoints(descriptionLength);
        String header = sessionDescHeader + DATE_STRING + generateChar(DATE_BLANK_LENGTH, ' ') + POINTS_STRING;
        String headerSeparator = generateChar(sessionDescHeader.length(), '-') + "|" +
                generateChar(DATE_BLANK_LENGTH + DATE_STRING.length() - 1, '-') + "|";

        return header + System.lineSeparator() + headerSeparator + System.lineSeparator();
    }

    /**
     * Generates a row for the points graph, which includes the session description, session datetime,
     * and a graphical representation of points as asterisks. It also displays the total points for the session.
     *
     * @param session The training session for which the points are displayed.
     * @param exercise The exercise for which the points are calculated, or null for total points.
     * @param descriptionLength The length of the session description used to ensure proper column alignment.
     * @return A formatted string representing a single row of the points graph.
     */
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

    /**
     * Generates the entire point progression graph, including the header and all rows for each training session.
     * It loops through the list of sessions and generates a formatted string representation for each one.
     *
     * @param sessionList The list of training sessions to be displayed in the graph.
     * @param exercise The specific exercise for points calculation, or null for total points across all exercises.
     * @return A formatted string representing the full points graph.
     */
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

    /**
     * Graphs the total points across all training sessions, displaying the points progression over time.
     *
     * @param sessionList The list of training sessions to be graphed.
     */
    public static void graphSessions(ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your point progression over the various training sessions:" + System.lineSeparator());
        System.out.print(generatePointGraphs(sessionList, null) + System.lineSeparator());
    }

    /**
     * Graphs the points for a specific exercise across all training sessions, displaying the points progression
     * for that exercise over time.
     *
     * @param exercise The exercise for which the points progression is displayed.
     * @param sessionList The list of training sessions to be graphed.
     */
    public static void graphExercisePoints(Exercise exercise, ArrayList<TrainingSession> sessionList) {
        System.out.print("Here's your point progression for " + exercise.toString() + " over your training sessions:"
                + System.lineSeparator());
        System.out.print(generatePointGraphs(sessionList, exercise) + System.lineSeparator());
    }
}
