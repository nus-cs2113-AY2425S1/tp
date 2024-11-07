package wheresmymoney;

import wheresmymoney.exception.WheresMyMoneyException;

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
            throw new WheresMyMoneyException("Date should not be null");
        } else if (!DateUtils.isInDateFormat(date)) {
            throw new WheresMyMoneyException("Invalid date format" + DateUtils.DATE_FORMAT);
        }
        this.lastDateAdded = date;
    }

    public void setFrequency(String frequency) throws WheresMyMoneyException {
        if (frequency == null || frequency.isEmpty()) {
            throw new WheresMyMoneyException("Frequency should not be null");
        } else if (!frequency.equals("daily") && !frequency.equals("weekly") && !frequency.equals("monthly")) {
            throw new WheresMyMoneyException("Frequency inputted is not \"daily\", \"weekly\" or \"monthly\"");
        }
        this.frequency = frequency;
    }
}
