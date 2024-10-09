package fittrack.ui;

import fittrack.user.User;

import java.util.List;

import static fittrack.messages.Messages.EXIT_MESSAGE;
import static fittrack.messages.Messages.INIT_SENTENCE;
import static fittrack.messages.Messages.INVALID_INPUT_MESSAGE;
import static fittrack.messages.Messages.LIST_EMPTY_MESSAGE;
import static fittrack.messages.Messages.LIST_MESSAGE;
import static fittrack.messages.Messages.SEPARATOR;

public class Ui {
    /**
     * Prints a line of underscores to mark the start of a print segment.
     */
    public static void beginSegment() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a line of underscores followed by a newline to mark the end of a print segment.
     */
    public static void endSegment() {
        System.out.println(SEPARATOR + System.lineSeparator());
    }

    public static void printGreeting() {
        beginSegment();
        System.out.println(INIT_SENTENCE); // Print the initial greeting
        endSegment();
    }

    public static void printUser(User user) {
        beginSegment();
        System.out.println("You are a " + user.getAge() + " year old "
                + user.getGender().toString().toLowerCase() + ".");
        endSegment();
    }

    /**
     * Prints the list of training sessions.
     *
     * @param sessionList The list of training sessions to print.
     */
    public static void printSessionList(List<TrainingSession> sessionList) {
        beginSegment();
        if (sessionList.isEmpty()) {
            System.out.println(LIST_EMPTY_MESSAGE);
            endSegment();
            return;
        }
        int index = 0;
        System.out.println(LIST_MESSAGE);
        while (index < sessionList.size()) {
            System.out.print(index + 1 + ". ");
            sessionList.get(index).printTrainingSession();
            index++;
        }
        endSegment();
    }

    /**
     * Prints the current number of training sessions in the list.
     *
     * @param sessionList The list of training sessions.
     */
    public static void printSessionCount(List<TrainingSession> sessionList) {
        System.out.println("Now you have " + sessionList.size() + " sessions in the list.");
    }

    /**
     * Prints the message for unrecognized input.
     */
    public static void printUnrecognizedInputMessage() {
        beginSegment();
        System.out.println(INVALID_INPUT_MESSAGE); // Print the unrecognized input message
        endSegment();
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        beginSegment();
        System.out.println(EXIT_MESSAGE); // Print the exit message
        endSegment();
    }
}