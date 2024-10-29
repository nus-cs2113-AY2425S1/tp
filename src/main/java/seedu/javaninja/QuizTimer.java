package seedu.javaninja;

import java.util.Scanner;

public class QuizTimer {

    private Scanner scanner;

    public QuizTimer(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getTimeLimitInSeconds() {
        System.out.println("Set a time limit for the quiz.");
        System.out.print("Enter the number of minutes (or 0 if you want to set seconds): ");
        int minutes = Integer.parseInt(scanner.nextLine().trim());

        int timeLimitInSeconds = 0;
        if (minutes == 0) {
            System.out.print("Enter the number of seconds: ");
            timeLimitInSeconds = Integer.parseInt(scanner.nextLine().trim());
        } else {
            timeLimitInSeconds = minutes * 60;  // Convert minutes to seconds
        }

        return timeLimitInSeconds;
    }

    public int getQuestionLimit() {
        System.out.print("Enter the number of questions you want to attempt: ");
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
