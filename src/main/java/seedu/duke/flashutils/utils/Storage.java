package seedu.duke.flashutils.utils;

import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashBook;
import seedu.duke.flashutils.types.FlashCardSet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Storage class is responsible for storing and reading data inputted from Flashbang
 */
public class Storage {
    private final File directory;

    public Storage(String directoryPath) {
        this.directory = new File(directoryPath);
        createDir();
    }

    /**
     * This function creates a directory at the file path of the class instance
     */
    private void createDir() {
        try {
            if(!directory.exists()){
                if(!directory.mkdirs()){
                    throw new IOException("Could not create directory");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function formats lines from the txt file into appropriate strings for flashcards
     * @param line represents a line from the txt file as a String
     * @return a {@code Card} formatted from the file
     */
    private Card cardFormatter(String line) {
        Pattern cardPattern = Pattern.compile(
                "(.+?)\\s*\\|\\s*(.+?)\\s*\\|\\s*(.+?)?");
        Matcher cardMatcher = cardPattern.matcher(line);
        boolean isMatch = cardMatcher.matches();
        assert isMatch: "Text Format Problem in File";
        if (cardMatcher.matches()) {
            String question = cardMatcher.group(1);
            String answer = cardMatcher.group(2);
            String topic = cardMatcher.group(3);
            return new Card(question, answer, topic);
        }
        return null;
    }

    /**
     * Reads the txt file with the matching name as the module
     * @param module represents the name of the module of the {@code FlashCardSet}
     * @param flashCardSetFile represents the file containing the {@code Card} in the set
     * @return a {@code FlashCardSet} read from the file
     * @throws IOException when an input/output error occurs
     */
    private FlashCardSet readFlashCardSetFromFile(String module, File flashCardSetFile) throws IOException {

        ArrayList<Card> cards = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(flashCardSetFile);
            while (scanner.hasNext()) {
                Card card = cardFormatter(scanner.nextLine());
                if (card != null) {
                    cards.add(card);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new IOException("An error occurred while reading from the file.");
        }
        return new FlashCardSet(module,cards);
    }

    private void createFile(File file) {
        try {
            if(!file.exists()){
                if(!file.createNewFile()){
                    throw new IOException("Could not create file");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes the {@code FlashBook} into a txt file
     * @param flashBook represents the {@code FlashBook} instance to be written
     */
    public void writeFlashBookToFile(FlashBook flashBook){
        flashBook.getAllFlashCardSets().forEach((module, flashCardSet)-> {
            File flashCardSetFile = new File(directory, module+".txt");
            createFile(flashCardSetFile);
            try {
                FileWriter fileWriter = new FileWriter(flashCardSetFile.getPath());
                for (Card card : flashCardSet.getFlashCardSet()) {
                    fileWriter.write(card.toWritableString()+"\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file. ");
            }
        });
    }

    /**
     * Reads the all txt files within the directory
     * @return a {@code HashMap<String, FlashCard Set>}. The key is a {@code String} which is the name of the txt file
     *     and the value stored is the {@code FlashCardSet} read from the file.
     * @throws IOException when an input/output error occurs
     */
    public HashMap<String, FlashCardSet> readFlashCardsFromFile() throws IOException {
        HashMap<String, FlashCardSet> flashCard = new HashMap<>();
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files != null && files.length > 0) {
            for (File file : files) {
                String module = file.getName().split("\\.")[0];
                flashCard.put(module,readFlashCardSetFromFile(module, file));
            }
        } else {
            System.out.println("No text files found in the directory.");
        }

        return flashCard;
    }
    public void deleteFlashCardSetFile(String module) throws IOException {
        File[] fileList = directory.listFiles((dir, name) -> name.equals(module + ".txt"));
        assert fileList != null;
        if (fileList[0].exists() && fileList[0].delete()) {
            System.out.println("Successfully deleted file");
        } else {
            throw new IOException();
        }
    }
}


