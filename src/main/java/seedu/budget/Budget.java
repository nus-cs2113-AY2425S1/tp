package seedu.budget;

import java.time.YearMonth;

public class SpendingLimit {
    protected double limit;
    protected YearMonth month;


    public SpendingLimit(double limit, String month) throws Exception {
        this.limit = limit;
        this.month = YearMonth.parse(month);
    }

    public YearMonth getMonth() {
        return month;
    }

    public double getLimit() {
        return limit;
    }

}
