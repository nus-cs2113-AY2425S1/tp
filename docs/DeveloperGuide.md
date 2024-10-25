# WheresMyMoney Developer Guide


## Table of Contents
- [Developer Guide](#wheresmymoney-developer-guide)
    - [Table of Contents](#table-of-contents)
    - [Acknowledgements](#acknowledgements)
    - [Design \& Implementation](#design--implementation)
    - [User Stories](#user-stories)
    - [Product scope](#product-scope)
        - [Target user profile](#target-user-profile)
        - [Value proposition](#value-proposition)
    - [Non-Functional Requirements](#non-functional-requirements)
    - [Glossary](#glossary)
    - [Instructions for Testing](#instructions-for-testing)
        - [Manual Testing](#manual-testing)
        - [JUnit Testing](#junit-testing)

<div style="page-break-after: always;"></div>

## Acknowledgements

WheresMyMoney uses the following libraries

1. [OpenCSV](https://opencsv.sourceforge.net/) - Used for saving/ loading expenses

WheresMyMoney uses the following tools for development:

1. [JUnit](https://junit.org/junit5/) - Used for testing.
2. [Gradle](https://gradle.org/) - Used for build automation.

## Design & implementation


Design and Implementation has been broken down into various sections, each tagged for ease of reference:

- [UI and Parser](#ui-and-parser)
- [Commands](#commands)
- [Expense and Expense List](#expense-and-expense-list)

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

### Commands

#### Overview

The abstract Command class has been implemented to introduce an additional layer of abstraction between I/O and command execution, allowing for separation of handling command keywords and executing commands.


<u>Implementation Details</u>

The following diagram is an inheritance diagram for Command and its children classes. 
This has been heavily simplified and only shows the key commands.

![CommandInheritance.png](diagrams%2Fimages%2FCommandInheritance.png)


The following diagram is a sequence diagram for execution of Command.

![CommandExecutionSequence.png](diagrams%2Fimages%2FCommandExecutionSequence.png)

### Expense and Expense List

<u>Overview</u>

The `Expense` class represents an individual expense with a price, description and a category.

The `ExpenseList` class manages a collection of `Expense` objects. 
It allows for the addition, editing and deletion of expenses.

<u>Methods</u>

The `Expense` class has no notable key methods.

The `ExpenseList` class has the following key methods: 

|     Method      |            Description            |
|:---------------:|:---------------------------------:|
|  `addExpense`   |    Adds an expense to the list    |
| `deleteExpense` | Removes an expense from the list  |
|  `editExpense`  |   Edits an expense in the list    |

### Exceptions and Logging

<u>Overview</u>

The program implements Exception handling and Logging with the WheresMyMoneyException and Logging classes.

<u>Implementation Details</u>

WheresMyMoneyException has various children classes, such as `StorageException` and `InvalidInputException`. 
These children classes are meant to provide more information on the error to the developer (beyond the message) such 
that exception handling in the program could be better targetted in the future.

The Logging class is implemented as a Singleton for ease of use. 
Developers can log down certain actions in the program by simply calling the relevant class method `log(Level, String)`. 


## Product scope

### Target user profile

Our target user profile is frugal and tech-savvy university students.

### Value proposition

The application will track how much a user is spending and what they are spending it on. 
The application can provide summaries and statistical insights to spending habits, optimised for people who prefer a CLI.

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

## Glossary

* *Expense* - Payment made for various purposes. It has a price, category and description.

## Instructions for Testing

## Manual Testing

View the [User Guide](UserGuide.md) for the full list of UI commands and their related use case and expected outcomes.

## JUnit Testing

JUnit tests are written in the subdirectory `test` and serve to test key methods part of the application.
