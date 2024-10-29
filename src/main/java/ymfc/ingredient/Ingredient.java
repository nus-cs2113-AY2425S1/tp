package ymfc.ingredient;

public class Ingredient {
    private String name;
    //int quantity;

    public Ingredient(String name) {
        this.name = name;
        //this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Ingredient ingredientToCheck) {
        return name.equals(ingredientToCheck.getName());
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns a string in the proper format to be written to the ingredients.txt save file
     *
     * @return String to be written to the save file
     */
    public String toSaveString() {

        String ingredientDetails = "new n/" + name;

        return ingredientDetails;
    }
}
