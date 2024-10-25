# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

---

### FinancialList Component

#### Overview

The `FinancialList` class is responsible for storing and managing all financial entries in the application,
including both `Expense` and `Income`. The `FinancialEntry` class acts as the base class, with `Expense` and `Income` 
classes extending it to represent specific types of transactions.

#### Class Structure

The `FinancialList` class contains the following attributes:
- entries: A list of FinancialEntry objects representing all stored transactions.

The `FinancialEntry` class contains the following attributes:
- **description**: A string describing the transaction.
- **amount**: A double representing the amount of the transaction.
- **date**: A LocalDate object representing the date of the transaction.

The Expense and Income classes extend FinancialEntry, each maintaining the base attributes while
adding context to the type of financial entry.

### Implementation Details

#### FinancialList Class Diagram
- The FinancialList stores and manages FinancialEntry objects, including Expense and Income.
---

### Commands

#### Overview

The abstract class `Command` has been implemented to introduce an additional layer
of abstraction between the `AppUi` class and command execution,
allowing for separation of handling command keywords and executing commands.

The diagram below shows the inheritance of the `Command` class.

### Exceptions and Logging

An exception class `FinanceBuddyException` is thrown when users use the product wrongly.
Exceptions are caught at the nearest instance that they occur.

*Insert code snippet here.*

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
