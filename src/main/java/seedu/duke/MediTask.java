package seedu.duke;

/*
import seedu.duke.Command.AddPatientCommand;
import seedu.duke.Command.DelPatientCommand;
import seedu.duke.Command.ListPatientCommand;
import seedu.duke.Command.SelectPatientCommand;
*/

import seedu.duke.data.hospital.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediTask {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        // Initialize patient list
        List<Patient> patients = new ArrayList<>();
    }
}
