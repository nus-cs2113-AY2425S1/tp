package parser;

import car.CarList;
import customer.CustomerList;
import exceptions.CarException;
import transaction.Transaction;
import transaction.TransactionList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static parser.Parser.ADD_TRANSACTION_COMMAND;

/**
 * Parses user commands into Transaction objects or performs actions on TransactionList.
 */
public class TransactionParser {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String ADD_TRANSACTION_FORMAT = "add-tx /c [LICENSE_PLATE_NUMBER] /u [CUSTOMER_NAME] " +
            "/d [DURATION] /s [START_DATE dd-MM-yyyy]";
    public static final String FIND_TRANSACTION_BY_CUSTOMER_FORMAT = "find-txs-by-customer /u [CUSTOMER_NAME]";
    public static final String REMOVE_TRANSACTION_FORMAT = "remove-tx /t [TRANSACTION_ID]";
    public static final String MARK_TRANSACTION_FORMAT = "mark-tx /t [TRANSACTION_ID]";
    public static final String UNMARK_TRANSACTION_FORMAT = "unmark-tx /t [TRANSACTION_ID]";

    private static final String LICENSE_PLATE_PARAM = "/c";
    private static final String CUSTOMER_NAME_PARAM = "/u";
    private static final String DURATION_PARAM = "/d";
    private static final String START_DATE_PARAM = "/s";

    /**
     * Parses a user input command into a Transaction object.
     *
     * @param userInput the user input command
     * @return a Transaction object based on parsed input
     * @throws IllegalArgumentException if the command format or parameters are invalid
     */
    public static Transaction parseIntoTransaction(String userInput) throws IllegalArgumentException {
        userInput = userInput.substring(ADD_TRANSACTION_COMMAND.length()).trim();

        String[] parameters = { LICENSE_PLATE_PARAM, CUSTOMER_NAME_PARAM, DURATION_PARAM, START_DATE_PARAM };
        String[] parameterContents;

        if (!isValidSequence(parameters, userInput)) {
            throw new IllegalArgumentException("Invalid command format for adding a transaction. Refer to the format: "
                    + ADD_TRANSACTION_FORMAT);
        }

        parameterContents = parseParameterContents(parameters, userInput);

        String carLicensePlate = parameterContents[0].toUpperCase();
        validateLicensePlate(carLicensePlate);

        String customerName = parameterContents[1];
        customerName = validateCustomerName(customerName);

        int duration = parseAndValidateDuration(parameterContents[2]);

        LocalDate startDate = parseAndValidateStartDate(parameterContents[3]);

        return new Transaction(carLicensePlate, customerName, duration, startDate);
    }

    private static void validateLicensePlate(String licensePlate) throws CarException {
        if (!CarParser.isValidLicensePlateNumber(licensePlate)) {
            throw CarException.invalidLicensePlateNumber();
        }
        if (!CarList.isExistingLicensePlateNumber(licensePlate)) {
            throw CarException.licensePlateNumberNotFound();
        }
    }

    private static String validateCustomerName(String customerName) {
        if (!CustomerList.isExistingCustomer(customerName)) {
            throw new IllegalArgumentException("Customer " + customerName + " does not exist.");
        }
        return CustomerList.getCustomerNameIfExist(customerName);
    }

    private static int parseAndValidateDuration(String durationStr) {
        try {
            int duration = Integer.parseInt(durationStr);
            if (duration < 1 || duration > 365) {
                throw new IllegalArgumentException("Invalid duration. Duration must be between 1 and 365 days.");
            }
            return duration;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid duration format. Duration must be an integer.");
        }
    }

    private static LocalDate parseAndValidateStartDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for startDate. Expected format: dd-MM-yyyy.");
        }
    }

    private static boolean isValidSequence(String[] parameters, String userInput) {
        StringBuilder patternBuilder = new StringBuilder();

        for (int i = 0; i < parameters.length; i++) {
            patternBuilder.append(parameters[i]).append("\\s+");

            if (i < parameters.length - 1) {
                patternBuilder.append("([^/]+?)\\s+");
            } else {
                patternBuilder.append("(.+)$");
            }
        }

        return userInput.matches(patternBuilder.toString());
    }

    private static String[] parseParameterContents(String[] parameters, String userInput) {
        String[] contents = new String[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            int startIndex = userInput.indexOf(parameters[i]) + parameters[i].length();
            int endIndex = userInput.length();
            for (int j = i + 1; j < parameters.length; j++) {
                int nextParamIndex = userInput.indexOf(parameters[j], startIndex);
                if (nextParamIndex != -1) {
                    endIndex = nextParamIndex;
                    break;
                }
            }

            contents[i] = userInput.substring(startIndex, endIndex).trim();

            if (contents[i].isEmpty()) {
                throw new IllegalArgumentException("Missing value for parameter: " + parameters[i]);
            }
        }
        return contents;
    }

    public static void parseFindTxsByCustomer(String userInput) {
        String[] words = userInput.split(" ", 3);
        if (words.length < 3 || !words[1].equals(CUSTOMER_NAME_PARAM)) {
            System.out.println("Unable to search for transaction. Refer to correct format: "
                    + FIND_TRANSACTION_BY_CUSTOMER_FORMAT);
            return;
        }
        TransactionList.findTxsByCustomer(words[2]);
    }

    public static void parseRemoveTx(String userInput) {
        String[] words = userInput.split(" ", 3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to remove transaction. Refer to correct format: " + REMOVE_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.removeTxByTxId(words[2].toLowerCase());
    }

    public static void parseMarkCompleted(String userInput) {
        String[] words = userInput.split(" ", 3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to mark transaction. Refer to correct format: " + MARK_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.markCompletedByTxId(words[2].toLowerCase());
    }

    public static void parseUnmarkCompleted(String userInput) {
        String[] words = userInput.split(" ", 3);
        if (words.length < 3 || !words[1].equals("/t") || !words[2].toLowerCase().startsWith("tx")) {
            System.out.println("Unable to unmark transaction. Refer to correct format: " + UNMARK_TRANSACTION_FORMAT);
            return;
        }
        TransactionList.unmarkCompletedByTxId(words[2].toLowerCase());
    }
}
