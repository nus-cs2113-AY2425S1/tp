import java.util.Scanner;

/**
 * A class representing the user interface for interacting with the user.
 */
public class Ui {
    private final String LINE = "\t_____________________________________________________________________________";
    private final String LOGO = """
                 (\\
                  \\ \\
              __    \\/ ___,.-------..__        __
             //\\\\ _,-'\\\\               `'--._ //\\\\
             \\\\ ;'      \\\\                   `: //
              `(          \\\\                   )'
                :.          \\\\,----,         ,;
                 `.`--.___   (    /  ___.--','
                   `.     ``-----'-''     ,'
                      -.               ,-
                         `-._______.-'\
            """;
    private Scanner userInput;

    /**
     * Constructor for a <code>Ui</code> object.
     * Overloads default constructor to initialise a scanner object reading user inputs.
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Display a greeting message.
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println("\tGreetings, this is YMFC.");
        System.out.println("\tGot a recipe? Pass it to me.");
        System.out.println("\tNeed a recipe? Just ask me.");
        System.out.println("\tNeed your dishes washed? Get off your couch.");
        System.out.println(LINE);
    }

    /**
     * Display a goodbye message.
     */
    public void bidFarewell() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println("\tBye bye, come again!");
        System.out.println(LINE);
    }

    /**
     * Return user input as a string when user hit enter key.
     *
     * @return String representing what user typed into the program
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /**
     * Display a specified message.
     *
     * @param input String array representing the message, each element representing one line
     */
    public void printMessage(String[] input) {
        System.out.println(LINE);
        for (String line: input) {
            System.out.println("\t" + line);
        }
        System.out.println(LINE);
    }
}
