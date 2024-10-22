package parser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class IndexParser {
    private static final Logger logger = Logger.getLogger(IndexParser.class.getName());

    public static int parseIndex(String indexString, String errorMessage) {
        assert indexString != null : "Input indexString must not be null";
        assert errorMessage != null : "Input errorMessage must not be null";

        if (indexString.isEmpty()){
            throw new IllegalArgumentException(errorMessage + "Value was not provided.");
        }

        try {
            int index = Integer.parseInt(indexString.trim()) - 1;
            if (index < 0) {
                throw new IllegalArgumentException(errorMessage + "It must be a positive number.");
            }
            logger.log(Level.INFO, "Successfully parsed index: {0}", index);
            return index;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage + "Please provide a valid number.");
        }
    }
}
