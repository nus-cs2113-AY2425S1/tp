package tutorlink.ui;

import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LOGO = "___________      __               .____    .__        __\n"
            + "\\__    ___/_ ___/  |_  ___________|    |   |__| ____ |  | __\n"
            + "  |    | |  |  \\   __\\/  _ \\_  __ \\    |   |  |/    \\|  |/ /\n"
            + "  |    | |  |  /|  | (  <_> )  | \\/    |___|  |   |  \\    <\n"
            + "  |____| |____/ |__|  \\____/|__|  |_______ \\__|___|  /__|_ \\\n"
            + "                                          \\/       \\/     \\/\n";
    private static final String HALF_BREAK_LINE =
            "-------------------------";
    private static final String FULL_BREAK_LINE =
            "-------------------------------------------------------------";

    private static final String HELP_MESSAGE = """
            -------------------   List of Commands   --------------------
            help: Displays list of commands
              Example: help
            
            add_student: Adds a student to the class roster
              Example: add_student i/A1234567X n/John Doe
            
            delete_student: Deletes a student from the class roster
              Example: delete_student i/A1234567X
            
            list_student: Lists all students in the class
              Example: list_student
            
            find_student: Finds a student in the class roster by name or matric number
              Example: find_student i/A1234567X n/John Doe
            
            add_component: Adds a new grading component to the class
              Example: add_component c/Quiz 1 w/30 m/50
            
            delete_component: Deletes a grading component from the class
              Example: delete_component c/Quiz 1
            
            update_component: Updates a component with a new maxscore or weight
              Example: update_component c/Quiz 1 w/40 m/60
            
            list_component: Lists all grading components
              Example: list_component
            
            add_grade: Adds a grade for a student for a specific component
              Example: add_grade i/A1234567X c/Quiz 1 s/45
            
            delete_grade: Deletes a student's grade for a specific component
              Example: delete_grade i/A1234567X c/Quiz 1
            
            list_grade: Lists all grades for a student
              Example: list_grade i/A1234567X
            
            bye: Exits the program
              Example: bye
            -------------------------------------------------------------""";

    private Scanner in = new Scanner(System.in);

    public Ui() {
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void displayWelcomeMessage() {
        System.out.println(FULL_BREAK_LINE);
        System.out.println(LOGO);
        System.out.println(FULL_BREAK_LINE);
        System.out.println("Hello! I'm TutorLink\nWhat can I do for you?");
        System.out.println(FULL_BREAK_LINE);
    }

    public void displayHelpMessage() {
        System.out.println(HELP_MESSAGE);
    }

    public void displayResult(CommandResult result) {
        System.out.println(HALF_BREAK_LINE + "   Result   " + HALF_BREAK_LINE);
        System.out.println(result.toString());
        System.out.println(FULL_BREAK_LINE);
    }

    public void displayException(TutorLinkException error) {
        System.out.println(HALF_BREAK_LINE + "   Error   " + HALF_BREAK_LINE);
        System.out.println(error.getMessage());
        System.out.println(FULL_BREAK_LINE);
    }

    public void displayDiscardedEntries(ArrayList<String> discardedEntries, String header) {
        if (discardedEntries.isEmpty()) {
            return;
        }
        System.out.println(FULL_BREAK_LINE);
        System.out.println(header);
        for (String entry : discardedEntries) {
            System.out.println("  " + entry);
        }
    }
}
