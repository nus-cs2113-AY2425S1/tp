package seedu.datastorage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import seedu.category.Category;
import seedu.transaction.Transaction;
import seedu.transaction.Expense;
import seedu.transaction.Income;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Storage {

    private static final String TRANSACTIONS_PATH = "transactions.json";
    private static final String CATEGORIES_PATH = "categories.json";

    private static final Gson gson;

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

    public static ArrayList<Transaction> loadTransactions() {
        File transactionsFile = new File(TRANSACTIONS_PATH);

        if (!transactionsFile.exists()) {
            System.out.println("No existing transaction file found. Starting fresh.");
            saveTransaction(new ArrayList<>()); // Create an empty transactions file
        }

        try (FileReader reader = new FileReader(TRANSACTIONS_PATH)) {
            Type listType = new TypeToken<ArrayList<Transaction>>() {}.getType();
            ArrayList<Transaction> transactions = gson.fromJson(reader, listType);
            return (transactions != null) ? transactions : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        } catch (com.google.gson.JsonSyntaxException | com.google.gson.JsonIOException e) {
            System.out.println("Invalid JSON format in transactions file. Re-initializing.");
            saveTransaction(new ArrayList<>());
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error deserializing transactions: " + e.getMessage());
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static void saveTransaction(ArrayList<Transaction> transactions) {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_PATH)) {
            gson.toJson(transactions, writer);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Similar methods for categories
    public static ArrayList<Category> loadCategories() {
        File categoriesFile = new File(CATEGORIES_PATH);
        if (!categoriesFile.exists()) {
            System.out.println("No existing category file found. Starting fresh.");
            saveCategory(new ArrayList<>()); // Create an empty file if it doesn't exist
        }

        try (FileReader reader = new FileReader(CATEGORIES_PATH)) {
            Type listType = new TypeToken<ArrayList<Category>>() {}.getType();
            ArrayList<Category> categories = gson.fromJson(reader, listType);
            return (categories != null) ? categories : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error loading categories: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error deserializing categories: " + e.getMessage());
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void saveCategory(ArrayList<Category> categories) {
        try (FileWriter writer = new FileWriter(CATEGORIES_PATH)) {
            gson.toJson(categories, writer);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error saving categories: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
