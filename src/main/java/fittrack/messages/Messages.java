package fittrack.messages;

public class Messages {
    public static final String SEPARATOR = "____________________________________________________________";

    public static final String SET_USER_COMMAND = "set";
    public static final String ADD_SESSION_COMMAND = "add";
    public static final String EDIT_EXERCISE_COMMAND = "edit";
    public static final String LIST_SESSIONS_COMMAND = "list";
    public static final String VIEW_SESSION_COMMAND = "view";
    public static final String DELETE_SESSION_COMMAND = "delete";
    public static final String EXIT_COMMAND = "exit";

    public static final String INIT_SENTENCE = "Hello! I'm FitTrack."
            + System.lineSeparator() + "Please input your gender and age:";
    public static final String LIST_MESSAGE = "Here are your training sessions:";
    public static final String LIST_EMPTY_MESSAGE = "Your list is currently empty.";
    public static final String ADD_SESSION_MESSAGE = "Got it. I've added a new training session:"
            + System.lineSeparator() + "    ";
    public static final String DELETE_SESSION_MESSAGE = "Got it. I've deleted this training session:"
            + System.lineSeparator() + "    ";
    public static final String NONNUMERICAL_INDEX_MESSAGE = "Please provide a valid numerical index.";
    public static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Sorry, but that index is not within the list.";
    public static final String INVALID_INPUT_MESSAGE = "I'm sorry, I don't know what that means.";
    public static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!";
}