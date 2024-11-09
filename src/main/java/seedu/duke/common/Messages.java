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

// public static final String MESSAGE_HELP_PATIENT = Colors.ANSI_BLUE + """
//         Here are the list of commands you can use in patient mode:
//         """ + Colors.ANSI_RESET + Colors.ANSI_YELLOW + """

//         COMMANDS:
//         """ + Colors.ANSI_RESET +
//         Colors.ANSI_GREEN + """
//             1. add    """ + Colors.ANSI_RESET + """ - Add a patient to the system (e.g. add John Doe)
//             """ + Colors.ANSI_GREEN + """2. delete """ + Colors.ANSI_RESET + """ - Delete a patient from the system
//             """ + Colors.ANSI_GREEN + """3. list   """ + Colors.ANSI_RESET + """ - List all patients in the system
//             """ + Colors.ANSI_GREEN + """4. select """ + Colors.ANSI_RESET + """ - Select a patient in the system
//             """ + Colors.ANSI_GREEN + """5. find   """ + Colors.ANSI_RESET + """ - Find a patient in the system
//             """ + Colors.ANSI_GREEN + """6. help   """ + Colors.ANSI_RESET + """ - Show help message
//             """ + Colors.ANSI_GREEN + """7. exit   """ + Colors.ANSI_RESET + """ - Exit the program
//         """;

public static final String MESSAGE_HELP_PATIENT = Colors.ANSI_BLUE + """
    Here are the list of commands you can use in patient mode:
    """ + Colors.ANSI_RESET + Colors.ANSI_YELLOW + """

    COMMANDS:
    """ + Colors.ANSI_RESET +
    " 1. " + Colors.ANSI_RED + "add" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "Add a patient to the system" + Colors.ANSI_RESET + "\n" +
            """
                            Format : add NAME /tag TAG_NAME
                            Example: add Alice /tag HighPriority
                            Info   : /tag is optional
            """ +
    " 2. " + Colors.ANSI_RED + "delete" + Colors.ANSI_RESET + "  - " + Colors.ANSI_BLUE + "Delete a patient from the system" + Colors.ANSI_RESET + "\n" +
            """
                            Format : delete INDEX
                            Example: delete 1
                            Info   : INDEX is the index of the patient in the list
            """ +
     " 3. " + Colors.ANSI_RED + "list" + Colors.ANSI_RESET + "    - " + Colors.ANSI_BLUE + "List all patients in the system" + Colors.ANSI_RESET + "\n" +
            """
                            Example: list
            """ +
    " 4. " + Colors.ANSI_RED + "select" + Colors.ANSI_RESET + "  - " + Colors.ANSI_BLUE + "Select a patient in the system" + Colors.ANSI_RESET + "\n" +
            """
                            Format : select INDEX
                            Example: select 1
                            Info   : INDEX is the index of the patient in the list
            """ +
    " 5. " + Colors.ANSI_RED + "find" + Colors.ANSI_RESET + "    - " + Colors.ANSI_BLUE + "Find a patient in the system" + Colors.ANSI_RESET + "\n" +
            """
                            Format : find KEYWORD
                            Example: find Alice
                            Info   : KEYWORD is the name of the patient to search for
            """ +
    " 6. " + Colors.ANSI_RED + "help" + Colors.ANSI_RESET + "    - " + Colors.ANSI_BLUE + "Show help message" + Colors.ANSI_RESET + "\n" +
            """
                            Example: help
            """ +
    " 7. " + Colors.ANSI_RED + "exit" + Colors.ANSI_RESET + "    - " + Colors.ANSI_BLUE + "Show help message" + Colors.ANSI_RESET + "\n" +
            """
                            Example: exit
            """;



    // public static final String MASSAGE_HELP_TASK = """
    //         Here are the list of commands you can use in task mode:

    //         1. add      - Add a task to the system
    //         2. delete   - Delete a task from the system
    //         3. list     - List all tasks in the system
    //         4. find     - Find a task in the system
    //         5. mark     - Mark a task as done
    //         6. unmark   - Mark a task as undone
    //         7. help     - Show help message
    //         8. exit     - Exit the program
    //         """;

    public static final String MESSAGE_HELP_TASK = Colors.ANSI_BLUE + """
        Here are the list of commands you can use in task mode:
        """ + Colors.ANSI_RESET + Colors.ANSI_YELLOW + """

        COMMANDS:
        """ + Colors.ANSI_RESET +
        " 1. " + Colors.ANSI_RED + "todo" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "Adds a new todo task to the list of tasks" + Colors.ANSI_RESET + "\n" +
        """
                            Format : todo TODO_NAME /tag TAG_NAME
                            Example: todo Check up /tag urgent
                            Info   : /tag is optional
        """ +
        " 2. " + Colors.ANSI_RED + "deadline" + Colors.ANSI_RESET + " - " + Colors.ANSI_BLUE + "Adds a task with deadline to the list of tasks" + Colors.ANSI_RESET + "\n" +
        """
                            Format : deadline DEADLINE_NAME /by DATE_TIME /tag TAG_NAME
                            Example: deadline Have medicine /by 19:00 /tag urgent
                            Info   : /by is needed, /tag is optional
        """ +
        " 2. " + Colors.ANSI_RED + "repeat" + Colors.ANSI_RESET + "   - " + Colors.ANSI_BLUE + "Adds a task with reminder to repeat it to the list of tasks." + Colors.ANSI_RESET + "\n" +
        """
                            Format : repeat TODO_NAME /every RECUR_BASIS /tag TAG_NAME
                            Example: repeat Drink supplements /every day /tag important
                            Info   : /every is needed, /tag is optional
        """ +
        " 2. " + Colors.ANSI_RED + "delete" + Colors.ANSI_RESET + "   - " + Colors.ANSI_BLUE + "Delete a task from the system" + Colors.ANSI_RESET + "\n" +
                """
                                    Format : delete INDEX
                                    Example: delete 1
                                    Info   : INDEX is the index of the task in the list
                """ +
         " 3. " + Colors.ANSI_RED + "list" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "List all tasks in the system" + Colors.ANSI_RESET + "\n" +
                """
                                    Example: list
                """ +
        " 4. " + Colors.ANSI_RED + "find" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "Find a task in the system" + Colors.ANSI_RESET + "\n" +
                """
                                    Format : find KEYWORD
                                    Example: find supplements
                                    Info   : KEYWORD is the description of the task to search for
                """ +
        " 5. " + Colors.ANSI_RED + "mark" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "Mark a task as done" + Colors.ANSI_RESET + "\n" +
                """
                                    Format : mark INDEX
                                    Example: mark 1
                                    Info   : INDEX is the index of the task in the list
                """ +
        " 6. " + Colors.ANSI_RED + "unmark" + Colors.ANSI_RESET + "   - " + Colors.ANSI_BLUE + "Mark a task as undone" + Colors.ANSI_RESET + "\n" +
                """
                                    Format : unmark INDEX
                                    Example: unmark 1
                                    Info   : INDEX is the index of the task in the list
                """ +
        " 7. " + Colors.ANSI_RED + "help" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "Show help message" + Colors.ANSI_RESET + "\n" +
                """
                                    Example: help
                """ +
        " 8. " + Colors.ANSI_RED + "exit" + Colors.ANSI_RESET + "     - " + Colors.ANSI_BLUE + "Exit the program" + Colors.ANSI_RESET + "\n" +
                """
                                    Example: exit
                """
        ;


}
