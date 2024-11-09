package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.Question;
import seedu.javaninja.question.TrueFalse;

import java.util.List;

/**
 * The `Quiz` class represents a single quiz session for a specified topic.
 * It manages quiz questions, tracks correct answers, and calculates the final score.
 */
public class Quiz {
    private Topic topic;                  // The topic of the quiz
    private int currentQuestionIndex;     // The index of the current question
    private int correctAnswers;           // Number of correct answers
    private QuizTimer quizTimer;          // Timer to track the quiz's time limit
    private Cli cli;                      // CLI instance for user interaction
    private int questionLimit;            // Limit on the number of questions in the quiz

    /**
     * Constructs a new `Quiz` instance with the specified topic and CLI instance.
     *
     * @param topic The topic for the quiz.
     * @param cli   The CLI instance to handle user input and output.
     * @throws IllegalArgumentException if the topic is null or contains no questions.
     */
    public Quiz(Topic topic, Cli cli) {
        assert topic != null : "Topic must not be null";
        assert !topic.getQuestions().isEmpty() : "Topic must contain at least one question";
        quizTimer = new QuizTimer();
        this.topic = topic;
        this.cli = cli;
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
        questionLimit = 0;
    }

    /**
     * Starts the quiz session by setting the time limit and question limit, and begins prompting the user with questions.
     *
     * @param timeLimitInSeconds The time limit for the quiz in seconds.
     * @param questionLimit      The number of questions to attempt in the quiz.
     * @throws IllegalStateException if the quiz has no questions to start.
     */
    public void start(int timeLimitInSeconds, int questionLimit) {
        this.questionLimit = questionLimit;
        List<Question> questions = topic.getRandomQuestions(questionLimit);
        quizTimer.startTimer(timeLimitInSeconds);

        if (questions.isEmpty()) {
            throw new IllegalStateException("Cannot start a quiz with no questions.");
        }

        while (currentQuestionIndex < questions.size() && currentQuestionIndex < questionLimit) {
            if (quizTimer.isTimeUp()) {
                break;
            }
            Question currentQuestion = questions.get(currentQuestionIndex);
            assert currentQuestion != null : "Current question must not be null";

            cli.printEnclosure();
            cli.printMessage(currentQuestion.getText());

            if (currentQuestion instanceof Mcq) {
                cli.printOptions(currentQuestion.getOptions());
            } else if (currentQuestion instanceof TrueFalse) {
                cli.printMessage("Please answer with 'true' or 'false'.");
            } else if (currentQuestion instanceof FillInTheBlank) {
                cli.printMessage("Please fill in the blank with the correct answer.");
            }
            cli.printEnclosure();

            if (!shouldContinueQuiz(currentQuestion)) {
                break;
            }

            currentQuestionIndex++;
        }

        quizTimer.cancelTimer();
        cli.printMessage("Quiz finished. Your score is: " + getScore() + "%");
    }

    /**
     * Prompts the user to answer a question and checks if the quiz should continue.
     *
     * @param currentQuestion The current question being answered.
     * @return `true` if the quiz should continue, `false` if it should end.
     */
    private boolean shouldContinueQuiz(Question currentQuestion) {
        boolean validInput = false;
        while (!validInput) {
            cli.printMessage("Enter your answer: ");
            String answer = cli.readInput();

            if (quizTimer.isTimeUp()) {
                break;
            }

            // Check if the user wants to exit the quiz
            if (answer.equalsIgnoreCase("exit")) {
                cli.printMessage("Exiting the quiz. Returning to main menu...");
                return false;
            }

            try {
                answerQuestion(answer, currentQuestion);
                validInput = true;
            } catch (IllegalArgumentException e) {
                cli.printMessage(e.getMessage());
            }
        }
        return true;
    }

    /**
     * Evaluates the user's answer to a question and provides feedback.
     *
     * @param answer          The answer provided by the user.
     * @param currentQuestion The current question to be answered.
     */
    public void answerQuestion(String answer, Question currentQuestion) {
        assert currentQuestionIndex < topic.getQuestions().size() : "Question index out of bounds";
        assert answer != null && !answer.trim().isEmpty() : "Answer must not be null or empty";

        if (currentQuestion.checkAnswer(answer)) {
            cli.printMessage("Correct!");
            correctAnswers++;
        } else {
            cli.printMessage("Incorrect!");
            cli.printMessage("The correct answer is: " + currentQuestion.getCorrectAnswer());
        }
    }

    /**
     * Calculates and returns the user's score as a percentage.
     *
     * @return The percentage of correct answers in the quiz.
     */
    public int getScore() {
        int totalQuestions = questionLimit;
        return (int) ((double) correctAnswers / totalQuestions * 100);
    }

    /**
     * Returns the total number of questions available in the topic.
     *
     * @return The total question count in the topic.
     */
    public int getQuestionCount() {
        return topic.getQuestions().size();
    }
}
