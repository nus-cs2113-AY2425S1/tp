# Kenneth Ang - Project Portfolio Page

## Overview
BudgetBuddy - BudgetBuddy is a desktop application for managing finance and anything related to it, optimised for
use via a Command Line Interface (CLI)

## Summary of Contributions

### Code Contribution
List of code contributed : [Reposense Code Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=w10-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20&tabOpen=true&tabType=authorship&tabAuthor=Chinorea&tabRepo=AY2425S1-CS2113-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Feature Implemented
Here is the list of feature implemented:

| Feature                     | Purpose                                                                                                                                            | 
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| List Expenses with filters  | Allow users to view all past expenses with total value of expenses logged within the application, as well as filter based on category and/or month |
| List Incomes with filters   | Allow users to view all past incomes with total value of income logged within the application, as well as filter based on month                    | 
| Edit a Pre-Existing Expense | Allow Users to edit and update an existing expense entry without creating a new entry                                                              |
| Edit a Pre-Exisitng Income  | Allow Users to edit and update an existing income entry without creating a new entry                                                               |

| Class                  | Functionality                                          | 
|------------------------|--------------------------------------------------------|
| `EditExpenseValidator` | Validate input from user specialised for Edit Expenses |
| `EditIncomeValidator`  | Validate input from user specialised for Edit Income   |  
| `ListIncomeValidator`  | Validate input from user specialised for List Income   |
| `ListExpenseValidator` | Validate input from user specialised for List Expense  |
| `EditExpenseCommand`   | Handling of user input values for feature execution    |
| `EditIncomeCommand`    | Handling of user input values for feature execution   |
| `ListIncomeCommand`    | Handling of user input values for feature execution   |
| `ListExpenseCommand`   | Handling of user input values for feature execution   |

### Enhancement Implemented
* Unit Testing of feature implemented above.
  * Input Validation: Test cases to validate input and ensure error messages are returned for invalid entries.
  * Boundary and Edge Case Tests: Test cases check for negative values, null values and other edge cases to ensure proper handling.
  * Error Handling: Test cases confirm that appropriate item or objects are returned when specific error occurs.

* Two Tier confirmation before editing entries.
  * Enable users to confirm selected entry before editing directly.
  * Simple exit procedure if chosen entry is incorrect.

### Contribution to UG
Documented the following portions:
* `ListExpense`, `ListIncome`, `EditIncome` and `EditExpense` feature portions and command summary,
* Introduction portion.


### Contribution to DG
Documented the following portions:
* `ListExpense`, `ListIncome`, `EditIncome` and `EditExpense` feature portions in section 4 of the DG
* Prequisites, Introduction, Non-Functional Requirements and Glossary Sections of the DG
* UML Sequence Diagrams for `EditValidatorClass`, `ListExpense`, `EditExpense`, `ListIncome` portions of the DG


### Contributions Beyond the Project Team
- **Bug Finding and Suggestions:** [Bug Report](https://github.com/Chinorea/ped/issues)
- **PR Review for Other Teams:** [Pull Request](https://github.com/nus-cs2113-AY2425S1/tp/pull/8/files/b0d61ae8c3b68a0ae2ce7bd0584483847eb39b8d#r1821762776)
