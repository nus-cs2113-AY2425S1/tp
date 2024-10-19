package seedu.duke.common;

import seedu.duke.ui.Colors;

public class Messages {
    // Application name
    public static final String APPLICATION_NAME = Colors.ANSI_YELLOW + "MediTask" + Colors.ANSI_RESET;

    // Logo
    public static final String LOGO = """
                  _____
                 [IIIII]
                 )"\"\""\"(
                /       \\
               /         \\
               |`-.....-'|
               | MediTask|
             _ |`-.....-'|     _
            (\\)`-.__.__.(I) _(/)
              (I)  (/)(I)(\\)
                 (I)        Task management for medical professionals
                        """;;

    // Messages
    public static final String MESSAGE_PROMPT = Colors.ANSI_GREEN + "  " + Colors.ANSI_RESET;
    public static final String MESSAGE_BREAKLINE = Colors.ANSI_BLACK
            + "────────────────────────────────────────────────────────────" + Colors.ANSI_RESET;
    public static final String MESSAGE_MAIN_PROMPT = Messages.APPLICATION_NAME + " " + Messages.MESSAGE_PROMPT;
    public static final String MESSAGE_WELCOME = "Welcome to " + APPLICATION_NAME + "!";
    public static final String MESSAGE_GOODBYE = "Goodbye! Hope to see you again soon!";
}
