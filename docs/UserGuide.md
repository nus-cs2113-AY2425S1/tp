# uNivUSaver User Guide
## Table of Contents
1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   - [Adding](#adding)
     - [Add an expense: `add-expense`](#add-an-expense-add-expense)
     - [Add an income: `add-income`](#add-an-income-add-income)
     - [Add a category: `add-category`](#add-a-category-add-category)
     - [Add a budget: `add-budget`](#add-a-budget-add-budget)
   - [Deleting](#deleting)
     - [Delete an expense/income: `delete-transaction`](#delete-an-expenseincome-delete-transaction)
     - [Delete a category: `delete-category`](#delete-a-category-delete-category)
   - [Viewing](#viewing)
     - [List all categories: `view-category`](#list-all-categories-view-category)
     - [View expenses: `view-expense`](#view-expenses-view-expense)
     - [View income: `view-income`](#view-income-view-income)
     - [View transaction history: `history`](#view-transaction-history-history)
     - [View total amount in account: `view-total`](#view-total-amount-in-account-view-total)
     - [View command list: `help`](#view-command-list-help)
   - [Searching](#searching)
     - [Search transaction by keywords: `KeywordsSearchCommand`](#search-transaction-by-keywords-keywordssearchcommand)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)

---
## Introduction
uNivUSaver is a CLI-based software that helps students to develop a better habit of managing money, which will help you avoid using up your money before the end of month

## Quick Start
1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `uNivUSaver` from [here](https://github.com/AY2425S1-CS2113-W10-4/tp/releases/tag/v1.0).
3. Copy the file to the folder you want the program to stay in.
4. Open a command terminal, cd into the folder you put the jar file in, and use the ```java -jar uNivUSaver.jar``` command to start the program!
   
## Features
> **&#9432;** **NOTES ON THE COMMAND FORMAT:**
>- Words in UPPER_CASE are the parameters to be supplied by the user.
>- Parameters in [] are optional (e.g. `[f/ DATE]`)
>- The keywords must be followed by at least one space before adding the associated parameter (e.g. `d/ DATE`)
>- Parameters can be in any order, except for the parameters without keyword (e.g. `DESCRIPTION`)
>- Extraneous parameters for commands that do not take in parameters (such as help, list, bye and clear) will be ignored.
>- The date and time format for input is `dd/MM/yyyy [hhMM]`

### Adding
#### Add an expense: `add-expense`
- Add an amount of expense into the history.
- **Format:** `add-expense [DESCRIPTION] a/ AMOUNT [d/ DATE] [c/ CATEGORY]`
- **Tips:**
  - If the category is not entered, the programme will prompt you to enter a category or to leave it empty
  - You may either fill in a category already in the category list or create a new category if it is not present in the list
  - If the date is not entered, the system will take the current day.
- **Examples:**
  ```
  add-expense a/ 17 d/ 07.09.2024 c/ FnB
  add-expense Amusement park a/ 52 d/ 08.09.2024
  add-expense ChiCha San Chen a/ 6 d/ 09.09.2024 c/ FnB
  ```

#### Add an income: `add-income`
- Add an amount of income into the history.
- **Format:** `add-income [DESCRIPTION] a/ AMOUNT [d/ DATE]`
- **Tip:**
  - If the date is not entered, the system will take the current day.
- **Examples:**
  ```
  add-income Monthly allowance a/ 300 d/ 2024-09-19 1100
  add-income a/ 52 d/ 2024-09-19 1100
  ```

#### Add a category: `add-category`
- Add a category into the category list.
- **Format:** `add-category n/NAME`
- **Tip:**
  - Category name must be unique.
- **Examples:**
  ```
  add-category n/ FnB
  add-category n/ Laundry
  ```
#### Add a budget: `add-budget`
- Sets a monthly budget to track spending against.
- **Format:** `add-budget m/ MONTH a/ AMOUNT`
- **Tips:**
  - The month should be specified in the format `yyyy-MM` (e.g., `2024-11`).
  - The amount represents the budget limit for that month.
  - Budget can only be set for current or future months
- **Examples:**
  ```
  add-budget a/ 1000 m/ 2024-11 
  add-budget a/ 200 m/ 2025-02
  ```

---
### Deleting
#### Delete an expense/income: `delete-transaction`
- Delete an expense or income from the history.
- **Format:** `delete-transaction i/ INDEX`
- **Example:**
  ```
  delete-transaction i/ 7
  ```

#### Delete a category: `delete-category`
- Delete a category from the category list.
- **Format:** `delete-category i/ INDEX`
- **Example:**
  ```
  delete-category i/ 6
  ```
  
---
### Viewing

#### List all categories: `view-category`
- View the category list.
- **Format:** `view-category`

#### View expenses: `view-expense`
- View the expenses with optional start/end time and category. If left blank, full history will be shown.
- **Format:** `view-expense [c/ CATEGORY] [f/ DATE] [t/ DATE]`
- **Examples:**
  ```
  view-expense c/ food
  view-expense f/ 2024-09-16 t/ 2024-09-19
  ```

#### View income: `view-income`
- View the income in an optional start and end time. If start and end time are left blank, full history will be shown.
- **Format:** `view-income [f/ DATE] [t/ DATE]`
- **Example:**
  ```
  view-income f/ 2024-09-16 t/ 2024-09-19
  view-income
  ```

#### View transaction history: `history`
- View transaction history with an optional start and end time. If left blank, full history will be shown.
- **Format:** `history [f/ DATE] [t/ DATE]`
- **Examples:**
  ```
  history f/ 2024-09-16 t/ 2024-09-19
  history
  ```

---
### View total amount in account: `view-total`
- View the total amount of money currently in the account.
- **Format:** `view-total`

---
### View command list: `help`
- View all available commands in the application.
- **Format:** `help`

---
### Searching

#### Search transaction by keywords: `KeywordsSearchCommand`
- Search for transaction or transactions in the transactionList using one or multiple keywords.
- **Format:** `search k/ [keyword_1] [keyword_2] ... [keyword_n]`
- **Example:**
  ```
  search k/ school
  search k/ school fee ABC
  ```
  
### Tracking

#### Track the progress towards budget for a certain moth: `track m/ Month`
- Track your spending progress to make sure you are within the budget
- **Format:** `track m/ MONTH`
- **Tips:**
  - The month should be specified in the format `yyyy-MM` (e.g., `2024-11`).
  - Tracking of progress is only available for current or past months.
- **Example:**
  ```
  track m/ 2024-11
  track m/ 2020-10
  ```


## FAQ

## Command Summary
It seems like you're trying to format a table of commands, but the current format may not render properly in the application or website you're using. Here's a properly formatted version of your command list using markdown or a clean text format, which should display well in most environments:

### Command List

| **Command Word**     |                          **Syntax**                           |                     **Example**                     |
|----------------------|:-------------------------------------------------------------:|:---------------------------------------------------:|
| `add-expense`        | `add-expense [DESCRIPTION] a/ AMOUNT [d/ DATE] [c/ CATEGORY]` |      `add-expense a/ 17 d/ 07.09.2024 c/ FnB`       |
| `add-income`         |        `add-income [DESCRIPTION] a/ AMOUNT [d/ DATE]`         | `add-income Monthly allowance a/ 300 d/ 2024-09-19` |
| `add-category`       |                    `add-category n/ NAME`                     |                `add-category n/ FnB`                |
| `add-budget`         |                `add-budget a/ AMOUNT m/ MONTH`                |           `add-budget a/ 1000 m/ 2024-12`           |
| `delete-transaction` |                 `delete-transaction i/ INDEX`                 |              `delete-transaction i/ 7`              |
| `delete-category`    |                  `delete-category i/ INDEX`                   |               `delete-category i/ 6`                |
| `view-category`      |                        `view-category`                        |                   `view-category`                   |
| `view-expense`       |       `view-expense [c/ CATEGORY] [f/ DATE] [t/ DATE]`        |               `view-expense c/ food`                |
| `view-income`        |               `view-income [f/ DATE] [t/ DATE]`               |      `view-income f/ 2024-09-16 t/ 2024-09-19`      |
| `history`            |                 `history [f/ DATE] [t/ DATE]`                 |        `history f/ 2024-09-16 t/ 2024-09-19`        |
| `view-total`         |                         `view-total`                          |                    `view-total`                     |
| `help`               |                            `help`                             |                       `help`                        |
| `track`              |                       `track m/ MONTH`                        |                 `track m/ 2024-11`                  |           

