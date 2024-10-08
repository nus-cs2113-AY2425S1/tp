import javax.sound.midi.Receiver;

public class Command {
    protected static boolean isBye;

    public Command() {
        isBye = false;
    }

    public void setBye() {
        Command.isBye = true;
    }

    public boolean isBye() {
        return isBye;
    }


    public void execute(RecipeList recipes, Ui ui) {

    }

    public static void addNewRecipe(RecipeList recipes, Recipe newRecipe, Ui ui) {

    }


}
