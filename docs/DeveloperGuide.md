# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

---
### FinancialList and FinancialEntry
__Overview__

Managing financial entries through two main components:

- **FinancialList**: A centralized data structure that stores and manages entries. It provides CRUD (Create, Read, Update, Delete) operations to handle financial records, such as adding new entries and modifying or retrieving existing ones.
- **FinancialEntry**: An abstract base class representing a generic financial record. Subclasses include `Income` and `Expense`, which inherit shared attributes like `amount`, `description`, and `date`. Each subclass has specific characteristics that distinguish income from expenses.

__Implementation__
- **Class Diagram**: Displays the relationship between `FinancialList`, `FinancialEntry`, `Income`, and `Expense`. It highlights `FinancialList` as the main container managing `FinancialEntry` objects.
  - {Input Class diagram}
- **Sequence Diagram**: Illustrates the process of adding a new entry, from parsing user input to creating and adding the entry to `FinancialList`.
  - {input sequence Diagram}

#### FinancialList Components
__Overview__

The `FinancialList` component is the main data structure responsible for managing all financial entries, specifically `Income` and `Expense`.
It provides methods to **add**, **edit**, **delete**, and **retrieve** entries, serving as the application’s primary entry manager.

__Class Structure__

- **Attributes**:
  - `entries`: `ArrayList<FinancialEntry>` — Stores both `Income` and `Expense` instances.

__Implementation Details__

*Class Diagram*: Show `FinancialList` managing `FinancialEntry` objects (`Income` and `Expense` subclasses).

{input diagram here }

__Constructor__

The `FinancialList` constructor initializes an empty list of entries to support CRUD operations. Key Arguments: None

__Methods__

- **addEntry(FinancialEntry entry)**: Adds a `FinancialEntry` object to `entries`.
- **deleteEntry(int index)**: Removes an entry at a specified index.
- **editEntry(int index, double amount, String description)**: Updates the `amount` and `description` of a specified entry.
- **getEntry(int index)**: Retrieves an entry by index.
- **getEntryCount()**: Returns the total count of entries.

__Usage Example__

```
FinancialList financialList = new FinancialList();
Income income = new Income(500.00, "Freelance Project", LocalDate.of(2023, 10, 27));
Expense expense = new Expense(50.00, "Groceries", LocalDate.of(2023, 10, 28));

financialList.addEntry(income);
financialList.addEntry(expense);

// Edit an entry
financialList.editEntry(1, 55.00, "Groceries & Snacks");

// Retrieve an entry
FinancialEntry entry = financialList.getEntry(0);
System.out.println("Description: " + entry.getDescription());
```

### Design Considerations

- **Future Budget Management**: `FinancialList` could incorporate a `budget` attribute and `checkBudget()` method to monitor expenses and alert users when limits are exceeded.
- **Scalability**: By using `FinancialEntry` as a base class, `FinancialList` can easily manage new types of entries (e.g., `Investment` or `Loan`), ensuring the design is open to future extensions.

---
#### FinancialEntry Component
__Overview__

`FinancialEntry` is an abstract base class that represents a generic financial record.
It defines shared attributes such as `amount`, `description`, and `date`, which are common across both `Income` and `Expense`.
`Income` and `Expense` inherit these properties and methods, each adding specific functionality related to its type.

__Implementation__

The class diagram above shows `FinancialEntry` as the base class with `Income` and `Expense` as specific implementations.
{input diagram here}

__Class Structure__
- **Attributes**:
  - `amount`: `double` — Represents the monetary value of the entry.
  - `date`: `LocalDate` — The date associated with the transaction.
  - `description`: `String` — A description identifying the entry.

__Constructor__

The `FinancialEntry` constructor initializes `amount`, `description`, and `date`.

- **Key Arguments**:
  - `double amount`: Monetary value for the entry.
  - `String description`: Description or label for the entry.
  - `LocalDate date`: Date of the entry.

__Methods__

