package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Flashcard;
import seedu.javaninja.question.TrueFalse;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TopicManager {
    private static final Logger logger = Logger.getLogger(TopicManager.class.getName());
    private static final String QUESTIONS_FILE_PATH = "./data/Questions.txt";
    private static final String FLASHCARDS_FILE_PATH = "./data/Flashcards.txt";
    private static Storage questions;
    private static Storage flashcards;
    private List<Topic> topics;
    private Cli cli;

    /**
     * Constructs a TopicManager object, initializing the topic list, storage paths, and Cli.
     * @param cli The Cli instance used for user interaction.
     */
    public TopicManager(Cli cli) {
        this.topics = new ArrayList<>();
        this.questions = new Storage(QUESTIONS_FILE_PATH);
        this.flashcards = new Storage(FLASHCARDS_FILE_PATH);
        this.cli = cli;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    /**
     * Retrieves an existing topic by name or creates a new one if it doesn't exist.
     * @param topicName The name of the topic to retrieve or create.
     * @return The retrieved or newly created Topic object.
     */
    public Topic getOrCreateTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) {
                return topic;
            }
        }
        Topic newTopic = new Topic(topicName);
        topics.add(newTopic);
        return newTopic;
    }

    /**
     * Retrieves a topic by name.
     * @param topicName The name of the topic to retrieve.
     * @return The Topic object if found; otherwise, null.
     */
    public Topic getTopic(String topicName) {
        for (Topic topic : topics) {
            if (topic.getName().equals(topicName)) { // Case-sensitive comparison
                return topic;
            }
        }
        cli.printMessage("Topic not found. Ensure the topic name matches exactly.");
        return null;
    }

    /**
     * Adds a topic to the list if it doesn't already exist.
     * @param topic The Topic object to add.
     */
    public void addTopic(Topic topic) {
        if (topic == null) {
            logger.warning("Cannot add a null topic.");
            return;
        }
        if (!topics.contains(topic)) {
            topics.add(topic);
            logger.info("Added topic: " + topic.getName());
        } else {
            logger.warning("Topic already exists: " + topic.getName());
        }
    }

    /**
     * Retrieves the names of all topics as a list.
     * @return A list of topic names.
     */
    public List<String> getTopicNames() {
        List<String> topicNames = new ArrayList<>();
        for (Topic topic : topics) {
            topicNames.add(topic.getName());
        }
        return topicNames;
    }

    /**
     * Adds a flashcard question from user input and appends it to the flashcards topic.
     * @param input The input string containing the flashcard command and details.
     */
    public void addFlashcardByUser(String input) {
        // Validate the command starts correctly and contains the necessary flags
        if (!input.startsWith("add Flashcards") || !input.contains("/q") || !input.contains("/a")) {
            cli.printMessage("Invalid command format. Use: add Flashcards /q [question] /a [answer]");
            return;
        }

        // Check for unexpected text or extra flags
        String commandPart = input.substring(0, input.indexOf("/q")).trim();
        if (!commandPart.equals("add Flashcards")) {
            cli.printMessage("Invalid command format. Use only 'add Flashcards /q [question] /a [answer]'.");
            return;
        }

        String[] questionSplit = input.split("/q", 2);
        if (questionSplit.length < 2 || questionSplit[1].isEmpty()) {
            cli.printMessage("Invalid command format. Please provide a question after '/q'.");
            return;
        }

        String[] answerSplit = questionSplit[1].split("/a", 2);
        if (answerSplit.length < 2 || answerSplit[0].trim().isEmpty() || answerSplit[1].trim().isEmpty()) {
            cli.printMessage("Invalid command format. Please provide both question and answer.");
            return;
        }

        String questionText = answerSplit[0].trim();
        String correctAnswer = answerSplit[1].trim();

        if (questionText.contains("/") || correctAnswer.contains("/")) {
            cli.printMessage("Invalid command format. Unexpected flag detected in question or answer.");
            return;
        }

        // Add the flashcard to the "Flashcards" topic
        Topic topic = getOrCreateTopic("Flashcards");
        topic.addQuestion(new Flashcard(questionText, correctAnswer));

        cli.printMessage("Added flashcard: " + "Q: " + questionText + " A: " + correctAnswer);
    }

    /**
     * Checks if flashcards are saved and attempts to save them if necessary.
     * @return True if flashcards are saved successfully; false otherwise.
     */
    public boolean isFlashcardsSaved () {
        Topic flashcardsTopic = getTopic("Flashcards");

        if (flashcardsTopic == null) {
            logger.warning("No 'flashcards' topic found to save.");
            return false;
        }

        List<String> flashcardLines = new ArrayList<>();

        for (Question question : flashcardsTopic.getQuestions()) {
            if (question instanceof Flashcard) {
                Flashcard flashcard = (Flashcard) question;

                String questionLine = String.format("Flashcards | Fc | %s | %s",
                    flashcard.getText(),
                    flashcard.getCorrectAnswer());

                flashcardLines.add(questionLine);
            }
        }

        try {
            questions.saveToFile(FLASHCARDS_FILE_PATH, flashcardLines, false);
            logger.info("All flashcards saved successfully");
            return true;
        } catch (IOException e) {
            logger.warning("Failed to save flashcards");
            return false;
        }

    }

    /**
     * Loads questions from the storage file and parses them into topics.
     */
    public void loadQuestions() {
        try {
            List<String> questionData = questions.loadData();
            for (String question : questionData) {
                if (!question.trim().isEmpty()) {
                    parseTopic(question);
                }
            }
        } catch (IOException e) {
            logger.warning("No questions found.");
        }
    }

    /**
     * Loads flashcards from the storage file and parses them into topics.
     */
    public void loadFlashcards() {
        try {
            List<String> flashcardData = flashcards.loadData();
            for (String flashcard : flashcardData) {
                if (!flashcard.trim().isEmpty()) {
                    parseTopic(flashcard);
                }
            }
        } catch (IOException e) {
            logger.warning("No questions found.");
        }

    }

    /**
     * Parses a line of data to create and add a question to the corresponding topic.
     * @param line The line of data representing a question in a specific format.
     */
    public void parseTopic(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 4) {
            logger.warning("Invalid line format: " + line);
            return;
        }

        String topicName = parts[0].trim();
        String questionType = parts[1].trim();
        String questionText = parts[2].trim();
        String correctAnswer = parts[3].trim();

        Topic topic = getOrCreateTopic(topicName);

        switch (questionType) {
        case "Mcq":
            List<String> options = new ArrayList<>();
            for (int i = 4; i < parts.length; i++) {
                options.add(parts[i].trim());
            }
            topic.addQuestion(new Mcq(questionText, correctAnswer, options));
            break;
        case "TrueFalse":
            boolean correctAnswerBoolean = Boolean.parseBoolean(correctAnswer);
            topic.addQuestion(new TrueFalse(questionText, correctAnswerBoolean));
            break;
        case "Fc":
            topic.addQuestion(new Flashcard(questionText, correctAnswer));
            break;
        case "FillInTheBlank":
            topic.addQuestion(new FillInTheBlank(questionText, correctAnswer));
            break;
        default:
            logger.warning("Invalid question type: " + questionType);
        }
    }
}
