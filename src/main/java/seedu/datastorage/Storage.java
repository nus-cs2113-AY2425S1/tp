package seedu.datastorage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import seedu.category.Category;
import seedu.main.UI;
import seedu.message.ErrorMessages;
import seedu.message.InfoMessages;
import seedu.transaction.Transaction;
import seedu.transaction.Expense;
import seedu.transaction.Income;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Handles the loading and saving of transactions and categories to and from JSON files.
 * Provides methods to load and save both transactions and categories using Gson serialization.
 */
public class Storage {

    private static final String TRANSACTIONS_PATH = "transactions.json";
    private static final String CATEGORIES_PATH = "categories.json";

    private static final Gson gson;
    private static final UI ui = new UI();

    static {
        // Register the RuntimeTypeAdapterFactory for polymorphic types
        RuntimeTypeAdapterFactory<Transaction> transactionAdapterFactory = RuntimeTypeAdapterFactory
                .of(Transaction.class, "type")
                .registerSubtype(Expense.class, "expense")
                .registerSubtype(Income.class, "income");

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(transactionAdapterFactory)
                .setPrettyPrinting()
                .create();
    }

    /**
     * Loads transactions from the transactions JSON file.
     * If the file does not exist, creates an empty transactions file.
     *
     * @return A list of transactions loaded from the file, or an empty list if no transactions are found.
     */
    public static ArrayList<Transaction> loadTransactions() {
        File transactionsFile = new File(TRANSACTIONS_PATH);

        if (!transactionsFile.exists()) {
            ui.printMessage(InfoMessages.NO_TRANSACTION_FILE_FOUND);
            saveTransaction(new ArrayList<>()); // Create an empty transactions file
        }

        try (FileReader reader = new FileReader(TRANSACTIONS_PATH)) {
            Type listType = new TypeToken<ArrayList<Transaction>>() {}.getType();
            ArrayList<Transaction> transactions = gson.fromJson(reader, listType);
            return (transactions != null) ? transactions : new ArrayList<>();
        } catch (IOException e) {
            ui.printMessage(String.format(ErrorMessages.ERROR_LOADING_TRANSACTIONS, e.getMessage()));
        } catch (com.google.gson.JsonSyntaxException | com.google.gson.JsonIOException e) {
            System.out.println(ErrorMessages.INVALID_JSON_TRANSACTIONS);
            saveTransaction(new ArrayList<>());
            return new ArrayList<>();
        } catch (Exception e) {
            ui.printMessage(String.format(ErrorMessages.ERROR_DESERIALIZING_TRANSACTIONS, e.getMessage()));
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * Saves the list of transactions to the transactions JSON file.
     *
     * @param transactions The list of transactions to be saved.
     */
    public static void saveTransaction(ArrayList<Transaction> transactions) {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_PATH)) {
            gson.toJson(transactions, writer);
            writer.flush();
        } catch (IOException e) {
            ui.printMessage(String.format(ErrorMessages.ERROR_SAVING_TRANSACTIONS, e.getMessage()));
            e.printStackTrace();
        }
    }

    /**
     * Loads categories from the categories JSON file.
     * If the file does not exist, creates an empty categories file.
     *
     * @return A list of categories loaded from the file, or an empty list if no categories are found.
     */
    public static ArrayList<Category> loadCategories() {
        File categoriesFile = new File(CATEGORIES_PATH);
        if (!categoriesFile.exists()) {
            ui.printMessage(InfoMessages.NO_CATEGORY_FILE_FOUND);
            saveCategory(new ArrayList<>()); // Create an empty file if it doesn't exist
        }

        try (FileReader reader = new FileReader(CATEGORIES_PATH)) {
            Type listType = new TypeToken<ArrayList<Category>>() {}.getType();
            ArrayList<Category> categories = gson.fromJson(reader, listType);
            return (categories != null) ? categories : new ArrayList<>();
        } catch (IOException e) {
            ui.printMessage(String.format(ErrorMessages.ERROR_LOADING_CATEGORIES, e.getMessage()));
        } catch (Exception e) {
            ui.printMessage(String.format(ErrorMessages.ERROR_DESERIALIZING_CATEGORIES, e.getMessage()));
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Saves the list of categories to the categories JSON file.
     *
     * @param categories The list of categories to be saved.
     */
    public static void saveCategory(ArrayList<Category> categories) {
        try (FileWriter writer = new FileWriter(CATEGORIES_PATH)) {
            gson.toJson(categories, writer);
            writer.flush();
        } catch (IOException e) {
            ui.printMessage(String.format(ErrorMessages.ERROR_SAVING_CATEGORIES, e.getMessage()));
            e.printStackTrace();
        }
    }
}
