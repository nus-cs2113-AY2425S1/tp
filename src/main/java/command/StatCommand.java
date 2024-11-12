package command;

import exceptions.SEPEmptyException;
import exceptions.SEPException;
import exceptions.SEPFormatException;

import studentlist.StudentList;
import ui.UI;
import university.UniversityRepository;

/**
 * The StatCommand class handles the "stats" command, which displays statistical information
 * about the GPAs of students who have chosen a specified partner university. The command supports
 * viewing the average GPA (-avggpa) and minimum GPA (-mingpa) of students for a given university index.
 */
public class StatCommand extends Command {
    private static final String INTEGER_REGEX = "-?\\d+"; // Regular expression for validating integer input
    private String input;
    private UI ui;

    /**
     * Constructs a new StatCommand with the given student list, user input, and UI instance.
     *
     * @param studentList The StudentList containing all student information.
     * @param input The input string provided by the user.
     * @param ui The UI instance for interacting with the user.
     */
    public StatCommand(StudentList studentList, String input, UI ui) {
        super(studentList);
        assert studentList != null : "StudentList must not be null";
        assert input != null && !input.isEmpty() : "Input must not be null or empty";
        assert ui != null : "UI instance must not be null";
        
        this.input = input;
        this.ui = ui;
    }

    /**
     * Executes the stat command, validating the input format and displaying
     * the appropriate statistical information (average GPA or minimum GPA) based on
     * the specified university index.
     *
     * @throws SEPException if there is any issue with the command input or if the university
     *                      index is not found.
     */
    @Override
    public void run() {
        try {
            // Split the input and validate the parts
            String[] parts = input.split(" ");
            assert parts.length > 0 : "Input split should produce parts";

            if (parts.length != 3) {
                throw SEPFormatException.rejectStatFormat();
            }

            // Validate the stat type (either -avggpa or -mingpa)
            String statType = parts[1].trim().toLowerCase();
            assert statType != null : "Stat type should not be null";
            if (!statType.equals("-avggpa") && !statType.equals("-mingpa")) {
                throw SEPFormatException.rejectStatFormat();
            }

            // Validate the university index format
            String uniIndexString = parts[2].trim();
            assert uniIndexString != null : "University index string should not be null";
            if (!uniIndexString.matches(INTEGER_REGEX)) {
                throw SEPFormatException.rejectStatFormat();
            }

            int uniIndex = Integer.parseInt(uniIndexString);
            assert uniIndex >= 0 : "University index must be non-negative";

            // Check if the university exists in the repository
            if (UniversityRepository.getUniversityByIndex(uniIndex) == null) {
                throw SEPEmptyException.rejectUniversityNotFound();
            }

            // Process the command based on the stat type
            switch (statType) {
            case "-avggpa":
                double avgGpa = super.studentList.calculateAverageGpaForUniversity(uniIndex);
                assert avgGpa >= 0.0 : "Average GPA must be non-negative";
                ui.printResponse("The average GPA for university index " + uniIndex
                        + " (" + UniversityRepository.getUniversityByIndex(uniIndex).getFullName() + ")" 
                        + " is: " + String.format("%.2f", avgGpa));
                break;
            case "-mingpa":
                double minGpa = super.studentList.calculateMinGpaForUniversity(uniIndex);
                assert minGpa >= 0.0 : "Minimum GPA must be non-negative";
                ui.printResponse("The minimum GPA for university index " + uniIndex 
                        + " (" + UniversityRepository.getUniversityByIndex(uniIndex).getFullName() + ")"
                        + " is: " + String.format("%.2f", minGpa));
                break;
            default:
                throw SEPFormatException.rejectStatFormat();
            }

        } catch (SEPException e) {
            ui.printResponse(e.getMessage());
        }
    }
}
