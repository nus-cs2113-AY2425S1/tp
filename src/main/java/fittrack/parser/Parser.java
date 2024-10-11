package fittrack.parser;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static fittrack.messages.Messages.*;
import static fittrack.ui.Ui.printAddedSession;
import static fittrack.ui.Ui.printDeletedSession;
import static fittrack.ui.Ui.printSessionList;
import static fittrack.ui.Ui.printSessionView;
import static fittrack.ui.Ui.printUnrecognizedInputMessage;

public class Parser {

    /**
     * Parses the user's input and calls the corresponding method based on the command.
     *
     * @param input    The input string entered by the user.
     * @param sessionList The list of sessions to be manipulated based on the command.
     */
    public static void parse(User user, String input, ArrayList<TrainingSession> sessionList) {
        String[] sentence = {input, input};
        String command = input;
        String description = "";

        // Split the input into command and description if applicable
        if (input.contains(" ")) {
            sentence = input.split(" ", 2);
            command = sentence[0];
            description = sentence[1];
        }

        switch (command) {
            case SET_USER_COMMAND:
                sentence = description.split(" ", 2);
                user.setGender(sentence[0]);
                user.setAge(sentence[1]);
                break;
            case ADD_SESSION_COMMAND:
                sessionList.add(new TrainingSession(LocalDateTime.now().toString() , description, user));
                printAddedSession(sessionList);
                break;
            case EDIT_EXERCISE_COMMAND:
                sentence = description.split(" ", 3);
                int sessionIndex = Integer.parseInt(sentence[0]) - 1;
                int exerciseIndex = Integer.parseInt(sentence[1]);
                int exerciseData = Integer.parseInt(sentence[2]);
                sessionList.get(sessionIndex).editExercise(exerciseIndex, exerciseData);
                break;
            case LIST_SESSIONS_COMMAND:
                printSessionList(sessionList); // Print the list of sessions
                break;
            case VIEW_SESSION_COMMAND:
                printSessionView(sessionList, Integer.parseInt(description)-1); // Print the list of sessions
                break;
            case DELETE_SESSION_COMMAND:
                int indexToDelete = Integer.parseInt(description) - 1;
                TrainingSession sessionToDelete = sessionList.get(indexToDelete);
                sessionList.remove(indexToDelete);
                printDeletedSession(sessionList, sessionToDelete);
                break;
            default:
                printUnrecognizedInputMessage(); // Response to unrecognized inputs
                break;
        }
    }
}
