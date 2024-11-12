# WheresMyMoney User Guide


## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Notes](#notes)
- [Features - Expenses](#features---expenses)
- [Features - Recurring Expenses](#features---recurring-expenses)
- [Features - Storage](#features---storage)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Introduction

WheresMyMoney allows you to keep track of your spending habits and trends with various supporting tools and functionalities.

---

## Quick Start

1. Ensure you have Java 17 or above installed in your computer.
2. Download the latest .jar file from the GitHub Repository, under releases.
3. Copy the file to the folder you want to use as the home folder for your expenses.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar [CS2113-W12-3][WheresMyMoney].jar` command to run the application.

---

<div style="page-break-after: always;"></div>

## Notes

- Text written in `SCREAMING_SNAKE_CASE` are user input fields.
- Text preceded with a `/` are flags that need to be inputted for the programme to recognise the argument.
- Square brackets `[...]` indicate optional arguments. Refer to the specifications for each command.
- Values in the argument can include spaces.
  - e.g. `<command> /argument value 1 and 2` -> `argument`: `value 1 and 2`
  - e.g. `<command> main value` -> `main`: `main value` (The main argument is the value following the command, such as `INDEX` in `delete INDEX`)
- When passing in a value with forward slashes (`/`) into an argument, make sure to escape it with `\`.
  - e.g. `<command> /argument \/value` -> `argument`: `/value`
- The program only officially supports values till 9999.99 (excluding), and to 2 decimal places. 
  - Inaccuracies may occur due to the implementation of floating point numbers used.
  - However, we allow the usage of numbers above the range for advanced users. 
  - We will give a warning on user input, and no warning on csv load/ other operations
- Commands default to no output on successful completion unless
  - The command produces an output by its nature (eg. `list`) or
  - There is an error in the command.

---

<div style="page-break-after: always;"></div>

## Features - Expenses

### Add an expense: `add`

Adds an expense to the tracker.

Format:  `add /price PRICE /description DESCRIPTION /category CATEGORY [/date DATE]`

Notes:
- `PRICE` is a positive decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` is optional and formatted as `DD-MM-YYYY`. 
If no `DATE` is specified, it will be defaulted to the current (system) date.

Examples: 
- `add /price 4.50 /description chicken rice /category food`
- `add /price 1 /description bus ride /category transport /date 01-10-2024`

### Edit your expense: `edit`

Edits an existing expense.

Format: `edit INDEX [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`

Notes:
- `INDEX` is an integer. You can use the `list` command to find the corresponding index.
- `PRICE` is a positive decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` takes a text format of `DD-MM-YYYY`.
- All parameters except `INDEX` are optional. You can specify only edited attributes.
- `edit INDEX ` (i.e. blank optional parameters) has no effect.
 
Examples: 
- `edit 1 /price 5.50 /description chicken rice /category food`
- `edit 2 /price 3.40`

### Delete an expense: `delete`

Deletes an expense from the system.

Format:  `delete INDEX`

You can use the `list` command to see the corresponding index.

Example: `delete 2`

### Get a list of all your transactions: `list`

Displays all expenses with an optional filter.

Format:  `list [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- `FROM_DATE` and `TO_DATE` are both inclusive of given date.
- Lists all expenses the user has if filters are not specified.
- Lists all expenses that pass through the filters if specified.

Example: 
```
> list
1. CATEGORY: food, DESCRIPTION: chicken rice, PRICE: 4.50, DATE ADDED: 09-11-2024
2. CATEGORY: transport, DESCRIPTION: bus ride, PRICE: 1.00, DATE ADDED: 01-10-2024
> list /category food /from 02-11-2024 /to 09-11-2024
1. CATEGORY: food, DESCRIPTION: chicken rice, PRICE: 4.50, DATE ADDED: 09-11-2024
> 
```


### Get statistics for your transactions: `stats`

Provides statistics for expenses according to specified filters.

Format:  `stats [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- `FROM_DATE` and `TO_DATE` are both inclusive of given date.
- Lists statistics of all expenses the user has if filters are not specified.
- Lists statistics of all expenses that pass through the filters if specified.

Example: 

```
> stats /category food /from 02-11-2024 /to 09-11-2024
HIGHEST EXPENSE:4.5
2. CATEGORY: food, DESCRIPTION: chicken rice, PRICE: 4.50, DATE ADDED: 04-11-2024
LOWEST EXPENSE:4.5
2. CATEGORY: food, DESCRIPTION: chicken rice, PRICE: 4.50, DATE ADDED: 04-11-2024
TOTAL EXPENSES: 27.0
AVERAGE PRICE: 4.5
> 
```

### Visualize your expenditures: `visualize`

Displays a bar graph showing your total expenditure over time, filtered by certain criteria.

Format:  `visualize [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in `DD-MM-YYYY` format.
- The command will filter out all expenses that satisfy your constraint and draw a bar graph.
If the time range is small (under one month), a daily graph will be displayed. Otherwise, a monthly graph will be used.

Note that this command currently has a time span limit of `1080 days` (approximately 36 months). Exceeding this limit will return an error.

Examples: 
- `visualize`
- `visualize /from 01-02-2024 /category food`

### Set a spending limit for a category: `set`

Allows you to set a spending limit for each category.
If this limit is exceeded (or nearly exceeded), you will receive warnings from the app.

Format: `set /category CATEGORY /limit LIMIT`

Example: `set /category food /limit 100` 

### View help: `help`

Lists the command formats that the app recognises.

Format: `help [/recur] [/method METHOD]`

Notes:
- `METHOD` is text.
- `METHOD` exists in our app.
- Use the `/recur` flag to get information on the methods for recurring expenses

Examples:
- `help`              lists all commands the app has since `METHOD` is not specified.
- `help /method add` lists format of the “add” command since `METHOD` is specified.
- `help /recur /method edit` lists format of the "edit" command since `/recur` and `METHOD` are specified.

```
> help /method add
Use the add command to add an expense.
Format:  add /price PRICE /description DESCRIPTION /category CATEGORY [/date DATE]
Notes:
    - PRICE is a decimal number.
    - DESCRIPTION and CATEGORY are text.
    - DATE is a string in DD-MM-YYYY format.
    - If no date is specified, it will be defaulted to the current date.
Examples: add /price 4.50 /description chicken rice /category food /date 01-01-2024

>
```
<br>

---

<div style="page-break-after: always;"></div>

## Features - Recurring Expenses

Recurring expenses allow you to automate adding expenses that occur on a regular basis.

Recurring expenses are saved to a separate `recurringExpenseList`. They do not affect calculations and visualizations.

Recurring expenses share some of the same commands as normal expenses. Such as:
- `add`
- `edit`
- `delete`
- `list`

To use these command for recurring expenses, a `/recur` flag must be added.

Only when you run the `load` command will "normal expenses" added to the expense list.

### Add a recurring expense: `add`

Adds a recurring expense to the system.

Format:  `add /recur /price PRICE /description DESCRIPTION /category CATEGORY [/date DATE] /frequency FREQUENCY`

Notes:
- `/recur` is a command flag indicating that the command is for a recurring expense.
- `PRICE` is a positive decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` takes a text format of `DD-MM-YYYY`. If no `DATE` is specified, it will be defaulted to the current date.
- `FREQUENCY` takes only 1 of 3 possible inputs: `daily`, `weekly`, or `monthly`. Any other input will throw an error.
- Adding a recurring expense will only add a singular normal expense for that specified date (or current date if a date was not specified). All other valid expenses will by added after a `save` and a `load` command is used.
  - The `save` command is needed to register the recurring expense into the system.
  - The `load` command is used to trigger the mechanism to add all other valid expenses according to the date specified. More details can be found in the Developer Guide.

Examples: 
- `add /recur /price 4.50 /description chicken rice /category food /frequency daily`
- `add /recur /price 15 /description Spotify /category subscription /date 01-10-2024 /frequency monthly`

### Edit a recurring expense: `edit`

Allows you to edit a recurring expense.

Format: `edit INDEX /recur [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE] [/frequency FREQUENCY]`

Notes:
- `INDEX` is an integer. Use the `list` command to find the corresponding index.
- `/recur` is a command flag indicating that the command is for a recurring expense.
- `PRICE` is a positive decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` takes a text format of `DD-MM-YYYY`. If no `DATE` is specified, it will be defaulted to the current date.
- `FREQUENCY` takes only 1 of 3 possible inputs: `daily`, `weekly`, or `monthly`. Any other input will throw an error.
- All parameters except `INDEX` and `/recur` are optional. You can specify which attribute of the expense you want to edit.
- Editing a recurring expense will not edit the normal expenses that are asscociated with the recurring expense. You will need to edit the normal expenses yourself.
 
Examples: 
- `edit 1 /recur /price 5.50 /description chicken rice /category food`
- `edit 2 /recur /price 3.40 /frequency monthly /date 02-10-2024`

### Delete a recurring expense: `delete`

Deletes a recurring expense. Use `list /recur` to find the corresponding index.

Format:  `delete INDEX /recur`

Notes:
- Deleting a recurring expense will not delete the normal expenses that are associated with the recurring expense. You will need to delete the normal expenses yourself.

Example: `delete 2 /recur`

### Get a list of all your transactions: `list`

Use the list command to display recurring expenses according to specified filters.

Format:  `list /recur [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `/recur` is a command flag indicating that the command is for recurring expenses.
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in `DD-MM-YYYY` format.
- `FROM_DATE` and `TO_DATE` are both inclusive of given date.
- Lists all recurring expenses that satisfy the given filter (if any).

Examples: `list /recur /category food /from 02-11-2024 /to 04-11-2024`


---

<div style="page-break-after: always;"></div>

## Features - Storage

### Save data to files: `save`

Saves data to files and stores them in a directory of your choice.

Format: `save [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`

Notes:
- If nothing at all is specified, it loads from the default paths:
  - `EXPENSE_FILE_PATH = "expenses_data.csv"`
  - `CATEGORY_FILE_PATH = "category_spending_limit.csv"`
  - `RECUR_FILE_PATH = "recurring_expenses_data.csv"`
- If some of the fields are specified, only the corresponding field(s) are saved.
- The save files are designed to be human-readable and editable. Be careful of the syntax while editing!
- Users are allowed to save to paths with their desired file extension in the csv file format.
  - They do not have to save to a `.csv` file. (e.g. they can save to `expenses_data.txt`)

Examples:
- `save`                         saves all data to the default paths.
- `save /expenseList ./data.csv` saves only the expenseList to `./data.csv`.

### Load data from files: `load`

Loads data from files into the app.

Format: `load [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`

Notes:
- If nothing at all is specified, it loads from the default paths:
  - `EXPENSE_FILE_PATH = "expenses_data.csv"`
  - `CATEGORY_FILE_PATH = "category_spending_limit.csv"`
  - `RECUR_FILE_PATH = "recurring_expenses_data.csv"`
- It clears existing data on read for ease of usage.
- On read failure, it loads whatever it could read from the corrupted files.
- Users are allowed to load from files with their desired file extension, as long as it follows the csv file format.
  - They do not have to load from `.csv` files (e.g. they can load from `expenses_data.txt`)
- For recurring expenses, It checks whether a recurring expense is past its due date and adds it as a normal expense to the expense list.

Examples:
- `load`                         loads data from the default paths.
- `load /expenseList ./data.csv` loads only the expenseList from `./data.csv`.

---

<div style="page-break-after: always;"></div>

## FAQ

**Q1**: How do I transfer my data to another computer? 

**A1**: You can save your expenses to a `.csv` file, which you can then transfer and load on another computer 


**Q2**: Can I only update limits (via the set command)? What about adding, deleting or viewing?

**A2**: In short, yes. 

- Adding is automatically done when you add an expense with a category not already stored by the program, defaulted to $100.00. 
- Deletion is not possible as every category will have a corresponding limit. No category can exist without a limit.
- Viewing is not directly possible using commands, only indirectly by saving and viewing the csv file. 

**Q3**: Why don't my total expense per category reset at the start of every month?

**A3**: The backend currently does not support different totals per month, but it will be implemented soon.

**Q4**: Why does my total expenditure for this category not exactly match what I've calculated? /  Why does the alerts/warnings show up even though my total is not yet nearing or exceeding the limit?

**A4**: The backend uses floats to represent prices, which may cause inaccuracies of about 1 cent.

---

<div style="page-break-after: always;"></div>

## Command Summary

| Command                           | Format                                                                                                                 | 
|-----------------------------------|------------------------------------------------------------------------------------------------------------------------|
| Add Expense                       | `add /price PRICE /description DESCRIPTION /category CATEGORY [/date DATE]`                                            |
| Edit Expense                      | `edit INDEX [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`                               |
| Delete Expense                    | `delete INDEX`                                                                                                         |
| List Expenses                     | `list [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                            | 
| Get Statistics                    | `stats [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                           | 
| Visualize Expenditures            | `visualize [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                       |
| Set Spending Limit for a Category | `set /category CATEGORY /limit LIMIT`                                                                                  |
| View Help                         | `help [/recur] [/method METHOD]`                                                                                       |
| Save Expenses to a File           | `save [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`     |
| Load Expenses from a File         | `load [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`     |
| Add Recurring Expense             | `add /recur /price PRICE /description DESCRIPTION /category CATEGORY /date DATE /frequency FREQUENCY`                  |
| Edit Recurring Expense            | `edit INDEX /recur [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE] [/frequency FREQUENCY]` |
| Delete Recurring Expense          | `delete INDEX /recur`                                                                                                  |
| List Recurring Expenses           | `list /recur [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                     | 
