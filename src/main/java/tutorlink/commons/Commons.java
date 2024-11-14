package tutorlink.commons;

public class Commons {

    public static final String ERROR_NULL = "Error! Null parameters %s passed!";

    //@@author RCPilot1604

    //Input Validation
    public static final String MATRIC_NUMBER_REGEX = "A\\d{7}[A-Z]";
    public static final String ERROR_ILLEGAL_MATRIC_NUMBER = "Error! Matric Number should start with \"A\", " +
            "followed by 7 digits, and end with an uppercase letter (e.g., A1234567X)";
    //Student
    public static final String ADD_STUDENT_SUCCESS = "Student %s (%s) added successfully!";
    public static final String ERROR_DUPLICATE_STUDENT =
            "Error! There is more than 1 student with the Matric Number, %s!";
    public static final String ERROR_STUDENT_BOTH_NULL = "Error! Both parameters passed are null!";
    public static final String DELETE_STUDENT_SUCCESS = "Student %s successfully deleted";
    public static final String LIST_STUDENT_SUCCESS = "Here are your students:";

    //@@author yeekian
    //Grade
    public static final String DELETE_GRADE_SUCCESS = "Grade: Component %s for student %s successfully deleted";
    public static final String ADD_GRADE_SUCCESS = "Score of %s added successfully to %s for %s!";
    public static final String ERROR_INVALID_SCORE =
            "Error! Score must be a numerical value and be between 0 and the max score of the component!";

    //@@author TrungBui32
    //Component
    public static final int MAX_WEIGHT = 100;
    public static final String ADD_COMPONENT_SUCCESS =
            "Component %s of weight %s%%, with max score %s added successfully!";
    public static final String DELETE_COMPONENT_SUCCESS = "Component %s successfully deleted";
    public static final String ERROR_COMPONENT_NOT_FOUND = "Error! Component (Name %s) not found";
    public static final String ERROR_DUPLICATE_COMPONENT = "Error! Component (Name %s) already exists in the list!";
    public static final String ERROR_MULTIPLE_QUERY_RESULT = "Error! Multiple query results for keyword: %s found " +
            "in list!";
    public static final String ERROR_INVALID_WEIGHTAGE = "Error! Weightage must be integer that is between 0 and 100!";
    public static final String ERROR_INVALID_MAX_SCORE =
            "Error! Max Score must be double that is between 0 and 10000!";
    //@@author RCPilot1604
    public static final String ERROR_INVALID_TOTAL_WEIGHTING = "Error! Total weighting must not exceed 100%%.\n" +
            "Current weighting (after addition): %s%%";

    //Invalid
    public static final String ERROR_INVALID_COMMAND = "Error! Invalid command given!";

    public static final String ERROR_FILESTORAGE_EXCEPTION = "Error! File storage exception encountered: ";

    //Exit
    public static final String EXIT = "Goodbye! See you soon!";

    //Help
    public static final String HELP_MESSAGE = """
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
            """;
}
