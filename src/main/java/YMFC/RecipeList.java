package YMFC;

import java.util.ArrayList;

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
        recipes.remove(id);
        counter--;
    }

    public int getCounter() {
        return counter;
    }

}
