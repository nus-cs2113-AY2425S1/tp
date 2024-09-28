package wheresmymoney;

public class Expense {
    protected float price;
    protected String description;
    protected String category;


    public Expense(float price, String description, String category) {
        this.price = price;
        this.description = description;
        this.category = category;
    }

    
    public float getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategory(String category) {
        this.category = category;
    }

}
