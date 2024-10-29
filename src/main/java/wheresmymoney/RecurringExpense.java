package wheresmymoney;

import java.time.LocalDate;

import wheresmymoney.exception.WheresMyMoneyException;

public class RecurringExpense extends Expense {
    protected String frequency;

    public RecurringExpense(Float price, String description, String category, String frequency) 
            throws WheresMyMoneyException {
        super(price, description, category);
        this.frequency = frequency;
    }

    public RecurringExpense(Float price, String description, String category, 
            LocalDate lastAddedDate, String frequency) throws WheresMyMoneyException{
        super(price, description, category, lastAddedDate);
        this.frequency = frequency;
    }

    public LocalDate getLastAddedDate() {
        return this.dateAdded;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setLastAddedDate(LocalDate date) {
        this.dateAdded = date;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