- **Core Methods** (inherited by both `Income` and `Expense`):
  - `getAmount()`, `getDescription()`, `getDate()`: Accessor methods for each attribute.
  - `setAmount(double newAmount)`, `setDescription(String newDescription)`, `setDate(LocalDate newDate)`: Mutator methods for updating values.

- **Custom Methods for Income and Expense**:
  - **toString()**:
    - `Income`: Returns formatted string as `[Income] - description $amount (on date)`.
    - `Expense`: Returns formatted string as `[Expense] - description $amount (on date)`.
  - **toStorageString()**:
    - `Income`: Formats as `"I | amount | description | date"` for storage.
    - `Expense`: Formats as `"E | amount | description | date"` for storage.

__Usage Example__

The following code segment demonstrates the creation of `Income` and `Expense` entries:
```
Income income = new Income(500.00, "Freelance Project", LocalDate.of(2023, 10, 27));
Expense expense = new Expense(50.00, "Groceries", LocalDate.of(2023, 10, 28));

System.out.println(income.toString());
System.out.println(expense.toString());
```

__Design Considerations__
- **Future Extension**: Additional fields could be added to `Income` and `Expense` for more specific details, such as a `source` for `Income` or a `category` for `Expense`.
- **Abstract Base Class**: The design decision to make `FinancialEntry` abstract enables extensibility, allowing for new types of financial records without modifying `FinancialList` or existing subclasses.

---

### Commands

<ins>Overview</ins>

The abstract class `Command` has been implemented to introduce an additional layer
of abstraction between the `CommandHandler` class and command execution,
allowing for separation of handling command keywords and executing commands.

The diagram below shows the inheritance of the `Command` class. The diagram is only meant to show
the hierarchy of classes and have been greatly simplified.

{Insert diagram here}

<ins>Constructor</ins>

The `Command` constructor updates the attributes based on the input arguments.

<ins>Methods</ins>

The abstract `Command` class and its related children classes have the following method:

- *execute*: Effect the command based on the corresponding child class.

### Adding Entries

<ins>Overview</ins>

The feature to add entries is facilitated by the abstract class `AddEntryCommand`.
The `AddExpenseCommand` and `AddIncomeCommand` classes extend from the `AddEntryCommand`,
and are used to add expenses and incomes respectively.

<ins>Class Structure</ins>

The `AddEntryCommand` class has the following attributes:
- *amount*: An object representing the amount of money in the transaction.
- *description*: An object representing the description of the transaction.
- *date*: An object representing the date on which the transaction occurred.

The `AddExpenseCommand` and `AddIncomeCommand` classes inherit all attributes
from the `AddEntryCommand` class and have no additional attributes.

The `AddExpenseCommand` and `AddIncomeCommand` classes have the following method:
- *execute*

<ins>Implementation</ins>

The user invokes the command to add entries by entering the following commands:
- `expense [DESCRIPTION] /a AMOUNT [/d DATE]` for adding an expense
- `income [DESCRIPTION] /a AMOUNT [/d DATE]` for adding an income

This is parsed by the InputParser, returning a HashMap `commandArguments`, containing the
following arguments:
- `argument`: Represents the description of the entry. The value can be left blank.
- `/a`: Represents the amount of money in the transaction. This is a compulsory argument.
- `/d`: Represents the date on which the transaction occurred. If this argument is not used,
  the current date is used. An exception occurs if this argument is used but the value is left blank.

### Editing Entries

<ins>Overview</ins>

The feature to add entries is facilitated by the `EditEntryCommand`. Both `Income` and `Expense`
can be edited using this one class.

<ins>Class Structure</ins>

The `EditEntryCommand` class has the following attributes:
- *index*: An object representing the index of the entry in the full financial list.
- *amount*: An object representing the amount of money used in the transaction.
- *description*: An object representing the description of the transaction.
- *date*: An object representing the date on which the transaction occurred.

