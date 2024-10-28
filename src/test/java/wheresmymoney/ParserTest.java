package wheresmymoney;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    public void parseLineToArgumentsMap_commandOnly_command() {
        String inputLine = "command";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.keySet().size(), 1);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithLeadingTrailingWhitespace_command() {
        String inputLine = "  command   ";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.keySet().size(), 1);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithMainArgument_arguments() {
        String inputLine = "command main";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithExtraSpaceAndMainArgument_arguments() {
        String inputLine = "command   main";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgument_arguments() {
        String inputLine = "command /custom value";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get("custom"), "value");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgumentAndWhitespaceValue_arguments() {
        String inputLine = "command /custom value abc";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get("custom"), "value abc");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithRestrictedArguments_arguments() {
        String inputLine = "command main /command value1 /main value2 /extra extra";
        HashMap<String, String> argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        System.out.println(argumentsMap);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
        assertEquals(argumentsMap.get("extra"), "extra");
    }
}
