package seedu.budgetbuddy.transaction.income;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncomeManagerTest {

    @BeforeEach
    void resetIncomeManager() {
        IncomeManager.reset(); // Ensure a clean state before each test
    }

    @Test
    void addIncome_validIncome_incomeAddedSuccessfully() {
        Income income = new Income("Salary", 5000, LocalDate.of(2024, 10, 1));
        IncomeManager.addIncome(income);

        assertEquals(1, IncomeManager.getNumberOfIncomes());
        assertEquals(income, IncomeManager.getIncomes().get(0));
    }

    @Test
    void deleteIncome_validIndex_incomeDeletedSuccessfully() {
        Income income = new Income("Salary", 5000, LocalDate.of(2024, 10, 1));
        IncomeManager.addIncome(income);

        IncomeManager.deleteIncome(0);

        assertEquals(0, IncomeManager.getNumberOfIncomes());
        assertTrue(IncomeManager.getIncomes().isEmpty());
    }

    @Test
    void getYearMonthFromDate_validDate_returnsCorrectYearMonth() {
        LocalDate date = LocalDate.of(2024, 10, 1);
        YearMonth expected = YearMonth.of(2024, 10);
        assertEquals(expected, IncomeManager.getYearMonthFromDate(date));
    }
}

