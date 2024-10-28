package seedu.classes;

import seedu.exception.WiagiInvalidInputException;
import seedu.type.Income;
import seedu.type.IncomeList;
import seedu.type.Spending;
import seedu.type.SpendingList;
import seedu.type.Type;

import java.io.ByteArrayInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.classes.Constants.ALL_TIME_OPTION;
import static seedu.classes.Constants.BIWEEKLY_OPTION;
import static seedu.classes.Constants.MONTHLY_OPTION;
import static seedu.classes.Constants.TIME_RANGE_MESSAGE;
import static seedu.classes.Constants.WEEKLY_OPTION;

public class Ui {
    public static final String EMPTY_STRING = "";
    public static final String TAB = "\t";
    public static final String INCOME = "Incomes";
    public static final String SPENDING = "Spendings";
    private static Scanner scanner = new Scanner(System.in);

    public static void userInputForTest(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scanner = new Scanner(System.in);
    }

    public static String readCommand() {
        String line = scanner.nextLine().trim();
        assert line != null : "Input line is null";
        Ui.printSeparator();
        return line;
    }

    public static void printSeparator() {
        printWithTab(Constants.SEPARATOR);
    }
    public static void printWithTab(String message) {
        System.out.println(TAB + message);
    }
    public static void printWithDoubleTab(String message) {
        System.out.println(TAB+TAB + message);
    }
    public static void welcome() {
        Ui.printSeparator();
        Ui.printWithTab("Hello from");
        printFancyWiagi();
        Ui.printSeparator();
    }
    private static void printFancyWiagi() {
        Ui.printWithTab("__        __  ___      /\\       ____   ___");
        Ui.printWithTab("\\ \\      / / |_ _|    /  \\     / ___| |_ _|");
        Ui.printWithTab(" \\ \\ /\\ / /   | |    / /\\ \\   | |  _   | |");
        Ui.printWithTab("  \\ V  V /    | |   / ____ \\  | |_| |  | |");
        Ui.printWithTab("   \\_/\\_/    |___| /_/    \\_\\  \\____| |___|");
        Ui.printSeparator();
    }

    public static void printSpendingStatistics(SpendingList spendings) {
        Ui.printWithDoubleTab("Daily spendings: " + formatPrintDouble(spendings.getDailySpending()));
        Ui.printWithDoubleTab("Daily Budget: " + spendings.getDailyBudget());
        Ui.printWithDoubleTab("Daily budget left: " + formatPrintDouble(spendings.getDailyBudget() -
                spendings.getDailySpending()));
        Ui.printWithDoubleTab("Monthly spendings: " + formatPrintDouble(spendings.getMonthlySpending()));
        Ui.printWithDoubleTab("Monthly Budget: " + spendings.getMonthlyBudget());
        Ui.printWithDoubleTab("Monthly budget left: " +
                formatPrintDouble(spendings.getMonthlyBudget() - spendings.getMonthlySpending()));
        Ui.printWithDoubleTab("Yearly spendings: " + formatPrintDouble(spendings.getYearlySpending()));
        Ui.printWithDoubleTab("Yearly Budget: " + spendings.getYearlyBudget());
        Ui.printWithDoubleTab("Yearly budget left: " + formatPrintDouble(spendings.getYearlyBudget() -
                spendings.getYearlySpending()));
    }

    public static <T extends Type> void printArrList(ArrayList<T> arrList) {
        String typeOfList;
        if (arrList instanceof SpendingList) {
            typeOfList = SPENDING;
        } else {
            typeOfList = INCOME;
        }
        Ui.printWithTab(typeOfList);
        Ui.printWithTab("Total " + typeOfList.toLowerCase() + ": " + printList(arrList));

    }

    /**
     * Prints the elements of the given ArrayList and calculates the sum of their amounts.
     *
     * @param <T>     The type of elements in the ArrayList, which must extend the Type class.
     * @param arrList The ArrayList containing elements to be printed and summed.
     * @return The sum of the amounts of the elements in the ArrayList as a String.
     */
    public static <T> String printList(ArrayList<T> arrList) {
        double sumOfAmountInList = 0;
        for (int indexInList = 0; indexInList < arrList.size(); indexInList++) {
            assert arrList != null : "ArrayList is null";
            int indexToUser = indexInList + 1;
            sumOfAmountInList += ((Type) arrList.get(indexInList)).getAmount();
            Ui.printWithTab(indexToUser + ". " + arrList.get(indexInList));
        }
        return formatPrintDouble(sumOfAmountInList);
    }

    private static String formatPrintDouble(double sum) {
        if (sum % 1 == 0) {
            return String.valueOf((int) sum);
        }
        return String.valueOf(sum);
    }

    //@@author wongwh2002
    public static void printSpecificTag(IncomeList incomes, SpendingList spendings, String tagName) {
        StringBuilder sbIncome = new StringBuilder();
        StringBuilder sbSpending = new StringBuilder();
        assert tagName != null && !tagName.isEmpty() : "Tag is null or empty";

        int incomeCount = getTagsCount(incomes, tagName, sbIncome, INCOME);
        int spendingCount = getTagsCount(spendings, tagName, sbSpending, SPENDING);
        int tagCount = incomeCount + spendingCount;

        if (tagCount == 0) {
            throw new WiagiInvalidInputException("No entries with tag: " + tagName + ". Please input tags first!");
        }
        assert tagCount > 0 : "No entries with tag: " + tagName;
        assert incomeCount > 0 || spendingCount > 0 : "No entries with tag: " + tagName;

        Ui.printWithTab("Tag: " + tagName);
        if (incomeCount > 0) {
            Ui.printWithTab(sbIncome.toString().trim());
        }
        if (spendingCount > 0) {
            Ui.printWithTab(sbSpending.toString().trim());
        }
    }

