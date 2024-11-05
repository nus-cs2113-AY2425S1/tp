# WheresMyMoney User Guide


## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features-)
- [FAQ](#faq)
- [Others](#others)
- [Command Summary](#command-summary)

---

## Introduction

WheresMyMoney allows you to keep track of your spending habits and trends with various supporting tools and functionalities.

---

## Quick Start

1. Ensure you have Java 17 or above installed in your computer.
2. Download the latest .jar file from the Github Repository, under releases.
3. Copy the file to the folder you want to use as the home folder for your expenses.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar tp.jar` command to run the application.

---

## Features 

### Add an expense: `add`

Use the add command to add an expense.

Format:  `add [/price PRICE] [/description DESCRIPTION] [/category CATEGORY]`

Notes:
- `PRICE` is a decimal number.
- `DESCRIPTION` and `CATEGORY` are text.

Examples: `add /price 4.50 /description chicken rice /category food`

### Edit your expense: `edit`

Use the edit command to edit an expense.

Format: `edit INDEX [/price PRICE] [/description DESCRIPTION] [/category CATEGORY]`

Notes:
- `PRICE` is a decimal number.
- `DESCRIPTION` and `CATEGORY` are text.
- All parameters are optional and only the parameters that are inputted will be reflected after the edit.
 
Examples: `edit 1 /price 5.50 /description chicken rice /category food`

### Delete an expense: `delete`

Use the delete command to delete an expense.

Format:  `delete [INDEX]`

Examples: `delete 2`

### Set a spending limit for a category `set`

Use the set command to set a spending limit for a category.

Format: `set [/category CATEGORY] [/limit LIMIT]`

Examples: `set /category food /limit 100` 

### Get a list of all your transactions: `list`

Use the list command to display expenses according to specified filters.

Format:  `list [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- Lists all expenses the user has if filters are not specified.
- Lists all expenses that pass through the filters if specified.

Examples: `list /category food /from 02-11-2024 /to 04-11-2024`

### Get statistics for your transactions: `stats`

Use the stats command to display statistics for expenses according to specified filters.

Format:  `stats [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`

Notes:
- `CATEGORY` is text.
- `FROM_DATE` and `TO_DATE` are dates in DD-MM-YYYY format.
- Lists statistics of all expenses the user has if filters are not specified.
- Lists statistics of all expenses that pass through the filters if specified.

Examples: `stats /category food /from 02-11-2024 /to 04-11-2024`

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
 - `EXPENSE_FILE_PATH == "expenses_data.csv"`
 - `CATEGORY_FILE_PATH == "category_spending_limit.csv"`
 - `RECUR_FILE_PATH == "recurring_expenses_data.csv"`

Examples:
- `save`                         saves data to the default paths.
- `save /expenseList ./data.csv` saves only the expenseList to `./data.csv`.

### Load data from files: `load`

Use the load command to load data from files. 

Format: `load [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`

Notes:
- If nothing at all is specified, it loads from the default paths:
  - `EXPENSE_FILE_PATH == "expenses_data.csv"`
  - `CATEGORY_FILE_PATH == "category_spending_limit.csv"`
  - `RECUR_FILE_PATH == "recurring_expenses_data.csv"`
- It clears existing data on read for ease of usage.
- On read failure, it loads whatever it could read from the corrupted files.

Examples:
- `load`                         loads data from the default paths.
- `load /expenseList ./data.csv` loads only the expenseList from `./data.csv`.

---

## FAQ

**Q1**: How do I transfer my data to another computer? 

**A1**: You can save your expenses to a `.csv` file, which you can then transfer and load on another computer 


**Q2**: Can I only update limits (via the set command)? What about adding, deleting or viewing?

**A2**: In short, yes. 

- Adding is automatically done when you add an expense with a category not already stored by the program, defaulted to $100.00. 
- Deletion is not possible as every category will have a corresponding limit. No category can exist without a limit.
- Viewing is not directly possible using commands, only indirectly by saving and viewing the csv file. 

## Others 

When passing in a value with forward slashes (`/`) into an argument, make sure to escape it with `\`

eg. `<command> /argument \/value` -> `argument`: `/value` 

---

## Command Summary


| Command                           | Format                                                                                                               | 
|-----------------------------------|----------------------------------------------------------------------------------------------------------------------|
| Add Expense                       | `add [/price PRICE] [/description DESCRIPTION] [/category CATEGORY]`                                                 |
| Edit Expense                      | `edit INDEX [/price PRICE] [/description DESCRIPTION] [/category CATEGORY]`                                          |
| Delete Expense                    | `delete [INDEX]`                                                                                                     |
| List Expenses                     | `list [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                          | 
| Get Statistics                    | `stats [/category CATEGORY] [/from FROM_DATE] [/to TO_DATE]`                                                         | 
| Set Spending Limit for a Category | `set [/category CATEGORY] [/limit LIMIT]`                                                                            |
| View Help                         | `help [/method METHOD]`                                                                                              |
| Save Expenses to a File           | `save [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`   |
| Load Expenses from a File         | `load [/expenseList EXPENSE_FILE_PATH] [/categoryInfo CATEGORY_FILE_PATH] [/recurringExpenseList RECUR_FILE_PATH]`   |
