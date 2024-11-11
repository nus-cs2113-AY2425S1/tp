package fittrack.fitnessgoals;

import fittrack.fitnessgoal.AddFitnessGoal;
import fittrack.fitnessgoal.DeleteFitnessGoal;
import fittrack.user.User;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FitnessGoalTest {

    private final String goalDescription = "Run 5km in under 30 minutes";
    private final LocalDateTime testDeadline = LocalDateTime.parse("2024-12-22T23:45:44");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private User testUser; // Declare testUser here

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        testUser = new User("FEMALE", "25"); // Initialize the User instance here
        testUser.clearGoals(); // Ensure goals are cleared before each test

        // Redirect log messages to the same output stream
        Logger logger = Logger.getLogger(DeleteFitnessGoal.class.getName());
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);
    }

    // Test for adding a goal
    @Test
    public void testAddFitnessGoal() {
        AddFitnessGoal addGoal = new AddFitnessGoal(goalDescription, testDeadline);
        addGoal.addGoal(testUser); // Adding the goal to the user

        // Updated expected output, including the deadline
        String expectedOutput = "Goal added: " + goalDescription + " with deadline: "
            + testDeadline.toString() + System.lineSeparator();

        // Compare the expected and actual output
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }


}
