package seedu.javaninja;

import seedu.javaninja.question.Question;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.TrueFalse;
import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Flashcard;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The `Quiz` class represents a single quiz session for a specified topic.
 * It manages quiz questions, tracks correct answers, and calculates the final score.
 */
public class Quiz {
    private Topic topic;
    private int currentQuestionIndex;
    private int correctAnswers;
    private QuizTimer quizTimer;
    private Cli cli;
    private int questionLimit;
    private boolean isTimed;

    public Quiz(Topic topic, Cli cli) {
        assert topic != null : "Topic must not be null";
        assert !topic.getQuestions().isEmpty() : "Topic must contain at least one question";
        quizTimer = new QuizTimer();
        this.topic = topic;
        this.cli = cli;
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
        questionLimit = 0;
        this.isTimed = false;
    }

    public void start(int timeLimitInSeconds, int questionLimit) {
        this.questionLimit = questionLimit;
        List<Question> questions = topic.getRandomQuestions(questionLimit);

        AtomicBoolean timeUp = null;
        if (timeLimitInSeconds > 0) {
            isTimed = true;
            timeUp = quizTimer.startTimer(timeLimitInSeconds, this::endQuizEarly);
        } else {
            isTimed = false;
        }

        if (questions.isEmpty()) {
            throw new IllegalStateException("Cannot start a quiz with no questions.");
        }

        while (currentQuestionIndex < questions.size() && currentQuestionIndex < questionLimit) {
            if (isTimed && timeUp.get()) {
                return; // Ensures the loop exits immediately
            }
            Question currentQuestion = questions.get(currentQuestionIndex);
            assert currentQuestion != null : "Current question must not be null";

            cli.printEnclosure();
            cli.printMessage("Questions remaining: " + (questionLimit - currentQuestionIndex));
            cli.printMessage(currentQuestion.getText());

            if (currentQuestion instanceof Mcq) {
                cli.printOptions(currentQuestion.getOptions());
            } else if (currentQuestion instanceof TrueFalse) {
                cli.printMessage("Please answer with 'true' or 'false'.");
            } else if (currentQuestion instanceof FillInTheBlank) {
                cli.printMessage("Please fill in the blank with the correct answer.");
            } else if (currentQuestion instanceof Flashcard) {
                cli.printMessage("Please answer with your flashcard answer.");
            }
            cli.printEnclosure();

            if (isTimed && timeUp.get()) {
                return;
            }

            if (!shouldContinueQuiz(currentQuestion)) {
                break;
            }

            currentQuestionIndex++;
        }

        if (timeLimitInSeconds > 0) {
            quizTimer.cancelTimer();
        }
    }

    private void endQuizEarly() {
        cli.printMessage("\nTime's up! The quiz is ending now.");
        cli.printMessage("Your score is: " + getScore() + "%");
    }

    private boolean shouldContinueQuiz(Question currentQuestion) {
        boolean validInput = false;
        while (!validInput) {
            cli.printMessage("Enter your answer: ");
            String answer = cli.readInput();

            if (isTimed && quizTimer.isTimeUp()) {
                return false;
            }

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

    public int getScore() {
        int totalQuestions = questionLimit;
        return (int) ((double) correctAnswers / totalQuestions * 100);
    }

    public int getQuestionCount() {
        return topic.getQuestions().size();
    }
}
