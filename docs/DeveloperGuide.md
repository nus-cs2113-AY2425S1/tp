# Developer Guide for SpendSwift

## Design & Implementation
### Core Classes Overview
![CoreManagement](diagrams/CoreManagement.png)
#### TrackerData

TrackerData centralizes and manages the lists of categories, expenses, and budgets, 
providing a unified data source for other classes.

Attributes:

   - List<Category> categories: Stores all categories.
   - List<Expense> expenses: Stores all expenses.
   - List<Budget> budgets: Stores all budget limits.
   
Methods:

   - getCategoryList(): Returns the list of categories.
   - getExpenseList(): Returns the list of expenses.
   - getBudgetList(): Returns the list of budgets.
   
   Usage:
TrackerData is utilized by the manager classes to store and retrieve categorized data. 
Each manager accesses TrackerData to perform operations.

#### CategoryManager
Handles all category-related operations, including adding and formatting categories.

Attributes and Methods:

- addCategory(name: String): Adds a new category.
- formatCategoryInput(input: String): Formats category input, ensuring consistency.

Relationship: 
- Dependency: Accesses TrackerData to add and retrieve categories.

#### BudgetManager
Handles budget-related functionalities like setting and viewing budget limits for categories.

Attributes and Methods:

- addBudgetLimit(category: String, limit: double): Adds a budget limit for a specific category.
- viewBudget(): Views current budget limits and spending against them.
- resetMonthlyBudget(): Resets budgets at the start of each month.

Relationship:
- Dependency: Accesses TrackerData to manage budget data associated with categories.

#### ExpenseManager
Manages expenses, including adding, deleting, and viewing expenses categorized by spending areas.

Attributes and Methods:

- addExpense(name: String, amount: double, category: String): Adds a new expense.
- deleteExpense(index: int): Deletes an expense by index.
- viewExpensesByCategory(): Displays expenses grouped by category.

Relationship:
- Dependency: Accesses TrackerData to add, delete, and view expenses categorized by spending areas.

### Command Parsing and Input Handling
![ParserManagement](diagrams/ParserManagement.png)
#### Parser
The Parser class interprets user input commands, delegating them to appropriate manager classes for processing.

Methods:
- parseCommand(input: String): Parses the command and creates a Command object based on the input.

Relationship:
- Composition: Uses InputParser for handling command-specific parsing.
- Dependency: Delegates tasks to CategoryManager, BudgetManager, and ExpenseManager.

#### InputParser
InputParser processes specific components within commands (such as names, amounts, categories) 
and breaks down inputs for more manageable handling.

Methods:
- parseComponent(input: String): Breaks down input components into a map for easy retrieval.

### Expense, Category, and Budget Entities
![Entities](diagrams/Entities.png)
#### Expense
Represents an expense with its name, amount, and associated category.

Attributes:
- String name: The name of the expense.
- double amount: The expense amount.
- Category category: Category linked to the expense.

#### Category
Represents a category, allowing expenses and budgets to be organized under specific areas.

Attributes:
- String name: The name of the category.

#### Budget
Represents a budget limit associated with a category, enabling users to track and manage spending.

Attributes:

- double limit: Budget limit for the category.
- Category category: The category associated with the budget.
Relationship:

- Each Expense is linked to exactly one Category, while each Budget is also associated with one Category.

### Sequence Diagrams
#### add-expense
![Add Expense Sequence Diagram](diagrams/AddExpense.png)

#### add-category
![Add Category Sequence Diagram](diagrams/AddCategory.png)

#### delete-expense
![Delete Expense Sequence Diagram](diagrams/DeleteExpense.png)

#### tag-expense
![Tag Expense Sequence Diagram](diagrams/TagExpense.png)

#### set-budget
![Set Budget Sequence Diagram](diagrams/SetBudget.png)

#### view-budget
![View Budget Sequence Diagram](diagrams/ViewBudget.png)

#### view-expenses
![View Expenses Sequence Diagram](diagrams/ViewExpenses.png)

#### toggle-reset
![Toggle Auto Reset Sequence Diagram](diagrams/ToggleAutoReset.png)


### Category
#### Purpose
Represents a category for expenses.
#### Operations
- `getName()`: Returns the category name.
- `toString()`: Provides the string representation of the category.

