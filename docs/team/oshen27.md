# Shen Jiaming - Project Portfolio Page

### Project: uNivUSaver

uNivUSaver is a CLI-based software that helps students to develop a better habit of managing money, which will help you avoid using up your money before the end of month. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added `AddIncomeCommand` to allow users to add income records.
    * **What it does**: Enables users to add income details such as description, amount, and date, which are stored in the transaction list.
    * **Highlights**: This feature provides essential income tracking, which improves the budget management capabilities of the application.

* **New Feature**: Added `AddExpenseCommand` to allow users to track expenses.
    * **What it does**: Enables users to add expense entries with details like description, amount, date, and category.
    * **Highlights**: Expense tracking with categorization allows users to analyze their spending in different areas, giving a more comprehensive view of their financial habits.

* **New Feature**: Developed `ViewTotalCommand` to calculate the net total of income minus expenses.
    * **What it does**: Displays the user’s total income and total expenses, along with the net balance, allowing for an overview of budget standing.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2425s1.github.io/tp-dashboard/?search=Oshen27&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-09-20)

* **Enhancements to existing features**:
    * Refactored existing command handling to improve code quality, ensuring adherence to the Single Responsibility Principle (SRP) for easier future development.
    * Integrated transaction tracking with the `BudgetTracker` class, enabling users to set and monitor monthly budgets.

* **Documentation**:
    * **User Guide**:
        * Documented the features implemented in v1.0, such as `add-income`, `add-expense`, and `view-total`.
    * **Developer Guide**:
        * Added detailed explanations and UML diagrams for `AddIncomeCommand`, `AddExpenseCommand`, and `ViewTotalCommand`.

* **Community**:
    * **Pull Request Reviews**: Provided feedback on team members’ pull requests, particularly for command parsing and budget management features.
    * **Mentorship**: Assisted team members in resolving issues related to command validation and argument parsing.

* **Tools**:
