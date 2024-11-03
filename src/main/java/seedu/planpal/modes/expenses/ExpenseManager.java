package seedu.planpal.modes.expenses;


import seedu.planpal.exceptions.EmptyDescriptionException;
import seedu.planpal.exceptions.PlanPalExceptions;
import seedu.planpal.exceptions.expenses.InvalidBudgetException;
import seedu.planpal.exceptions.expenses.NegativeBudgetException;
import seedu.planpal.exceptions.expenses.NoBudgetException;
import seedu.planpal.utility.ListFunctions;
import seedu.planpal.utility.Ui;
import seedu.planpal.utility.filemanager.FileManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseManager implements ListFunctions {
    private static final int BUDGET_SEGMENT = 2;
    private static final String MONTH_SEPARATOR = "budget_";
    private static final String TXT_SEPARATOR = ".txt";
    FileManager savedExpenses = new FileManager();
    private Map<String, ArrayList<Expense>> monthlyExpenses = new HashMap<>();
    private Map<String, String> monthlyBudget = new HashMap<>();
    private ArrayList<Expense> overallExpenseList = new ArrayList<>();
    private String budget;

    private String getCurrentMonth(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }

    public ArrayList<Expense> getOverallExpenseList() {
        return overallExpenseList;
    }

    public ArrayList<Expense> getMonthlyExpenses(String month) {
        return monthlyExpenses.getOrDefault(month, new ArrayList<>());
    }

    public ArrayList<Expense> getMonthlyExpenses() {
        return getMonthlyExpenses(getCurrentMonth());
    }

    public void printExceededBudgetMessage(){
        double budgetValue = Double.parseDouble(getBudget());
        if (budgetValue < getTotalCost()){
            System.out.println("It's time to readjust your spending habits!");
        }
    }

    public void printExceededBudgetMessage(String month){
        double budgetValue = Double.parseDouble(monthlyBudget.get(month));
        if (budgetValue < getTotalCost()){
            System.out.println("It's time to readjust your spending habits!");
        }
    }

    public void addExpense(String description) throws PlanPalExceptions {
        if (description.isEmpty()){
            throw new EmptyDescriptionException();
        }

        Expense newExpense = new Expense(description);
        String targetMonth = newExpense.getMonth();
        if (!monthlyBudget.containsKey(targetMonth) || monthlyBudget.get(targetMonth).equals("0")){
            throw new NoBudgetException();
        }

        monthlyExpenses.putIfAbsent(targetMonth, new ArrayList<>());
        addToList(monthlyExpenses.get(targetMonth), newExpense);
        printExceededBudgetMessage(targetMonth);
        savedExpenses.saveList(monthlyExpenses.get(targetMonth));
    }

    public void viewExpenseList(String month){
        ArrayList<Expense> expenseList = monthlyExpenses.get(month);
        monthlyExpenses.putIfAbsent(month, new ArrayList<>());
        viewList(expenseList);
        double budgetValue = Double.parseDouble(monthlyBudget.get(month));
        System.out.println("For the month of " + month);
        System.out.println("    Total budget: $" + monthlyBudget.get(month));
        System.out.println("    Total cost: $" + getTotalCost(month));
        System.out.println("    Remaining budget: $" + (budgetValue - getTotalCost(month)));
        Ui.printLine();
    }

    public void viewExpenseList(){
        viewExpenseList(getCurrentMonth());
    }

    public double getTotalCost(String month){
        ArrayList<Expense> expenseList = monthlyExpenses.get(month);
        double totalCost = 0.0;
        for (Expense expense : expenseList){
            String costInString = expense.getCost();
            if (costInString == null){
                costInString = "0";
            }
            totalCost += Double.parseDouble(costInString);
        }
        return totalCost;
    }

    public double getTotalCost(){
        return getTotalCost(getCurrentMonth());
    }

    public void editExpense(String query, String month) throws PlanPalExceptions {
        monthlyExpenses.putIfAbsent(month, new ArrayList<>());
        editList(monthlyExpenses.get(month), query);
        printExceededBudgetMessage(month);
        savedExpenses.saveList(monthlyExpenses.get(month));
    }

    public void editExpense(String query) throws PlanPalExceptions {
        editExpense(query, getCurrentMonth());
    }

    public void deleteExpense(String index, String month) throws PlanPalExceptions {
        monthlyExpenses.putIfAbsent(month, new ArrayList<>());
        if (index.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        assert index.length() != 0 : "Input must not be empty";

        try {

            boolean hasTwoBeforeDelete = (monthlyExpenses.get(month).size() == 2);
            deleteList(monthlyExpenses.get(month), index);
            savedExpenses.saveList(monthlyExpenses.get(month), hasTwoBeforeDelete);
        } catch (PlanPalExceptions e) {
            System.out.println ("Failed to delete an expense: " + e.getMessage());
            throw e;
        }
    }

    public void deleteExpense(String index) throws PlanPalExceptions {
        deleteExpense(index, getCurrentMonth());
    }

    public void findExpense(String description, String month) throws PlanPalExceptions {
        monthlyExpenses.putIfAbsent(month, new ArrayList<>());
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }

        assert description != null : "Description must not be null";
        assert !description.isEmpty() : "Description must not be empty";

        try {
            findInList(monthlyExpenses.get(month), description);
        } catch (PlanPalExceptions e) {
            throw e;
        }
    }

    public void findExpense(String description) throws PlanPalExceptions {
        findExpense(description, getCurrentMonth());
    }

    public void setBudget(String budget, String month, boolean isDefault) throws PlanPalExceptions{
        try {
            String targetMonth;
            if (month == null) {
                targetMonth = getCurrentMonth();
            } else {
                targetMonth = month;
            }

            double budgetValue = Double.parseDouble(budget);
            if (budgetValue < 0){
                throw new NegativeBudgetException();
            }

            monthlyBudget.put(targetMonth, budget);
            savedExpenses.saveValue("budgets/budget_" + targetMonth + ".txt", budget);
            if (isDefault) {
                Ui.print("For the month of " + targetMonth,
                        "Budget has been set to: $" + monthlyBudget.get(targetMonth));
            }

        } catch (NumberFormatException e) {
            throw new InvalidBudgetException();
        }
    }

    // Overload setBudget function
    public void setBudget(String budget, String month) throws PlanPalExceptions{
        setBudget(budget, month, true);
    }

    // Overload setBudget function
    public void setBudget(String budget) throws PlanPalExceptions{
        setBudget(budget, null, true);
    }

    public void setAllBudget(ArrayList<String> budgetList) throws PlanPalExceptions{
        try {
            for (String budget : budgetList){
                String[] budgetParts = budget.split(":", BUDGET_SEGMENT);
                String fileName = budgetParts[0].trim();
                String month = fileName.replace(MONTH_SEPARATOR,"").replace(TXT_SEPARATOR, "").trim();
                budget = budgetParts[1].trim();
                setBudget(budget, month, false);
            }
        } catch (Exception e) {
            throw new PlanPalExceptions(e.getMessage());
        }
    }

    public String getBudget(String month){
        return monthlyBudget.get(month);
    }

    public String getBudget() {
        return getBudget(getCurrentMonth());
    }
}
