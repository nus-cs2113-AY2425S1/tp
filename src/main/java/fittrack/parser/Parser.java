package fittrack.parser;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;


import static fittrack.messages.Messages.ADD_SESSION_COMMAND;
import static fittrack.messages.Messages.DELETE_SESSION_COMMAND;
import static fittrack.messages.Messages.EDIT_EXERCISE_COMMAND;
import static fittrack.messages.Messages.LIST_SESSIONS_COMMAND;
import static fittrack.messages.Messages.SET_USER_COMMAND;
import static fittrack.messages.Messages.VIEW_SESSION_COMMAND;
import static fittrack.ui.Ui.printAddedSession;
import static fittrack.ui.Ui.printDeletedSession;
import static fittrack.ui.Ui.printSessionList;
import static fittrack.ui.Ui.printSessionView;
import static fittrack.ui.Ui.printUnrecognizedInputMessage;

public class Parser {

    /**
     * Parses the user's input and calls the corresponding method based on the command.
     *
     * @param user The user object to be manipulated based on the command.
     * @param input The input string entered by the user.
     * @param sessionList The list of sessions to be manipulated based on the command.
     */
    public static void parse(User user, String input, ArrayList<TrainingSession> sessionList) {
        assert input != null : "Input must not be null";
        assert user != null : "User object must not be null";
        assert sessionList != null : "Session list must not be null";

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
            assert description.contains(" ") : "Invalid user data format";
            sentence = description.split(" ", 2);
            user.setGender(sentence[0]);
            user.setAge(sentence[1]);
            break;
        case ADD_SESSION_COMMAND:
            assert !description.isEmpty() : "Session description must not be empty";
            LocalDateTime currentTime = LocalDateTime.now();
            sessionList.add(new TrainingSession(currentTime, description, user));
            printAddedSession(sessionList);
            break;
        case EDIT_EXERCISE_COMMAND:
            sentence = description.split(" ", 3);
            assert sentence.length == 3 : "Edit exercise command requires exactly 3 arguments";
            int sessionIndex = Integer.parseInt(sentence[0]) - 1;
            int exerciseIndex = Integer.parseInt(sentence[1]);
            String exerciseData = sentence[2];
            assert sessionIndex >= 0 && sessionIndex < sessionList.size() : "Session index out of bounds";
            sessionList.get(sessionIndex).editExercise(exerciseIndex, exerciseData);
            break;
        case LIST_SESSIONS_COMMAND:
            printSessionList(sessionList); // Print the list of sessions
            break;
        case VIEW_SESSION_COMMAND:
            int viewIndex = Integer.parseInt(description) - 1;
            assert viewIndex >= 0 && viewIndex < sessionList.size() : "View session index out of bounds";
            printSessionView(sessionList, viewIndex); // Print the session view
            break;
        case DELETE_SESSION_COMMAND:
            int indexToDelete = Integer.parseInt(description) - 1;
            assert indexToDelete >= 0 && indexToDelete < sessionList.size() : "Delete session index out of bounds";
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

