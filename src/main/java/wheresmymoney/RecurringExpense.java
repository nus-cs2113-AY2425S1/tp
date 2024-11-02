package wheresmymoney;

import wheresmymoney.exception.WheresMyMoneyException;

public class RecurringExpense extends Expense {
    protected String frequency;
    private String lastDateAdded;

    public RecurringExpense(Float price, String description, String category, String frequency) 
            throws WheresMyMoneyException {
        super(price, description, category);
        this.lastDateAdded = DateUtils.dateFormatToString(DateUtils.getCurrentDate());
        this.frequency = frequency;
    }

    public RecurringExpense(Float price, String description, String category, 
            String lastAddedDate, String frequency) throws WheresMyMoneyException{
        super(price, description, category, lastAddedDate);
        this.lastDateAdded = lastAddedDate;
        this.frequency = frequency;
    }

    public String getlastAddedDate() {
        return this.lastDateAdded;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setlastAddedDate(String date) {
        this.lastDateAdded = date;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
