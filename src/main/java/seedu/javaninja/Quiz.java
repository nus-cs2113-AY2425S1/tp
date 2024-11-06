package seedu.javaninja;

import seedu.javaninja.question.FillInTheBlank;
import seedu.javaninja.question.Mcq;
import seedu.javaninja.question.Question;
import seedu.javaninja.question.TrueFalse;

import java.util.List;

public class Quiz {
    private Topic topic;
    private int currentQuestionIndex;
    private int correctAnswers;
    private QuizTimer quizTimer;
    private Cli cli;
    private int questionLimit;

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

    public int getQuestionCount () {
        return topic.getQuestions().size();
    }
}
