package wheresmymoney;

/**
 * The {@code CategoryData} class represents the expenditure data for a specific category,
 * consisting of the current total spent in that category
 * and a spending limit for that category.
 */
public class CategoryData {
    private Float currExpenditure;
    private Float maxExpenditure;
    
    /**
     * Constructs a {@code CategoryData} object with a specified current expenditure
     * and a default spending limit of $100.00.
     *
     * @param currExpenditure The current expenditure for this category.
     */
    public CategoryData(Float currExpenditure) {
        this.currExpenditure = currExpenditure;
        this.maxExpenditure = 100F;
    }
    /**
     * Constructs a {@code CategoryData} object with a specified current expenditure
     * and a specified spending limit
     *
     * @param currExpenditure The current expenditure for this category.
     * @param maxExpenditure  The spending limit for this category.
     */
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
    
    /**
     * Increases the expenditure for this category by a specified amount.
     *
     * @param price The amount to add to the current expenditure.
     */
    public void increaseCurrExpenditureBy(Float price) {
        this.currExpenditure += price;
    }
    /**
     * Decreases the expenditure for this category by a specified amount.
     *
     * @param price The amount to subtract from the current expenditure.
     */
    public void decreaseCurrExpenditureBy(Float price) {
        this.currExpenditure -= price;
    }
    /**
     * Checks if the expenditure for this category is nearing the spending limit.
     * Nearing the limit is defined as exceeding 80% of the maximum expenditure.
     *
     * @return {@code true} if the current expenditure is at least 80% of the spending limit,
     *         {@code false} otherwise.
     */
    public boolean isNearingLimit() {
        return 0.8 * maxExpenditure <= currExpenditure;
    }
    /**
     * Checks if the expenditure for this category has exceeded the spending limit.
     *
     * @return {@code true} if the current expenditure exceeds the spending limit,
     *         {@code false} otherwise.
     */
    public boolean hasExceededLimit() {
        return currExpenditure > maxExpenditure;
    }
}