The `EditEntryCommand` class has the following method:
- *execute*

<ins>Implementation</ins>

The user invokes the command to add entries by entering the following command:
`edit INDEX [/des DESCRIPTION] [/a AMOUNT] [/d DATE]`.

This is parsed by the InputParser, returning a HashMap `commandArguments`, containing the
following arguments:
- `argument`: Represents the index of the entry in the full financial list.
  This is a compulsory argument.
- `/des`: Represents the description of the transaction. This is an optional argument.
- `/a`: Represents the amount of money used in the transaction. This is an optional argument.
- `/d`: Represents the date on which the transaction occurred. This is an optional argument.

### Listing Entries
<ins>Overview</ins>

The list entries feature is facilitated by the `SeeAllEntriesCommand` class.
The classes `SeeAllExpensesCommand` and `SeeAllIncomesCommand` extend from `SeeAllEntriesCommand` 
and facilitate listing out expenses and incomes respectively.

<ins>Class Structure</ins>

The `SeeAllEntriesCommand` class has the following attributes:

- _start_: The starting date from which Financial Entries are to be listed. `null` if there is no starting date.
- _end_: The ending date up to which Financial Entries should be listed. `null` if there is no ending date.
- _entriesListedMessage_: String constant containing message when there are entries to be listed.
- _noEntriesMessage_: String constant containing message when there are no entries to be listed.
- _cashflowHeader_: String constant containing header to be printed when displaying total cashflow.

The `SeeAllExpensesCommand` and `SeeAllIncomesCommand` classes inherit these attributes from `SeeAllEntriesCommand`,
with _entriesListedMessage_, _noEntriesMessage_ and _cashflowHeader_ overwritten to contain customized messages for
each respective command.

The `SeeAllEntriesCommand` class has the following methods:

- `execute`
- Getters:
  - `getEntriesListedMessage`
  - `getNoEntriesMessage`
  - `getCashflowHeader`
- `getCashflowString`: takes in the net cashflow as a double and returns it as a String for printing.
- `shouldBeIncluded`: determines if an entry in the Financial list should be listed out.

The `SeeAllExpensesCommand` and `SeeAllIncomesCommand` classes inherit all of the aforementioned methods, overriding
the following methods:

- The getters to print their customized messages/headers
- `shouldBeIncluded` to further filter out incomes/expenses respectively

Additionally, in the `SeeAllExpensesCommand` class, the `getCashflowString` method is overridden to negate the
net cashflow input into the method to give a positive number for total Expenses.

<ins>Implementation</ins>

The user invokes the command to list entries by entering the following command:
```list [income|expense] [/from START_DATE] [/to END_DATE]```.

This is parsed by the InputParser, returning a HashMap `commandArgumets`, containing the following optional arguments:
- `argument`: Represents the type of Financial Entries to be printed. Can take 3 possible values:
  - `expense`: List only Expenses
  - `income`: List only Incomes
  - `null`: List both Expenses and Incomes
- `/from`: Represents the starting date from which Financial Entries should be listed. If value is `null`,
there is no defined starting date.
- `/to`: Represents the ending date by which Financial Entries should be listed. If value is `null`,
  there is no defined ending date.

`CommandHandler` invokes the `listHelper` method to create and execute the command to list the financial entries
according to the following logic.

{add in diagram}

The interaction between the command classes and the `FinancialList` is as follows,
using `SeeAllEntriesCommand` as an example:

{add diagram}

__Design Considerations__

Given that the logic for `SeeAllEntriesCommand`, `SeeAllExpensesCommand` and `SeeAllIncomesCommand` are very similar 
with the only difference being the criteria for printing the entries, we made `SeeAllExpensesCommand` and 
`SeeAllIncomesCommand` inherit `SeeAllEntriesCommand` to reduce the amount of duplicated code written.

### Exceptions and Logging

An exception class `FinanceBuddyException` is thrown when users use the product wrongly.
Exceptions are caught at the nearest instance that they occur.

