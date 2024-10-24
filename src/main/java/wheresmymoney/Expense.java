package wheresmymoney;

import wheresmymoney.exception.WheresMyMoneyException;

public class Expense {
    protected Float price;
    protected String description;
    protected String category;


    public Expense(Float price, String description, String category) {
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Expense() {}

    
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
            throw new WheresMyMoneyException("Expense's price shouldn't be null.");
        }
        this.price = price;
        assert this.price != null : "Expense's price shouldn't be null.";
    }
    public void setDescription(String description) throws WheresMyMoneyException {
        if (description == null) {
            throw new WheresMyMoneyException("Expense's description shouldn't be null.");
        }
        this.description = description;
        assert this.description != null : "Expense's description shouldn't be null.";
    }
    public void setCategory(String category) throws WheresMyMoneyException {
        if (category == null) {
            throw new WheresMyMoneyException("Expense's category shouldn't be null.");
        }
        this.category = category;
        assert this.category != null : "Expense's category shouldn't be null.";
    }

}
