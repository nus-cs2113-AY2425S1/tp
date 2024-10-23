package wheresmymoney;

public class RecurringExpense extends Expense {
    protected String lastUpdated;
    protected String frequency;

    public RecurringExpense(Float price, String description, String category, 
            String lastUpdated, String frequency) {
        super(price, description, category);
        this.lastUpdated = lastUpdated;
        this.frequency = frequency;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setLastUpdated(String date) {
        this.lastUpdated = date;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
