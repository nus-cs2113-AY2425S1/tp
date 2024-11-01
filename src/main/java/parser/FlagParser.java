package parser;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

import static common.Utils.isNull;
import static parser.ParserUtils.parseIndex;
import static parser.ParserUtils.parseInteger;
import static parser.ParserUtils.parseFloat;
import static parser.ParserUtils.parseDate;
import static parser.ParserUtils.splitArguments;

/*
    FlagParser simplifies parsing flagged argument strings
    From an argument string, creates a hashmap of flag -> value
    These values can then be retrieved in Integer, Date, String or Index formats
*/
public class FlagParser {
    private static final String DEFAULT_SPLIT_BY = "(?=/)";
    private static final String SPLIT_BY_START = "(?=/(?!(";
    private static final String SPLIT_BY_DELIMITER = "|";
    private static final String SPLIT_BY_END = ")\\b))";

    private final Logger logger = Logger.getLogger(FlagParser.class.getName());

    private final Map<String, String> parsedFlags = new HashMap<>();
    private final Map<String, String> aliasMap = new HashMap<>();

    public FlagParser(String argumentString, String... ignoredFlags) {
        if (isNull(argumentString)){
            throw new IllegalArgumentException("ArgumentString: " + argumentString + " is null");
        }

        initializeAliasMap();
        parse(argumentString, generateSplitBy(ignoredFlags));
    }

    // Generates a regex to parse the argumentString for flags, accounting for any flags/aliases that should be ignored
    private String generateSplitBy(String... ignoredFlags){
        if (ignoredFlags.length == 0){
            return DEFAULT_SPLIT_BY;
        }

        StringBuilder splitBy = new StringBuilder(SPLIT_BY_START);
        for (String ignoredFlag: ignoredFlags) {
            splitBy.append(ignoredFlag.substring(1)).append(SPLIT_BY_DELIMITER);

            for (Map.Entry<String, String> entry: aliasMap.entrySet()){
                String canonicalFlag = entry.getValue();
                String aliasFlag = entry.getKey();
                if (canonicalFlag.equals(ignoredFlag)){
                    splitBy.append(aliasFlag.substring(1)).append(SPLIT_BY_DELIMITER);
                }
            }
        }

        // Trim the last SPLIT_BY_DELIMITER from StringBuilder
        splitBy.setLength(splitBy.length() - 1);

        return splitBy.append(SPLIT_BY_END).toString();
    }

    private void initializeAliasMap() {
        aliasMap.put("/p", "/p");
        aliasMap.put("/programme", "/p");

        aliasMap.put("/day", "/d");
        aliasMap.put("/date", "/t");

        aliasMap.put("/name", "/n");
        aliasMap.put("/exercise", "/e");
        aliasMap.put("/set", "/s");
        aliasMap.put("/rep", "/r");
        aliasMap.put("/weight", "/w");
        aliasMap.put("/calories", "/c");

        aliasMap.put("/createEx", "/a");
        aliasMap.put("/updateEx", "/u");
        aliasMap.put("/removeEx", "/x");
        aliasMap.put("/createDay", "/ad");
        aliasMap.put("/removeDay", "/xd");

        aliasMap.put("/meal", "/m");

        aliasMap.put("/volume", "/v");
        aliasMap.put("/water", "/w");
    }

    private void parse(String argumentString, String splitBy) {
        assert argumentString != null : "Argument string must not be null";

        String[] args = argumentString.split(splitBy);
        for (String arg : args) {

            logger.log(Level.INFO, "Parsing argument: " + arg);

            String[] argParts = splitArguments(arg);

            String flag = argParts[0].trim();
            String value = argParts[1].trim();

            flag = resolveAlias(flag);
            logger.log(Level.INFO, "Parsed flag: {0} with value: {1}", new Object[]{flag, value});
            parsedFlags.put(flag, value);
        }
    }

    private String resolveAlias(String flag) {
        if (aliasMap.containsKey(flag)) {
            return aliasMap.get(flag);
        }
        return flag;
    }

    public boolean hasFlag(String flag) {
        assert flag != null && !flag.isEmpty() : "Flag must not be null or empty";

        flag = resolveAlias(flag);
        boolean hasFlag = parsedFlags.containsKey(flag);

        logger.log(Level.INFO, "Flag {0} presence: {1}", new Object[]{flag, hasFlag});
        return hasFlag;
    }


    public void validateRequiredFlags(String... requiredFlags) {
        assert requiredFlags != null : "Required flags string must not be null";

        for (String flag : requiredFlags) {
            flag = resolveAlias(flag);
            if (!hasFlag(flag)) {
                throw new IllegalArgumentException("Required flag: " + flag + " is missing. Please provide the flag.");
            }
        }
    }

    public String getStringByFlag(String flag) {
        assert flag != null && !flag.isEmpty() : "Flag must not be null or empty";

        flag = resolveAlias(flag);

        if (!parsedFlags.containsKey(flag)) {
            return null;
        }

        String value = parsedFlags.get(flag);
        logger.log(Level.INFO, "Successfully retrieved value for flag {0}: {1}", new Object[]{flag, value});
        return value.trim();
    }

    public int getIndexByFlag(String flag) {
        String indexString = getStringByFlag(flag);
        return parseIndex(indexString);
    }

    public int getIntegerByFlag(String flag){
        String intString = getStringByFlag(flag);
        return parseInteger(intString);
    }

    public float getFloatByFlag(String flag) {
        String floatString = getStringByFlag(flag);
        return parseFloat(floatString);
    }

    public LocalDate getDateByFlag(String flag){
        String dateString = getStringByFlag(flag);
        return parseDate(dateString);
    }
}
