package seedu.javaninja;

import seedu.javaninja.question.Question;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.ArrayList;

public class QuizGenerator {
    private final Cli cli;
    private final TopicManager topicManager;
    private int questionLimit;
    private int timeLimitInSeconds;
    private Topic topic;
    private Quiz currentQuiz;

    public QuizGenerator(TopicManager topicManager, Cli cli) {
        this.topicManager = topicManager;
        this.cli = cli;
        this.questionLimit = 0;
        this.timeLimitInSeconds = 0;
        this.topic = null;
        this.currentQuiz = null;
    }

    /**
     * Returns the current Quiz instance.
     * @return the current quiz.
     */
    public Quiz getQuiz() {
        return currentQuiz;
    }

    public boolean selectTimedQuiz(String topicName) {
        topic = topicManager.getTopic(topicName);
        if (topic == null) {
            cli.printMessage("Topic not found: " + topicName);
            return false;
        }

        currentQuiz = new Quiz(topic, cli);
        questionLimit = getQuestionLimitFromUser(topic);
        timeLimitInSeconds = getTimeLimitInSecondsFromUser();

        currentQuiz.start(timeLimitInSeconds, questionLimit);
        return true;
    }

    public boolean selectUntimedQuiz(String topicName) {
        topic = topicManager.getTopic(topicName);
        if (topic == null) {
            cli.printMessage("Topic not found: " + topicName);
            return false;
        }

        timeLimitInSeconds = -1;
        questionLimit = getQuestionLimitFromUser(topic);
        currentQuiz = new Quiz(topic, cli);

        currentQuiz.start(timeLimitInSeconds, questionLimit);
        return true;
    }

    public boolean selectRandomTopicsQuiz(boolean isTimed) {
        int numTopics = getNumTopicsFromUser();
        int numQuestions = getNumQuestionsFromUser();
        questionLimit = numQuestions * numTopics;

        topic = generateRandomQuiz(numTopics, numQuestions);
        currentQuiz = new Quiz(topic, cli);

        if (isTimed) {
            timeLimitInSeconds = getTimeLimitInSecondsFromUser();
            currentQuiz.start(timeLimitInSeconds, questionLimit);
        } else {
            timeLimitInSeconds = -1;
            currentQuiz.start(timeLimitInSeconds, questionLimit);
        }
        return true;
    }

    private Topic generateRandomQuiz(int numTopics, int numQuestions) {
        List<Topic> allTopics = topicManager.getTopics();

        if (numTopics > allTopics.size()) {
            numTopics = allTopics.size();
        }

        Collections.shuffle(allTopics);
        List<Topic> selectedTopics = allTopics.stream()
                .limit(numTopics)
                .toList();

        List<Question> limitedQuestions = selectedTopics.stream()
                .flatMap(topic -> {
                    List<Question> shuffledQuestions = new ArrayList<>(topic.getQuestions());
                    Collections.shuffle(shuffledQuestions);
                    return shuffledQuestions.stream().limit(numQuestions);
                })
                .collect(Collectors.toList());

        Topic randomQuiz = new Topic("Random Quiz");
        for (Question question : limitedQuestions) {
            randomQuiz.addQuestion(question);
        }

        return randomQuiz;
    }


    private int getQuestionLimitFromUser(Topic topic) {
        int maxQuestions = topic.getQuestions().size();
        int upperLimit = 1000;

        while (true) {
            cli.printMessage("Enter the number of questions you want to attempt (Max " + maxQuestions + "): ");
            try {
                int questionLimit = Integer.parseInt(cli.readInput().trim());

                if (questionLimit > 0 && questionLimit <= maxQuestions) {
                    return questionLimit;
                } else if (questionLimit > upperLimit) {
                    cli.printMessage("The number entered is too large. Please enter a realistic number below "
                            + upperLimit + ".");
                } else {
                    cli.printMessage("Invalid number. Please enter a number between 1 and " + maxQuestions + ".");
                }
            } catch (NumberFormatException e) {
                cli.printMessage("Invalid input. Please enter a valid number.");
            }
        }
    }

    public int getTimeLimitInSecondsFromUser() {
        while (true) {
            cli.printMessage("Enter the number of minutes for the quiz (or 0 for seconds): ");
            try {
                int minutes = Integer.parseInt(cli.readInput().trim());
                if (minutes >= 0) {
                    if (minutes == 0) {
                        cli.printMessage("Enter the number of seconds: ");
                        int seconds = Integer.parseInt(cli.readInput().trim());
                        if (seconds > 0) {
                            return seconds;
                        }
                    } else {
                        return minutes * 60;
                    }
                }
                cli.printMessage("Invalid input. Time must be a positive number.");
            } catch (NumberFormatException e) {
                cli.printMessage("Invalid input. Please enter a valid number.");
            }
        }
    }

    private int getNumTopicsFromUser() {
        while (true) {
            cli.printMessage("Enter the number of topics to include in the random quiz: ");
            try {
                int numTopics = Integer.parseInt(cli.readInput().trim());
                if (numTopics > 0) {
                    return numTopics;
                }
                cli.printMessage("Invalid input. Number of topics must be greater than zero.");
            } catch (NumberFormatException e) {
                cli.printMessage("Invalid input. Please enter a valid number.");
            }
        }
    }

    private int getNumQuestionsFromUser() {
        while (true) {
            cli.printMessage("Enter the number of questions per topic for the random quiz: ");
            try {
                int numQuestions = Integer.parseInt(cli.readInput().trim());
                if (numQuestions > 0) {
                    return numQuestions;
                }
                cli.printMessage("Invalid input. Number of questions must be greater than zero.");
            } catch (NumberFormatException e) {
                cli.printMessage("Invalid input. Please enter a valid number.");
            }
        }
    }

    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    public int getQuizScore() {
        return currentQuiz.getScore();
    }

    public String getTopicName() {
        return topic.getName();
    }

    public int getQuestionLimit() {
        return questionLimit;
    }
}
