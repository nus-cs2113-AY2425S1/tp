package seedu.duke.common;

import seedu.duke.MediTask;
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
                        """ + MediTask.VERSION + "\n";

    // Messages
    public static final String MESSAGE_PROMPT = Colors.ANSI_GREEN + "> " + Colors.ANSI_RESET;
    public static final String MESSAGE_BREAKLINE = Colors.ANSI_BLACK
            + "────────────────────────────────────────────────────────────" + Colors.ANSI_RESET;
    public static final String MESSAGE_MAIN_PROMPT = Messages.APPLICATION_NAME + " " + Messages.MESSAGE_PROMPT;
    public static final String MESSAGE_WELCOME = "Welcome to " + APPLICATION_NAME + "!";
    public static final String MESSAGE_GOODBYE = "Goodbye! Hope to see you again soon!";

    public static final String MESSAGE_HELP_PATIENT = """
            Here are the list of commands you can use in patient mode:

            1. add      - Add a patient to the system
            2. delete   - Delete a patient from the system
            3. list     - List all patients in the system
            4. select   - Select a patient in the system
            5. find     - Find a patient in the system
            6. help     - Show help message
            7. exit     - Exit the program
            """;

    public static final String MASSAGE_HELP_TASK = """
            Here are the list of commands you can use in task mode:

            1. add      - Add a task to the system
            2. delete   - Delete a task from the system
            3. list     - List all tasks in the system
            4. find     - Find a task in the system
            5. mark     - Mark a task as done
            6. unmark   - Mark a task as undone
            7. help     - Show help message
            8. exit     - Exit the program
            """;

}
