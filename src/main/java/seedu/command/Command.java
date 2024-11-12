package seedu.command;

import seedu.model.Inventory;
import seedu.ui.Ui;
import seedu.storage.Csv;
import seedu.exceptions.InventraException;

/**
 * An abstract class representing a command in the Inventra application.
 * Commands are used to perform various actions on the inventory, such as adding, deleting, or viewing records and fields.
 *
 * Each command class extending {@code Command} is expected to implement the {@code execute} method to define its specific behavior.
 */
public abstract class Command {
    protected Inventory inventory;
    protected Ui ui;
    protected Csv csv;

    /**
     * Constructs a {@code Command} with the specified inventory, UI, and CSV handler.
     *
     * @param inventory the inventory to be operated on by the command.
     * @param ui        the UI handler for user interaction.
     * @param csv       the CSV handler for managing CSV file operations.
     */
    public Command(Inventory inventory, Ui ui, Csv csv) {
        this.inventory = inventory;
        this.ui = ui;
        this.csv = csv;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Ui getUi() {
        return ui;
    }

    public Csv getCsv() {
        return csv;
    }

    /**
     * Executes the command with the specified arguments.
     * Each subclass should implement this method to define specific behavior for the command.
     *
     * @param args the arguments to pass to the command during execution.
     * @throws InventraException if an error occurs during command execution.
     */
    public abstract void execute(String[] args) throws InventraException;
}
