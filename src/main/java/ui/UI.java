package ui;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import exceptions.SEPEmptyException;
import student.Student;
import university.University;
import university.UniversityRepository;

import static filehandler.FileHandler.isValidPath;

/**
 * Handles user interface FindMySEP application.
 * Methods to print messages, retrieve user input, display ASCII table
 * for student data.
 */
public class UI {
    private static final int LINE_LENGTH = 80;
    public static final String HORIZONTAL_LINE = "-".repeat(LINE_LENGTH);
    private final Scanner scanner;

    /**
     * Constructor for UI class
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the given item to the console, wrapped by horizontal lines above
     * and below the specified item.
     *
     * @param item The item to be printed, which can be of any type.
     *             If the item is null, it prints "null".
     */
    public <T> void printResponse(T item) {
        String content = (item == null) ? "null" : item.toString();
        System.out.println(HORIZONTAL_LINE + "\n" + content + "\n" + HORIZONTAL_LINE);
    }

    /**
     * Retrieves a line of user input from the console.
     *
     * @return The line of input entered by the user, or an empty string
     *         if an error occurs while reading input.
     */
    public String getUserInput() {
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            printResponse("Error reading input: " + e.getMessage());
            return ""; // Return an empty string
        }
    }

    /**
     * Prints a greeting message to the console.
     */
    public void sayHi() {
        printResponse(Messages.WELCOME);
    }

    /**
     * Prints a farewell message to the console.
     */
    public void cleanupAndExit() {
        printResponse("Do you want to save your results?");
    }

    /**
     * Prints table displaying student information.
     * The table includes student IDs, GPAs, and preference rankings.
     *
     * @param studentList ArrayList of Student objects to be printed
     * @throws SEPEmptyException if the student list is null or empty.
     */
    public void printStudentList(ArrayList<Student> studentList) throws SEPEmptyException {
        if (studentList == null || studentList.isEmpty()) {
            throw SEPEmptyException.rejectEmptyStudentList();
        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Student", "GPA", "Preference Rankings");
        at.addRule();

        for (Student s : studentList) {
            // Using Java Streams to create a comma-separated preference rankings string
            String uniPrefsString = s.getUniPreferences().stream()
                    .map(String::valueOf) // Convert each Integer to String
                    .collect(Collectors.joining(",")); // Join them with commas

            // Use the uniPrefsString for adding to the table
            at.addRow(s.getId(), s.getGpa(), uniPrefsString);
        }
        at.addRule();

        at.setTextAlignment(TextAlignment.CENTER);
        at.setPaddingLeft(3);
        at.setPaddingRight(3);

        at.getRenderer().setCWC(new CWC_LongestLine());
        printResponse("Here is the list:\n" + at.render());
    }

    /**
     * Prints a buffering message when the algorithm is running.
     * Upon algorithm completion, replaces the buffering message with a
     * completed message.
     */
    public void printAllocatingMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\r" + Messages.ALLOCATE_COMPLETE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints table displaying allocation results.
     * The table includes student IDs and respective allocation outcomes.
     *
     * @param studentList ArrayList of Student objects to be printed
     * @throws SEPEmptyException If the student list is null or empty,
     *     indicating that there are no students to generate.
     */
    public void generateReport(ArrayList<Student> studentList) throws SEPEmptyException {
        if (studentList == null || studentList.isEmpty()) {
            throw SEPEmptyException.rejectEmptyStudentList();
        }

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Student", "University Granted");
        at.addRule();

        for (Student s : studentList) {
            int choice = s.getAllocatedUniversity();
            University allocated = UniversityRepository.getUniversityByIndex(choice);

            if (allocated != null) {
                at.addRow(s.getId(), choice + ". " + allocated.getFullName());
            } else {
                at.addRow(s.getId(), "Not Allocated"); // Handle cases with no allocation
            }
        }
        at.addRule();

        at.setTextAlignment(TextAlignment.LEFT);
        at.setPaddingLeft(1);
        at.setPaddingRight(1);

        at.getRenderer().setCWC(new CWC_LongestLine());
        printResponse("Here is the allocation report:\n" + at.render());
    }

    /**
     * Prints a prompt for the user on program entry.
     */
    public void printConfigMessage() {
        StringBuilder output = new StringBuilder();
        output.append("Good day to you!" + "\n")
            .append("Before we begin, would you like to:\n")
            .append("1. Manually input students data\n")
            .append("2. Upload a file (.csv, .txt, .json)\n")
            .append("Please choose 1 or 2 or exit :)");
        printResponse(output.toString());
    }

    public void printProcessError() {
        printResponse("""
                Process error! To re-upload a file, please restart the program by entering 'exit'\s
                followed by 'no' and \
                ensure the file is formatted correctly before retrying. \
                
                Otherwise, you can continue to use the program. Enter help for available commands.""");
    }

    public void printFileLoadSuccessMessage() {
        printResponse("File loaded successfully! Let's begin! Enter help for available commands.");
    }

    /**
     * Checks if the user's input matches the expected input format.
     *
     * @param option The user's input response to the initial prompt
     * @return A boolean that indicates validity of the input string
     */
    public boolean isValidOption(String option) {
        return option.equals("1") || option.equals("2") || option.equals("exit")
                || option.equals("bye") || option.equals("quit");
    }

    /**
     * Gets the user's input for file storage option.
     *
     * @return A string guaranteed to contain the user's choice for preferred input mode.
     */
    public String promptFilePath() {
        String command = pollInitialInput();
        return processInput(command);
    }

    /**
     * Verifies the input required for processing the user's choice.
     *
     * @return A string guaranteed to be in the expected form.
     */
    public String pollInitialInput() {
        String input = getUserInput();
        while (!isValidOption(input)) {
            printResponse("Boss, type 1 or 2 or exit only leh!");
            input = getUserInput().toLowerCase();
        }
        if (input.equals("exit") || input.equals("quit") || input.equals("bye")) {
            sayBye();
            System.exit(0);
        }
        return input;
    }

    /**
     * Verifies the input required for processing the input file path.
     *
     * @return The file path of the input file as a string guaranteed to be in the expected form.
     */
    public String processFilePathInput() {
        String input = getUserInput();
        while (input.isEmpty() || !isValidPath(input)) {
            if (input.isEmpty()) {
                printResponse("Filepath cannot be empty!");
            }
            if (!isValidPath(input)) {
                printResponse("Invalid filepath!");
            }
            input = getUserInput();
        }
        return input;
    }

    /**
     * Ensures that the response for selecting input mode is not empty. Exits the program if the user
     * inputs the exit command. Prompts for file path if user chooses input file option.
     *
     * @param input The user's input response to the initial prompt
     * @return If the user chooses to select an input file source, returns a string containing
     *         the file path of the user's student list file.
     *         If the user chooses manual input, returns an empty string.
     */
    public String processInput(String input) {
        if (input.equals("exit")) {
            cleanupAndExit();
            System.exit(0);
        }
        if (input.equals("2")) {
            printResponse("Example: C:\\Users\\bob\\OneDrive\\Documents\\tp\\test.csv" +
                    "\nPlease enter the ABSOLUTE path to the file: ");
            return processFilePathInput();
        }
        sayHi();
        return "";
    }

    /**
     * Verifies that the user's choice of file extension is valid.
     *
     * @param input The user's choice of file output extension format.
     * @return A boolean representing the validity of choice.
     */
    public boolean isValidSaveChoice(String input) {
        return input.equals("csv") || input.equals("txt") || input.equals("json");
    }

    /**
     * Prompts the user for their choice of file type for the save file, and checks the input.
     *
     * @return A string guaranteed to contain a valid file extension.
     */
    public String getSaveFileChoice() {
        printResponse("Please choose a file type (CSV, JSON, TXT) to save your results.");
        String input = getUserInput().toLowerCase();
        while (!isValidSaveChoice(input)) {
            printResponse("Please enter a valid (CSV, JSON, TXT) save choice!");
            input = getUserInput();
        }
        return input;
    }

    /**
     * Prompts the user whether they want to save the allocation results.
     *
     * @return A boolean representing whether the user chooses to save a file.
     */
    public boolean toSave() {
        String save = getUserInput().toLowerCase();
        while (!(save.equals("yes") || save.equals("no"))) {
            printResponse("Please enter a valid command (Yes/No)!");
            save = getUserInput().toLowerCase();
        }
        return save.equals("yes");
    }

    /**
     * Prints a farewell message and shuts down the scanner object.
     */
    public void sayBye() {
        printResponse("Adios, amigo!");
        scanner.close();
    }
}
