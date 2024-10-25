package wheresmymoney;

import wheresmymoney.exception.WheresMyMoneyException;

public class Expense {
    protected Float price;
    protected String description;
    protected String category;
    protected String dateAdded;

    
    public Expense(Float price, String description, String category)
            throws WheresMyMoneyException {
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
        this.dateAdded = DateUtils.dateFormatToString(DateUtils.getCurrentDate());
    }
    public Expense(Float price, String description, String category, String dateAdded)
            throws WheresMyMoneyException {
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
        this.setDateAdded(dateAdded);
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
    public String getDateAdded() {
        return dateAdded;
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
    public void setDateAdded(String dateAdded) throws WheresMyMoneyException {
        if (dateAdded == null) {
            throw new WheresMyMoneyException("Expense's date added shouldn't be null.");
        } else if (!DateUtils.isInDateFormat(dateAdded)) {
            throw new WheresMyMoneyException("Expense's date added is not in recognised format.");
        }
        this.dateAdded = dateAdded;
        assert this.dateAdded != null : "Expense's date added shouldn't be null.";
    }

}
