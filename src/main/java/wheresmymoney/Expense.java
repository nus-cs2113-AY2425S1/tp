package wheresmymoney;

public class Expense {
    protected Float price;
    protected String description;
    protected String category;


    public Expense(Float price, String description, String category) {
        this.price = price;
        this.description = description;
        this.category = category;
    }

    
    public Float getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public void setPrice(Float price) throws WheresMyMoneyException {
        if (price == null) {
            throw new WheresMyMoneyException("Price shouldn't be null.");
        }
        this.price = price;
        assert this.price != null : "Expense's price is null.";
    }
    public void setDescription(String description) throws WheresMyMoneyException {
        if (description == null) {
            throw new WheresMyMoneyException("Description shouldn't be null.");
        }
        this.description = description;
        assert this.description != null : "Expense's description is null.";
    }
    public void setCategory(String category) throws WheresMyMoneyException {
        if (category == null) {
            throw new WheresMyMoneyException("Category shouldn't be null.");
        }
        this.category = category;
        assert this.category != null : "Expense's category is null.";
    }

}
