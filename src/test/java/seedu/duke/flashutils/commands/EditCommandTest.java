package seedu.duke.flashutils.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashCardSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {
    @Test
    public void testEditCommandConstructor() {
        FlashCardSet testModule = new FlashCardSet("Some module");
        testModule.addCard(new Card("Some Question", "Some Answer"));

        EditCommand command = new EditCommand(testModule, 1, "New Question", "New Answer");

        assertEquals("New Question", command.getNewCard().getQuestion());
        assertEquals("New Answer", command.getNewCard().getAnswer());
        assertEquals(testModule, command.getTargetSet());
    }

    @Test
    public void testSuccessfulCardEditCommand() {
        String testQuestion = "Some question";
        String testAnswer = "Some answer";
        String newQuestion = "New Question";
        String newAnswer = "New Answer";
        FlashCardSet testModule = new FlashCardSet("Some module");
        Card testCard = new Card(testQuestion, testAnswer);

        testModule.addCard(testCard);
        EditCommand command = new EditCommand(testModule, 1, newQuestion, newAnswer);
        command.execute();

        Card editedCard = testModule.getCard(0);
        assertEquals(newQuestion, editedCard.getQuestion());
        assertEquals(newAnswer, editedCard.getAnswer());
    }

    @Test
    public void testEditInvalidIndex() {
        FlashCardSet testModule = new FlashCardSet("Some module");
        assertThrows(IndexOutOfBoundsException.class, () ->
                new EditCommand(testModule, -1, "New Question", "New Answer"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                new EditCommand(testModule, 1, "New Question", "New Answer"));
    }
}
