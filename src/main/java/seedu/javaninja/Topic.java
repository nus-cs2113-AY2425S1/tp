package seedu.javaninja;

import seedu.javaninja.question.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

/* Represents a programming topic */
public class Topic {
    private String name;
    private List<Question> questions;

    /**
     * Constructs a Topic object with the specified name.
     * Initializes an empty list to store questions.
     *
     * @param name The name of the topic.
     */
    public Topic (String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    /**
     * Adds a question to the topic.
     *
     * @param question The question to be added to the topic.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    /**
     * Retrieves all questions associated with this topic.
     *
     * @return A list of questions for the topic.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Retrieves the name of the topic.
     *
     * @return The name of the topic.
     */
    public String getName() {
        return name;
    }

    /**
     * Selects a specified number of random questions from the topic.
     * Ensures that the number of questions selected does not exceed the total number available.
     *
     * @param numQuestions The number of random questions to select.
     * @return A list of randomly selected questions.
     */
    public List<Question> getRandomQuestions(int numQuestions) {
        // Determine the number of questions to select, ensuring it doesn't exceed the available questions
        int selectionSize = Math.min(numQuestions, questions.size());

        // Create a set to store unique random indices
        Set<Integer> selectedIndices = new HashSet<>();
        Random random = new Random();

        // Select unique random indices until we have the desired number
        while (selectedIndices.size() < selectionSize) {
            int randomIndex = random.nextInt(questions.size());
            selectedIndices.add(randomIndex);
        }

        // Collect questions at the selected indices
        return selectedIndices.stream()
            .map(questions::get)
            .collect(Collectors.toList());
    }
}
