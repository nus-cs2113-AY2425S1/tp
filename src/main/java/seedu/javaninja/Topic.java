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

    public Topic (String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

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
