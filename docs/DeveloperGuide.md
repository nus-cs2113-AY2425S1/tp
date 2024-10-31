# WheresMyMoney Developer Guide


## Table of Contents
- [Developer Guide](#wheresmymoney-developer-guide)
    - [Table of Contents](#table-of-contents)
    - [Acknowledgements](#acknowledgements)
    - [Design & Implementation](#design--implementation)
    - [Product Scope](#product-scope)
        - [Target User Profile](#target-user-profile)
        - [Value Proposition](#value-proposition)
    - [User Stories](#user-stories)
    - [Non-Functional Requirements](#non-functional-requirements)
    - [Glossary](#glossary)
    - [Instructions for Testing](#instructions-for-testing)
        - [Manual Testing](#manual-testing)
        - [JUnit Testing](#junit-testing)

<div style="page-break-after: always;"></div>

---

## Acknowledgements

WheresMyMoney uses the following libraries

1. [OpenCSV](https://opencsv.sourceforge.net/) - Used for saving/ loading expenses

WheresMyMoney uses the following tools for development:

1. [JUnit](https://junit.org/junit5/) - Used for testing.
2. [Gradle](https://gradle.org/) - Used for build automation.

---

## Design & Implementation


Design and Implementation has been broken down into various sections, each tagged for ease of reference:

- [Architecture](#architecture)
- [UI and Parser](#ui-and-parser)
- [Commands](#commands)
- [Storage](#storage)
- [Expense and Expense List](#expense-and-expense-list)
- [Expense Filter](#expense-filter)
- [Date and Time Handling](#date-and-time-handling)
- [Exceptions and Logging](#exceptions-and-logging)
- [Recurring Expense and Recurring Expense List](#recurring-expense-and-recurring-expense-list)

### Architecture
A high-level overview of the system is shown in the Architecture Diagram below.

![ArchitectureDiagram.png](diagrams%2Fimages%2FArchitectureDiagram.png "Architecture Diagram of WheresMyMoney")

This architecture consist of: 
1. `UI`, `Main`, `Parser`, and `Command` classes: These classes stand between the user and the internal processing of the software.
2. `Expense`, `ExpenseList`, `ExpenseFilter` classes: Model expenses that commands can interact with.
3. `Storage` class: Stores information between sessions.
4. Logger and other utility classes: Provide extra functionalities for the software.
### UI and Parser

<u>Overview</u>

The Ui class handles I/O operations such as displaying messages and reading user input.
The Parser parses user input and returns the relevant Command Object. 
Both these classes are important for allowing the User to interact with the application.

<u>Methods</u>

The `Ui` class has the following key methods:

| Method           | Description                   |
|------------------|-------------------------------|
| `displayMessage` | Displays a message (`String`) |
| `getUserInput`   | Reads User Input              |

The Parser class has the following key method:

| Method                 | Description                                               |
|------------------------|-----------------------------------------------------------|
| `parseInputToCommand`  | Parses a given user input and returns the related Command |


<u>Design Considerations</u>

Low-level I/O operations (eg. stdio) are consolidated in the Ui class such that we can easily switch the I/O methods by 
modifying only the Ui class. This would make it easier to port the application to other platforms if needed.

Ui class is used as part of exception handling for displaying of error messages to the user for feedback.

The Parser also has some considerations such as
1. Restricted arguments which should not be used by other developers in their commands. These include
    1. `/command` -> used for the main command keyword
    2. `/main` -> used for the main text argument right after the command keyword
3. Any duplicate arguments will throw an InvalidInputException
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
    3. Leading and Trailing spaces are ignored, but additional spaces within values (eg. `main  value`) are counted 

### Commands

#### Overview

The abstract Command class has been implemented to introduce an additional layer of abstraction between I/O and command execution, allowing for separation of handling command keywords and executing commands.


<u>Implementation Details</u>

The following diagram is an inheritance diagram for Command and its children classes. 
This has been heavily simplified and only shows the key commands.

![CommandInheritance.png](diagrams%2Fimages%2FCommandInheritance.png)


The following diagram is a sequence diagram for execution of Command.

![CommandExecutionSequence.png](diagrams%2Fimages%2FCommandExecutionSequence.png)

Commands interact with `UI` and `Parser` classes via `Main`, as illustrated in the following class diagram:

![UiToCommand.png](diagrams%2Fimages%2FUiToCommand.png)

### Storage

<u>Overview</u>

Majority of the storage handling occurs in the ExpenseList class. 
This is to keep the storage tightly coupled with the expense data and ensures that when the expense list is updated,
the storage format is updated accordingly.

OpenCSV is used for the storage format to allow for reliable handling of CSV files. 
The CSV file format was used to allow expenses to be easily exported/ imported to and from other programs (like Excel).

In the future, if the state expands to be greater than an ExpenseList, it would be better for a separate class to be
created for solely handling Storage. 

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

<u>Design Considerations</u>

The setters in `Expense` class checks for null and blank.
The `Expense` constructors also do, as they use those setters.

The nontrivial methods in `ExpenseList` class contain some sort of exception handling. 

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

The `DateUtils` class provides utility methods to handle date formatting, validation and conversion. 

The `DateUtils` class has no notable methods.

<u>Implementation Details</u>

The `DateUtils` class is implemented as a Singleton as its methods are common to all other classes that require it.

### Recurring Expense and Recurring Expense List

<u>Overview</u>

The `RecurringExpense` class extends from the `Expense` class and it represents an indivual recurring expense with a price, description, category, last date added and a frequency.

The `RecurringExpenseList` class extends from the `ExpenseList` class and it manages a collection of `RecurringExpense` objects.
It allows for addition, editing and deletion of expenses.

<u>Methods</u>

The `RecurringExpense` class has no notable methods.

The `RecurringExpenseList` class has the following key methods:

|          Method          |                Description                 |
|:------------------------:|:------------------------------------------:|
|  `addRecurringExpense`   |    Adds a recurring expense to the list    |
| `deleteRecurringExpense` | Removes a recurring expense from the list  |
|  `editRecurringExpense`  |   Edits a recurring expense in the list    |
| `loadFromCsv` | Adds the appropriate amount of `Expense` objects with the correct date to the `ExpenseList`|


<u>Design Considerations</u>

Since the programme does not have an auto-save function upon closing the programme or auto-load when starting the programme, it is up to the user to save their work and to load it again.

To minimise the amount of checks that need to be done, the recurring expenses are only added after the user calls the `load` command.

<u>Implementation Details</u>

Below is the UML class diagram for `RecurringExpense` and `RecurringExpenseList`:

![RecurringExpenseAndRecurringExpenseList.png](diagrams%2Fimages%2FRecurringExpenseAndRecurringExpenseList.png "UML Class Diagram for RecurringExpense and RecurringExpenseList class")

Below is the sequence diagram for when the user calls the `load` command.

![RecurringExpenseLoadFromCsvSequence.png](diagrams%2Fimages%2FRecurringExpenseLoadFromCsvSequence.png "UML Sequence Diagram for calling load command")

### Exceptions and Logging

<u>Overview</u>

The program implements Exception handling and Logging with the WheresMyMoneyException and Logging classes.

<u>Implementation Details</u>

WheresMyMoneyException has various children classes, such as `StorageException` and `InvalidInputException`. 
These children classes are meant to provide more information on the error to the developer (beyond the message) such 
that exception handling in the program could be better targetted in the future.

The Logging class is implemented as a Singleton for ease of use. 
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

| Version | As a ...    | I want to ...                                                                  | So that I can ...                                                  |
|---------|-------------|--------------------------------------------------------------------------------|--------------------------------------------------------------------|
| v1.0    | user        | add expenses                                                                   | track how much money I have spent so far                           |
| v1.0    | user        | delete expenses                                                                | clear wrong expenses to ensure expense tracking is accurate        |
| v1.0    | user        | edit expenses                                                                  | correct inaccurate expenses to ensure expense tracking is accurate |
| v1.0    | user        | list expenses                                                                  | track my spending                                                  |
| v1.0    | new user    | see usage instructions                                                         | refer to them when I forget how to use the application             |
| v2.0    | user        | save and load my expenses from a file                                          | retain memory of past expenses from past runs of the program       |
| v2.0    | frugal user | set spending limits for each category and month                                | control my spending                                                |
| v2.0    | frugal user | be alerted when I exceed spending limits for each category and month           | control my spending                                                |
| v2.0    | user        | visualise my spending in the form of graphs                                    | better conceptualise and understand spending trends and patterns   |
| v2.0    | user        | detailed statistical information about my spending (such as mean, median etc.) | better quantify and understand spending trends and patterns        |
| v2.0    | user        | add recurring expenses                                                         | automate expense tracking and make it more convenient              |

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

View the [User Guide](UserGuide.md) for the full list of UI commands and their related use case and expected outcomes.

## JUnit Testing

JUnit tests are written in the subdirectory `test` and serve to test key methods part of the application.
