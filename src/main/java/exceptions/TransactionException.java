package exceptions;

import java.util.ArrayList;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }

    /**
     * Exception thrown if data in transactionData.txt does not fit pre-determined format.
     *
     * @param errorLines List of row numbers in the transactionData.txt file which the data format is wrong.
     * @return Exception with message of which the row of data which are wrong.
     */
    public static TransactionException invalidParameters(ArrayList<Integer> errorLines){
        String message = "Transaction data do not match parameters requirements in "
                + errorLines.size() + " rows of data" + System.lineSeparator();
        message += "Rows are : ";
        message += errorLines.toString();
        message = message + "\n";
        return new TransactionException(message);
    }
}
