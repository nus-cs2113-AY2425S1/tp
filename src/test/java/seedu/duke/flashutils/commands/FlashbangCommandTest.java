package seedu.duke.flashutils.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.FlashCardSet;
import seedu.duke.flashutils.utils.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashbangCommandTest {

    @BeforeEach
    public void setUp() {
        String simulatedUserInput = "Some user input\n";
        new Ui(); // Initialize the Ui instance and scanner
    }

    @Test
    public void testFlashbangCommandConstructor() {
        FlashCardSet testModule = new FlashCardSet("Some module");
        FlashbangCommand command = new FlashbangCommand(testModule);
        assertEquals(testModule, command.getTargetSet());
    }

}
