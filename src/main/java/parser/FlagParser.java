package parser;

import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FlagParser {
    private static final Logger logger = Logger.getLogger(FlagParser.class.getName());

    private final Map<String, String> parsedFlags = new HashMap<>();

    public FlagParser(String argumentString) {
        if (argumentString != null && !argumentString.trim().isEmpty()) {
            parse(argumentString);
        }
    }

    private void parse(String argumentString) {
        assert argumentString != null : "Argument string must not be null";

        String[] args = argumentString.trim().split(" (?=/)");
        for (String arg : args) {
            String[] argParts = arg.split(" ", 2);
            String flag = argParts[0].trim();
            String value = "";

            if (argParts.length > 1) {
                value = argParts[1].trim();
            }

            if (value.isEmpty()) {
                throw new IllegalArgumentException("Argument " + flag + " is empty. Please provide a valid value.");
            } else {
                logger.log(Level.INFO, "Parsed flag: {0} with value: {1}", new Object[]{flag, value});
                parsedFlags.put(flag, value);
            }
        }
    }

    public String getFlagValue(String flag) {
        assert flag != null && !flag.isEmpty() : "Flag must not be null or empty";

        String value = parsedFlags.get(flag);

        logger.log(Level.INFO, "Successfully retrieved value for flag {0}: {1}", new Object[]{flag, value});
        return value;
    }

    public boolean hasFlag(String flag) {
        assert flag != null && !flag.isEmpty() : "Flag must not be null or empty";

        boolean hasFlag = parsedFlags.containsKey(flag);

        logger.log(Level.INFO, "Flag {0} presence: {1}", new Object[]{flag, hasFlag});
        return hasFlag;
    }
}
