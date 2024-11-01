package wheresmymoney;

/**
 * The {@code CategoryData} class represents the expenditure data for a specific category,
 * consisting of:
 * <li> The current total spent in that category </li>
 * <li> The spending limit for that category </li>
 */
public class CategoryData {
    private Float currExpenditure;
    private Float maxExpenditure;
    
    /**
     * Constructs a {@code CategoryData} object with a specified current expenditure
     * and a default spending limit of $100.00.
     *
     * @param currExpenditure The current expenditure for this category.
     * @throws WheresMyMoneyException If currExpenditure is null.
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
     * @throws WheresMyMoneyException If currExpenditure or maxExpenditure is null.
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
     * Increases the current total expenditure for this category by a specified amount.
     *
     * @param price The amount to add to the current total expenditure.
     * @throws WheresMyMoneyException If the price is null.
     */
    public void increaseCurrExpenditureBy(Float price) throws WheresMyMoneyException {
        if (price == null) {
            throw new WheresMyMoneyException("Price to update current total expenditure by should not be null.");
        }
        this.currExpenditure += price;
    }
    /**
     * Decreases the current total expenditure for this category by a specified amount.
     *
     * @param price The amount to subtract from the current total expenditure.
     * @throws WheresMyMoneyException If the price is null.
     */
    public void decreaseCurrExpenditureBy(Float price) throws WheresMyMoneyException {
        if (price == null) {
            throw new WheresMyMoneyException("Price to update current total expenditure by should not be null.");
        }
        this.currExpenditure -= price;
    }
    /**
     * Checks if the expenditure for this category is nearing its spending limit.
     * Nearing the limit is defined as the current total expenditure exceeding
     * 80% of its spending limit.
     *
     * @return {@code true} if the current total expenditure is at least 80% of the spending limit,
     *         {@code false} otherwise.
     */
    public boolean isNearingLimit() {
        return 0.8 * maxExpenditure <= currExpenditure;
    }
    /**
     * Checks if the current total expenditure for this category has exceeded its spending limit.
     *
     * @return {@code true} if the current expenditure exceeds the spending limit,
     *         {@code false} otherwise.
     */
    public boolean hasExceededLimit() {
        return currExpenditure > maxExpenditure;
    }
}
