package seedu.duke.flashutils.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashBook;
import seedu.duke.flashutils.types.FlashCardSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    private final String directoryPath = "./data";
    private File testFile;
    private Storage storage;

    @BeforeEach
    public void createFile() {
        this.storage = new Storage(directoryPath);
    }

    /**
     * This test is to ensure that files to store data are created, written on in the correct format
     * and read into the FlashCardSets correctly
     */
    @Test
    void writeAndReadFlashCardsTest() {
        FlashBook flashBook = FlashBook.getInstance();
        flashBook.addFlashCardSet("FunModule");
        FlashCardSet testSet = flashBook.getFlashCardSet("FunModule");
        testSet.addCard(new Card("is water wet?", "no?", "wetness"));
        storage.writeFlashBookToFile(flashBook);
        testFile = new File(directoryPath +"/FunModule.txt");
        assertTrue(testFile.exists());
        Scanner scanner;
        try {
            scanner = new Scanner(testFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals("is water wet? | no? | wetness", scanner.nextLine());
        HashMap<String, FlashCardSet> testBook;
        try {
            testBook = storage.readFlashCardsFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FlashCardSet testSet2 = testBook.get("FunModule");
        FlashCardSet actualSet = flashBook.getFlashCardSet("FunModule");
        assertTrue(testSet2.getCard(0).getAnswer().equals(actualSet.getCard(0).getAnswer())
                && testSet2.getCard(0).getQuestion().equals(actualSet.getCard(0).getQuestion()));
        scanner.close();
    }

    private class StubCard extends Card {
        public StubCard(Card card) {
            super(card.getQuestion(), card.getAnswer(), card.getTopic());
        }
        public boolean isEqual(Card card) {
            return this.getQuestion().equals(card.getQuestion())
                    && this.getAnswer().equals(card.getAnswer())
                    && this.getTopic().equals(card.getTopic());
        }
    }
    /**
     * This test is to ensure that multiple cards are written on to the text files and read appropriately using a stub
     */
    @Test
    public void readAndWriteMultipleCards() throws IOException {
        FlashCardSet set1 = new FlashCardSet("CS2113");
        set1.addCard(new Card("question1", "answer1", "topic1"));
        set1.addCard(new Card("question2", "answer2", "topic2"));
        set1.addCard(new Card("question3", "answer3", "topic3"));
        set1.addCard(new Card("question4", "answer4", "topic4"));
        ArrayList<Card> testList = new ArrayList<>(set1.getFlashCardSet());
        FlashBook testBook = FlashBook.getInstance();
        testBook.getAllFlashCardSets().put(set1.getModuleName(), set1);
        storage.writeFlashBookToFile(testBook);
        testFile = new File(directoryPath + "/CS2113.txt");
        assertTrue(testFile.exists());
        HashMap<String, FlashCardSet> actualMap = storage.readFlashCardsFromFile();
        FlashCardSet actualList = actualMap.get("CS2113");
        for (int i = 0; i < testList.size(); i++) {
            StubCard actualCard = new StubCard(actualList.getCard(i));
            StubCard expectedCard = new StubCard(testList.get(i));
            assertTrue(actualCard.isEqual(expectedCard));
        }
    }
    @AfterEach
    public void cleanFile() throws IOException {
        if (Files.deleteIfExists(testFile.toPath())) {
            System.out.println("file deleted successfully");
        } else {
            System.out.println("error");
        }
    }
}
