// @@author nirala-ts

package parser;

import command.Command;
import command.InvalidCommand;
import command.ExitCommand;
import exceptions.ParserExceptions;
import parser.command.factory.CommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class ParserTest {

    private Parser parser;
    private CommandFactory commandFactoryMock;

    @BeforeEach
    void setUp() {
        commandFactoryMock = mock(CommandFactory.class);
        parser = new Parser(commandFactoryMock);
    }

    @Test
    void testParseValidCommand() {
        String fullCommand = "prog start /p 1";
        Command expectedCommand = mock(Command.class);
        when(commandFactoryMock.createCommand("prog", "start /p 1")).thenReturn(expectedCommand);

        Command result = parser.parse(fullCommand);

        assertNotNull(result);
        assertEquals(expectedCommand, result);
        verify(commandFactoryMock).createCommand("prog", "start /p 1");
    }

    @Test
    void testParseExitCommand() {
        String fullCommand = "bye";
        when(commandFactoryMock.createCommand("bye", "")).thenReturn(new ExitCommand());
        Command result = parser.parse(fullCommand);

        assertInstanceOf(ExitCommand.class, result);
    }

    @Test
    void testParseInvalidCommand() {
        String fullCommand = "unknownCommand";
        when(commandFactoryMock.createCommand("unknownCommand", "")).
                thenReturn(new InvalidCommand());
        Command result = parser.parse(fullCommand);

        assertInstanceOf(InvalidCommand.class, result);
    }

    @Test
    void testParseEmptyCommand() {
        assertThrows(ParserExceptions.class, () -> parser.parse(""),
                "Should throw EmptyInputBuffBuddyException on empty command");
    }

    @Test
    void testParseOnlySpacesCommand() {
        assertThrows(ParserExceptions.class, () -> parser.parse("   "),
                "Should throw EmptyInputBuffBuddyException on command with only spaces");
    }

    @Test
    void testParseNullCommand() {
        assertThrows(ParserExceptions.class, () -> parser.parse(null),
                "Should throw EmptyInputBuffBuddyException on null command");
    }
}
