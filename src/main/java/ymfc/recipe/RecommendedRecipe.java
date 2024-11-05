package ymfc.recipe;

import java.util.ArrayList;

public class RecommendedRecipe{

    private Recipe recipe;
    private int percentMatch;
    private ArrayList<String> missingIngredients;

    public RecommendedRecipe(Recipe recipe, int percentMatch, ArrayList<String> missingIngredients) {
        this.recipe = recipe;
        this.percentMatch = percentMatch;
        this.missingIngredients = missingIngredients;
    }

    public int getPercentMatch() {
        return percentMatch;
    }

    @Override
    public String toString() {
        StringBuilder recommendedRecipeDetails = new StringBuilder();
        // Show percentage of the recipe's ingredients that the user has
        recommendedRecipeDetails.append("\tFor this recipe, you have ").append(percentMatch)
                .append("% of the ingredients needed").append(System.lineSeparator());

        // Add list of missing ingredients if there exists missing ingredients
        if (!missingIngredients.isEmpty()) {
            recommendedRecipeDetails.append("\tYou are missing the following:").append(System.lineSeparator());
            for (int i = 0; i < missingIngredients.size(); i++) {
                recommendedRecipeDetails.append("\t  - ").append(missingIngredients.get(i))
                        .append(System.lineSeparator());
            }
        }

        // Show details on the recipe
        recommendedRecipeDetails.append("\t").append(recipe).append(System.lineSeparator());

        return recommendedRecipeDetails.toString();
    }
}
