package ymfc.recipe;

public class Ingredient {
    String name;
    int quantity;

    public Ingredient(String name) {
        this.name = name;
        this.quantity = 1;
    }

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeQuantity(int change) {
        this.quantity += change;
    }

    public boolean equals(Ingredient o) {
        if (!name.equals(o.getName())) {
            return false;
        }

        if (this.quantity != o.getQuantity()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name + " (" + quantity + ")";
    }

    public String toSaveString() {

        String ingredientDetails = "add n/" + name + " " +
                "q/" + quantity + " ";

        return ingredientDetails;
    }
}
