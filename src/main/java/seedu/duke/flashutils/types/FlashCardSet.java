package seedu.duke.flashutils.types;

import seedu.duke.flashutils.utils.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the list of flashcards of the same module
 */
public class FlashCardSet implements Iterable<Card> {

    private final ArrayList<Card> flashCardSet;
    private final String moduleName;

    public FlashCardSet(String module) {
        this.moduleName = module;
        this.flashCardSet = new ArrayList<>();
    }

    public FlashCardSet(String module, ArrayList<Card> flashCardSet) {
        this.moduleName = module;
        this.flashCardSet = flashCardSet;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public ArrayList<Card> getFlashCardSet() {
        return this.flashCardSet;
    }

    public Card getCard(int cardIndex) throws IndexOutOfBoundsException {
        if (!indexIsValid(cardIndex)) {
            throw new IndexOutOfBoundsException();
        }
        return this.flashCardSet.get(cardIndex);
    }

    public void addCard(Card toAdd) {
        flashCardSet.add(toAdd);
        assert flashCardSet.contains(toAdd);
    }

    public void removeCard(Card toRemove) {
        flashCardSet.remove(toRemove);
        assert !flashCardSet.contains(toRemove);
    }


    // Displays all flashcards (view command) in FLashCardSet
    public void viewFlashCards(String module) {
        String currentModule = getModuleName(); 
        assert (currentModule != null);
        if ((currentModule != null) && (currentModule.equals(module)) && (!flashCardSet.isEmpty())) {
            int index = 1;
            System.out.println("_".repeat(50));
            for (Card flashCard : flashCardSet) {
                System.out.println(index + ". " + flashCard);
                System.out.println("_".repeat(50));
                index++;
            }
        } else if (flashCardSet.isEmpty()) {
            Ui.printResponse("No flashcards found for this module.");
        }
    }

    public void performFlashBang(long timerThreshold) {
        //start keeps track of time spent in answering all questions,
        // recurring keeps track of time spent in answering each question
        Date start = new Date();
        Date recurring = new Date();
        // variables to store the number of correct and wrong answers
        int flashCardIndex = 0;
        int correctAnswers = 0;
        int wrongAnswers = 0;

        List<Card> mistakes = new ArrayList<>();
        for (Card card : flashCardSet) {
            Ui.printResponse("Flashcard no." + flashCardIndex + "\n\t" + card.getQuestion());
            Ui.printResponse("Reveal the answer? (y/n)");
            String reveal = Ui.getRequest();

            while (!reveal.equalsIgnoreCase("y") && !reveal.equalsIgnoreCase("n")) {
                Ui.printResponse("Invalid input. Please enter 'y' or 'n'.");
                reveal = Ui.getRequest();
            }
            if (reveal.equals("y")) {
                System.out.println("Answer : "+card.getAnswer());
            }

            Ui.printResponse("Did you get the correct answer? (y/n)");
            String answerCorrect = Ui.getRequest();

            while (!answerCorrect.equalsIgnoreCase("y") && !answerCorrect.equalsIgnoreCase("n")) {
                Ui.printResponse("Invalid input. Please enter 'y' or 'n'.");
                answerCorrect = Ui.getRequest();
            }

            if (answerCorrect.equals("y")) {
                correctAnswers+=1;
            } else if (answerCorrect.equals("n")) {
                wrongAnswers+=1;
                mistakes.add(card); // Add card to the mistake list
            }

            double timeSpentPerQuestion = Math.round(((new Date()).getTime()-recurring.getTime())/1000.00);

            Ui.printResponse("You spent "+timeSpentPerQuestion+" seconds reviewing this flashcard.");
            recurring = new Date();

            if(timerThreshold > 0) {
                if (recurring.getTime() - start.getTime() > timerThreshold) {
                    Ui.printResponse("Oops You've run out of time! ");
                }
            }
            flashCardIndex++;
        }

        // Calculate percentage of right/wrong answers
        int totalAnswers = correctAnswers + wrongAnswers;
        double correctPercentage = (double) correctAnswers / totalAnswers * 100;
        System.out.println("Your score is: " + correctPercentage + "% (" + correctAnswers + "/" + totalAnswers + ")");
        // Print mistakes list
        System.out.println("You answered the following flashcards incorrectly:\n");
        for (Card card : mistakes) {
            System.out.println(card.toString());
        }
    }

    @Override
    public String toString() {
        StringBuilder setString = new StringBuilder(String.format("MODULE: %1$s\n", moduleName));
        for (Card card : flashCardSet) {
            setString.append(card.toString()).append("\n");
        }
        return setString.toString();
    }

    public int getNumberOfFlashcards() {
        return flashCardSet.size();
    }

    public boolean indexIsValid(int index) {
        return index >= 0 && index < flashCardSet.size();
    }
    
    @Override
    public Iterator<Card> iterator() {
        return flashCardSet.iterator();
    }

}
