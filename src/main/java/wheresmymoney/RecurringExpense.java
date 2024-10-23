package wheresmymoney;

public class RecurringExpense extends Expense {
    protected String lastAddedDate;
    protected String frequency;

    public RecurringExpense(Float price, String description, String category, 
            String lastAddedDate, String frequency) {
        super(price, description, category);
        this.lastAddedDate = lastAddedDate;
        this.frequency = frequency;
    }

    public String getlastAddedDate() {
        return this.lastAddedDate;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setlastAddedDate(String date) {
        this.lastAddedDate = date;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
