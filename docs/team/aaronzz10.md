# Liu Hao's Project Portfolio Page

## Project: CLIRental

## Overview

**CliRental** is a command-line application designed for car rental managers to efficiently
manage and track customers, cars, and rental transactions in a centralized platform. 
Built to replace traditional pen-and-paper methods, CliRental provides a user-friendly, 
reliable solution for handling large volumes of data, streamlining operations for managers 
with quick data entry and tracking capabilities. Developed in Java with approximately 
4,000 lines of code (LOC), this tool serves as an effective desktop app tailored to the
core needs of car rental businesses.

## Features Implemented
**Feature 1** : Implement Transaction Management Commands

- **What it does** : Introduces commands to manage rental transactions, including removing transactions, marking them as complete or incomplete, listing completed or uncompleted transactions, and finding transactions by customer.

- **Justification**: Efficient transaction management is crucial for tracking the status of rentals, ensuring cars are available when needed, and maintaining accurate records for billing and reporting purposes.

- **Highlights**:
    - **remove-tx**: Allows users to delete specific transactions using the transaction ID.
    - **mark-tx**: Enables marking a transaction as completed, indicating the rental period has ended.
    - **unmark-tx**: Allows users to revert a transaction's status to incomplete if needed.
    - **list-tx-completed**: Lists all transactions that have been marked as completed.
    - **list-tx-uncompleted**: Displays all transactions that are still active or incomplete.
    - **find-tx-by-customer**: Provides the ability to search for all transactions associated with a specific customer.

**Feature 2** : Develop Comprehensive Unit Tests for Transaction Classes

- **What it does** : Implements JUnit tests for all methods within the `Transaction` and `TransactionList` classes to ensure functionality and reliability.

- **Justification**: Unit testing is essential for verifying that individual components of the application work as intended, facilitating maintenance, and preventing regressions during future development.

- **Highlights**:
    - **Transaction Class Tests**: Tests cover methods such as adding, removing, marking, and unmarking transactions, ensuring each function behaves correctly under various scenarios.
    - **TransactionList Class Tests**: Verifies the integrity of the transaction list operations, including data consistency and correct handling of edge cases.
    - **Automated Testing**: Integrates tests into the development workflow, enabling continuous verification of code changes and enhancing overall code quality.

**Feature 3** : Implement Comprehensive Input/Output (IO) Testing

- **What it does** : Conducts full IO testing for the application, verifying that commands entered via the CLI produce the expected outputs. Tests ensure that user interactions with features like transaction management and data listing work as designed and meet the application's functionality requirements.

- **Justification**: IO testing is critical for confirming that the application responds correctly to user commands, providing reliable and expected results. This testing approach validates the end-to-end functionality of the application from the user's perspective, ensuring a smooth and predictable user experience.

- **Highlights**:
  - **Command Accuracy**: Tests cover all core commands, including transaction addition, removal, completion status toggling, and customer-specific searches, to confirm correct outputs for each operation.
  - **End-to-End Verification**: Ensures that user commands execute successfully and return accurate, user-friendly information, maintaining data consistency throughout.
  - **CLI Simulation**: Simulates actual CLI interactions to replicate real user scenarios, verifying the app’s robustness in handling various user inputs and sequences.

## <u>Code Contribution</u>

Code contributed: [AaronZZ10's Contributions](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=aaronzz10&breakdown=true)

## Documentation

### User Guide (UG) Contributions
- Added documentation for features:
  - `list-users`
  - `remove-tx`
  - `mark-tx`
  - `unmark-tx`
  - `list-txs-completed`
  - `list-txs-uncompleted`
  - `find-txs-by-customer`

### Developer Guide (DG) Contributions
- Added overview for Transaction Completion Management and Retrieval Features
- Added class-level design for TransactionList and Transaction class

## Community

### Team-Based Task Contributions
- Improved code quality and documentation by organizing format, adding divider lines for readability, and creating a contents summary for the User Guide.
- Reviewed teammates' pull requests, providing feedback to enhance code quality and functionality.
- Approved pull requests as needed to support workflow and project progression.
- Identified bugs and suggested improvements in other teams' Developer Guides (DGs) during peer review.

