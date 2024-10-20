package seedu.exchangecoursemapper.command;

import seedu.exchangecoursemapper.constants.Logs;
import seedu.exchangecoursemapper.exception.Exception;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Command {
    private static final Logger logger = Logger.getLogger(Command.class.getName());
    public static final String FILE_PATH = "/database.json";
    public abstract void execute(String userInput);

    public JsonObject createJsonObject() throws IOException {
        logger.log(Level.INFO, Logs.EXECUTING_COMMAND);
        InputStream inputStream = getClass().getResourceAsStream(FILE_PATH);
        if (inputStream == null) {
            throw new IOException();
        }
        JsonReader jsonReader = Json.createReader(inputStream);
        return jsonReader.readObject();
    }
}
