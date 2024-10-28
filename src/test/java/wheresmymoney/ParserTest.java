package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    @Test
    public void parseLineToArgumentsMap_commandOnly_command() {
        String inputLine = "command";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 1);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithLeadingTrailingWhitespace_command() {
        String inputLine = "  command   ";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 1);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithMainArgument_arguments() {
        String inputLine = "command main";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithExtraSpaceAndMainArgument_arguments() {
        String inputLine = "command   main";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgument_arguments() {
        String inputLine = "command /custom value";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get("custom"), "value");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgumentAndWhitespaceValue_arguments() {
        String inputLine = "command /custom value abc";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get("custom"), "value abc");
    }

    @Test
    public void parseLineToArgumentsMap_commandWithRestrictedArguments_arguments() {
        String inputLine = "command main /command value1 /main value2 /extra extra";
        assertThrows(InvalidInputException.class,
                ()->Parser.parseLineToArgumentsMap(inputLine));
    }

    @Test
    public void parseLineToArgumentsMap_commandWithDuplicateArguments_chooseFirstArgument()
            throws WheresMyMoneyException {
        String inputLine = "command main /extra extra1 /extra extra2";
        assertThrows(InvalidInputException.class,
                ()->Parser.parseLineToArgumentsMap(inputLine));
    }
}
