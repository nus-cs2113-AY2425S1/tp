package seedu.exchangecoursemapper.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.exchangecoursemapper.constants.Logs.INITIALISE_FILE;
import static seedu.exchangecoursemapper.constants.Logs.INITIALISE_FILE_FAIL;
import static seedu.exchangecoursemapper.constants.Logs.LINES_SIZE;
import static seedu.exchangecoursemapper.constants.Logs.LINE_READ_FAIL;
import static seedu.exchangecoursemapper.constants.Logs.SAVE_LINE_SIZE;
import static seedu.exchangecoursemapper.constants.Logs.LINE_WRITE_FAIL;
import static seedu.exchangecoursemapper.constants.Logs.APPEND_LINE;
import static seedu.exchangecoursemapper.constants.Logs.APPEND_LINE_FAIL;

public class FileHandler {
    private final String filePath;
    private static final Logger logger = Logger.getLogger(FileHandler.class.getName());

    public FileHandler(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }

    private void initializeFile() {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                logger.log(Level.INFO, INITIALISE_FILE, filePath);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, INITIALISE_FILE_FAIL, e);
        }
    }

    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            logger.log(Level.INFO, LINES_SIZE, lines.size());
        } catch (IOException e) {
            logger.log(Level.SEVERE, LINE_READ_FAIL, e);
        }
        return lines;
    }

    public void writeAllLines(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            logger.log(Level.INFO, SAVE_LINE_SIZE, lines.size());
        } catch (IOException e) {
            logger.log(Level.SEVERE, LINE_WRITE_FAIL, e);
        }
    }

    public void appendLine(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
            logger.log(Level.INFO, APPEND_LINE, line);
        } catch (IOException e) {
            logger.log(Level.SEVERE, APPEND_LINE_FAIL, e);
        }
    }
}
