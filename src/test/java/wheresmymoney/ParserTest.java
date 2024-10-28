package wheresmymoney;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    public void parseLineToArgumentsMap_commandOnly_command() {
        String inputLine = "command";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithLeadingTrailingWhitespace_command() {
        String inputLine = "  command   ";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        System.out.println(argumentsMap);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithMainArgument_arguments() {
        String inputLine = "command main";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithExtraSpaceAndMainArgument_arguments() {
        String inputLine = "command   main";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
    }
}
