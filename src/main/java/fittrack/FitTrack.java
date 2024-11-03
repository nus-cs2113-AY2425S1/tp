package fittrack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fittrack.parser.Parser;
import fittrack.reminder.Reminder;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;

import static fittrack.exception.ParserExceptions.parseUserInfo;
import static fittrack.exception.ParserExceptions.validUser;
import static fittrack.logger.FitTrackLogger.setupLogger;
import static fittrack.messages.Messages.EXIT_COMMAND;
import static fittrack.storage.Storage.initialiseSaveFile;
import static fittrack.storage.Storage.loadSaveFile;
import static fittrack.ui.Ui.printExitMessage;
import static fittrack.ui.Ui.printGreeting;
import static fittrack.ui.Ui.printHelp;
import static fittrack.ui.Ui.printUpcomingReminders;
import static fittrack.ui.Ui.printUser;
import fittrack.fitnessgoal.Goal;

public class FitTrack {
    /**
     * Main entry-point for the FitTrack CLI application.
     */
    public static void main(String[] args) throws FileNotFoundException {
        setupLogger();

        // Initialize scanner and session / reminder list
        Scanner scan = new Scanner(System.in);
        ArrayList<TrainingSession> sessionList = new ArrayList<>();
        ArrayList<Reminder> reminderList = new ArrayList<>();
        ArrayList<Goal> goalList = new ArrayList<>();


        // Initialize and load the save file
        try {
            initialiseSaveFile();
            loadSaveFile(sessionList);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        printGreeting();

        // Set user gender and age
        User user = null;
        while(user == null) {
            try {
                String userInput = scan.nextLine();
                String[] userInfo = parseUserInfo(userInput);
                user = validUser(userInfo[0], userInfo[1]);
                printUser(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        printHelp();

        // Inform users of upcoming reminders
        printUpcomingReminders(reminderList);

        String input = scan.nextLine();

        // Until the exit command is entered, execute command then read user input
        while (!input.trim().equals(EXIT_COMMAND)) {
            Parser.parse(user, input, sessionList, reminderList, goalList);
            input = scan.nextLine();
        }

        printExitMessage();
    }
}