### Expense
#### Purpose
Represents an individual expense within the system.
#### Operations
- `getName()`, `getAmount()`: Retrieve the expense's name and amount.
- `getCategory()`, `setCategory(Category)`: Manage the expense's category association.
- `formatAmount()`: Formats the expense amount for display.

### Budget
#### Purpose
Handle monetary constraints per category.
#### Operations
- `setLimit(double)`: Ensures limits are non-negative.
- `formatLimit(double)`: Formats the budget limit for display.

### ExpenseTracker
#### Purpose
Track and manage expenses and categories.
#### Operations
##### Adding Expenses and Categories
- `addExpense`: Adds an expense after verifying or creating the necessary category.
- `addCategory`: Adds a new category if it does not exist.

##### Monthly Budget Reset Functionality
- `toggleAutoReset`: Toggles automatic budget resets on or off.
- `checkAndResetBudgets`: Checks for a new month and triggers budget resets if enabled.
- `resetBudgets`: Resets all budgets to their predefined limits.

##### Viewing and Organizing Data
- `viewExpensesByCategory`: Displays expenses organized by categories.
- `viewBudget`: Displays budget limits and current expenditures for each category.

##### Modifying Data
- `deleteExpense`: Deletes an expense based on its index.
- `tagExpense`: Reassigns an expense to a different category based on user input.

### Duke
#### Purpose
Interface for command-line interactions.
#### Main flow
- The system starts and displays a greeting.
- Continuously processes user commands until "bye".
- Directly invokes methods from ExpenseTracker based on input.

## Product Scope
<!-- @@author glenda-1506 -->
### Target User Profile
SpendSwift is designed for budget-conscious individuals who prefer a simple, efficient way to track expenses and manage budgets using text commands.

### Value Proposition
SpendSwift provides a fast, text-based solution for managing finances, eliminating the complexity of traditional budgeting tools.

## User Stories
<!-- @@author glenda-1506 -->  

| Version | As a...               | I want to...                                                          | So that I can...                                         |
|---------|-----------------------|-----------------------------------------------------------------------|----------------------------------------------------------|
| v1.0    | Budget-conscious user | Quickly log an expense using a typed command (e.g., add 50 groceries) | track my spending with easy input                        |
| v1.0    | Budget-conscious user | View my budget for all categories                                     | see how much I could spend                               |
| v1.0    | Budget-conscious user | Set a monthly reset for my budget tracking                            | start each month fresh with my budgeting                 |
| v1.0    | Budget planner        | View all my expenses                                                  | monitor what I have been spending on                     |
| v1.0    | Frequent user         | Set a budget limit for each category (e.g., set budget 200 groceries) | limit my spending according to categories                |
| v1.0    | Frequent user         | Delete an expense entry (e.g., delete 5)                              | quickly correct mistakes                                 |
| v1.0    | Frequent user         | Categorize expenses (e.g., add category food)                         | customize my expense tracking to better manage my budget |
| v2.0    | New user              | See commands easily                                                   | quickly familiarise myself with the program              |
| v2.0    | Frequent user         | Save my previous inputs                                               | Record over a period of time                             |

## Non-Functional Requirements
<!-- @@author mayfairmi6 -->  

| ID  | Requirement         | Description                                                                                | Rationale                                                                                   |
|-----|---------------------|--------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| 1   | Responsiveness      | The system should respond to user commands within 2 seconds.                               | Ensures efficient interaction and enhances user satisfaction.                               |
| 2   | Data Integrity      | The system must maintain accurate tracking and updating of financial entries.              | Prevents discrepancies in financial reporting, ensuring reliability.                        |
| 3   | User Error Handling | The system should provide clear error messages and support easy correction of user inputs. | Facilitates management of entries and reduces user frustration.                             |
| 4   | Customizability     | Users should be able to easily add and modify expense categories.                          | Allows users to tailor the system to their specific needs.                                  |
| 5   | Automated Tasks     | Support automated budget resets at the start of each month.                                | Minimizes user effort in maintaining accurate monthly tracking.                             |
| 6   | Accessibility       | The chat interface should be simple and intuitive.                                         | Ensures that all users can effectively interact with the system without extensive training. |

## Glossary
- **glossary item**: Definition

## Instructions for Manual Testing
{Provide instructions on how to conduct manual product testing, e.g., how to load sample data to be used for testing.}
