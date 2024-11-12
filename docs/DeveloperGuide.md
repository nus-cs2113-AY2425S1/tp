# WheresMyMoney Developer Guide


## Table of Contents
- [Developer Guide](#wheresmymoney-developer-guide)
    - [Table of Contents](#table-of-contents)
    - [Acknowledgements](#acknowledgements)
    - [Design & Implementation](#design--implementation)
        - [Architecture](#architecture)
        - [Ui and Parser](#ui-and-parser)
        - [Commands](#commands)
        - [Storage](#storage)
        - [Expense and Expense List](#expense-and-expense-list)
        - [Expense Filter](#expense-filter)
        - [Date and Time Handling](#date-and-time-handling)
        - [Calculations](#calculations)
        - [Visualizer](#visualizer)
        - [Recurring Expense and Recurring Expense List](#recurring-expense-and-recurring-expense-list)
        - [Category Package](#category-package)
        - [Exceptions and Logging](#exceptions-and-logging)
    - [Product Scope](#product-scope)
        - [Target User Profile](#target-user-profile)
        - [Value Proposition](#value-proposition)
    - [User Stories](#user-stories)
    - [Non-Functional Requirements](#non-functional-requirements)
    - [Glossary](#glossary)
    - [Instructions for Testing](#instructions-for-testing)
        - [Manual Testing](#manual-testing)
        - [JUnit Testing](#junit-testing)
        - [Text UI Testing](#text-ui-testing)

<div style="page-break-after: always;"></div>

---

## Acknowledgements

WheresMyMoney uses the following libraries

1. [OpenCSV](https://opencsv.sourceforge.net/) - Used for saving/ loading expenses
2. [XChart](https://knowm.org/open-source/xchart/) - Used for visualizing expenses

WheresMyMoney uses the following tools for development:

1. [JUnit](https://junit.org/junit5/) - Used for testing.
2. [Gradle](https://gradle.org/) - Used for build automation.

---

## Design & Implementation


Design and Implementation has been broken down into various sections, each tagged for ease of reference:

- [Architecture](#architecture)
- [Ui and Parser](#ui-and-parser)
- [Commands](#commands)
- [Storage](#storage)
- [Expense and Expense List](#expense-and-expense-list)
- [Expense Filter](#expense-filter)
- [Date and Time Handling](#date-and-time-handling)
- [Calculations](#calculations)
- [Visualizer](#visualizer)
- [Recurring Expense and Recurring Expense List](#recurring-expense-and-recurring-expense-list)
- [Category Package](#category-package)
- [Exceptions and Logging](#exceptions-and-logging)

### Architecture
A high-level overview of the system is shown in the Architecture Diagram below.

![ArchitectureDiagram.png](diagrams%2Fimages%2FArchitectureDiagram.png "Architecture Diagram of WheresMyMoney")

This architecture consist of: 
1. `Ui`, `Main`, `Parser`, and `Command` classes: These classes stand between the user and the internal processing of the software.
2. `Expense`, `ExpenseList`, `ExpenseFilter` classes: Model expenses that commands can interact with.
3. `Storage` class: Stores information between sessions.
4. `Logging` and other utility classes: Provide extra functionalities for the software.

### Ui and Parser

<u>Overview</u>

The `Ui` class handles I/O operations such as displaying messages and reading user input.
The `Parser` parses user input and returns the relevant Command Object.
An `ArgumentsMap` object storing the mappings of the arguments to their values is passed in to the constructor of that `Command` object.
These classes are important for allowing the User to interact with the application.


<u>Methods</u>

The `Ui` class has the following key methods:

| Method           | Description                   |
|------------------|-------------------------------|
| `displayMessage` | Displays a message (`String`) |
| `getUserInput`   | Reads User Input              |

The `Parser` class has the following key method:

| Method                 | Description                                               |
|------------------------|-----------------------------------------------------------|
| `parseInputToCommand`  | Parses a given user input and returns the related Command |

The `ArgumentsMap` class extends the `HashMap<String, String>` class with the following methods:

| Method             | Description                                                                                   |
|--------------------|-----------------------------------------------------------------------------------------------|
| `isRecur`          | Checks if the arguments passed shows that the user wants to access the Recurring Expense List |
| `getRequired`      | Gets a required argument and throws an exception if that argument is not provided.            |
| `getRequiredIndex` | Gets a required index and throws an exception if that index is not provided.                  |
| `getPrice`         | Gets a price and throws an exception if that price is invalid.                                |
| `getRequiredPrice` | Gets a required price and throws an exception if that price is not provided/ invalid.         |
| `getRequiredLimit` | Gets a required limit and throws an exception if that limit is not provided/ invalid.         |


<u>Design Considerations</u>

Low-level I/O operations (e.g. stdio) are consolidated in the `Ui` class such that we can easily switch the I/O methods by 
modifying only the `Ui` class. This would make it easier to port the application to other platforms if needed.

`Ui` class is used as part of exception handling for displaying of error messages to the user for feedback.

The Parser also has some considerations such as
1. Restricted arguments which should not be used by other developers in their commands. These include
    1. `/command` -> used for the main command keyword
    2. `/index` -> used for the main text argument (the index of the expense to edit/ delete) right after the command keyword
3. Any duplicate arguments will throw an `InvalidInputException`
4. All `/` in the argument values should be escaped
    1. Examples
       1. `command /argument \/value` -> `argument`: `/value`
       2. `command /argument value\/value` -> `argument`:`value/value`
       3. `command /argument value/value` -> `argument`:`value/value` (this is accepted for now, but not recommended)
       4. `command /argument value\\/value` -> `argument`:`value\/value`
   2. `/` doesn't need to be escaped for
       1. commands -> e.g. `/command /argument value1` -> the command is `/command`
           1. It is discouraged to do so, but the option is left for potential expandability
       2. arguments -> e.g. `command /argument/param value` -> the argument name is `argument/param`
    3. Leading and Trailing spaces are ignored, but additional spaces within values (e.g. `main  value`) are counted 

An `ArgumentsMap` class was created as it makes it easier to do argument validation, compared to a regular `HashMap<String, String>` class.

### Commands

#### Overview

The abstract `Command` class has been implemented to introduce an additional layer of abstraction between I/O and command execution, allowing for separation of handling command keywords and executing commands.


<u>Implementation Details</u>

The following diagram is a class diagram for `Command` and its children classes. 
This has been heavily simplified and only shows the key commands.

![CommandInheritance.png](diagrams%2Fimages%2FCommandInheritance.png)

The following diagram is a sequence diagram for execution of `Command`.

![CommandExecutionSequence.png](diagrams%2Fimages%2FCommandExecutionSequence.png)

Commands interact with `Ui` and `Parser` classes via `Main`, as illustrated in the following class diagram:

![UiToCommand.png](diagrams%2Fimages%2FUiToCommand.png)

### Storage

<u>Overview</u>

Storage is mostly handled by the different states themselves (`ExpenseList`, `RecurringExpenseList`, `CategoryStorage`).
This is to keep the storage tightly coupled with the data and ensures that when the data format is updated,
the storage format is updated accordingly, increasing cohesion.
The current implementation abstracted out common Csv functions into the `CsvUtils` class, but this implementation
also allows more flexible file formats between different classes, instead of relying solely on a certain format. 
This might help for future expandability.


However, we also do have a `Storage` class which handles how these file handling methods interact with one another. 
This is to consolidate the overall file loading and saving logic in the program. 
This is useful for certain cases, such as standardising default file paths 
It would be modified when there is a change of interaction between the various loading/ saving methods of the classes.

The `LoadCommand` and `SaveCommand` would reference the Storage class, so ideally they would not need to be changed much 
for feature changes.

<u>Implementation Details</u>

Here is an illustration of the related classes involved.

![StorageInteraction.png](diagrams%2Fimages%2FStorageInteraction.png)

<u>Design Considerations</u>

OpenCSV is used for the storage format to allow for reliable handling of CSV files.
The CSV file format was used to allow expenses to be easily exported/ imported to and from other programs (like Excel).

Storage is also meant to be an intentional act by the user. 
This means functionality such as autosave was not implemented due to the fact that it is not an intentional act.


### Expense and Expense List

<u>Overview</u>

The `Expense` class represents an individual expense with a price, description, category and the date added.

The `ExpenseList` class manages a collection of `Expense` objects. 
It allows for the addition, editing and deletion of expenses.

<u>Methods</u>

The `Expense` class has no notable methods.

The `ExpenseList` class has the following key methods: 

|     Method      |            Description            |
|:---------------:|:---------------------------------:|
|  `addExpense`   |    Adds an expense to the list    |
| `deleteExpense` | Removes an expense from the list  |
|  `editExpense`  |   Edits an expense in the list    |

There are 2 versions of `addExpense`: one with a date and one without a date.
- The former is used when the user does not specify the date: dateAdded is initialised as the current date. 
- The latter is used when the user specifies the date: dateAdded is initialised as that specified date. 

<u>Design Considerations</u>

The setters in `Expense` class checks for null and blank.
The `Expense` constructors also do, as they use those setters.

The `ExpenseList` class contains exception handling when attempting to edit or delete an expense that is not in the list.

<u>Implementation Details</u>

UML class diagram to show the dependency between `Command` and `ExpenseList` classes:

![CommandAndExpenseList.png](diagrams%2Fimages%2FCommandAndExpenseList.png)

The following diagram is a UML class diagram for `Expense` and `ExpenseList`:

![ExpenseAndExpenseList.png](diagrams%2Fimages%2FExpenseAndExpenseList.png "UML Class Diagram for Expense and ExpenseList")

### Expense Filter

The `ExpenseFilter` class provides utility methods for selecting expenses based on their category and time range.

Its interaction with `ExpenseList` is demonstrated in the following UML Class Diagram:

![ExpenseListAndFilter.png](diagrams%2Fimages%2FExpenseListAndFilter.png)

`ExpenseFilter` is a prerequisite to implementing other features, e.g. Expense statistics and visualization.

<u>Implementation Details</u>

`ExpenseFilter` filters expenses by 3 criteria: Category, From (date), To (date).
Each criterion is taken care of by a helper method.
Since these filter fields are optional, if they are `null`, helper methods will evaluate to `true`.

Given a list of expenses, `ExpenseFilter` iterates through each expense, applying three criteria checks on them; and 
it would add the expense to a new `ArrayList` if all three checks are satisfied.

The `ArrayList` is then returned to the caller.



### Date and Time Handling

<u>Overview</u>

The `DateUtils` class provides utility methods to handle date formatting, validation and conversion. 

<u>Methods</u>

The `DateUtils` class has the following key methods:

|        Method        |                      Description                       |
|:--------------------:|:------------------------------------------------------:|
|   `isInDateFormat`   | checks if a given string is in the correct date format |
|   `getCurrentDate`   |      gets the current date in `LocalDate` format       |
|    `stringToDate`    |   converts from given string to a `LocalDate` object   |
| `dateFormatToString` |   converts from given `LocalDate` object to a string   |

The date format that `DateUtils` uses, and thus the WheresMyMoney program uses, is `dd-MM-yyyy`. This ensures consistency in date formatting throughout the program.

<u>Implementation Details</u>

Most methods are essentially wrappers around the existing `java.time` API methods, but customised to fit this program's needs.

- `isInDateFormat` is a wrapper for `java.time`'s `parse` method, but returns a boolean instead. 
- `getCurrentDate` is a wrapper for `java.time`'s `now` method.
- `stringToDate` is a wrapper for `java.time`'s `parse` method.
- `dateFormatToString` is a wrapper for `java.time`'s `format` method.

<u>Design Considerations</u>

The `DateUtils` class' attributes and methods are all class-level, because:
- Utility methods should be independent of object state 
- No maintenance or storing of instance-specific date is needed 
- Calling is easier, without requiring instantiation or to be passed through method parameters 
- Utility functionality should not differ between instances.



### Calculations

<u>Overview</u>

The `StatsCommand` class first passes the expense list through the filters specified by the user, then performs
calculations on the expenses in the list to obtain meaningful statistics from them.

<u>Implementation Details</u>

`StatsCommand`, similar to the `list` command, takes in `category` and `from`/`to` dates.
It uses `ExpenseFilter` to generate an `ArrayList<Expense>` of matched expenses and passes it to `displayStats`.

The `displayStats` method, upon receiving `filteredExpenses`, performs the following steps:
+ Determine `highest` and `lowest` (expenses with the highest and lowest `price` in `filteredExpenses` respectively).
+ Calculate `sum` by adding `price` of all expenses in `filteredExpenses`.
+ Calculate `mean` by dividing `sum` by the number of expenses in `filteredExpenses`.
+ Obtain `truncatedMean`:
  + On displaying it to the user, `mean` has too many decimal places since it is a float, making it hard to read and 
  slightly inaccurate.
  + This is why `displayStats` obtains `truncatedMean`, a double with only two decimal places to display to the
  user. This is done by:
    + First multiplying `mean` by 100 to keep 2 decimal places,
    + Passing it through `Math.floor()` to remove the excess decimal places,
    + Dividing it by 100 to get the final `mean`, but with only 2 decimal places.
+ Display the obtained statistics to the user.



### Visualizer

The `VisualizeCommand`, similar to the `list` command, takes in `category` and `from`/`to` dates.
It uses `ExpenseFilter` to generate an `ArrayList<Expense>` of matched expenses and passes it to `Visualizer`.

The `Visualizer` class, upon receiving `expenses`, performs the following steps:
+ Determine `beginDate` and `endDate` (the earliest and latest `dateAdded` among all expenses).
+ `getTimeRange()` - Calculate `dateRange` - the difference (in days) between `beginDate` and `endDate` plus one.
+ If `dateRange` is within a month (no more than 32 days):
  + `createDateList()` - Generate a `List<String> timeSeries` of dates, spanning from `beginDate` to `endDate`.
  + `groupPriceByDay()` - Create a `Hashmap<String, Float> dateToExpenseMap`. 
  The keys are elements of `timeSeries`, and values are the total expenses in the corresponding day.
+ If `dateRange` is more than a month, perform similar operations where each element of `timeSeries` is a whole month.
+ `drawChart()` - Pass data to the `CategoryChart` object, customize and display the chart.

Data is passed to the XChart library in the form of two series - a `timeSeries` and a `valueSeries`.

### Recurring Expense and Recurring Expense List

<u>Overview</u>

The `RecurringExpense` class extends from the `Expense` class and it represents an individual recurring expense with a price, description, category, last date added and a frequency.

The `RecurringExpenseList` class extends from the `ExpenseList` class and it manages a collection of `RecurringExpense` objects.
It allows for addition, editing and deletion of expenses.

<u>Methods</u>

The `RecurringExpense` class has no notable methods.

The `RecurringExpenseList` class has the following key methods:

|          Method          |                                         Description                                         |
|:------------------------:|:-------------------------------------------------------------------------------------------:|
|  `addRecurringExpense`   |                            Adds a recurring expense to the list                             |
| `deleteRecurringExpense` |                          Removes a recurring expense from the list                          |
|  `editRecurringExpense`  |                            Edits a recurring expense in the list                            |
|      `loadFromCsv`       | Adds the appropriate amount of `Expense` objects with the correct date to the `ExpenseList` |


<u>Design Considerations</u>

Since the programme does not have an auto-save function upon closing the programme or auto-load when starting the programme, it is up to the user to save their work and to load it again.

Adding a recurring expense will only add a singular normal expense for that specified date (or current date if a date was not specified). All other valid expenses will be added after a `save` and a `load` command is used.
- The `save` command is needed to register the recurring expense into the system.
- The `load` command is used to trigger the mechanism to add all other valid expenses according to the date specified.

Editing a recurring expense will not edit the normal expenses that are associated with the recurring expense. You will need to edit the normal expenses yourself.

Deleting a recurring expense will not delete the normal expenses that are associated with the recurring expense. You will need to delete the normal expenses yourself.

<u>Implementation Details</u>

Below is the UML class diagram for `RecurringExpense` and `RecurringExpenseList`:

![RecurringExpenseAndRecurringExpenseList.png](diagrams%2Fimages%2FRecurringExpenseAndRecurringExpenseList.png "UML Class Diagram for RecurringExpense and RecurringExpenseList class")

Below is the sequence diagram for when the user calls the `load` command.

![RecurringExpenseLoadFromCsvSequence.png](diagrams%2Fimages%2FRecurringExpenseLoadFromCsvSequence.png "UML Sequence Diagram for calling load command")



### Category Package

This packages contains 5 classes relating to category management: `CategoryFacade`, `CategoryTracker`, `CategoryData`, `CategoryFilter` and `CategoryStorage`. 

<u>Overview</u>

The `CategoryFacade` class serves as an interface that simplifies the interaction with various category-related classes (`CategoryTracker` and `CategoryFilter`), providing a unified API for the rest of the application (namely the `Command` classes).

The `CategoryTracker` class manages a collection of Category-`CategoryData` pairs. It allows for the addition, editing and deletion of category-related information.

The `CategoryData` class contains category-related information, namely the cumulative expenditure (the sum of all prices of expenses with that category) and the spending limits for that category. 

The `CategoryFilter` class is responsible for filtering categories based on various criteria.

The `CategoryStorage` class contains methods to convert between category tracker and CSV file.

<u>Methods</u>

The `CategoryFacade` class has key methods for the 6 commands of:
- Add
  - ![CategoryFacade Add SequenceUML.png](diagrams%2Fimages%2FCategoryFacade%20Add%20SequenceUML.png)
- Delete
  - ![CategoryFacade Delete SequenceUML.png](diagrams%2Fimages%2FCategoryFacade%20Delete%20SequenceUML.png)
- Edit
  - ![CategoryFacade Edit SequenceUML.png](diagrams%2Fimages%2FCategoryFacade%20Edit%20SequenceUML.png)
- Load
  - ![CategoryFacade Load SequenceUML.png](diagrams%2Fimages%2FCategoryFacade%20Load%20SequenceUML.png)
- Save
  - ![CategoryFacade Save SequenceUML.png](diagrams%2Fimages%2FCategoryFacade%20Save%20SequenceUML.png)
- Set
  - ![CategoryFacade Set SequenceUML.png](diagrams%2Fimages%2FCategoryFacade%20Set%20SequenceUML.png)

Since this class' methods just call other methods from the other category classes, the (simplified) sequence diagrams are given instead of the method table.


The `CategoryTracker` class has the following key methods: 

|        Method         |                                                           Description                                                            |
|:---------------------:|:--------------------------------------------------------------------------------------------------------------------------------:|
|    `checkLimitOf`     |                  Prints a message to output if total expenditure is nearing or has exceeded the spending limit                   |
|     `addCategory`     | Adds a new category to the tracker. If already in the tracker, then the total expenditure for that category is increased instead |
|   `deleteCategory`    |    Decreases total expenditure of a category. If that total drops to zero or below, the category is removed from the tracker     |
|    `editCategory`     |                   Updates the old and new category's total expenditure when an `Expense`'s category is changed                   |
| `setSpendingLimitFor` |                                         Sets a spending limit for a particular category                                          |


The `CategoryData` class has these key methods: 

|           Method            |                   Description                   |
|:---------------------------:|:-----------------------------------------------:|
| `increaseCurrExpenditureBy` |    Increments current total by a given price    |
| `decreaseCurrExpenditureBy` |    Decrements current total by a given price    |
|      `isNearingLimit`       | Checks if current total is 80% of limit or more |
|     `hasExceededLimit`      |   Checks if current total is more than limit    |


The `CategoryFilter` class has key methods for:

|           Method            |                                                    Description                                                    |
|:---------------------------:|:-----------------------------------------------------------------------------------------------------------------:|
|        `initMaxHeap`        |              Initialises a custom max heap that sorts categories by their current total expenditure               |
|   `getCategoriesFiltered`   | Sorts categories in the tracker, which are nearing or have exceeded the designated spending limit, into max-heaps |
| `displayFilteredCategories` |              Displays the categories in the provided category-filtered max-heap, in a preset format               |
| `displayExceededCategories` |                          Displays the categories that have exceeded its spending limits                           |
| `displayNearingCategories`  |                  Displays the categories that are nearing, but not exceeded, its spending limits                  |

After the user adds or edits an `Expense`, it alerts the user if the spending limit is approached or exceeded for that `Expenses`'s category.

The `CategoryStorage` class has key methods for:

|       Method        |                                Description                                 |
|:-------------------:|:--------------------------------------------------------------------------:|
| `trackCategoriesOf` |            Creates a category tracker based on an expense list             |
|    `loadFromCsv`    |              Loads spending limits from a CSV file, only for               |
|     `saveToCsv`     | Saves all categories and their corresponding spending limits to a CSV file |

After the user loads from file, all categories that have exceeded its designated spending limit will be displayed to the user, followed by all categories that have not exceeded its designated spending limit but are close to it.

<u>Design Considerations</u>

Each of the classes in this package handle a separate concern relating to category management (achieving Separation of Concerns Principle) and each focuses on a specific responsibility (achieving Single Responsibility Principle).

- `CategoryFacade` class
  - Acts as a Singleton because only one instance of this exists throughout the program's lifetime. The program instantiates this class once and passes this instance around through method parameters.
  - Acts as a facade because it provides the Command classes with a unified interface to the underlying category management classes, without the Command classes knowing of the complexity of the interactions between the category-related classes. 
    - This abstracts and encapsulates the category management information with an interface. 
    - This decouples classes outside the package from the classes inside.
  - Acts as a mediator because the category classes access information from the other classes through this class.
    - `CategoryFilter` accesses the tracker in `CategoryTracker` via the instance attribute in `CategoryFacade`.
    - `CategoryStorage` accesses the tracker in `CategoryTracker` via the instance attribute in `CategoryFacade`.
- `CategoryTracker` class
  - Contains exception handling when attempting to edit or delete a category that is not in the tracker. 
  - Uses a hashmap data structure to store `CategoryData` objects, providing quick access and updating of total expenses.
- `CategoryData` class
  - Setter methods checks for null inputs. Constructor methods also do, as they use those setters. 
- `CategoryFilter` class
  -  Uses priority queue data structures as max heaps to sort the category expenditure information
- `CategoryStorage` class
  - Methods are specifically related to saving and loading category-information to achieve SoC and SRP.

<u>Implementation Details</u>

The following diagram is a UML class diagram for `CategoryFacade`, `CategoryTracker`, `CategoryData`, `CategoryFilter` and `CategoryStorage`:

![CategoryClasses ClassUML.png](diagrams%2Fimages%2FCategoryClasses%20ClassUML.png)


### Exceptions and Logging

<u>Overview</u>

The program implements Exception handling and Logging with the `WheresMyMoneyException` and `Logging` classes.

<u>Implementation Details</u>

`WheresMyMoneyException` has various children classes, such as `StorageException` and `InvalidInputException`. 
These children classes are meant to provide more information on the error to the developer (beyond the message) such 
that exception handling in the program could be better targeted in the future.

The `Logging` class is implemented as a Singleton for ease of use. 
Developers can log down certain actions in the program by simply calling the relevant class method `log(Level, String)`. 

---

## Product scope

### Target user profile

Our target user profile is frugal and tech-savvy university students.

### Value proposition

The application will track how much a user is spending and what they are spending it on. 
The application can provide summaries and statistical insights to spending habits, optimised for people who prefer a CLI.

---

## User Stories

| Version | As a ...    | I want to ...                                                            | So that I can ...                                                  |
|---------|-------------|--------------------------------------------------------------------------|--------------------------------------------------------------------|
| v1.0    | user        | add expenses                                                             | track how much money I have spent so far                           |
| v1.0    | user        | delete expenses                                                          | clear wrong expenses to ensure expense tracking is accurate        |
| v1.0    | user        | edit expenses                                                            | correct inaccurate expenses to ensure expense tracking is accurate |
| v1.0    | user        | list expenses                                                            | track my spending                                                  |
| v1.0    | new user    | see usage instructions                                                   | refer to them when I forget how to use the application             |
| v2.0    | user        | save and load my expenses from a file                                    | retain memory of past expenses from past runs of the program       |
| v2.0    | frugal user | set spending limits for each category                                    | control my spending                                                |
| v2.0    | frugal user | be alerted when I exceed spending limits for each category and month     | control my spending                                                |
| v2.0    | user        | visualise my spending in the form of graphs                              | better conceptualise and understand spending trends and patterns   |
| v2.0    | user        | detailed statistical information about my spending (mean, highest, etc.) | better quantify and understand spending trends and patterns        |
| v2.0    | user        | add recurring expenses                                                   | automate expense tracking and make it more convenient              |

## Non-Functional Requirements

1. Technical Requirements: Any mainstream OS, i.e. Windows, macOS or Linux, with Java 17 installed.
2. Project Scope Constraints: The application should only be used for tracking. It is not meant to be involved in any form of monetary transaction.
3. Project Scope Constraints: Data storage is only to be performed locally.
4. Quality Requirements: The application should be able to be used effectively by a novice with little experience with CLIs.

---

## Glossary

* *Expense* - Payment made for various purposes. It has a price, category, description and the date added.

---

## Instructions for Testing

## Manual Testing

View the [User Guide](UserGuide.md) for the full list of commands and their related use case and expected outcomes.

### Recurring Expenses

Recurring expenses can be tested by setting its date. Below is an example.

```
add /recur /price 1.00 /category A /description A /date 01-01-2024 /frequency daily
add /recur /price 10.00 /category B /description B /date 01-01-2024 /frequency weekly
add /recur /price 100.00 /category C /description C /date 31-01-2024 /frequency monthly
save
load
```

## JUnit Testing

JUnit tests are written in the subdirectory `test` and serve to test key methods part of the application.

## Text UI Testing

Files relating to Text UI Testing can be found in the directory text-ui-test.

When running tests on a Windows system, run the following command from the specified directory:

```
./runtest.bat
```

When running tests on a UNIX-based system, run the following command from the specified directory:

```
./runtest.sh
```