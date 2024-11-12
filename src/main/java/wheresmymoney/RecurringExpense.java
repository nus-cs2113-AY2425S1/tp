package wheresmymoney;

import wheresmymoney.exception.InvalidInputException;
import wheresmymoney.exception.WheresMyMoneyException;

//@@author

public class RecurringExpense extends Expense {
    protected String frequency;
    private String lastDateAdded;

    public RecurringExpense(Float price, String description, String category, String frequency) 
            throws WheresMyMoneyException {
        super(price, description, category);
        setLastAddedDate(DateUtils.dateFormatToString(DateUtils.getCurrentDate()));
        setFrequency(frequency);
    }

    public RecurringExpense(Float price, String description, String category, 
            String lastAddedDate, String frequency) throws WheresMyMoneyException{
        super(price, description, category, lastAddedDate);
        setLastAddedDate(lastAddedDate);
        setFrequency(frequency);
    }

    public String getLastAddedDate() {
        return this.lastDateAdded;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setLastAddedDate(String date) throws WheresMyMoneyException {
        if (date == null) {
            throw new InvalidInputException("Date should not be null");
        } else if (!DateUtils.isInDateFormat(date)) {
            throw new InvalidInputException("Invalid date format, please follow " + DateUtils.DATE_FORMAT);
        }
        this.lastDateAdded = date;
    }

    public void setFrequency(String frequency) throws WheresMyMoneyException {
        if (frequency == null || frequency.isEmpty()) {
            throw new InvalidInputException("Frequency should not be empty/ null");
        } else if (!frequency.equals("daily") && !frequency.equals("weekly") && !frequency.equals("monthly")) {
            throw new InvalidInputException("Frequency inputted is not \"daily\", \"weekly\" or \"monthly\"");
        }
        this.frequency = frequency;
    }
}
