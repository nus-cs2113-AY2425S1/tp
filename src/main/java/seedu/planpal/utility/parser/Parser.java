package seedu.planpal.utility.parser;

import seedu.planpal.modes.activities.ActivityManager;
import seedu.planpal.modes.contacts.ContactManager;
import seedu.planpal.exceptions.PlanPalExceptions;
import seedu.planpal.modes.expenses.ExpenseManager;
import seedu.planpal.utility.Ui;
import seedu.planpal.utility.filemanager.FileManager;

import java.util.Scanner;

public class Parser {
    protected static final String BYE_COMMAND = "bye";
    protected static final String ADD_COMMAND = "add";
    protected static final String DELETE_COMMAND = "delete";
    protected static final String SET_CATEGORY_COMMAND = "category";
    protected static final String SEARCH_CATEGORY_COMMAND = "search";
    protected static final String EDIT_COMMAND = "edit";
    protected static final String FIND_COMMAND = "find";
    protected static final String LIST_COMMAND = "list";
    protected static final String EXIT_MODE_COMMAND = "exit";
    private static final String CONTACT_MANAGER = "1";
    private static final String EXPENSE_MANAGER = "2";
    private static final String ACTIVITY_MANAGER = "3";
    protected FileManager fileManager;
    protected ContactManager contactManager;
    protected ExpenseManager expenseManager;
    protected ActivityManager activityManager;

    public Parser(){
        this.fileManager = new FileManager();
        this.contactManager = new ContactManager();
        this.expenseManager = new ExpenseManager();
        this.activityManager = new ActivityManager();
    }

    private void loadFiles(){
        fileManager.loadList(contactManager, "contacts.txt");
        fileManager.loadList(expenseManager, "expenses.txt");
        fileManager.loadList(activityManager, "activities.txt");
    }

    private String getCommand(String currentMode){
        Scanner in = new Scanner(System.in);
        System.out.println("Current Mode: " + currentMode);
        System.out.print("Enter command: ");
        return in.nextLine();
    }

    public boolean processCommand(String modeInput) throws PlanPalExceptions {
        boolean isProcessing = true;
        loadFiles();
        while (isProcessing) {
            switch (modeInput){
            case CONTACT_MANAGER:
                String commandForContact = getCommand("CONTACT_MANAGER");
                ContactParser contactParser = new ContactParser(contactManager);
                isProcessing = contactParser.processCommand(commandForContact);
                break;

            case EXPENSE_MANAGER:
                // do something
                String commandForExpense = getCommand("EXPENSE_MANAGER");
                ExpenseParser expenseParser = new ExpenseParser(expenseManager);
                isProcessing = expenseParser.processCommand(commandForExpense);
                break;

            case ACTIVITY_MANAGER:
                // do something
                String commandForActivity = getCommand("ACTIVITY_MANAGER");
                ActivityParser activityParser = new ActivityParser(activityManager);
                isProcessing = activityParser.processCommand(commandForActivity);
                break;

            case BYE_COMMAND:
                Ui.printByeMessage();
                System.exit(0);
                break;

            default:
                throw new PlanPalExceptions("Invalid mode");
            }
        }
        return false;
    }
}