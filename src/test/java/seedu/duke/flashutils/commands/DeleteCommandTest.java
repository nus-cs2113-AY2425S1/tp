package seedu.duke.flashutils.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashCardSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    @Test
    public void testDeleteCommandConstructor() {
        String testQuestion = "Some question";
        String testAnswer = "Some answer";
        FlashCardSet testModule = new FlashCardSet("Some module");
        Card testCard = new Card(testQuestion, testAnswer);
        testModule.addCard(testCard);
        DeleteCommand command = new DeleteCommand(testModule, 1);

        assertEquals(testCard, command.getTargetCard());
        assertEquals(testModule, command.getTargetSet());
    }

    @Test
    public void testSuccessfulDeleteCommand() {
        String testQuestion = "Some question";
        String testAnswer = "Some answer";
        FlashCardSet testModule = new FlashCardSet("Some module");
        Card testCard = new Card(testQuestion, testAnswer);

        testModule.addCard(testCard);
        DeleteCommand command = new DeleteCommand(testModule, 1);
        command.execute();

        assertFalse(testModule.getFlashCardSet().contains(testCard));
    }

    @Test
    public void testInvalidIndex() {
        FlashCardSet testModule = new FlashCardSet("Some module");
        Card testCard1 = new Card("Question 1", "Answer 1");
        Card testCard2 = new Card("Question 2", "Answer 2");
        new AddCommand(testModule, testCard1).execute();
        new AddCommand(testModule, testCard2).execute();

        assertThrows(IndexOutOfBoundsException.class, () -> new DeleteCommand(testModule, 3)
                .execute());
    }

}
