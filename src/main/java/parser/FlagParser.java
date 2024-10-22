package parser;

import java.util.HashMap;
import java.util.Map;

public class FlagParser {
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
                parsedFlags.put(flag, value);
            }
        }
    }

    public String getFlagValue(String flag) {
        return parsedFlags.get(flag);
    }

    public boolean hasFlag(String flag) {
        return parsedFlags.containsKey(flag);
    }
}
