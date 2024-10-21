package parser;

import command.Command;
import command.InvalidCommand;
import command.programme.edit.CreateExerciseCommand;
import command.programme.edit.DeleteExerciseCommand;
import command.programme.edit.EditExerciseCommand;
import command.programme.edit.EditSubCommand;
import programme.Day;
import programme.Exercise;

import command.programme.CreateCommand;
import command.programme.ViewCommand;
import command.programme.StartCommand;
import command.programme.EditCommand;
import command.programme.DeleteCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProgCommandParserTest {

    private ProgCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ProgCommandParser();
    }

    @Test
    public void testParse_viewCommand() {
        String argumentString = "view 1";
        Command command = parser.parse(argumentString);

        assertInstanceOf(ViewCommand.class, command, "Expected ViewCommand instance");
        ViewCommand viewCommand = (ViewCommand) command;
        assertEquals(0, viewCommand.getProgId());
    }

    @Test
    public void testParse_createCommand() {
        String argumentString = "create MyProgram /d Day1";
        Command command = parser.parse(argumentString);

        assertInstanceOf(CreateCommand.class, command, "Expected CreateCommand instance");
        CreateCommand createCommand = (CreateCommand) command;
        assertEquals("MyProgram", createCommand.getName());
        assertEquals(1, createCommand.getDays().size());

        Day day = createCommand.getDays().get(0);
        assertNotNull(day, "Day object should not be null");
        assertEquals("Day1", day.getName(), "Expected day name to be 'Day1'");
    }

    @Test
    public void testParse_invalidCommand() {
        String argumentString = "invalid";
        Command command = parser.parse(argumentString);

        assertInstanceOf(InvalidCommand.class, command, "Expected InvalidCommand instance");
    }

    @Test
    public void testPrepareCreateCommand_validInputSingleExercise() {
        String argumentString = "MyProgram /d Day1 /e /n Squat /s 3 /r 10 /w 100";
        Command command = parser.parse("create " + argumentString);

        assertInstanceOf(CreateCommand.class, command, "Expected CreateCommand instance");
        CreateCommand createCommand = (CreateCommand) command;
        assertEquals("MyProgram", createCommand.getName());
        assertEquals(1, createCommand.getDays().size());

        Day day = createCommand.getDays().get(0);
        assertEquals("Day1", day.getName());
        assertEquals(1, day.getExercisesCount());

        Exercise exercise = day.getExercise(0);
        assertEquals("Squat", exercise.getName());
        assertEquals(3, exercise.getSets());
        assertEquals(10, exercise.getReps());
        assertEquals(100, exercise.getWeight());
    }

    @Test
    public void testPrepareCreateCommand_validInputMultipleExercises() {
        String argumentString = "MyProgram /d Day1 /e /n Squat /s 3 /r 10 /w 100 /e " +
                "/n BenchPress /s 4 /r 8 /w 80";
        Command command = parser.parse("create " + argumentString);

        assertInstanceOf(CreateCommand.class, command, "Expected CreateCommand instance");
        CreateCommand createCommand = (CreateCommand) command;

        assertEquals("MyProgram", createCommand.getName());
        assertEquals(1, createCommand.getDays().size());

        Day day = createCommand.getDays().get(0);
        assertEquals(2, day.getExercisesCount(), "Exercise count should be 2.");

        // Verifying the first exercise
        Exercise exercise1 = day.getExercise(0);
        assertEquals("Squat", exercise1.getName(), "First exercise name should be 'Squat'.");
        assertEquals(3, exercise1.getSets(), "First exercise sets should be 3.");
        assertEquals(10, exercise1.getReps(), "First exercise reps should be 10.");
        assertEquals(100, exercise1.getWeight(), "First exercise weight should be 100.");

        // Verifying the second exercise
        Exercise exercise2 = day.getExercise(1);
        assertEquals("BenchPress", exercise2.getName(), "Second exercise name should be 'BenchPress'.");
        assertEquals(4, exercise2.getSets(), "Second exercise sets should be 4.");
        assertEquals(8, exercise2.getReps(), "Second exercise reps should be 8.");
        assertEquals(80, exercise2.getWeight(), "Second exercise weight should be 80.");
    }

    @Test
    public void testPrepareCreateCommand_missingProgrammeName() {
        String argumentString = " /d Day1 /e n Squat /s 3 /r 10 /w 100";

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                parser.parse("create " + argumentString));

        assertEquals("Programme name cannot be empty. Please enter a name.", exception.getMessage());
    }

    @Test
    public void testPrepareCreateCommand_missingExerciseArguments() {
        String argumentString = "MyProgram /d Day1 /e n Squat /s 3";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                parser.parse("create " + argumentString));


        assertEquals("Missing exercise arguments. Please provide exercise name, set, " +
                "rep and weight.", exception.getMessage());
    }

    @Test
    public void testPrepareCreateCommand_invalidNumberFormat() {
        String argumentString = "MyProgram /d Day1 /e /n Squat /s three /r 10 /w 100";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                parser.parse("create "+ argumentString));


        assertEquals("Invalid sets value. It must be an integer.", exception.getMessage());
    }

    @Test
    public void testPrepareEditCommand_addExercise() {
        String argumentString = "/p 1 /d 1 /a /n BenchPress /s 3 /r 10 /w 80";
        Command command = parser.parse("edit " + argumentString);

        assertInstanceOf(EditCommand.class, command, "Expected EditCommand instance");
        EditCommand editCommand = (EditCommand) command;

        // Verify that the subcommand added is a CreateExerciseCommand
        assertEquals(1, editCommand.getSubCommands().size());
        EditSubCommand subCommand = editCommand.getSubCommands().get(0);
        assertInstanceOf(CreateExerciseCommand.class, subCommand, "Expected CreateExerciseCommand");

        CreateExerciseCommand createExerciseCommand = (CreateExerciseCommand) subCommand;
        assertEquals("BenchPress", createExerciseCommand.getCreated().getName());
        assertEquals(3, createExerciseCommand.getCreated().getSets());
        assertEquals(10, createExerciseCommand.getCreated().getReps());
        assertEquals(80, createExerciseCommand.getCreated().getWeight());
    }

    @Test
    public void testPrepareEditCommand_deleteExercise() {
        String argumentString = "/p 1 /d 1 /x 1";
        Command command = parser.parse("edit " + argumentString);

        assertInstanceOf(EditCommand.class, command, "Expected EditCommand instance");
        EditCommand editCommand = (EditCommand) command;

        // Verify that the subcommand added is a DeleteExerciseCommand
        assertEquals(1, editCommand.getSubCommands().size());
        EditSubCommand subCommand = editCommand.getSubCommands().get(0);
        assertInstanceOf(DeleteExerciseCommand.class, subCommand, "Expected DeleteExerciseCommand");

        DeleteExerciseCommand deleteExerciseCommand = (DeleteExerciseCommand) subCommand;
        assertEquals(0, deleteExerciseCommand.getDayId());
        assertEquals(0, deleteExerciseCommand.getExerciseId());
    }

    @Test
    public void testPrepareEditCommand_updateExercise() {
        String argumentString = "/p 1 /d 1 /u 1 /n DeadLift /s 3 /r 5 /w 120";
        Command command = parser.parse("edit " + argumentString);

        assertInstanceOf(EditCommand.class, command, "Expected EditCommand instance");
        EditCommand editCommand = (EditCommand) command;

        // Verify that the subcommand added is an EditExerciseCommand
        assertEquals(1, editCommand.getSubCommands().size());
        EditSubCommand subCommand = editCommand.getSubCommands().get(0);
        assertInstanceOf(EditExerciseCommand.class, subCommand, "Expected EditExerciseCommand");

        EditExerciseCommand editExerciseCommand = (EditExerciseCommand) subCommand;
        Exercise updatedExercise = editExerciseCommand.getUpdate();
        assertEquals("DeadLift", updatedExercise.getName());
        assertEquals(3, updatedExercise.getSets());
        assertEquals(5, updatedExercise.getReps());
        assertEquals(120, updatedExercise.getWeight());
    }

    @Test
    public void testPrepareEditCommand_unknownFlag() {
        String argumentString = "/p 1 /d 1 /z 1";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                parser.parse("edit " + argumentString));


        assertEquals("Unknown flag: z", exception.getMessage());
    }

    @Test
    public void testPrepareDeleteCommand_validInput() {
        String argumentString = "1";
        Command command = parser.parse("delete " + argumentString);

        assertInstanceOf(DeleteCommand.class, command, "Expected DeleteCommand instance");
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(0, deleteCommand.getProgId(),
                "Program ID should be parsed as 0 for input '1'");
    }

    @Test
    public void testPrepareDeleteCommand_invalidNumberFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("abc")
        );

        assertEquals("Invalid index. Please provide a valid number.", exception.getMessage());
    }

    @Test
    public void testPrepareDeleteCommand_negativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("-1")
        );

        assertEquals("Index must be a positive number.", exception.getMessage());
    }

    @Test
    public void testPrepareDeleteCommand_zeroAsInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("0")
        );

        assertEquals("Index must be a positive number.", exception.getMessage());
    }

    @Test
    public void testPrepareDeleteCommand_emptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("")
        );

        assertEquals("Index was not provided.", exception.getMessage());
    }

    @Test
    public void testPrepareDeleteCommand_inputWithSpaces() {
        int result = IndexParser.parseIndex("  3  ");

        assertEquals(2, result, "Index should be parsed as 2 for input '  3  '");
    }

    @Test
    public void testPrepareViewCommand_validInput() {
        String argumentString = "1";
        Command command = parser.parse("view " + argumentString);

        assertInstanceOf(ViewCommand.class, command, "Expected ViewCommand instance");
        ViewCommand viewCommand = (ViewCommand) command;
        assertEquals(0, viewCommand.getProgId(), "Programme index mismatch");
    }

    @Test
    public void testPrepareViewCommand_invalidNumberFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("abc")
        );

        assertEquals("Invalid index. Please provide a valid number.", exception.getMessage());
    }

    @Test
    public void testPrepareViewCommand_negativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("-1")
        );

        assertEquals("Index must be a positive number.", exception.getMessage());
    }

    @Test
    public void testPrepareViewCommand_zeroAsInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("0")
        );

        assertEquals("Index must be a positive number.", exception.getMessage());
    }

    @Test
    public void testPrepareViewCommand_emptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("")
        );

        assertEquals("Index was not provided.", exception.getMessage());
    }

    @Test
    public void testPrepareViewCommand_inputWithSpaces() {
        int result = IndexParser.parseIndex("  3  ");

        assertEquals(2, result, "Index should be parsed as 2 for input '  3  '");
    }

    @Test
    public void testPrepareStartCommand_validInput() {
        String argumentString = "2";
        Command command = parser.parse("start " + argumentString);

        assertInstanceOf(StartCommand.class, command, "Expected StartCommand instance");
        StartCommand startCommand = (StartCommand) command;
        assertEquals(1, startCommand.getProgId(), "Programme index mismatch");
    }

    @Test
    public void testPrepareStartCommand_invalidNumberFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("abc")
        );

        assertEquals("Invalid index. Please provide a valid number.", exception.getMessage());
    }

    @Test
    public void testPrepareStartCommand_negativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("-1")
        );

        assertEquals("Index must be a positive number.", exception.getMessage());
    }

    @Test
    public void testPrepareStartCommand_zeroAsInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("0")
        );

        assertEquals("Index must be a positive number.", exception.getMessage());
    }

    @Test
    public void testPrepareStartCommand_emptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                IndexParser.parseIndex("")
        );

        assertEquals("Index was not provided.", exception.getMessage());
    }

    @Test
    public void testPrepareStartCommand_inputWithSpaces() {
        int result = IndexParser.parseIndex("  3  ");

        assertEquals(2, result, "Index should be parsed as 2 for input '  3  '");
    }
}
