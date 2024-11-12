# Ang Zi Le, Telfer - Project Portfolio Page

## Overview
BudgetBuddy is a desktop application for managing finance and anything related to it, optimised for
use via a Command Line Interface (CLI). It is written in Java, and has around 8kLoC.

---

### Summary of Contributions
I was primarily responsible for implementing the core features related to managing expenses and incomes. This involved creating command classes to process user input, designing the parser to interpret user commands, and ensuring that the user interface (CLI) was intuitive and efficient.

---

### Code Contribution
List of code contributed: [Reposense Code Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=telferang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20)

---

### Project management
- Setting up the GitHub team repo

---

### Feature Implemented
Here is the list of feature implemented:

#### 1. Add expense and income entries
- **What it does:** Implemented a command-driven approach to allow users to easily add their income and expense records. The program processes the user's input, validates the data, and stores the entries in a structured manner.
- **Justification:** This feature is essential for users to keep a detailed record of their expenses and incomes.

#### 2. Delete expenses and incomes entries
- **What it does:** Enabled the user to delete entries that were incorrectly added, ensuring the integrity of their records. The command checks for valid indexes and removes the corresponding entry from the data store.
- **Justification:** This feature is necessary if the user added the wrong expense or income

#### 3. List all the expense and income entries
- **What it does:** Displays a comprehensive list of all expense or income entries recorded by the user, including details like description, amount, date, and category(only for expense).
- **Justification:** This feature gives users a full overview of their spending, making it easier to track patterns, spot areas where they can save, and analyze spending behavior over time.

#### 4. Show a list of the budget remaining
- **What it does:** Displays the total remaining budget for the current month, after subtracting all the expenses from the corresponding budget entries.
- **Justification:** It enables users to get a quick overview of their financial status, helping them make informed spending decisions for the rest of the month.

#### 5. Show the budget remaining for the month and category after adding an expense
- **What it does:** After each new expense entry, the program automatically shows the updated budget remaining for the month as well as for the specific expense category.
- **Justification:** Providing this immediate feedback allows users to track spending in real-time, encouraging better budget management and avoiding overspending in specific categories.

---

### Enhancements Implemented

#### 1. Modify the storage to reject invalid entries
- **Details**: Reject entries with incorrect number of parameter or entries with invalid parameter such as negative amount or invalid date.
- **Impact**: By rejecting invalid entries upfront, I ensured the stability of the application. Users are now prevented from adding records with negative values or incorrect date formats, improving overall data integrity.

#### 2. Modify the amount to only accept and show 2 decimal place
- **Details**: All amounts are consistently displayed and stored to two decimal places.
- **Impact**: This enhancement addressed issues with floating-point precision errors, ensuring all amounts were consistent and displayed correctly, thus improving the accuracy of financial records.

---

### Contribution to UG
Documented the following portions:
- `Add Expense`, `Delete Expense`, `Add Income`, `Delete Income` and `List Remaining Budget` features, along with command summary.
  ([PR #114](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/114))

---

### Contribution to DG

- **Documented  class design in Section 3:**
    - Section 3.6 `Expense and Income Class`

- **Documented Features in Section 4:**
    - Section 4.1.1 `Add Expense`
    - Section 4.1.2 `Delete Expense`
    - Section 4.2.1 `Add Income`
    - Section 4.2.2 `Delete Income`
    - Section 4.3.4 `List Remaining Budget`

- **UML Sequence Diagrams:**
    - Created diagrams for the following features:
        - Section 3.6 `Expense and Income Class`
        - Section 4.1.1 `Add Expense`
        - Section 4.1.2 `Delete Expense`
        - Section 4.2.1 `Add Income`
        - Section 4.2.2 `Delete Income`
        - Section 4.3.4 `List Remaining Budget`

**Relevant Pull Requests**:
([PR #137](https://github.com/AY2425S1-CS2113-W10-1/tp/pull/137))

---

### Community
- Reported bugs and suggestions for other groups in the class. ([Bug Report](https://github.com/telferang/ped/issues))
- Provided feedback on pull requests for other groups. ([PR Feedback](https://github.com/nus-cs2113-AY2425S1/tp/pull/25))