    //@@author wongwh2002
    private static <T extends Type> int getTagsCount(ArrayList<T> arrList, String tag,
                                                     StringBuilder sb, String listName) {
        sb.append(listName).append(System.lineSeparator());
        int tagsCount = 0;
        int sizeOfArray = arrList.size();
        for (int indexInList = 0; indexInList < sizeOfArray; indexInList++) {
            Type entry = arrList.get(indexInList);
            if (entry.getTag().equals(tag)) {
                tagsCount++;
                int indexToUser = indexInList + 1;
                sb.append(TAB).append(indexToUser).append(". ")
                        .append(entry).append(System.lineSeparator());
            }
        }
        return tagsCount;
    }

    //@@author wongwh2002
    public static void printAllTags(IncomeList incomes, SpendingList spendings) {
        ArrayList<String> tags = getStrings(incomes, spendings);
        tags.sort(String::compareTo);
        if (tags.isEmpty()) {
            throw new WiagiInvalidInputException("No tags found. Please input more tags!");
        }
        assert tags != null : "Tags list is null";
        Ui.printWithTab("Tags");
        for (int indexInList = 0; indexInList < tags.size(); indexInList++) {
            int indexToUser = indexInList + 1;
            Ui.printWithTab(indexToUser + ". " + tags.get(indexInList));
        }
    }

    //@@author wongwh2002
    private static ArrayList<String> getStrings(IncomeList incomes, SpendingList spendings) {
        ArrayList<String> tags = new ArrayList<>();
        tags.add(EMPTY_STRING);
        assert tags != null : "Tags list is null";
        for (Income income : incomes) {
            String tag = income.getTag();
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        for (Spending spending : spendings) {
            String tag = spending.getTag();
            if (!tags.contains(tag)) {
                tags.add(tag);
            }
        }
        tags.remove(EMPTY_STRING);
        return tags;
    }

    //@@author wx-03
    public static <T extends Type> void printWeekly(ArrayList<T> arrList) {
        ArrayList<T> filteredList = new ArrayList<>();
        LocalDate currDate = LocalDate.now();
        LocalDate monday = getMondayDate(currDate);
        LocalDate sunday = getSundayDate(currDate);
        for (T entry : arrList) {
            LocalDate entryDate = entry.getDate();
            if (isInRange(entryDate, monday, sunday)) {
                filteredList.add(entry);
            }
        }
        printList(filteredList);
    }

    public static <T extends Type> void printMonthly(ArrayList<T> arrList) {
        ArrayList<T> filteredList = new ArrayList<>();
        LocalDate currDate = LocalDate.now();
        LocalDate monthStart = LocalDate.of(currDate.getYear(), currDate.getMonth(), 1);
        LocalDate monthEnd = monthStart.plusDays(currDate.getMonth().length(currDate.isLeapYear()) - 1);
        for (T entry : arrList) {
            if (isInRange(entry.getDate(), monthStart, monthEnd)) {
                filteredList.add(entry);
            }
        }
        printList(filteredList);
    }

    public static <T extends Type> void printBiweekly(ArrayList<T> arrList) {
        ArrayList<T> filteredList = new ArrayList<>();
        LocalDate currDate = LocalDate.now();
        LocalDate start = getMondayDate(currDate.minusDays(7));
        LocalDate end = getSundayDate(currDate);
        for (T entry : arrList) {
            LocalDate entryDate = entry.getDate();
            if (isInRange(entryDate, start, end)) {
                filteredList.add(entry);
            }
        }
        printList(filteredList);
    }

    //@@author wx-03
    public static <T extends Type> boolean printListOfTimeRange(ArrayList<T> arrList) {
        while (true) {
            Ui.printWithTab(TIME_RANGE_MESSAGE);
            String userInput = Ui.readCommand();
            switch (userInput) {
            case ALL_TIME_OPTION:
                return true;
            case WEEKLY_OPTION:
                Ui.printWeekly(arrList);
                return false;
            case BIWEEKLY_OPTION:
                Ui.printBiweekly(arrList);
                return false;
            case MONTHLY_OPTION:
                Ui.printMonthly(arrList);
                return false;
            default:
                Ui.printWithTab("Invalid input");
            }
        }
    }

    public static void printStatisticsIfRequired(SpendingList spendings) {
        Ui.printWithTab("List all statistics? [Y/N]:");
        while (true) {
            String userInput = Ui.readCommand().toLowerCase();
            switch (userInput) {
            case "y":
                Ui.printArrList(spendings);
                Ui.printSpendingStatistics(spendings);
                return;
            case "n":
                Ui.printArrList(spendings);
                return;
            default:
                Ui.printWithTab("Invalid input. [Y/N].");
            }
        }
    }

    private static LocalDate getMondayDate(LocalDate currDate) {
        while (currDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            currDate = currDate.minusDays(1);
        }
        return currDate;
    }

    private static LocalDate getSundayDate(LocalDate currDate) {
        while (currDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
            currDate = currDate.plusDays(1);
        }
        return currDate;
    }

    private static boolean isInRange(LocalDate date, LocalDate start, LocalDate end) {
        return (date.isAfter(start) || date.isEqual(start))
                && (date.isBefore(end) || date.isEqual(end));
    }

}
