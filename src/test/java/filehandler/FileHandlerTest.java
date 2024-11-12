package filehandler;

import exceptions.SEPEmptyException;
import exceptions.SEPException;
import exceptions.SEPUnknownException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.Parser;
import student.Student;
import studentlist.StudentList;
import ui.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHandlerTest {
    private static final Path CSV_FILE_PATH = Paths.get("test.csv");
    private static final Path JSON_FILE_PATH = Paths.get("test.json");
    private static final Path TXT_FILE_PATH = Paths.get("test.txt");

    private FileHandler fileHandler;
    private Parser parser;
    private StudentList studentList;
    private UI ui;
    private ArrayList<Student> results;

    @BeforeEach
    void setUp() throws SEPException, IOException {
        this.ui = new UI();
        this.studentList = new StudentList(ui);
        this.parser = new Parser(studentList, ui);
        this.fileHandler = new FileHandler("test.csv", parser);
        this.fileHandler.hasProcessFileSuccessfully();
        results = this.studentList.getList();
    }

    @Test
    void testCSVFileProcessing() throws IOException, SEPException {
        fileHandler = new FileHandler(CSV_FILE_PATH.toString(), parser);

        boolean result = fileHandler.hasProcessFileSuccessfully();

        assertTrue(result, "Expected CSV file to be processed successfully.");
    }

    @Test
    void testJSONFileProcessing() throws IOException, SEPException {
        fileHandler = new FileHandler(JSON_FILE_PATH.toString(), parser);

        boolean result = fileHandler.hasProcessFileSuccessfully();

        assertTrue(result, "Expected JSON file to be processed successfully.");
    }

    @Test
    void testTXTFileProcessing() throws IOException, SEPException {
        fileHandler = new FileHandler(TXT_FILE_PATH.toString(), parser);

        boolean result = fileHandler.hasProcessFileSuccessfully();

        assertTrue(result, "Expected TXT file to be processed successfully.");
    }

    @Test
    void hasProcessFileSuccessfully_missingFile_fileNotFound() throws IOException {
        fileHandler = new FileHandler("nonexistent.glb", parser);

        SEPException exception = assertThrows(SEPEmptyException.class, fileHandler::hasProcessFileSuccessfully);
        assertEquals("This file don't exist...Don't make me go through your folder for nothing leh :(.\n" +
                "Process Outcome: No data is loaded. You can continue using the program.", exception.getMessage());
    }

    @Test
    void hasProcessFileSuccessfully_blankFile_emptyFileDetected() throws IOException, SEPException {
        File emptyFile = Files.createTempFile("empty", ".csv").toFile();
        emptyFile.deleteOnExit();

        fileHandler = new FileHandler(emptyFile.getAbsolutePath(), parser);

        SEPException exception = assertThrows(SEPEmptyException.class, fileHandler::hasProcessFileSuccessfully);
        assertEquals("Nothing to read from the file. Please ensure there is content next time.",
                exception.getMessage());
    }

    @Test
    void hasProcessFileSuccessfully_invalidFileType_unknownFileDetected() throws IOException {
        fileHandler = new FileHandler("test.xml", parser);

        SEPException exception = assertThrows(SEPUnknownException.class, fileHandler::hasProcessFileSuccessfully);
        assertEquals("Unknown/Unsupported file type detected! " +
                "\nYou can continue to use the program.", exception.getMessage());
    }


    @Test
    void saveAllocationResults_saveInCSV_resultsSavedSuccessfully() throws IOException, SEPException {
        Path p = Paths.get("data/allocation_results.csv");
        fileHandler.saveAllocationResults(this.results, "csv");
        assertTrue(Files.exists(p));
        assertTrue(Files.size(p) != 0);
    }


    @Test
    void saveAllocationResults_saveInJSON_resultsSavedSuccessfully() throws IOException {
        Path p = Paths.get("data/allocation_results.json");
        this.fileHandler.saveAllocationResults(this.results, "json");
        assertTrue(Files.exists(p));
        assertTrue(Files.size(p) != 0);
    }


    @Test
    void saveAllocationResults_saveInTXT_resultsSaveSuccessfully() throws IOException {
        Path p = Paths.get("data/allocation_results.txt");
        this.fileHandler.saveAllocationResults(this.results, "txt");
        assertTrue(Files.exists(p));
        assertTrue(Files.size(p) != 0);
    }
}
