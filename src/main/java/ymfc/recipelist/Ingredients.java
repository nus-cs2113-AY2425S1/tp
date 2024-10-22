package ymfc.recipelist;

import ymfc.recipe.Ingredient;
import ymfc.recipe.Recipe;

import java.util.ArrayList;
import java.util.Comparator;

public class Ingredients {
    ArrayList<Ingredient> ingredients = new ArrayList<>();

    public Ingredients() {
        this.ingredients = new ArrayList<>();
    }

    public void addRecipe(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getRecipes() {
        return ingredients;
    }

    public Ingredient getIngredient(int id) {
        return ingredients.get(id);
    }

    public void removeRecipe(int id) {
        assert !ingredients.isEmpty() : "List should not be empty when deleting ingredient";
        ingredients.remove(id);
    }

    public boolean removeRecipeByName(String name) {
        assert !ingredients.isEmpty() : "List should not be empty when deleting ingredient";
        assert name != null : "Ingredient name should not be null";
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().equalsIgnoreCase(name)) {
                ingredients.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getCounter() {
        return ingredients.size();
    }

    public void sortAlphabetically() {
        ingredients.sort(Comparator.comparing(Ingredient::getName, String.CASE_INSENSITIVE_ORDER));
    }
}
