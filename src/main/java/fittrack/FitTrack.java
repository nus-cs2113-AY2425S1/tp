package fittrack;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fittrack.parser.Parser;
import fittrack.trainingsession.TrainingSession;
import fittrack.user.User;

import static fittrack.messages.Messages.EXIT_COMMAND;
import static fittrack.storage.Storage.initialiseSaveFile;
import static fittrack.storage.Storage.loadSaveFile;
import static fittrack.ui.Ui.printExitMessage;
import static fittrack.ui.Ui.printGreeting;
import static fittrack.ui.Ui.printUser;

public class FitTrack {
    /**
     * Main entry-point for the FitTrack CLI application.
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<TrainingSession> sessionList = new ArrayList<>();

        initialiseSaveFile();
        loadSaveFile(sessionList);

        printGreeting();
        Scanner scan = new Scanner(System.in);

        String gender = scan.nextLine();
        String age = scan.nextLine();
        User user = new User(gender, age);
        printUser(user);

        String input = scan.nextLine();

        // Until the exit command is entered, execute command then read user input
        while (!input.equals(EXIT_COMMAND)) {
            Parser.parse(user,input,sessionList);
            input = scan.nextLine();
        }

        printExitMessage();
    }
}