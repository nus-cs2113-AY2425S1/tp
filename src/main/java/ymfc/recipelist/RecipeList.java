package ymfc.recipelist;

import ymfc.recipe.Recipe;

import java.util.ArrayList;
import java.util.Comparator;

public class RecipeList {
    private ArrayList<Recipe> recipes;
    private int counter;

    public RecipeList() {
        counter = 0;
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
        counter++;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public Recipe getRecipe(int id) {
        return recipes.get(id);
    }

    public void removeRecipe(int id) {
        assert counter > 0 : "List should not be empty when deleting recipe";
        recipes.remove(id);
        counter--;
    }

    public boolean removeRecipeByName(String name) {
        assert counter > 0 : "List should not be empty when deleting recipe";
        assert name != null : "Recipe name should not be null";
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equalsIgnoreCase(name)) {
                recipes.remove(i);
                counter--;
                return true;
            }
        }
        return false;
    }

    public boolean editRecipe(String name, Recipe editedRecipe) {
        assert counter > 0 : "List should not be empty when editing recipe";
        assert name != null : "Recipe name should not be null";
        // Find the index of the recipe to edit
        int index = -1;
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equalsIgnoreCase(name)) {
                index = i;
            }
        }
        // If index not found, return false, else edit the recipe based on index found
        if (index == -1) {
            return false;
        }
        recipes.set(index, editedRecipe);
        return true;
    }

    public int getCounter() {
        return counter;
    }

    public void sortAlphabetically() {
        recipes.sort(Comparator.comparing(Recipe::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public void sortByTimeTaken() {
        recipes.sort(Comparator.comparing(Recipe::getTimeTaken));
    }

}
