# Tsou Teng Yuan - Project Portfolio Page

## Overview

Finance Buddy is a software that allows university students to log their daily expenditures to help manage their
budgets. Users can add, delete and edit expenditure logs into the app as well as list out all their logged 
transactions. Users are also able to set a monthly budget for themselves in the app, and the app will notify them if
they have exceeded, or are close to exceeding, their budget.

&nbsp; 

### Summary of Contributions

- **Feature:** Edit Command (co-contribution)
  - <ins>What it does:</ins>  
    Allows users to edit existing transaction properties, such as description, date, amount, and categories, making it easy to update or correct transaction details.

  - <ins>Justification:</ins>  
    Provides users with the flexibility to modify a transaction after it has been created. 
    This feature is essential for maintaining accurate records, as users may need to adjust details due to mistakes or changes in transaction information. It enhances user control and ensures the data remains relevant and correct.

&nbsp; 

- **Feature:** Storage
  - <ins>What it does:</ins>  
    Saves the `FinancialList` and `Budget` to files, preserving all user-recorded financial data and budget.

  - <ins>Justification:</ins>  
    Enables users to retain their financial data even after the program is closed, ensuring data persistence. 
    This feature is crucial for any financial management tool as it allows users to access their records and updates without the need to re-enter data each time they open the application, enhancing convenience and data reliability.
&nbsp; 

- **Feature:** Exit Command
  - <ins>What it does:</ins>  
    Terminates the FinanceBuddy application, allowing users to gracefully exit the program when they are done using it.
  
  - <ins>Justification:</ins>  
    Provides users with the ability to end the program session, ensuring they have full control over when they wish to close the application. 
    This is essential for user experience, as it allows users to end their session efficiently without needing to force close or terminate the app through other means.

&nbsp; 

- **Feature:** ViewAllExpenses Command (co-contribution)
  - <ins>What it does:</ins>  
    The `ViewAllExpenses` command provides users with a comprehensive overview of all their recorded expenses. 
    By simply executing this command, users can access a complete list of expenses they have logged, including details such as the amount spent, date, and purpose or category of the expense. 
    This command is especially useful for users who want to review their past expenditures in one place and get a clear picture of their financial habits.
  
  - <ins>Justification:</ins>  
    The `ViewAllExpenses` feature is a fundamental component of any expense-tracking application. 
    It serves as a quick and efficient way for users to gain insight into their spending patterns and track their financial activities. 
    By allowing users to see all expenses in a single view, this feature helps them analyze their financial behavior, identify areas where they might be overspending, and make informed budgeting decisions. 
    Additionally, having all expenses readily accessible improves the user experience, as it removes the need for manual searching or filtering to retrieve past transactions, making it more convenient and user-friendly.

&nbsp; 

- **Documentations:**
  - <ins>User Guide</ins> 
    - Edit command
    - Saving Data
  - <ins>Developer Guide</ins> 
    - Storage component 
    - SDs for loading from files
    - Use cases
    - NFRs
    - Glossary

&nbsp;  
  
- **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=TSOU&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20) 

&nbsp; 