# Finance Buddy User Guide

## Contents
- [Finance Buddy User Guide](#finance-buddy-user-guide)
  - [Contents](#contents)
  - [Introduction](#introduction)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Help](#help)
    - [Set/Edit Budget](#setedit-budget)
    - [Add Transaction](#add-transaction)
    - [Edit Transaction](#edit-transaction)
    - [Delete Transaction](#delete-transaction)
      - [Example Usage](#example-usage)
    - [List Transactions](#list-transactions)
      - [List by Type](#list-by-type)
      - [List by Date](#list-by-date)
      - [Viewing budget](#viewing-budget)
    - [Saving Data](#saving-data)
  - [Command Summary](#command-summary)

## Introduction

Finance Buddy is a command-line interface application that allows university students
to log their daily expenditures to help manage their budgets.

Users can add, delete and edit income and expenditure logs into the app, as well as
list out all their logged transactions. Users are also able to set a monthly budget
for themselves in the app, and the app will notify them if
they have exceeded, or are close to exceeding their budget.

The current progress of the logging will be saved after each successful command.
Progress is restored when FinanceBuddy is started up each time.

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure that Java 17 or above is installed.
2. Download the latest version of `FinanceBuddy` [here](https://github.com/AY2425S1-CS2113-W14-3/tp/releases).
3. Download the `.jar` file and save it on the computer.
4. In the directory where the jar file is saved, open Terminal.
5. In Terminal, run `java -jar FinanceBuddy.jar`.
6. Happy logging!

## Features

### Help

Lists out the full list of usable commands.

**Format**: `help`

**Output**:

```
--------------------------------------------
List of commands:
--------------------------------------------
1. list [income|expense] [/from START_DATE] [/to END_DATE]
   - Shows a list of logged transactions
    - Also displays categories with highest income/expenditure, monthly
      budget and balance
    - Optional: Specify 'income' or 'expense' to filter the list
    - Optional: Specify start/end date to only list transactions before/after
      specified dates
2. expense DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]
   - Adds a new expense with an optional date and category
    - Categories include: FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, OTHER,
      UNCATEGORIZED
3. income DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]
   - Adds a new income with an optional date and category
    - Categories include: SALARY, INVESTMENT, GIFT, OTHER, UNCATEGORIZED
4. edit INDEX [/des DESCRIPTION] [/a AMOUNT] [/d DATE] [/c CATEGORY]
   - Edits the transaction at the specified INDEX with optional fields
5. delete INDEX
   - Deletes the transaction at the specified INDEX
6. budget
   - Set/modify your monthly budget
7. exit
   - Exits the program
8. help
   - Shows a list of all valid commands
--------------------------------------------
```
Note: The formatting of the above sample output is slightly different from what you will see in the program.
A few line breaks have been added to allow the sample output to fit within the boundaries of the PDF page.

<hr>
<div style="page-break-after: always;"></div>

### Set/Edit Budget

User can set a monthly budget when app is initialized and budget is not set, or by using the budget command.
If the user has already set a budget, the app will prompt the user to ask if they would like to modify their budget at start up.

Budget command can be used to set budget if `no` is keyed in for the initial prompt.
The command can also be used to edit budget after initial budget is set.

After budget is set by user, adding, deleting or editing expenses will show the budget and remaining balance for the month.
The budget amount and balance will also be viewable by the user under the list command.

**Format**: `budget`

**Example Usage**:

```
Would you like to set a budget? (yes/no)
--------------------------------------------
yes
--------------------------------------------
Please set your budget amount:
--------------------------------------------
1000
--------------------------------------------
Your budget has successfully been set to: $ 1000.00
Your current monthly balance is: $ 1000.00
--------------------------------------------
```
Initial budget setting option.

<br>

```
budget
--------------------------------------------
Your current budget is: $ 1000.00
Would you like to modify your budget? (yes/no)
--------------------------------------------
yes
--------------------------------------------
Please set your budget amount:
--------------------------------------------
2000
--------------------------------------------
Your budget has successfully been set to: $ 2000.00
Your current monthly balance is: $ 2000.00
--------------------------------------------
```
Budget modification option to change budget amount.

<hr>
<div style="page-break-after: always;"></div>

### Add Transaction

The `Add Transaction` command allows you to add either an income or expense entry to your financial list. You can specify details like a description, amount, date, and category for better tracking.

**Format**:
- **For Expense**: `expense DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]`
- **For Income**: `income DESCRIPTION /a AMOUNT [/d DATE] [/c CATEGORY]`

<a id="param_details"></a>
**Parameter Details**:
- `DESCRIPTION`: A brief label describing the transaction (e.g., "Lunch" or "Freelance Work").
- `AMOUNT`: The transaction amount. This should be a positive value.
  - Note: Input amounts are rounded off to the nearest 2 decimal places.
  - Note: The app does not allow transactions to have an amount greater than $9999999.00 after rounding of to the nearest 2 d.p.
- `DATE` (optional): Date of the transaction in `dd/MM/yy` format. If omitted, today’s date is used.
  - Note: The app does not allow transactions to be entered with a date later than the system date.
- `CATEGORY` (optional): Specifies a category for the transaction, aiding in detailed financial tracking. If omitted, it defaults to `UNCATEGORIZED`.

**Category Options**:
- **Expense**: Categories include `FOOD`, `TRANSPORT`, `ENTERTAINMENT`, `UTILITIES`, `OTHERS`, and `UNCATEGORIZED`.
- **Income**: Categories include `SALARY`, `INVESTMENT`, `GIFT`, `OTHERS`, and `UNCATEGORIZED`.

**Examples Usage**:

```
expense Lunch /a 10.50 /d 12/10/24 /c FOOD
--------------------------------------------
Got it! I've added this expense:
[Expense] - Lunch $ 10.50 (on 12/10/24) [FOOD]
--------------------------------------------
```
Adds an expense of $10.50 for lunch on October 12, 2024, categorized as FOOD.

```
income Freelance Work /a 500 /d 15/10/24 /c SALARY
--------------------------------------------
Got it! I've added this income:
[Income] - Freelance Work $ 500.00 (on 15/10/24) [SALARY]
--------------------------------------------
```
Adds an income of $500 from freelance work on October 15, 2024, categorized as SALARY.

Examples Without Optional Parameters (Date and Category omitted):<br>

```
expense Coffee /a 8.00
--------------------------------------------
Got it! I've added this expense:
[Expense] - Coffee $ 8.00 (on 09/11/24) [UNCATEGORIZED]
--------------------------------------------
```
Adds an expense of $8.00 for coffee, using today’s date and the default category UNCATEGORIZED.

```
income Gift Money /a 200
--------------------------------------------
Got it! I've added this income:
[Income] - Gift Money $ 200.00 (on 09/11/24) [UNCATEGORIZED]
--------------------------------------------
```
Adds an income of $200 from a gift, using today’s date and the default category UNCATEGORIZED.

<hr>
<div style="page-break-after: always;"></div>

### Edit Transaction
Edits an existing transaction in your financial list.

**Format**: `edit INDEX [/des DESCRIPTION] [/a AMOUNT] [/d DATE] [/c CATEGORY]`

 - Edits the transaction at the specified `INDEX`. `INDEX` refers to the index number shown in the displayed financial list when [`list`](#list-transactions) is called. 
 - `INDEX` must be a positive integer.

**Parameter Details:** (Refer to [here](#param_details) for what each parameter represents)
 - `DESCRIPTION` (optional): 
   - shouldn't be blank if provided.
   - DO NOT USE `¦¦` in `DESCRIPTION` cause it serve as the seperator token in storage file.
 - `AMOUNT` (optional):
   - Must be a positive number with a maximum value of $9999999.00. If it's a floating-point number, it will be rounded to two decimal places.
 - `DATE` (optional):
   - Should follow `DD/MM/YY` format and cannot be after the system date.
 - `CATEGORY` (optional):
   - Should be one of the categories allowed in Expenses/Incomes.


**Example Usages**:

```
edit 1 /des breakfast
--------------------------------------------
Got it. I've edited this expense:
[Expense] - breakfast $ 10.50 (on 12/10/24) [FOOD]
--------------------------------------------
```
Edits the description of the 1st entry to be breakfast.

<br>

```
edit 1 /a 5.99
--------------------------------------------
Got it. I've edited this expense:
[Expense] - breakfast $ 5.99 (on 12/10/24) [FOOD]
--------------------------------------------
```
Edits the amount of the 1st entry to be 5.99.

<br>

```
edit 2 /des lunch /a 20
--------------------------------------------
Got it. I've edited this expense:
[Expense] - lunch $ 20.00 (on 12/10/24) [FOOD]
--------------------------------------------
```
Edits the description and amount of the 2nd entry to be lunch and 20 respectively.

<br>

```
edit 3 /des dinner /d 11/09/24
--------------------------------------------
Got it. I've edited this expense:
[Expense] - dinner $ 8.00 (on 11/09/24) [UNCATEGORIZED]
--------------------------------------------
```
Edits the description and date of the 3rd entry to be dinner and 11/09/2024 respectively.

<br>

```
edit 4 /des breakfast /a 5 /d 12/09/24
--------------------------------------------
Got it. I've edited this expense:
[Expense] - breakfast $ 5.00 (on 12/09/24) [UNCATEGORIZED]
--------------------------------------------
```
Edits the description, amount, and date of the 4th entry to be breakfast, 5 and 12/09/2024 respectively.

<br>

```
edit 5 /c FOOD
--------------------------------------------
Got it. I've edited this expense:
[Expense] - bubble tea $ 4.00 (on 07/11/24) [FOOD]
--------------------------------------------
```
Edits the category of the 5th entry to be FOOD.

<hr>
<div style="page-break-after: always;"></div>

### Delete Transaction

The `Delete Transaction` command removes a specific entry from your financial list. This command uses the entry’s index to identify and delete it from the list.

**Format**: `delete INDEX`

**Parameter Details**:
- `INDEX`: The position of the transaction in the financial list, as displayed by the [`list`](#list-transactions) command. Using an invalid index will produce an error.

#### Example Usage

```
delete 5
--------------------------------------------
Okay! The following entry has been deleted:
[Income] - TA Allowance $ 3000.00 (on 09/11/24) [UNCATEGORIZED]
--------------------------------------------
```
Deletes the transaction at index 5 in the financial list.

<hr>
<div style="page-break-after: always;"></div>

### List Transactions

Lists out transactions in your financial list in ascending order of date for your perusal. Transactions can be 
filtered by type (income/expense) or restricted to a stipulated period. The app will display the total
cashflow/expenditure/income during the stipulated period depending on the transaction type selected to be listed,
as well as the category with the highest total expenditure/income.

**Format:** `list [expense|income] [/from START_DATE] [/to END_DATE]`

<br>

#### List by Type

User can command app to list out only expenses, only incomes or both expenses and incomes.

**Example Usage:**

```
list
--------------------------------------------
Here's a list of all recorded entries:
1. [Expense] - lunch $ 3.50 (on 22/09/24) [FOOD]
2. [Income] - salary $ 3000.00 (on 03/10/24) [SALARY]
3. [Expense] - dinner $ 4.50 (on 05/10/24) [FOOD]
4. [Expense] - movie $ 20.00 (on 10/10/24) [ENTERTAINMENT]
5. [Income] - allowance $ 100.00 (on 27/10/24) [GIFT]
6. [Income] - ang pow money $ 15.00 (on 01/11/24) [GIFT]

Net cashflow: $ 3087.00
                
Highest Expense Category: ENTERTAINMENT ($20.00)
Highest Income Category: SALARY ($3000.00)
--------------------------------------------
No budget has been set.
--------------------------------------------
```
Lists out all expenses and incomes. 
Displays total cashflow (income - expenditure), and shows categories with the highest total expenditure and income respectively.

<br>

```
list expense
--------------------------------------------
Here's a list of all recorded expenses:
1. [Expense] - lunch $ 3.50 (on 22/09/24) [FOOD]
2. [Expense] - dinner $ 4.50 (on 05/10/24) [FOOD]
3. [Expense] - movie $ 20.00 (on 10/10/24) [ENTERTAINMENT]

Total expense: $ 28.00

Highest Expense Category: ENTERTAINMENT ($20.00)
--------------------------------------------
No budget has been set.
--------------------------------------------
```
Lists out all expenses. Displays total expenditure, and shows category with highest total expenditure.

`list income` functions similarly, but displays:
- all incomes
- total income
- category with highest total income

<br>

#### List by Date

User can command app to only list out transactions starting from a certain date using the `/from` flag, 
and/or up to a certain date using the `/to` flag.

Total cashflow/expenditure/income displayed will be restricted to the range of dates entered by the user.
Category with highest expenditure/income displayed will also be based on the entered date range.

**Example Usage:**

```
list /from 03/10/24 /to 10/10/24
--------------------------------------------
Here's a list of all recorded entries:
1. [Income] - salary $ 3000.00 (on 03/10/24) [SALARY]
2. [Expense] - dinner $ 4.50 (on 05/10/24) [FOOD]
3. [Income] - allowance $ 100.00 (on 10/10/24) [GIFT]

Net cashflow: $ 3095.50
                
Highest Expense Category: FOOD ($4.50)
Highest Income Category: SALARY ($3000.00)
--------------------------------------------
No budget has been set.
--------------------------------------------
```
Lists out all expenses and incomes with date equal to or after 03/10/24.
Displays total cashflow (income - expenditure) during that period, and shows
categories with the highest total expenditure and income during that period respectively.

The `expense` and `income` keywords can also be used in conjunction with the
`/from` and `/to` flags to filter by both transaction type and date.

<br>

#### Viewing budget

If a budget has been set by the user (refer to section on [Set/Edit Budget](#setedit-budget), the user's set monthly 
budget as well as his/her balance (budget - total expenditure that month) will be displayed when the `list` command is executed.

**Example Usage:**

```
list
--------------------------------------------
Here's a list of all recorded entries:
1. [Expense] - lunch $ 3.50 (on 22/09/24) [FOOD]
2. [Income] - salary $ 3000.00 (on 03/10/24) [SALARY]
3. [Expense] - dinner $ 4.50 (on 05/10/24) [FOOD]
4. [Expense] - movie $ 20.00 (on 10/10/24) [ENTERTAINMENT]
5. [Income] - allowance $ 100.00 (on 27/10/24) [GIFT]
6. [Income] - ang pow money $ 15.00 (on 01/11/24) [GIFT]

Net cashflow: $ 3087.00
                
Highest Expense Category: ENTERTAINMENT ($20.00)
Highest Income Category: SALARY ($3000.00)
--------------------------------------------
Your budget has successfully been set to: $ 2000.00
Your current monthly balance is : $ 2000.00
--------------------------------------------
```
Viewing budget/remaining balance using `list`. Note that the above
is in the context of the user using the app in November 2024. (i.e. budget is for 11/2024)

<hr>

### Saving Data
Your Financial List will be stored in to `data/FinancialList.txt`, while your budget is stored in `data/Budget.txt`.
FinanceBuddy will automatically update the files whenever your list or budget been modified through FinanceBuddy.
When you start the FinanceBuddy program, it will check if the `data/FinancialList.txt` and `data/Budget.txt` exist.
If do, it'll try to load the transactions and budget in the file row by row.
Please do not modify these files manually, otherwise the transactions or the budget with incorrect format will not be loaded.

<div style="page-break-after: always;"></div>

## Command Summary

| **Command**                   | **Usage**                                                                                  |
|-------------------------------|---------------------------------------------------------------------------------------------|
| **Help**                      | `help`                                                                                     |
| **List All Transactions**     | `list`                                                                                      |
| **List Income Transactions**  | `list income`                                                                               |
| **List Expense Transactions** | `list expense`                                                                              |
| **List Transactions by Date** | `list [/from START_DATE] [/to END_DATE]`                                                    |
| **Add Expense**               | `expense DESCRIPTION /a AMOUNT`                                                             |
| **Add Expense with Date**     | `expense DESCRIPTION /a AMOUNT /d DATE`                                                     |
| **Add Expense with Category** | `expense DESCRIPTION /a AMOUNT /c CATEGORY`                                                 |
| **Add Expense with Date and Category** | `expense DESCRIPTION /a AMOUNT /d DATE /c CATEGORY`                                |
| **Add Income**                | `income DESCRIPTION /a AMOUNT`                                                              |
| **Add Income with Date**      | `income DESCRIPTION /a AMOUNT /d DATE`                                                      |
| **Add Income with Category**  | `income DESCRIPTION /a AMOUNT /c CATEGORY`                                                  |
| **Add Income with Date and Category**  | `income DESCRIPTION /a AMOUNT /d DATE /c CATEGORY`                                 |
| **Edit Entry**                | `edit INDEX`                                                                               |
| **Edit Entry Description**    | `edit INDEX /des DESCRIPTION`                                                               |
| **Edit Entry Amount**         | `edit INDEX /a AMOUNT`                                                                      |
| **Edit Entry Date**           | `edit INDEX /d DATE`                                                                       |
| **Edit Entry Category**       | `edit INDEX /c CATEGORY`                                                                    |
| **Edit Entry with All Fields**| `edit INDEX /des DESCRIPTION /a AMOUNT /d DATE /c CATEGORY`                                 |
| **Delete Entry**              | `delete INDEX`                                                                             |
| **Set Budget**                | `budget`                                                                                   |
| **Exit Program**              | `exit`                                                                                     |

**Defined Categories**:

| **Category Type**  | **Categories**                                  |
|--------------------|-------------------------------------------------|
| **Expense**        | FOOD, TRANSPORT, ENTERTAINMENT, UTILITIES, OTHER, UNCATEGORIZED |
| **Income**         | SALARY, INVESTMENT, GIFT, OTHER, UNCATEGORIZED  |

