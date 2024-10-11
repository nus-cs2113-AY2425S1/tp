package fittrack.messages;

public class Messages {
    public static final String SEPARATOR = "____________________________________________________________"; // Separator line to demarcate output messages

    public static final String SET_USER_COMMAND = "set";
    public static final String ADD_SESSION_COMMAND = "add";
    public static final String EDIT_EXERCISE_COMMAND = "edit";
    public static final String LIST_SESSIONS_COMMAND = "list";
    public static final String VIEW_SESSION_COMMAND = "view";
    public static final String DELETE_SESSION_COMMAND = "delete";
    public static final String EXIT_COMMAND = "exit";

    public static final String INIT_SENTENCE = "Hello! I'm FitTrack." + System.lineSeparator() + "Please input your gender and age:"; // Initial greeting
    public static final String LIST_MESSAGE = "Here are your training sessions:"; // Message when listing training sessions
    public static final String LIST_EMPTY_MESSAGE = "Your list is currently empty."; // Message when no training sessions are in the list
    public static final String ADD_SESSION_MESSAGE = "Got it. I've added a new training session:" + System.lineSeparator() + "    "; // Message when a new training session is added
    public static final String DELETE_SESSION_MESSAGE = "Got it. I've deleted this training session:" + System.lineSeparator() + "    "; // Message when a training session is deleted
    public static final String NONNUMERICAL_INDEX_MESSAGE = "Please provide a valid numerical index."; // Message for non-numeric index
    public static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Sorry, but that index is not within the list."; // Message for out-of-bounds index
    public static final String INVALID_INPUT_MESSAGE = "I'm sorry, I don't know what that means."; // Message for invalid input
    public static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!"; // Exit message
}