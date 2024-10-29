package wheresmymoney;

public class CategoryData {
    private Float currExpenditure;
    private Float maxExpenditure;
    
    public CategoryData(Float currExpenditure) {
        this.currExpenditure = currExpenditure;
        this.maxExpenditure = 100F; // default dummy limit
    }
    public CategoryData(Float currExpenditure, Float maxExpenditure) {
        this.currExpenditure = currExpenditure;
        this.maxExpenditure = maxExpenditure;
    }
    
    public Float getCurrExpenditure() {
        return currExpenditure;
    }
    public Float getMaxExpenditure() {
        return maxExpenditure;
    }
    public void setCurrExpenditure(Float currExpenditure) {
        this.currExpenditure = currExpenditure;
    }
    public void setMaxExpenditure(Float maxExpenditure) {
        this.maxExpenditure = maxExpenditure;
    }
    
    public void increaseCurrExpenditureBy(Float price) {
        this.currExpenditure += price;
    }
    public void decreaseCurrExpenditureBy(Float price) {
        this.currExpenditure -= price;
    }
    public boolean isNearingLimit() {
        return maxExpenditure - currExpenditure <= 100; // dummy alert
    }
    public boolean hasExceededLimit() {
        return currExpenditure > maxExpenditure;
    }
}
