package wheresmymoney;

import org.junit.jupiter.api.Test;
import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;
import wheresmymoney.utils.Parser;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    /**
     * Tests the parsing of a command into an arguments map.
     */
    @Test
    public void parseLineToArgumentsMap_commandOnly_command() {
        String inputLine = "command";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "");
    }

    /**
     * Tests the parsing of a command with leading and trailing spaces into an arguments map.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithLeadingTrailingWhitespace_command() {
        String inputLine = "  command   ";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "");
    }

    /**
     * Tests the parsing of a command with a leading forward slash into an arguments map.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithLeadingForwardSlash_command() {
        String inputLine = "/command   ";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assert(argumentsMap.containsKey(Parser.ARGUMENT_COMMAND));
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "/command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "");
    }

    /**
     * Tests the parsing of a line with a main argument into an arguments map.
     */
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

    /**
     * Tests the parsing of a line with a main argument into an arguments map.
     * Extra spaces between the command and main argument should be ignored.
     * Spaces within the argument value should be counted.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithExtraSpaceAndMainArgument_arguments() {
        String inputLine = "command   main  value";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 2);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "main  value");
    }

    /**
     * Tests the parsing of a line with a custom argument into an arguments map.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgument_arguments() {
        String inputLine = "command /custom value";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 3);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "");
        assertEquals(argumentsMap.get("custom"), "value");
    }

    /**
     * Tests the parsing of a line with a custom argument into an arguments map.
     * The value of the argument is split among various strings.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgumentAndWhitespaceValue_arguments() {
        String inputLine = "command /custom value abc";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 3);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "");
        assertEquals(argumentsMap.get("custom"), "value abc");
    }

    /**
     * Tests the parsing of a line with a custom argument into an arguments map.
     * The value of the argument is split among various strings.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithCustomArgumentAndMultipleWhitespaceValue_arguments() {
        String inputLine = "command /custom value  abc";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 3);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get(Parser.ARGUMENT_MAIN), "");
        assertEquals(argumentsMap.get("custom"), "value  abc");
    }

    /**
     * Tests the parsing of a line with various custom arguments into an arguments map.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithRestrictedArguments_arguments() {
        String inputLine = "command main /command value1 /index value2 /extra extra";
        assertThrows(InvalidInputException.class,
                ()->Parser.parseLineToArgumentsMap(inputLine));
    }

    /**
     * Tests the parsing of a line with duplicate arguments. This should be considered invalid input.
     */
    @Test
    public void parseLineToArgumentsMap_commandWithDuplicateArguments_invalidInputException() {
        String inputLine = "command main /extra extra1 /extra extra2";
        assertThrows(InvalidInputException.class,
                ()->Parser.parseLineToArgumentsMap(inputLine));
    }

    /**
     * Tests the parsing of a line with arguments that do not have values. The arguments should still be mapped.
     */
    @Test
    public void parseLineToArgumentsMap_commandArgumentWithoutValue_parseBoth() {
        String inputLine = "command main /extra /extra2";
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap(inputLine);
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
        assertEquals(argumentsMap.keySet().size(), 4);
        assertEquals(argumentsMap.get(Parser.ARGUMENT_COMMAND), "command");
        assertEquals(argumentsMap.get("extra"), "");
        assertEquals(argumentsMap.get("extra2"), "");
    }

    /**
     * Tests the parsing of a line with various forward slash escapes.
     * The escape sequences should be replaced accordingly.
     */
    @Test
    public void parseLineToArgumentsMap_commandArgumentWithForwardSlashHandling_forwardSlashHandled() {
        HashMap<String, String> argumentsMap = null;
        try {
            argumentsMap = Parser.parseLineToArgumentsMap("command /argument /value");
            assertEquals(argumentsMap.get("argument"), "");
            assertEquals(argumentsMap.get("value"), "");

            argumentsMap = Parser.parseLineToArgumentsMap("command /argument \\/value");
            assertEquals(argumentsMap.get("argument"), "/value");

            argumentsMap = Parser.parseLineToArgumentsMap("command /argument value\\/value");
            assertEquals(argumentsMap.get("argument"), "value/value");

            argumentsMap = Parser.parseLineToArgumentsMap("command /argument value/value");
            assertEquals(argumentsMap.get("argument"), "value/value");

            argumentsMap = Parser.parseLineToArgumentsMap("command /argument/param value");
            assertEquals(argumentsMap.get("argument/param"), "value");
        } catch (WheresMyMoneyException e) {
            assert(false);
        }
    }
}
