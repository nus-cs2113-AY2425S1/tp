# WheresMyMoney User Guide


## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features-)
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
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar tp.jar` command to run the application.

---

## Notes

- Text written in `SCREAMING_SNAKE_CASE` are user input fields.
- Text preceded with a `/` are flags that need to be inputted for the programme to recognise.
- When passing in a value with forward slashes (`/`) into an argument, make sure to escape it with `\`:
  - eg. `<command> /argument \/value` -> `argument`: `/value`
- Not all commands will give an output

---

## Features 

### Add an expense: `add`

Use the add command to add an expense.

Format:  `add [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`

Notes:
- `PRICE` is a decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` is an optional parameter that takes a text format of DD-MM-YYYY. If no `DATE` is specified, it will be defaulted to the current date.

Examples: 
- `add /price 4.50 /description chicken rice /category food`
- `add /price 1 /description bus ride /category transport /date 01-10-2024`

### Edit your expense: `edit`

Use the edit command to edit an expense.

Format: `edit [INDEX] [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`

Notes:
- `INDEX` is an integer. To find the index of an expense, use the `list` command to see the corresponding index.
- `PRICE` is a decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` is an optional parameter that takes a text format of DD-MM-YYYY.
- All parameters except `INDEX` are optional and the user can choose exactly which attribute of the expense they want to edit.
 
Examples: 
- `edit 1 /price 5.50 /description chicken rice /category food`
- `edit 2 /price 3.40`

### Delete an expense: `delete`

Use the delete command to delete an expense.

Format:  `delete [INDEX]`

Examples: `delete 2`

### Get a list of all your transactions: `list`

Use the list command to display expenses according to specified filters.

Format:  `list [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- `FROM_DATE` and `TO_DATE` are both inclusive of given date.
- Lists all expenses the user has if filters are not specified.
- Lists all expenses that pass through the filters if specified.

Examples: `list /category food /from 02-11-2024 /to 04-11-2024`

### Get statistics for your transactions: `stats`

Use the stats command to display statistics for expenses according to specified filters.

Format:  `stats [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- `FROM_DATE` and `TO_DATE` are both inclusive of given date.
- Lists statistics of all expenses the user has if filters are not specified.
- Lists statistics of all expenses that pass through the filters if specified.

Examples: `stats /category food /from 02-11-2024 /to 04-11-2024`

### Set a spending limit for a category `set`

Use the set command to set a spending limit for a category.

Format: `set [/category CATEGORY] [/limit LIMIT]`

Examples: `set /category food /limit 100` 

### View help: `help`

Use the help command to list the command formats that the app recognises.

Format: `help [/method METHOD]`

Notes:
- `METHOD` is text.
- `METHOD` exists in our app.

Examples:
- `help`              lists all commands the app has since `METHOD` is not specified.
- `help /method add` lists format of the “add” command since `METHOD` is specified.

### Save data to files: `save`

Use the save command to save data to files.

Format: `save [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`

Notes:
- If nothing at all is specified, it loads from the default paths:
  - `EXPENSE_FILE_PATH = "expenses_data.csv"`
  - `CATEGORY_FILE_PATH = "category_spending_limit.csv"`
  - `RECUR_FILE_PATH = "recurring_expenses_data.csv"`

Examples:
- `save`                         saves data to the default paths.
- `save /expenseList ./data.csv` saves only the expenseList to `./data.csv`.

### Load data from files: `load`

Use the load command to load data from files. 

Format: `load [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`

Notes:
- If nothing at all is specified, it loads from the default paths:
  - `EXPENSE_FILE_PATH = "expenses_data.csv"`
  - `CATEGORY_FILE_PATH = "category_spending_limit.csv"`
  - `RECUR_FILE_PATH = "recurring_expenses_data.csv"`
- It clears existing data on read for ease of usage.
- On read failure, it loads whatever it could read from the corrupted files.

Examples:
- `load`                         loads data from the default paths.
- `load /expenseList ./data.csv` loads only the expenseList from `./data.csv`.

---

## Recurring Expenses

Users can add automate the process of adding expenses that occur on a regular basis. 

### Add a recurring expense: `add`

Use the add command to add a recurring expense.

Format:  `add [/recur] [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE] [/frequency FREQUENCY]`

Notes:
- `/recur` is a command flag that indicates that the add command is meant to add recurring expenses and not normal expenses.
- `PRICE` is a decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` is an optional parameter that takes a text format of DD-MM-YYYY. If no `DATE` is specified, it will be defaulted to the current date.
- `FREQUENCY` takes only 1 of 3 possible inputs, `daily`, `weekly`, or `monthly`. Any other inputs will throw an error.

Examples: 
- `add /price 4.50 /description chicken rice /category food /frequency daily`
- `add /price 1 /description bus ride /category transport /date 01-10-2024 /frequency weekly`

### Edit your recurring expense: `edit`

Use the edit command to edit an expense.

Format: `edit INDEX [/recur] [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`

Notes:
- `INDEX` is an integer. To find the index of an expense, use the `list` command to see the corresponding index.
- `/recur` is a command flag that indicates that the add command is meant to add recurring expenses and not normal expenses.
- `PRICE` is a decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- `DATE` is an optional parameter that takes a text format of DD-MM-YYYY. If no `DATE` is specified, it will be defaulted to the current date.
- All parameters except `INDEX` and `/recur` are optional and the user can choose exactly which attribute of the expense that they want to edit.
 
Examples: 
- `edit 1 /price 5.50 /description chicken rice /category food`
- `edit 2 /price 3.40 /frequency monthly /date 02-10-2024`

### Delete an expense: `delete`

Use the delete command to delete a recurring expense.

Format:  `delete [/recur] [INDEX]`

Examples: `delete 2` 

### Get a list of all your transactions: `list`

Use the list command to display recurring expenses according to specified filters.

Format:  `list [/recur] [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `/recur` is a command flag that indicates that the add command is meant to add recurring expenses and not normal expenses
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- `FROM_DATE` and `TO_DATE` are both inclusive of given date.
- Lists all expenses the user has if filters are not specified.
- Lists all expenses that pass through the filters if specified.

Examples: `list /category food /from 02-11-2024 /to 04-11-2024`

### Save data to files: `save`

Works the same way as normal expenses

### Load data from files: `load`

On top of working the same way as normal expenses, this command also checks whether a recurring expense is past its due date and adds it as a normal expense to the expense list.

---

## FAQ

**Q1**: How do I transfer my data to another computer? 

**A1**: You can save your expenses to a `.csv` file, which you can then transfer and load on another computer 


**Q2**: Can I only update limits (via the set command)? What about adding, deleting or viewing?

**A2**: In short, yes. 

- Adding is automatically done when you add an expense with a category not already stored by the program, defaulted to $100.00. 
- Deletion is not possible as every category will have a corresponding limit. No category can exist without a limit.
- Viewing is not directly possible using commands, only indirectly by saving and viewing the csv file. 

---

## Command Summary

| Command                           | Format                                                                                                               | 
|-----------------------------------|----------------------------------------------------------------------------------------------------------------------|
| Add Expense                       | `add [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`                                    |
| Edit Expense                      | `edit INDEX [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`                             |
| Delete Expense                    | `delete [INDEX]`                                                                                                     |
| List Expenses                     | `list [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                          | 
| Get Statistics                    | `stats [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                         | 
| Set Spending Limit for a Category | `set [/category CATEGORY] [/limit LIMIT]`                                                                            |
| View Help                         | `help [/method METHOD]`                                                                                              |
| Save Expenses to a File           | `save [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`   |
| Load Expenses from a File         | `load [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`   |                                                             |
| Add Recurring Expense             | `add [/recur] [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE] [/frequency FREQUENCY]`    |
| Edit Recurring Expense            | `edit INDEX [/recur] [/price PRICE] [/description DESCRIPTION] [/category CATEGORY] [/date DATE]`                    |
| Delete Recurring Expense          | `delete [INDEX]`                                                                                                     |
| List Recurring Expenses           | `list [/recur] [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                 | 
