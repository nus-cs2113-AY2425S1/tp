package ymfc.ingredient;

public class Ingredient {
    String name;
    //int quantity;

    public Ingredient(String name) {
        this.name = name;
        //this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Ingredient o) {
        if (!name.equals(o.getName())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toSaveString() {

        String ingredientDetails = "new n/" + name;

        return ingredientDetails;
    }
}
