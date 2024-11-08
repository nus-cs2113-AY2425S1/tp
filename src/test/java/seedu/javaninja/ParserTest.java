import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.javaninja.Cli;
import seedu.javaninja.Parser;
import seedu.javaninja.QuizManager;
import seedu.javaninja.Topic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private Parser parser;
    private QuizManager quizManager;
    private Cli cli;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        String simulatedUserInput = "Sample input\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());

        cli = new Cli(inputStream);
        quizManager = new QuizManager(cli);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        parser = new Parser(quizManager, cli);
    }

    @Test
    public void determineCommand_viewCommand_displaysAvailableTopics() throws IOException {
        quizManager.getTopicManager().addTopic(new Topic("Java Basics"));
        quizManager.getTopicManager().addTopic(new Topic("Data Structures"));

        // Simulate "view" command
        parser.determineCommand("view");

        // Check printed output contains topics
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Java Basics"), "Output should contain 'Java Basics' topic.");
        assertTrue(printedOutput.contains("Data Structures"),
                "Output should contain 'Data Structures' topic.");
    }

    @Test
    public void determineCommand_selectCommandWithTopic_verifiesQuizExecution() throws IOException {
        quizManager.getTopicManager().addTopic(new Topic("Java Basics"));

        // Simulate "select Java Basics" command
        parser.determineCommand("select Java Basics");

        // Check printed output for any indication of quiz execution, such as a prompt or message
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Set a time limit for the quiz."),
                "Output should indicate the start of quiz interaction (e.g., prompting for time limit).");
    }

    @Test
    public void determineCommand_selectCommandWithoutTopic_printsErrorMessage() throws IOException {
        // Simulate "select" command without a topic
        parser.determineCommand("select");

        // Check printed output contains an error message
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Please provide a topic to select."),
                "Output should indicate missing topic.");
    }

    @Test
    public void determineCommand_reviewCommand_displaysPastResults() throws IOException {
        // Simulate "review" command
        parser.determineCommand("review");

        // Check printed output contains review message
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Reviewing your past results:"),
                "Output should display review message.");
    }

    @Test
    public void determineCommand_helpCommand_displaysHelpInstructions() throws IOException {
        // Simulate "help" command
        parser.determineCommand("help");

        // Check printed output contains help instructions
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("List of available commands:"),
                "Output should contain help instructions.");
    }

    @Test
    public void determineCommand_addCommand_callsAddInput() throws IOException {
        // Simulate "add Flashcards /q What is Java? /a A programming language" command
        String input = "add Flashcards /q What is Java? /a A programming language";
        parser.determineCommand(input);

        // Verify that the topic "Flashcards" exists and contains the added question
        Topic flashcardsTopic = quizManager.getTopicManager().getTopic("Flashcards");
        assertTrue(flashcardsTopic.getQuestions().stream()
                        .anyMatch(q -> q.getText().equals("What is Java?")),
                "Flashcards topic should contain the added question.");
    }

    @Test
    public void determineCommand_invalidCommand_displaysErrorMessage() throws IOException {
        // Simulate an invalid command
        parser.determineCommand("invalidCommand");

        // Check printed output contains an invalid command message
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Invalid input. Type 'help' for a list of commands."),
                "Output should indicate invalid command.");
    }

    @Test
    public void processCommand_returnsLowerCaseCommand() {
        // Test that processCommand returns the first word in lowercase
        String command = parser.processCommand("SELECT topic");
        assertEquals("select", command, "processCommand should return the first word in lowercase.");
    }
}
