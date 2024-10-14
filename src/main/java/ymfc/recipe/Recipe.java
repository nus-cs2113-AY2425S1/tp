package ymfc.recipe;

import java.util.ArrayList;

public class Recipe {

    public String name;
    public ArrayList<String> ingredients;
    public ArrayList<String> steps;

    public Recipe(String name, ArrayList<String> ingredients, ArrayList<String> steps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public boolean equals(Recipe o) {
        if (!name.equals(o.getName())) {
            return false;
        }

        if (!ingredients.equals(o.getIngredients())) {
            return false;
        }

        if (!steps.equals(o.getSteps())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder recipeDetails = new StringBuilder();
        recipeDetails.append("Recipe: ").append(name).append(System.lineSeparator());

        recipeDetails.append("  Ingredients: ");
        for (String ingredient : ingredients) {
            recipeDetails.append(System.lineSeparator()).append("    - ").append(ingredient);
        }

        recipeDetails.append(System.lineSeparator()).append("  Steps: ");
        for (int i = 0; i < steps.size(); i++) {
            recipeDetails.append(System.lineSeparator()).append("    ").append(i + 1).append(". ").append(steps.get(i));
        }

        return recipeDetails.toString();
    }

    public String toSaveString() {
        StringBuilder recipeDetails = new StringBuilder();
        recipeDetails.append("add n/").append(name).append(" ");

        for (int i = 0; i < ingredients.size(); i++) {
            recipeDetails.append("i/").append(ingredients.get(i)).append(" ");
        }

        for (int i = 0; i < steps.size(); i++) {
            recipeDetails.append("s").append(i+1).append("/").append(steps.get(i)).append(" ");
        }

        return recipeDetails.toString();
    }
}
