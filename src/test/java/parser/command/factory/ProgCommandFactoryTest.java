// @@author nirala-ts

package parser.command.factory;

import command.Command;
import command.InvalidCommand;
import command.programme.CreateProgrammeCommand;
import command.programme.DeleteProgrammeCommand;
import command.programme.ListProgrammeCommand;
import command.programme.StartProgrammeCommand;
import command.programme.ViewProgrammeCommand;
import command.programme.LogProgrammeCommand;

import exceptions.ProgrammeException;
import exceptions.ParserException;
import exceptions.FlagException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programme.Day;
import programme.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProgCommandFactoryTest {
    private ProgCommandFactory progCommandFactory;

    @BeforeEach
    void setUp() {
        progCommandFactory = new ProgCommandFactory();
    }

    // Tests for parse
    @Test
    void testParseValidCreateCommand() {
        String argumentString = "create /p MyProgram /d Day1";

        Command result = progCommandFactory.parse(argumentString);

        assertInstanceOf(CreateProgrammeCommand.class, result);
    }

    @Test
    void testParseValidListCommand() {
        String argumentString = "list";

        Command result = progCommandFactory.parse(argumentString);

        assertInstanceOf(ListProgrammeCommand.class, result);
    }

    @Test
    void testParseInvalidCommand() {
        String argumentString = "unknownCommand";

        Command result = progCommandFactory.parse(argumentString);

        assertInstanceOf(InvalidCommand.class, result);
    }

    // Tests for prepareCreateCommand
    @Test
    public void testPrepareCreateCommandValidInput() {
        String argumentString = "MyProgram /d Day1 /e /name PushUps /set 3 /rep 15 /w 0 /c 50";

        Day expectedDay = new Day("Day1");
        expectedDay.insertExercise(new Exercise(3, 15, 0, 50, "PushUps"));

        ArrayList<Day> expectedDays = new ArrayList<>(List.of(expectedDay));
        CreateProgrammeCommand expectedCommand = new CreateProgrammeCommand("MyProgram", expectedDays);

        Command result = progCommandFactory.parse("create " + argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareCreateCommandNoDaysNoExercises() {
        String argumentString = "MyEmptyProgram";

        CreateProgrammeCommand expectedCommand = new CreateProgrammeCommand("MyEmptyProgram",
                new ArrayList<>());

        Command result = progCommandFactory.parse("create " + argumentString);

        assertEquals(expectedCommand, result);
    }


    @Test
    public void testPrepareCreateCommandMultipleDays() {
        String argumentString = "MyProgram /d Day1 /e /name PushUps /set 3 /rep 15 /w 0 /c 50 /d " +
                "Day2 /e /name SitUps /set 2 /rep 20 /w 0 /c 30";

        Day day1 = new Day("Day1");
        day1.insertExercise(new Exercise(3, 15, 0, 50, "PushUps"));

        Day day2 = new Day("Day2");
        day2.insertExercise(new Exercise(2, 20, 0, 30, "SitUps"));

        ArrayList<Day> expectedDays = new ArrayList<>(Arrays.asList(day1, day2));
        CreateProgrammeCommand expectedCommand = new CreateProgrammeCommand("MyProgram", expectedDays);

        Command result = progCommandFactory.parse("create " + argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareCreateCommandMultipleDaysMultipleExercises() {
        String argumentString = "MyProgram " +
                "/d Day1 /e /name PushUps /set 3 /rep 15 /w 0 /c 50 " +
                "/e /name SitUps /set 2 /rep 20 /w 0 /c 30 " +
                "/d Day2 /e /name Squats /set 4 /rep 10 /w 20 /c 100 " +
                "/e /name Lunges /set 3 /rep 12 /w 15 /c 80";

        Day day1 = new Day("Day1");
        day1.insertExercise(new Exercise(3, 15, 0, 50, "PushUps"));
        day1.insertExercise(new Exercise(2, 20, 0, 30, "SitUps"));

        Day day2 = new Day("Day2");
        day2.insertExercise(new Exercise(4, 10, 20, 100, "Squats"));
        day2.insertExercise(new Exercise(3, 12, 15, 80, "Lunges"));

        ArrayList<Day> expectedDays = new ArrayList<>(Arrays.asList(day1, day2));
        CreateProgrammeCommand expectedCommand = new CreateProgrammeCommand("MyProgram", expectedDays);

        Command result = progCommandFactory.parse("create " + argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test void testPrepareCreateCommandNegativeExerciseParameter() {
        String argumentString = "MyProgram /d Day1 /e /name PushUps /set -3 /rep 15 /w 5 /c 50";

        assertThrows(ParserExceptions.class,
                () -> progCommandFactory.parse("create " + argumentString));
    }

    @Test
    public void testPrepareCreateCommandMissingProgrammeName() {
        String argumentString = "/d Day1 /e /name PushUps /set 3 /rep 15 /w 0 /c 50";

        assertThrows(ProgrammeException.class,
                () -> progCommandFactory.parse("create " + argumentString));
    }

    @Test
    public void testPrepareCreateCommandInvalidDayFormat() {
        String argumentString = "MyProgram /d /e /name PushUps /set 3 /rep 15 /w 0 /c 50";

        assertThrows(ProgrammeException.class,
                () -> progCommandFactory.parse("create " + argumentString));
    }

    @Test
    public void testPrepareCreateCommandInvalidExerciseFormat() {
        String argumentString = "MyProgram /d Day1 /e /name PushUps /set 3 /rep 15 /w invalid /c 50";

        assertThrows(ParserException.class,
                () -> progCommandFactory.parse("create " + argumentString));
    }

    @Test
    public void testPrepareCreateCommandMissingExerciseName() {
        String argumentString = "MyProgram /d Day1 /e /name  /set 3 /rep 15 /w 0 /c 50";

        assertThrows(FlagException.class,
                () -> progCommandFactory.parse("create " + argumentString));
    }

    @Test
    public void testPrepareCreateCommandMissingExerciseFlag() {
        String argumentString = "MyProgram /d Day1 /e /name Lunges /rep 15 /w 0 /c 50";

        assertThrows(FlagException.class,
                () -> progCommandFactory.parse("create " + argumentString));
    }


    // Tests for prepareViewCommand
    @Test
    public void testPrepareViewCommandValidIndex() {
        String argumentString = "view 1";
        ViewProgrammeCommand expectedCommand = new ViewProgrammeCommand(0);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareViewCommandInvalidIndexFormat() {
        String argumentString = "view invalidIndex";

        assertThrows(ParserException.class, () -> progCommandFactory.parse(argumentString));
    }

    @Test
    public void testPrepareViewCommandNoIndex() {
        String argumentString = "view";
        ViewProgrammeCommand expectedCommand = new ViewProgrammeCommand(-1);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    // Tests for prepareStartCommand
    @Test
    public void testPrepareStartCommandValidIndex() {
        String argumentString = "start 1";
        StartProgrammeCommand expectedCommand = new StartProgrammeCommand(0);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareStartCommandNoIndex() {
        String argumentString = "start";

        assertThrows(ParserException.class, () -> progCommandFactory.parse( argumentString));
    }

    @Test
    public void testPrepareStartCommandInvalidIndexFormat() {
        String argumentString = "start invalidIndex";

        assertThrows(ParserException.class, () -> progCommandFactory.parse(argumentString));
    }

    // Tests for prepareDeleteCommand
    @Test
    public void testPrepareDeleteCommandValidIndex() {
        String argumentString = "delete 1";
        DeleteProgrammeCommand expectedCommand = new DeleteProgrammeCommand(0);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareDeleteCommandInvalidIndexFormat() {
        String argumentString = "delete invalidIndex";

        assertThrows(ParserException.class, () -> progCommandFactory.parse(argumentString));
    }

    @Test
    public void testPrepareDeleteCommandNoIndex() {
        String argumentString = "delete";
        DeleteProgrammeCommand expectedCommand = new DeleteProgrammeCommand(-1);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    // Tests for prepareLogCommand
    @Test
    public void testPrepareLogCommandValidArgumentsAllFlags() {
        String argumentString = "log /p 1 /d 1 /date 05-11-2023";
        LogProgrammeCommand expectedCommand = new LogProgrammeCommand(0, 0,
                LocalDate.of(2023, 11, 5));

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareLogCommandMissingDayFlag() {
        String argumentString = "log /p 1 /date 05-11-2023";

        assertThrows(FlagException.class, () -> progCommandFactory.parse( argumentString));
    }

    @Test
    public void testPrepareLogCommandInvalidDateFormat() {
        //Expected format: dd-MM-yyyy
        String argumentString = "log /p 1 /d 0 /date 2023-11-05";

        assertThrows(ParserException.class, () -> progCommandFactory.parse( argumentString));
    }

    @Test
    public void testPrepareLogCommandMissingDateFlag() {
        String argumentString = "log /p 1 /d 1";
        LocalDate currentDate = LocalDate.now();
        LogProgrammeCommand expectedCommand = new LogProgrammeCommand(0, 0, currentDate);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }

    @Test
    public void testPrepareLogCommandMissingDateAndProgIndexFlag() {
        String argumentString = "log /d 4";

        LocalDate currentDate = LocalDate.now();
        LogProgrammeCommand expectedCommand = new LogProgrammeCommand(-1, 3, currentDate);

        Command result = progCommandFactory.parse(argumentString);

        assertEquals(expectedCommand, result);
    }
}
