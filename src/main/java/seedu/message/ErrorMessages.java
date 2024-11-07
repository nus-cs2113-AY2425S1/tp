package seedu.message;

public class ErrorMessages {
    public static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error: ";
    public static final String LACK_ARGUMENTS_ERROR_MESSAGE = "Lack mandatory arguments.";
    public static final String INDEX_OUT_OF_BOUNDS = "Your index is out of bound! Current list size: ";
    public static final String INVALID_NUMBER_FORMAT = "Your index has invalid number format.";
    public static final String CATEGORY_NOT_FOUND = "Can not find the given category in the category list";
    public static final String BUDGET_NOT_FOUND = "Missing budget for given month";
    public static final String NEGATIVE_BUDGET_AMOUNT = "Budget amount cannot be negative";
    // Update expense category
    public static final String NOT_AN_EXPENSE = "Your transaction is not an expense.";

    // Add category
    public static final String DUPLICATED_CATEGORY = "Duplicated category.";

    // Type adapter
    public static final String UNKNOWN_SUBTYPE_SERIALIZATION = "Cannot serialize subtype of %s; " +
            "did you forget to register a subtype?";
    public static final String UNKNOWN_TYPE_LABEL = "Unknown type label: %s";
    public static final String MISSING_TYPE_INFORMATION = "Cannot deserialize without type information";

    // Storage
    public static final String ERROR_LOADING_TRANSACTIONS = "Error loading transactions: %s";
    public static final String INVALID_JSON_TRANSACTIONS = "Invalid JSON format in transactions file. Re-initializing.";
    public static final String ERROR_DESERIALIZING_TRANSACTIONS = "Error deserializing transactions: %s";
    public static final String ERROR_SAVING_TRANSACTIONS = "Error saving transactions: %s";

    public static final String ERROR_LOADING_CATEGORIES = "Error loading categories: %s";
    public static final String ERROR_DESERIALIZING_CATEGORIES = "Error deserializing categories: %s";
    public static final String ERROR_SAVING_CATEGORIES = "Error saving categories: %s";
    public static final String ERROR_LOADING_BUDGETS = "Error loading budgets: %s";
    public static final String ERROR_DESERIALIZING_BUDGETS = "Error deserializing budgets: %s";
    public static final String ERROR_SAVING_BUDGETS = "Error saving budgets: %s";

    // DateTime
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Your date and/or time is invalid!";
    public static final String MESSAGE_INVALID_YEAR_MONTH_FORMAT = "Your year and month format is invalid!";
    public static final String MESSAGE_PAST_MONTH_BUDGET = "Budget can only be set for the current or future months.";
    public static final String MESSAGE_TRACK_FUTURE_MONTH_BUDGET =
            "Progress can only be checked for current or past months.";

    // Amount
    public static final String INVALID_AMOUNT_FORMAT = "Invalid amount format: ";



}
