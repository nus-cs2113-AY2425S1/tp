package ymfc.recipe;

import java.util.ArrayList;

public class Recipe {

    public String name;
    public ArrayList<String> ingredients;
    public ArrayList<String> steps;

    // Optional attributes
    public String cuisine;
    public Integer timeTaken;

    public Recipe(String name, ArrayList<String> ingredients, ArrayList<String> steps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = null;
        this.timeTaken = null;
    }

    public Recipe(String name, ArrayList<String> ingredients, ArrayList<String> steps,
                  String cuisine, Integer timeTaken) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = cuisine;
        this.timeTaken = timeTaken;
    }

    public Recipe(String name, ArrayList<String> ingredients, ArrayList<String> steps, String cuisine) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = cuisine;
        this.timeTaken = null;
    }

    public Recipe(String name, ArrayList<String> ingredients, ArrayList<String> steps, Integer timeTaken) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.cuisine = null;
        this.timeTaken = timeTaken;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
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

        if (cuisine != null) {
            recipeDetails.append(System.lineSeparator()).append("  Cuisine: ").append(cuisine);
        }

        if (timeTaken != null) {
            recipeDetails.append(System.lineSeparator()).append("  Time taken: ").append(timeTaken);
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

        if (cuisine != null) {
            recipeDetails.append("c/").append(cuisine).append(" ");
        }

        if (timeTaken != null) {
            recipeDetails.append("t/").append(timeTaken).append(" ");
        }

        return recipeDetails.toString();
    }
}
