package seedu.duke.flashutils.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.flashutils.types.Card;
import seedu.duke.flashutils.types.FlashBook;
import seedu.duke.flashutils.types.FlashCardSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewAllCommandTest {

    private FlashCardSet module1;
    private FlashCardSet module2;
    private Card card1;
    private Card card2;
    private Card card3;
    private FlashBook flashBook;

    @BeforeEach
    public void setUp() {
        // Set up the flashcards and sets for testing
        card1 = new Card("What is Java?", "A programming language.");
        card2 = new Card("What is OOP?", "Object-Oriented Programming.");
        card3 = new Card("What is Python?","A programming language.");

        flashBook = FlashBook.getInstance();
        flashBook.addFlashCardSet("CS1010");
        flashBook.getFlashCardSet("CS1010").addCard(card1);
        flashBook.getFlashCardSet("CS1010").addCard(card3);
        flashBook.addFlashCardSet("CS2113");
        flashBook.getFlashCardSet("CS2113").addCard(card2);
    }

    @Test
    public void viewAllCommandTestExecute() {
        // Test the execute method for ViewAllCommand, which should view all flashcards from all modules
        ViewAllCommand command = new ViewAllCommand();
        CommandResult result = command.execute();

        // Check that the command result contains flashcards for both modules
        String expectedMessage = """
                MODULE NAME: CS1010
                #1 -> What is Java? : A programming language.
                #2 -> What is Python? : A programming language.
                
                MODULE NAME: CS2113
                #1 -> What is OOP? : Object-Oriented Programming.""";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void viewAllCommandTestLongCardContent() {
        // Test handling of flashcards with lengthy content
        Card longCard = new Card("Describe the OSI model",
                "The OSI model is a conceptual framework used to understand network interactions. " +
                        "It has 7 layers: Physical, Data Link, Network, Transport, Session, Presentation, " +
                        "and Application.");
        flashBook.getFlashCardSet("CS1010").addCard(longCard);

        ViewAllCommand command = new ViewAllCommand();
        CommandResult result = command.execute();

        String expectedMessage = """
                MODULE NAME: CS1010
                #1 -> What is Java? : A programming language.
                #2 -> What is Python? : A programming language.
                #3 -> Describe the OSI model : The OSI model is a conceptual framework used to understand \
                network interactions. \
                It has 7 layers: Physical, Data Link, Network, Transport, Session, \
                Presentation, and Application.
                
                MODULE NAME: CS2113
                #1 -> What is OOP? : Object-Oriented Programming.""";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void viewAllCommandTestEmptyModule() {
        // Test the handling of a module with no flashcards
        flashBook.deleteFlashCardSet("CS1010");
        ViewAllCommand command = new ViewAllCommand();
        CommandResult result = command.execute();

        String expectedMessage =
                "MODULE NAME: CS2113\n" +
                "#1 -> What is OOP? : Object-Oriented Programming.";

        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void viewAllCommandTestEmptyBook() {
        // Test the behavior when there are no flashcards in FlashBook
        flashBook.deleteFlashCardSet("CS1010");
        flashBook.deleteFlashCardSet("CS2113");// Empty the FlashBook
        ViewAllCommand command = new ViewAllCommand();
        CommandResult result = command.execute();

        // Expect an empty result message as there are no flashcards
        assertEquals("", result.getFeedbackToUser());
    }
}
