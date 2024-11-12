# Liu Hao's Project Portfolio Page

## Overview

CliRental is a CLI-based application designed to streamline the operations of car rental companies by efficiently managing customers, cars, and rental transactions. Written in Java with approximately 4k lines of code, CliRental replaces traditional pen-and-paper methods, offering car rental managers a reliable and user-friendly tool for handling large volumes of data.

## Summary of Contributions

### <u>Features Added</u>

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

### <u>Code Contribution</u>

Code contributed: [AaronZZ10's Contributions](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=aaronzz10&breakdown=true)