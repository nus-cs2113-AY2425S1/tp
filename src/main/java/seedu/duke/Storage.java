package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage system for saving and loading tasks.
 * The <code>Storage</code> class handles reading from and writing to the file specified by the user.
 */
public class Storage {
    private String filePath;

    public Storage(String programFilePath, String historyFilePath) {
        this.filePath = filePath;
    }

    public void load(ProgramList programList, History history) {
        programmeListLoad(programList);
        historyLoad(history);

    }

    //load the program list and history
    public ProgramList programmeListLoad(ProgramList programList) {
        //creates a new ProgramList

        try {
            //create a scanner

            //while there is a next line
            //put the line into a string
            //parse the line into a program
            //if programme is not null, add the programme
            //close the scanner
            //print that the programme list is loaded
        } catch (FileNotFoundException e) {
             //new file to be created
        }
        //return the programme list
    }

    //load the history
    public History historyLoad(History history) {
        //creates a new history

        try {
            //create a scanner

            //while there is a next line
            //put the line into a string
            //parse the line into a workout
            //if workout is not null, add the workout into the hsitory
            //close the scanner
            //print that the history is loaded
        } catch (FileNotFoundException e) {
            //new history to be created
        }
        //return the history
    }

    //parseProgramme Method

    //parse workout Method

    //parse exercise Method

    //saveProgrammeList

    //saveHistory
}
