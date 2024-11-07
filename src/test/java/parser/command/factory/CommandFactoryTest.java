// @@author nirala-ts

package parser.command.factory;

import command.Command;
import command.ExitCommand;
import command.InvalidCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class CommandFactoryTest {

    private CommandFactory commandFactory;
    private ProgCommandFactory progFactoryMock;
    private MealCommandFactory mealFactoryMock;
    private WaterCommandFactory waterFactoryMock;
    private HistoryCommandFactory historyFactoryMock;

    @BeforeEach
    void setUp() {
        progFactoryMock = mock(ProgCommandFactory.class);
        mealFactoryMock = mock(MealCommandFactory.class);
        waterFactoryMock = mock(WaterCommandFactory.class);
        historyFactoryMock = mock(HistoryCommandFactory.class);

        commandFactory = new CommandFactory(progFactoryMock, mealFactoryMock, waterFactoryMock, historyFactoryMock);
    }

    @Test
    void testCreateExitCommand() {
        String commandString = "bye";
        String argumentString = "";

        Command result = commandFactory.createCommand(commandString, argumentString);

        assertInstanceOf(ExitCommand.class, result);
    }

    @Test
    void testCreateInvalidCommand() {
        String commandString = "unknownCommand";
        String argumentString = "";

        Command result = commandFactory.createCommand(commandString, argumentString);

        assertInstanceOf(InvalidCommand.class, result);
    }

    @Test
    void testCreateProgCommand() {
        String commandString = "prog";
        String argumentString = "start /p 1";
        Command expectedCommand = mock(Command.class);

        when(progFactoryMock.parse(argumentString)).thenReturn(expectedCommand);

        Command result = commandFactory.createCommand(commandString, argumentString);

        assertEquals(expectedCommand, result);
        verify(progFactoryMock).parse(argumentString);
    }

    @Test
    void testCreateMealCommand() {
        String commandString = "meal";
        String argumentString = "add /name Sample Meal";
        Command expectedCommand = mock(Command.class);

        when(mealFactoryMock.parse(argumentString)).thenReturn(expectedCommand);

        Command result = commandFactory.createCommand(commandString, argumentString);

        assertEquals(expectedCommand, result);
        verify(mealFactoryMock).parse(argumentString);
    }

    @Test
    void testCreateWaterCommand() {
        String commandString = "water";
        String argumentString = "log /volume 500";
        Command expectedCommand = mock(Command.class);

        when(waterFactoryMock.parse(argumentString)).thenReturn(expectedCommand);

        Command result = commandFactory.createCommand(commandString, argumentString);

        assertEquals(expectedCommand, result);
        verify(waterFactoryMock).parse(argumentString);
    }

    @Test
    void testCreateHistoryCommand() {
        String commandString = "history";
        String argumentString = "view /d 11-11-2023";
        Command expectedCommand = mock(Command.class);

        when(historyFactoryMock.parse(argumentString)).thenReturn(expectedCommand);

        Command result = commandFactory.createCommand(commandString, argumentString);

        assertEquals(expectedCommand, result);
        verify(historyFactoryMock).parse(argumentString);
    }
}
