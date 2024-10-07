package core;

import programme.Day;
import programme.Programme;
import programme.ProgrammeList;

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

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(ProgrammeList programmeList, History history) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            boolean isHistorySection = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if(line.equalsIgnoreCase("HISTORY")) {
                    isHistorySection = true;
                    continue;
                }

                if(isHistorySection) {
                    //Reading in HISTORY (can pull out to loadHistory)
                    loadHistory(line, history);
                } else {
                    // READING IN PROGRAMMELIST (pullout to Load ProgrammeList)
                    loadProgrammeList(line, programmeList);
                    }
                }
            scanner.close();
            System.out.println("Programmes and history have been loaded from: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Oh first time here? Welcome to a life of Fitness, lets start!");
        }
    }

    public void loadHistory(String line, History history) {
        Day day = Day.fromJson(line);
        if (day != null) {
            history.logDay(day);
        }
    }

    public void loadProgrammeList (String line, ProgrammeList programmeList) {
        Programme programme = Programme.fromJson(line);
        if (programme != null) {
            programmeList.addProgramme(programme);
        }
    }


    //SAVE
    public void save(ProgrammeList programmeList, History history) {
        try {
            FileWriter writer = new FileWriter(filePath);
            saveProgrammeList(programmeList);
            writer.write("HISTORY\n");
            saveHistory(history);
            writer.close();
            System.out.println("Saving done, Good Job!");
        } catch (IOException e) {
            System.out.println("An error has occurred when saving data: " + e.getMessage())
        }
    }

    //saveProgrammeList
    public void saveProgrammeList(ProgrammeList programmeList) {
        try {
            FileWriter writer = new FileWriter(filePath); //overwrite the file
            String programmeListJson = programmeList.toJson();
            writer.write(programmeListJson);
            writer.write("\n");
            writer.close();
            System.out.println("Programme List saved in your file");
        } catch (IOException e) {
            System.out.println("An error has occurred when saving Programme List: " + e.getMessage());
        }
    }

    //saveHistory
    public void saveHistory(History history) {
        try {
            FileWriter writer = new FileWriter(filePath, true); //append
            String historyJson = history.toJson();
            writer.write(historyJson);
            writer.write("\n");
            System.out.println("History saved in your file");
        } catch (IOException e) {
            System.out.println("An error has occurred when saving History: " + e.getMessage());
        }
    }
}