### Storage

__Overview__

The `Storage` class has been implemented to store the `FinancialList` into a file. 
Also it's responsible for restoring users' progress even if they have terminate the program using the saved file.

__Implementation Details__

The function `updateStorage` should be called whenever the `FinancialList` in an `AppUi` object. 
It will overide the `data/FinancialList.txt` file with the up-to-date `FinancialList` that has been converted to string.
Note: if the `data/FinancialList.txt` file doesn't exist, the program should generate one.

The storage format of an `Expense` or `Income`, handled by `toStorageString()` method is different from the format generate by `toString()` method for CLS display.

EX:
For an Income with description "Lunch", amount "10.90" and date "2024/10/25":
The `toString()` method will return as `[Income] - Lunch $ 10.90 (on 25/10/24)`
And the `toStorageString()` method will return as `I | 10.90 | Lunch | 25/10/24`

## Product scope
### Target user profile:
- University student who wants to manage their limited finances
- busy with academics and CCAs, wants to manage finances quickly
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

### Value proposition
Finance Buddy allows our target user profile to keep track of their income/expenditures
faster than a typical mouse/GUI driven app

## User Stories

| Version | As a ...                       | I want to ...                                                                       | So that I can ...                                              |
|---------|--------------------------------|-------------------------------------------------------------------------------------|----------------------------------------------------------------|
| v1.0    | new user                       | see usage instructions                                                              | remember how to use the app in case I forget the commands      |
| v1.0    | user                           | record my daily expenses                                                            | keep track on how much I spend and what I spend on             |
| v1.0    | user                           | delete my logging records                                                           | remove a wrong record                                          |
| v1.0    | user                           | edit my logs                                                                        | edit a wrong record                                            |
| v1.0    | user                           | see my cash flows                                                                   | have an overview of my cash flow                               |
| v2.0    | user                           | view my expenditure over a certain period                                           | see how much money I spent recently                            |
| v2.0    | user                           | keep a log of my data                                                               | retain memory of past transactions in previous runs of the app |
| v2.0    | user                           | set a monthly budget for myself                                                     | ensure that I am saving enough money                           |
| v2.0    | user                           | be alerted when I exceed my allocated budget                                        | know when I spend too much money                               |
| v2.0    | user                           | categorise my spendings                                                             | know my spending across different areas                        |
| v2.0    | user                           | view my expenditure over different categories                                       | see where I spend the most                                     |
| v2.0    | busy user                      | log my finances in the shortest possible time                                       | have more time for other activities                            |
| v2.1    | busy user                      | use shortcuts to log frequent and similar expenses                                  | save time logging expenses                                     |

## Non-Functional Requirements

 - Technical Requirements: Any mainstream OS, i.e. Windows, macOS or Linux, with Java 11 installed. Instructions for downloading Java 11 can be found [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
 - Project Scope Constraints: The application should only be used for tracking. It is not meant to be involved in any form of monetary transaction.
 - Project Scope Constraints: Data storage is only to be performed locally.
 - Quality Requirements: The application should be able to be used effectively by a novice with little experience with CLIs.


## Glossary

* *FinancialEntry* - Refers to the base class for financial entries, containing common attributes such as description, amount, and date that define a financial transaction.
* *description* - A brief text that provides context or details about the financial transaction, such as what the expense or income relates to.
* *amount* - A numeric value representing the monetary value of the transaction. The amount must be a number greater than or equal to zero, with precision up to two decimal places.
* *date* - Represents the date of the financial entry, recorded in the format dd/MM/YY, indicating the day, month, and year when the transaction occurred.
* *Expense* - A subclass of FinancialEntry that represents a financial outflow or expenditure made by the user.
* *Income* - A subclass of FinancialEntry that represents a financial inflow or income received by the user.
* *FinancialList* - A class responsible for storing and managing all financial entries, including both expenses and incomes, allowing for the organization and manipulation of financial data.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
