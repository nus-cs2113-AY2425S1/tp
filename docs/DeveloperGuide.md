# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

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

### Storage
The `Storage` class has been implemented to store the `FinancialList` into a file. 
In that case, user can restore his/her progress even if they have terminate the program.

The function `updateStorage` should be called whenever the `FinancialList` in an `AppUi` object. 
It will overide the file with the up-to-date `FinancialList` that has been converted to string.

*more guid coming up*

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
