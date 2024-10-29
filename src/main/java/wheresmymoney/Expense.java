package wheresmymoney;

import java.time.LocalDate;

import wheresmymoney.exception.WheresMyMoneyException;

import java.time.LocalDate;

/**
 * The {@code Expense} class represents an individual expense
 * with a price, description, category and date added.
 *
 * <p>
 * The class ensures that all required fields are set and validated upon creation and
 * modification to prevent invalid data entry.
 * </p>
 */
public class Expense {

    protected Float price;
    protected String description;
    protected String category;
    protected LocalDate dateAdded;

    
    public Expense(Float price, String description, String category)
            throws WheresMyMoneyException {
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
        this.dateAdded = DateUtils.getCurrentDate();
    }

    public Expense(Float price, String description, String category, LocalDate dateAdded)
            throws WheresMyMoneyException {
        if (!DateUtils.isInDateFormat(dateAdded)){
            throw new WheresMyMoneyException("Invalid date format" + DateUtils.DATE_FORMAT);
        }
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
        this.setDateAdded(DateUtils.stringToDate(dateAdded));
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

    public LocalDate getDateAdded() {
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
        } else if (description.isBlank()) {
            throw new WheresMyMoneyException("Expense's description shouldn't be blank.");
        }
        this.description = description;
        assert this.description != null : "Expense's description shouldn't be null.";
    }

    public void setCategory(String category) throws WheresMyMoneyException {
        if (category == null) {
            throw new WheresMyMoneyException("Expense's category shouldn't be null.");
        } else if (category.isBlank()) {
            throw new WheresMyMoneyException("Expense's category shouldn't be blank.");
        }
        this.category = category;
        assert this.category != null : "Expense's category shouldn't be null.";
    }

    public void setDateAdded(LocalDate dateAdded) throws WheresMyMoneyException {
        // if (dateAdded == null) {
        //     throw new WheresMyMoneyException("Expense's date added shouldn't be null.");
        // } else if (dateAdded.isBlank()) {
        //     throw new WheresMyMoneyException("Expense's date added shouldn't be blank.");
        // } else if (!DateUtils.isInDateFormat(dateAdded)) {
        //     throw new WheresMyMoneyException("Expense's date added is not in recognised format.");
        // }
        this.dateAdded = dateAdded;
        assert this.dateAdded != null : "Expense's date added shouldn't be null.";
    }
    
}
