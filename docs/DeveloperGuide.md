# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

---
### Ui and Parser
__Overview__
The Ui component, `AppUi` manages user interactions by displaying messages and receiving input. 

The Parser component, comprising `DateParser` and `InputParser`, handles input parsing to interpret commands and dates entered bythe user accurately

__Implementation__

- **Class Diagram**: Displays the relationship between `AppUi`, `DateParser`, and `InputParser`. `AppUi` serves as the main interface for user interaction, utilizing `DateParser` and `InputParser` to interpret and process input effectively.
  - {Input Class diagram}
- **Sequence Diagram**: Illustrates the flow of processing user input, from capturing input in `AppUi`, parsing it with `InputParser`, and validating date formats via `DateParser`.
  - {input sequence Diagram}

### Ui Component

__Overview__

The `AppUi` class in the Ui component facilitates user interactions, including displaying start up messages, errors, and capturing input from users.

__Class Structure__

- **Attributes**:
  - `scanner`: `Scanner` — Reads user input from the console

__Implementation Details__

*Class Diagram*: Illustrates `AppUi` with its methods for displaying messages and capturing user input.

{insert class diagram for UI Component here}

__Methods__

- **displayWelcomeMessage()**: Outputs a startup message.
- **getUserInput()**: Reads input from the user.
- **showUnknownCommandMessage()**: Notifies the user of an unrecognized command.
- **showErrorMessage(String message)**: Displays a specific error message.

__Usage Example__

```
AppUi ui = new AppUi();
ui.displayWelcomeMessage();
String userInput = ui.getUserInput();
ui.showUnknownCommandMessage();
```

__Design Considerations__

- **Future Extension**: Additional Ui features such as graphical interfaces or web-based interactions may be added to enhance user experience.

### Parser Component
__Overview__

The Parser component includes `InputParser` and `DateParser`. `InputParser` processes user commands, while `DateParser` validates date string.

__Class Structure__

- **Attributes**:
  - `formatter`: `DateTimeFormatter` — Defines a date format for parsing.

__Implementation Details__

*Class Diagram*: Shows `InputParser` parsing command input and `DateParser` handling date validation.

{insert class diagram for Parser Component here}

__Methods__

- **InputParser.parseCommands(String input)**: Breaks down commands and arguments.
- **DateParser.parse(String dateStr)**: Validates and converts date strings.

__Usage Example__

```
HashMap<String, String> commandArgs = InputParser.parseCommands("add /date 12/10/24 /amount 500");
LocalDate parsedDate = DateParser.parse("12/10/24");
```

__Design Considerations__

- **Future Extension**: To support more complex commands and argument parsing, the Parser component could introduce additional parsers, such as `CommandParser` and `ArgumentParser`, extending from an abstract base. Supporting alternative date formats in `DateParser` could enhance flexibility, accommodating user input from different locales or formats.

---
### Logic
__Overview__

The Logic component manages core functionalities in the application like adding, editing and deleting financial entries. 
It interacts with `FinancialList`, `AppUi` and `Storage`, and leverages command classes (`AddExpenseCommand`, `AddIncomeCommand`, etc.) to execute operations.

__Class Structure__

- **Attributes**:
  - `financialList`: `FinancialList` — Stores financial entries.
  - `ui`: `AppUi` - Manages user interactions.
  - `storage`: `Storage` - Handles data persistence

__Implementation Details__

*Class Diagram*: Shows Logic as the main controller interacting with `FinancialList`, `AppUi`, and various command classes for operations (e.g., `AddIncomeCommand`, `DeleteCommand`).

{Insert Class Diagram for Logic Component here}

__Constructor__

TThe Logic constructor initializes key components (FinancialList, AppUi, and Storage) to facilitate CRUD operations and manage interactions with users and stored data. 

__Methods__

- **executeCommand(String userInput)**: Parses and executes the command from `userInput`.
- **addExpense(double amount, String description, LocalDate date)**: Adds a new `Expense` to `FinancialList`.
- **addIncome(double amount, String description, LocalDate date)**: Adds a new `Income` to `FinancialList`.
- **deleteEntry(int index)**: Removes an entry at a given index.
- **editEntry(int index, double amount, String description)**: Updates an entry's amount and description.
- **seeAllEntries()**: Displays all entries in `FinancialList`

__Usage Example__

```
FinancialList financialList = new FinancialList();
AppUi ui = new AppUi();
Storage storage = new Storage();

Logic logic = new Logic(financialList, ui, storage);
logic.addIncome(500.00, "Freelance Project", LocalDate.of(2023, 10, 27));
logic.seeAllEntries();

```

__Design Considerations__

- **Future Extension**: External APIs could be integrated in the future for features like currency conversion or market updates, making Logic an ideal candidate for adaptability.

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
__Overview__

The list entries feature is facilitated by the `SeeAllEntriesCommand` class.
Similarly, classes `SeeAllExpensesCommand` and `SeeAllIncomesCommand` facilitate 
listing out expenses and incomes respectively.

__Implementation__

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

The `shouldBeIncluded()` method marks Financial Entries as "should be included" if their
dates fall between the start and end dates passed into the command object.

`SeeAllExpensesCommand` and `SeeAllIncomesCommand` interact with the `FinancialList` in a 
similar manner, with the only difference being that the `shouldBeIncluded()` methods of
`SeeAllExpensesCommand` and `SeeAllIncomesCommand` only mark `Expenses` and `Incomes` as "should be included".

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
- unsure of how to manage his finances, wants to learn
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
| v2.0    | user                           | view my expenditure over the last X days                                            | see how much money I spent recently                            |
| v2.0    | user                           | keep a log of my data                                                               | retain memory of past transactions in previous runs of the app |
| v2.0    | user                           | set a monthly budget for myself                                                     | ensure that I am saving enough money                           |
| v2.0    | user                           | be alerted when I exceed my allocated budget                                        | know when I spend too much money                               |
| v2.0    | user                           | categorise my spendings                                                             | know my spending across different areas                        |
| v2.0    | user                           | view my expenditure over different categories                                       | see where I spend the most                                     |
| v2.0    | user new to financial planning | get suggested budget allocations according to income, expenses, and financial goals | have realistic financial budgets                               |
| v2.0    | user                           | have reports or summaries of my spending trends                                     | make better financial decisions in the future                  |
| v2.0    | busy user                      | log my finances in the shortest possible time                                       | have more time for other activities                            |
| v2.0    | busy user                      | visualize my overall cash flow (inflows and outflows) across all accounts           | see my total financial health at a glance                      |
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

### Manual Testing

View the [User Guide](UserGuide.md) for the list of UI commands and their related use case and expected outcomes.
{Currently the link to the User Guide is not up yet}

### JUnit Testing

JUnit tests are written in the subdirectory `test` and serve to test the key methods part of the application.
