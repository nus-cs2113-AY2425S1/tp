package fittrack.parser;
import fittrack.user.User;
import java.util.ArrayList;

import static fittrack.messages.Messages.ADD_SESSION_COMMAND;
import static fittrack.messages.Messages.DELETE_SESSION_COMMAND;
import static fittrack.messages.Messages.EDIT_SESSION_COMMAND;
import static fittrack.messages.Messages.LIST_SESSIONS_COMMAND;
import static fittrack.messages.Messages.SET_USER_COMMAND;
import static fittrack.messages.Messages.VIEW_SESSION_COMMAND;
import static fittrack.ui.Ui.printSessionList;
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
                sessionList.add(new TrainingSession);
                break;
            case EDIT_SESSION_COMMAND:
                sessionList(description).editSession();
                break;
            case LIST_SESSIONS_COMMAND:
                printSessionList(sessionList); // Print the list of sessions
                break;
            case VIEW_SESSION_COMMAND:
                sessionList(description).viewSession(); // Print the list of sessions
                break;
            case DELETE_SESSION_COMMAND:
                sessionList.remove(description);
                break;
            default:
                printUnrecognizedInputMessage(); // Response to unrecognized inputs
                break;
        }
    }
}
